package com.skripsi.user.etm.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.skripsi.user.etm.AppsController;
import com.skripsi.user.etm.R;
import com.skripsi.user.etm.model.JadwalModel;
import com.skripsi.user.etm.util.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by USER on 2/28/2018.
 */

public class JadwalAdapter extends RecyclerView.Adapter<JadwalAdapter.ViewHolder> {
    private Context context;
    private List<JadwalModel> jadwalModels;
    ProgressDialog loading;
    int idjadwal;
    String idus="0";

    public JadwalAdapter(Context context, List<JadwalModel> jadwalModel) {
        this.context = context;
        this.jadwalModels = jadwalModel;
    }

    @Override
    public JadwalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new JadwalAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_jadwal_sms, parent,false));
    }

    @Override
    public void onBindViewHolder(JadwalAdapter.ViewHolder holder, int position) {
        final JadwalModel data = jadwalModels.get(position);
        holder.tvTanggal.setText(String.valueOf(data.getTanggal()));
        holder.tvKonten.setText(String.valueOf(data.getKonten()));
        //holder.tvStatus.setText(String.valueOf(data.getStatus()));
        String sts = String.valueOf(data.getStatus());
        idjadwal = data.getId();
        Log.e("status", sts);
        if(sts.equals("Dikirim")){
            holder.ivEdit.setImageResource(R.drawable.ic_check_green);
        } else {
            holder.ivEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);

                    builder.setTitle("Komfirmasi");
                    builder.setMessage("Apakah yakin sudah melakukan kegiatan ini?");

                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            // Do nothing but close the dialog
                            //Toast.makeText(context, "berhasil", Toast.LENGTH_LONG).show();
                            Log.e("idjadwal", String.valueOf(idjadwal));
                            prosesEdit(idjadwal);
                            dialog.dismiss();
                        }
                    });

                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Do nothing
                            Toast.makeText(context, "gagal", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();
                }
            });
        }
    }

    @Override
    public int getItemCount(){
        return jadwalModels.size();
        //return 15;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTanggal;
        TextView tvKonten;
        TextView tvStatus;
        ImageView ivEdit;
        ViewHolder(View view){
            super(view);
            tvTanggal = (TextView) view.findViewById(R.id.tv_tgl_kirim);
            tvKonten = (TextView) view.findViewById(R.id.isi_konten);
            tvStatus = (TextView) view.findViewById(R.id.status_sms);
            ivEdit = (ImageView) view.findViewById(R.id.iv_edit_jadwal);
        }
    }

    public void prosesEdit(int id){
        final SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        String getUserData = sharedPrefs.getString(Constant.KEY_SHAREDPREFS_USER_DATA, null);
        if (getUserData != null) {
            JSONObject json = null;
            try {
                json = new JSONObject(getUserData);
                idus = json.getString("id");
                Log.d("idus_dus", idus);
                //getDataPosisi(id);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Log.d("idjadwalbaru", String.valueOf(id));
        loading = new ProgressDialog(context);
        loading.setMessage("Loading...");
        loading.show();
        String urlHal = Constant.ENDPOINT_EDIT_JADWAL+id;
        Log.d("link ni: ", urlHal);
        StringRequest req = new StringRequest(Request.Method.POST, urlHal, listenerSuccess(), listenerError()){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("status", "Dikirim");
                params.put("iduser", idus);
                Log.d("Parameter",params.toString());
                return params;
            }
        };
        AppsController.getInstance().addToRequestQueue(req);
    }

    private Response.ErrorListener listenerError() {
        return new Response.ErrorListener() {
            @Override
            public  void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Log.e("Error", "Edit Data");
                Log.e("Error", String.valueOf(error));
                Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();
            }
        };
    }

    private Response.Listener<String> listenerSuccess() {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                try{
                    Log.d("response", response);
                    JSONObject json = new JSONObject(response);
                    String message = json.getString("message");
                    String status = json.getString("status");
                    Log.d("message", message);
                    if (status.equals("200"))
                    {
                        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
    }

}
