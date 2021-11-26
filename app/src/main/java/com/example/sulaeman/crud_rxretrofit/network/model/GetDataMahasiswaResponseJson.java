package com.example.sulaeman.crud_rxretrofit.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GetDataMahasiswaResponseJson {
    @SerializedName("siswa_data")
    @Expose
    private List<Mahasiswa> mahasiswas = null;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;


    public List<Mahasiswa> getMahasiswas() {
        return mahasiswas;
    }

    public void setMahasiswas(List<Mahasiswa> mahasiswas) {
        this.mahasiswas = mahasiswas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
