package com.nichias.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import com.nichias.R;

public class CustomProgressDialog {
    private static CustomProgressDialog ourInstance = new CustomProgressDialog();
    Dialog dialog;

    public static CustomProgressDialog getInstance() {
        return ourInstance;
    }

    private CustomProgressDialog() {
    }

    public void show(Context context) {
        try {
            dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.pdialog);
            // dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        //    ImageView imgLoader = dialog.findViewById(R.id.imgLoader);
//            RequestOptions requestOptions = new RequestOptions();
//            requestOptions.placeholder(R.drawable.ic_place_holder);
//            requestOptions.error(R.drawable.ic_place_holder);
          //  Glide.with(context).asGif().load(R.raw.ic_q)/*.apply(requestOptions)*/.into(imgLoader);
            dialog.show();
        } catch (Exception ignored) {
        }
    }

    public void dismiss() {
        try {
            if (dialog != null) {
                dialog.dismiss();
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public boolean isShowing() {
        if (dialog != null && dialog.isShowing())
            return true;
        else return false;
    }
}