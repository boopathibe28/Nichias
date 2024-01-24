package com.nichias.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.nichias.R;


public class ConnectionErrorDialog {
    private static final ConnectionErrorDialog ourInstance = new ConnectionErrorDialog();

    public static ConnectionErrorDialog getInstance() {
        return ourInstance;
    }

    private ConnectionErrorDialog() {
    }

    public void showDialog(Context context) {

        // custom dialog
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_connection_error);
        dialog.setTitle("");
        dialog.setCancelable(false);

        // set the custom dialog components - text, image and button
        TextView dialogButtonOK = dialog.findViewById(R.id.dialogButtonOK);


        dialogButtonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                ((AppCompatActivity)context).finishAffinity();
                System.exit(0);

            }
        });

        if(!((Activity) context).isFinishing())
        {
            dialog.show();
        }



    }
}