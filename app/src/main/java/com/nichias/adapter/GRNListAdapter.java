package com.nichias.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.nichias.R;
import com.nichias.databinding.AdapterRmGrnListBinding;
import com.nichias.model_api.GRNListApiResponse;

import java.util.List;

public class GRNListAdapter extends RecyclerView.Adapter<GRNListAdapter.MyViewHolder> {

    private final Context context;
    private final List<GRNListApiResponse.InwardArray> data;
    AdapterRmGrnListBinding binding;


    public GRNListAdapter(Context context, List<GRNListApiResponse.InwardArray> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public GRNListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_rm_grn_list, parent, false);
        return new GRNListAdapter.MyViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull GRNListAdapter.MyViewHolder myViewHolder, int position) {

        GRNListApiResponse.InwardArray values = data.get(position);
        myViewHolder.edtCustomerRMIN.setText(values.getCustomer());
        myViewHolder.edtPartNameRMIN.setText(values.getPartName());
        myViewHolder.edtPartNoRMIN.setText(values.getPartNo());
        myViewHolder.edtSerialCodeRMIN.setText(values.getSerialCode());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        EditText edtCustomerRMIN,edtPartNameRMIN,edtPartNoRMIN,edtSerialCodeRMIN;

        MyViewHolder(@NonNull AdapterRmGrnListBinding itemView) {
            super(itemView.getRoot());

            edtCustomerRMIN = itemView.edtCustomerRMIN;
            edtPartNameRMIN = itemView.edtPartNameRMIN;
            edtPartNoRMIN = itemView.edtPartNoRMIN;
            edtSerialCodeRMIN = itemView.edtSerialCodeRMIN;
        }
    }
}
