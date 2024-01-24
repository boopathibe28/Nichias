package com.nichias.activity;

import static com.nichias.utils.CommonFunctions.BAR_CODE_VALUE_FG;
import static com.nichias.utils.CommonFunctions.WorkOrder_TRIMMED_SCRAP;
import static com.nichias.utils.CommonFunctions.coilNo_FG;
import static com.nichias.utils.CommonFunctions.ssiplID;
import static com.nichias.utils.CommonFunctions.ssiplID_FG;
import static com.nichias.utils.CommonFunctions.thick_FG;
import static com.nichias.utils.CommonFunctions.width_FG;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.nichias.R;
import com.nichias.api.CommonApiCalls;
import com.nichias.app_interfaces.CommonCallback;
import com.nichias.databinding.ActivityHrslittingBinding;
import com.nichias.dummy_model.FGCheckWeightJson;
import com.nichias.dummy_model.FGDataCheckApiResponse;
import com.nichias.dummy_model.FGJson;
import com.nichias.dummy_model.HRSSlittingJson;
import com.nichias.model_api.FGDataApiResponse;
import com.nichias.model_api.HrsSlittingApiResponse;
import com.nichias.utils.CommonFunctions;

import java.text.DecimalFormat;

import hari.bounceview.BounceView;

public class HRSlitting extends AppCompatActivity implements View.OnClickListener {
    ActivityHrslittingBinding binding;
    private IntentIntegrator qrScan;
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    String selectionTab = "";
    String selectionScan = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_hrslitting);

        qrScan = new IntentIntegrator(HRSlitting.this);
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }

        initialView();

    }

    private void initialView() {
        BounceView.addAnimTo(binding.imgBack);
        BounceView.addAnimTo(binding.ImgScanSSIPL);
        BounceView.addAnimTo(binding.ImgScanWorkOrderTrimmed);
        BounceView.addAnimTo(binding.txtOk);
        BounceView.addAnimTo(binding.txtNotOk);
        BounceView.addAnimTo(binding.txtRescan);

        BounceView.addAnimTo(binding.rlyoutColiInward);
        BounceView.addAnimTo(binding.rlyoutFGDetails);
        BounceView.addAnimTo(binding.txtOkFG);
        BounceView.addAnimTo(binding.rlyoutScrapEnter);
        BounceView.addAnimTo(binding.imgScannFG);

        binding.imgBack.setOnClickListener(this);
        binding.ImgScanSSIPL.setOnClickListener(this);
        binding.ImgScanWorkOrderTrimmed.setOnClickListener(this);
        binding.imgScannFG.setOnClickListener(this);

        binding.txtOk.setOnClickListener(this);
        binding.txtNotOk.setOnClickListener(this);
        binding.txtRescan.setOnClickListener(this);

        binding.rlyoutColiInward.setOnClickListener(this);
        binding.rlyoutFGDetails.setOnClickListener(this);
        binding.txtOkFG.setOnClickListener(this);
        binding.rlyoutScrapEnter.setOnClickListener(this);

        binding.tvTitle.setText("HR Slitting");
        binding.edtMachineName.setText("HRS");

        // Initial
        selectionTab = "coil";
        binding.viewCoilInward.setVisibility(View.VISIBLE);
        binding.viewFGDetails.setVisibility(View.GONE);
        binding.viewScrapEnter.setVisibility(View.GONE);
        binding.imgScannFG.setVisibility(View.GONE);

        binding.lyoutCoilInward.setVisibility(View.VISIBLE);
        binding.lyoutFGDetails.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        if (view == binding.imgBack){
            finish();
        }
        else if (view == binding.imgScannFG){
            Bundle bundle = new Bundle();
            bundle.putString("from", "7");
            CommonFunctions.getInstance().newIntent(HRSlitting.this, ScannedBarcodeActivity.class, bundle, false, false);
        }
        else if (view == binding.ImgScanSSIPL){
            selectionScan = "ssipl";
            Bundle bundle = new Bundle();
            bundle.putString("from", "11");
            CommonFunctions.getInstance().newIntent(HRSlitting.this, ScannedBarcodeActivity.class, bundle, false, false);
        }
        else if (view == binding.ImgScanWorkOrderTrimmed){
            selectionScan = "work";
            Bundle bundle = new Bundle();
            bundle.putString("from", "8");
            CommonFunctions.getInstance().newIntent(HRSlitting.this, ScannedBarcodeActivity.class, bundle, false, false);
        }
        else if (view == binding.txtNotOk){
            /*binding.edtSSIPLID.setText("");
            binding.edtMachineName.setText("");
            binding.edtMachineID.setText("");
            binding.edtUpdate.setText("");*/
        }
        else if (view == binding.rlyoutColiInward){
            selectionTab = "coil";
            binding.viewCoilInward.setVisibility(View.VISIBLE);
            binding.viewFGDetails.setVisibility(View.GONE);
            binding.viewScrapEnter.setVisibility(View.GONE);

            binding.lyoutCoilInward.setVisibility(View.VISIBLE);
            binding.lyoutFGDetails.setVisibility(View.GONE);
            binding.imgScannFG.setVisibility(View.GONE);
        }
        else if (view == binding.rlyoutFGDetails){
            selectionTab = "fg";
            binding.viewCoilInward.setVisibility(View.GONE);
            binding.viewFGDetails.setVisibility(View.VISIBLE);
            binding.viewScrapEnter.setVisibility(View.GONE);

            binding.lyoutCoilInward.setVisibility(View.GONE);
            binding.lyoutFGDetails.setVisibility(View.VISIBLE);
            binding.imgScannFG.setVisibility(View.VISIBLE);
        }
        else if (view == binding.txtOk){
            if (binding.edtSSIPLID.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationError(HRSlitting.this,"SSIPL ID is Empty");
            }
            else if (binding.edtWorkOrderTrimmed.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationError(HRSlitting.this,"Work Order is Empty");
            }
            else {
                checkJobProcess();
            }
        }
        else if (view == binding.txtOkFG){
            if (binding.edtCoilNoFG.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationError(HRSlitting.this,"Mother Coil ID is Empty");
            }
            else if (binding.edtSSIPLIDFG.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationError(HRSlitting.this,"SSIPL ID is Empty");
            }
            else if (binding.edtThickFG.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationError(HRSlitting.this,"Thick is Empty");
            }
            else if (binding.edtWidthFG.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationError(HRSlitting.this,"Width is Empty");
            }
            /*else if (binding.edtLengthFG.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationError(HRSlitting.this,"Length is Empty");
            }*/
            else if (binding.edtWeightFG.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationError(HRSlitting.this,"Weight is Empty");
            }
            else {
                verificationDialog();
            }
        }
        else if (view == binding.rlyoutScrapEnter){
            CommonFunctions.getInstance().newIntent(HRSlitting.this, ScrapEnterActivity.class, Bundle.EMPTY, false, false);
        }
    }

    private void verificationDialog() {
        final Dialog dialog = new Dialog(HRSlitting.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_verification_fg);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.setCancelable(true);

        TextView txtThick =  dialog.findViewById(R.id.txtThick);
        TextView txtWidth =  dialog.findViewById(R.id.txtWidth);
        TextView txtLenght =  dialog.findViewById(R.id.txtLenght);
        txtLenght.setVisibility(View.GONE);
        TextView txtWeight =  dialog.findViewById(R.id.txtWeight);
        TextView txtValid =  dialog.findViewById(R.id.txtValid);
        TextView txtCancel =  dialog.findViewById(R.id.txtCancel);

        txtThick.setText("Thick : "+binding.edtThickFG.getText().toString().trim());
        txtWidth.setText("Width : "+binding.edtWidthFG.getText().toString().trim());
       // txtLenght.setText("Length : "+binding.edtLengthFG.getText().toString().trim());
        txtWeight.setText("Weight : "+binding.edtWeightFG.getText().toString().trim());

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
                fgJsonProcess();
            }
        });
        dialog.show();
    }


    private void fgJsonProcess() {
        FGJson fgJson = new FGJson();
        fgJson.setSSIPL(binding.edtSSIPLIDFG.getText().toString().trim());
        fgJson.setThick(binding.edtThickFG.getText().toString().trim());
        fgJson.setWidth(binding.edtWidthFG.getText().toString().trim());
       // fgJson.setLength(binding.edtLengthFG.getText().toString().trim());
        fgJson.setWeight(binding.edtWeightFG.getText().toString().trim());
        fgJson.setMethod("update");

        Gson gson = new Gson();
        String input = gson.toJson(fgJson);
        System.out.println("Input ==> " + input);

        fgApiCall(input);
    }

    private void fgApiCall(String input) {
        CommonApiCalls.getInstance().fgData(HRSlitting.this, input, new CommonCallback.Listener() {
            @Override
            public void onSuccess(Object body) {
                FGDataApiResponse apiResponse = (FGDataApiResponse) body;
                if (apiResponse.getStatus().toLowerCase().equals("success")){
                    CommonFunctions.getInstance().successResponseToast(HRSlitting.this, apiResponse.getMsg());

                    binding.edtSSIPLIDFG.setText("");
                    binding.edtCoilNoFG.setText("");
                    binding.edtBarcodeFG.setText("");
                    binding.edtThickFG.setText("");
                    binding.edtWidthFG.setText("");
                   // binding.edtLengthFG.setText("");
                    binding.edtWeightFG.setText("");
                    binding.lyoutFgWeight.setVisibility(View.GONE);
                }
                else {
                    binding.lyoutFgWeight.setVisibility(View.GONE);

                    binding.txtErrorToast.setVisibility(View.VISIBLE);
                    binding.txtErrorToast.setText(apiResponse.getMsg());
                    Animation anim = new AlphaAnimation(0.0f, 1.0f);
                    anim.setDuration(50); //You can manage the blinking time with this parameter
                    anim.setStartOffset(20);
                    anim.setRepeatMode(Animation.REVERSE);
                    anim.setRepeatCount(Animation.INFINITE);
                    binding.txtErrorToast.startAnimation(anim);

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            binding.txtErrorToast.setVisibility(View.GONE);
                        }
                    }, 3000);
                }

            }

            @Override
            public void onFailure(String reason) {
                binding.lyoutFgWeight.setVisibility(View.GONE);
                CommonFunctions.getInstance().validationEmptyError(HRSlitting.this, reason);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (selectionTab.equals("coil")) {
            if (selectionScan.equals("ssipl")) {
                if (!ssiplID.isEmpty()) {
                    binding.edtSSIPLID.setText(ssiplID);
                    ssiplID = "";

                   // checkJobProcess();
                }
            }
            else if (selectionScan.equals("work")){
                if (!WorkOrder_TRIMMED_SCRAP.isEmpty()) {
                    binding.edtWorkOrderTrimmed.setText(WorkOrder_TRIMMED_SCRAP);
                    WorkOrder_TRIMMED_SCRAP = "";

                   // checkJobProcess();
                }
            }
        }
        else if (selectionTab.equals("fg")){
            if (!ssiplID_FG.isEmpty()) {
                binding.edtSSIPLIDFG.setText(ssiplID_FG);
                ssiplID_FG = "";

                binding.edtCoilNoFG.setText(coilNo_FG);
                coilNo_FG = "";

                binding.edtBarcodeFG.setText(BAR_CODE_VALUE_FG);
                BAR_CODE_VALUE_FG = "";

                binding.edtThickFG.setText(thick_FG);
                thick_FG = "";

                binding.edtWidthFG.setText(width_FG);
                width_FG = "";

                fgCheckWeightJsonProcess();
            }
        }
    }


    private void fgCheckWeightJsonProcess() {
        FGCheckWeightJson fgJson = new FGCheckWeightJson();
        fgJson.setSSIPL(binding.edtSSIPLIDFG.getText().toString().trim());
        fgJson.setMethod("check");

        Gson gson = new Gson();
        String input = gson.toJson(fgJson);
        System.out.println("Input ==> " + input);

        fgWeightApiCall(input);
    }

    private void fgWeightApiCall(String input) {
        CommonApiCalls.getInstance().fgDataCheck(HRSlitting.this, input, new CommonCallback.Listener() {
            @Override
            public void onSuccess(Object body) {
                FGDataCheckApiResponse apiResponse = (FGDataCheckApiResponse) body;
                if (apiResponse.getResult().equals("1")) {
                    binding.lyoutFgWeight.setVisibility(View.VISIBLE);
                    CommonFunctions.getInstance().successResponseToast(HRSlitting.this, apiResponse.getMsg());

                    binding.edtWeightFG.setFocusable(true);
                    binding.edtWeightFG.setFocusableInTouchMode(true);
                    binding.edtWeightFG.setClickable(true);
                }
                else if (apiResponse.getResult().equals("0")) {
                    binding.lyoutFgWeight.setVisibility(View.VISIBLE);
                    CommonFunctions.getInstance().successResponseToast(HRSlitting.this, apiResponse.getMsg());

                    Double valueWeight = 0.0;
                    if (apiResponse.getWeight() != null && !apiResponse.getWeight().isEmpty()){
                        valueWeight = Double.valueOf(apiResponse.getWeight());
                    }
                    binding.edtWeightFG.setText(new DecimalFormat("##.###").format(valueWeight));
                    binding.edtWeightFG.setFocusable(false);
                    binding.edtWeightFG.setFocusableInTouchMode(false);
                    binding.edtWeightFG.setClickable(false);
                }
                else {
                    binding.lyoutFgWeight.setVisibility(View.GONE);
                    CommonFunctions.getInstance().validationError(HRSlitting.this, apiResponse.getMsg());
                }
            }

            @Override
            public void onFailure(String reason) {
                CommonFunctions.getInstance().validationEmptyError(HRSlitting.this, reason);
            }
        });
    }




    private void checkJobProcess() {
        HRSSlittingJson hrsSlittingJson = new HRSSlittingJson();
        hrsSlittingJson.setMachine("HRS");
        hrsSlittingJson.setSSIPL(binding.edtSSIPLID.getText().toString().trim());
        hrsSlittingJson.setWo_Num(binding.edtWorkOrderTrimmed.getText().toString().trim());

        Gson gson = new Gson();
        String input = gson.toJson(hrsSlittingJson);
        System.out.println("Input ==> " + input);

        apiCall(input);

    }

    private void apiCall(String input) {
        CommonApiCalls.getInstance().hrsSlitting(HRSlitting.this, input, new CommonCallback.Listener() {
            @Override
            public void onSuccess(Object body) {
                HrsSlittingApiResponse apiResponse = (HrsSlittingApiResponse) body;
                if (apiResponse.getResult().equals("1")) {
                    //binding.txtOk.setVisibility(View.VISIBLE);
                    binding.txtNotOk.setVisibility(View.GONE);
                    binding.edtUpdate.setText(apiResponse.getMachine());
                    CommonFunctions.getInstance().successResponseToast(HRSlitting.this, apiResponse.getMsg());
                }
                else {
                    //binding.txtOk.setVisibility(View.GONE);
                    binding.txtNotOk.setVisibility(View.VISIBLE);
                    binding.edtUpdate.setText(apiResponse.getMachine());
                    CommonFunctions.getInstance().validationError(HRSlitting.this, apiResponse.getMsg());
                }
            }

            @Override
            public void onFailure(String reason) {
                CommonFunctions.getInstance().validationEmptyError(HRSlitting.this, reason);
            }
        });

    }

}
