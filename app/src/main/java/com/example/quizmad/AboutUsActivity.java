package com.example.quizmad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.quizmad.model.Map;
import com.example.quizmad.service.MapDataService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class AboutUsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private List<Map> mapData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.map);

        MapDataService mapDataService = new MapDataService(getApplicationContext());
        mapDataService.getLocationData(new MapDataService.VolleyResponseListener() {
            @Override
            public void onError() {
                Toast.makeText(getApplicationContext(), "Something went wrong ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(List<Map> maps) {
                    AboutUsActivity.this.mapData = maps;
                    mapFragment.getMapAsync(AboutUsActivity.this);

            }
        });



    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        double LatAnggrek = Double.parseDouble(mapData.get(0).getLat());
        double LngAnggrek = Double.parseDouble(mapData.get(0).getLng());

        LatLng anggrek = new LatLng(LatAnggrek, LngAnggrek);
        mMap.addMarker(new MarkerOptions().position(anggrek).title(mapData.get(0).getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(anggrek));

        double LatSyahdan = Double.parseDouble(mapData.get(1).getLat());
        double LngSyahdan = Double.parseDouble(mapData.get(1).getLng());


        LatLng syahdan = new LatLng(LatSyahdan, LngSyahdan);
        mMap.addMarker(new MarkerOptions().position(syahdan).title(mapData.get(1).getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(syahdan));


        double LatKijang = Double.parseDouble(mapData.get(2).getLat());
        double LngKijang = Double.parseDouble(mapData.get(2).getLng());


        LatLng kijang = new LatLng(LatKijang, LngKijang );
        mMap.addMarker(new MarkerOptions().position(kijang).title(mapData.get(2).getName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(kijang));
    }
}