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
import com.skripsi.user.etm.model.EmailModel;
import com.skripsi.user.etm.pagehome.FastScrollRecyclerViewInterface;
import com.skripsi.user.etm.pagehome.ViewSiswaActivity;

import java.util.HashMap;
import java.util.List;

/**
 * Created by USER on 2/20/2018.
 */

public class EmailAdapter extends RecyclerView.Adapter<EmailAdapter.ViewHolder> implements FastScrollRecyclerViewInterface {

    private Context context;
    private List<EmailModel> emailModels;
    private HashMap<String, Integer> mMapIndex;

    public EmailAdapter(Context context, List<EmailModel> emailModels, HashMap<String, Integer> mapIndex) {
        this.context = context;
        this.emailModels = emailModels;
        this.mMapIndex = mapIndex;
        Log.d("map", String.valueOf(mapIndex));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EmailAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_emailmarketing, parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final EmailModel data = emailModels.get(position);
        holder.tvNama.setText(String.valueOf(data.getNama()));
        holder.tvNama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmailAdapter.this.context, ViewSiswaActivity.class);
                intent.putExtra("id_siswa", String.valueOf(data.getIds()));
                intent.putExtra("jenis_kegiatan", "Emailmarketing");
                Log.e("id_siswa", String.valueOf(data.getIds()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return emailModels.size();
    }

    @Override
    public HashMap<String, Integer> getMapIndex() {
        return this.mMapIndex;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama;
        public ViewHolder(View itemView) {
            super(itemView);
            tvNama = (TextView) itemView.findViewById(R.id.emailNama);
        }
    }
}
