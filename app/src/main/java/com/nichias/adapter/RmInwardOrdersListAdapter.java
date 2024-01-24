package com.nichias.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nichias.R;
import com.nichias.model_api.RMInwardsOrdersApiResponse;

import java.util.List;

public class RmInwardOrdersListAdapter extends RecyclerView.Adapter<RmInwardOrdersListAdapter.ViewHolder> {
    private final Context context;
    private final List<RMInwardsOrdersApiResponse.Data> data;

    public RmInwardOrdersListAdapter(Context context, List<RMInwardsOrdersApiResponse.Data> data) {
        this.context = context;
        this.data = data;
    }


    @NonNull
    @Override
    public RmInwardOrdersListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.adapter_rm_inward_orders, parent, false);
        return new RmInwardOrdersListAdapter.ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final RmInwardOrdersListAdapter.ViewHolder holder,
                                 @SuppressLint("RecyclerView") int position) {
        RMInwardsOrdersApiResponse.Data datas = data.get(position);
        holder.txtSno.setText((position+1)+"");
        holder.txtRollNo.setText(datas.getRollNo());
        holder.txtInwardDate.setText(datas.getInwardDate());
        holder.txtQuantity.setText(datas.getQuality());

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout lyoutParent;
        private final TextView txtSno,txtRollNo,txtInwardDate,txtQuantity;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            lyoutParent =  itemView.findViewById(R.id.lyoutParent);
            txtSno = itemView.findViewById(R.id.txtSno);
            txtRollNo = itemView.findViewById(R.id.txtRollNo);
            txtInwardDate = itemView.findViewById(R.id.txtInwardDate);
            txtQuantity = itemView.findViewById(R.id.txtQuantity);
        }
    }
}