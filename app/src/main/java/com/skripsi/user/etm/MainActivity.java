package com.skripsi.user.etm;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.skripsi.user.etm.mainpage.KontakActivity;
import com.skripsi.user.etm.mainpage.LoginActivity;
import com.skripsi.user.etm.mainpage.MappingSiswaActivity;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission_group.CAMERA;

public class MainActivity extends AppCompatActivity {

    RelativeLayout rl_kontak;
    RelativeLayout rl_menu;
    Button btnLogin;
    static final Integer LOCATION = 0x1;
    static final Integer STORAGE = 0x2;

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
        askForPermission(Manifest.permission.ACCESS_FINE_LOCATION,LOCATION);
    }

    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);
            }
        } else {
            Log.d("Permission : ","permission is already granted.");
        }
    }

    public void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MainActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(ActivityCompat.checkSelfPermission(MainActivity.this, permissions[0]) == PackageManager.PERMISSION_GRANTED){
            switch (requestCode) {
                //Location
                case 1:
                    if (grantResults.length > 0) {

                        boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                        if (locationAccepted){
                            Toast.makeText(this,"OK Permission Granted, Now you can access location data."
                                    ,Toast.LENGTH_LONG).show();
                            askForPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,STORAGE);
                            //pindahLokasi();
                        }
                        else {
                            Toast.makeText(this,"Permission Denied, You cannot access location data."
                                    ,Toast.LENGTH_LONG).show();

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
                                    showMessageOKCancel("You need to allow access to the permissions",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                        askForPermission(ACCESS_FINE_LOCATION,LOCATION);
                                                    }
                                                }
                                            });
                                    return;
                                }
                            }

                        }
                    }
                    break;
                case 2:
                    if (grantResults.length > 0) {

                        //boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                        boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                        if (cameraAccepted){
                            Toast.makeText(this,"Permission Granted, Now you can access storage.",Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(this,"Permission Denied, You cannot access storage.",Toast.LENGTH_LONG).show();

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                if (shouldShowRequestPermissionRationale(CAMERA)) {
                                    showMessageOKCancel("You need to allow access to the permissions",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                        askForPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,STORAGE);
                                                    }
                                                }
                                            });
                                    return;
                                }
                            }

                        }
                    }

                    break;
            }
        }else{
            showMessageOKCancel("You need to allow access to the permissions",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                askForPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE,STORAGE);
                            }
                        }
                    });
            return;
        }
    }
}
