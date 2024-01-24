package com.nichias.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.nichias.R;
import com.nichias.adapter.DeletePrimeListAdapter;
import com.nichias.adapter.RmInwardOrdersListAdapter;
import com.nichias.api.CommonApiCalls;
import com.nichias.app_interfaces.CommonCallback;
import com.nichias.databinding.ActivityRmstorageBinding;
import com.nichias.dummy_model.RMLocationEnteryJson;
import com.nichias.dummy_model.RmInventoryApiJson;
import com.nichias.dummy_model.RmInwardOrdersJson;
import com.nichias.model_api.RMInventoryApiResponse;
import com.nichias.model_api.RMInwardsOrdersApiResponse;
import com.nichias.model_api.RMLocationEntryApiResponse;
import com.nichias.utils.CommonFunctions;

import hari.bounceview.BounceView;

public class RMStorageActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityRmstorageBinding binding;
    private IntentIntegrator qrScan;
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    String selectionTab = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rmstorage);

        qrScan = new IntentIntegrator(RMStorageActivity.this);
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }

        initialView();

    }

    private void initialView() {
        BounceView.addAnimTo(binding.txtSave);
        BounceView.addAnimTo(binding.imgBack);
        BounceView.addAnimTo(binding.rlyoutRMInwardOrder);
        BounceView.addAnimTo(binding.rlyoutRMLocationEntry);
        BounceView.addAnimTo(binding.imgScannerQRCode);

        binding.imgScannerQRCode.setOnClickListener(this);
        binding.txtSave.setOnClickListener(this);
        binding.imgBack.setOnClickListener(this);
        binding.rlyoutRMInwardOrder.setOnClickListener(this);
        binding.rlyoutRMLocationEntry.setOnClickListener(this);

        binding.lyoutRmInwardOrder.setVisibility(View.GONE);
        binding.viewRMInwardOrder.setVisibility(View.GONE);
        binding.viewRMLocationEntry.setVisibility(View.VISIBLE);
        binding.lyoutRmLocationEntry.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        if (view == binding.imgBack){
            finish();
        }
        else if (view == binding.rlyoutRMInwardOrder){
            binding.lyoutRmInwardOrder.setVisibility(View.VISIBLE);
            binding.viewRMInwardOrder.setVisibility(View.VISIBLE);
            binding.viewRMLocationEntry.setVisibility(View.GONE);
            binding.lyoutRmLocationEntry.setVisibility(View.GONE);

            inwardOrderList();
        }
        else if (view == binding.rlyoutRMLocationEntry){
            binding.lyoutRmInwardOrder.setVisibility(View.GONE);
            binding.viewRMInwardOrder.setVisibility(View.GONE);
            binding.viewRMLocationEntry.setVisibility(View.VISIBLE);
            binding.lyoutRmLocationEntry.setVisibility(View.VISIBLE);
        }
        else if (view == binding.imgScannerQRCode){
            Bundle bundle = new Bundle();
            bundle.putString("from", "rmstorage");
           // CommonFunctions.getInstance().newIntent(RMStorageActivity.this, ScannedBarcodeActivity.class, bundle, false, false);
            CommonFunctions.getInstance().newIntent(RMStorageActivity.this, ScanActivity.class, bundle, false, false);
        }
        else if (view == binding.txtSave){
            if (binding.edtQRCode.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RMStorageActivity.this,"Kindly Enter valid Qr Code");
            }
            else if (binding.edtRollNo.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RMStorageActivity.this,"Kindly Enter valid Roll No");
            }
            else if (binding.edtLotNo.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RMStorageActivity.this,"Kindly Enter valid Lot No");
            }
            else if (binding.edtSize.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RMStorageActivity.this,"Kindly Enter valid Size");
            }
            else if (binding.edtManagedNo.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RMStorageActivity.this,"Kindly Enter valid Managed No");
            }
            else if (binding.edtRackNo.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RMStorageActivity.this,"Kindly Enter valid Rack No");
            }
            else if (binding.edtLocation.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RMStorageActivity.this,"Kindly Enter valid Location");
            }
            else if (binding.edtSerialCode.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RMStorageActivity.this,"Kindly Enter valid Serial Code");
            }
            else if (binding.edtQtyMeter.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RMStorageActivity.this,"Kindly Enter valid Qty/Meter");
            }
            else {
                RMLocationEnteryJson apiJson = new RMLocationEnteryJson();
                apiJson.setMethod("rm_storage");
                apiJson.setRollNo(binding.edtRollNo.getText().toString().trim());
                apiJson.setLotNo(binding.edtLotNo.getText().toString().trim());
                apiJson.setSize(binding.edtSize.getText().toString().trim());
                apiJson.setManagedNo(binding.edtManagedNo.getText().toString().trim());
                apiJson.setRMRackNo(binding.edtRackNo.getText().toString().trim());
                apiJson.setRMLocation(binding.edtLocation.getText().toString().trim());
                apiJson.setSerialCode(binding.edtSerialCode.getText().toString().trim());
                apiJson.setQuantityMeter(binding.edtQtyMeter.getText().toString().trim());

                Gson gson = new Gson();
                String input = gson.toJson(apiJson);
                System.out.println("Input ==> " + input);

                postApiCall(input);
            }
        }
    }

    private void postApiCall(String input) {
        CommonApiCalls.getInstance().rmLocationEntryApiData(RMStorageActivity.this, input, new CommonCallback.Listener() {
            @Override
            public void onSuccess(Object body) {
                RMLocationEntryApiResponse apiResponse = (RMLocationEntryApiResponse) body;
                CommonFunctions.getInstance().successResponseToast(RMStorageActivity.this, apiResponse.getMsg());

                binding.edtQRCode.setText("");
                binding.edtRollNo.setText("");
                binding.edtLotNo.setText("");
                binding.edtSize.setText("");
                binding.edtManagedNo.setText("");
                binding.edtRackNo.setText("");
                binding.edtLocation.setText("");
                binding.edtSerialCode.setText("");
                binding.edtQtyMeter.setText("");
            }

            @Override
            public void onFailure(String reason) {
                CommonFunctions.getInstance().validationEmptyError(RMStorageActivity.this, reason);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!CommonFunctions.RollNo.isEmpty()){
            binding.edtQRCode.setText(CommonFunctions.BAR_CODE_VALUE);
            binding.edtRollNo.setText(CommonFunctions.RollNo);
            binding.edtLotNo.setText(CommonFunctions.LotNo);
            binding.edtSize.setText(CommonFunctions.Size);
            binding.edtManagedNo.setText(CommonFunctions.ManagedNo);

            CommonFunctions.RollNo = "";
            CommonFunctions.LotNo = "";
            CommonFunctions.Size = "";
            CommonFunctions.ManagedNo = "";

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    showAleartDialog();
                }
            }, 1500);

        }
    }

    private void inwardOrderList() {
        RmInwardOrdersJson apiJson = new RmInwardOrdersJson();
        apiJson.setMethod("rm_inward_order");

        Gson gson = new Gson();
        String input = gson.toJson(apiJson);
        System.out.println("Input ==> " + input);

        CommonApiCalls.getInstance().rmInwardOrders(RMStorageActivity.this, input, new CommonCallback.Listener() {
            @Override
            public void onSuccess(Object body) {
                RMInwardsOrdersApiResponse apiResponse = (RMInwardsOrdersApiResponse) body;
                 CommonFunctions.getInstance().successResponseToast(RMStorageActivity.this, apiResponse.getMsg());
                if (apiResponse.getData().size() > 0){
                    binding.rvInwardOrder.setVisibility(View.VISIBLE);
                    binding.txtNoDataFound.setVisibility(View.GONE);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(RMStorageActivity.this, LinearLayoutManager.VERTICAL, false);
                    binding.rvInwardOrder.setLayoutManager(layoutManager);

                    RmInwardOrdersListAdapter adapter = new RmInwardOrdersListAdapter(RMStorageActivity.this, apiResponse.getData());
                    binding.rvInwardOrder.setAdapter(adapter);
                }
                else {
                    binding.rvInwardOrder.setVisibility(View.GONE);
                    binding.txtNoDataFound.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(String reason) {
                CommonFunctions.getInstance().validationEmptyError(RMStorageActivity.this, reason);
            }
        });

    }

    private void showAleartDialog() {
        final Dialog dialog = new Dialog(RMStorageActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_alert_pop);
        dialog.setCancelable(false);
        TextView tvTitle = (TextView) dialog.findViewById(R.id.tvTitle);
        TextView tvMessage = (TextView) dialog.findViewById(R.id.tvMessage);

       dialog.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 2000);


    }
}
