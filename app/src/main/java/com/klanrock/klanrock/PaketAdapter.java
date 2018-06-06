package com.klanrock.klanrock;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class PaketAdapter extends RecyclerView.Adapter<PaketAdapter.MyViewHolder> {

    private Context mContext;
    private List<Paket> paketList;
    Locale localeID = new Locale("in","ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count,kategori;
        public ImageView thumbnail;
        public Button btn_pesan;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            btn_pesan = (Button) view.findViewById(R.id.btn_pesan);
            kategori = (TextView) view.findViewById(R.id.kategori);

        }
    }


    public PaketAdapter(Context mContext, List<Paket> paketList) {
        this.mContext = mContext;
        this.paketList = paketList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_paket, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        final Paket paket = paketList.get(position);
        holder.title.setText(paket.getName());
        holder.count.setText(formatRupiah.format(paket.getPrice()));
        holder.btn_pesan.setText(paket.getBtn());
        // loading paket cover using Glide library
        Glide.with(mContext).load(paket.getThumbnail()).into(holder.thumbnail);
        holder.btn_pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent open_detail = new Intent(mContext,DetailOrderActivity.class);
                v.getContext().startActivity(open_detail);
            }
        });
        holder.kategori.setText(paket.getKategori());
    }


    @Override
    public int getItemCount() {
        return paketList.size();
    }
}
