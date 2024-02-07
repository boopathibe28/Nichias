package com.nichias.activity;

import static com.nichias.utils.CommonFunctions.PART_NO;
import static com.nichias.utils.CommonFunctions.SERIAL_CODE;
import static com.nichias.utils.CommonFunctions.SL_NO;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.nichias.R;
import com.nichias.api.CommonApiCalls;
import com.nichias.app_interfaces.CommonCallback;
import com.nichias.databinding.ActivityPrimaryPackingBinding;
import com.nichias.dummy_model.InspectionCheckJson;
import com.nichias.model_api.PrimaryPackingApiResponse;
import com.nichias.utils.CommonFunctions;

import hari.bounceview.BounceView;

public class PrimaryPackingActivity extends AppCompatActivity implements View.OnClickListener{
    ActivityPrimaryPackingBinding binding;
    private IntentIntegrator qrScan;
    private static final int MY_CAMERA_REQUEST_CODE = 100;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_primary_packing);

        qrScan = new IntentIntegrator(PrimaryPackingActivity.this);
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }

        initialView();
    }
    private void initialView() {
        BounceView.addAnimTo(binding.imgBack);
        BounceView.addAnimTo(binding.imgScannerQRCode);

        binding.imgBack.setOnClickListener(this);
        binding.imgScannerQRCode.setOnClickListener(this);
        binding.edtQRCode.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if (view == binding.imgBack){
            finish();
        }
        else if (view == binding.imgScannerQRCode || view == binding.edtQRCode){
            Bundle bundle = new Bundle();
            bundle.putString("from", "inspection");
            CommonFunctions.getInstance().newIntent(PrimaryPackingActivity.this, ScanActivity.class, bundle, false, false);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (!SERIAL_CODE.isEmpty()){
            binding.edtSerialNo.setText(SL_NO);
            binding.edtSerialCode.setText(SERIAL_CODE);
            binding.edtPartNo.setText(PART_NO);

            SL_NO = "";
            SERIAL_CODE = "";
            PART_NO = "";

            InspectionCheckJson json = new InspectionCheckJson();
            json.setMethod("Primary_Packing");
            json.setSL_NO(binding.edtSerialNo.getText().toString().trim());
            json.setSERIAL_CODE(binding.edtSerialCode.getText().toString().trim());
            json.setPART_NO(binding.edtPartNo.getText().toString().trim());

            Gson gson = new Gson();
            String input = gson.toJson(json);
            System.out.println("Input ==> " + input);

            CommonApiCalls.getInstance().primary_packing(PrimaryPackingActivity.this, input, new CommonCallback.Listener() {
                @Override
                public void onSuccess(Object body) {
                    PrimaryPackingApiResponse apiResponse = (PrimaryPackingApiResponse) body;
                    if (apiResponse.getStatus().toLowerCase().equals("success")){
                        CommonFunctions.getInstance().showCustomSuccessToast(PrimaryPackingActivity.this,apiResponse.getMsg());
                    }
                    else {
                        CommonFunctions.getInstance().showCustomErrorToast(PrimaryPackingActivity.this,apiResponse.getMsg());
                        binding.edtSerialNo.setText("");
                        binding.edtSerialCode.setText("");
                        binding.edtPartNo.setText("");

                        Bundle bundle = new Bundle();
                        bundle.putString("from", "inspection");
                        CommonFunctions.getInstance().newIntent(PrimaryPackingActivity.this, ScanActivity.class, bundle, false, false);
                    }
                }

                @Override
                public void onFailure(String reason) {
                    CommonFunctions.getInstance().validationEmptyError(PrimaryPackingActivity.this, reason);
                }
            });
        }
    }

}
