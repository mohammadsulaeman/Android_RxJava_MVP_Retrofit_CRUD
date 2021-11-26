package com.example.sulaeman.crud_rxretrofit.presenter;

public interface AddDataView {
    void onSuccessText(String msg);
    void onErrorText(String msg);
    void onShowLoading();
    void onHideLoading();
}
