package com.example.sulaeman.crud_rxretrofit.ui.lihat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sulaeman.crud_rxretrofit.R;
import com.example.sulaeman.crud_rxretrofit.adapter.MahasiswaAdapter;
import com.example.sulaeman.crud_rxretrofit.databinding.ActivityMainBinding;
import com.example.sulaeman.crud_rxretrofit.network.model.GetDataMahasiswaResponseJson;
import com.example.sulaeman.crud_rxretrofit.network.model.Mahasiswa;
import com.example.sulaeman.crud_rxretrofit.presenter.GetDataMahasiswaPresenter;
import com.example.sulaeman.crud_rxretrofit.presenter.GetDataSiswaView;
import com.example.sulaeman.crud_rxretrofit.ui.tambah.TambahDataActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements GetDataSiswaView {

    @BindView(R.id.list_data_siswa)
    RecyclerView rvSiswa;
    @BindView(R.id.fab_add_siswa)
    FloatingActionButton fabBtn;
    @BindView(R.id.progress_bar_show)
    ProgressBar progressBar;
    GetDataMahasiswaPresenter presenter;
    MahasiswaAdapter adapter;
    ActivityMainBinding binding;
    private List<Mahasiswa> mahasiswa;
    private String TAG = "MainPresenter";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ButterKnife.bind(this);

        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TambahDataActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        setUpMVP();
        setupViews();
        getSiswaList();
    }

    private void getSiswaList() {
        presenter.getSiswa();
    }

    private void setupViews() {
        rvSiswa.setHasFixedSize(true);
        rvSiswa.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }

    private void setUpMVP() {
        presenter = new GetDataMahasiswaPresenter(this);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onSuccessText(String status) {
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetDataSiswaAll(GetDataMahasiswaResponseJson responseJson) {
        if (responseJson!=null){
            Log.d(TAG, "onGetDataSiswaAll: "+responseJson.getMessage());
            adapter = new MahasiswaAdapter(MainActivity.this,responseJson.getMahasiswas());
            rvSiswa.setAdapter(adapter);
        }
    }


}