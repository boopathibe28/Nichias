package com.nichias.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.nichias.R;
import com.nichias.adapter.LineItemListAdapter;
import com.nichias.api.CommonApiCalls;
import com.nichias.app_interfaces.CommonCallback;
import com.nichias.databinding.ActivityFgDispatchNewBinding;
import com.nichias.dummy_model.FgDispatchApiResponse;
import com.nichias.dummy_model.FgDispatchDocNoJson;
import com.nichias.dummy_model.FgDispatchLoadApiResponse;
import com.nichias.dummy_model.FgDispatchLoadJson;
import com.nichias.dummy_model.WIPStoreJson;
import com.nichias.model_api.WIPStorageApiResponse;
import com.nichias.utils.CommonFunctions;

import java.util.ArrayList;
import java.util.List;

import hari.bounceview.BounceView;

public class FGDispatchNew extends AppCompatActivity implements View.OnClickListener {
    ActivityFgDispatchNewBinding binding;
    private IntentIntegrator qrScan;
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    List<FgDispatchApiResponse.Result> lineItemsArray;
    LineItemListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_fg_dispatch_new);

        qrScan = new IntentIntegrator(this);
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }

        initialView();

    }

    private void initialView() {
        BounceView.addAnimTo(binding.imgBack);
        BounceView.addAnimTo(binding.imgScanner);
        BounceView.addAnimTo(binding.txtGo);
        BounceView.addAnimTo(binding.txtAddLine);
        BounceView.addAnimTo(binding.txtLoad);

        binding.imgBack.setOnClickListener(this);
        binding.imgScanner.setOnClickListener(this);
        binding.txtGo.setOnClickListener(this);
        binding.txtAddLine.setOnClickListener(this);
        binding.txtLoad.setOnClickListener(this);

        binding.tvTitle.setText("FG Dispatch");
    }

    @Override
    public void onClick(View view) {
        if (view == binding.imgBack) {
            finish();
        }
        //Scanner
        else if (view == binding.imgScanner){
            Bundle bundle = new Bundle();
            bundle.putString("from", "12");
            CommonFunctions.getInstance().newIntent(FGDispatchNew.this, ScannedBarcodeActivity.class, bundle, false, false);
        }
        // Go
        else if (view == binding.txtGo){
            if (binding.edtDocNo.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationError(FGDispatchNew.this,"Enter valid Document Number");
            }
            else {
                // Api Call
                FgDispatchDocNoJson fgDispatchDocNoJson = new FgDispatchDocNoJson();
                fgDispatchDocNoJson.setMethod("get");
                fgDispatchDocNoJson.setDocno(binding.edtDocNo.getText().toString().trim());

                Gson gson = new Gson();
                String input = gson.toJson(fgDispatchDocNoJson);
                System.out.println("Input ==> " + input);

                CommonFunctions.DOC_NO = binding.edtDocNo.getText().toString().trim();
                apiGetListCall(input);
            }
        }
        // AddLine
        else if (view == binding.txtAddLine){
            addMoreLineItem();
        }
        // Load
        else if (view == binding.txtLoad){
            if (lineItemsArray != null && lineItemsArray.size() > 0){
                final Dialog dialog = new Dialog(FGDispatchNew.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_fg_dispatch);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                dialog.setCancelable(true);

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

                        FgDispatchLoadJson fgDispatchLoadJson = new FgDispatchLoadJson();
                        fgDispatchLoadJson.setMethod("update");
                        fgDispatchLoadJson.setDocno(binding.edtDocNo.getText().toString().trim());

                        List<FgDispatchLoadJson.Value> valueList = new ArrayList<>();

                        if (lineItemsArray.size() > 0){
                            for (int i = 0; i < lineItemsArray.size(); i++) {
                                if (!lineItemsArray.get(i).getBarcode_Batchno().isEmpty()){
                                    FgDispatchLoadJson.Value data = new FgDispatchLoadJson.Value();
                                    data.setBatch(lineItemsArray.get(i).getBatch_No());
                                    data.setBarcode_batch(lineItemsArray.get(i).getBarcode_Batchno());
                                    data.setBarcode_qty(lineItemsArray.get(i).getBarcode_Qty());
                                    valueList.add(data);
                                }
                                else if (!lineItemsArray.get(i).getBatch_No().isEmpty()){
                                    FgDispatchLoadJson.Value data = new FgDispatchLoadJson.Value();
                                    data.setBatch(lineItemsArray.get(i).getBatch_No());
                                    data.setBarcode_batch("");
                                    data.setBarcode_qty("");
                                    valueList.add(data);
                                }
                            }

                            fgDispatchLoadJson.setValues(valueList);
                        }

                        Gson gson = new Gson();
                        String input = gson.toJson(fgDispatchLoadJson);
                        System.out.println("Input ==> " + input);

                        finalLoadApiCall(input);

                    }
                });

            }

        }
    }
// Load Api
    private void finalLoadApiCall(String input){
        CommonApiCalls.getInstance().fgDispatchLoad(FGDispatchNew.this, input, new CommonCallback.Listener() {
            @Override
            public void onSuccess(Object body) {
                FgDispatchLoadApiResponse apiResponse = (FgDispatchLoadApiResponse) body;
                CommonFunctions.getInstance().successResponseToast(FGDispatchNew.this, apiResponse.getMsg());
                lineItemsArray.clear();
                binding.edtDocNo.setText("");
                loadLineItemList(lineItemsArray);
            }

            @Override
            public void onFailure(String reason) {
                CommonFunctions.getInstance().validationEmptyError(FGDispatchNew.this, reason);
            }
        });
    }

// Get List Api
    private void apiGetListCall(String input){
        CommonApiCalls.getInstance().fgDispatch(FGDispatchNew.this, input, new CommonCallback.Listener() {
            @Override
            public void onSuccess(Object body) {
                FgDispatchApiResponse fgDocNoLineItem = (FgDispatchApiResponse) body;
                //CommonFunctions.getInstance().successResponseToast(FGDispatchNew.this, apiResponse.getMsg());

                lineItemsArray = fgDocNoLineItem.getResult();

                loadLineItemList(lineItemsArray);
            }

            @Override
            public void onFailure(String reason) {
                CommonFunctions.getInstance().validationEmptyError(FGDispatchNew.this, reason);
            }
        });
    }


    private void loadLineItemList(List<FgDispatchApiResponse.Result> data){
        if (data.size() > 0) {
            binding.rvLineItem.setVisibility(View.VISIBLE);

            LinearLayoutManager layoutManager = new LinearLayoutManager(FGDispatchNew.this, LinearLayoutManager.VERTICAL, false);
            binding.rvLineItem.setLayoutManager(layoutManager);

            adapter = new LineItemListAdapter(FGDispatchNew.this, data);
            binding.rvLineItem.setAdapter(adapter);
        }
        else {
            binding.rvLineItem.setVisibility(View.GONE);
        }
    }

    private void addMoreLineItem(){
        /*final LinearLayout newView = (LinearLayout) FGDispatchNew.this.getLayoutInflater().inflate(R.layout.inflate_line_item, null);

        LinearLayout lyoutParent =  newView.findViewById(R.id.lyoutParent);
        TextView txtSno = newView.findViewById(R.id.txtSno);
        ImageView imgCancel = newView.findViewById(R.id.imgCancel);

        txtSno.setText("");

        imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.llyoutLineItem.removeView(newView);
            }
        });

        // Add View
        binding.llyoutLineItem.addView(newView);*/

        if (lineItemsArray == null){
            lineItemsArray = new ArrayList<>();
        }
        CommonFunctions.DOC_NO = binding.edtDocNo.getText().toString().trim();

        FgDispatchApiResponse.Result lineItem = new FgDispatchApiResponse.Result();
        lineItem.setBatch_No("");
        lineItem.setFG_Locaton("");
        lineItem.setQuantity("");
        lineItem.setBarcode_Batchno("");
        lineItem.setBarcode_Qty("");
        lineItem.setThick("");
        lineItem.setWidth("");
        lineItem.setLength("");
        lineItem.setGrade("");

        lineItemsArray.add(lineItem);

        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
        else {
            loadLineItemList(lineItemsArray);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!CommonFunctions.FG_Scan_Batch_No.isEmpty()){

            String conditions = "";
            if (lineItemsArray != null){
                for (int i = 0; i < lineItemsArray.size(); i++) {
                    if (lineItemsArray.get(i).getBatch_No().equals(CommonFunctions.FG_Scan_Batch_No))
                    {
                        lineItemsArray.get(i).setBarcode_Batchno(CommonFunctions.FG_Scan_Batch_No);
                        lineItemsArray.get(i).setBarcode_Qty(CommonFunctions.FG_Scan_Batch_QTY);
                        conditions = "true";
                    }
                    else if (lineItemsArray.get(i).getThick().equals(CommonFunctions.FG_Scan_Thick) &&
                             lineItemsArray.get(i).getWidth().equals(CommonFunctions.FG_Scan_Width) &&
                             lineItemsArray.get(i).getGrade().equals(CommonFunctions.FG_Scan_Grade))
                    {
                        //lineItemsArray.get(i).setBatch_No(CommonFunctions.FG_Scan_Batch_No);
                        lineItemsArray.get(i).setBatch_No("");
                        lineItemsArray.get(i).setBarcode_Batchno(CommonFunctions.FG_Scan_Batch_No);
                        lineItemsArray.get(i).setBarcode_Qty(CommonFunctions.FG_Scan_Batch_QTY);
                        conditions = "true";
                    }
                    else if (lineItemsArray.get(i).getBatch_No().isEmpty() && lineItemsArray.get(i).getBarcode_Batchno().isEmpty()){
                       // lineItemsArray.get(i).setBatch_No(CommonFunctions.FG_Scan_Batch_No);
                        lineItemsArray.get(i).setBatch_No("");
                        lineItemsArray.get(i).setBarcode_Batchno(CommonFunctions.FG_Scan_Batch_No);
                        lineItemsArray.get(i).setBarcode_Qty(CommonFunctions.FG_Scan_Batch_QTY);
                        lineItemsArray.get(i).setThick(CommonFunctions.FG_Scan_Thick);
                        lineItemsArray.get(i).setWidth(CommonFunctions.FG_Scan_Width);
                        lineItemsArray.get(i).setGrade(CommonFunctions.FG_Scan_Grade);
                        if (!CommonFunctions.FG_Scan_Length.isEmpty()){
                            lineItemsArray.get(i).setLength(CommonFunctions.FG_Scan_Length);
                            CommonFunctions.FG_Scan_Length = "";
                        }
                        conditions = "true";
                        break;
                    }
                }

                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }
                else {
                    loadLineItemList(lineItemsArray);
                }

                if (conditions.equals("")){
                    CommonFunctions.getInstance().validationError(FGDispatchNew.this,"Data not matched");
                }
            }
        }
    }

}
