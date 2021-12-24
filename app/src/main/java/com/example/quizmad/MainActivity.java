package com.example.quizmad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.quizmad.DataAccess.DummyData;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

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
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_view,AboutUs.class,null)
                        .commit();
                break;
        }
        return true;
    }
}