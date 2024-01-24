package com.nichias.interfaces;


import com.nichias.model_api.BranchListApiResponse;

public interface BranchClick {
    void onClick(int position, BranchListApiResponse.Lists response);
    void onClickFav(int position);
}
