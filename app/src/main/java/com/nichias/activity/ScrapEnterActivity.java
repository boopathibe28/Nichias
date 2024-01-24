package com.nichias.activity;

import static com.nichias.utils.CommonFunctions.WorkOrder_TRIMMED_SCRAP;
import static com.nichias.utils.CommonFunctions.ssiplID;

import static io.realm.mongodb.ErrorCode.Type.JSON;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
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
import com.nichias.adapter.DeletePrimeListAdapter;
import com.nichias.adapter.LineItemListAdapter;
import com.nichias.adapter.TrimmedScrapAdapter;
import com.nichias.api.CommonApiCalls;
import com.nichias.app_interfaces.CommonCallback;
import com.nichias.databinding.ActivityHrslittingBinding;
import com.nichias.databinding.ActivityScrapEnterBinding;
import com.nichias.dummy_model.AddPrimeApiResponse;
import com.nichias.dummy_model.AddPrimeJson;
import com.nichias.dummy_model.DeletePrimeGetApiResponse;
import com.nichias.dummy_model.FGJson;
import com.nichias.dummy_model.OpBatchArray;
import com.nichias.dummy_model.RollEndCut;
import com.nichias.dummy_model.RollEndCutWorkOrder;
import com.nichias.dummy_model.ScrapJson;
import com.nichias.dummy_model.Trimmed_Scrap_Get;
import com.nichias.model_api.FGDataApiResponse;
import com.nichias.model_api.HrsSlittingApiResponse;
import com.nichias.model_api.RollbackEndCutWorkOrder;
import com.nichias.model_api.RoolbackEndcutApiResponse;
import com.nichias.model_api.TrimmedScrapApiResponse;
import com.nichias.model_api.TrimmedScrapPostApiResponse;
import com.nichias.utils.CommonFunctions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import hari.bounceview.BounceView;

public class ScrapEnterActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityScrapEnterBinding binding;
    private IntentIntegrator qrScan;
    private static final int REQUEST_CODE_QR_SCAN = 101;
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    String selectionTab = "";
    DeletePrimeListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_scrap_enter);

        qrScan = new IntentIntegrator(ScrapEnterActivity.this);
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }

        initialView();

    }

    private void initialView() {
        BounceView.addAnimTo(binding.imgBack);
        BounceView.addAnimTo(binding.rlyoutTrimmedScrap);
        BounceView.addAnimTo(binding.rlyoutRollbackEndCut);
        BounceView.addAnimTo(binding.txtGo);
        BounceView.addAnimTo(binding.imgRollbackWorkOrder);
        BounceView.addAnimTo(binding.ImgScanWorkOrderTrimmed);
        BounceView.addAnimTo(binding.txtInflateLoad);
        BounceView.addAnimTo(binding.txtRollbackWorkOrderGo);
        BounceView.addAnimTo(binding.txtAddPrimeCreate);
        BounceView.addAnimTo(binding.txtDeletePrimeGo);
        BounceView.addAnimTo(binding.ImgScanSSIPL);

        binding.txtAddPrimeCreate.setOnClickListener(this);
        binding.txtDeletePrimeGo.setOnClickListener(this);
        binding.imgBack.setOnClickListener(this);
        binding.rlyoutTrimmedScrap.setOnClickListener(this);
        binding.rlyoutRollbackEndCut.setOnClickListener(this);
        binding.txtGo.setOnClickListener(this);
        binding.ImgScanWorkOrderTrimmed.setOnClickListener(this);
        binding.imgRollbackWorkOrder.setOnClickListener(this);
        binding.txtRollbackWorkOrderGo.setOnClickListener(this);

        binding.lyoutRollbackDrop.setOnClickListener(this);
        binding.edtRollbackDrop.setOnClickListener(this);
        binding.imgeRollbackDrop.setOnClickListener(this);
        binding.txtLoadRollback.setOnClickListener(this);
        binding.txtInflateLoad.setOnClickListener(this);
        binding.ImgScanSSIPL.setOnClickListener(this);

        selectionTab = "trimmed";
        binding.viewTrimmedScrap.setVisibility(View.VISIBLE);
        binding.viewRollbackEndCut.setVisibility(View.GONE);
        binding.lyoutTrimmedScrap.setVisibility(View.VISIBLE);
        binding.lyoutRollbackEndCut.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        if (view == binding.imgBack){
            finish();
        }
        else if (view == binding.rlyoutTrimmedScrap){
            selectionTab = "trimmed";
            binding.viewTrimmedScrap.setVisibility(View.VISIBLE);
            binding.viewRollbackEndCut.setVisibility(View.GONE);
            binding.lyoutTrimmedScrap.setVisibility(View.VISIBLE);
            binding.lyoutRollbackEndCut.setVisibility(View.GONE);
        }
        else if (view == binding.ImgScanSSIPL){
            Bundle bundle = new Bundle();
            bundle.putString("from", "11");
            CommonFunctions.getInstance().newIntent(ScrapEnterActivity.this, ScannedBarcodeActivity.class, bundle, false, false);
        }
        else if (view == binding.rlyoutRollbackEndCut){
            selectionTab = "roll";
            binding.viewTrimmedScrap.setVisibility(View.GONE);
            binding.viewRollbackEndCut.setVisibility(View.VISIBLE);
            binding.lyoutTrimmedScrap.setVisibility(View.GONE);
            binding.lyoutRollbackEndCut.setVisibility(View.VISIBLE);
        }
        else if (view == binding.ImgScanWorkOrderTrimmed || view == binding.imgRollbackWorkOrder){
            Bundle bundle = new Bundle();
            bundle.putString("from", "8");
            CommonFunctions.getInstance().newIntent(ScrapEnterActivity.this, ScannedBarcodeActivity.class, bundle, false, false);
        }
        else if (view == binding.txtGo){
            if (binding.edtWorkOrderTrimmed.getText().toString().trim().equals("")){
                CommonFunctions.getInstance().validationError(ScrapEnterActivity.this,"Enter valid Work Order");
            }
            else {
                trimmedScrapGet();
            }
        }
        else if (view == binding.lyoutRollbackDrop || view == binding.edtRollbackDrop || view == binding.imgeRollbackDrop){
            typeDialog();
        }
        else if (view == binding.txtLoadRollback) {
            if (binding.edtRollbackWorkOrder.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationError(ScrapEnterActivity.this,"Enter valid Work Order");
            }
            else if (binding.edtRollbackDrop.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationError(ScrapEnterActivity.this,"Select Roolback [OR] End Cut");
            }
            else if (binding.edtRollbackWeight.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationError(ScrapEnterActivity.this,"Enter valid Weight");
            }
            else if (binding.edtSSIPLID.getText().toString().trim().isEmpty()) {
                CommonFunctions.getInstance().validationError(ScrapEnterActivity.this, "Enter valid SSIPL ID");
            }
            else {
                verificationRollBackEndCutDialog();
            }
        }
        else if (view == binding.txtRollbackWorkOrderGo){
            if (binding.edtRollbackWorkOrder.getText().toString().trim().isEmpty()) {
                CommonFunctions.getInstance().validationError(ScrapEnterActivity.this, "Enter valid Work Order");
            }
            else {
                RollEndCutWorkOrder rollEndCutWorkOrder = new RollEndCutWorkOrder();
                rollEndCutWorkOrder.setWo_num(binding.edtRollbackWorkOrder.getText().toString().trim());
                rollEndCutWorkOrder.setMethod("get");

                Gson gson = new Gson();
                String input = gson.toJson(rollEndCutWorkOrder);
                System.out.println("Input ==> " + input);

                workOrderApiCall(input);
            }
        }
        else if (view == binding.txtInflateLoad){
            if (binding.lyoutInflateData.getChildCount() > 0){
                List<OpBatchArray.Result> list = new ArrayList<>();
                for (int i = 0; i < binding.lyoutInflateData.getChildCount(); i++) {
                    OpBatchArray.Result data = new OpBatchArray.Result();
                    View v = binding.lyoutInflateData.getChildAt(i);
                    EditText edtOpBatch = v.findViewById(R.id.edtOpBatch);
                    EditText edtWeight = v.findViewById(R.id.edtWeight);

                    String data1 =  edtOpBatch.getText().toString();
                    String data2 =  edtWeight.getText().toString();

                    data.setData1(data1);
                    data.setData2(data2);
                    if (data2.isEmpty()){
                        break;
                    }
                    list.add(data);
                }
                verificationScrpDataDialog(list);
            }
            else {
                CommonFunctions.getInstance().validationError(ScrapEnterActivity.this,"Enter valid Weight");
            }
        }
        else if (view == binding.txtAddPrimeCreate){
            if (binding.edtRollbackWorkOrder.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationError(ScrapEnterActivity.this, "Enter valid WorkOrder");
            }
            else if (binding.edtIPBatch.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationError(ScrapEnterActivity.this, "Enter valid IPBatch");
            }
            else if (binding.edtPhysicalQuantity.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationError(ScrapEnterActivity.this, "Enter valid Physical Quantity");
            }
            else if (binding.edtActualThick.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationError(ScrapEnterActivity.this, "Enter valid Actual Thick");
            }
            else if (binding.edtActualWidth.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationError(ScrapEnterActivity.this, "Enter valid Actual Width");
            }
            else if (binding.edtActualLength.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationError(ScrapEnterActivity.this, "Enter valid Actual Length");
            }
            else if (binding.edtActualPcs.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationError(ScrapEnterActivity.this, "Enter valid Actual Pcs");
            }
            else {
                AddPrimeJson addPrimeJson = new AddPrimeJson();
                addPrimeJson.setMethod("insert_prime");
                addPrimeJson.setSchedule_num(binding.txtScheduleNumber.getText().toString().trim());
                addPrimeJson.setWo_num(binding.edtRollbackWorkOrder.getText().toString().trim());
                addPrimeJson.setSsipl(binding.edtIPBatch.getText().toString().trim());
                addPrimeJson.setWeight(binding.edtPhysicalQuantity.getText().toString().trim());
                addPrimeJson.setThick(binding.edtActualThick.getText().toString().trim());
                addPrimeJson.setWidth(binding.edtActualWidth.getText().toString().trim());
                addPrimeJson.setLength(binding.edtActualLength.getText().toString().trim());
                addPrimeJson.setPcs(binding.edtActualPcs.getText().toString().trim());

                Gson gson = new Gson();
                String input = gson.toJson(addPrimeJson);
                System.out.println("Input ==> " + input);

                apiCallAddPrime(input);
            }
        }
        else if (view == binding.txtDeletePrimeGo){
            if (binding.edtRollbackWorkOrder.getText().toString().trim().isEmpty()) {
                CommonFunctions.getInstance().validationError(ScrapEnterActivity.this, "Enter valid Work Order");
            }
            else {
                RollEndCutWorkOrder rollEndCutWorkOrder = new RollEndCutWorkOrder();
                rollEndCutWorkOrder.setWo_num(binding.edtRollbackWorkOrder.getText().toString().trim());
                rollEndCutWorkOrder.setMethod("get_opbatches");

                Gson gson = new Gson();
                String input = gson.toJson(rollEndCutWorkOrder);
                System.out.println("Input ==> " + input);

                deleteApiCall(input);
            }
        }
    }


    private void apiCallAddPrime(String input) {
        CommonApiCalls.getInstance().addPrime(ScrapEnterActivity.this, input, new CommonCallback.Listener() {
            @Override
            public void onSuccess(Object body) {
                AddPrimeApiResponse apiResponse = (AddPrimeApiResponse) body;

                CommonFunctions.getInstance().successResponseToast(ScrapEnterActivity.this, apiResponse.getMsg());

                binding.edtRollbackWorkOrder.setText("");
                binding.edtRollbackDrop.setText("");

                binding.txtScheduleNumber.setText("");
                binding.lyoutScheduleNumber.setVisibility(View.GONE);
                binding.lyoutAddPrime.setVisibility(View.GONE);

                binding.edtIPBatch.setText("");
                binding.edtPhysicalQuantity.setText("");
                binding.edtActualThick.setText("");
                binding.edtActualWidth.setText("");
                binding.edtActualLength.setText("");
                binding.edtActualPcs.setText("");

            }

            @Override
            public void onFailure(String reason) {
                CommonFunctions.getInstance().validationEmptyError(ScrapEnterActivity.this, reason);
            }
        });
    }

    private void verificationScrpDataDialog(List<OpBatchArray.Result> list) {
        final Dialog dialog = new Dialog(ScrapEnterActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_verification_scrap_data);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.setCancelable(true);

        LinearLayout lyoutInflate =  dialog.findViewById(R.id.lyoutInflate);
        TextView txtValid =  dialog.findViewById(R.id.txtValid);
        TextView txtCancel =  dialog.findViewById(R.id.txtCancel);


        for (int count = 0; count < list.size(); count++) {
            LinearLayout newView = (LinearLayout) (ScrapEnterActivity.this).getLayoutInflater().inflate(R.layout.adapter_trimmed_scrap, null);

            EditText edtOpBatch = newView.findViewById(R.id.edtOpBatch);
            EditText edtWeight = newView.findViewById(R.id.edtWeight);

            edtOpBatch.setText(list.get(count).getData1());
            edtWeight.setText(list.get(count).getData2());

            // Add View
            lyoutInflate.addView(newView);
        }

        txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        txtValid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                scrapApiCall(list);
            }
        });
        dialog.show();
    }

    private void scrapApiCall(List<OpBatchArray.Result> list) {
        JSONArray jrrM = new JSONArray();
        for(int i=0; i<list.size(); i++){
            jrrM.put(list.get(i).getData1());
        }

        JSONArray jsonArray = new JSONArray();
        for(int i=0; i<list.size(); i++){
            jsonArray.put(list.get(i).getData2());
        }

        JSONObject jsonResult = new JSONObject();
        try {
            jsonResult.put("method", "load");
            jsonResult.put("ssipl", jrrM);
            jsonResult.put("weight", jsonArray);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        String input = jsonResult.toString();
        System.out.println("Input ==> " + jsonResult);

        scrapPostApiCall(input);
    }

    private void scrapPostApiCall(String input) {
        CommonApiCalls.getInstance().postTrimmedScrap(ScrapEnterActivity.this, input, new CommonCallback.Listener() {
            @Override
            public void onSuccess(Object body) {
                TrimmedScrapPostApiResponse apiResponse = (TrimmedScrapPostApiResponse) body;

               binding.lyoutInflateData.setVisibility(View.GONE);
               binding.txtInflateLoad.setVisibility(View.GONE);
               binding.edtWorkOrderTrimmed.setText("");

               if (apiResponse.getStatus().equals("Failure")){
                   CommonFunctions.getInstance().validationError(ScrapEnterActivity.this, apiResponse.getMsg());
               }
               else {
                   CommonFunctions.getInstance().successResponseToast(ScrapEnterActivity.this, apiResponse.getMsg());
               }
            }

            @Override
            public void onFailure(String reason) {
                CommonFunctions.getInstance().validationEmptyError(ScrapEnterActivity.this, reason);
            }
        });
    }


    private void verificationRollBackEndCutDialog() {
        final Dialog dialog = new Dialog(ScrapEnterActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_verification_roll_end);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.setCancelable(true);

        TextView txtWorkOrder =  dialog.findViewById(R.id.txtWorkOrder);
        TextView txtType =  dialog.findViewById(R.id.txtType);
        TextView txtWeight =  dialog.findViewById(R.id.txtWeight);
        TextView txtSSIPLID =  dialog.findViewById(R.id.txtSSIPLID);
        txtSSIPLID.setVisibility(View.VISIBLE);
        TextView txtValid =  dialog.findViewById(R.id.txtValid);
        TextView txtCancel =  dialog.findViewById(R.id.txtCancel);

        txtWorkOrder.setText("Work Order : "+binding.edtRollbackWorkOrder.getText().toString().trim());
        txtType.setText("Type : "+binding.edtRollbackDrop.getText().toString().trim());
        txtWeight.setText("Weight : "+binding.edtRollbackWeight.getText().toString().trim());
        txtSSIPLID.setText("SSIPL ID : " + binding.edtSSIPLID.getText().toString().trim());

        txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        txtValid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                recApiCall();
            }
        });
        dialog.show();
    }

    private void typeDialog() {
        final Dialog dialog = new Dialog(ScrapEnterActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_select_type);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.setCancelable(false);

        ImageView imgClose =  dialog.findViewById(R.id.imgClose);
        TextView txtHeader =  dialog.findViewById(R.id.txtHeader);
        TextView txtRollBack =  dialog.findViewById(R.id.txtRollBack);
        TextView txtEndCut =  dialog.findViewById(R.id.txtEndCut);
        TextView txtAddPrime =  dialog.findViewById(R.id.txtAddPrime);
        TextView txtDeletePrime =  dialog.findViewById(R.id.txtDeletePrime);

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        txtRollBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.edtRollbackDrop.setText("Roll-Back");
                binding.lyoutWeightRollAndEnd.setVisibility(View.VISIBLE);
                binding.txtLoadRollback.setVisibility(View.VISIBLE);
                binding.lyoutAddPrime.setVisibility(View.GONE);
                binding.txtDeletePrimeGo.setVisibility(View.GONE);
                dialog.dismiss();
            }
        });

        txtEndCut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.edtRollbackDrop.setText("End-Cut");
                binding.lyoutWeightRollAndEnd.setVisibility(View.VISIBLE);
                binding.txtLoadRollback.setVisibility(View.VISIBLE);
                binding.lyoutAddPrime.setVisibility(View.GONE);
                binding.txtDeletePrimeGo.setVisibility(View.GONE);
                dialog.dismiss();
            }
        });

        txtAddPrime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.edtRollbackDrop.setText("Add-Prime");
                binding.lyoutWeightRollAndEnd.setVisibility(View.GONE);
                binding.txtLoadRollback.setVisibility(View.GONE);
                binding.lyoutAddPrime.setVisibility(View.VISIBLE);
                //Changed Vi = go
                binding.txtDeletePrimeGo.setVisibility(View.GONE);
                dialog.dismiss();
            }
        });

        txtDeletePrime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.edtRollbackDrop.setText("Delete-Prime");
                binding.lyoutWeightRollAndEnd.setVisibility(View.GONE);
                binding.txtLoadRollback.setVisibility(View.GONE);
                binding.lyoutAddPrime.setVisibility(View.GONE);
                binding.txtDeletePrimeGo.setVisibility(View.VISIBLE);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void recApiCall() {
        RollEndCut rollEndCut = new RollEndCut();
        rollEndCut.setWo_num(binding.edtRollbackWorkOrder.getText().toString().trim());
        rollEndCut.setType(binding.edtRollbackDrop.getText().toString().trim());
        rollEndCut.setWeight(binding.edtRollbackWeight.getText().toString().trim());
        rollEndCut.setSchedule_num(binding.txtScheduleNumber.getText().toString().trim());
        rollEndCut.setSsipl(binding.edtSSIPLID.getText().toString().trim());
        rollEndCut.setMethod("insert");

        Gson gson = new Gson();
        String input = gson.toJson(rollEndCut);
        System.out.println("Input ==> " + input);

        recPostApiCall(input);
    }

    private void recPostApiCall(String input) {
        CommonApiCalls.getInstance().recPostApiData(ScrapEnterActivity.this, input, new CommonCallback.Listener() {
            @Override
            public void onSuccess(Object body) {
                RoolbackEndcutApiResponse apiResponse = (RoolbackEndcutApiResponse) body;
                CommonFunctions.getInstance().successResponseToast(ScrapEnterActivity.this, apiResponse.getMsg());

                binding.edtRollbackWorkOrder.setText("");
                binding.edtRollbackDrop.setText("");
                binding.edtRollbackWeight.setText("");

                binding.txtScheduleNumber.setText("");
                binding.lyoutScheduleNumber.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(String reason) {
                CommonFunctions.getInstance().validationEmptyError(ScrapEnterActivity.this, reason);
            }
        });
    }

    private void trimmedScrapGet() {
        Trimmed_Scrap_Get trimmedScrapGet = new Trimmed_Scrap_Get();
        trimmedScrapGet.setMethod("get");
        trimmedScrapGet.setWo_num(binding.edtWorkOrderTrimmed.getText().toString().trim());

        Gson gson = new Gson();
        String input = gson.toJson(trimmedScrapGet);
        System.out.println("Input ==> " + input);

        trimmedApiCall(input);

    }

    private void trimmedApiCall(String input) {
        CommonApiCalls.getInstance().timmedScrapGetData(ScrapEnterActivity.this, input, new CommonCallback.Listener() {
            @Override
            public void onSuccess(Object body) {
                TrimmedScrapApiResponse apiResponse = (TrimmedScrapApiResponse) body;
                CommonFunctions.getInstance().successResponseToast(ScrapEnterActivity.this, apiResponse.getMsg());

                if (apiResponse.getResult() != null){
                    if (apiResponse.getResult().size() > 0){
                        inflateView(apiResponse.getResult());
                        binding.txtInflateLoad.setVisibility(View.VISIBLE);
                    }
                    else {
                        binding.txtInflateLoad.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(String reason) {
                CommonFunctions.getInstance().validationEmptyError(ScrapEnterActivity.this, reason);
            }
        });
    }

    private void inflateView(List<TrimmedScrapApiResponse.Result> data) {
        binding.lyoutInflateData.removeAllViews();
        for (int count = 0; count < data.size(); count++) {
            LinearLayout newView = (LinearLayout) (ScrapEnterActivity.this).getLayoutInflater().inflate(R.layout.adapter_trimmed_scrap, null);

            EditText edtOpBatch = newView.findViewById(R.id.edtOpBatch);
            EditText edtWeight = newView.findViewById(R.id.edtWeight);

            edtOpBatch.setText(data.get(count).getData2());

            // Add View
            binding.lyoutInflateData.addView(newView);
        }
    }

    private void workOrderApiCall(String input){
        CommonApiCalls.getInstance().rollbackEndCutWorkOrder(ScrapEnterActivity.this, input, new CommonCallback.Listener() {
            @Override
            public void onSuccess(Object body) {
                RollbackEndCutWorkOrder apiResponse = (RollbackEndCutWorkOrder) body;
                CommonFunctions.getInstance().successResponseToast(ScrapEnterActivity.this, apiResponse.getMsg());

                if (apiResponse.getSchedule_num() != null) {
                    binding.lyoutScheduleNumber.setVisibility(View.VISIBLE);
                    binding.txtScheduleNumber.setText(apiResponse.getSchedule_num());
                }
                else {
                    binding.lyoutScheduleNumber.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(String reason) {
                CommonFunctions.getInstance().validationEmptyError(ScrapEnterActivity.this, reason);
            }
        });
    }


    private void deleteApiCall(String input){
        CommonApiCalls.getInstance().deletePrimeGet(ScrapEnterActivity.this, input, new CommonCallback.Listener() {
            @Override
            public void onSuccess(Object body) {
                DeletePrimeGetApiResponse apiResponse = (DeletePrimeGetApiResponse) body;
                CommonFunctions.getInstance().successResponseToast(ScrapEnterActivity.this, apiResponse.getMsg());

                if (apiResponse.getResult() != null) {
                    binding.lyoutDeletePrime.setVisibility(View.VISIBLE);
                    loadDeletePrimeList(apiResponse.getResult());
                }
                else {
                    binding.lyoutDeletePrime.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(String reason) {
                binding.lyoutDeletePrime.setVisibility(View.GONE);
                CommonFunctions.getInstance().validationEmptyError(ScrapEnterActivity.this, reason);
            }
        });
    }

    private void loadDeletePrimeList(List<DeletePrimeGetApiResponse.Result> data) {
        if (data.size() > 0) {
            binding.rcDeletePrime.setVisibility(View.VISIBLE);

            LinearLayoutManager layoutManager = new LinearLayoutManager(ScrapEnterActivity.this, LinearLayoutManager.VERTICAL, false);
            binding.rcDeletePrime.setLayoutManager(layoutManager);

            adapter = new DeletePrimeListAdapter(ScrapEnterActivity.this, data,binding.edtWorkOrderTrimmed.getText().toString().trim());
            binding.rcDeletePrime.setAdapter(adapter);
        }
        else {
            binding.rcDeletePrime.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!WorkOrder_TRIMMED_SCRAP.isEmpty()){
            if (selectionTab.equals("trimmed")) {
                binding.edtWorkOrderTrimmed.setText(WorkOrder_TRIMMED_SCRAP);
            }
            else if (selectionTab.equals("roll")){
                binding.edtRollbackWorkOrder.setText(WorkOrder_TRIMMED_SCRAP);
            }
            WorkOrder_TRIMMED_SCRAP = "";
        }
        if (!ssiplID.isEmpty()) {
            binding.edtSSIPLID.setText(ssiplID);
            ssiplID = "";
        }

    }
}
