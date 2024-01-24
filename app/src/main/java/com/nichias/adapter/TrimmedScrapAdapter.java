package com.nichias.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nichias.R;
import com.nichias.model_api.TrimmedScrapApiResponse;

import java.util.List;

public class TrimmedScrapAdapter extends RecyclerView.Adapter<TrimmedScrapAdapter.ViewHolder> {
    private final Context context;
    private final List<TrimmedScrapApiResponse.Result> data;

    public TrimmedScrapAdapter(Context context, List<TrimmedScrapApiResponse.Result> data) {
        this.context = context;
        this.data = data;
    }


    @NonNull
    @Override
    public TrimmedScrapAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.adapter_trimmed_scrap, parent, false);

        return new TrimmedScrapAdapter.ViewHolder(itemView);
    }

    @SuppressLint({"SetTextI18n", "RtlHardcoded"})
    @Override
    public void onBindViewHolder(@NonNull final TrimmedScrapAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        TrimmedScrapApiResponse.Result datas = data.get(position);

        holder.edtOpBatch.setText(datas.getData2());

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtOpBatch,txtWeight;
        private final EditText edtOpBatch,edtWeight;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtOpBatch = itemView.findViewById(R.id.txtOpBatch);
            txtWeight = itemView.findViewById(R.id.txtWeight);
            edtOpBatch = itemView.findViewById(R.id.edtOpBatch);
            edtWeight = itemView.findViewById(R.id.edtWeight);
        }
    }

}