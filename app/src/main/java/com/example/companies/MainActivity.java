package com.example.companies;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.content.Context;
public class MainActivity extends AppCompatActivity {

    public Toolbar appBarLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private ImageView input;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //za application bar
       // appBarLayout = (AppBarLayout) findViewById(R.id.appbarid);
setSupportActionBar(appBarLayout);
        //tab
        tabLayout= (TabLayout) findViewById(R.id.appbarid);
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        //ovde da dodadam fragmenti treba
        adapter.AddFragment(new FragmentServices(),"Services");
        adapter.AddFragment(new FragmentFun(),"Fun");
        adapter.AddFragment(new FragmentIndustry(),"Industry");
        adapter.AddFragment(new FragmentEducation(),"Education");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_services);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_fun);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_industy);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_education);

        input = (ImageView) findViewById(R.id.input);
        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openIntent();

            }
        });

    }
    public void openIntent(){
        Intent intent = new Intent(this, Input.class);
        startActivity(intent);
    }

}
