package com.nichias.interfaces;

public interface OrderClick {
    void onClickViewOrder(String key);
    void onClickReOrder(String key,String branch_key,String branch_availability_status);
    void onClickRating(String key);
    void onClickCancel(String key);
}
