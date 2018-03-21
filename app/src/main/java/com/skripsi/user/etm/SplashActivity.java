package com.skripsi.user.etm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.skripsi.user.etm.util.Constant;

import org.json.JSONException;
import org.json.JSONObject;

public class SplashActivity extends AppCompatActivity {

    private String statusLogin = "0";
    private String role = "0";
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(SplashActivity.this);

        /**ambil data dari shared preference**/
        String getStatusLogin = sharedPrefs.getString(Constant.KEY_SHAREDPREFS_LOGIN_STATUS, null);
        if(getStatusLogin.equals("1")){
            statusLogin = "1";
            String getLocalData = sharedPrefs.getString(Constant.KEY_SHAREDPREFS_USER_DATA,null);
            if(getLocalData != null){
                JSONObject json = null;
                try{
                    json = new JSONObject(getLocalData);
                    role = json.getString("id_role");
                } catch (JSONException e){

                }
            }
        }

        Thread myThread = new Thread(){
            @Override
            public void run(){
                try{
                    sleep(3000);
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}
