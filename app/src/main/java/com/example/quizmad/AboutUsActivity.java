package com.example.quizmad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class AboutUsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        double LatAnggrek = Double.parseDouble(MainActivity.maps.get(0).getLat());
        double LngAnggrek = Double.parseDouble(MainActivity.maps.get(0).getLng());

        LatLng anggrek = new LatLng(LatAnggrek, LngAnggrek);
        mMap.addMarker(new MarkerOptions().position(anggrek).title(MainActivity.maps.get(0).getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(anggrek));

        double LatSyahdan = Double.parseDouble(MainActivity.maps.get(1).getLat());
        double LngSyahdan = Double.parseDouble(MainActivity.maps.get(1).getLng());


        LatLng syahdan = new LatLng(LatSyahdan, LngSyahdan);
        mMap.addMarker(new MarkerOptions().position(syahdan).title(MainActivity.maps.get(1).getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(syahdan));


        double LatKijang = Double.parseDouble(MainActivity.maps.get(2).getLat());
        double LngKijang = Double.parseDouble(MainActivity.maps.get(2).getLng());


        LatLng kijang = new LatLng(LatKijang, LngKijang );
        mMap.addMarker(new MarkerOptions().position(kijang).title(MainActivity.maps.get(2).getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(kijang));
    }
}