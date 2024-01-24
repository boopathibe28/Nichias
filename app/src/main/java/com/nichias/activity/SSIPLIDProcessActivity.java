package com.nichias.activity;

import static com.nichias.utils.CommonFunctions.ansCoilNo;
import static com.nichias.utils.CommonFunctions.ansSize;
import static com.nichias.utils.CommonFunctions.ansWeight;
import static com.nichias.utils.CommonFunctions.ssiplID;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.gson.Gson;
import com.nichias.R;
import com.nichias.api.CommonApiCalls;
import com.nichias.app_interfaces.CommonCallback;
import com.nichias.databinding.ActivitySsiplidprocessBinding;
import com.nichias.dummy_model.RMGridStorageJSON;
import com.nichias.dummy_model.SSIPLidJson;
import com.nichias.model_api.RMInventoryApiResponse;
import com.nichias.model_api.SsiplIDApiResponse;
import com.nichias.utils.CommonFunctions;

import hari.bounceview.BounceView;

public class SSIPLIDProcessActivity extends AppCompatActivity implements View.OnClickListener {
    ActivitySsiplidprocessBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ssiplidprocess);

        initialView();

    }

    private void initialView() {
        BounceView.addAnimTo(binding.imgBack);
        BounceView.addAnimTo(binding.imgScanner);
        BounceView.addAnimTo(binding.txtHomeSSIPL);
        BounceView.addAnimTo(binding.txtNotOkSSIPL);
        BounceView.addAnimTo(binding.txtOkSSIPL);

        binding.imgBack.setOnClickListener(this);
        binding.imgScanner.setOnClickListener(this);
        binding.txtHomeSSIPL.setOnClickListener(this);
        binding.txtNotOkSSIPL.setOnClickListener(this);
        binding.txtOkSSIPL.setOnClickListener(this);

        binding.tvTitle.setText("SSIPL ID Process");
    }

    @Override
    public void onClick(View view) {
        if (view == binding.imgBack){
            finish();
        }
        else if (view == binding.imgScanner) {
            if (!binding.edtCoilNoSSIPL.getText().toString().trim().isEmpty()) {
                Bundle bundle = new Bundle();
                bundle.putString("from", "4");
                CommonFunctions.getInstance().newIntent(SSIPLIDProcessActivity.this, ScannedBarcodeActivity.class, bundle, false, false);
            }
            else {
                Bundle bundle = new Bundle();
                bundle.putString("from", "1");
                CommonFunctions.getInstance().newIntent(SSIPLIDProcessActivity.this, ScannedBarcodeActivity.class, bundle, false, false);
            }
        }
        else if (view == binding.txtOkSSIPL){

        }
        else if (view == binding.txtNotOkSSIPL){
            binding.edtCoilNoSSIPL.setText("");
            binding.edtBarcodeSSIPL.setText("");
            binding.edtSizeSSIPL.setText("");
            binding.edtGradeSSIPL.setText("");
            binding.edtWeightSSIPL.setText("");
            binding.edtHeatNoSSIPL.setText("");
            binding.edtSSIPLIDSSIPL.setText("");
        }
        else if (view == binding.txtHomeSSIPL){
            CommonFunctions.getInstance().newIntent(SSIPLIDProcessActivity.this, HomeListViewActivity.class, Bundle.EMPTY, true, true);
        }
    }



    @Override
    protected void onResume() {
        super.onResume();
        if (!ansCoilNo.isEmpty()){
            binding.edtCoilNoSSIPL.setText(ansCoilNo);
            binding.edtSizeSSIPL.setText(ansSize);
            binding.edtWeightSSIPL.setText(ansWeight);

            ansCoilNo = "";
            ansSize = "";
            ansWeight = "";
        }
        if (!ssiplID.isEmpty()){
            binding.edtSSIPLIDSSIPL.setText(ssiplID);

            ssiplID = "";
        }

        // api call
        if (!binding.edtCoilNoSSIPL.getText().toString().trim().isEmpty() &&
            !binding.edtSSIPLIDSSIPL.getText().toString().trim().isEmpty() ){
            setValue();
        }
    }

    private void setValue() {
        SSIPLidJson ssipLidJson = new SSIPLidJson();
        ssipLidJson.setSSIPL_Id(binding.edtSSIPLIDSSIPL.getText().toString().trim());
        ssipLidJson.setCoil_No(binding.edtCoilNoSSIPL.getText().toString().trim());

        Gson gson = new Gson();
        String input = gson.toJson(ssipLidJson);
        System.out.println("Input ==> " + input);

        apiCall(input);
    }

    private void apiCall(String input) {
        CommonApiCalls.getInstance().ssiplID(SSIPLIDProcessActivity.this, input, new CommonCallback.Listener() {
            @Override
            public void onSuccess(Object body) {
                SsiplIDApiResponse apiResponse = (SsiplIDApiResponse) body;

                if (apiResponse.getResult().equals("1")){
                    binding.txtOkSSIPL.setVisibility(View.VISIBLE);
                    binding.txtNotOkSSIPL.setVisibility(View.GONE);

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            CommonFunctions.getInstance().successResponseToast(SSIPLIDProcessActivity.this, apiResponse.getStatus());

                            binding.edtCoilNoSSIPL.setText("");
                            binding.edtBarcodeSSIPL.setText("");
                            binding.edtSizeSSIPL.setText("");
                            binding.edtGradeSSIPL.setText("");
                            binding.edtWeightSSIPL.setText("");
                            binding.edtHeatNoSSIPL.setText("");
                            binding.edtSSIPLIDSSIPL.setText("");
                        }
                    }, 900);

                }
                else {
                    binding.txtOkSSIPL.setVisibility(View.GONE);
                    binding.txtNotOkSSIPL.setVisibility(View.VISIBLE);
                }

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        CommonFunctions.getInstance().newIntent(SSIPLIDProcessActivity.this, HomeListViewActivity.class, Bundle.EMPTY, true, true);
                    }
                }, 4500);
            }

            @Override
            public void onFailure(String reason) {
                CommonFunctions.getInstance().validationEmptyError(SSIPLIDProcessActivity.this, reason);
            }
        });
    }

}
