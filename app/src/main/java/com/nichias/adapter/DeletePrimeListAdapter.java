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
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.nichias.R;
import com.nichias.api.CommonApiCalls;
import com.nichias.app_interfaces.CommonCallback;
import com.nichias.dummy_model.DeletePrimeDeleteApiResponse;
import com.nichias.dummy_model.DeletePrimeDeleteJson;
import com.nichias.dummy_model.DeletePrimeGetApiResponse;
import com.nichias.dummy_model.FgDispatchApiResponse;
import com.nichias.dummy_model.FgDispatchLoadApiResponse;
import com.nichias.dummy_model.FgDispatchLoadJson;
import com.nichias.utils.CommonFunctions;

import java.util.ArrayList;
import java.util.List;

public class DeletePrimeListAdapter extends RecyclerView.Adapter<DeletePrimeListAdapter.ViewHolder> {
    private final Context context;
    private final List<DeletePrimeGetApiResponse.Result> data;
    private final String workOrderNumber_;

    public DeletePrimeListAdapter(Context context, List<DeletePrimeGetApiResponse.Result> data,String workOrderNumber) {
        this.context = context;
        this.data = data;
        this.workOrderNumber_ = workOrderNumber;
    }


    @NonNull
    @Override
    public DeletePrimeListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.adapter_delete_prime_items, parent, false);
        return new DeletePrimeListAdapter.ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final DeletePrimeListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        DeletePrimeGetApiResponse.Result response = data.get(position);

        holder.txtSno.setText(position+1+"");
        holder.txtWrokOrderNo.setText(workOrderNumber_);
        holder.txtOpBatch.setText(response.getData2());

        holder.txtDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeletePrimeDeleteJson deletePrimeDeleteJson = new DeletePrimeDeleteJson();
                deletePrimeDeleteJson.setMethod("delete_prime");
                deletePrimeDeleteJson.setWo_num(workOrderNumber_);
                deletePrimeDeleteJson.setOpbatch(response.getData2());

                Gson gson = new Gson();
                String input = gson.toJson(deletePrimeDeleteJson);
                System.out.println("Input ==> " + input);

                CommonApiCalls.getInstance().deletePrimeDelete(context, input, new CommonCallback.Listener() {
                    @Override
                    public void onSuccess(Object body) {
                        DeletePrimeDeleteApiResponse apiResponse = (DeletePrimeDeleteApiResponse) body;
                        CommonFunctions.getInstance().successResponseToast(context, apiResponse.getMsg());

                        data.remove(position);
                        notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(String reason) {
                        CommonFunctions.getInstance().validationEmptyError(context, reason);
                    }
                });
            }
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout lyoutParent;
        private final TextView txtSno,txtDelete,txtWrokOrderNo,txtOpBatch;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            lyoutParent =  itemView.findViewById(R.id.lyoutParent);
            txtSno = itemView.findViewById(R.id.txtSno);
            txtDelete = itemView.findViewById(R.id.txtDelete);
            txtWrokOrderNo = itemView.findViewById(R.id.txtWrokOrderNo);
            txtOpBatch = itemView.findViewById(R.id.txtOpBatch);
        }
    }
}