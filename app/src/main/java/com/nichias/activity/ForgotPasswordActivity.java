package com.nichias.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.nichias.R;
import com.nichias.databinding.ActivityForgotPasswordBinding;
import com.nichias.utils.CommonFunctions;
import com.nichias.utils.LanguageConstants;
import hari.bounceview.BounceView;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityForgotPasswordBinding binding;

    @SuppressLint("RtlHardcoded")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password);
        //CommonFunctions.getInstance().ChangeDirection(ForgotPasswordActivity.this);


        initiView();
    }
// ---- Initial View
    private void initiView() {
        BounceView.addAnimTo(binding.txtSubmit);
        BounceView.addAnimTo(binding.imgBack);

        binding.imgBack.setOnClickListener(this);
        binding.txtSubmit.setOnClickListener(this);

        binding.txtForgotPassword.setText("Forgot Password");
        binding.edtEmail.setHint(LanguageConstants.mobileNo);
        binding.txtSubmit.setText(LanguageConstants.submit);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            // Submit
            case R.id.txtSubmit:
                /*if (binding.edtEmail.getText().toString().trim().isEmpty()){
                    CommonFunctions.getInstance().validationError(ForgotPasswordActivity.this, LanguageConstants.emailAddressIsRequired);
                }
                else if (!CommonFunctions.getInstance().isValidEmail(binding.edtEmail.getText().toString().trim())) {
                    CommonFunctions.getInstance().validationError(ForgotPasswordActivity.this, LanguageConstants.entervalidemailAddress);
                }*/
                if (binding.edtEmail.getText().toString().trim().isEmpty()){
                    CommonFunctions.getInstance().validationError(ForgotPasswordActivity.this, "Enter valid Mobile No");
                }
                else if (binding.edtEmail.getText().toString().trim().length() < 10){
                    CommonFunctions.getInstance().validationError(ForgotPasswordActivity.this, "Enter valid Mobile No");
                }
                else {
                   finish();
                }
                break;
             // Back
            case R.id.imgBack:
                finish();
                break;
            default:
                break;
        }
    }

}