package com.example.sulaeman.crud_rxretrofit.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sulaeman.crud_rxretrofit.R;
import com.example.sulaeman.crud_rxretrofit.network.model.Mahasiswa;
import com.example.sulaeman.crud_rxretrofit.ui.editdelete.EditDeleteDataActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.ItemRowHolder>
{
    private Context context;
    private List<Mahasiswa> datalist;

    public MahasiswaAdapter(Context context, List<Mahasiswa> datalist) {
        this.context = context;
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public ItemRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_data_mahasiswa,parent,false);
        return new ItemRowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaAdapter.ItemRowHolder holder, int position) {
        final Mahasiswa singleItem = datalist.get(position);
        holder.nama.setText(singleItem.getNama());
        holder.nim.setText(singleItem.getNim());
        holder.alamat.setText(singleItem.getAlamat());
        holder.phone.setText(singleItem.getPhone());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditDeleteDataActivity.class);
                intent.putExtra("id_siswa",singleItem.getId());
                intent.putExtra("nama_siswa",singleItem.getNama());
                intent.putExtra("nim_siswa",singleItem.getNim());
                intent.putExtra("alamat_siswa",singleItem.getAlamat());
                intent.putExtra("phone_siswa",singleItem.getPhone());
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    static class ItemRowHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.nama_siswa_row)
        TextView nama;
        @BindView(R.id.nim_siswa_row)
        TextView nim;
        @BindView(R.id.alamat_siswa_row)
        TextView alamat;
        @BindView(R.id.klik_line)
        LinearLayout linearLayout;
        @BindView(R.id.phone_siswa_row)
        TextView phone;
        public ItemRowHolder(@NonNull  View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
