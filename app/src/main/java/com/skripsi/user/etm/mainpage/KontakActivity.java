package com.skripsi.user.etm.mainpage;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.skripsi.user.etm.R;

public class KontakActivity extends AppCompatActivity {

    ImageView iv_kembali;
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontak);

        iv_kembali = (ImageView) findViewById(R.id.iv_back);
        iv_kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
