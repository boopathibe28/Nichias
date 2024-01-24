package com.nichias.activity;

import static com.nichias.utils.CommonFunctions.FG_SSIPL_ID;
import static com.nichias.utils.CommonFunctions.Mother_Coil_ID;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.nichias.R;
import com.nichias.api.CommonApiCalls;
import com.nichias.app_interfaces.CommonCallback;
import com.nichias.databinding.ActivityReprintBinding;
import com.nichias.dummy_model.FGJson;
import com.nichias.dummy_model.RMInwardJson;
import com.nichias.dummy_model.WIPJson;
import com.nichias.model_api.FGDataApiResponse;
import com.nichias.model_api.ReprintApiResponse;
import com.nichias.model_api.WipApiResponse;
import com.nichias.utils.CommonFunctions;

import hari.bounceview.BounceView;

public class Reprint extends AppCompatActivity implements View.OnClickListener {
    ActivityReprintBinding binding;
    private IntentIntegrator qrScan;
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    String selectionTab = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_reprint);

        qrScan = new IntentIntegrator(this);
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }

        initialView();
    }

    private void initialView() {
        BounceView.addAnimTo(binding.imgBack);

        BounceView.addAnimTo(binding.rlyoutRMInward);
        BounceView.addAnimTo(binding.rlyoutWip);
        BounceView.addAnimTo(binding.rlyoutFG);

        BounceView.addAnimTo(binding.ImgScanMotherCoilIDRM);
        BounceView.addAnimTo(binding.txtPrintRM);

        binding.tvTitle.setText("Reprint");

        binding.imgBack.setOnClickListener(this);
        binding.rlyoutRMInward.setOnClickListener(this);
        binding.rlyoutWip.setOnClickListener(this);
        binding.rlyoutFG.setOnClickListener(this);

        binding.ImgScanMotherCoilIDRM.setOnClickListener(this);
        binding.txtPrintRM.setOnClickListener(this);

        binding.ImgScanSSIPLIDWIP.setOnClickListener(this);
        binding.txtPrintWIP.setOnClickListener(this);


        // Initial
        selectionTab = "rm";
        binding.lyoutRMInward.setVisibility(View.VISIBLE);
        binding.viewRMInward.setVisibility(View.VISIBLE);
        binding.lyoutWIP.setVisibility(View.GONE);
        binding.viewWip.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View view) {
        if (view == binding.imgBack){
            finish();
        }
        else if (view == binding.rlyoutRMInward){
            selectionTab = "rm";
            binding.lyoutRMInward.setVisibility(View.VISIBLE);
            binding.viewRMInward.setVisibility(View.VISIBLE);
            binding.lyoutWIP.setVisibility(View.GONE);
            binding.viewWip.setVisibility(View.GONE);
        }
        else if (view == binding.rlyoutWip){
            selectionTab = "wip";
            binding.lyoutRMInward.setVisibility(View.GONE);
            binding.viewRMInward.setVisibility(View.GONE);
            binding.lyoutWIP.setVisibility(View.VISIBLE);
            binding.viewWip.setVisibility(View.VISIBLE);
        }
        else if (view == binding.rlyoutFG){
          //  CommonFunctions.getInstance().newIntent(Reprint.this, ReprintFGActivity.class, Bundle.EMPTY, false, false);
        }
        else if (view == binding.ImgScanMotherCoilIDRM){
            Bundle bundle = new Bundle();
            bundle.putString("from", "9");
            CommonFunctions.getInstance().newIntent(Reprint.this, ScannedBarcodeActivity.class, bundle, false, false);
        }
        else if (view == binding.txtPrintRM){
            if (binding.edtMotherCoilIDRM.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationError(Reprint.this,"Enter valid Mother Coil ID");
            }
            else {
                RMInwardJson rmInwardJson = new RMInwardJson();
                rmInwardJson.setMethod("rm_inward");
                rmInwardJson.setCoil(binding.edtMotherCoilIDRM.getText().toString().trim());

                Gson gson = new Gson();
                String input = gson.toJson(rmInwardJson);
                System.out.println("Input ==> " + input);

                rmInwardApiCall(input);
            }
        }
        else if (view == binding.ImgScanSSIPLIDWIP){
            Bundle bundle = new Bundle();
            bundle.putString("from", "10");
            CommonFunctions.getInstance().newIntent(Reprint.this, ScannedBarcodeActivity.class, bundle, false, false);
        }
        else if (view == binding.txtPrintWIP){
            if (binding.edtSSIPLIDWIP.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationError(Reprint.this,"Enter valid SSIPL ID");
            }
            else {
                WIPJson wipJson = new WIPJson();
                wipJson.setMethod("slit_wip");
                wipJson.setOp_batch(binding.edtSSIPLIDWIP.getText().toString().trim());

                Gson gson = new Gson();
                String input = gson.toJson(wipJson);
                System.out.println("Input ==> " + input);

                wipApiCall(input);
            }
        }
    }

    private void wipApiCall(String input) {
        CommonApiCalls.getInstance().wipCall(Reprint.this, input, new CommonCallback.Listener() {
            @Override
            public void onSuccess(Object body) {
                WipApiResponse apiResponse = (WipApiResponse) body;
                if (apiResponse.getStatus().toLowerCase().equals("success")){
                    CommonFunctions.getInstance().successResponseToast(Reprint.this, apiResponse.getMsg());

                    binding.edtSSIPLIDWIP.setText("");
                }
                else {
                    CommonFunctions.getInstance().validationError(Reprint.this, apiResponse.getMsg());
                }
            }

            @Override
            public void onFailure(String reason) {
                CommonFunctions.getInstance().validationEmptyError(Reprint.this, reason);
            }
        });
    }

    private void rmInwardApiCall(String input) {
        CommonApiCalls.getInstance().rmInward(Reprint.this, input, new CommonCallback.Listener() {
            @Override
            public void onSuccess(Object body) {
                ReprintApiResponse apiResponse = (ReprintApiResponse) body;
                if (apiResponse.getStatus().toLowerCase().equals("success")){
                    CommonFunctions.getInstance().successResponseToast(Reprint.this, apiResponse.getMsg());

                    binding.edtMotherCoilIDRM.setText("");
                }
                else {
                    CommonFunctions.getInstance().validationError(Reprint.this, apiResponse.getMsg());
                }
            }

            @Override
            public void onFailure(String reason) {
                CommonFunctions.getInstance().validationEmptyError(Reprint.this, reason);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (selectionTab.equals("rm")){
            if (!Mother_Coil_ID.isEmpty()){
                binding.edtMotherCoilIDRM.setText(Mother_Coil_ID);
                Mother_Coil_ID = "";
            }
        }
        else if (selectionTab.equals("wip")){
            if (!FG_SSIPL_ID.isEmpty()){
                binding.edtSSIPLIDWIP.setText(FG_SSIPL_ID);
                FG_SSIPL_ID = "";
            }
        }
    }
}
