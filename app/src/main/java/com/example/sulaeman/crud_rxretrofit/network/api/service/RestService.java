package com.example.sulaeman.crud_rxretrofit.network.api.service;

import com.example.sulaeman.crud_rxretrofit.network.model.BaseResponse;
import com.example.sulaeman.crud_rxretrofit.network.model.GetDataMahasiswaResponseJson;
import com.example.sulaeman.crud_rxretrofit.network.model.Mahasiswa;
import com.example.sulaeman.crud_rxretrofit.network.model.TambahSiswaRequestJson;
import com.example.sulaeman.crud_rxretrofit.network.model.UpdateDataRequestJson;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestService {

    @GET("siswa/datasiswa")
    Observable<GetDataMahasiswaResponseJson> getDataSiswa();

    @POST("siswa/tambah_siswa")
    Observable<BaseResponse> tambah_siswa (@Body TambahSiswaRequestJson siswaRequestJson);

    @PUT("siswa/update/{id_siswa}")
    Observable<BaseResponse> update_siswa (@Body UpdateDataRequestJson param, @Path("id_siswa") String id);

    @DELETE("siswa/delete/{id_siswa}")
    Observable<BaseResponse> delete_siswa (@Path("id_siswa") String id);

}
