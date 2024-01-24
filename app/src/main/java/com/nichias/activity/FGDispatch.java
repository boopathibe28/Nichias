package com.nichias.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.zxing.integration.android.IntentIntegrator;
import com.nichias.R;
import com.nichias.databinding.ActivityFgdispatchBinding;

import hari.bounceview.BounceView;

public class FGDispatch extends AppCompatActivity implements View.OnClickListener {
    ActivityFgdispatchBinding binding;
    private IntentIntegrator qrScan;
    private static final int MY_CAMERA_REQUEST_CODE = 100;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fgdispatch);

        qrScan = new IntentIntegrator(this);
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }

        initialView();

    }

    private void initialView() {
        BounceView.addAnimTo(binding.imgBack);
        BounceView.addAnimTo(binding.txtDelete);
        BounceView.addAnimTo(binding.txtAccept);
        BounceView.addAnimTo(binding.imgSearch);

        binding.imgBack.setOnClickListener(this);
        binding.txtDelete.setOnClickListener(this);
        binding.txtAccept.setOnClickListener(this);

        binding.imgSearch.setOnClickListener(this);
        binding.edtSSIPLID.setOnClickListener(this);

        binding.lyoutLocationID.setOnClickListener(this);
        binding.edtLocationID.setOnClickListener(this);

        binding.tvTitle.setText("FG Dispatch");
    }

    @Override
    public void onClick(View view) {
        if (view == binding.imgBack) {
            finish();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        /*if (!ssiplID.isEmpty()){
            binding.edtSSIPLID.setText(ssiplID);
            binding.edtBarcode.setText(ssiplID);

            ssiplID = "";
        }

        if (!locationID.isEmpty()){
            binding.edtLocationID.setText(locationID);
            binding.edtBarcode.setText(locationID);

            locationID = "";
        }*/
    }
}
