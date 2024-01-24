package com.nichias.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.nichias.R;
import com.nichias.adapter.BusinessTypeListAdapter;
import com.nichias.api.CommonApiCalls;
import com.nichias.app_interfaces.CommonCallback;
import com.nichias.databinding.ActivityPrintBinding;
import com.nichias.dummy_model.BatchList;
import com.nichias.dummy_model.BusinessTypeModel;
import com.nichias.dummy_model.BusinessTypeListApiResponse;
import com.nichias.dummy_model.OpBatchListApiResponse;
import com.nichias.dummy_model.PrintingListApiResponse;
import com.nichias.dummy_model.PrintingProcess;
import com.nichias.interfaces.BusinessType;
import com.nichias.interfaces.OpBatchPrint;
import com.nichias.utils.CommonFunctions;

import java.util.ArrayList;
import java.util.List;

import hari.bounceview.BounceView;

public class PrintActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityPrintBinding binding;
    private IntentIntegrator qrScan;
    private static final int MY_CAMERA_REQUEST_CODE = 100;

    List<String> selectedPrint;
    List<BusinessTypeListApiResponse.BusinessType> businessTypeList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_print);

        qrScan = new IntentIntegrator(this);
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }

        selectedPrint = new ArrayList<String>();
        initialView();

    }
    private void initialView() {
        BounceView.addAnimTo(binding.imgBack);
        BounceView.addAnimTo(binding.txtSearch);
        BounceView.addAnimTo(binding.txtPrint);
        binding.tvTitle.setText("Print");

        binding.imgBack.setOnClickListener(this);
        binding.lyoutSelectBusinessCase.setOnClickListener(this);
        binding.edtSelectBusinessCase.setOnClickListener(this);
        binding.imgeSelectBusinessCase.setOnClickListener(this);
        binding.txtSearch.setOnClickListener(this);
        binding.txtPrint.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == binding.imgBack){
            finish();
        }
        else if (view == binding.lyoutSelectBusinessCase || view == binding.edtSelectBusinessCase || view == binding.imgeSelectBusinessCase){
            typeDialog();
        }
        else if (view == binding.txtSearch){
           getList();
        }
        else if (view == binding.txtPrint){
            if (selectedPrint.size() > 0) {
                printerPreviewDialog();
            }
            else {
                CommonFunctions.getInstance().validationError(PrintActivity.this,"Select Print OpBatch");
            }
        }
    }

    private void getList(){
        if (binding.edtSelectBusinessCase.getText().toString().trim().isEmpty()){
            CommonFunctions.getInstance().validationError(PrintActivity.this,"Select Business Case");
        }
        else if (binding.edtDocNo.getText().toString().trim().isEmpty()){
            CommonFunctions.getInstance().validationError(PrintActivity.this,"Enter valid Doc No");
        }
        else {
            BatchList batchList = new BatchList();
            batchList.setMethod("get_opbatches");
            batchList.setDocno(binding.edtDocNo.getText().toString().trim());
            batchList.setType(binding.edtSelectBusinessCase.getText().toString().trim());

            Gson gson = new Gson();
            String input = gson.toJson(batchList);
            System.out.println("Input ==> " + input);

            opBatchGetApiCall(input);
        }
    }

    private void printerPreviewDialog(){
        final Dialog dialog = new Dialog(PrintActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_printer_preview);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.setCancelable(false);

        ImageView imgClose =  dialog.findViewById(R.id.imgClose);
        TextView txtHeader =  dialog.findViewById(R.id.txtHeader);
        TextView txtOk =  dialog.findViewById(R.id.txtOk);
        LinearLayout lyoutInflate =  dialog.findViewById(R.id.lyoutInflate);

        dialog.show();

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        if (selectedPrint.size() > 0){
            lyoutInflate.removeAllViews();

            for (int i = 0; i < selectedPrint.size(); i++) {
                LinearLayout newView = (LinearLayout) (PrintActivity.this).getLayoutInflater().inflate(R.layout.adapter_selected_opbatch, null);

                TextView txtOpBatch = newView.findViewById(R.id.txtOpBatch);

                txtOpBatch.setText(selectedPrint.get(i));

                // Add View
                lyoutInflate.addView(newView);
            }
        }

        txtOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                PrintingProcess printingProcess = new PrintingProcess();
                printingProcess.setMethod("print");
                printingProcess.setType("Sales Return");
                printingProcess.setDocno(binding.edtDocNo.getText().toString().trim());
                printingProcess.setBatch(selectedPrint.toString());

                Gson gson = new Gson();
                String input = gson.toJson(printingProcess);
                System.out.println("Input ==> " + input);

                printApiCall(input);
            }
        });



    }

    private void typeDialog() {
        final Dialog dialog = new Dialog(PrintActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_select_business_case);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.setCancelable(false);

        ImageView imgClose =  dialog.findViewById(R.id.imgClose);
        TextView txtHeader =  dialog.findViewById(R.id.txtHeader);
        TextView txtSalesReturn =  dialog.findViewById(R.id.txtSalesReturn);
        txtSalesReturn.setVisibility(View.GONE);
        RecyclerView rvBusinessTypeList =  dialog.findViewById(R.id.rvBusinessTypeList);

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        txtSalesReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.edtSelectBusinessCase.setText("Sales Return");

                dialog.dismiss();
            }
        });

        if (businessTypeList != null) {
            if (businessTypeList.size() > 0) {
                rvBusinessTypeList.setVisibility(View.VISIBLE);
                txtSalesReturn.setVisibility(View.GONE);

                @SuppressLint("WrongConstant")
                LinearLayoutManager layoutManager = new LinearLayoutManager(PrintActivity.this, LinearLayoutManager.VERTICAL, false);
                rvBusinessTypeList.setLayoutManager(layoutManager);
                BusinessTypeListAdapter adapter = new BusinessTypeListAdapter(PrintActivity.this, businessTypeList, new BusinessType() {
                    @Override
                    public void onClick(String value) {
                        binding.edtSelectBusinessCase.setText(value);

                        dialog.dismiss();
                    }
                });
                rvBusinessTypeList.setAdapter(adapter);
            }
            else {
                rvBusinessTypeList.setVisibility(View.GONE);
                txtSalesReturn.setVisibility(View.GONE);
            }
        }

        dialog.show();
    }

    private void opBatchGetApiCall(String input){
        CommonApiCalls.getInstance().getOpBatchList(PrintActivity.this, input, new CommonCallback.Listener() {
            @Override
            public void onSuccess(Object body) {
                OpBatchListApiResponse apiResponse = (OpBatchListApiResponse) body;
                CommonFunctions.getInstance().successResponseToast(PrintActivity.this, apiResponse.getMsg());

                if (apiResponse.getResult().size() > 0){
                    selectedPrint = new ArrayList<String>();

                    loadOpBatchList(apiResponse.getResult());
                }

            }

            @Override
            public void onFailure(String reason) {
                CommonFunctions.getInstance().validationEmptyError(PrintActivity.this, reason);
            }
        });
    }

    private void loadOpBatchList(List<String> data){
        if (data.size() > 0){
            binding.rvOpBatchList.setVisibility(View.VISIBLE);
            binding.txtPrint.setVisibility(View.VISIBLE);

            String docNo = binding.edtDocNo.getText().toString().trim();
            @SuppressLint("WrongConstant")
            LinearLayoutManager layoutManager = new LinearLayoutManager(PrintActivity.this, LinearLayoutManager.VERTICAL, false);
            binding.rvOpBatchList.setLayoutManager(layoutManager);
            OpBatchListAdapter adapter = new OpBatchListAdapter(PrintActivity.this, data,docNo,selectedPrint,new OpBatchPrint(){
                @Override
                public void onClick(String selectedValue) {
                    if (selectedPrint.size() == 0){
                        selectedPrint.add(selectedValue);
                    }
                    else if (selectedPrint.size() > 0){
                        String isAlready = "";
                        for (int i = 0; i < selectedPrint.size(); i++) {
                            if (selectedPrint.get(i).equals(selectedValue)){
                                selectedPrint.remove(i);
                                isAlready = "true";
                            }
                        }

                        if (isAlready.equals("")){
                            selectedPrint.add(selectedValue);
                        }
                    }

                    loadOpBatchList(data);
                }
            });
            binding.rvOpBatchList.setAdapter(adapter);
        }
        else {
            binding.rvOpBatchList.setVisibility(View.GONE);
            binding.txtPrint.setVisibility(View.GONE);
        }

    }



    private void printApiCall(String input){
        CommonApiCalls.getInstance().printingList(PrintActivity.this, input, new CommonCallback.Listener() {
            @Override
            public void onSuccess(Object body) {
                PrintingListApiResponse apiResponse = (PrintingListApiResponse) body;
                CommonFunctions.getInstance().successResponseToast(PrintActivity.this, apiResponse.getMsg());
                getList();
            }

            @Override
            public void onFailure(String reason) {
                CommonFunctions.getInstance().validationEmptyError(PrintActivity.this, reason);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        getBusinessTypeApi();
    }

    private void getBusinessTypeApi(){
        BusinessTypeModel businessTypeModel = new BusinessTypeModel();
        businessTypeModel.setMethod("business_type");

        Gson gson = new Gson();
        String input = gson.toJson(businessTypeModel);
        System.out.println("Input ==> " + input);

        CommonApiCalls.getInstance().business_type(PrintActivity.this, input, new CommonCallback.Listener() {
            @Override
            public void onSuccess(Object body) {
                BusinessTypeListApiResponse apiResponse = (BusinessTypeListApiResponse) body;

                if (apiResponse.getResult().getBusinessTypes().size() > 0){
                    businessTypeList = apiResponse.getResult().getBusinessTypes();

                    //CommonFunctions.getInstance().successResponseToast(PrintActivity.this,apiResponse.getResult().getBusiness_types()+"");
                }

            }

            @Override
            public void onFailure(String reason) {
                CommonFunctions.getInstance().validationEmptyError(PrintActivity.this, reason);
            }
        });

    }
}
