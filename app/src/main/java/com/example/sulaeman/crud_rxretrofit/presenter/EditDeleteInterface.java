package com.example.sulaeman.crud_rxretrofit.presenter;

public interface EditDeleteInterface {
    void getEditData(String id,String nama, String nim, String alamat, String phone);

    void  getDeleteData(String id);
}
