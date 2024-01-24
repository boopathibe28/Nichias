package com.nichias.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
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
import com.nichias.activity.HelpViewActivity;
import com.nichias.databinding.AdapterHelpListBinding;
import com.nichias.model_api.SettingsApiResponse;
import com.nichias.utils.CommonFunctions;
import com.nichias.utils._pref.SessionManager;

import java.util.List;

import static com.nichias.utils._pref.SharedPrefConstants.LANGUAGE_CODE;

public class HelpListAdapter extends RecyclerView.Adapter<HelpListAdapter.MyViewHolder> {

    private final Context context;
    private final List<SettingsApiResponse.Datum> data;
    AdapterHelpListBinding binding;

    public HelpListAdapter(Context context, List<SettingsApiResponse.Datum> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public HelpListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.adapter_help_list, parent, false);
        return new HelpListAdapter.MyViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HelpListAdapter.MyViewHolder myViewHolder, int position) {

        SettingsApiResponse.Datum listApiResponse = data.get(position);

        if (SessionManager.getInstance().getFromPreference(LANGUAGE_CODE).equals("ar")) {
            binding.txtViewName.setGravity(Gravity.RIGHT|Gravity.CENTER);
            binding.imgArrow.setImageResource(R.drawable.ic_help_next_ar);
        } else {
            binding.txtViewName.setGravity(Gravity.LEFT|Gravity.CENTER);
            binding.imgArrow.setImageResource(R.drawable.ic_help_next);
        }

        myViewHolder.txtViewName.setText(listApiResponse.getCms_title());

// ---- List Parent Click
        myViewHolder.llyParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("cms_title", listApiResponse.getCms_title());
                bundle.putString("cms_url", listApiResponse.getCms_url());
                CommonFunctions.getInstance().newIntent(context, HelpViewActivity.class, bundle, false,false);
            }
        });

    }



    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtViewName;
        LinearLayout llyParent;
        ImageView imgArrow;

        MyViewHolder(@NonNull AdapterHelpListBinding itemView) {
            super(itemView.getRoot());

            txtViewName = itemView.txtViewName;
            llyParent = itemView.llyParent;
            imgArrow = itemView.imgArrow;
        }
    }
}