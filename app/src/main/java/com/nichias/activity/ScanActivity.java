package com.nichias.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.nichias.utils.CommonFunctions;

import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class ScanActivity extends AppCompatActivity implements ZBarScannerView.ResultHandler {
    private ZBarScannerView mScannerView;
    private String from = "";

    //camera permission is needed.
    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZBarScannerView(this);    // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view

        if (getIntent().getExtras() != null){
            from = getIntent().getExtras().getString("from");
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void handleResult(me.dm7.barcodescanner.zbar.Result result) {
        // Do something with the result here
        Log.v("kkkk", result.getContents()); // Prints scan results
        Log.v("uuuu", result.getBarcodeFormat().getName()); // Prints the scan format (qrcode, pdf417 etc.)

      //  Toast.makeText(ScanActivity.this,result+"", Toast.LENGTH_LONG).show();

        CommonFunctions.BAR_CODE_VALUE = result.getContents();

        if (from.equals("rmpoka_mold")){
            CommonFunctions.RM_POKA_MOLD = result.getContents();
            finish();
        }
        else if (from.equals("rmpoka_machine")){
            CommonFunctions.RM_POKA_MACHINE_NO = result.getContents();
            finish();
        }
        else if (from.equals("rminventoryDate")) {
            CommonFunctions.DATA_TIME = result.getContents();
            finish();
        }
        else if (from.equals("rminventory") || from.equals("rmstorage") || from.equals("rmpoka_rm")){
             /*
PM9429011
Roll No:PM9429011
Lot No:D3619PM-1
Size: 0.4   x 87.5   x 870 m
Managed No:726804
 */
            String split[] = result.getContents().split(":");
            String tempF = split[0];
            String tempR = split[1];
            String tempL = split[2];
            String tempS = split[3];

            String tempManaged_No = split[4];

            //Managed
            String splitR[] = tempR.split("L");
            String AnsR = splitR[0];
            //Lot No
            String splitL[] = tempL.split("Siz");
            String AnsL = splitL[0];
            //Size
            String splitS[] = tempS.split("Managed");
            String AnsS = splitS[0];

            CommonFunctions.RollNo = AnsR;
            CommonFunctions.LotNo = AnsL;
            CommonFunctions.Size = AnsS;
            CommonFunctions.ManagedNo = tempManaged_No;

            finish();
        }

        Bundle bundle = new Bundle();
        bundle.putString("from","scaning");
        bundle.putString("barCode",result.getContents().replace("http://",""));
        //CommonFunctions.getInstance().newIntent(ScanActivity.this, LookingForSomething.class, bundle, true,false);

    }


}