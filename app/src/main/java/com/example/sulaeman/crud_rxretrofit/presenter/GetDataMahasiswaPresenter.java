package com.example.sulaeman.crud_rxretrofit.presenter;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.sulaeman.crud_rxretrofit.network.api.Restfactory;
import com.example.sulaeman.crud_rxretrofit.network.api.service.RestService;
import com.example.sulaeman.crud_rxretrofit.network.model.GetDataMahasiswaResponseJson;
import com.example.sulaeman.crud_rxretrofit.network.model.Mahasiswa;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class GetDataMahasiswaPresenter implements MainViewInterface {

    GetDataSiswaView view;
    private String TAG = "MainPresenter";

    public GetDataMahasiswaPresenter(GetDataSiswaView view) {
        this.view = view;
    }

    @Override
    public void getSiswa() {
        getObservable().subscribeWith(getObserver());
    }

    public Observable<GetDataMahasiswaResponseJson> getObservable(){
        return Restfactory.createService(RestService.class,"admin","admin")
                .getDataSiswa()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<GetDataMahasiswaResponseJson> getObserver(){
        view.showLoading();
        return new DisposableObserver<GetDataMahasiswaResponseJson>() {
            @Override
            public void onNext(@NonNull GetDataMahasiswaResponseJson responseJson) {
                Log.d(TAG, "onNext: "+responseJson.getMahasiswas());
                view.onGetDataSiswaAll(responseJson);
                view.onSuccessText(responseJson.getStatus());
                view.hideLoading();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "onError: "+e);
                e.printStackTrace();
                view.onError(e.getLocalizedMessage());
                view.showLoading();

            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
                view.hideLoading();
            }
        };
    }
}
