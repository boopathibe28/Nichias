package com.nichias.activity;

import static com.nichias.utils.CommonFunctions.ssiplID;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.nichias.R;
import com.nichias.api.CommonApiCalls;
import com.nichias.app_interfaces.CommonCallback;
import com.nichias.databinding.ActivityGrnProcessBinding;
import com.nichias.dummy_model.FgGridStorageJson;
import com.nichias.dummy_model.GrnProcessJson;
import com.nichias.model_api.GrnProcessCheckApiResponse;
import com.nichias.model_api.GrnProcessPrintBarCodeApiResponse;
import com.nichias.model_api.RMStorageApiResponse;
import com.nichias.utils.CommonFunctions;

import hari.bounceview.BounceView;

public class GRNProcess extends AppCompatActivity implements View.OnClickListener {
    ActivityGrnProcessBinding binding;
    private IntentIntegrator qrScan;
    private static final int REQUEST_CODE_QR_SCAN = 101;
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private String GrnData = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_grn_process);

        qrScan = new IntentIntegrator(GRNProcess.this);
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }

        initialView();
        buttonPrint();
    }


    private void initialView() {
        BounceView.addAnimTo(binding.imgBack);
        BounceView.addAnimTo(binding.imgScanner);
        BounceView.addAnimTo(binding.txtPrintGRN);
        BounceView.addAnimTo(binding.txtHomeGRN);

        binding.imgBack.setOnClickListener(this);
        binding.imgScanner.setOnClickListener(this);
        binding.txtPrintGRN.setOnClickListener(this);
        binding.txtHomeGRN.setOnClickListener(this);

        binding.tvTitle.setText("GRN Process");
    }

    @Override
    public void onClick(View view) {
        if (view == binding.imgBack){
            finish();
        }
        else if (view == binding.imgScanner){
            Bundle bundle = new Bundle();
            bundle.putString("from","4");
            CommonFunctions.getInstance().newIntent(GRNProcess.this, ScannedBarcodeActivity.class, bundle, false, false);
        }
        else if (view == binding.txtPrintGRN){
            if (binding.edtSSIPLIDGRN.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationError(GRNProcess.this,"SSIPL ID is Empty");
            }
            else {
                if (GrnData.equals("1")){
                    GrnProcessJson grnProcessJson = new GrnProcessJson();
                    grnProcessJson.setMethod("print");
                    grnProcessJson.setSsipl(binding.edtSSIPLIDGRN.getText().toString().trim());

                    Gson gson = new Gson();
                    String input = gson.toJson(grnProcessJson);
                    System.out.println("Input ==> " + input);

                    CommonApiCalls.getInstance().GrnProcessPrintBarCode(GRNProcess.this, input, new CommonCallback.Listener() {
                        @Override
                        public void onSuccess(Object body) {
                            GrnProcessPrintBarCodeApiResponse apiResponse = (GrnProcessPrintBarCodeApiResponse) body;

                            Toast.makeText(GRNProcess.this,apiResponse.getMsg(),Toast.LENGTH_LONG);
                            // CommonFunctions.getInstance().successResponseToast(GRNProcess.this, apiResponse.getMsg());
                        }

                        @Override
                        public void onFailure(String reason) {
                            CommonFunctions.getInstance().validationEmptyError(GRNProcess.this, reason);
                        }
                    });
                }
            }
        }
        else if (view == binding.txtHomeGRN){
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!ssiplID.isEmpty()){
            binding.edtSSIPLIDGRN.setText(ssiplID);
            ssiplID = "";

            checkGrnProcess();
        }
    }

    private void checkGrnProcess() {
        GrnProcessJson grnProcessJson = new GrnProcessJson();
        grnProcessJson.setMethod("check");
        grnProcessJson.setSsipl(binding.edtSSIPLIDGRN.getText().toString().trim());

        Gson gson = new Gson();
        String input = gson.toJson(grnProcessJson);
        System.out.println("Input ==> " + input);

        apiCallGrn(input);
    }


    private void apiCallGrn(String input) {
        CommonApiCalls.getInstance().GrnProcessCheck(GRNProcess.this, input, new CommonCallback.Listener() {
            @Override
            public void onSuccess(Object body) {
                GrnProcessCheckApiResponse apiResponse = (GrnProcessCheckApiResponse) body;
                GrnData = apiResponse.getResult();
                buttonPrint();
                if (GrnData.equals("1")) {
                    CommonFunctions.getInstance().successResponseToast(GRNProcess.this, apiResponse.getMsg());
                }
                else {
                    //CommonFunctions.getInstance().validationError(GRNProcess.this, apiResponse.getMsg());
                  //  CommonFunctions.getInstance().validationError_Custom(GRNProcess.this,apiResponse.getMsg());

                    binding.txtErrorToastGRN.setVisibility(View.VISIBLE);
                    binding.txtErrorToastGRN.setText(apiResponse.getMsg());
                    Animation anim = new AlphaAnimation(0.0f, 1.0f);
                    anim.setDuration(50); //You can manage the blinking time with this parameter
                    anim.setStartOffset(20);
                    anim.setRepeatMode(Animation.REVERSE);
                    anim.setRepeatCount(Animation.INFINITE);
                    binding.txtErrorToastGRN.startAnimation(anim);

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            binding.txtErrorToastGRN.setVisibility(View.GONE);
                        }
                    }, 3000);
                }
            }

            @Override
            public void onFailure(String reason) {
                CommonFunctions.getInstance().validationEmptyError(GRNProcess.this, reason);
            }
        });

    }



    private void buttonPrint() {
        if (GrnData.equals("1")){
            binding.txtPrintGRN.setVisibility(View.VISIBLE);
            binding.txtPrintGRN.setTextColor(ContextCompat.getColor(GRNProcess.this, R.color.black));
            binding.txtPrintGRN.setBackground(ContextCompat.getDrawable(GRNProcess.this, R.drawable.bg_rescan));
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    binding.txtPrintGRN.setVisibility(View.GONE);
                }
            }, 60000);
        }
        else {
            binding.txtPrintGRN.setVisibility(View.GONE);
            binding.txtPrintGRN.setTextColor(ContextCompat.getColor(GRNProcess.this, R.color.txt_gray));

            binding.txtPrintGRN.setBackground(ContextCompat.getDrawable(GRNProcess.this, R.drawable.bg_print_diasble));
        }
    }


}
