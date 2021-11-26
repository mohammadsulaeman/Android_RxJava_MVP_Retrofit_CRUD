package com.example.sulaeman.crud_rxretrofit.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.sulaeman.crud_rxretrofit.network.api.Restfactory;
import com.example.sulaeman.crud_rxretrofit.network.api.service.RestService;
import com.example.sulaeman.crud_rxretrofit.network.model.BaseResponse;
import com.example.sulaeman.crud_rxretrofit.network.model.DeleteDataRequestJson;
import com.example.sulaeman.crud_rxretrofit.network.model.UpdateDataRequestJson;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class EditDeleteDataMahasiswaPresenter implements EditDeleteInterface{

    private EditDeleteView view;
    private String TAG = "MainActivity";

    public EditDeleteDataMahasiswaPresenter(EditDeleteView view) {
        this.view = view;
    }

    @Override
    public void getEditData(String id, String nama, String nim, String alamat, String phone) {
        getObserverEdit(id, nama, nim, alamat, phone).subscribeWith(getObserverEditData());
    }

    @Override
    public void getDeleteData(String id) {
        getObserverDelete(id).subscribeWith(getObservalDelete());
    }

    public Observable<BaseResponse> getObserverEdit(String id, String nama, String nim, String alamat, String phone){
        UpdateDataRequestJson request = new UpdateDataRequestJson();
        request.setNama(nama);
        request.setNim(nim);
        request.setAlamat(alamat);
        request.setPhone(phone);

        return Restfactory.createService(RestService.class,nama,nim)
                .update_siswa(request,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<BaseResponse> getObserverEditData(){
        return new DisposableObserver<BaseResponse>() {
            @Override
            public void onNext(@NonNull BaseResponse baseResponse) {
                if (baseResponse.getStatus().equalsIgnoreCase("success")){
                    view.onSuccessLog(baseResponse.getMessage());
                }else{
                    view.onErrorLog(baseResponse.getMessage());
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                view.onErrorLog(e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: Data Berhasil Di Tambah");
            }
        };
    }

    //delete data
    public Observable<BaseResponse> getObserverDelete(String id){
        return Restfactory.createService(RestService.class,id,id)
                .delete_siswa(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public DisposableObserver<BaseResponse> getObservalDelete(){
        return new DisposableObserver<BaseResponse>() {
            @Override
            public void onNext(@NonNull BaseResponse baseResponse) {
                if (baseResponse.getStatus().equalsIgnoreCase("status")){
                    view.onSuccessLog(baseResponse.getMessage());
                }else
                {
                    view.onErrorLog(baseResponse.getMessage());
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e(TAG, "onError: "+e.getLocalizedMessage());
                e.printStackTrace();
                view.onErrorLog(e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: Data Berhasil Di Edit");
            }
        };
    }
}
