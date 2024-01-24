package com.nichias.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.nichias.R;


public class NoInternetConnectionDialog {
    private static final NoInternetConnectionDialog ourInstance = new NoInternetConnectionDialog();

    public static NoInternetConnectionDialog getInstance() {
        return ourInstance;
    }

    private NoInternetConnectionDialog() {
    }

    public void showDialog(Context context) {
        if (CommonFunctions.getInstance().isActivityRunning(context)) {
            // custom dialog
            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.dialog_no_internet);
            dialog.setTitle("");
            dialog.setCancelable(false);

            // set the custom dialog components - text, image and button
            TextView dialogButtonOK = dialog.findViewById(R.id.dialogButtonOK);
            TextView dialogButtonCancel = dialog.findViewById(R.id.dialogButtonCancel);


            dialogButtonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            dialogButtonOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            dialog.show();
        }
    }
}