package com.example.sulaeman.crud_rxretrofit.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TambahSiswaRequestJson {

    @SerializedName("id_siswa")
    @Expose
    private String id;

    @SerializedName("nama_siswa")
    @Expose
    private String nama;

    @SerializedName("nim_siswa")
    @Expose
    private String nim;

    @SerializedName("alamat_siswa")
    @Expose
    private String alamat;

    @SerializedName("phone_siswa")
    @Expose
    private String phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

