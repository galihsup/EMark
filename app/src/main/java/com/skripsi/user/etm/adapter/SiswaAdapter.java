package com.skripsi.user.etm.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skripsi.user.etm.R;
import com.skripsi.user.etm.model.SiswaModel;
import com.skripsi.user.etm.pagehome.FastScrollRecyclerViewInterface;
import com.skripsi.user.etm.pagehome.ViewSiswaActivity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by USER on 1/16/2018.
 */

public class SiswaAdapter extends RecyclerView.Adapter<SiswaAdapter.ViewHolder> implements FastScrollRecyclerViewInterface {
    private Context context;
    private List<SiswaModel> siswaModels;
    private HashMap<String, Integer> mMapIndex;

    public SiswaAdapter(Context context, List<SiswaModel> siswaModels, HashMap<String, Integer> mapIndex){
        this.context = context;
        this.siswaModels = siswaModels;
        this.mMapIndex = mapIndex;
        Log.d("map", String.valueOf(mapIndex));
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_siswa, parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        final SiswaModel data = siswaModels.get(position);
        holder.tvNama.setText(String.valueOf(data.getNama()));
        holder.tvNama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SiswaAdapter.this.context, ViewSiswaActivity.class);
                intent.putExtra("id_siswa", String.valueOf(data.getId()));
                Log.e("id_siswa", String.valueOf(data.getId()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount(){
        return siswaModels.size();
        //return 15;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvNama;
        ViewHolder(View view){
            super(view);
            tvNama = (TextView) view.findViewById(R.id.txtNama);
        }
    }

    @Override
    public HashMap<String, Integer> getMapIndex() {
        return this.mMapIndex;
    }
}
