package com.nichias.app_interfaces;


public class CommonCallback {
    public interface Listener {
        public void onSuccess(Object body);
        public void onFailure(String reason);
    }

    private Listener listener;
}
