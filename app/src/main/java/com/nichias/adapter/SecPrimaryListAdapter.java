package com.nichias.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.nichias.R;
import com.nichias.databinding.AdapterRmGrnListBinding;
import com.nichias.databinding.AdapterSecPrimarylistBinding;
import com.nichias.dummy_model.InwardArrayJson;

import java.util.List;

public class SecPrimaryListAdapter extends RecyclerView.Adapter<SecPrimaryListAdapter.MyViewHolder> {
    AdapterSecPrimarylistBinding binding;
    private final Context context;
    private final List<InwardArrayJson.InwardArray> data;


    public SecPrimaryListAdapter(Context context, List<InwardArrayJson.InwardArray> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public SecPrimaryListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_sec_primarylist, parent, false);
        return new SecPrimaryListAdapter.MyViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull SecPrimaryListAdapter.MyViewHolder myViewHolder, int position) {

        InwardArrayJson.InwardArray values = data.get(position);

        myViewHolder.edtSno.setText(values.getSl_No());
        myViewHolder.edtPartNo.setText(values.getPartNo());
        myViewHolder.edtSerialCode.setText(values.getSerialCode());

        myViewHolder.imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_alert_pop_sec_primary);
                dialog.setCancelable(false);

                TextView tvQue=  dialog.findViewById(R.id.tvQue);
                TextView txtNo=  dialog.findViewById(R.id.txtNo);
                TextView txtYes=  dialog.findViewById(R.id.txtYes);

                txtNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                txtYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        data.remove(position);
                        dialog.dismiss();
                        notifyDataSetChanged();
                    }
                });

                Window window = dialog.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();
                wlp.gravity = Gravity.CENTER;
                window.setAttributes(wlp);
                dialog.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        EditText edtSno,edtPartNo,edtSerialCode;
        ImageView imgCancel;

        MyViewHolder(@NonNull AdapterSecPrimarylistBinding itemView) {
            super(itemView.getRoot());

            edtSno = itemView.edtSno;
            edtPartNo = itemView.edtPartNo;
            edtSerialCode = itemView.edtSerialCode;
            imgCancel = itemView.imgCancel;
        }
    }
}
