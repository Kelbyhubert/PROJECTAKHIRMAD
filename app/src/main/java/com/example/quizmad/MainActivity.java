package com.example.quizmad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.quizmad.DataAccess.DummyData;
import com.example.quizmad.context.UserSession;
import com.example.quizmad.model.Map;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    RequestQueue mQueue;
    static ArrayList<Map> maps = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        if(getIntent().getExtras() == null){
            DummyData.initDollData();
        }


        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout ,toolbar ,R.string.open,R.string.close );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_view,AllDollView.class,null).commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                switch (itemId){
                    case R.id.viewAllDollMenu:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_view,AllDollView.class,null)
                                .commit();
                        break;
                    case R.id.insertDollMenu:
                        Intent x = new Intent(getApplicationContext(),ModifyDollActivity.class);
                        startActivity(x);

                        break;
                    case R.id.logoutMenu:
                        new UserSession(getApplicationContext()).destroySession();
                        Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(i);
                        finish();
                        break;
                    default:
                }

                drawerLayout.closeDrawer(GravityCompat.START, true);
                return true;
            }
        });




    }

    private void init(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.app_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.locationMenu:

//                Intent i = new Intent(getApplicationContext(),AboutUsActivity.class);
//                startActivity(i);
                break;
        }
        return true;
    }

 public void jsonParse (){
        String url = "https://raw.githubusercontent.com/acad600/JSONRepository/master/ISYS6203/O212-ISYS6203-SO02-00-BlueDoll.json";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("markers");

                    for(int i =0; i<jsonArray.length(); i++){
                        JSONObject marker = jsonArray.getJSONObject(i);
                        String name = marker.getString("name");

                        JSONObject location = marker.getJSONObject("location");
                        String lat = location.getString("lat");
                        String lng = location.getString("lng");

                        Map map = new Map (name, lat, lng);
                        maps.add(map);



                    }

                    for (int j =0; j<maps.size(); j++){
                        Log.i("Location", maps.get(j).getName());
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) ;

        mQueue.add(request);
    }

}