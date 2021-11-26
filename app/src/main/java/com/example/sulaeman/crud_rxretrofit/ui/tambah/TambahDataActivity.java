package com.example.sulaeman.crud_rxretrofit.ui.tambah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.sulaeman.crud_rxretrofit.R;
import com.example.sulaeman.crud_rxretrofit.databinding.ActivityTambahDataBinding;
import com.example.sulaeman.crud_rxretrofit.presenter.AddDataMahasiswaPresenter;
import com.example.sulaeman.crud_rxretrofit.presenter.AddDataView;
import com.example.sulaeman.crud_rxretrofit.ui.lihat.MainActivity;

public class TambahDataActivity extends AppCompatActivity implements AddDataView {

    AddDataMahasiswaPresenter presenter;
    ActivityTambahDataBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTambahDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.imgBackAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(TambahDataActivity.this,MainActivity.class);
                main.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
            }
        });
        presenter = new AddDataMahasiswaPresenter(this);
        binding.btnAddSiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = binding.namaSiswaAdd.getText().toString();
                String nim = binding.nimSiswaAdd.getText().toString();
                String alamat = binding.alamatSiswaAdd.getText().toString();
                String phone = binding.phoneSiswaAdd.getText().toString();
                if (TextUtils.isEmpty(binding.namaSiswaAdd.getText().toString())){
                    Toast.makeText(TambahDataActivity.this, getString(R.string.namaEmpty), Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(binding.nimSiswaAdd.getText().toString())){
                    Toast.makeText(TambahDataActivity.this, getString(R.string.nimEmpty), Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(binding.alamatSiswaAdd.getText().toString())){
                    Toast.makeText(TambahDataActivity.this, getString(R.string.alamatEmpty), Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(binding.phoneSiswaAdd.getText().toString())){
                    Toast.makeText(TambahDataActivity.this, getString(R.string.phoneEmpty), Toast.LENGTH_SHORT).show();
                }else{
                    presenter.getTambahData(nama,nim,alamat,phone);
                    if (presenter !=null){
                        Intent main = new Intent(TambahDataActivity.this, MainActivity.class);
                        main.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(main);
                    }else{
                        Toast.makeText(TambahDataActivity.this, "data gagal di tambah", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void onSuccessText(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErrorText(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onShowLoading() {
        binding.progressBarAdd.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHideLoading() {
        binding.progressBarAdd.setVisibility(View.GONE);
    }
}