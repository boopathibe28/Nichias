package com.nichias.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.nichias.R;
import com.nichias.activity.OpBatchListAdapter;
import com.nichias.databinding.AdapterBusinessTypeListBinding;
import com.nichias.databinding.AdapterOpBatchListBinding;
import com.nichias.dummy_model.BusinessTypeListApiResponse;
import com.nichias.interfaces.BusinessType;
import com.nichias.interfaces.OpBatchPrint;

import java.util.List;

public class BusinessTypeListAdapter extends RecyclerView.Adapter<BusinessTypeListAdapter.MyViewHolder> {

    private final Context context;
    private final List<BusinessTypeListApiResponse.BusinessType> data;
    AdapterBusinessTypeListBinding binding;
    BusinessType businessType;

    public BusinessTypeListAdapter(Context context, List<BusinessTypeListApiResponse.BusinessType> data, BusinessType businessType) {
        this.context = context;
        this.data = data;
        this.businessType = businessType;
    }

    @NonNull
    @Override
    public BusinessTypeListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_business_type_list, parent, false);
        return new BusinessTypeListAdapter.MyViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull BusinessTypeListAdapter.MyViewHolder myViewHolder, int position) {

        BusinessTypeListApiResponse.BusinessType type = data.get(position);
        myViewHolder.txtName.setText(type.getTypes());

        myViewHolder.txtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                businessType.onClick(type.getTypes());
            }
        });

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;

        MyViewHolder(@NonNull AdapterBusinessTypeListBinding itemView) {
            super(itemView.getRoot());

            txtName = itemView.txtName;
        }
    }
}