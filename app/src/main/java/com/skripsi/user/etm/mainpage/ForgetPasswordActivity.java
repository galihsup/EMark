package com.skripsi.user.etm.mainpage;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import java.util.HashMap;
import java.util.Map;

public class ForgetPasswordActivity extends AppCompatActivity {

    TextInputEditText tieEmail;
    TextInputEditText tieNewPas;
    TextInputEditText tieRetype;
    Button btnCek;
    Button btnSimpan;
    ImageView ivBack;
    ProgressDialog pd;
    String newpas;
    String retype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        tieEmail = (TextInputEditText) findViewById(R.id.txt_lupa_email);
        tieNewPas = (TextInputEditText) findViewById(R.id.txt_new_pass);
        tieRetype = (TextInputEditText) findViewById(R.id.txt_retype_new_pass);
        ivBack = (ImageView) findViewById(R.id.iv_lupa_pass_back);
        btnCek = (Button) findViewById(R.id.btn_cek_email);
        btnSimpan = (Button) findViewById(R.id.btn_new_pass);

        btnCek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cekEmail();
            }
        });
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void cekEmail(){
        pd = new ProgressDialog(this);
        pd.setMessage("Loading...");
        pd.show();
        String urlHal = Constant.ENDPOINT_CEK_EMAIL_USER;
        Log.d("link ni: ", urlHal);
        StringRequest req = new StringRequest(Request.Method.POST, urlHal, successListener(), errListener()){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", tieEmail.getText().toString());
                return params;
            }
        };
        AppsController.getInstance().addToRequestQueue(req);
    }

    private Response.ErrorListener errListener() {
        return new Response.ErrorListener() {
            @Override
            public  void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Log.e("Error", "Data tidak ditemukan");
                Log.e("Error", String.valueOf(error));
                Toast.makeText(ForgetPasswordActivity.this,"Data tidak ditemukan",Toast.LENGTH_LONG).show();
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
                        tieNewPas.setVisibility(View.VISIBLE);
                        tieRetype.setVisibility(View.VISIBLE);
                        btnSimpan.setBackground(getResources().getDrawable(R.drawable.rounded_blue));
                        btnSimpan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                newpas = tieNewPas.getText().toString();
                                retype = tieRetype.getText().toString();
                                if(newpas.equals(retype)){
                                    simpanPassword();
                                } else {
                                    Toast.makeText(ForgetPasswordActivity.this,"Password tidak match",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public void simpanPassword(){
        pd = new ProgressDialog(this);
        pd.setMessage("Loading...");
        pd.show();
        String urlHal = Constant.ENDPOINT_SAVE_PASSWORD_USER;
        Log.d("link ni: ", urlHal);
        StringRequest req = new StringRequest(Request.Method.POST, urlHal, listenerSukses(), listenerErr()){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("newpas", newpas.toString());
                params.put("email", tieEmail.getText().toString());
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
                Log.e("Error", "Gagal ganti password");
                Log.e("Error", String.valueOf(error));
                Toast.makeText(ForgetPasswordActivity.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        };
    }

    private Response.Listener<String> listenerSukses() {
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
                        Toast.makeText(ForgetPasswordActivity.this,message,Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ForgetPasswordActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
