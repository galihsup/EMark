package com.skripsi.user.etm.pagehome;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditEmailSiswaActivity extends AppCompatActivity {

    private String id_siswa;
    ProgressDialog loading;
    ImageView ivExit;
    ImageView ivSave;
    CircleImageView civProfile;
    TextInputEditText etNama,etEmail,etAsal,etJurusan,etJur,etPtn,etPts,etRespon1,etRespon2,etRespon3;
    String ambilTanggal="";
    private SharedPreferences.Editor editor;
    SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_email_siswa);

        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPrefs.edit();

        ivExit = (ImageView) findViewById(R.id.iv_edit_email_exit);
        ivSave = (ImageView) findViewById(R.id.iv_edit_email_save);
        ivSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prosesEdit();
            }
        });
        ivExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        etNama = (TextInputEditText) findViewById(R.id.tv_email_editname);
        etEmail = (TextInputEditText) findViewById(R.id.tv_email_editemail);
        etAsal = (TextInputEditText) findViewById(R.id.tv_email_editasalsekolah);
        etJurusan = (TextInputEditText) findViewById(R.id.tv_email_editjurusansekolah);
        etJur = (TextInputEditText) findViewById(R.id.tv_email_editpilih_jurusan);
        etPtn = (TextInputEditText) findViewById(R.id.tv_email_editpilih_ptn);
        etPts = (TextInputEditText) findViewById(R.id.tv_email_editpilih_pts);
        etRespon1 = (TextInputEditText) findViewById(R.id.tv_email_editrespon1);
        etRespon2 = (TextInputEditText) findViewById(R.id.tv_email_editrespon2);
        etRespon3 = (TextInputEditText) findViewById(R.id.tv_email_editrespon3);
        civProfile = (CircleImageView) findViewById(R.id.edprofile_email_image);

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        ambilTanggal = df.format(new Date());

        Intent intent = getIntent();
        id_siswa = intent.getStringExtra("ids");
        Log.d("idsiswa", id_siswa);
        getViewSiswa(id_siswa);
    }

    public void getViewSiswa(String id) {
        loading = new ProgressDialog(this);
        loading.setMessage("Loading...");
        loading.show();
        String url = Constant.ENDPOINT_GETDATA_EMAILMARKETING+id;
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
                        editor.putString(Constant.KEY_SHAREDPREFS_USER_DATA, json.getString("data"));
                        Log.d("json_object", String.valueOf(data));
                        etNama.setText(data.getString("etm_nama_siswa").toString());
                        String nm = data.getString("etm_nama_siswa").toString();
                        String img = nm.substring(0,1);
                        img = img.toUpperCase();
                        etJurusan.setText(data.getString("etm_jurusan_sekolah").toString());
                        etAsal.setText(data.getString("etm_asal_sekolah").toString());
                        etEmail.setText(data.getString("etm_email_siswa").toString());
                        etJur.setText(data.getString("etm_jurusan_kuliah").toString());
                        etPtn.setText(data.getString("etm_ptn_pilihan").toString());
                        etPts.setText(data.getString("etm_pts_pilihan").toString());
                        etRespon1.setText(data.getString("etm_respon_1").toString());
                        etRespon2.setText(data.getString("etm_respon_2").toString());
                        etRespon3.setText(data.getString("etm_respon_3").toString());
                        isiPhoto(img);
                    } else {
                        Toast.makeText(EditEmailSiswaActivity.this,msg,Toast.LENGTH_LONG).show();
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

    public void prosesEdit(){
        loading = new ProgressDialog(this);
        loading.setMessage("Loading...");
        loading.show();
        String urlHal = Constant.ENDPOINT_EDIT_EMAIL_SISWA+id_siswa;
        Log.d("link ni: ", urlHal);
        StringRequest req = new StringRequest(Request.Method.POST, urlHal, listenerSuccess(), listenerError()){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", etNama.getText().toString());
                Log.d("nama : ", etNama.getText().toString());
                params.put("email", etEmail.getText().toString());
                params.put("asal", etAsal.getText().toString());
                params.put("jurusan", etJurusan.getText().toString());
                params.put("jur", etJur.getText().toString());
                params.put("ptn", etPtn.getText().toString());
                params.put("pts", etPts.getText().toString());
                params.put("respon1", etRespon1.getText().toString());
                params.put("respon2", etRespon2.getText().toString());
                params.put("respon3", etRespon3.getText().toString());
                params.put("tgl", ambilTanggal.toString());
                if(etRespon1.getText().toString().equals("") || etRespon2.getText().toString().equals("")
                        || etRespon3.getText().toString().equals("")){
                    params.put("status", "Respon");
                } else {
                    params.put("status", "");
                }
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
                Toast.makeText(EditEmailSiswaActivity.this,error.toString(),Toast.LENGTH_LONG).show();
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
                        Toast.makeText(EditEmailSiswaActivity.this,message,Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(EditEmailSiswaActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
