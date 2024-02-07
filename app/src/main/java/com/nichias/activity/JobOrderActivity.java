package com.nichias.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.nichias.R;
import com.nichias.adapter.JobOrdersListAdapter;
import com.nichias.adapter.RmInwardOrdersListAdapter;
import com.nichias.api.CommonApiCalls;
import com.nichias.app_interfaces.CommonCallback;
import com.nichias.databinding.ActivityJobOrderBinding;
import com.nichias.dummy_model.OperatorSwap;
import com.nichias.dummy_model.PokayokeCheck;
import com.nichias.dummy_model.RMLocationEnteryJson;
import com.nichias.dummy_model.RmInwardOrdersJson;
import com.nichias.model_api.JobOrderPickupApiResponse;
import com.nichias.model_api.JobOrdersApiResponse;
import com.nichias.model_api.OperatorSwapApiResponse;
import com.nichias.model_api.PokayokeCheckApiResponse;
import com.nichias.model_api.RMInwardsOrdersApiResponse;
import com.nichias.utils.CommonFunctions;

import hari.bounceview.BounceView;

public class JobOrderActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityJobOrderBinding binding;
    private IntentIntegrator qrScan;
    String selectedTab = "";
    private static final int MY_CAMERA_REQUEST_CODE = 100;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_job_order);

        qrScan = new IntentIntegrator(JobOrderActivity.this);
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }

        initialView();
    }

    private void initialView() {
        BounceView.addAnimTo(binding.txtCheck);
        BounceView.addAnimTo(binding.imgBack);
        BounceView.addAnimTo(binding.rlyoutAssignedJobOrder);
        BounceView.addAnimTo(binding.rlyoutRMPickup);
        BounceView.addAnimTo(binding.imgScannerQRCode);
        BounceView.addAnimTo(binding.imgRefresh);
        BounceView.addAnimTo(binding.rlyoutRMPokaYoke);

        BounceView.addAnimTo(binding.txtCheckRMPoka);
        BounceView.addAnimTo(binding.txtOperatorSwap);
        BounceView.addAnimTo(binding.txtSave);

        BounceView.addAnimTo(binding.imgScannerQRCodeRMPoka);
        BounceView.addAnimTo(binding.edtQRCodeRMPoka);
        BounceView.addAnimTo(binding.lyoutQRCodeRMPoka);

        binding.imgScannerQRCode.setOnClickListener(this);
        binding.txtCheck.setOnClickListener(this);
        binding.imgBack.setOnClickListener(this);
        binding.rlyoutAssignedJobOrder.setOnClickListener(this);
        binding.rlyoutRMPickup.setOnClickListener(this);
        binding.imgRefresh.setOnClickListener(this);
        binding.rlyoutRMPokaYoke.setOnClickListener(this);

        binding.txtCheckRMPoka.setOnClickListener(this);
        binding.txtOperatorSwap.setOnClickListener(this);
        binding.txtSave.setOnClickListener(this);

        binding.imgScannerQRCodeRMPoka.setOnClickListener(this);
        binding.edtQRCodeRMPoka.setOnClickListener(this);
        binding.lyoutQRCodeRMPoka.setOnClickListener(this);

        binding.imgScannerMold.setOnClickListener(this);
        binding.edtMold.setOnClickListener(this);
        binding.lyoutMold.setOnClickListener(this);

        binding.imgScannerMachineNo.setOnClickListener(this);
        binding.edtMachineNo.setOnClickListener(this);
        binding.lyoutMachineNo.setOnClickListener(this);

        binding.lyoutAssignedJobOrder.setVisibility(View.GONE);
        binding.viewAssignedJobOrder.setVisibility(View.GONE);
        binding.viewRMPickup.setVisibility(View.VISIBLE);
        binding.lyoutRMPickup.setVisibility(View.VISIBLE);
        binding.imgRefresh.setVisibility(View.GONE);
        binding.viewRMPokaYoke.setVisibility(View.GONE);
        binding.lyoutRMPokaYoke.setVisibility(View.GONE);

        selectedTab = "rm_pickup";
    }

    @Override
    public void onClick(View view) {
        if (view == binding.imgBack){
            finish();
        }
        else if (view == binding.imgRefresh){
            binding.edtQRCodeRMPoka.setText("");
            binding.edtRollNoRMPoka.setText("");
            binding.edtLotNoRMPoka.setText("");
            binding.edtSizeRMPoka.setText("");
            binding.edtManagedNoRMPoka.setText("");
            binding.edtMold.setText("");
            binding.edtMachineNo.setText("");
            binding.edtOperatorID.setText("");

            binding.lyoutTopViewRM.setVisibility(View.VISIBLE);
            binding.lyoutTopViewMold.setVisibility(View.VISIBLE);
            binding.txtCheckRMPoka.setVisibility(View.VISIBLE);
            binding.txtSave.setVisibility(View.GONE);
        }
        else if (view == binding.txtCheckRMPoka){
            if (binding.edtQRCodeRMPoka.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(JobOrderActivity.this,"Kindly Enter valid Qr Code");
            }
            else if (binding.edtRollNoRMPoka.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(JobOrderActivity.this,"Kindly Enter valid Roll No");
            }
            else if (binding.edtLotNoRMPoka.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(JobOrderActivity.this,"Kindly Enter valid Lot No");
            }
            else if (binding.edtSizeRMPoka.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(JobOrderActivity.this,"Kindly Enter valid Size");
            }
            else if (binding.edtManagedNoRMPoka.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(JobOrderActivity.this,"Kindly Enter valid Managed No");
            }
            else if (binding.edtMold.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(JobOrderActivity.this,"Kindly Enter valid Mold");
            }
            else if (binding.edtMachineNo.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(JobOrderActivity.this,"Kindly Enter valid Machine No");
            }
            else if (binding.edtOperatorID.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(JobOrderActivity.this,"Kindly Enter valid Operator ID");
            }
            else {
                PokayokeCheck apiJson = new PokayokeCheck();
                apiJson.setMethod("pokayoke_check");
                apiJson.setRollNo(binding.edtRollNoRMPoka.getText().toString().trim());
                apiJson.setLotNo(binding.edtLotNoRMPoka.getText().toString().trim());
                apiJson.setSize(binding.edtSizeRMPoka.getText().toString().trim());
                apiJson.setManagedNo(binding.edtManagedNoRMPoka.getText().toString().trim());
                apiJson.setMould(binding.edtMold.getText().toString().trim());
                apiJson.setMachineNo(binding.edtMachineNo.getText().toString().trim());
                apiJson.setOperatorId(binding.edtOperatorID.getText().toString().trim());

                Gson gson = new Gson();
                String input = gson.toJson(apiJson);
                System.out.println("Input ==> " + input);

                CommonApiCalls.getInstance().pokaYokeCheck(JobOrderActivity.this, input, new CommonCallback.Listener() {
                    @Override
                    public void onSuccess(Object body) {
                        PokayokeCheckApiResponse apiResponse = (PokayokeCheckApiResponse) body;
                        CommonFunctions.getInstance().successResponseToast(JobOrderActivity.this, apiResponse.getMsg());

                        binding.edtQRCodeRMPoka.setText("");
                        binding.edtRollNoRMPoka.setText("");
                        binding.edtLotNoRMPoka.setText("");
                        binding.edtSizeRMPoka.setText("");
                        binding.edtManagedNoRMPoka.setText("");
                        binding.edtMold.setText("");
                        binding.edtMachineNo.setText("");
                        binding.edtOperatorID.setText("");
                    }

                    @Override
                    public void onFailure(String reason) {
                        CommonFunctions.getInstance().validationEmptyError(JobOrderActivity.this, reason);
                    }
                });
            }
        }
        else if (view == binding.txtOperatorSwap){
            binding.lyoutTopViewRM.setVisibility(View.GONE);
            binding.lyoutTopViewMold.setVisibility(View.GONE);
            binding.txtCheckRMPoka.setVisibility(View.GONE);
            binding.txtSave.setVisibility(View.VISIBLE);
        }
        else if (view == binding.txtSave){
            if (binding.edtMachineNo.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(JobOrderActivity.this,"Kindly Enter valid Machine No");
            }
            else if (binding.edtOperatorID.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(JobOrderActivity.this,"Kindly Enter valid Operator ID");
            }
            else {
                final Dialog dialog = new Dialog(JobOrderActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.setContentView(R.layout.dialog_operator_swap_alert_pop);
                dialog.setCancelable(false);

                TextView txtNo =  dialog.findViewById(R.id.txtNo);
                TextView txtYes =  dialog.findViewById(R.id.txtYes);

                dialog.show();

                txtNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                txtYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        //Api Call
                        OperatorSwap apiJson = new OperatorSwap();
                        apiJson.setMethod("pokayoke_opswap");
                        apiJson.setMachineNo(binding.edtMachineNo.getText().toString().trim());
                        apiJson.setOperatorId(binding.edtOperatorID.getText().toString().trim());

                        Gson gson = new Gson();
                        String input = gson.toJson(apiJson);
                        System.out.println("Input ==> " + input);

                        CommonApiCalls.getInstance().operatorSwap(JobOrderActivity.this, input, new CommonCallback.Listener() {
                            @Override
                            public void onSuccess(Object body) {
                                OperatorSwapApiResponse apiResponse = (OperatorSwapApiResponse) body;
                                CommonFunctions.getInstance().successResponseToast(JobOrderActivity.this, apiResponse.getMsg());

                                binding.edtMachineNo.setText("");
                                binding.edtOperatorID.setText("");

                                binding.lyoutTopViewRM.setVisibility(View.VISIBLE);
                                binding.lyoutTopViewMold.setVisibility(View.VISIBLE);
                                binding.txtCheckRMPoka.setVisibility(View.VISIBLE);
                                binding.txtSave.setVisibility(View.GONE);
                            }

                            @Override
                            public void onFailure(String reason) {
                                CommonFunctions.getInstance().validationEmptyError(JobOrderActivity.this, reason);
                            }
                        });

                    }
                });
            }
        }
        else if (view == binding.imgScannerMold || view == binding.edtMold || view == binding.lyoutMold){
            Bundle bundle = new Bundle();
            bundle.putString("from", "rmpoka_mold");
           // CommonFunctions.getInstance().newIntent(JobOrderActivity.this, ScannedBarcodeActivity.class, bundle, false, false);
            CommonFunctions.getInstance().newIntent(JobOrderActivity.this, ScanActivity.class, bundle, false, false);
        }
        else if (view == binding.imgScannerMachineNo || view == binding.edtMachineNo || view == binding.lyoutMachineNo){
            Bundle bundle = new Bundle();
            bundle.putString("from", "rmpoka_machine");
           // CommonFunctions.getInstance().newIntent(JobOrderActivity.this, ScannedBarcodeActivity.class, bundle, false, false);
            CommonFunctions.getInstance().newIntent(JobOrderActivity.this, ScanActivity.class, bundle, false, false);
        }
        else if (view == binding.imgScannerQRCodeRMPoka || view == binding.edtQRCodeRMPoka || view == binding.lyoutQRCodeRMPoka){
            Bundle bundle = new Bundle();
            bundle.putString("from", "rmpoka_rm");
          //  CommonFunctions.getInstance().newIntent(JobOrderActivity.this, ScannedBarcodeActivity.class, bundle, false, false);
            CommonFunctions.getInstance().newIntent(JobOrderActivity.this, ScanActivity.class, bundle, false, false);
        }
        else if (view == binding.rlyoutAssignedJobOrder){
            selectedTab = "job_order";
            binding.lyoutAssignedJobOrder.setVisibility(View.VISIBLE);
            binding.viewAssignedJobOrder.setVisibility(View.VISIBLE);
            binding.viewRMPickup.setVisibility(View.GONE);
            binding.lyoutRMPickup.setVisibility(View.GONE);
            binding.imgRefresh.setVisibility(View.GONE);
            binding.viewRMPokaYoke.setVisibility(View.GONE);
            binding.lyoutRMPokaYoke.setVisibility(View.GONE);

            jobOrderList();
        }
        else if (view == binding.rlyoutRMPickup){
            selectedTab = "rm_pickup";
            binding.lyoutAssignedJobOrder.setVisibility(View.GONE);
            binding.viewAssignedJobOrder.setVisibility(View.GONE);
            binding.viewRMPickup.setVisibility(View.VISIBLE);
            binding.lyoutRMPickup.setVisibility(View.VISIBLE);
            binding.imgRefresh.setVisibility(View.GONE);
            binding.viewRMPokaYoke.setVisibility(View.GONE);
            binding.lyoutRMPokaYoke.setVisibility(View.GONE);
        }
        else if (view == binding.imgScannerQRCode){
            Bundle bundle = new Bundle();
            bundle.putString("from", "rmstorage");
            CommonFunctions.getInstance().newIntent(JobOrderActivity.this, ScannedBarcodeActivity.class, bundle, false, false);
        }
        else if (view == binding.rlyoutRMPokaYoke){
            selectedTab = "rm_poka_yoke";
            binding.lyoutAssignedJobOrder.setVisibility(View.GONE);
            binding.viewAssignedJobOrder.setVisibility(View.GONE);
            binding.viewRMPickup.setVisibility(View.GONE);
            binding.lyoutRMPickup.setVisibility(View.GONE);
            binding.viewRMPokaYoke.setVisibility(View.VISIBLE);
            binding.lyoutRMPokaYoke.setVisibility(View.VISIBLE);
            binding.imgRefresh.setVisibility(View.VISIBLE);
        }
        else if (view == binding.txtCheck){
            if (binding.edtQRCode.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(JobOrderActivity.this,"Kindly Enter valid Qr Code");
            }
            else if (binding.edtRollNo.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(JobOrderActivity.this,"Kindly Enter valid Roll No");
            }
            else if (binding.edtLotNo.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(JobOrderActivity.this,"Kindly Enter valid Lot No");
            }
            else if (binding.edtSize.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(JobOrderActivity.this,"Kindly Enter valid Size");
            }
            else if (binding.edtManagedNo.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(JobOrderActivity.this,"Kindly Enter valid Managed No");
            }
            else {
                RMLocationEnteryJson apiJson = new RMLocationEnteryJson();
                apiJson.setMethod("job_order_pick");
                apiJson.setRollNo(binding.edtRollNo.getText().toString().trim());
                apiJson.setLotNo(binding.edtLotNo.getText().toString().trim());
                apiJson.setSize(binding.edtSize.getText().toString().trim());
                apiJson.setManagedNo(binding.edtManagedNo.getText().toString().trim());

                Gson gson = new Gson();
                String input = gson.toJson(apiJson);
                System.out.println("Input ==> " + input);

                postApiCall(input);
            }
        }
    }

    private void postApiCall(String input) {
        CommonApiCalls.getInstance().jobOrderPickup(JobOrderActivity.this, input, new CommonCallback.Listener() {
            @Override
            public void onSuccess(Object body) {
                JobOrderPickupApiResponse apiResponse = (JobOrderPickupApiResponse) body;
                CommonFunctions.getInstance().successResponseToast(JobOrderActivity.this, apiResponse.getMsg());

                binding.edtQRCode.setText("");
                binding.edtRollNo.setText("");
                binding.edtLotNo.setText("");
                binding.edtSize.setText("");
                binding.edtManagedNo.setText("");
            }

            @Override
            public void onFailure(String reason) {
                CommonFunctions.getInstance().validationEmptyError(JobOrderActivity.this, reason);
            }
        });
    }

    private void jobOrderList() {
        RmInwardOrdersJson apiJson = new RmInwardOrdersJson();
        apiJson.setMethod("job_order");

        Gson gson = new Gson();
        String input = gson.toJson(apiJson);
        System.out.println("Input ==> " + input);

        CommonApiCalls.getInstance().jobOrdersList(JobOrderActivity.this, input, new CommonCallback.Listener() {
            @Override
            public void onSuccess(Object body) {
                JobOrdersApiResponse apiResponse = (JobOrdersApiResponse) body;
                CommonFunctions.getInstance().successResponseToast(JobOrderActivity.this, apiResponse.getHavedata());
                if (apiResponse.getData().size() > 0){
                    binding.rvAssignedJobOrder.setVisibility(View.VISIBLE);
                    binding.txtNoDataFound.setVisibility(View.GONE);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(JobOrderActivity.this, LinearLayoutManager.VERTICAL, false);
                    binding.rvAssignedJobOrder.setLayoutManager(layoutManager);

                    JobOrdersListAdapter adapter = new JobOrdersListAdapter(JobOrderActivity.this, apiResponse.getData());
                    binding.rvAssignedJobOrder.setAdapter(adapter);
                }
                else {
                    binding.rvAssignedJobOrder.setVisibility(View.GONE);
                    binding.txtNoDataFound.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(String reason) {
                CommonFunctions.getInstance().validationEmptyError(JobOrderActivity.this, reason);
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        if (selectedTab.equals("rm_pickup")) {
            if (!CommonFunctions.RollNo.isEmpty()) {
                binding.edtQRCode.setText(CommonFunctions.BAR_CODE_VALUE);
                binding.edtRollNo.setText(CommonFunctions.RollNo);
                binding.edtLotNo.setText(CommonFunctions.LotNo);
                binding.edtSize.setText(CommonFunctions.Size);
                binding.edtManagedNo.setText(CommonFunctions.ManagedNo);

                CommonFunctions.RollNo = "";
                CommonFunctions.LotNo = "";
                CommonFunctions.Size = "";
                CommonFunctions.ManagedNo = "";
            }
        }
        else if (selectedTab.equals("rm_poka_yoke")){
            if (!CommonFunctions.RollNo.isEmpty()) {
                binding.edtQRCodeRMPoka.setText(CommonFunctions.BAR_CODE_VALUE);
                binding.edtRollNoRMPoka.setText(CommonFunctions.RollNo);
                binding.edtLotNoRMPoka.setText(CommonFunctions.LotNo);
                binding.edtSizeRMPoka.setText(CommonFunctions.Size);
                binding.edtManagedNoRMPoka.setText(CommonFunctions.ManagedNo);

                CommonFunctions.RollNo = "";
                CommonFunctions.LotNo = "";
                CommonFunctions.Size = "";
                CommonFunctions.ManagedNo = "";
            }
            if (!CommonFunctions.RM_POKA_MOLD.isEmpty()){
                binding.edtMold.setText(CommonFunctions.RM_POKA_MOLD);
                CommonFunctions.RM_POKA_MOLD = "";
            }
            if (!CommonFunctions.RM_POKA_MACHINE_NO.isEmpty()){
                binding.edtMachineNo.setText(CommonFunctions.RM_POKA_MACHINE_NO);
                CommonFunctions.RM_POKA_MACHINE_NO = "";
            }
        }
    }

}
