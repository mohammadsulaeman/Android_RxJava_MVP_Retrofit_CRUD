package com.example.sulaeman.crud_rxretrofit.presenter;

public interface EditDeleteView {
    void showLoading();
    void hideLoading();
    void onSuccessLog(String status);
    void onErrorLog(String error);
}
