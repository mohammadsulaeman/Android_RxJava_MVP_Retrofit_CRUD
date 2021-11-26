package com.example.sulaeman.crud_rxretrofit.presenter;

import com.example.sulaeman.crud_rxretrofit.network.model.TambahSiswaRequestJson;

public interface AddDataInterface {
    void getTambahData(String nama, String nim,String alamat, String phoe);
}
