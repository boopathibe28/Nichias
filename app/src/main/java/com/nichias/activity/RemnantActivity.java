package com.nichias.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.nichias.R;
import com.nichias.api.CommonApiCalls;
import com.nichias.app_interfaces.CommonCallback;
import com.nichias.databinding.ActivityRemnantBinding;
import com.nichias.dummy_model.PokayokeCheck;
import com.nichias.dummy_model.RemnantFGINJson;
import com.nichias.dummy_model.RemnantFGOUTJson;
import com.nichias.dummy_model.RemnantInspectionINJson;
import com.nichias.model_api.InFGResponse;
import com.nichias.model_api.OutFGResponse;
import com.nichias.model_api.PokayokeCheckApiResponse;
import com.nichias.utils.CommonFunctions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import hari.bounceview.BounceView;

public class RemnantActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityRemnantBinding binding;
    private IntentIntegrator qrScan;
    private static final int REQUEST_CODE_QR_SCAN = 101;
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    String selectedTab = "";
    String selectedIN_View = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_remnant);

        qrScan = new IntentIntegrator(RemnantActivity.this);
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }

        initialView();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        //IN
        binding.edtINInspectionDate.setText(currentDateandTime);
        binding.edtINFGDate.setText(currentDateandTime);
        //OUT
        binding.edtOUTInspectionDate.setText(currentDateandTime);
        binding.edtOUTFGDate.setText(currentDateandTime);

        LoadCurrentTime();
    }

    void LoadCurrentTime() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault());
                String currentDateandTime = sdf.format(new Date());
                //IN
                binding.edtINInspectionDate.setText(currentDateandTime);
                binding.edtINFGDate.setText(currentDateandTime);
                //OUT
                binding.edtOUTInspectionDate.setText(currentDateandTime);
                binding.edtOUTFGDate.setText(currentDateandTime);

                LoadCurrentTime();
            }
        }, 60000);
    }


    private void initialView() {
        BounceView.addAnimTo(binding.imgBack);
        BounceView.addAnimTo(binding.rlyoutRemnantIN);
        BounceView.addAnimTo(binding.rlyoutRemnantOUT);

        BounceView.addAnimTo(binding.lyoutRemnantDropDown);
        BounceView.addAnimTo(binding.edtRemnantDropDownIN);
        BounceView.addAnimTo(binding.imgScannerRemnantIN);
        BounceView.addAnimTo(binding.txtSaveINInspection);
        BounceView.addAnimTo(binding.txtSaveINFG);

        binding.imgBack.setOnClickListener(this);
        binding.rlyoutRemnantIN.setOnClickListener(this);
        binding.rlyoutRemnantOUT.setOnClickListener(this);

        binding.lyoutRemnantDropDown.setOnClickListener(this);
        binding.edtRemnantDropDownIN.setOnClickListener(this);
        binding.imgScannerRemnantIN.setOnClickListener(this);
        binding.txtSaveINInspection.setOnClickListener(this);
        binding.txtSaveINFG.setOnClickListener(this);

        binding.viewRemnantIN.setVisibility(View.VISIBLE);
        binding.lyoutIN.setVisibility(View.VISIBLE);
        binding.lyoutOUT.setVisibility(View.GONE);
        binding.viewRemnantOUT.setVisibility(View.GONE);
        selectedTab = "in";


        BounceView.addAnimTo(binding.lyoutRemnantDropDownOUT);
        BounceView.addAnimTo(binding.edtRemnantDropDownOUT);
        BounceView.addAnimTo(binding.imgScannerRemnantOUT);
        BounceView.addAnimTo(binding.txtSaveOUTInspection);
        BounceView.addAnimTo(binding.txtSaveOUTFG);

        binding.lyoutRemnantDropDownOUT.setOnClickListener(this);
        binding.edtRemnantDropDownOUT.setOnClickListener(this);
        binding.imgScannerRemnantOUT.setOnClickListener(this);
        binding.txtSaveOUTInspection.setOnClickListener(this);
        binding.txtSaveOUTFG.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == binding.imgBack){
            finish();
        }
        else if (view == binding.rlyoutRemnantIN){
            selectedTab = "in";
            binding.viewRemnantIN.setVisibility(View.VISIBLE);
            binding.lyoutIN.setVisibility(View.VISIBLE);
            binding.lyoutOUT.setVisibility(View.GONE);
            binding.viewRemnantOUT.setVisibility(View.GONE);
        }
        else if (view == binding.rlyoutRemnantOUT){
            selectedTab = "out";
            binding.viewRemnantIN.setVisibility(View.GONE);
            binding.lyoutIN.setVisibility(View.GONE);
            binding.lyoutOUT.setVisibility(View.VISIBLE);
            binding.viewRemnantOUT.setVisibility(View.VISIBLE);
        }
        else if (view == binding.lyoutRemnantDropDown || view == binding.edtRemnantDropDownIN){
            final Dialog dialog = new Dialog(RemnantActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_remnant_selection);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            dialog.setCancelable(false);

            ImageView imgClose =  dialog.findViewById(R.id.imgClose);
            TextView txtHeader =  dialog.findViewById(R.id.txtHeader);
            TextView txtInspection =  dialog.findViewById(R.id.txtInspection);
            TextView txtFG =  dialog.findViewById(R.id.txtFG);

            imgClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            txtInspection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedIN_View = "in_inspection";
                    binding.edtRemnantDropDownIN.setText("Inspection");
                    binding.lyoutINInspection.setVisibility(View.VISIBLE);
                    binding.lyoutINFG.setVisibility(View.GONE);
                    dialog.dismiss();
                }
            });

            txtFG.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedIN_View = "in_fg";
                    binding.edtRemnantDropDownIN.setText("FG");
                    binding.lyoutINInspection.setVisibility(View.GONE);
                    binding.lyoutINFG.setVisibility(View.VISIBLE);
                    dialog.dismiss();
                }
            });

            dialog.show();
        }
        else if (view == binding.imgScannerRemnantIN){

        }
        else if (view == binding.txtSaveINInspection){
            if (binding.edtINInspectionPartNo.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this,"Kindly Enter valid Part No");
            }
            else if (binding.edtINInspectionPartName.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this,"Kindly Enter valid Part Name");
            }
            else if (binding.edtINInspectionDate.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this,"Kindly Enter valid Date");
            }
            else if (binding.edtINInspectionShift.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this,"Kindly Enter valid Shift");
            }
            else if (binding.edtINInspectionLessPackingQty.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this,"Kindly Enter valid Less Packing Qty");
            }
            else if (binding.edtINInspectionLessPackingQtyLotNo.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this,"Kindly Enter valid Lot No");
            }
            else if (binding.edtINInspectionMiddlePartQty.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this,"Kindly Enter valid Middle Part Qty");
            }
            else if (binding.edtINInspectionMiddlePartQtyLotNo.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this,"Kindly Enter valid Lot No");
            }
            else if (binding.edtINInspectionAcceptableLimitPart.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this,"Kindly Enter valid Acceptable Limit Part");
            }
            else if (binding.edtINInspectionRejectionLotNo.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this,"Kindly Enter valid Rejection Lot No");
            }
            else if (binding.edtINInspectionLocation.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this,"Kindly Enter valid Location");
            }
            else if (binding.edtINInspectionIssuedBy.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this,"Kindly Enter valid Issued By");
            }
            else {
                // IN Inspection Save Data

                RemnantInspectionINJson apiJson = new RemnantInspectionINJson();
                apiJson.setMethod("Remnant-Inspection-IN");
                apiJson.setRemnant(binding.edtRemnantDropDownIN.getText().toString().trim());
                apiJson.setPartNo(binding.edtINInspectionPartNo.getText().toString().trim());
                apiJson.setPartName(binding.edtINInspectionPartName.getText().toString().trim());
                apiJson.setSerialCode("");
                apiJson.setDate(binding.edtINInspectionDate.getText().toString().trim());
                apiJson.setShift(binding.edtINInspectionShift.getText().toString().trim());
                apiJson.setLessPackingQty(binding.edtINInspectionLessPackingQty.getText().toString().trim());
                apiJson.setMiddlePartQty(binding.edtINInspectionMiddlePartQty.getText().toString().trim());
                apiJson.setAcceptableLimitPart(binding.edtINInspectionAcceptableLimitPart.getText().toString().trim());
                apiJson.setLocation(binding.edtINInspectionLocation.getText().toString().trim());
                apiJson.setLotNo1(binding.edtINInspectionLessPackingQtyLotNo.getText().toString().trim());
                apiJson.setLotNo2(binding.edtINInspectionMiddlePartQtyLotNo.getText().toString().trim());
                apiJson.setRejectionLotNo(binding.edtINInspectionRejectionLotNo.getText().toString().trim());
                apiJson.setIssuedBy(binding.edtINInspectionIssuedBy.getText().toString().trim());

                Gson gson = new Gson();
                String input = gson.toJson(apiJson);
                System.out.println("Input ==> " + input);

                CommonApiCalls.getInstance().RemnantInspection_IN(RemnantActivity.this, input, new CommonCallback.Listener() {
                    @Override
                    public void onSuccess(Object body) {
                        PokayokeCheckApiResponse apiResponse = (PokayokeCheckApiResponse) body;
                        CommonFunctions.getInstance().successResponseToast(RemnantActivity.this, apiResponse.getMsg());

                        binding.edtINInspectionPartNo.setText("");
                        binding.edtINInspectionPartName.setText("");
                        binding.edtINInspectionShift.setText("");
                        binding.edtINInspectionLessPackingQty.setText("");
                        binding.edtINInspectionLessPackingQtyLotNo.setText("");
                        binding.edtINInspectionMiddlePartQty.setText("");
                        binding.edtINInspectionMiddlePartQtyLotNo.setText("");
                        binding.edtINInspectionAcceptableLimitPart.setText("");
                        binding.edtINInspectionRejectionLotNo.setText("");
                        binding.edtINInspectionLocation.setText("");
                        binding.edtINInspectionIssuedBy.setText("");
                    }

                    @Override
                    public void onFailure(String reason) {
                        CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this, reason);
                    }
                });

            }
        }
        else if (view == binding.txtSaveINFG){
            if (binding.edtINFGPartNo.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this,"Kindly Enter valid Part No");
            }
            else if (binding.edtINFGSerialCode.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this,"Kindly Enter valid Serial Code");
            }
            else if (binding.edtINFGDate.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this,"Kindly Enter valid Date");
            }
            else if (binding.edtINFGShift.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this,"Kindly Enter valid Shift");
            }
            else if (binding.edtINFGQty.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this,"Kindly Enter valid Qty");
            }
            else if (binding.edtINFGLotNo.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this,"Kindly Enter valid Lot No");
            }
            else if (binding.edtINFGIssuedBy.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this,"Kindly Enter valid Issued By");
            }
            else if (binding.edtINFGLocation.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this,"Kindly Enter valid Location");
            }
            else {
                // IN FG Save Data

                RemnantFGINJson apiJson = new RemnantFGINJson();
                apiJson.setMethod("Remnant-FG-IN");
                apiJson.setRemnant(binding.edtRemnantDropDownIN.getText().toString().trim());
                apiJson.setPartNo(binding.edtINFGPartNo.getText().toString().trim());
                apiJson.setSerialCode(binding.edtINFGSerialCode.getText().toString().trim());
                apiJson.setDate(binding.edtINFGDate.getText().toString().trim());
                apiJson.setShift(binding.edtINFGShift.getText().toString().trim());
                apiJson.setQty(binding.edtINFGQty.getText().toString().trim());
                apiJson.setLotNo(binding.edtINFGLotNo.getText().toString().trim());
                apiJson.setIssuedBy(binding.edtINFGLotNo.getText().toString().trim());
                apiJson.setLocation(binding.edtINFGLocation.getText().toString().trim());

                Gson gson = new Gson();
                String input = gson.toJson(apiJson);
                System.out.println("Input ==> " + input);

                CommonApiCalls.getInstance().RemnantInspection_FG(RemnantActivity.this, input, new CommonCallback.Listener() {
                    @Override
                    public void onSuccess(Object body) {
                        InFGResponse apiResponse = (InFGResponse) body;
                        CommonFunctions.getInstance().successResponseToast(RemnantActivity.this, apiResponse.getMsg());

                        binding.edtINFGPartNo.setText("");
                        binding.edtINFGSerialCode.setText("");
                        binding.edtINFGShift.setText("");
                        binding.edtINFGQty.setText("");
                        binding.edtINFGLotNo.setText("");
                        binding.edtINFGIssuedBy.setText("");
                        binding.edtINFGLocation.setText("");
                    }

                    @Override
                    public void onFailure(String reason) {
                        CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this, reason);
                    }
                });

            }
        }
        // OUT
        else if (view == binding.lyoutRemnantDropDownOUT || view == binding.edtRemnantDropDownOUT){
            final Dialog dialog = new Dialog(RemnantActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_remnant_selection);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            dialog.setCancelable(false);

            ImageView imgClose =  dialog.findViewById(R.id.imgClose);
            TextView txtHeader =  dialog.findViewById(R.id.txtHeader);
            TextView txtInspection =  dialog.findViewById(R.id.txtInspection);
            TextView txtFG =  dialog.findViewById(R.id.txtFG);

            imgClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            txtInspection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedIN_View = "out_inspection";
                    binding.edtRemnantDropDownOUT.setText("Inspection");
                    binding.lyoutOUTInspection.setVisibility(View.VISIBLE);
                    binding.lyoutOUTFG.setVisibility(View.GONE);
                    dialog.dismiss();
                }
            });

            txtFG.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedIN_View = "out_fg";
                    binding.edtRemnantDropDownOUT.setText("FG");
                    binding.lyoutOUTInspection.setVisibility(View.GONE);
                    binding.lyoutOUTFG.setVisibility(View.VISIBLE);
                    dialog.dismiss();
                }
            });

            dialog.show();
        }
        else if (view == binding.imgScannerRemnantOUT){

        }
        else if (view == binding.txtSaveOUTInspection){
            if (binding.edtOUTInspectionPartNo.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this,"Kindly Enter valid Part No");
            }
            else if (binding.edtOUTInspectionSerialCode.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this,"Kindly Enter valid Serial Code");
            }
            else if (binding.edtOUTInspectionDate.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this,"Kindly Enter valid Date");
            }
            else if (binding.edtOUTInspectionShift.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this,"Kindly Enter valid Shift");
            }
            else if (binding.edtOUTInspectionQty.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this,"Kindly Enter valid Qty");
            }
            else if (binding.edtOUTInspectionLotNo.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this,"Kindly Enter valid Lot No");
            }
            else if (binding.edtOUTInspectionReceiverName.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this,"Kindly Enter valid Receiver Name");
            }
            else if (binding.edtOUTInspectionSupervisorName.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this,"Kindly Enter valid Supervisor Name");
            }
            else {
                // OUT Inspection Save Data
            }
        }
        else if (view == binding.txtSaveOUTFG){
            if (binding.edtOUTFGPartNo.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this,"Kindly Enter valid Part No");
            }
            else if (binding.edtOUTFGSerialCode.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this,"Kindly Enter valid Serial Code");
            }
            else if (binding.edtOUTFGPartName.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this,"Kindly Enter valid Part Name");
            }
            else if (binding.edtOUTFGBinQty.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this,"Kindly Enter valid Bin Qty");
            }
            else if (binding.edtOUTFGDate.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this,"Kindly Enter valid Date");
            }
            else if (binding.edtOUTFGShift.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this,"Kindly Enter valid Shift");
            }
            else if (binding.edtOUTFGRemnantQty.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this,"Kindly Enter valid Remnant Qty");
            }
            else if (binding.edtOUTFGLotNo.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this,"Kindly Enter valid Lot No");
            }
            else if (binding.edtOUTFGIssuedBY.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this,"Kindly Enter valid Issued By");
            }
            else if (binding.edtOUTFGCheckedBy.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this,"Kindly Enter valid Checked By");
            }
            else if (binding.edtOUTFGVerifiedBy.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this,"Kindly Enter valid Verified By");
            }
            else {
                // OUT FG Save Data

                RemnantFGOUTJson apiJson = new RemnantFGOUTJson();
                apiJson.setMethod("Remnant-FG-OUT");
                apiJson.setRemnant(binding.edtRemnantDropDownOUT.getText().toString().trim());
                apiJson.setPartNo(binding.edtOUTFGPartNo.getText().toString().trim());
                apiJson.setSerialCode(binding.edtOUTFGSerialCode.getText().toString().trim());
                apiJson.setPartName(binding.edtOUTFGPartName.getText().toString().trim());
                apiJson.setBinQty(binding.edtOUTFGBinQty.getText().toString().trim());
                apiJson.setDate(binding.edtOUTFGDate.getText().toString().trim());
                apiJson.setShift(binding.edtOUTFGShift.getText().toString().trim());
                apiJson.setRemnantQty(binding.edtOUTFGRemnantQty.getText().toString().trim());
                apiJson.setLotNo(binding.edtOUTFGLotNo.getText().toString().trim());
                apiJson.setIssuedBy(binding.edtOUTFGIssuedBY.getText().toString().trim());
                apiJson.setCheckedBy(binding.edtOUTFGCheckedBy.getText().toString().trim());
                apiJson.setVerifiededBy(binding.edtOUTFGVerifiedBy.getText().toString().trim());

                Gson gson = new Gson();
                String input = gson.toJson(apiJson);
                System.out.println("Input ==> " + input);

                CommonApiCalls.getInstance().Remnant_FG_out(RemnantActivity.this, input, new CommonCallback.Listener() {
                    @Override
                    public void onSuccess(Object body) {
                        OutFGResponse apiResponse = (OutFGResponse) body;
                        CommonFunctions.getInstance().successResponseToast(RemnantActivity.this, apiResponse.getMsg());

                        binding.edtOUTFGPartNo.setText("");
                        binding.edtOUTFGSerialCode.setText("");
                        binding.edtOUTFGPartName.setText("");
                        binding.edtOUTFGBinQty.setText("");
                        binding.edtOUTFGShift.setText("");
                        binding.edtOUTFGRemnantQty.setText("");
                        binding.edtOUTFGLotNo.setText("");
                        binding.edtOUTFGIssuedBY.setText("");
                        binding.edtOUTFGCheckedBy.setText("");
                        binding.edtOUTFGVerifiedBy.setText("");

                    }

                    @Override
                    public void onFailure(String reason) {
                        CommonFunctions.getInstance().validationEmptyError(RemnantActivity.this, reason);
                    }
                });

            }
        }
    }
}
