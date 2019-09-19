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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

    public static float distance(float lat1, float lng1, float lat2, float lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        float distance = (float) (earthRadius * c);

        return distance;
    }
//    public class MyLocation implements LocationListener {
//        double lat2,lng2,lat1,lng1;
//        @Override
//        public void onLocationChanged(Location loc)
//        {
//            lat2=loc.getLatitude();
//            lng2=loc.getLongitude();
//            String Text = "My current location is: " +"Latitud = "+ loc.getLatitude() +"Longitud = " + loc.getLongitude();
//
//            //System.out.println("Lat & Lang form Loc"+Text);
//            //Toast.makeText( getApplicationContext(), Text,Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public void onProviderDisabled(String provider)
//        {
//        }
//
//        @Override
//        public void onProviderEnabled(String provider)
//        {
//        }
//
//        @Override
//        public void onStatusChanged(String provider, int status, Bundle extras)
//        {
//        }
//
//
//        //Calculating distance
//        double earthRadius = 3958.75;
//
//        double dLat = Math.toRadians(lat1-lat2);
//        double dLng = Math.toRadians(lng1-lng2);
//        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
//                Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(lat1)) *
//                        Math.sin(dLng/2) * Math.sin(dLng/2);
//        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
//        double dist = earthRadius * c;
//    }
//    Location startPoint=new Location("locationA");
//startPoint.setLatitude(17.372102);
//startPoint.setLongitude(78.484196);
//
//    Location endPoint=new Location("locationA");
//endPoint.setLatitude(17.375775);
//endPoint.setLongitude(78.469218);
//
//    double distance=startPoint.distanceTo(endPoint);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                final float currentLat = (float) location.getLatitude();
                final float currentLon = (float) location.getLongitude();
                String Text = "My current location is: " + "Latitude = " + currentLat + "Longitude = " + currentLon;
//                Toast.makeText(getApplicationContext(), Text, Toast.LENGTH_SHORT).show();

                final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Companies");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Companies.CompanyData company;
                            company = snapshot.getValue(Companies.CompanyData.class);
                            float myDistance = distance(company.getLatitude(), company.getLongitude(), currentLat, currentLon);
                            if (myDistance <= 50) {
                                Toast.makeText(getApplicationContext(), "You are within 50 meters of " + company.getName(), Toast.LENGTH_SHORT).show();
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }

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
