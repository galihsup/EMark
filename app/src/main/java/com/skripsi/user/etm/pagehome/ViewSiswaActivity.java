package com.skripsi.user.etm.pagehome;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.skripsi.user.etm.AppsController;
import com.skripsi.user.etm.R;
import com.skripsi.user.etm.util.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewSiswaActivity extends AppCompatActivity {

    ImageView ivKeluar;
    FloatingActionButton fab;
    String id_siswa;
    ProgressDialog loading;
    CircleImageView civProfile;
    TextView tvNamaSiswa;
    TextInputEditText tieAlamat, tieAsal, tieJurusan,tieKelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_siswa);

        ivKeluar = (ImageView) findViewById(R.id.iv_view_exit);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        civProfile = (CircleImageView) findViewById(R.id.profile_image);
        tvNamaSiswa = (TextView) findViewById(R.id.tv_nama_siswa);
        tieAlamat = (TextInputEditText) findViewById(R.id.tv_view_add_alamat);
        tieAsal = (TextInputEditText) findViewById(R.id.tv_view_add_asalsekolah);
        tieJurusan = (TextInputEditText) findViewById(R.id.tv_view_add_jurusansekolah);
        tieKelas = (TextInputEditText) findViewById(R.id.tv_view_add_kelas);
        ivKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        id_siswa = intent.getStringExtra("id_siswa");
        Log.d("ids", id_siswa);
        getViewSiswa(id_siswa);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewSiswaActivity.this, EditSiswaActivity.class);
                intent.putExtra("ids", id_siswa);
                startActivity(intent);
            }
        });
    }

    public void getViewSiswa(String id) {
        loading = new ProgressDialog(this);
        loading.setMessage("Loading...");
        loading.show();
        String url = Constant.ENDPOINT_GET_SISWA_BY_ID+id;
        final SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        final String token = sharedPrefs.getString(Constant.KEY_SHAREDPREFS_TOKEN, null);
        if (token != null) {

            StringRequest req = new StringRequest(Request.Method.GET, url, successListener(), errListener()) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Authorization", "Bearer " + token);
                    return params;
                }
            };
            AppsController.getInstance().addToRequestQueue(req);
        }
    }

    private Response.ErrorListener errListener() {
        return new Response.ErrorListener() {
            @Override
            public  void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Log.e("Error", "Error get view booking");
                Log.e("Error", String.valueOf(error));
            }
        };
    }
    private Response.Listener<String> successListener(){
        return new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                loading.dismiss();
                try{
                    JSONObject json = new JSONObject(response);
                    String msg = json.getString("message");
                    if (json.getInt("status") == 200) {
                        String jsonOutput = json.getString("data");
                        Log.d("json_out", jsonOutput);
                        JSONObject data = new JSONObject(json.getString("data"));
                        Log.d("json_object", String.valueOf(data));
                        tvNamaSiswa.setText(data.getString("etm_nama_siswa").toString());
                        String nm = data.getString("etm_nama_siswa").toString();
                        String img = nm.substring(0,1);
                        img = img.toUpperCase();
                        tieAlamat.setText(data.getString("etm_alamat_siswa").toString());
                        tieKelas.setText(data.getString("etm_kelas_siswa").toString());
                        tieJurusan.setText(data.getString("etm_jurusan_sekolah").toString());
                        tieAsal.setText(data.getString("etm_asal_sekolah").toString());
                        isiPhoto(img);

                    } else {
                        Toast.makeText(ViewSiswaActivity.this,msg,Toast.LENGTH_LONG).show();
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public void isiPhoto(String img){
        if(img == "A"){
            Glide.with(ViewSiswaActivity.this)
                    .load(R.drawable.ic_a)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "B"){
            Glide.with(ViewSiswaActivity.this)
                    .load(R.drawable.ic_b)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "C"){
            Glide.with(ViewSiswaActivity.this)
                    .load(R.drawable.ic_c)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "D"){
            Glide.with(ViewSiswaActivity.this)
                    .load(R.drawable.ic_d)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "E"){
            Glide.with(ViewSiswaActivity.this)
                    .load(R.drawable.ic_e)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "F"){
            Glide.with(ViewSiswaActivity.this)
                    .load(R.drawable.ic_f)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "G"){
            Glide.with(ViewSiswaActivity.this)
                    .load(R.drawable.ic_g)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "H"){
            Glide.with(ViewSiswaActivity.this)
                    .load(R.drawable.ic_h)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "I"){
            Glide.with(ViewSiswaActivity.this)
                    .load(R.drawable.ic_i)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "J"){
            Glide.with(ViewSiswaActivity.this)
                    .load(R.drawable.ic_j)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "K"){
            Glide.with(ViewSiswaActivity.this)
                    .load(R.drawable.ic_k)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "L"){
            Glide.with(ViewSiswaActivity.this)
                    .load(R.drawable.ic_l)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "M"){
            Glide.with(ViewSiswaActivity.this)
                    .load(R.drawable.ic_m)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "N"){
            Glide.with(ViewSiswaActivity.this)
                    .load(R.drawable.ic_n)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "O"){
            Glide.with(ViewSiswaActivity.this)
                    .load(R.drawable.ic_o)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "P"){
            Glide.with(ViewSiswaActivity.this)
                    .load(R.drawable.ic_p)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "Q"){
            Glide.with(ViewSiswaActivity.this)
                    .load(R.drawable.ic_q)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "R"){
            Glide.with(ViewSiswaActivity.this)
                    .load(R.drawable.ic_r)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "S"){
            Glide.with(ViewSiswaActivity.this)
                    .load(R.drawable.ic_s)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "T"){
            Glide.with(ViewSiswaActivity.this)
                    .load(R.drawable.ic_t)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "U"){
            Glide.with(ViewSiswaActivity.this)
                    .load(R.drawable.ic_u)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "V"){
            Glide.with(ViewSiswaActivity.this)
                    .load(R.drawable.ic_v)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "W"){
            Glide.with(ViewSiswaActivity.this)
                    .load(R.drawable.ic_w)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "X"){
            Glide.with(ViewSiswaActivity.this)
                    .load(R.drawable.ic_x)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "Y"){
            Glide.with(ViewSiswaActivity.this)
                    .load(R.drawable.ic_y)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "Z"){
            Glide.with(ViewSiswaActivity.this)
                    .load(R.drawable.ic_z)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        }
    }
}
