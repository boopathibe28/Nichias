package com.nichias.activity;

import static com.nichias.utils.CommonFunctions.BAR_CODE_VALUE;
import static com.nichias.utils.CommonFunctions.BAR_CODE_VALUE_FG;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.nichias.R;
import com.nichias.utils.CommonFunctions;

import java.io.IOException;

public class ScannedBarcodeActivity extends AppCompatActivity {


    SurfaceView surfaceView;
    TextView txtBarcodeValue;
    private BarcodeDetector barcodeDetector;
    private CameraSource cameraSource;
    private static final int REQUEST_CAMERA_PERMISSION = 201;
    Button btnAction;
    String intentData = "";
    boolean isEmail = false;
    private String from = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_barcode);

        if (getIntent().getExtras() != null){
            from = getIntent().getExtras().getString("from");
        }

        initViews();
    }

    private void initViews() {
        txtBarcodeValue = findViewById(R.id.txtBarcodeValue);
        surfaceView = findViewById(R.id.surfaceView);
        btnAction = findViewById(R.id.btnAction);


        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (intentData.length() > 0) {
                    if (isEmail) {
                        //startActivity(new Intent(ScannedBarcodeActivity.this, EmailActivity.class).putExtra("email_address", intentData));
                    }
                    else {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(intentData)));
                    }
                }


            }
        });
    }

    private void initialiseDetectorsAndSources() {

        Toast.makeText(getApplicationContext(), "Barcode scanner started", Toast.LENGTH_SHORT).show();

        barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.ALL_FORMATS)
                .build();

        cameraSource = new CameraSource.Builder(this, barcodeDetector)
                .setRequestedPreviewSize(1920, 1080)
                .setAutoFocusEnabled(true) //you should add this feature
                .build();

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (ActivityCompat.checkSelfPermission(ScannedBarcodeActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        cameraSource.start(surfaceView.getHolder());
                    } else {
                        ActivityCompat.requestPermissions(ScannedBarcodeActivity.this, new
                                String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });


        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
                //Toast.makeText(getApplicationContext(), "To prevent memory leaks barcode scanner has been stopped", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if (barcodes.size() != 0) {
                    txtBarcodeValue.post(new Runnable() {
                        @Override
                        public void run() {
                            if (barcodes.valueAt(0).email != null) {
                                txtBarcodeValue.removeCallbacks(null);
                                intentData = barcodes.valueAt(0).email.address;
                                txtBarcodeValue.setText(intentData);
                                isEmail = true;
                                btnAction.setText("ADD CONTENT TO THE MAIL");
                            } else {
                                isEmail = false;
                                btnAction.setText("LAUNCH URL");
                                intentData = barcodes.valueAt(0).displayValue;
                                txtBarcodeValue.setText(intentData);

                                NextMethod(intentData);

                            }
                        }
                    });

                }
            }
        });
    }

    private void NextMethod(String intentData) {
        CommonFunctions.BAR_CODE_VALUE = intentData;
        Toast.makeText(ScannedBarcodeActivity.this,BAR_CODE_VALUE,Toast.LENGTH_LONG);
        if (from.equals("1")){
            if (intentData.contains(" X ")){
                intentData =  intentData.replace(" X ","X");
            }
            String split[] = intentData.split(" ");

            CommonFunctions.ansCoilNo = split[0];
            if (split.length > 1) {
                CommonFunctions.ansSize = split[1];
                CommonFunctions.ansWeight = split[2];
            }
            finish();
        }
        else if (from.equals("4")){
            CommonFunctions.ssiplID = intentData;
            finish();
        }
        else if (from.equals("5")){
            if (intentData != null && !intentData.isEmpty()){
                char c= intentData.charAt(3);
                if (Character.isAlphabetic(c)) {
                    CommonFunctions.ssiplID = intentData;
                }
                else {
                    CommonFunctions.locationID = intentData;
                }
                finish();
            }
        }
        else if (from.equals("6")){
            String split[] = intentData.split("/");
            CommonFunctions.ssiplID = split[1];
            finish();
        }
        else if (from.equals("7")){
            CommonFunctions.BAR_CODE_VALUE_FG = intentData;
            String split[] = intentData.split("/");
            CommonFunctions.coilNo_FG = split[0];
            CommonFunctions.ssiplID_FG = split[1];

            if (split[2].contains(" X ")){
                split[2] =  split[2].replace(" X ","X");
            }
            String split1[] = split[2].split("X");
            CommonFunctions.thick_FG = split1[0];
            CommonFunctions.width_FG = split1[1];
            finish();
        }
        else if (from.equals("8")){
            String split[] = intentData.split("/");
            CommonFunctions.WorkOrder_TRIMMED_SCRAP = split[split.length - 1];
            finish();
        }
        else if (from.equals("9")){
            String split[] = intentData.split(" ");
            CommonFunctions.Mother_Coil_ID = split[0];
            finish();
        }
        else if (from.equals("10")){
            String split[] = intentData.split("/");
            CommonFunctions.FG_SSIPL_ID = split[1];
            finish();
        }
        else if (from.equals("11")){
            if (intentData.contains("/")){
                String split[] = intentData.split("/");
                if (split.length > 1) {
                    CommonFunctions.ssiplID = split[1];
                }
            }
            else {
                CommonFunctions.ssiplID = intentData;
            }
            finish();
        }
        else if (from.equals("12")){
            String data = intentData;
            String split[] = intentData.split("/");
            CommonFunctions.FG_Scan_Batch_No = split[1];
            CommonFunctions.FG_Scan_T_W_L = split[2];
            CommonFunctions.FG_Scan_Grade = split[3];
            CommonFunctions.FG_Scan_Batch_QTY = split[4];

            String split_T_W_L[] = CommonFunctions.FG_Scan_T_W_L.split("X");
            if (split_T_W_L.length > 2) {
                CommonFunctions.FG_Scan_Thick = split_T_W_L[0];
                CommonFunctions.FG_Scan_Width = split_T_W_L[1];
                CommonFunctions.FG_Scan_Length = split_T_W_L[2];
            }
            else {
                CommonFunctions.FG_Scan_Thick = split_T_W_L[0];
                CommonFunctions.FG_Scan_Width = split_T_W_L[1];
            }

            finish();
        }
        else if (from.equals("13")){
            String split[] = intentData.split("/");
            CommonFunctions.OP_BATCH = split[1];
            finish();
        }
        else if (from.equals("rmpoka_mold")){
            CommonFunctions.RM_POKA_MOLD = intentData;
            finish();
        }
        else if (from.equals("rmpoka_machine")){
            CommonFunctions.RM_POKA_MACHINE_NO = intentData;
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
            String split[] = intentData.split(":");
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
    }


    @Override
    protected void onPause() {
        super.onPause();
        cameraSource.release();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initialiseDetectorsAndSources();


    }
}
