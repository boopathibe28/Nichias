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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.nichias.R;
import com.nichias.activity.FGDispatchNew;
import com.nichias.api.CommonApiCalls;
import com.nichias.app_interfaces.CommonCallback;
import com.nichias.dummy_model.FgDispatchApiResponse;
import com.nichias.dummy_model.FgDispatchLoadApiResponse;
import com.nichias.dummy_model.FgDispatchLoadJson;
import com.nichias.utils.CommonFunctions;

import java.util.ArrayList;
import java.util.List;

public class LineItemListAdapter extends RecyclerView.Adapter<LineItemListAdapter.ViewHolder> {
    private final Context context;
    private final List<FgDispatchApiResponse.Result> data;

    public LineItemListAdapter(Context context, List<FgDispatchApiResponse.Result> data) {
        this.context = context;
        this.data = data;
    }


    @NonNull
    @Override
    public LineItemListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.inflate_line_item, parent, false);
        return new LineItemListAdapter.ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final LineItemListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        FgDispatchApiResponse.Result response = data.get(position);

        holder.txtSno.setText(position+1+"");
        holder.txtDocNo.setText(CommonFunctions.DOC_NO);
        holder.txtBatchNum.setText(response.getBatch_No());
        holder.txtFGBatchLocation.setText(response.getFG_Locaton());
        holder.txtQuantity.setText(response.getQuantity());
        holder.txtBarCodeBatchNo.setText(response.getBarcode_Batchno());
        holder.txtBarCodeQty.setText(response.getBarcode_Qty());
        holder.txtThick.setText(response.getThick());
        holder.txtWidth.setText(response.getWidth());
        holder.txtLength1.setText(response.getLength());
        holder.txtGrade.setText(response.getGrade());

        holder.imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FgDispatchLoadJson fgDispatchLoadJson = new FgDispatchLoadJson();
                fgDispatchLoadJson.setMethod("update");
                fgDispatchLoadJson.setDocno(CommonFunctions.DOC_NO);

                List<FgDispatchLoadJson.Value> valueList = new ArrayList<>();

                FgDispatchLoadJson.Value datas = new FgDispatchLoadJson.Value();
                datas.setBatch(data.get(position).getBatch_No());
                datas.setBarcode_batch("null");
                datas.setBarcode_qty("0");
                valueList.add(datas);
                fgDispatchLoadJson.setValues(valueList);

                Gson gson = new Gson();
                String input = gson.toJson(fgDispatchLoadJson);
                System.out.println("Input ==> " + input);

                CommonApiCalls.getInstance().fgDispatchLoad(context, input, new CommonCallback.Listener() {
                    @Override
                    public void onSuccess(Object body) {
                        FgDispatchLoadApiResponse apiResponse = (FgDispatchLoadApiResponse) body;
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
        private final TextView txtSno,txtDocNo,txtBatchNum,txtFGBatchLocation,txtQuantity,txtBarCodeBatchNo,
                txtBarCodeQty,txtThick,txtWidth,txtLength1,txtGrade;
        private final ImageView imgCancel;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            lyoutParent =  itemView.findViewById(R.id.lyoutParent);
            imgCancel = itemView.findViewById(R.id.imgCancel);
            txtSno = itemView.findViewById(R.id.txtSno);
            txtDocNo = itemView.findViewById(R.id.txtDocNo);
            txtBatchNum = itemView.findViewById(R.id.txtBatchNum);
            txtFGBatchLocation = itemView.findViewById(R.id.txtFGBatchLocation);
            txtQuantity = itemView.findViewById(R.id.txtQuantity);
            txtBarCodeBatchNo = itemView.findViewById(R.id.txtBarCodeBatchNo);
            txtBarCodeQty = itemView.findViewById(R.id.txtBarCodeQty);
            txtThick = itemView.findViewById(R.id.txtThick);
            txtWidth = itemView.findViewById(R.id.txtWidth);
            txtLength1 = itemView.findViewById(R.id.txtLength1);
            txtGrade = itemView.findViewById(R.id.txtGrade);
        }
    }
}