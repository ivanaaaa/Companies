package com.example.companies;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.support.v7.widget.Toolbar;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.text.DecimalFormat;

import static android.text.TextUtils.*;

public class Input extends AppCompatActivity implements View.OnClickListener {
    public ImageView input;
    Toolbar appbar;
    public EditText name_company;
    public EditText address_company;
    public EditText latitude;
    public EditText longitude;
    public EditText email, phone, web;
    private CheckBox services_check, fun_check, industry_check, education_check;
    Button save;

    //    FirebaseDatabase database;
    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        //Toast.makeText(Input.this,"uspesno",Toast.LENGTH_LONG).show();

        FirebaseApp.initializeApp(this);

        appbar = (Toolbar) findViewById(R.id.appbarid);
        setSupportActionBar(appbar);
        name_company = (EditText) findViewById(R.id.name_company);
        address_company = (EditText) findViewById(R.id.address_company);
        latitude = (EditText) findViewById(R.id.latitude_company);
        longitude = (EditText) findViewById(R.id.longitude_company);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone_company);
        web = (EditText) findViewById(R.id.web_company);

//        input = (ImageView) findViewById(R.id.input);
        services_check = findViewById(R.id.services_check);
        fun_check = findViewById(R.id.fun_check);
        industry_check = findViewById(R.id.industry_check);
        education_check = findViewById(R.id.education_check);


        findViewById(R.id.btnInsert).setOnClickListener(this);
    }

    public boolean validator (String name, String address, String lat, String lon, String mail, String tel,String web_site){
        if (name.isEmpty()) {
            name_company.setError("Enter Name");
            name_company.requestFocus();
            return true;
        }
        if (address.isEmpty()) {
            address_company.setError("Enter address");
            address_company.requestFocus();
            return true;

        }

        if (lat.isEmpty()) {
            latitude.setError("Enter latitude");
            latitude.requestFocus();
            return true;

        }

        if (lon.isEmpty()) {
            longitude.setError("Enter longitude");
            longitude.requestFocus();
            return true;
        }

        if (mail.isEmpty()) {
            email.setError("Enter mail");
            email.requestFocus();
            return true;
        }
        if (tel.isEmpty()) {
            phone.setError("Enter phone");
            phone.requestFocus();
            return true;
        }
        if (web_site.isEmpty()) {
            web.setError("Enter web site");
            web.requestFocus();
            return true;
        }
        return false;
    }
    public void onClick(View v) {

        String name = (String) name_company.getText().toString().trim();
        String address = (String) address_company.getText().toString().trim();
        String lat = (String) latitude.getText().toString().trim();
        String lon = (String) longitude.getText().toString().trim();
        String mail = (String) email.getText().toString().trim();
        String tel = (String) phone.getText().toString().trim();
        String web_site = (String) web.getText().toString().trim();
        boolean services = (boolean) services_check.isChecked();
        boolean fun = (boolean) fun_check.isChecked();
        boolean industry = (boolean) industry_check.isChecked();
        boolean education = (boolean) education_check.isChecked();
        if (!validator(name, address, lat, lon, mail, tel, web_site))
        {
            Companies.CompanyData Company;
            String company_id;
//            ref = FirebaseDatabase.getInstance().getReference("Companies");
//            company_id = ref.push().getKey();
            DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference().child("Companies");
            company_id = dbRef.push().getKey();
            Company = new Companies.CompanyData(company_id, name, address, lat, lon, mail, tel, web_site, services, fun, industry, education);
            dbRef.child(company_id).setValue(Company);
//            ref.child(company_id).setValue(Company);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Data entered successfully.", Toast.LENGTH_LONG).show();
        }
     else
            Toast.makeText(this, "error", Toast.LENGTH_LONG).show();

    }

//        input = (ImageView) findViewById(R.id.input);
//        input.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                openIntent();
//
//            }
//        });

    //mRootRef = new Firebase("https://companies-fb229.firebaseio.com/Company");

    //mdb= FirebaseDatabase.getInstance().getReference();
//    ----------------------------

//    database =FirebaseDatabase.getInstance();
//    Company =new Companies();
//
//    ref =database.getReference("Companies").
//
//    child("Company");
//    ----------------------------
//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //Double lat = Double.parseDouble((latitude.getText().toString()));
//                //Double lon = Double.parseDouble((longitude.getText().toString()));
//                Company.setName(name_company.getText().toString());
//                Company.setAddress(address_company.getText().toString());
//                Company.setLatitude(latitude.getText().toString());
//                Company.setLongitude(longitude.getText().toString());
//                Company.setEmail(email.getText().toString());
//                Company.setTelephone(phone.getText().toString());
//                Company.setWeb_site(web.getText().toString());
//                ref.push().setValue(Company);
//                Toast.makeText(Input.this,"Successfully inserted",Toast.LENGTH_LONG).show();
//
//            }
//        });

    // Company = new Companies();
//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                addCompany();
//            }
//        });

//    private void getValues() {
//        Company.setName(name_company.getText().toString());
//        Company.setAddress(address_company.getText().toString());
//        Company.setLatitude(latitude.getText().toString());
//        Company.setLongitude(longitude.getText().toString());
//        Company.setEmail(email.getText().toString());
//        StringBuilder result = new StringBuilder();
//        if (services_check.isChecked()) {
//            result.append("services");
//        }
//        if (fun_check.isChecked()) {
//            result.append("fun");
//        }
//        if (industry_check.isChecked()) {
//            result.append("industry");
//        }
//        if (education_check.isChecked()) {
//            result.append("education");
//        }
//        Company.setCategory(result.toString());
//        Company.setTelephone(phone.getText().toString());
//        Company.setWeb_site(web.getText().toString());
//
//    }

//    public void openIntent() {
//
//    }
//    save.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//
//            btnInsert();
//
//        }
//    });
//
//    public void btnInsert(View view) {
//
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                getValues();
//                ref.setValue(Company);
//                Toast.makeText(Input.this, "Successfully inserted", Toast.LENGTH_LONG).show();
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
//    public void addCompany (){
//        String name = name_company.getText().toString();
//        String address = address_company.getText().toString();
//        String lat = latitude.getText().toString();
//        String lon = longitude.getText().toString();
//        String mail = email.getText().toString();
//        String tel = phone.getText().toString();
//        String Web = web.getText().toString();
//        if(!isEmpty(name) && !isEmpty(address) && !isEmpty(lat) && !isEmpty(lon) && !isEmpty(mail) && !isEmpty(tel) && !isEmpty(Web)){
//            String id = ref.push().getKey();
//            Companies Company = new Companies(id,name,address,lat,lon,mail,tel,Web);
//            ref.child(id).setValue(Company);
//
//            name_company.setText("");
//            address_company.setText("");
//            latitude.setText("");
//            longitude.setText("");
//            email.setText("");
//            phone.setText("");
//            web.setText("");
//        }
//        else{
//            Toast.makeText(Input.this,"Please fill the empty field",Toast.LENGTH_LONG).show();
//        }
//    }}

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater;

        menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_when_input, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.back) {
            startActivity(new Intent(this, MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}