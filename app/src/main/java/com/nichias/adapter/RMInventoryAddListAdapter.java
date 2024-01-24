package com.nichias.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import com.nichias.databinding.AdapterRmInventoryAddListBinding;
import com.nichias.dummy_model.InwardArrayResponse;
import com.nichias.interfaces.ListRemove;
import com.nichias.utils.CommonFunctions;

import java.util.List;

public class RMInventoryAddListAdapter extends RecyclerView.Adapter<RMInventoryAddListAdapter.MyViewHolder> {

    private final Context context;
    private final List<InwardArrayResponse.InwardArray> data;
    private final ListRemove listRemove;
    AdapterRmInventoryAddListBinding binding;

    public RMInventoryAddListAdapter(Context context, List<InwardArrayResponse.InwardArray> data, ListRemove listRemove) {
        this.context = context;
        this.data = data;
        this.listRemove = listRemove;
    }

    @NonNull
    @Override
    public RMInventoryAddListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_rm_inventory_add_list, parent, false);
        return new RMInventoryAddListAdapter.MyViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RMInventoryAddListAdapter.MyViewHolder myViewHolder, int position) {

        InwardArrayResponse.InwardArray values = data.get(position);
        myViewHolder.edtCustomerRMIN.setText(values.getCustomer());
        myViewHolder.edtPartNameRMIN.setText(values.getPartName());
        myViewHolder.edtPartNoRMIN.setText(values.getPartNo());
        myViewHolder.edtSerialCodeRMIN.setText(values.getSerialCode());

        myViewHolder.imgAddLineClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_alert_pop_rm);
                dialog.setCancelable(false);

                TextView tvTitle=  dialog.findViewById(R.id.tvTitle);
                TextView tvQue=  dialog.findViewById(R.id.tvQue);
                TextView txtNo=  dialog.findViewById(R.id.txtNo);
                TextView txtYes=  dialog.findViewById(R.id.txtYes);
                EditText edtName =  dialog.findViewById(R.id.edtName);

                tvQue.setText("Are you sure you want to Remove ?");
                txtYes.setText("Yes");
                tvTitle.setVisibility(View.GONE);
                edtName.setVisibility(View.GONE);

                txtNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                txtYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        data.remove(position);
                        listRemove.onClick();
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
        myViewHolder.edtCustomerRMIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog(data.get(position),"customer");
            }
        });
        myViewHolder.edtPartNameRMIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog(data.get(position),"partname");
            }
        });



    }

    private void alertDialog(InwardArrayResponse.InwardArray inwardArray, String from) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_alert_pop_rm);
        dialog.setCancelable(false);

        TextView tvTitle=  dialog.findViewById(R.id.tvTitle);
        TextView tvQue=  dialog.findViewById(R.id.tvQue);
        TextView txtNo=  dialog.findViewById(R.id.txtNo);
        TextView txtYes=  dialog.findViewById(R.id.txtYes);
        EditText edtName =  dialog.findViewById(R.id.edtName);

        if (from.equals("customer")){
            tvTitle.setText("Customer Name");
        }
        else if (from.equals("partname")){
            tvTitle.setText("Part Name");
        }

        tvQue.setText("Are you sure you want to Edit ?");
        txtYes.setText("Yes");
        edtName.setVisibility(View.GONE);

        txtNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        txtYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (txtYes.getText().toString().trim().equals("Yes")){
                   tvQue.setVisibility(View.GONE);
                   edtName.setVisibility(View.VISIBLE);
                   txtYes.setText("Submit");
               }
               else {
                   if (from.equals("customer")){
                       inwardArray.setCustomer(edtName.getText().toString().trim());
                   }
                   else if (from.equals("partname")){
                       inwardArray.setPartName(edtName.getText().toString().trim());
                   }
                   CommonFunctions.getInstance().hideSoftKeyboardNew((Activity) context);
                   dialog.dismiss();
                   notifyDataSetChanged();
               }
            }
        });

        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        window.setAttributes(wlp);
        dialog.show();

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        EditText edtCustomerRMIN,edtPartNameRMIN,edtPartNoRMIN,edtSerialCodeRMIN;
        ImageView imgAddLineClose;

        MyViewHolder(@NonNull AdapterRmInventoryAddListBinding itemView) {
            super(itemView.getRoot());

            edtCustomerRMIN = itemView.edtCustomerRMIN;
            edtPartNameRMIN = itemView.edtPartNameRMIN;
            edtPartNoRMIN = itemView.edtPartNoRMIN;
            edtSerialCodeRMIN = itemView.edtSerialCodeRMIN;
            imgAddLineClose = itemView.imgAddLineClose;
        }
    }
}
