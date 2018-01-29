package com.skripsi.user.etm.mainpage;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.skripsi.user.etm.R;
import com.skripsi.user.etm.util.MLocation;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission_group.CAMERA;

public class MappingSiswaActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    float zoomLevel = 6.0f;
    static final Integer LOCATION = 0x1;
    static final Integer CAMERAS = 0x2;
    ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapping_siswa);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        askForPermission(Manifest.permission.ACCESS_FINE_LOCATION,LOCATION);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ivBack = (ImageView) findViewById(R.id.iv_mapping_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
        && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            LatLng jakarta = new LatLng(-6.252887, 106.8469626);
            //mMap.addMarker(new MarkerOptions().position(jakarta).title("Marker in Jakarta"));
            //mMap.moveCamera(CameraUpdateFactory.newLatLng(jakarta));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jakarta, zoomLevel));
            mMap.getUiSettings().setZoomControlsEnabled(true);

            return;
        }
        else{
            Location myLocation = MLocation.getLocation(this);

            // Add a marker in where ever we are and move the camera
            LatLng jakarta = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jakarta, zoomLevel));
            mMap.getUiSettings().setZoomControlsEnabled(true);

            mMap.setMyLocationEnabled(true);
        }
    }

    private void askForPermission(String permission, Integer requestCode) {
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MappingSiswaActivity.this, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(MappingSiswaActivity.this, new String[]{permission}, requestCode);

            } else {

                ActivityCompat.requestPermissions(MappingSiswaActivity.this, new String[]{permission}, requestCode);
            }
        } else {
            //Toast.makeText(Splash.this, "permission is already granted.", Toast.LENGTH_SHORT).show();
            Log.d("Permission : ","permission is already granted.");
        }
    }

    public void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(MappingSiswaActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(ActivityCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_GRANTED){
            switch (requestCode) {
                //Location
                case 1:
                    if (grantResults.length > 0) {

                        boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                        //boolean cameraAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                        if (locationAccepted){
                            Toast.makeText(this,"OK Permission Granted, Now you can access location data."
                                    ,Toast.LENGTH_LONG).show();
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
                        boolean cameraAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                        if (cameraAccepted){
                            Toast.makeText(this,"Permission Granted, Now you can access camera.",Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(this,"Permission Denied, You cannot access camera.",Toast.LENGTH_LONG).show();

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                if (shouldShowRequestPermissionRationale(CAMERA)) {
                                    showMessageOKCancel("You need to allow access to the permissions",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                        askForPermission(CAMERA,CAMERAS);
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
            Location myLocation = MLocation.getLocation(this);
            LatLng jakarta = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jakarta, zoomLevel));
            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.setMyLocationEnabled(true);
        }else{
            //Toast.makeText(getContext(), "Permission denied", Toast.LENGTH_SHORT).show();
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
