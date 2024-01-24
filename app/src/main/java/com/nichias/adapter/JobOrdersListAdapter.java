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
import com.nichias.model_api.JobOrdersApiResponse;

import java.util.List;

public class JobOrdersListAdapter extends RecyclerView.Adapter<JobOrdersListAdapter.ViewHolder> {
    private final Context context;
    private final List<JobOrdersApiResponse.Data> data;

    public JobOrdersListAdapter(Context context, List<JobOrdersApiResponse.Data> data) {
        this.context = context;
        this.data = data;
    }


    @NonNull
    @Override
    public JobOrdersListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.adapter_job_orders, parent, false);
        return new JobOrdersListAdapter.ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final JobOrdersListAdapter.ViewHolder holder,
                                 @SuppressLint("RecyclerView") int position) {
        JobOrdersApiResponse.Data datas = data.get(position);

        holder.txtSno.setText((position+1)+"");
        holder.txtRollNo.setText(datas.getRollNo());
        holder.txtLotNo.setText(datas.getLotNo());
        holder.txtSize.setText(datas.getSize());
        holder.txtSerialCode.setText(datas.getSerialCode());
        holder.txtPartName.setText(datas.getPartName());
        holder.txtMaterialName.setText(datas.getMaterialName());
        holder.txtMachineName.setText(datas.getMachineName());
        holder.txtRMRackNo.setText(datas.getRMRackNo());

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout lyoutParent;
        private final TextView txtSno,txtRollNo,txtLotNo,txtSize,txtSerialCode,txtPartName,txtMaterialName,
                txtMachineName,txtRMRackNo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            lyoutParent =  itemView.findViewById(R.id.lyoutParent);
            txtSno = itemView.findViewById(R.id.txtSno);
            txtRollNo = itemView.findViewById(R.id.txtRollNo);
            txtLotNo = itemView.findViewById(R.id.txtLotNo);
            txtSize = itemView.findViewById(R.id.txtSize);
            txtSerialCode = itemView.findViewById(R.id.txtSerialCode);
            txtPartName = itemView.findViewById(R.id.txtPartName);
            txtMaterialName = itemView.findViewById(R.id.txtMaterialName);
            txtMachineName = itemView.findViewById(R.id.txtMachineName);
            txtRMRackNo = itemView.findViewById(R.id.txtRMRackNo);
        }
    }
}