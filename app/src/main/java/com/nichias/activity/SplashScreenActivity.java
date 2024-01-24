package com.nichias.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.nichias.R;
import com.nichias.databinding.ActivitySplashScreenBinding;
import com.nichias.utils.CommonFunctions;
import com.nichias.utils.LanguageConstants;
import com.nichias.utils.LocationOnOff_Similar_To_Google_Maps;
import com.nichias.utils.VersionComparator;
import com.nichias.utils._pref.SessionManager;
import com.google.android.material.snackbar.Snackbar;
import com.onesignal.OneSignal;
import com.nichias.utils._pref.SharedPrefConstants;

import org.jsoup.Jsoup;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Timer;
import java.util.TimerTask;

import static com.nichias.utils._pref.SharedPrefConstants.CURRENCY_CODE;
import static com.nichias.utils._pref.SharedPrefConstants.DEVICE_TOKEN;
import static com.nichias.utils._pref.SharedPrefConstants.LANGUAGE_CODE;
import static com.nichias.utils._pref.SharedPrefConstants.TOURPAGE;
import static com.nichias.utils._pref.SharedPrefConstants.USER_KEY;

public class SplashScreenActivity extends AppCompatActivity {
    ActivitySplashScreenBinding binding;
    long SPLASH_TIME = 4500;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen);

        if (SessionManager.getInstance().getFromPreference(LANGUAGE_CODE).isEmpty()) {
            SessionManager.getInstance().insertIntoPreference(getApplicationContext(),LANGUAGE_CODE,"en");
        }
        else {
            SessionManager.getInstance().insertIntoPreference(SplashScreenActivity.this,LANGUAGE_CODE,SessionManager.getInstance().getFromPreference(LANGUAGE_CODE));
        }

        SessionManager.getInstance().insertIntoPreference(SplashScreenActivity.this, SharedPrefConstants.DEVICE_TYPE, "1");
       // CommonFunctions.Onesignal(SplashScreenActivity.this);

        //binding.txtVersion.setText(CommonFunctions.version());
        binding.rlyoutParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkInternetConnection();
            }
        });
        checkInternetConnection();

      //  Intent intent = new Intent(SplashScreenActivity.this, LocationOnOff_Similar_To_Google_Maps.class);
       // startActivity(intent);

        // throw new RuntimeException("Test Crash");
    }



    private void checkInternetConnection() {
        //Check internet connection
        /*if (CommonFunctions.CheckInternetConnection()) {
            splash();
        } else {
            CommonFunctions.getInstance().validationError(SplashScreenActivity.this, LanguageConstants.kindly_check_internet);
        }*/
        splash();
    }

    private void splash() {
        // Check Permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(SplashScreenActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    + ContextCompat.checkSelfPermission(SplashScreenActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                    + ContextCompat.checkSelfPermission(SplashScreenActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(SplashScreenActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        || ActivityCompat.shouldShowRequestPermissionRationale(SplashScreenActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                        || ActivityCompat.shouldShowRequestPermissionRationale(SplashScreenActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)) {

                    Snackbar.make(findViewById(android.R.id.content), "Please Grant Permissions", Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ActivityCompat.requestPermissions(SplashScreenActivity.this,
                                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                                            999);
                                }
                            }).show();
                } else {
                    ActivityCompat.requestPermissions(SplashScreenActivity.this,
                            new String[]{ Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 999);
                }
            } else {
                LoadSplash();
            }
        } else {
            LoadSplash();
        }

        generateKeyHash();
    }


    void LoadSplash() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LoadNext();
               // new GetVersionCode().execute();
            }
        }, SPLASH_TIME);
    }

    private class GetVersionCode extends AsyncTask<Void, String, String> {
        @Override
        protected String doInBackground(Void... voids) {

            String newVersion = null;
            try {

                newVersion = Jsoup.connect("https://play.google.com/store/apps/details?id=" + SplashScreenActivity.this.getPackageName()+ "&hl=en")
                        .timeout(30000)
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.google.com")
                        .get()
                        .select("div.hAyfc:nth-child(4) > span:nth-child(2) > div:nth-child(1) > span:nth-child(1)")
                        .first()
                        .ownText();
                return newVersion;
            } catch (Exception e) {
                e.printStackTrace();
                return newVersion;
            }
        }

        @Override
        protected void onPostExecute(String onlineVersion) {
            super.onPostExecute(onlineVersion);
            String currentVersion = null;
            try {
                currentVersion = SplashScreenActivity.this.getPackageManager().getPackageInfo(SplashScreenActivity.this.getPackageName(), 0).versionName;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            if (onlineVersion != null && currentVersion != null && !onlineVersion.isEmpty() && !currentVersion.isEmpty()) {

                int version = VersionComparator.compareVersionNames(currentVersion, onlineVersion);

                if (version < 0) {
                    ShowUpdateDialog();
                } else {
                    LoadNext();
                }
            } else {
                LoadNext();
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void ShowUpdateDialog() {
        final Dialog dialog = new Dialog(SplashScreenActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_updateapp);
        dialog.setCancelable(false);
        TextView tvTitle = (TextView) dialog.findViewById(R.id.tvTitle);
        TextView tvMessage = (TextView) dialog.findViewById(R.id.tvMessage);

        Button btnUpdate= (Button) dialog.findViewById(R.id.btnUpdate);
        Button btnLater= (Button) dialog.findViewById(R.id.btnLater);

        tvTitle.setText("New Version!");
        tvMessage.setText("There is newer version of this application available, click UPDATE to upgrade now?");
        btnUpdate.setText("Update");
        btnLater.setText("Later");
        btnLater.setVisibility(View.GONE);

        btnLater.setAllCaps(false);
        btnUpdate.setAllCaps(false);

        btnLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadNext();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://play.google.com/store/apps/details?id="+ SplashScreenActivity.this.getPackageName()));
                startActivity(intent);
            }
        });

        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        window.setAttributes(wlp);
        if(!SplashScreenActivity.this.isFinishing()) {
            dialog.show();
        }

    }

    void LoadNext() {
        getLanguageConstants();
    }

    private void getLanguageConstants() {
        // ---- Load Next
        if (CommonFunctions.getInstance().isActivityRunning(SplashScreenActivity.this)) {
//            if (SessionManager.getInstance().getFromPreference(TOURPAGE).isEmpty()) {
//               // CommonFunctions.getInstance().newIntent(SplashScreenActivity.this, TourActivity.class, Bundle.EMPTY, true,true);
//            } else {


            if (SessionManager.getInstance().getFromPreference(USER_KEY).isEmpty()) {
                CommonFunctions.getInstance().newIntent(SplashScreenActivity.this, LoginActivity.class, Bundle.EMPTY, true, true);
               // CommonFunctions.getInstance().newIntent(SplashScreenActivity.this, HomeActivity.class, Bundle.EMPTY, true, true);
            } else {
                CommonFunctions.getInstance().newIntent(SplashScreenActivity.this, HomeListViewActivity.class, Bundle.EMPTY, true, true);
            }

        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 999: {
                if ((grantResults.length > 0) && (grantResults[0] +
                        grantResults[1]) == PackageManager.PERMISSION_GRANTED) {
                    LoadSplash();
                } else {
                    Snackbar snackBarView = Snackbar.make(findViewById(android.R.id.content), "Enable Permissions from settings",
                            Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
                            new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent();
                                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                                    intent.setData(Uri.parse("package:" + getPackageName()));
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                                    startActivity(intent);
                                }
                            });
//                    CommonFunctions.getInstance().displaySnackBarWithBottomMargin(snackBarView, 0, 100);
                    snackBarView.show();
                }
                return;
            }
        }
    }

    // ------ generateKeyHash
    private void generateKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.nabawater", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.i("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }

    @Override
    public void onBackPressed() {

    }

}