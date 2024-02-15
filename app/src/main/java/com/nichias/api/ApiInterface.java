package com.nichias.api;

import com.nichias.dummy_model.AddPrimeApiResponse;
import com.nichias.dummy_model.BusinessTypeListApiResponse;
import com.nichias.dummy_model.DeletePrimeDeleteApiResponse;
import com.nichias.dummy_model.DeletePrimeGetApiResponse;
import com.nichias.dummy_model.FGDataCheckApiResponse;
import com.nichias.dummy_model.FgDispatchApiResponse;
import com.nichias.dummy_model.FgDispatchLoadApiResponse;
import com.nichias.dummy_model.OPBatchDetailsApiResponse;
import com.nichias.dummy_model.OPBatchPrintApiResponse;
import com.nichias.dummy_model.OpBatchListApiResponse;
import com.nichias.dummy_model.PokayokeCheck;
import com.nichias.dummy_model.PrintingListApiResponse;
import com.nichias.dummy_model.QueryApiResponse;
import com.nichias.model_api.FGDataApiResponse;
import com.nichias.model_api.GRN2ListApiResponse;
import com.nichias.model_api.GRNListApiResponse;
import com.nichias.model_api.GrnProcessCheckApiResponse;
import com.nichias.model_api.GrnProcessPrintBarCodeApiResponse;
import com.nichias.model_api.HrsSlittingApiResponse;
import com.nichias.model_api.InFGResponse;
import com.nichias.model_api.InInspectionResponse;
import com.nichias.model_api.InspectionApiResponse;
import com.nichias.model_api.JobOrderPickupApiResponse;
import com.nichias.model_api.JobOrdersApiResponse;
import com.nichias.model_api.LoginApiResponse;
import com.nichias.model_api.OperatorSwapApiResponse;
import com.nichias.model_api.OutFGResponse;
import com.nichias.model_api.PokayokeCheckApiResponse;
import com.nichias.model_api.PrimaryPackingApiResponse;
import com.nichias.model_api.PrintRMGateInventoryApiResponse;
import com.nichias.model_api.RMInventoryApiResponse;
import com.nichias.model_api.RMInwardApiResponse;
import com.nichias.model_api.RMInwardsOrdersApiResponse;
import com.nichias.model_api.RMLocationEntryApiResponse;
import com.nichias.model_api.RMStorageApiResponse;
import com.nichias.model_api.ReprintApiResponse;
import com.nichias.model_api.RollbackEndCutWorkOrder;
import com.nichias.model_api.RoolbackEndcutApiResponse;
import com.nichias.model_api.SalesReturnCheckApiResponse;
import com.nichias.model_api.SalesReturnPrintBarCodeApiResponse;
import com.nichias.model_api.SecondaryPackingApiResponse;
import com.nichias.model_api.SsiplIDApiResponse;
import com.nichias.model_api.TrimmedScrapApiResponse;
import com.nichias.model_api.TrimmedScrapPostApiResponse;
import com.nichias.model_api.WIPStorageApiResponse;
import com.nichias.model_api.WipApiResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;


public interface ApiInterface {
// ---- Login
    @FormUrlEncoded
    @POST(Urls.LOGIN)
    Call<LoginApiResponse> login(@Field("mobile_number") String mobile_number,
                                 @Field("device_token") String device_token,
                                 @Field("device_type_id") String device_type_id);



/*
// ---- RM_INVENTORY
    @POST(Urls.RM_INVENTORY)
    Call<RMInventoryApiResponse> rm_inventory(@Body RequestBody body);
*/

    // ----RM_INVENTORY
    @FormUrlEncoded
    @POST(Urls.RM_INVENTORY)
    Call<RMInventoryApiResponse> rm_inventory(@Field("data") String data);



    // ----SSIPL_ID
    @FormUrlEncoded
    @POST(Urls.SSIPL_ID)
    Call<SsiplIDApiResponse> ssipl_id(@Field("data") String data);

    // ----RM_STORAGE
    @FormUrlEncoded
    @POST(Urls.RM_STORAGE)
    Call<RMStorageApiResponse> rm_storage(@Field("data") String data);

    // ----GRN Process CHECK
    @FormUrlEncoded
    @POST(Urls.GRN_PROCESS)
    Call<GrnProcessCheckApiResponse> grn_process(@Field("data") String data);

    // ----GRN Process Print Bar Code
    @FormUrlEncoded
    @POST(Urls.GRN_PROCESS)
    Call<GrnProcessPrintBarCodeApiResponse> grn_process_print_bar_code(@Field("data") String data);



    // ----HRS Slitting
    @FormUrlEncoded
    @POST(Urls.HRS_SLITTING)
    Call<HrsSlittingApiResponse> hrs_slitting(@Field("data") String data);


    // ----FG_DATA
    @FormUrlEncoded
    @POST(Urls.FG_DATA)
    Call<FGDataApiResponse> fg_data(@Field("data") String data);


    // ----Sales Return CHECK
    @FormUrlEncoded
    @POST(Urls.SALES_RETURN)
    Call<SalesReturnCheckApiResponse> sales_return(@Field("data") String data);


    // ----GRN Sales Return Print Bar Code
    @FormUrlEncoded
    @POST(Urls.SALES_RETURN)
    Call<SalesReturnPrintBarCodeApiResponse> sales_return_print_bar_code(@Field("data") String data);


    // ----GET Data Trimmed Scrap
    @FormUrlEncoded
    @POST(Urls.TRIMMED_SCRAP)
    Call<TrimmedScrapApiResponse> trimmed_scrap(@Field("data") String data);


    // ----POST Roolback_Endcut
    @FormUrlEncoded
    @POST(Urls.ROOLBACK_ENDCUT)
    Call<RoolbackEndcutApiResponse> roolback_endcut(@Field("data") String data);


    // ----POST Data Trimmed Scrap
    @FormUrlEncoded
    @POST(Urls.TRIMMED_SCRAP)
    Call<TrimmedScrapPostApiResponse> post_trimmed_scrap(@Field("data") String data);



    // ----POST Rollback End Cut Work Order
    @FormUrlEncoded
    @POST(Urls.ROOLBACK_ENDCUT)
    Call<RollbackEndCutWorkOrder> rollback_end_cut(@Field("data") String data);


    // ----POST RE-PRINT RM_Inward
    @FormUrlEncoded
    @POST(Urls.REPRINT)
    Call<ReprintApiResponse> reprint(@Field("data") String data);


    // ----POST RE-PRINT Wip
    @FormUrlEncoded
    @POST(Urls.REPRINT)
    Call<WipApiResponse> wip(@Field("data") String data);


    // ----WIP_STORAGE
    @FormUrlEncoded
    @POST(Urls.WIP_STORAGE)
    Call<WIPStorageApiResponse> wip_storage(@Field("data") String data);



    // ----FG_DATA Check
    @FormUrlEncoded
    @POST(Urls.FG_DATA)
    Call<FGDataCheckApiResponse> fg_data_check(@Field("data") String data);


    // ----POST DISPATCH
    @FormUrlEncoded
    @POST(Urls.DISPATCH)
    Call<FgDispatchApiResponse> fg_dispatch(@Field("data") String data);


    // ----POST DISPATCH
    @FormUrlEncoded
    @POST(Urls.DISPATCH)
    Call<FgDispatchLoadApiResponse> fg_dispatch_load(@Field("data") String data);


    // ----POST QUERY
    @FormUrlEncoded
    @POST(Urls.QUERY)
    Call<QueryApiResponse> query(@Field("data") String data);

    // ----ADD_PRIME
    @FormUrlEncoded
    @POST(Urls.ADD_PRIME)
    Call<AddPrimeApiResponse> add_prime(@Field("data") String data);


    // ----POST Delete Prime GET
    @FormUrlEncoded
    @POST(Urls.DELETE_PRIME_GET)
    Call<DeletePrimeGetApiResponse> delete_prime_get(@Field("data") String data);

    // ----POST Delete Prime DELETE Call
    @FormUrlEncoded
    @POST(Urls.DELETE_PRIME_DELETE)
    Call<DeletePrimeDeleteApiResponse> delete_prime_delete(@Field("data") String data);

    // ----GET OP Batch List
    @FormUrlEncoded
    @POST(Urls.PRINT)
    Call<OpBatchListApiResponse> get_op_batch(@Field("data") String data);

    // ----GET Printing List
    @FormUrlEncoded
    @POST(Urls.PRINT)
    Call<PrintingListApiResponse> get_printing(@Field("data") String data);


    // ----GET Business Type List
    @FormUrlEncoded
    @POST(Urls.PRINT)
    Call<BusinessTypeListApiResponse> get_business_type(@Field("data") String data);


    // ----GET OP BATCH CORRECTION
    @FormUrlEncoded
    @POST(Urls.PRINT)
    Call<OPBatchDetailsApiResponse> get_opbatch_detail(@Field("data") String data);


    // ----GET OP BATCH CORRECTION PRINT
    @FormUrlEncoded
    @POST(Urls.PRINT)
    Call<OPBatchPrintApiResponse> get_opbatch_print(@Field("data") String data);



    // ----POST RM Inventory
    @FormUrlEncoded
    @POST(Urls.RMS)
    Call<RMInventoryApiResponse> rms_inventory(@Field("data") String data);

    // ----POST RM Location Entry
    @FormUrlEncoded
    @POST(Urls.RMS)
    Call<RMLocationEntryApiResponse> rms_location(@Field("data") String data);

    // ----POST RM Inwards Orders List
    @FormUrlEncoded
    @POST(Urls.RMS_IO)
    Call<RMInwardsOrdersApiResponse> rm_inwards(@Field("data") String data);


    // ---- POST Job Order Pickup
    @FormUrlEncoded
    @POST(Urls.RMS)
    Call<JobOrderPickupApiResponse> job_order_pickup(@Field("data") String data);



    // ----POST Job Orders List
    @FormUrlEncoded
    @POST(Urls.RMS_IO)
    Call<JobOrdersApiResponse> job_orders(@Field("data") String data);


    // ----POST  Poka Yoke Check
    @FormUrlEncoded
    @POST(Urls.RMS)
    Call<PokayokeCheckApiResponse> poka_yoke_check(@Field("data") String data);


    // ----POST  Operator Swap
    @FormUrlEncoded
    @POST(Urls.RMS)
    Call<OperatorSwapApiResponse> operator_swap(@Field("data") String data);




    // ----Print RM Gate INVENTORY
    @FormUrlEncoded
    @POST(Urls.PRINT_RM_GATE_INVENTORY)
    Call<PrintRMGateInventoryApiResponse> print_rmgate_inventory(@Field("data") String data);





    // ----RM Inward
    @FormUrlEncoded
    @POST(Urls.PRINT_RM_GATE_INVENTORY)
    Call<RMInwardApiResponse> rm_inward(@Field("data") String data);




    // ----GRN Get List
    @FormUrlEncoded
    @POST(Urls.RMS_IO)
    Call<GRNListApiResponse> get_grn_list(@Field("data") String data);



    // ----GRN 2 process POST  List
    @FormUrlEncoded
    @POST(Urls.RMS)
    Call<GRN2ListApiResponse> grn_2list(@Field("data") String data);


    // ----Inspection
    @FormUrlEncoded
    @POST(Urls.RMS)
    Call<InspectionApiResponse> inspection(@Field("data") String data);



    // ----Inspection
    @FormUrlEncoded
    @POST(Urls.RMS)
    Call<PrimaryPackingApiResponse> primary_packing(@Field("data") String data);


    // ----Secondary Packing
    @FormUrlEncoded
    @POST(Urls.RMS)
    Call<SecondaryPackingApiResponse> secondary_packing(@Field("data") String data);

    // ----In Inspection
    @FormUrlEncoded
    @POST(Urls.RMS)
    Call<InInspectionResponse> remnant_inspection_in(@Field("data") String data);

    // ----In FG
    @FormUrlEncoded
    @POST(Urls.RMS)
    Call<InFGResponse> remnant_fg_in(@Field("data") String data);

    // ----OUT FG
    @FormUrlEncoded
    @POST(Urls.RMS)
    Call<OutFGResponse> remnant_fg_out(@Field("data") String data);


}
