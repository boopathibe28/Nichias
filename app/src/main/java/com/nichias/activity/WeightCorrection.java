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
import com.nichias.databinding.ActivityScrapEnterBinding;
import com.nichias.databinding.ActivityWeightCorrectionBinding;
import com.nichias.dummy_model.Query;
import com.nichias.dummy_model.QueryApiResponse;
import com.nichias.dummy_model.RollEndCut;
import com.nichias.model_api.RoolbackEndcutApiResponse;
import com.nichias.utils.CommonFunctions;

import hari.bounceview.BounceView;

public class WeightCorrection extends AppCompatActivity implements View.OnClickListener {
    ActivityWeightCorrectionBinding binding;
    private IntentIntegrator qrScan;
    private static final int REQUEST_CODE_QR_SCAN = 101;
    private static final int MY_CAMERA_REQUEST_CODE = 100;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_weight_correction);

        qrScan = new IntentIntegrator(WeightCorrection.this);
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }

        initialView();

    }

    private void initialView() {
        BounceView.addAnimTo(binding.imgBack);
        BounceView.addAnimTo(binding.ImgScanWorkOrder);
        BounceView.addAnimTo(binding.ImgScanSSIPL);
        BounceView.addAnimTo(binding.ImgScanWeight);
        BounceView.addAnimTo(binding.txtSubmit);

        binding.imgBack.setOnClickListener(this);
        binding.ImgScanWorkOrder.setOnClickListener(this);
        binding.ImgScanSSIPL.setOnClickListener(this);
        binding.ImgScanWeight.setOnClickListener(this);
        binding.txtSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == binding.imgBack) {
            finish();
        }
        else if (view == binding.ImgScanWorkOrder){
            Bundle bundle = new Bundle();
            bundle.putString("from", "8");
            CommonFunctions.getInstance().newIntent(WeightCorrection.this, ScannedBarcodeActivity.class, bundle, false, false);
        }
        else if (view == binding.ImgScanSSIPL){
            Bundle bundle = new Bundle();
            bundle.putString("from", "4");
            CommonFunctions.getInstance().newIntent(WeightCorrection.this, ScannedBarcodeActivity.class, bundle, false, false);
        }
        else if (view == binding.txtSubmit){
            if (binding.edtWorkOrder.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationError(WeightCorrection.this,"Enter valid Work Order");
            }
            else if (binding.edtSSIPLID.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationError(WeightCorrection.this,"Enter valid SSIPL ID");
            }
            else if (binding.edtWeight.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationError(WeightCorrection.this,"Enter valid Weight");
            }
            else {
                Query query = new Query();
                query.setMethod("physical_weight_updation");
                query.setWo_number(binding.edtWorkOrder.getText().toString().trim());
                query.setOpbatch(binding.edtSSIPLID.getText().toString().trim());
                query.setWeight(binding.edtWeight.getText().toString().trim());

                Gson gson = new Gson();
                String input = gson.toJson(query);
                System.out.println("Input ==> " + input);

                CommonApiCalls.getInstance().query(WeightCorrection.this, input, new CommonCallback.Listener() {
                    @Override
                    public void onSuccess(Object body) {
                        QueryApiResponse apiResponse = (QueryApiResponse) body;
                        CommonFunctions.getInstance().successResponseToast(WeightCorrection.this, apiResponse.getMsg());

                        binding.edtWorkOrder.setText("");
                        binding.edtSSIPLID.setText("");
                        binding.edtWeight.setText("");
                    }

                    @Override
                    public void onFailure(String reason) {
                        CommonFunctions.getInstance().validationEmptyError(WeightCorrection.this, reason);
                    }
                });

            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!WorkOrder_TRIMMED_SCRAP.isEmpty()){
            binding.edtWorkOrder.setText(WorkOrder_TRIMMED_SCRAP);
            WorkOrder_TRIMMED_SCRAP = "";
        }
        if (!ssiplID.isEmpty()){
            binding.edtSSIPLID.setText(ssiplID);
            ssiplID = "";
        }
    }

}
