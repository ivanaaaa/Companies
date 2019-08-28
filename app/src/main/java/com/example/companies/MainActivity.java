package com.example.companies;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.content.Context;

public class MainActivity extends AppCompatActivity {

    public Toolbar appBarLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private ImageView input;


    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
//        locationListener = new LocationListener() {
//            @Override
//            public void onLocationChanged(Location location) {
//
//            }
//
//            @Override
//            public void onStatusChanged(String provider, int status, Bundle extras) {
//
//            }
//
//            @Override
//            public void onProviderEnabled(String provider) {
//
//            }
//
//            @Override
//            public void onProviderDisabled(String provider) {
//
//            }
//        };
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
//
//        } else {
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
//        }

        //za application bar
        // appBarLayout = (AppBarLayout) findViewById(R.id.appbarid);
        appBarLayout = (Toolbar) findViewById(R.id.appbarid);
        setSupportActionBar(appBarLayout);
        //tab
        ViewPagerAdapter adapter;

        tabLayout = (TabLayout) findViewById(R.id.tablayout_id);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        //ovde da dodadam fragmenti treba
        adapter.AddFragment(new FragmentServices(), "Services");
        adapter.AddFragment(new FragmentFun(), "Fun");
        adapter.AddFragment(new FragmentIndustry(), "Industry");
        adapter.AddFragment(new FragmentEducation(), "Education");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_services);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_fun);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_industy);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_education);


    }

//    public void openIntent() {
//        Intent intent = new Intent(this, Input.class);
//        startActivity(intent);
//    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater;

        menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_when_fragments, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.input) {
            startActivity(new Intent(this, Input.class));
        }
        return super.onOptionsItemSelected(item);
    }

}
