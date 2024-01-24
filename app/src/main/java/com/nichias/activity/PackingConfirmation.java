package com.nichias.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.blikoon.qrcodescanner.QrCodeActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.nichias.R;
import com.nichias.databinding.ActivityPackingConfirmationBinding;
import com.nichias.utils.CommonFunctions;

import hari.bounceview.BounceView;

public class PackingConfirmation extends AppCompatActivity implements View.OnClickListener {
    ActivityPackingConfirmationBinding binding;
    private IntentIntegrator qrScan;
    private static final int REQUEST_CODE_QR_SCAN = 101;
    private static final int MY_CAMERA_REQUEST_CODE = 100;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_packing_confirmation);

        qrScan = new IntentIntegrator(PackingConfirmation.this);
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST_CODE);
        }

        initialView();

    }
    private void initialView() {
        BounceView.addAnimTo(binding.imgBack);
        BounceView.addAnimTo(binding.imgScanner);
        BounceView.addAnimTo(binding.txtOk);
        BounceView.addAnimTo(binding.txtNotOk);
        BounceView.addAnimTo(binding.txtRescan);

        binding.imgBack.setOnClickListener(this);
        binding.imgScanner.setOnClickListener(this);
        binding.txtOk.setOnClickListener(this);
        binding.txtNotOk.setOnClickListener(this);
        binding.txtRescan.setOnClickListener(this);
        binding.txtNextPage.setOnClickListener(this);

        binding.tvTitle.setText("Packing Confirmation");
    }

    @Override
    public void onClick(View view) {
        if (view == binding.imgBack){
            finish();
        }
        else if (view == binding.imgScanner || view == binding.txtRescan){
            // CommonFunctions.getInstance().newIntent(RMInventoryActivity.this, ScanActivity.class, Bundle.EMPTY, false, false);
            Intent i = new Intent(PackingConfirmation.this, QrCodeActivity.class);
            startActivityForResult( i,REQUEST_CODE_QR_SCAN);
        }
        else if (view == binding.txtNotOk){
            binding.edtCoilNo.setText("");
            binding.edtBarcode.setText("");
        }
        else if (view == binding.txtNextPage){
            CommonFunctions.getInstance().newIntent(PackingConfirmation.this, PackingSSIPLIDConfirmation.class, Bundle.EMPTY, false, false);
        }
    }
}
