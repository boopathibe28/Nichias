package com.nichias.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.facebook.login.Login;
import com.onesignal.OneSignal;
import com.nichias.R;
import com.nichias.api.CommonApiCalls;
import com.nichias.app_interfaces.CommonCallback;
import com.nichias.databinding.ActivityLoginBinding;
import com.nichias.model_api.LoginApiResponse;
import com.nichias.model_api.RegisterOTPApiResponse;
import com.nichias.utils.CommonFunctions;
import com.nichias.utils.LanguageConstants;
import com.nichias.utils._pref.SessionManager;
import hari.bounceview.BounceView;
import static com.nichias.utils.CommonFunctions.DEVICE_TYPE;
import static com.nichias.utils.CommonFunctions.FROM_CART_PAGE;
import static com.nichias.utils._pref.SharedPrefConstants.ACCESS_TOKEN;
import static com.nichias.utils._pref.SharedPrefConstants.AREA;
import static com.nichias.utils._pref.SharedPrefConstants.CITY;
import static com.nichias.utils._pref.SharedPrefConstants.DEVICE_TOKEN;
import static com.nichias.utils._pref.SharedPrefConstants.DOB;
import static com.nichias.utils._pref.SharedPrefConstants.EMAIL_ADDRESS;
import static com.nichias.utils._pref.SharedPrefConstants.FIRST_NAME;
import static com.nichias.utils._pref.SharedPrefConstants.GENDER;
import static com.nichias.utils._pref.SharedPrefConstants.LANGUAGE_CODE;
import static com.nichias.utils._pref.SharedPrefConstants.LAST_NAME;
import static com.nichias.utils._pref.SharedPrefConstants.MOBILE_NO;
import static com.nichias.utils._pref.SharedPrefConstants.NAME;
import static com.nichias.utils._pref.SharedPrefConstants.PROFILE_IMAGE;
import static com.nichias.utils._pref.SharedPrefConstants.TOURPAGE;
import static com.nichias.utils._pref.SharedPrefConstants.USER_KEY;

import java.util.Locale;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityLoginBinding binding;
    private boolean isPassword;
    public static LoginActivity tempLoginActivity;
    public static String register = "false";

    @SuppressLint("RtlHardcoded")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);


        initView();
    }


    // ---- Initial View
    private void initView() {
        BounceView.addAnimTo(binding.txtLogin);
        BounceView.addAnimTo(binding.txtForgotPassword);
        BounceView.addAnimTo(binding.lyoutNewAccount);

        binding.txtHeader.setText(LanguageConstants.logIn);
        binding.edtEmail.setHint("User Name");
        binding.edtPassword.setHint(LanguageConstants.password);
        binding.txtForgotPassword.setText(LanguageConstants.forgotPassword);
        binding.txtLogin.setText(LanguageConstants.logIn);
        binding.txtRegister.setText(LanguageConstants.register_a);
        binding.txtNewAccount.setText(LanguageConstants.newAccount);

        binding.txtLogin.setOnClickListener(this);
        binding.txtForgotPassword.setOnClickListener(this);
        binding.imgPasswordVG.setOnClickListener(this);
        binding.lyoutNewAccount.setOnClickListener(this);

        binding.imgPasswordVG.setBackgroundResource(R.drawable.password_gone);

    }
    // ---- OnClickListener
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            // Password
            case R.id.imgPasswordVG:
                if (isPassword){
                    binding.imgPasswordVG.setBackgroundResource(R.drawable.password_gone);
                    binding.edtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    isPassword = false;
                    binding.edtPassword.setSelection(binding.edtPassword.getText().length());
                }
                else {
                    binding.imgPasswordVG.setBackgroundResource(R.drawable.password_visibility);
                    binding.edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    isPassword = true;
                    binding.edtPassword.setSelection(binding.edtPassword.getText().length());
                }
                break;
            // SignIn
            case R.id.lyoutNewAccount:
                //CommonFunctions.getInstance().newIntent(LoginActivity.this, SignUpActivity.class, Bundle.EMPTY, false,false);
                break;
            // ForgotPassword
            case R.id.txtForgotPassword:
                CommonFunctions.getInstance().newIntent(LoginActivity.this, ForgotPasswordActivity.class, Bundle.EMPTY, false,false);
                break;
            // Login
            case R.id.txtLogin:
                if (binding.edtEmail.getText().toString().trim().isEmpty()){
                    CommonFunctions.getInstance().validationError(LoginActivity.this, "User name is required");
                }
                else if (binding.edtEmail.getText().toString().length() < 3){
                    CommonFunctions.getInstance().validationError(LoginActivity.this, "Enter valid User name");
                }
                else if (binding.edtPassword.getText().toString().trim().isEmpty()) {
                    CommonFunctions.getInstance().validationError(LoginActivity.this, "Password is required");
                }
                /*else if (binding.edtPassword.getText().toString().trim().length() < 6) {
                    CommonFunctions.getInstance().validationError(LoginActivity.this, "Enter valid Password");
                }*/
                else {
                    if (binding.edtEmail.getText().toString().trim().equals("Traceability") && binding.edtPassword.getText().toString().trim().equals("123456")) {
                        SessionManager.getInstance().insertIntoPreference(LoginActivity.this, USER_KEY, "1");
                        CommonFunctions.getInstance().newIntent(LoginActivity.this, HomeListViewActivity.class, Bundle.EMPTY, true, true);
                    }
                }

                break;

            default:
                break;
        }

    }


}