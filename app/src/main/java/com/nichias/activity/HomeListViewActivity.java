package com.nichias.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;

import com.google.zxing.integration.android.IntentIntegrator;
import com.nichias.R;
import com.nichias.databinding.ActivityHomeListBinding;
import com.nichias.interfaces.OnHomePressedListener;
import com.nichias.utils.CommonFunctions;

import java.util.Objects;

import hari.bounceview.BounceView;

public class HomeListViewActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityHomeListBinding binding;
    private boolean doubleBackToExitPressedOnce = false;
    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home_list);
        qrScan = new IntentIntegrator(HomeListViewActivity.this);
        checkPermission();

        initialView();


        HomeWatcher mHomeWatcher = new HomeWatcher(this);
        mHomeWatcher.setOnHomePressedListener(new OnHomePressedListener() {
            @Override
            public void onHomePressed() {
              //  Toast.makeText(HomeListViewActivity.this, "Home Button Pressed", Toast.LENGTH_SHORT).show();
                // do something here...
            }
            @Override
            public void onHomeLongPressed() {
            }
        });
        mHomeWatcher.startWatch();

    }

    private void initialView() {
        BounceView.addAnimTo(binding.lyoutRMInventory);
        BounceView.addAnimTo(binding.lyoutRmStorage);


        binding.lyoutRMInventory.setOnClickListener(this);
        binding.lyoutRmStorage.setOnClickListener(this);
        binding.lyoutJobOrder.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == binding.lyoutRMInventory){
            CommonFunctions.getInstance().newIntent(HomeListViewActivity.this, RMInventoryActivity.class, Bundle.EMPTY, false, false);
        }
        else if (view == binding.lyoutRmStorage){
            CommonFunctions.getInstance().newIntent(HomeListViewActivity.this, RMStorageActivity.class, Bundle.EMPTY, false, false);
        }
        else if (view == binding.lyoutJobOrder){
            CommonFunctions.getInstance().newIntent(HomeListViewActivity.this, JobOrderActivity.class, Bundle.EMPTY, false, false);
        }
    }

    // ----- On BackPressed
    @Override
    public void onBackPressed() {
        FragmentManager manager = getSupportFragmentManager();
        int count = manager.getBackStackEntryCount();
        if (count == 1) {
            super.onBackPressed();
        }

        if (count == 0) {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            CommonFunctions.getInstance().validationEmptyError(HomeListViewActivity.this, "Please click back again to exit.");
        }
    }



    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(HomeListViewActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    + ContextCompat.checkSelfPermission(HomeListViewActivity.this, Manifest.permission.CAMERA)
                    + ContextCompat.checkSelfPermission(HomeListViewActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(HomeListViewActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        || ActivityCompat.shouldShowRequestPermissionRationale(HomeListViewActivity.this, Manifest.permission.CAMERA)
                        || ActivityCompat.shouldShowRequestPermissionRationale(HomeListViewActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                    ActivityCompat.requestPermissions(HomeListViewActivity.this,
                            new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                            999);

                } else {
                    ActivityCompat.requestPermissions(HomeListViewActivity.this,
                            new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 999);
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        CommonFunctions.getInstance().hideSoftKeyboardNew(HomeListViewActivity.this);
    }
}
