package com.nichias.activity;

import static com.nichias.utils.CommonFunctions.WorkOrder_TRIMMED_SCRAP;
import static com.nichias.utils.CommonFunctions.ssiplID;
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
import com.nichias.databinding.ActivityCrCutterBinding;
import com.nichias.dummy_model.CRSCutterJson;
import com.nichias.model_api.HrsSlittingApiResponse;
import com.nichias.utils.CommonFunctions;

import hari.bounceview.BounceView;

public class CRCutter extends AppCompatActivity implements View.OnClickListener {
    ActivityCrCutterBinding binding;
    private IntentIntegrator qrScan;
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    String selectionTab = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cr_cutter);

        qrScan = new IntentIntegrator(this);
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }

        initialView();
    }

    private void initialView() {
        BounceView.addAnimTo(binding.imgBack);
        BounceView.addAnimTo(binding.txtOk);
        BounceView.addAnimTo(binding.txtNotOk);
        BounceView.addAnimTo(binding.txtRescan);

        BounceView.addAnimTo(binding.ImgScanSSIPL);
        BounceView.addAnimTo(binding.ImgScanWorkOrderTrimmed);

        BounceView.addAnimTo(binding.rlyoutColiInward);
        BounceView.addAnimTo(binding.rlyoutFGDetails);
        BounceView.addAnimTo(binding.txtOkFG);
        BounceView.addAnimTo(binding.rlyoutScrapEnter);

        binding.imgBack.setOnClickListener(this);
        binding.txtOk.setOnClickListener(this);
        binding.txtNotOk.setOnClickListener(this);
        binding.txtRescan.setOnClickListener(this);

        binding.rlyoutColiInward.setOnClickListener(this);
        binding.rlyoutFGDetails.setOnClickListener(this);
        binding.txtOkFG.setOnClickListener(this);
        binding.rlyoutScrapEnter.setOnClickListener(this);

        binding.ImgScanSSIPL.setOnClickListener(this);
        binding.ImgScanWorkOrderTrimmed.setOnClickListener(this);

        binding.tvTitle.setText("CR Cutter");
        binding.edtMachineName.setText("CRCT");

        // Initial
        binding.viewCoilInward.setVisibility(View.VISIBLE);
        binding.viewFGDetails.setVisibility(View.GONE);
        binding.viewScrapEnter.setVisibility(View.GONE);

        binding.lyoutCoilInward.setVisibility(View.VISIBLE);
        binding.lyoutFGDetails.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        if (view == binding.imgBack){
            finish();
        }
        else if (view == binding.ImgScanSSIPL){
            selectionTab = "ssipl";
            Bundle bundle = new Bundle();
            bundle.putString("from", "11");
            CommonFunctions.getInstance().newIntent(CRCutter.this, ScannedBarcodeActivity.class, bundle, false, false);
        }
        else if (view == binding.ImgScanWorkOrderTrimmed){
            selectionTab = "work";
            Bundle bundle = new Bundle();
            bundle.putString("from", "8");
            CommonFunctions.getInstance().newIntent(CRCutter.this, ScannedBarcodeActivity.class, bundle, false, false);
        }
        else if (view == binding.rlyoutColiInward){
            binding.viewCoilInward.setVisibility(View.VISIBLE);
            binding.viewFGDetails.setVisibility(View.GONE);
            binding.viewScrapEnter.setVisibility(View.GONE);

            binding.lyoutCoilInward.setVisibility(View.VISIBLE);
            binding.lyoutFGDetails.setVisibility(View.GONE);
        }
        else if (view == binding.rlyoutScrapEnter){
            CommonFunctions.getInstance().newIntent(CRCutter.this, ScrapEnterCRCutterActivity.class, Bundle.EMPTY, false, false);
        }
        else if (view == binding.txtOk){
            if (binding.edtSSIPLID.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationError(CRCutter.this,"SSIPL ID is Empty");
            }
            else if (binding.edtWorkOrderTrimmed.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationError(CRCutter.this,"Work Order is Empty");
            }
            else {
                checkJobProcess();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (selectionTab.equals("ssipl")) {
            if (!ssiplID.isEmpty()) {
                binding.edtSSIPLID.setText(ssiplID);
                ssiplID = "";

                //checkJobProcess();
            }
        }
        else if (selectionTab.equals("work")){
            if (!WorkOrder_TRIMMED_SCRAP.isEmpty()) {
                binding.edtWorkOrderTrimmed.setText(WorkOrder_TRIMMED_SCRAP);
                WorkOrder_TRIMMED_SCRAP = "";

               // checkJobProcess();
            }
        }
    }

    private void checkJobProcess() {
        CRSCutterJson crsCutterJson = new CRSCutterJson();
        crsCutterJson.setMachine("CRCT");
        crsCutterJson.setSSIPL(binding.edtSSIPLID.getText().toString().trim());
        crsCutterJson.setWo_Num(binding.edtWorkOrderTrimmed.getText().toString().trim());

        Gson gson = new Gson();
        String input = gson.toJson(crsCutterJson);
        System.out.println("Input ==> " + input);

        apiCall(input);
    }

    private void apiCall(String input) {
        CommonApiCalls.getInstance().hrsSlitting(CRCutter.this, input, new CommonCallback.Listener() {
            @Override
            public void onSuccess(Object body) {
                HrsSlittingApiResponse apiResponse = (HrsSlittingApiResponse) body;
                if (apiResponse.getResult().equals("1")) {
                   // binding.txtOk.setVisibility(View.VISIBLE);
                    binding.txtNotOk.setVisibility(View.GONE);
                    binding.edtUpdate.setText(apiResponse.getMachine());
                    CommonFunctions.getInstance().successResponseToast(CRCutter.this, apiResponse.getMsg());
                }
                else {
                   // binding.txtOk.setVisibility(View.GONE);
                    binding.txtNotOk.setVisibility(View.VISIBLE);
                    binding.edtUpdate.setText(apiResponse.getMachine());
                    CommonFunctions.getInstance().validationError(CRCutter.this, apiResponse.getMsg());
                }
            }

            @Override
            public void onFailure(String reason) {
                CommonFunctions.getInstance().validationEmptyError(CRCutter.this, reason);
            }
        });

    }

}
