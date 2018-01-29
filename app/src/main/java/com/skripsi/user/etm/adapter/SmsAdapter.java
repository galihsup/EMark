package com.skripsi.user.etm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skripsi.user.etm.R;
import com.skripsi.user.etm.model.SmsModel;

import java.util.List;

/**
 * Created by USER on 1/22/2018.
 */

public class SmsAdapter extends RecyclerView.Adapter<SmsAdapter.ViewHolder> {

    private Context context;
    private List<SmsModel> smsModels;

    public SmsAdapter(Context context, List<SmsModel> smsModels) {
        this.context = context;
        this.smsModels = smsModels;
    }

    @Override
    public SmsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SmsAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_jadwal_sms, parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final SmsModel data = smsModels.get(position);
        holder.tvTanggal.setText(String.valueOf(data.getTanggal()));
        holder.tvKonten.setText(String.valueOf(data.getKonten()));
        holder.tvStatus.setText(String.valueOf(data.getStatus()));
    }

    @Override
    public int getItemCount(){
        return smsModels.size();
        //return 15;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTanggal;
        TextView tvKonten;
        TextView tvStatus;
        ViewHolder(View view){
            super(view);
            tvTanggal = (TextView) view.findViewById(R.id.tv_tgl_kirim);
            tvKonten = (TextView) view.findViewById(R.id.isi_konten);
            tvStatus = (TextView) view.findViewById(R.id.status_sms);
        }
    }
}
