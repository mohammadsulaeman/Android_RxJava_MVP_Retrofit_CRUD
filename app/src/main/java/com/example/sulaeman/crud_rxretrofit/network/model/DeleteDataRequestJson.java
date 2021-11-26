package com.example.sulaeman.crud_rxretrofit.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeleteDataRequestJson {
    @SerializedName("id_siswa")
    @Expose
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
