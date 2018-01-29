package com.skripsi.user.etm.pagehome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.skripsi.user.etm.R;
import com.skripsi.user.etm.mainpage.LoginActivity;
import com.skripsi.user.etm.util.Constant;

public class HomeActivity extends AppCompatActivity {

    /** deklarasi variable dan object yang dibutuhkan di class ini **/
    private BottomBar bottomBar;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPrefs.edit();

        /** ini adalah bagian untuk mendirect menu bottom bar **/
        bottomBar = (BottomBar) findViewById(R.id.bottombar);
        commitFragment(new SiswaFragment());
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if(tabId == R.id.tab_home){
                    commitFragment(new SiswaFragment());
                } else if(tabId == R.id.tab_sms){
                    commitFragment(new SmsFragment());
                } else if(tabId == R.id.tab_telemart){
                    commitFragment(new TelemarketingFragment());
                } else if(tabId == R.id.tab_email){
                    commitFragment(new EmailFragment());
                } else if(tabId == R.id.tab_logout){
                    logout();
                    Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void logout(){
        editor.putString(Constant.KEY_SHAREDPREFS_USER_DATA, null);
        editor.putString(Constant.KEY_SHAREDPREFS_LOGIN_STATUS, "0");
        editor.commit();
    }

    /** ini adalah fungsi untuk mengganti layout dari activity ke fragment **/
    private void commitFragment(Fragment fragment){
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }
}
