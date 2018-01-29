package com.skripsi.user.etm.mainpage;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.skripsi.user.etm.R;
import com.skripsi.user.etm.pagehome.HomeActivity;
import com.skripsi.user.etm.AppsController;
import com.skripsi.user.etm.util.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    private TextInputEditText mUsername;
    private TextInputEditText mPasswordView;
    private Gson gson;
    private SharedPreferences.Editor editor;
    private String yourToken;
    ImageView iv_show_pass;
    private ProgressDialog pd;
    TextView tvLupa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPrefs.edit();
        gson = new Gson();
        String getUserData = sharedPrefs.getString(Constant.KEY_SHAREDPREFS_USER_DATA, null);
        String getToken = sharedPrefs.getString(Constant.KEY_SHAREDPREFS_TOKEN, null);
        if (getUserData != null && getToken !=null) {
            JSONObject json = null;
            try {
                json = new JSONObject(getUserData);
                String role = json.getString("role");
                if (role.equals("2")) {
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                } else {
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        tvLupa = (TextView) findViewById(R.id.tv_lupa);
        tvLupa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgetPasswordActivity.class));
            }
        });
        iv_show_pass = (ImageView) findViewById(R.id.iv_show_pass);
        iv_show_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPasswordView.getTransformationMethod() != null){
                    mPasswordView.setTransformationMethod(null);
                } else {
                    mPasswordView.setTransformationMethod(new PasswordTransformationMethod());
                }
            }
        });

        mUsername = (TextInputEditText) findViewById(R.id.et_email);
        mPasswordView = (TextInputEditText) findViewById(R.id.et_pass);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginProses();
            }
        });
    }

    private void loginProses(){
        pd = new ProgressDialog(this);
        pd.setMessage("Loading...");
        pd.show();
        String url = Constant.ENDPOINT_LOGIN;
        final String username = mUsername.getText().toString();
        final String password = mPasswordView.getText().toString();
        StringRequest req = new StringRequest(Request.Method.POST, url, loginListener(), errListener()){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", username);
                params.put("password", password);
                params.put("login_type", "1");

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
                Log.e("Error", "Login");
                Log.e("Error", String.valueOf(error));
            }
        };
    }

    private Response.Listener<String> loginListener() {
        return new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pd.dismiss();
                try{
                    Log.d("response", response);
                    JSONObject json = new JSONObject(response);
                    String message = json.getString("message");

                    Log.d("message", message);
                    Toast.makeText(LoginActivity.this,message,Toast.LENGTH_LONG).show();

                    if (json.getString("status").equals("200")) {
                        JSONObject data = new JSONObject(json.getString("data"));
                        String role = data.getString("id_role");

                        /** Store data in shared preference **/
                        editor.putString(Constant.KEY_SHAREDPREFS_USER_DATA, json.getString("data"));
                        editor.putString(Constant.KEY_SHAREDPREFS_LOGIN_STATUS, "1");
                        editor.putString(Constant.KEY_SHAREDPREFS_TOKEN, json.getString("_token")); //buat ngambil json token
                        editor.commit();

                        Intent intent;
                        if (role.equals("2")) {
                            intent = new Intent(LoginActivity.this, HomeActivity.class);
                        } else {
                            intent = new Intent(LoginActivity.this, HomeActivity.class);
                        }
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
