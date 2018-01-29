package com.skripsi.user.etm.pagehome;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
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

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditSiswaActivity extends AppCompatActivity {

    private String id_siswa;
    ProgressDialog loading;

    TextView tvMore;
    RelativeLayout rlMore;
    RelativeLayout rlJurusan;
    RelativeLayout rlKelas;
    RelativeLayout rlPilihJurusan;
    RelativeLayout rlPilihPtn;
    RelativeLayout rlPilihPts;
    RelativeLayout rlFindTrilogi;
    RelativeLayout rlMengetahui;
    ImageView ivExit;
    ImageView ivSave;
    View vwMore;
    RadioButton rdYa;
    RadioButton rdTidak;
    CircleImageView civProfile;

    TextInputEditText etNama,etNohp,etEmail,etAlamat,etKode,etAsal,etJurusan,etJur,etKelas,etPtn,etPts;
    CheckBox cbPameran,cbEdufair,cbPresentasi,cbSosmed,cbWebsite,cbGuru,cbTeman,cbSms,cbPoster,cbSurat,cbOrangtua,
            cbMajalah,cbRadio,cbSaudara,cbLainya;

    String deskripsi = "", ambilTanggal="";
    public String inisial="";
    private SharedPreferences.Editor editor;
    SharedPreferences sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_siswa);
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPrefs.edit();

        tvMore = (TextView) findViewById(R.id.tv_more_edfields);
        rlMore = (RelativeLayout)findViewById(R.id.rl_more_edfields);
        rlJurusan = (RelativeLayout)findViewById(R.id.rl_edjurusan);
        rlKelas = (RelativeLayout)findViewById(R.id.rl_edkelas);
        rlPilihJurusan = (RelativeLayout)findViewById(R.id.rl_pilih_edjurusan);
        rlPilihPtn = (RelativeLayout)findViewById(R.id.rl_pilih_edptn);
        rlPilihPts = (RelativeLayout)findViewById(R.id.rl_pilih_edpts);
        rlFindTrilogi = (RelativeLayout)findViewById(R.id.rl_find_edtrilogi);
        rlMengetahui = (RelativeLayout)findViewById(R.id.rl_ed_mengetahui);
        vwMore = (View) findViewById(R.id.vw_ed_more);
        rdYa = (RadioButton) findViewById(R.id.rd_editya);
        rdTidak = (RadioButton) findViewById(R.id.rd_edittidak);
        ivExit = (ImageView) findViewById(R.id.iv_edit_exit);
        ivSave = (ImageView) findViewById(R.id.iv_edit_save);

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

        etNama = (TextInputEditText) findViewById(R.id.tv_add_editname);
        etNohp = (TextInputEditText) findViewById(R.id.tv_add_editphone);
        etEmail = (TextInputEditText) findViewById(R.id.tv_add_editemail);
        etAlamat = (TextInputEditText) findViewById(R.id.tv_add_editalamat);
        etKode = (TextInputEditText) findViewById(R.id.tv_add_editkodepos);
        etAsal = (TextInputEditText) findViewById(R.id.tv_add_editasalsekolah);
        etKelas = (TextInputEditText) findViewById(R.id.tv_add_editkelas);
        etJurusan = (TextInputEditText) findViewById(R.id.tv_add_editjurusansekolah);
        etJur = (TextInputEditText) findViewById(R.id.tv_add_editpilih_jurusan);
        etPtn = (TextInputEditText) findViewById(R.id.tv_add_editpilih_ptn);
        etPts = (TextInputEditText) findViewById(R.id.tv_add_editpilih_pts);
        cbPameran = (CheckBox) findViewById(R.id.ed_pameran);
        cbEdufair = (CheckBox) findViewById(R.id.ed_edufair);
        cbPresentasi = (CheckBox) findViewById(R.id.ed_presentasi);
        cbSosmed = (CheckBox) findViewById(R.id.ed_sosmed);
        cbWebsite = (CheckBox) findViewById(R.id.ed_website);
        cbGuru = (CheckBox) findViewById(R.id.ed_guru);
        cbTeman = (CheckBox) findViewById(R.id.ed_teman);
        cbSms = (CheckBox) findViewById(R.id.ed_sms);
        cbPoster = (CheckBox) findViewById(R.id.ed_poster);
        cbSurat = (CheckBox) findViewById(R.id.ed_surat_kabar);
        cbOrangtua = (CheckBox) findViewById(R.id.ed_orangtua);
        cbMajalah = (CheckBox) findViewById(R.id.ed_majalah);
        cbRadio = (CheckBox) findViewById(R.id.ed_radio);
        cbSaudara = (CheckBox) findViewById(R.id.ed_saudara);
        cbLainya = (CheckBox) findViewById(R.id.ed_lain_lain);
        civProfile = (CircleImageView) findViewById(R.id.edprofile_image);

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        ambilTanggal = df.format(new Date());

        Intent intent = getIntent();
        id_siswa = intent.getStringExtra("ids");
        Log.d("idsiswa", id_siswa);
        getViewSiswa(id_siswa);
        //isiPhoto();
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rd_editya:
                if (checked)
                    rlMengetahui.setVisibility(View.VISIBLE);
                break;
            case R.id.rd_edittidak:
                if (checked)
                    rlMengetahui.setVisibility(View.GONE);
                break;
        }
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
                        editor.putString(Constant.KEY_SHAREDPREFS_USER_DATA, json.getString("data"));
                        Log.d("json_object", String.valueOf(data));
                        etNama.setText(data.getString("etm_nama_siswa").toString());
                        etAlamat.setText(data.getString("etm_alamat_siswa").toString());
                        etKelas.setText(data.getString("etm_kelas_siswa").toString());
                        etJurusan.setText(data.getString("etm_jurusan_sekolah").toString());
                        etAsal.setText(data.getString("etm_asal_sekolah").toString());
                        etNohp.setText(data.getString("etm_no_hp").toString());
                        etEmail.setText(data.getString("etm_email_siswa").toString());
                        etKode.setText(data.getString("etm_kode_pos").toString());
                        etJur.setText(data.getString("etm_jurusan_kuliah").toString());
                        etPtn.setText(data.getString("etm_ptn_pilihan").toString());
                        etPts.setText(data.getString("etm_pts_pilihan").toString());
                        deskripsi = String.valueOf(data.getString("etm_deskripsi_trilogi").toString());
                        Log.e("kenal", deskripsi);
                        isiCB();
                    } else {
                        Toast.makeText(EditSiswaActivity.this,msg,Toast.LENGTH_LONG).show();
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public void isiCB(){
        List<String> items = Arrays.asList(deskripsi.split(","));
        Log.e("itemCB", items.toString());
        for(int i=0; i<items.size(); i++){
            Log.e("panjangitem", String.valueOf(items.size()));
            String isi = items.get(i).toString();
            Log.d("isiitem", isi);
            if(isi.equals("Pameran Pendidikan")){
                cbPameran.setChecked(true);
            } else if (isi.equals("Edufair di sekolah")){
                cbEdufair.setChecked(true);
            } else if (isi.equals("Presentasi di sekolah")){
                cbPresentasi.setChecked(true);
            } else if (isi.equals("Facebook/Twitter")){
                cbSosmed.setChecked(true);
            } else if (isi.equals("Website Trilogi")){
                cbWebsite.setChecked(true);
            } else if (isi.equals("Guru")){
                cbGuru.setChecked(true);
            } else if (isi.equals("Teman")){
                cbTeman.setChecked(true);
            } else if (isi.equals("SMS")){
                cbSms.setChecked(true);
            } else if (isi.equals("Poster")){
                cbPoster.setChecked(true);
            } else if (isi.equals("Surat Kabar")){
                cbSurat.setChecked(true);
            } else if (isi.equals("Orang Tua")){
                cbOrangtua.setChecked(true);
            } else if (isi.equals("Majalah")){
                cbMajalah.setChecked(true);
            } else if (isi.equals("Radio")){
                cbRadio.setChecked(true);
            } else if (isi.equals("Saudara")){
                cbSaudara.setChecked(true);
            } else if (isi.equals("Lainya")){
                cbLainya.setChecked(true);
            }
        }
    }

    public void isiPhoto(){
        inisial = etNama.getText().toString();
        String nama = inisial;
        String img = nama.substring(0,1);
        img = img.toUpperCase();
        if(img == "A"){
            Glide.with(EditSiswaActivity.this)
                    .load(R.drawable.ic_a)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "B"){
            Glide.with(EditSiswaActivity.this)
                    .load(R.drawable.ic_b)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "C"){
            Glide.with(EditSiswaActivity.this)
                    .load(R.drawable.ic_c)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "D"){
            Glide.with(EditSiswaActivity.this)
                    .load(R.drawable.ic_d)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "E"){
            Glide.with(EditSiswaActivity.this)
                    .load(R.drawable.ic_e)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "F"){
            Glide.with(EditSiswaActivity.this)
                    .load(R.drawable.ic_f)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "G"){
            Glide.with(EditSiswaActivity.this)
                    .load(R.drawable.ic_g)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "H"){
            Glide.with(EditSiswaActivity.this)
                    .load(R.drawable.ic_h)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "I"){
            Glide.with(EditSiswaActivity.this)
                    .load(R.drawable.ic_i)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "J"){
            Glide.with(EditSiswaActivity.this)
                    .load(R.drawable.ic_j)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "K"){
            Glide.with(EditSiswaActivity.this)
                    .load(R.drawable.ic_k)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "L"){
            Glide.with(EditSiswaActivity.this)
                    .load(R.drawable.ic_l)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "M"){
            Glide.with(EditSiswaActivity.this)
                    .load(R.drawable.ic_m)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "N"){
            Glide.with(EditSiswaActivity.this)
                    .load(R.drawable.ic_n)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "O"){
            Glide.with(EditSiswaActivity.this)
                    .load(R.drawable.ic_o)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "P"){
            Glide.with(EditSiswaActivity.this)
                    .load(R.drawable.ic_p)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "Q"){
            Glide.with(EditSiswaActivity.this)
                    .load(R.drawable.ic_q)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "R"){
            Glide.with(EditSiswaActivity.this)
                    .load(R.drawable.ic_r)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "S"){
            Glide.with(EditSiswaActivity.this)
                    .load(R.drawable.ic_s)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "T"){
            Glide.with(EditSiswaActivity.this)
                    .load(R.drawable.ic_t)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "U"){
            Glide.with(EditSiswaActivity.this)
                    .load(R.drawable.ic_u)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "V"){
            Glide.with(EditSiswaActivity.this)
                    .load(R.drawable.ic_v)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "W"){
            Glide.with(EditSiswaActivity.this)
                    .load(R.drawable.ic_w)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "X"){
            Glide.with(EditSiswaActivity.this)
                    .load(R.drawable.ic_x)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "Y"){
            Glide.with(EditSiswaActivity.this)
                    .load(R.drawable.ic_y)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        } else if(img == "Z"){
            Glide.with(EditSiswaActivity.this)
                    .load(R.drawable.ic_z)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .dontAnimate().into(civProfile);
        }
    }

    public void prosesEdit(){
        loading = new ProgressDialog(this);
        loading.setMessage("Loading...");
        loading.show();
        String urlHal = Constant.ENDPOINT_EDIT_SISWA+id_siswa;
        Log.d("link ni: ", urlHal);
        StringRequest req = new StringRequest(Request.Method.POST, urlHal, listenerSuccess(), listenerError()){
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
                params.put("tgl", ambilTanggal.toString());
                params.put("status", "Waiting");
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
                Toast.makeText(EditSiswaActivity.this,error.toString(),Toast.LENGTH_LONG).show();
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
                        Toast.makeText(EditSiswaActivity.this,message,Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(EditSiswaActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
