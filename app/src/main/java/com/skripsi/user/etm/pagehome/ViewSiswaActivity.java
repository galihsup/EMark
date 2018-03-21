package com.skripsi.user.etm.pagehome;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
import com.skripsi.user.etm.AppsController;
import com.skripsi.user.etm.R;
import com.skripsi.user.etm.util.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewSiswaActivity extends AppCompatActivity {

    ImageView ivKeluar,ivTlp, ivSms;
    FloatingActionButton fab;
    String id_siswa, jenis_kegiatan;
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
        ivTlp = (ImageView) findViewById(R.id.gambar_phone);
        ivSms = (ImageView) findViewById(R.id.gambar_msg);

        ivKeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        id_siswa = intent.getStringExtra("id_siswa");
        jenis_kegiatan = intent.getStringExtra("jenis_kegiatan");
        Log.d("ids", id_siswa);
        getViewSiswa(id_siswa);

        ivTlp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jenis_kegiatan.equals("Telemarketing") || jenis_kegiatan.equals("Siswa")) {
                    String phone = "089524786527";
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                    startActivity(intent);
                } else {

                }
            }
        });

        ivSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jenis_kegiatan.equals("SmsMarketing") || jenis_kegiatan.equals("Siswa")) {
                    /** Creating an intent to initiate view action */
                    Intent intent = new Intent("android.intent.action.VIEW");

                    /** creates an sms uri */
                    Uri data = Uri.parse("smsto:" + "089524786527");

                    /** Setting sms uri to the intent */
                    intent.setData(data);

                    /** Initiates the SMS compose screen, because the activity contain ACTION_VIEW and sms uri */
                    startActivity(intent);
                } else {

                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                if(jenis_kegiatan.equals("Siswa"))
                {
                    intent = new Intent(ViewSiswaActivity.this, EditSiswaActivity.class);
                    intent.putExtra("ids", id_siswa);
                } else if(jenis_kegiatan.equals("SmsMarketing")){
                    intent = new Intent(ViewSiswaActivity.this, EditSmsSiswaActivity.class);
                    intent.putExtra("ids", id_siswa);
                }
                else if(jenis_kegiatan.equals("Telemarketing")){
                    intent = new Intent(ViewSiswaActivity.this, EditTeleSiswaActivity.class);
                    intent.putExtra("ids", id_siswa);
                } else if(jenis_kegiatan.equals("Emailmarketing")){
                    intent = new Intent(ViewSiswaActivity.this, EditEmailSiswaActivity.class);
                    intent.putExtra("ids", id_siswa);
                }
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
                        Log.e("gambarlo",img);
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
        if(img.equals("A")){
            civProfile.setImageResource(R.drawable.ic_a);
        } else if(img.equals("B")){
            civProfile.setImageResource(R.drawable.ic_b);
        } else if(img.equals("C")){
            civProfile.setImageResource(R.drawable.ic_c);
        } else if(img.equals("D")){
            civProfile.setImageResource(R.drawable.ic_d);
        } else if(img.equals("E")){
            civProfile.setImageResource(R.drawable.ic_e);
        } else if(img.equals("F")){
            civProfile.setImageResource(R.drawable.ic_f);
        } else if(img.equals("G")){
            civProfile.setImageResource(R.drawable.ic_g);
        } else if(img.equals("H")){
            civProfile.setImageResource(R.drawable.ic_h);
        } else if(img.equals("I")){
            civProfile.setImageResource(R.drawable.ic_i);
        } else if(img.equals("J")){
            civProfile.setImageResource(R.drawable.ic_j);
        } else if(img.equals("K")){
            civProfile.setImageResource(R.drawable.ic_k);
        } else if(img.equals("L")){
            civProfile.setImageResource(R.drawable.ic_l);
        } else if(img.equals("M")){
            civProfile.setImageResource(R.drawable.ic_m);
        } else if(img.equals("N")){
            civProfile.setImageResource(R.drawable.ic_n);
        } else if(img.equals("O")){
            civProfile.setImageResource(R.drawable.ic_o);
        } else if(img.equals("P")){
            civProfile.setImageResource(R.drawable.ic_p);
        } else if(img.equals("Q")){
            civProfile.setImageResource(R.drawable.ic_q);
        } else if(img.equals("R")){
            civProfile.setImageResource(R.drawable.ic_r);
        } else if(img.equals("S")){
            civProfile.setImageResource(R.drawable.ic_s);
        } else if(img.equals("T")){
            civProfile.setImageResource(R.drawable.ic_t);
        } else if(img.equals("U")){
            civProfile.setImageResource(R.drawable.ic_u);
        } else if(img.equals("V")){
            civProfile.setImageResource(R.drawable.ic_v);
        } else if(img.equals("W")){
            civProfile.setImageResource(R.drawable.ic_w);
        } else if(img.equals("X")){
            civProfile.setImageResource(R.drawable.ic_x);
        } else if(img.equals("Y")){
            civProfile.setImageResource(R.drawable.ic_y);
        } else if(img.equals("Z")){
            civProfile.setImageResource(R.drawable.ic_z);
        }
    }
}
