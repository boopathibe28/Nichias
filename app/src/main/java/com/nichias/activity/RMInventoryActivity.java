package com.nichias.activity;

import static com.nichias.utils.CommonFunctions.DATA_TIME;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.nichias.R;
import com.nichias.adapter.GRNListAdapter;
import com.nichias.adapter.RMInventoryAddListAdapter;
import com.nichias.api.CommonApiCalls;
import com.nichias.app_interfaces.CommonCallback;
import com.nichias.databinding.ActivityRminventoryBinding;
import com.nichias.dummy_model.GRNProcess2Json;
import com.nichias.dummy_model.GRNProcessGETJson;
import com.nichias.dummy_model.InwardArrayResponse;
import com.nichias.dummy_model.OperatorSwap;
import com.nichias.dummy_model.PrintRmGateJson;
import com.nichias.dummy_model.RMInwardAPIJson;
import com.nichias.interfaces.ListRemove;
import com.nichias.model_api.GRN2ListApiResponse;
import com.nichias.model_api.GRNListApiResponse;
import com.nichias.model_api.OperatorSwapApiResponse;
import com.nichias.model_api.PrintRMGateInventoryApiResponse;
import com.nichias.model_api.RMInwardApiResponse;
import com.nichias.utils.CommonFunctions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import hari.bounceview.BounceView;

public class RMInventoryActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityRminventoryBinding binding;
    private IntentIntegrator qrScan;
    private static final int REQUEST_CODE_QR_SCAN = 101;
    private static final int MY_CAMERA_REQUEST_CODE = 100;
    String selectedTab = "";
    List<InwardArrayResponse.InwardArray> inwardArrayResponses;
    private List<GRNListApiResponse.InwardArray> GRN_inward_Array;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rminventory);

        qrScan = new IntentIntegrator(RMInventoryActivity.this);
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }

        initialView();

        inwardArrayResponses = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        binding.edtInwardDateRMIN.setText(currentDateandTime);
        LoadCurrentTime();
    }

    void LoadCurrentTime() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault());
                String currentDateandTime = sdf.format(new Date());
                binding.edtInwardDateRMIN.setText(currentDateandTime);
                LoadCurrentTime();
            }
        }, 60000);
    }

    private void initialView() {
        BounceView.addAnimTo(binding.imgBack);
        BounceView.addAnimTo(binding.rlyoutRMGateInward);
        BounceView.addAnimTo(binding.rlyoutRMInward);
        BounceView.addAnimTo(binding.rlyoutGRNProcess);
        BounceView.addAnimTo(binding.txtRmGateInwardPrint);
        BounceView.addAnimTo(binding.imgScannerQRCodeRMIN);
        BounceView.addAnimTo(binding.txtAddDataRMIN);
        BounceView.addAnimTo(binding.txtAddMoreRMIN);
        BounceView.addAnimTo(binding.txtSaveGRN);
        BounceView.addAnimTo(binding.edtInspectionDateGRN);

        BounceView.addAnimTo(binding.edtQRCodeGRN);
        BounceView.addAnimTo(binding.imgScannerQRCodeGRN);
        BounceView.addAnimTo(binding.txtSaveRMIN);
        BounceView.addAnimTo(binding.edtQAStatusGRN);


        binding.edtQRCodeGRN.setOnClickListener(this);
        binding.imgScannerQRCodeGRN.setOnClickListener(this);

        binding.imgBack.setOnClickListener(this);
        binding.imgScannerQRCodeRMIN.setOnClickListener(this);
        binding.edtQRCodeRMIN.setOnClickListener(this);
        binding.edtReceivedDateRMIN.setOnClickListener(this);
        binding.imgScannerQRCodeReceivedDateRMIN.setOnClickListener(this);
        binding.edtInspectionDateGRN.setOnClickListener(this);

        binding.rlyoutRMGateInward.setOnClickListener(this);
        binding.rlyoutRMInward.setOnClickListener(this);
        binding.rlyoutGRNProcess.setOnClickListener(this);
        binding.txtRmGateInwardPrint.setOnClickListener(this);
        binding.txtAddDataRMIN.setOnClickListener(this);
        binding.txtAddMoreRMIN.setOnClickListener(this);
        binding.txtSaveGRN.setOnClickListener(this);
        binding.txtSaveRMIN.setOnClickListener(this);
        binding.edtQAStatusGRN.setOnClickListener(this);

        binding.tvTitle.setText("RM Inventory");

        selectedTab = "RmGateInward";
        binding.lyoutRmGateInwardView.setVisibility(View.VISIBLE);
        binding.viewRMGateInward.setVisibility(View.VISIBLE);

        binding.lyoutRmInwardView.setVisibility(View.GONE);
        binding.viewRMInward.setVisibility(View.GONE);

        binding.lyoutGRNProcessView.setVisibility(View.GONE);
        binding.viewGRNProcess.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        if (view == binding.imgBack){
            finish();
        }
        else if (view == binding.rlyoutRMGateInward) {
            selectedTab = "RmGateInward";
            binding.lyoutRmGateInwardView.setVisibility(View.VISIBLE);
            binding.viewRMGateInward.setVisibility(View.VISIBLE);

            binding.lyoutRmInwardView.setVisibility(View.GONE);
            binding.viewRMInward.setVisibility(View.GONE);

            binding.lyoutGRNProcessView.setVisibility(View.GONE);
            binding.viewGRNProcess.setVisibility(View.GONE);
        }
        else if (view == binding.rlyoutRMInward) {
            selectedTab = "RmInward";
            binding.lyoutRmGateInwardView.setVisibility(View.GONE);
            binding.viewRMGateInward.setVisibility(View.GONE);

            binding.lyoutRmInwardView.setVisibility(View.VISIBLE);
            binding.viewRMInward.setVisibility(View.VISIBLE);

            binding.lyoutGRNProcessView.setVisibility(View.GONE);
            binding.viewGRNProcess.setVisibility(View.GONE);
        }
        else if (view == binding.rlyoutGRNProcess) {
            selectedTab = "GRNProcess";
            binding.lyoutRmGateInwardView.setVisibility(View.GONE);
            binding.viewRMGateInward.setVisibility(View.GONE);

            binding.lyoutRmInwardView.setVisibility(View.GONE);
            binding.viewRMInward.setVisibility(View.GONE);

            binding.lyoutGRNProcessView.setVisibility(View.VISIBLE);
            binding.viewGRNProcess.setVisibility(View.VISIBLE);
        }
        else if (view == binding.txtRmGateInwardPrint) {
            PrintRmGateJson apiJson = new PrintRmGateJson();
            apiJson.setMethod("gate_inward");

            Gson gson = new Gson();
            String input = gson.toJson(apiJson);
            System.out.println("Input ==> " + input);

            CommonApiCalls.getInstance().printRmGateInward(RMInventoryActivity.this, input, new CommonCallback.Listener() {
                @Override
                public void onSuccess(Object body) {
                    PrintRMGateInventoryApiResponse apiResponse = (PrintRMGateInventoryApiResponse) body;
                    CommonFunctions.getInstance().successResponseToast(RMInventoryActivity.this, apiResponse.getMsg());
                }

                @Override
                public void onFailure(String reason) {
                    CommonFunctions.getInstance().validationEmptyError(RMInventoryActivity.this, reason);
                }
            });

        }
        else if (view == binding.edtQRCodeRMIN || view == binding.imgScannerQRCodeRMIN){
            Bundle bundle = new Bundle();
            bundle.putString("from", "rminventory");
          //  CommonFunctions.getInstance().newIntent(RMInventoryActivity.this, ScannedBarcodeActivity.class, bundle, false, false);
            CommonFunctions.getInstance().newIntent(RMInventoryActivity.this, ScanActivity.class, bundle, false, false);
        }
        else if (view == binding.edtQRCodeGRN || view == binding.imgScannerQRCodeGRN){
            Bundle bundle = new Bundle();
            bundle.putString("from", "rminventory");
          //  CommonFunctions.getInstance().newIntent(RMInventoryActivity.this, ScannedBarcodeActivity.class, bundle, false, false);
            CommonFunctions.getInstance().newIntent(RMInventoryActivity.this, ScanActivity.class, bundle, false, false);
        }
        else if (view == binding.edtReceivedDateRMIN || view == binding.imgScannerQRCodeReceivedDateRMIN){
            Bundle bundle = new Bundle();
            bundle.putString("from", "rminventoryDate");
            CommonFunctions.getInstance().newIntent(RMInventoryActivity.this, ScanActivity.class, bundle, false, false);
        }
        else if (view == binding.txtAddDataRMIN) {
            if (binding.edtCustomerRMIN.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RMInventoryActivity.this,"Kindly enter valid Customer");
            }
            else if (binding.edtPartNameRMIN.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RMInventoryActivity.this,"Kindly enter valid Part Name");
            }
            else if (binding.edtPartNoRMIN.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RMInventoryActivity.this,"Kindly enter valid Part No");
            }
            else if (binding.edtSerialCodeRMIN.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RMInventoryActivity.this,"Kindly enter valid Serial Code");
            }
            else {
                InwardArrayResponse.InwardArray array = new InwardArrayResponse.InwardArray();
                array.setCustomer(binding.edtCustomerRMIN.getText().toString().trim());
                array.setPartName(binding.edtPartNameRMIN.getText().toString().trim());
                array.setPartNo(binding.edtPartNoRMIN.getText().toString().trim());
                array.setSerialCode(binding.edtSerialCodeRMIN.getText().toString().trim());
                inwardArrayResponses.add(array);

                binding.edtCustomerRMIN.setText("");
                binding.edtPartNameRMIN.setText("");
                binding.edtPartNoRMIN.setText("");
                binding.edtSerialCodeRMIN.setText("");

                LoadRecyclerView();
            }
        }
        else if (view == binding.txtAddMoreRMIN){
            int pos = inwardArrayResponses.size() - 1;
            InwardArrayResponse.InwardArray array = new InwardArrayResponse.InwardArray();
            array.setCustomer(inwardArrayResponses.get(pos).getCustomer());
            array.setPartName(inwardArrayResponses.get(pos).getPartName());

            //Part no
            String part_2 = inwardArrayResponses.get(pos).getPartNo().toString().trim();
            String lastTwo_part = part_2.substring(Math.max(part_2.length() - 2, 0));

            int PartNo = Integer.parseInt(lastTwo_part) +1;

            String final_Part =  part_2.substring(0, part_2.length() - 2);
            array.setPartNo(final_Part+PartNo+"");


            // Serial
            String serial_2 = inwardArrayResponses.get(pos).getSerialCode().toString().trim();
            String lastTwo_serial = serial_2.substring(Math.max(serial_2.length() - 2, 0));

            int SerialCode = Integer.parseInt(lastTwo_serial) +1;
            String final_Serial =  serial_2.substring(0, serial_2.length() - 2);
            array.setSerialCode(final_Serial+SerialCode+"");

            inwardArrayResponses.add(array);

            LoadRecyclerView();
        }
        else if (view == binding.txtSaveRMIN){
            if (binding.edtQRCodeRMIN.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RMInventoryActivity.this,"Kindly enter vaild QR Code");
            }
            else if (binding.edtRollNoRMIN.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RMInventoryActivity.this,"Kindly enter vaild Roll No");
            }
            else if (binding.edtLotNoRMIN.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RMInventoryActivity.this,"Kindly enter vaild Lot No");
            }
            else if (binding.edtSizeRMIN.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RMInventoryActivity.this,"Kindly enter vaild Size");
            }
            else if (binding.edtManagedNoRMIN.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RMInventoryActivity.this,"Kindly enter vaild Managed No");
            }
            else if (binding.edtMaterNameRMIN.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RMInventoryActivity.this,"Kindly enter vaild Material Name");
            }
            else if (binding.edtSupplierRMIN.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RMInventoryActivity.this,"Kindly enter vaild Supplier");
            }
            else if (binding.edtMaterialGradeRMIN.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RMInventoryActivity.this,"Kindly enter vaild Material Grade");
            }
            else if (binding.edtQuantityRMIN.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RMInventoryActivity.this,"Kindly enter vaild Quantity");
            }
            else if (binding.edtReceivedDateRMIN.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RMInventoryActivity.this,"Kindly enter vaild Received Date");
            }
            else if (binding.edtInwardDateRMIN.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RMInventoryActivity.this,"Kindly enter vaild Inward Date");
            }
            else if (inwardArrayResponses.size() == 0){
                CommonFunctions.getInstance().validationEmptyError(RMInventoryActivity.this,"Kindly enter vaild Customer, Part Name, Part No, Serial Code");
            }
            else if (binding.edtRemarksRMIN.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RMInventoryActivity.this,"Kindly enter vaild Remarks");
            }
            else {

                RMInwardAPIJson rmInwardAPIJson = new RMInwardAPIJson();
                rmInwardAPIJson.setMethod("rm_inward");
                rmInwardAPIJson.setRollNo(binding.edtRollNoRMIN.getText().toString().trim());
                rmInwardAPIJson.setLotNo(binding.edtLotNoRMIN.getText().toString().trim());
                rmInwardAPIJson.setSize(binding.edtSizeRMIN.getText().toString().trim());
                rmInwardAPIJson.setManagedNo(binding.edtManagedNoRMIN.getText().toString().trim());
                rmInwardAPIJson.setMaterialName(binding.edtMaterNameRMIN.getText().toString().trim());
                rmInwardAPIJson.setMaterialGrade(binding.edtMaterialGradeRMIN.getText().toString().trim());
                rmInwardAPIJson.setSupplier(binding.edtSupplierRMIN.getText().toString().trim());
                rmInwardAPIJson.setQuantity(binding.edtQuantityRMIN.getText().toString().trim());
                rmInwardAPIJson.setReceivedDate(binding.edtReceivedDateRMIN.getText().toString().trim().replace("-","."));
                rmInwardAPIJson.setInwardDate(binding.edtInwardDateRMIN.getText().toString().trim().replace("-","."));
                rmInwardAPIJson.setRemarks(binding.edtRemarksRMIN.getText().toString().trim());

                List<RMInwardAPIJson.InwardArray> arrays = new ArrayList<>();
                for (int i = 0; i < inwardArrayResponses.size(); i++) {
                    RMInwardAPIJson.InwardArray data = new RMInwardAPIJson.InwardArray();
                    data.setCustomer(inwardArrayResponses.get(i).getCustomer());
                    data.setPartName(inwardArrayResponses.get(i).getPartName());
                    data.setPartNo(inwardArrayResponses.get(i).getPartNo());
                    data.setSerialCode(inwardArrayResponses.get(i).getSerialCode());

                    arrays.add(data);
                }

                rmInwardAPIJson.setInwardArray(arrays);

                Gson gson = new Gson();
                String input = gson.toJson(rmInwardAPIJson);
                System.out.println("Input ==> " + input);

                CommonApiCalls.getInstance().rmInwardPost(RMInventoryActivity.this, input, new CommonCallback.Listener() {
                    @Override
                    public void onSuccess(Object body) {
                        RMInwardApiResponse apiResponse = (RMInwardApiResponse) body;
                        CommonFunctions.getInstance().successResponseToast(RMInventoryActivity.this, apiResponse.getMsg());

                        binding.edtCustomerRMIN.setText("");
                        binding.edtPartNameRMIN.setText("");
                        binding.edtPartNoRMIN.setText("");
                        binding.edtSerialCodeRMIN.setText("");

                        inwardArrayResponses = new ArrayList<>();

                        binding.edtQRCodeRMIN.setText("");
                        binding.edtRollNoRMIN.setText("");
                        binding.edtLotNoRMIN.setText("");
                        binding.edtSizeRMIN.setText("");
                        binding.edtManagedNoRMIN.setText("");
                        binding.edtMaterNameRMIN.setText("");
                        binding.edtSupplierRMIN.setText("");
                        binding.edtMaterialGradeRMIN.setText("");
                        binding.edtQuantityRMIN.setText("");
                        binding.edtReceivedDateRMIN.setText("");
                        binding.edtRemarksRMIN.setText("");

                        LoadRecyclerView();

                    }

                    @Override
                    public void onFailure(String reason) {
                        CommonFunctions.getInstance().validationEmptyError(RMInventoryActivity.this, reason);
                    }
                });

            }
        }
        else if (view == binding.edtInspectionDateGRN){
            final Calendar ca = Calendar.getInstance();
            int mYear = ca.get(Calendar.YEAR);
            int mMonth = ca.get(Calendar.MONTH);
            int mDay = ca.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(RMInventoryActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    // day
                    String day = dayOfMonth+"";
                    if (dayOfMonth < 10) {
                        day = "0"+dayOfMonth;
                    }
                    // month
                    int month_ = (monthOfYear + 1);
                    String month = month_+"";
                    if (month_ < 10) {
                        month = "0"+month_;
                    }
                    binding.edtInspectionDateGRN.setText(day + "." + month + "." + year);

                }
            }, mYear, mMonth, mDay);
            datePickerDialog.getDatePicker().setMinDate(ca.getTimeInMillis());
            datePickerDialog.show();
        }
        else if (view == binding.edtQAStatusGRN){
            final Dialog dialog = new Dialog(RMInventoryActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.setContentView(R.layout.dialog_qa_status);
            dialog.setCancelable(false);

            TextView txtNo =  dialog.findViewById(R.id.txtNo);
            TextView txtYes =  dialog.findViewById(R.id.txtYes);
            TextView tvPass =  dialog.findViewById(R.id.tvPass);
            TextView tvFail =  dialog.findViewById(R.id.tvFail);
            TextView tvOnHold =  dialog.findViewById(R.id.tvOnHold);

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
                }
            });

            tvPass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    binding.edtQAStatusGRN.setText("Pass");
                    dialog.dismiss();
                }
            });
            tvFail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    binding.edtQAStatusGRN.setText("Fail");
                    dialog.dismiss();
                }
            });
            tvOnHold.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    binding.edtQAStatusGRN.setText("Hold");
                    dialog.dismiss();
                }
            });

        }
        else if(view == binding.txtSaveGRN){
            if (binding.edtQAStatusGRN.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RMInventoryActivity.this,"Kindly enter valid QA Status");
            }
            else if (binding.edtQAPersonGRN.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RMInventoryActivity.this,"Kindly enter valid Qa Person");
            }
            else if (binding.edtInspectionDateGRN.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RMInventoryActivity.this,"Kindly enter valid Inspection Date");
            }
            else if (binding.edtRemarksGRN.getText().toString().trim().isEmpty()){
                CommonFunctions.getInstance().validationEmptyError(RMInventoryActivity.this,"Kindly enter valid Remarks");
            }
            else {
                GRNProcess2Json json = new GRNProcess2Json();
                json.setMethod("grn_process_2");
                json.setRollNo(binding.edtRollNoGRN.getText().toString().trim());
                json.setLotNo(binding.edtLotNoGRN.getText().toString().trim());
                json.setSize(binding.edtSizeGRN.getText().toString().trim());
                json.setManagedNo(binding.edtManagedNoGRN.getText().toString().trim());
                json.setMaterialName(binding.edtMaterNameGRN.getText().toString().trim());
                json.setSupplier(binding.edtSupplierGRN.getText().toString().trim());
                json.setMaterialGrade(binding.edtMaterialGradeGRN.getText().toString().trim());
                json.setQuantity(binding.edtQuantityGRN.getText().toString().trim());
                json.setReceivedDate(binding.edtReceivedDateGRN.getText().toString().trim());
                json.setInwardDate(binding.edtInwardDateGRN.getText().toString().trim());
                json.setQAStatus(binding.edtQAStatusGRN.getText().toString().trim());
                json.setQAPerson(binding.edtQAPersonGRN.getText().toString().trim());
                json.setInspectionDate(binding.edtInspectionDateGRN.getText().toString().trim());
                json.setRemarks(binding.edtRemarksGRN.getText().toString().trim());

                List<GRNProcess2Json.InwardArray> arrays = new ArrayList<>();
                if (GRN_inward_Array.size() > 0){
                    for (int i = 0; i < GRN_inward_Array.size(); i++) {
                        GRNProcess2Json.InwardArray value = new GRNProcess2Json.InwardArray();
                        GRNListApiResponse.InwardArray data = GRN_inward_Array.get(i);
                        value.setCustomer(data.getCustomer());
                        value.setPartName(data.getPartName());
                        value.setPartNo(data.getPartNo());
                        value.setSerialCode(data.getSerialCode());

                        arrays.add(value);
                    }
                }
                json.setInwardArray(arrays);


                Gson gson = new Gson();
                String input = gson.toJson(json);
                System.out.println("Input ==> " + input);


                CommonApiCalls.getInstance().PostGRN2Process(RMInventoryActivity.this, input, new CommonCallback.Listener() {
                    @Override
                    public void onSuccess(Object body) {
                        GRN2ListApiResponse apiResponse = (GRN2ListApiResponse) body;
                        CommonFunctions.getInstance().successResponseToast(RMInventoryActivity.this, apiResponse.getMsg());

                        binding.rvListGRN.setVisibility(View.GONE);
                        GRN_inward_Array = new ArrayList<>();

                        binding.edtQRCodeGRN.setText("");
                        binding.edtRollNoGRN.setText("");
                        binding.edtLotNoGRN.setText("");
                        binding.edtSizeGRN.setText("");
                        binding.edtManagedNoGRN.setText("");
                        binding.edtMaterNameGRN.setText("");
                        binding.edtSupplierGRN.setText("");
                        binding.edtMaterialGradeGRN.setText("");
                        binding.edtQuantityGRN.setText("");
                        binding.edtReceivedDateGRN.setText("");
                        binding.edtInwardDateGRN.setText("");
                        binding.edtQAStatusGRN.setText("");
                        binding.edtQAPersonGRN.setText("");
                        binding.edtInspectionDateGRN.setText("");
                        binding.edtReceivedDateGRN.setText("");

                    }

                    @Override
                    public void onFailure(String reason) {
                        CommonFunctions.getInstance().validationEmptyError(RMInventoryActivity.this, reason);
                    }
                });

            }
        }
    }

    private void LoadRecyclerView() {
        if (inwardArrayResponses.size() > 0) {
            binding.rvAddList.setVisibility(View.VISIBLE);
            binding.txtAddMoreRMIN.setVisibility(View.VISIBLE);

            @SuppressLint("WrongConstant")
            LinearLayoutManager layoutManager = new LinearLayoutManager(RMInventoryActivity.this, LinearLayoutManager.VERTICAL, false);
            binding.rvAddList.setLayoutManager(layoutManager);
            RMInventoryAddListAdapter adapter = new RMInventoryAddListAdapter(RMInventoryActivity.this, inwardArrayResponses, new ListRemove() {
                @Override
                public void onClick() {
                    if (inwardArrayResponses.size() == 0) {
                        binding.rvAddList.setVisibility(View.GONE);
                        binding.txtAddMoreRMIN.setVisibility(View.GONE);
                    }
                }
            });
            binding.rvAddList.setAdapter(adapter);
        }
        else {
            binding.rvAddList.setVisibility(View.GONE);
            binding.txtAddMoreRMIN.setVisibility(View.GONE);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (selectedTab.equals("RmInward")) {
            if (!CommonFunctions.RollNo.isEmpty()) {
                binding.edtQRCodeRMIN.setText(CommonFunctions.BAR_CODE_VALUE);
                binding.edtRollNoRMIN.setText(CommonFunctions.RollNo);
                binding.edtLotNoRMIN.setText(CommonFunctions.LotNo);
                binding.edtSizeRMIN.setText(CommonFunctions.Size);
                binding.edtManagedNoRMIN.setText(CommonFunctions.ManagedNo);

                CommonFunctions.RollNo = "";
                CommonFunctions.LotNo = "";
                CommonFunctions.Size = "";
                CommonFunctions.ManagedNo = "";
            }
            else if (!DATA_TIME.isEmpty()) {
                binding.edtReceivedDateRMIN.setText(DATA_TIME);
                CommonFunctions.DATA_TIME = "";
            }
        }
        else if (selectedTab.equals("GRNProcess")){
            if (!CommonFunctions.RollNo.isEmpty()) {
                binding.edtQRCodeGRN.setText(CommonFunctions.BAR_CODE_VALUE);
                binding.edtRollNoGRN.setText(CommonFunctions.RollNo);
                binding.edtLotNoGRN.setText(CommonFunctions.LotNo);
                binding.edtSizeGRN.setText(CommonFunctions.Size);
                binding.edtManagedNoGRN.setText(CommonFunctions.ManagedNo);

                CommonFunctions.RollNo = "";
                CommonFunctions.LotNo = "";
                CommonFunctions.Size = "";
                CommonFunctions.ManagedNo = "";

                GRNProcessGETJson json = new GRNProcessGETJson();
                json.setMethod("grn_process_1");
                json.setRollNo(binding.edtRollNoGRN.getText().toString().trim());
                json.setLotNo(binding.edtLotNoGRN.getText().toString().trim());
                json.setSize(binding.edtSizeGRN.getText().toString().trim());
                json.setManagedNo(binding.edtManagedNoGRN.getText().toString().trim());

                Gson gson = new Gson();
                String input = gson.toJson(json);
                System.out.println("Input ==> " + input);

                CommonApiCalls.getInstance().getGRNProcess(RMInventoryActivity.this, input, new CommonCallback.Listener() {
                    @Override
                    public void onSuccess(Object body) {
                        GRNListApiResponse apiResponse = (GRNListApiResponse) body;
                        CommonFunctions.getInstance().successResponseToast(RMInventoryActivity.this, apiResponse.getMsg());

                        if (apiResponse.getData().size() > 0){
                            GRNListApiResponse.Datum value = apiResponse.getData().get(0);
                            binding.edtMaterNameGRN.setText(value.getMaterialName());
                            binding.edtSupplierGRN.setText(value.getSupplier());
                            binding.edtMaterialGradeGRN.setText(value.getMaterialGrade());
                            binding.edtQuantityGRN.setText(value.getQuantity());
                            binding.edtReceivedDateGRN.setText(value.getReceivedDate().replace("-","."));
                            binding.edtInwardDateGRN.setText(value.getInwardDate().replace("-","."));

                            if (value.getInwardArray().size() > 0) {
                                binding.rvListGRN.setVisibility(View.VISIBLE);

                                GRN_inward_Array = value.getInwardArray();

                                @SuppressLint("WrongConstant")
                                LinearLayoutManager layoutManager = new LinearLayoutManager(RMInventoryActivity.this, LinearLayoutManager.VERTICAL, false);
                                binding.rvListGRN.setLayoutManager(layoutManager);
                                GRNListAdapter adapter = new GRNListAdapter(RMInventoryActivity.this, value.getInwardArray());
                                binding.rvListGRN.setAdapter(adapter);
                            }
                            else {
                                binding.rvListGRN.setVisibility(View.GONE);
                            }
                        }

                    }

                    @Override
                    public void onFailure(String reason) {
                        CommonFunctions.getInstance().validationEmptyError(RMInventoryActivity.this, reason);
                    }
                });
            }
        }
    }

}
