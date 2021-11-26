package com.example.sulaeman.crud_rxretrofit.presenter;

import com.example.sulaeman.crud_rxretrofit.network.model.GetDataMahasiswaResponseJson;
import com.example.sulaeman.crud_rxretrofit.network.model.Mahasiswa;

import java.util.List;

public interface GetDataSiswaView {
    void showLoading();
    void hideLoading();
    void onSuccessText(String status);
    void onError(String error);
    void onGetDataSiswaAll(GetDataMahasiswaResponseJson responseJson);
}
