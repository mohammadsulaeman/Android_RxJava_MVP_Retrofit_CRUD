package com.example.sulaeman.crud_rxretrofit.ui.editdelete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.sulaeman.crud_rxretrofit.R;
import com.example.sulaeman.crud_rxretrofit.databinding.ActivityEditDeleteDataBinding;
import com.example.sulaeman.crud_rxretrofit.presenter.EditDeleteDataMahasiswaPresenter;
import com.example.sulaeman.crud_rxretrofit.presenter.EditDeleteView;
import com.example.sulaeman.crud_rxretrofit.ui.lihat.MainActivity;

public class EditDeleteDataActivity extends AppCompatActivity implements EditDeleteView {

    EditDeleteDataMahasiswaPresenter presenter;
    ActivityEditDeleteDataBinding binding;
    String idsiswa,namasiswa,nimsiswa,alamatsiswa,phonesiswa;
    private String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditDeleteDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        idsiswa = intent.getStringExtra("id_siswa");
        namasiswa = intent.getStringExtra("nama_siswa");
        nimsiswa = intent.getStringExtra("nim_siswa");
        alamatsiswa = intent.getStringExtra("alamat_siswa");
        phonesiswa = intent.getStringExtra("phone_siswa");
        binding.namaSiswaEdit.setText(namasiswa);
        binding.nimSiswaEdit.setText(nimsiswa);
        binding.alamatSiswaEdit.setText(alamatsiswa);
        binding.phoneSiswaEdit.setText(phonesiswa);
        presenter = new EditDeleteDataMahasiswaPresenter(this);
        binding.imgBackEditelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditDeleteDataActivity.this, MainActivity.class));
                finish();
            }
        });
        binding.btnEditSiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = binding.namaSiswaEdit.getText().toString();
                String nim = binding.nimSiswaEdit.getText().toString();
                String alamat = binding.alamatSiswaEdit.getText().toString();
                String phone = binding.phoneSiswaEdit.getText().toString();
                if (TextUtils.isEmpty(nama)){
                    Toast.makeText(EditDeleteDataActivity.this, getString(R.string.namaEmpty), Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(nim)){
                    Toast.makeText(EditDeleteDataActivity.this, getString(R.string.nimEmpty), Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(alamat)){
                    Toast.makeText(EditDeleteDataActivity.this, getString(R.string.alamatEmpty), Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(phone)){
                    Toast.makeText(EditDeleteDataActivity.this, getString(R.string.phoneEmpty), Toast.LENGTH_SHORT).show();
                }else{
                    presenter.getEditData(idsiswa,nama,nim,alamat,phone);
                    if (presenter !=null){
                        Intent main = new Intent(EditDeleteDataActivity.this,MainActivity.class);
                        main.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(main);
                    }else{
                        Toast.makeText(EditDeleteDataActivity.this, "data tidak berhasil di edit", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        binding.btnDeleteSiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getDeleteData(idsiswa);
                if (presenter !=null){
                    Intent del = new Intent(EditDeleteDataActivity.this,MainActivity.class);
                    del.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(del);
                }else{
                    Toast.makeText(EditDeleteDataActivity.this, "data tidak berhasil di hapus", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void showLoading() {
        binding.progressBarEditDelete.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        binding.progressBarEditDelete.setVisibility(View.GONE);
    }

    @Override
    public void onSuccessLog(String status) {
        Log.d(TAG, "onSuccessLog: "+status);
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErrorLog(String error) {
        Log.e(TAG, "onErrorLog: "+error);
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}