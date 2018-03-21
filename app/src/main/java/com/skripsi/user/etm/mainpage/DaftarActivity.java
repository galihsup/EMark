package com.skripsi.user.etm.mainpage;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DaftarActivity extends AppCompatActivity {

    TextView tvMore;
    RelativeLayout rlMore,rlJurusan,rlKelas,rlPilihJurusan,rlPilihPtn,rlPilihPts,rlFindTrilogi,rlMengetahui;
    RelativeLayout rlPetugas,rlTanggal;
    ImageView ivExit,ivSave;
    View vwMore;
    RadioGroup rdCek;
    RadioButton rdYa;
    RadioButton rdTidak;
    ProgressDialog pd;
    boolean flag = false;

    TextInputEditText etNama,etNohp,etEmail,etAlamat,etKode,etAsal,etJurusan,etJur,etKelas,etPtn,etPts,etTanggal,etPwd;
    CheckBox cbPameran,cbEdufair,cbPresentasi,cbSosmed,cbWebsite,cbGuru,cbTeman,cbSms,cbPoster,cbSurat,cbOrangtua,
            cbMajalah,cbRadio,cbSaudara,cbLainya;

    String deskripsi = "", ambilTanggal="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        tvMore = (TextView) findViewById(R.id.tv_daftar_more_fields);
        rlMore = (RelativeLayout)findViewById(R.id.rl_daftar_more_fields);
        rlJurusan = (RelativeLayout)findViewById(R.id.rl_jurusan);
        rlKelas = (RelativeLayout)findViewById(R.id.rl_kelas);
        rlPilihJurusan = (RelativeLayout)findViewById(R.id.rl_pilih_jurusan);
        rlPilihJurusan = (RelativeLayout)findViewById(R.id.rl_pilih_jurusan);
        rlPilihPtn = (RelativeLayout)findViewById(R.id.rl_pilih_ptn);
        rlPilihPts = (RelativeLayout)findViewById(R.id.rl_pilih_pts);
        rlFindTrilogi = (RelativeLayout)findViewById(R.id.rl_find_trilogi);
        rlMengetahui = (RelativeLayout)findViewById(R.id.rl_mengetahui);
        rlPetugas = (RelativeLayout)findViewById(R.id.rl_petugas);
        rlTanggal = (RelativeLayout)findViewById(R.id.rl_tanggal);
        vwMore = (View) findViewById(R.id.vw_more);
        ivExit = (ImageView) findViewById(R.id.iv_daftar_exit);
        ivSave = (ImageView) findViewById(R.id.iv_daftar_save);
        rdCek = (RadioGroup) findViewById(R.id.radio_cek);
        rdYa = (RadioButton) findViewById(R.id.rd_ya);
        rdTidak = (RadioButton) findViewById(R.id.rd_tidak);

        etNama = (TextInputEditText) findViewById(R.id.tv_daftar_add_name);
        etNohp = (TextInputEditText) findViewById(R.id.tv_daftar_add_phone);
        etEmail = (TextInputEditText) findViewById(R.id.tv_daftar_add_email);
        etPwd = (TextInputEditText) findViewById(R.id.tv_daftar_add_password);
        etAlamat = (TextInputEditText) findViewById(R.id.tv_daftar_add_alamat);
        etKode = (TextInputEditText) findViewById(R.id.tv_daftar_add_kodepos);
        etAsal = (TextInputEditText) findViewById(R.id.tv_daftar_add_asalsekolah);
        etKelas = (TextInputEditText) findViewById(R.id.tv_daftar_add_kelas);
        etJurusan = (TextInputEditText) findViewById(R.id.tv_daftar_add_jurusansekolah);
        etJur = (TextInputEditText) findViewById(R.id.tv_daftar_add_pilih_jurusan);
        etPtn = (TextInputEditText) findViewById(R.id.tv_daftar_add_pilih_ptn);
        etPts = (TextInputEditText) findViewById(R.id.tv_daftar_add_pilih_pts);
        etTanggal = (TextInputEditText) findViewById(R.id.tv_daftar_add_tanggal);
        cbPameran = (CheckBox) findViewById(R.id.pameran);
        cbEdufair = (CheckBox) findViewById(R.id.edufair);
        cbPresentasi = (CheckBox) findViewById(R.id.presentasi);
        cbSosmed = (CheckBox) findViewById(R.id.sosmed);
        cbWebsite = (CheckBox) findViewById(R.id.website);
        cbGuru = (CheckBox) findViewById(R.id.guru);
        cbTeman = (CheckBox) findViewById(R.id.teman);
        cbSms = (CheckBox) findViewById(R.id.sms);
        cbPoster = (CheckBox) findViewById(R.id.poster);
        cbSurat = (CheckBox) findViewById(R.id.surat_kabar);
        cbOrangtua = (CheckBox) findViewById(R.id.orangtua);
        cbMajalah = (CheckBox) findViewById(R.id.majalah);
        cbRadio = (CheckBox) findViewById(R.id.cb_radio);
        cbSaudara = (CheckBox) findViewById(R.id.saudara);
        cbLainya = (CheckBox) findViewById(R.id.lain_lain);

        tvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlMore.setVisibility(View.VISIBLE);
                rlJurusan.setVisibility(View.VISIBLE);
                rlKelas.setVisibility(View.VISIBLE);
                rlPilihJurusan.setVisibility(View.VISIBLE);
                rlPilihPtn.setVisibility(View.VISIBLE);
                rlPilihPts.setVisibility(View.VISIBLE);
                rlFindTrilogi.setVisibility(View.VISIBLE);
                vwMore.setVisibility(View.VISIBLE);
                tvMore.setVisibility(View.GONE);
            }
        });
        ivExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ivSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deskripsi = ambil_cb();
                simpanData();
            }
        });

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        ambilTanggal = df.format(new Date());

        etTanggal.setText(ambilTanggal);
    }

    public String ambil_cb(){
        String out = "";
        if(cbPameran.isChecked()){
            if(out == ""){
                out += cbPameran.getText().toString() + ",";
            } else {
                out += ","+cbPameran.getText().toString();
            }
        }
        if(cbEdufair.isChecked()){
            if(out == ""){
                out += cbEdufair.getText().toString() + ",";
            } else {
                out += ","+cbEdufair.getText().toString();
            }
        }
        if(cbPresentasi.isChecked()){
            if(out == ""){
                out += cbPresentasi.getText().toString()+ ",";
            } else {
                out += ","+cbPresentasi.getText().toString();
            }

        }
        if(cbSosmed.isChecked()){
            if(out == ""){
                out += cbSosmed.getText().toString()+ ",";
            } else {
                out += ","+cbSosmed.getText().toString();
            }

        }
        if(cbWebsite.isChecked()){
            if(out == ""){
                out += cbWebsite.getText().toString()+ ",";
            } else {
                out += ","+cbWebsite.getText().toString();
            }
        }
        if(cbGuru.isChecked()){
            if(out == ""){
                out += cbGuru.getText().toString()+ ",";
            } else {
                out += ","+cbGuru.getText().toString();
            }
        }
        if(cbTeman.isChecked()){
            if(out == ""){
                out += cbTeman.getText().toString()+ ",";
            } else {
                out += ","+cbTeman.getText().toString();
            }
        }
        if(cbSms.isChecked()){
            if(out == ""){
                out += cbSms.getText().toString()+ ",";
            } else {
                out += ","+cbSms.getText().toString();
            }
        }
        if(cbPoster.isChecked()){
            if(out == ""){
                out += cbPoster.getText().toString()+ ",";
            } else {
                out += ","+cbPoster.getText().toString();
            }
        }
        if(cbSurat.isChecked()){
            if(out == ""){
                out += cbSurat.getText().toString()+ ",";
            } else {
                out += ","+cbSurat.getText().toString();
            }
        }
        if(cbOrangtua.isChecked()){
            if(out == ""){
                out += cbOrangtua.getText().toString()+ ",";
            } else {
                out += ","+cbOrangtua.getText().toString();
            }
        }
        if(cbMajalah.isChecked()){
            if(out == ""){
                out += cbMajalah.getText().toString()+ ",";
            } else {
                out += ","+cbMajalah.getText().toString();
            }
        }
        if(cbRadio.isChecked()){
            if(out == ""){
                out += cbRadio.getText().toString()+ ",";
            } else {
                out += ","+cbRadio.getText().toString();
            }
        }
        if(cbSaudara.isChecked()){
            if(out == ""){
                out += cbSaudara.getText().toString()+ ",";
            } else {
                out += ","+cbSaudara.getText().toString();
            }
        }
        if(cbLainya.isChecked()){
            if(out == ""){
                out += cbLainya.getText().toString()+ ",";
            } else {
                out += ","+cbLainya.getText().toString();
            }
        }
        return out;
    }

    public void cekValid(){
        if(etNama.getText().toString().equals("")){
            Toast.makeText(DaftarActivity.this,"Nama tidak boleh kosong",Toast.LENGTH_LONG).show();
            etNama.requestFocus();
            flag = false;
        } else if(etNohp.getText().toString().equals("") && etEmail.getText().toString().equals("")){
            Toast.makeText(DaftarActivity.this,"No HP atau Email tidak boleh kosong",Toast.LENGTH_LONG).show();
            etNohp.requestFocus();
            flag = false;
        } else if(etPwd.getText().toString().equals("")) {
            Toast.makeText(DaftarActivity.this, "Password tidak boleh kosong", Toast.LENGTH_LONG).show();
            etPwd.requestFocus();
            flag = false;
        } else if(etAsal.getText().toString().equals("")) {
            Toast.makeText(DaftarActivity.this, "Asal sekolah tidak boleh kosong", Toast.LENGTH_LONG).show();
            etAsal.requestFocus();
            flag = false;
        } else {
            flag = true;
        }
    }

    public void simpanData(){
        cekValid();
        if(flag == false){
            //Toast.makeText(DaftarActivity.this,"Data tidak boleh kosong",Toast.LENGTH_LONG).show();
        } else {
            pd = new ProgressDialog(this);
            pd.setMessage("Loading...");
            pd.show();
            String urlHal = Constant.ENDPOINT_ADD_SISWA;
            Log.d("link ni: ", urlHal);
            StringRequest req = new StringRequest(Request.Method.POST, urlHal, successListener(), errListener()){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("name", etNama.getText().toString());
                    Log.d("nama : ", etNama.getText().toString());
                    params.put("nohp", etNohp.getText().toString());
                    params.put("email", etEmail.getText().toString());
                    params.put("alamat", etAlamat.getText().toString());
                    params.put("kodepos", etKode.getText().toString());
                    params.put("asal", etAsal.getText().toString());
                    params.put("kelas", etKelas.getText().toString());
                    params.put("jurusan", etJurusan.getText().toString());
                    params.put("jur", etJur.getText().toString());
                    params.put("ptn", etPtn.getText().toString());
                    params.put("pts", etPts.getText().toString());
                    Log.d("desk :", String.valueOf(deskripsi));
                    params.put("deskripsi", String.valueOf(deskripsi));
                    params.put("tgl", etTanggal.getText().toString());
                    params.put("status", "Waiting");
                    Log.d("Parameter",params.toString());
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
                pd.dismiss();
                Log.e("Error", "Tambah Data");
                Log.e("Error", String.valueOf(error));
                Toast.makeText(DaftarActivity.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        };
    }

    private Response.Listener<String> successListener() {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                try{
                    Log.d("response", response);
                    JSONObject json = new JSONObject(response);
                    String message = json.getString("message");
                    String status = json.getString("status");
                    Log.d("message", message);
                    if (status.equals("200"))
                    {
                        //Toast.makeText(DaftarActivity.this,message,Toast.LENGTH_LONG).show();
                        simpanAkun();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rd_ya:
                if (checked)
                    rlMengetahui.setVisibility(View.VISIBLE);
                rlTanggal.setVisibility(View.VISIBLE);
                break;
            case R.id.rd_tidak:
                if (checked)
                    rlMengetahui.setVisibility(View.GONE);
                rlTanggal.setVisibility(View.GONE);
                break;
        }
    }

    public void simpanAkun(){

        pd = new ProgressDialog(this);
        pd.setMessage("Loading...");
        pd.show();
        String urlHal = Constant.ENDPOINT_ADD_USER;
        Log.d("link ni: ", urlHal);
        StringRequest req = new StringRequest(Request.Method.POST, urlHal, listenerSuccess(), listenerErr()){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("name", etNama.getText().toString());
                params.put("nohp", etNohp.getText().toString());
                params.put("email", etEmail.getText().toString());
                params.put("password", etPwd.getText().toString());
                params.put("status", "Aktif");
                Log.d("Parameter",params.toString());
                return params;
            }
        };
        AppsController.getInstance().addToRequestQueue(req);
    }

    private Response.ErrorListener listenerErr() {
        return new Response.ErrorListener() {
            @Override
            public  void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Log.e("Error", "Tambah Data");
                Log.e("Error", String.valueOf(error));
                Toast.makeText(DaftarActivity.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        };
    }

    private Response.Listener<String> listenerSuccess() {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                try{
                    Log.d("response", response);
                    JSONObject json = new JSONObject(response);
                    String message = json.getString("message");
                    String status = json.getString("status");
                    Log.d("message", message);
                    if (status.equals("200"))
                    {
                        Toast.makeText(DaftarActivity.this,message,Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(DaftarActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
