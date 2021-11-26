package com.example.sulaeman.crud_rxretrofit.presenter;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.sulaeman.crud_rxretrofit.network.api.Restfactory;
import com.example.sulaeman.crud_rxretrofit.network.api.service.RestService;
import com.example.sulaeman.crud_rxretrofit.network.model.BaseResponse;
import com.example.sulaeman.crud_rxretrofit.network.model.TambahSiswaRequestJson;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class AddDataMahasiswaPresenter implements AddDataInterface {

    AddDataView view;
    private String TAG = "MainActivity";

    public AddDataMahasiswaPresenter(AddDataView view) {
        this.view = view;
    }

    @Override
    public void getTambahData(String nama, String nim, String alamat, String phone) {
        getObserverl(nama, nim, alamat, phone).subscribeWith(getObserver());
    }

    public Observable<BaseResponse> getObserverl(String nama,String nim,String alamat, String phone){
        TambahSiswaRequestJson request = new TambahSiswaRequestJson();
        request.setNama(nama);
        request.setNim(nim);
        request.setAlamat(alamat);
        request.setPhone(phone);
        return Restfactory.createService(RestService.class,nama,nim)
                .tambah_siswa(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }


    public DisposableObserver<BaseResponse> getObserver(){
        return new DisposableObserver<BaseResponse>() {
            @Override
            public void onNext(@NonNull BaseResponse baseResponse) {
                if (baseResponse.getStatus().equalsIgnoreCase("success")){
                    view.onSuccessText(baseResponse.getMessage());
                    view.onHideLoading();
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                view.onErrorText(e.getLocalizedMessage());
                view.onShowLoading();
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
                view.onHideLoading();
            }
        };
    }
}
