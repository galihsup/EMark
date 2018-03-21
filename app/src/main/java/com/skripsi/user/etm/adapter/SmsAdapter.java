package com.skripsi.user.etm.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skripsi.user.etm.R;
import com.skripsi.user.etm.model.SmsModel;
import com.skripsi.user.etm.pagehome.FastScrollRecyclerViewInterface;
import com.skripsi.user.etm.pagehome.ViewSiswaActivity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by USER on 1/22/2018.
 */

public class SmsAdapter extends RecyclerView.Adapter<SmsAdapter.ViewHolder> implements FastScrollRecyclerViewInterface {

    private Context context;
    private List<SmsModel> smsModels;
    private HashMap<String, Integer> mMapIndex;
    ProgressDialog loading;
    int idjadwal;

    public SmsAdapter(Context context, List<SmsModel> smsModels, HashMap<String, Integer> mapIndex) {
        this.context = context;
        this.smsModels = smsModels;
        this.mMapIndex = mapIndex;
        Log.d("map", String.valueOf(mapIndex));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SmsAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sms, parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final SmsModel data = smsModels.get(position);
        holder.tvNama.setText(String.valueOf(data.getNama()));
        holder.tvNama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SmsAdapter.this.context, ViewSiswaActivity.class);
                intent.putExtra("id_siswa", String.valueOf(data.getIds()));
                intent.putExtra("jenis_kegiatan", "SmsMarketing");
                Log.e("id_siswa", String.valueOf(data.getIds()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount(){
        return smsModels.size();
        //return 15;
    }

    @Override
    public HashMap<String, Integer> getMapIndex() {
        return this.mMapIndex;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNama;
        public ViewHolder(View itemView) {
            super(itemView);
            tvNama = (TextView) itemView.findViewById(R.id.smsNama);
        }
    }
}
