package com.nichias.activity;

import static com.nichias.utils._pref.SharedPrefConstants.LANGUAGE_CODE;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.nichias.R;
import com.nichias.databinding.ActivityHelpViewBinding;
import com.nichias.utils._pref.SessionManager;

import hari.bounceview.BounceView;

public class HelpViewActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityHelpViewBinding binding;
    private String cms_url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_help_view);

        if (SessionManager.getInstance().getFromPreference(LANGUAGE_CODE).equals("ar")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            binding.imgBack.setImageResource(R.drawable.ic_color_back_ar);
        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
            binding.imgBack.setImageResource(R.drawable.ic_color_back);
        }

        initialView();

        if (getIntent().getExtras() != null) {
            String cms_title = getIntent().getExtras().getString("cms_title");
            cms_url = getIntent().getExtras().getString("cms_url");

            binding.tvTitle.setText(cms_title);
          //  binding.txtContent.setText(Html.fromHtml(content));
        }

        WebSettings webSettings = binding.webview.getSettings();
        binding.webview.loadUrl(cms_url);

    }

// ---- Initial View
    private void initialView() {
        BounceView.addAnimTo(binding.imgBack);

        binding.imgBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == binding.imgBack){
            finish();
        }
    }
}
