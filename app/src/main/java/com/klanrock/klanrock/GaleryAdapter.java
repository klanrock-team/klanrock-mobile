package com.klanrock.klanrock;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
/**
 * Created by fendrik on 20/05/2018.
 */



import java.util.List;
public class GaleryAdapter extends RecyclerView.Adapter<GaleryAdapter.MyViewHolder> {

    private Context mContext;
    private List<Paket> paketList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, count;
        public ImageView thumbnail;
        public Button btn_pesan;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            btn_pesan = (Button) view.findViewById(R.id.btn_pesan);
        }
    }


    public GaleryAdapter(Context mContext, List<Paket> paketList) {
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
        Paket paket = paketList.get(position);
        holder.title.setText(paket.getName());
        holder.count.setText("Rp. " + paket.getPrice());
        holder.btn_pesan.setText(paket.getBtn());
        // loading paket cover using Glide library
        Glide.with(mContext).load(paket.getThumbnail()).into(holder.thumbnail);

    }


    @Override
    public int getItemCount() {
        return paketList.size();
    }
}
