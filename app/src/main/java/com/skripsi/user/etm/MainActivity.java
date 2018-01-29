package com.skripsi.user.etm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.skripsi.user.etm.mainpage.KontakActivity;
import com.skripsi.user.etm.mainpage.LoginActivity;
import com.skripsi.user.etm.mainpage.MappingSiswaActivity;

public class MainActivity extends AppCompatActivity {

    RelativeLayout rl_kontak;
    RelativeLayout rl_menu;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rl_kontak = (RelativeLayout) findViewById(R.id.ly_menu_kontak);
        rl_kontak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, KontakActivity.class);
            startActivity(intent);
            }
        });

        rl_menu = (RelativeLayout) findViewById(R.id.ly_menu);
        rl_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MappingSiswaActivity.class);
                startActivity(intent);
            }
        });

        btnLogin = (Button) findViewById(R.id.bt_pergi_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
