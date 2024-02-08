package com.nichias.activity;

import static com.nichias.utils.CommonFunctions.PART_NO;
import static com.nichias.utils.CommonFunctions.SERIAL_CODE;
import static com.nichias.utils.CommonFunctions.SL_NO;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.nichias.R;
import com.nichias.adapter.GRNListAdapter;
import com.nichias.adapter.SecPrimaryListAdapter;
import com.nichias.api.CommonApiCalls;
import com.nichias.app_interfaces.CommonCallback;
import com.nichias.databinding.ActivitySecondaryPackingBinding;
import com.nichias.dummy_model.InspectionCheckJson;
import com.nichias.dummy_model.InwardArrayJson;
import com.nichias.dummy_model.SecondaryPackingJson;
import com.nichias.model_api.GrnProcessPrintBarCodeApiResponse;
import com.nichias.model_api.InspectionApiResponse;
import com.nichias.model_api.SecondaryPackingApiResponse;
import com.nichias.utils.CommonFunctions;

import java.util.ArrayList;
import java.util.List;

import hari.bounceview.BounceView;

public class SecondaryPackingActivity extends AppCompatActivity implements View.OnClickListener {
    ActivitySecondaryPackingBinding binding;
    private IntentIntegrator qrScan;
    private static final int REQUEST_CODE_QR_SCAN = 101;
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    List<InwardArrayJson.InwardArray> inwardArrayJson;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_secondary_packing);

        qrScan = new IntentIntegrator(SecondaryPackingActivity.this);
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }

        initialView();

        inwardArrayJson = new ArrayList<>();
    }

    private void initialView() {
        BounceView.addAnimTo(binding.imgBack);
        BounceView.addAnimTo(binding.txtSave);
        BounceView.addAnimTo(binding.imgScanner);

        binding.imgScanner.setOnClickListener(this);
        binding.imgBack.setOnClickListener(this);
        binding.txtSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == binding.imgBack){
            finish();
        }
        else if (view == binding.imgScanner){
            Bundle bundle = new Bundle();
            bundle.putString("from", "secondary_packing");
            CommonFunctions.getInstance().newIntent(SecondaryPackingActivity.this, ScanActivity.class, bundle, false, false);
        }
        else if (view == binding.txtSave){
            if (binding.edtLotNo.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(SecondaryPackingActivity.this,"Enter valid Lot No");
            }
            else if (inwardArrayJson.size() == 0) {
                CommonFunctions.getInstance().validationEmptyError(SecondaryPackingActivity.this,"Kindly Scan the valid pack");
            }
            else {
                final Dialog dialog = new Dialog(SecondaryPackingActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_alert_pop_sec_primary);
                dialog.setCancelable(false);

                TextView tvQue=  dialog.findViewById(R.id.tvQue);
                TextView txtNo=  dialog.findViewById(R.id.txtNo);
                TextView txtYes=  dialog.findViewById(R.id.txtYes);
                tvQue.setText("Are you sure to pack "+inwardArrayJson.size()+" No's(I mean the count of the records)");
                txtNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                txtYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();

                        apiCall();
                    }
                });

                Window window = dialog.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();
                wlp.gravity = Gravity.CENTER;
                window.setAttributes(wlp);
                dialog.show();
            }
        }
    }

    private void apiCall() {
        SecondaryPackingJson json = new SecondaryPackingJson();
        json.setMethod("Secondary_Packing");
        json.setProductLotNo(binding.edtLotNo.getText().toString().trim());

        List<SecondaryPackingJson.InwardArray> arrays = new ArrayList<>();

        for (int i = 0; i < inwardArrayJson.size(); i++) {
            SecondaryPackingJson.InwardArray inwardArray = new SecondaryPackingJson.InwardArray();
            inwardArray.setSl_No(inwardArrayJson.get(i).getSl_No());
            inwardArray.setSerialCode(inwardArrayJson.get(i).getSerialCode());
            inwardArray.setPartNo(inwardArrayJson.get(i).getPartNo());

            arrays.add(inwardArray);
        }

        json.setInwardArray(arrays);

        Gson gson = new Gson();
        String input = gson.toJson(json);
        System.out.println("Input ==> " + input);

        CommonApiCalls.getInstance().secondaryPacking(SecondaryPackingActivity.this, input, new CommonCallback.Listener() {
            @Override
            public void onSuccess(Object body) {
                SecondaryPackingApiResponse apiResponse = (SecondaryPackingApiResponse) body;

                //Toast.makeText(SecondaryPackingActivity.this,apiResponse.getMsg(),Toast.LENGTH_LONG);
                CommonFunctions.getInstance().successResponseToast(SecondaryPackingActivity.this, apiResponse.getMsg());

                inwardArrayJson = new ArrayList<>();
                binding.edtLotNo.setText("");
                binding.rvAddList.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(String reason) {
                CommonFunctions.getInstance().validationEmptyError(SecondaryPackingActivity.this, reason);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!SERIAL_CODE.isEmpty()){
            if (inwardArrayJson.size() == 0){
                InwardArrayJson.InwardArray json = new InwardArrayJson.InwardArray();
                json.setSl_No(SL_NO);
                json.setSerialCode(SERIAL_CODE);
                json.setPartNo(PART_NO);
                inwardArrayJson.add(json);
            }
            else {
                String alreadyValue = "true";
                for (int i = 0; i < inwardArrayJson.size(); i++) {
                    if (inwardArrayJson.get(i).getSerialCode().equals(SERIAL_CODE) && inwardArrayJson.get(i).getPartNo().equals(PART_NO)){
                        alreadyValue = "false";
                        break;
                    }
                }

                if (alreadyValue.equals("true")){
                    InwardArrayJson.InwardArray json = new InwardArrayJson.InwardArray();
                    json.setSl_No(SL_NO);
                    json.setSerialCode(SERIAL_CODE);
                    json.setPartNo(PART_NO);
                    inwardArrayJson.add(json);
                }
            }

            loadList();
            SL_NO = "";
            SERIAL_CODE = "";
            PART_NO = "";
        }
    }

    private void loadList() {
        if (inwardArrayJson.size() > 0){
            binding.rvAddList.setVisibility(View.VISIBLE);
            @SuppressLint("WrongConstant")
            LinearLayoutManager layoutManager = new LinearLayoutManager(SecondaryPackingActivity.this, LinearLayoutManager.VERTICAL, false);
            binding.rvAddList.setLayoutManager(layoutManager);
            SecPrimaryListAdapter adapter = new SecPrimaryListAdapter(SecondaryPackingActivity.this, inwardArrayJson);
            binding.rvAddList.setAdapter(adapter);
        }
        else {
            binding.rvAddList.setVisibility(View.GONE);
        }
    }

}
