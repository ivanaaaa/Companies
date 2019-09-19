package com.example.companies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Input extends AppCompatActivity implements View.OnClickListener {
    public ImageView input;
    Toolbar appbar;
    public EditText name_company;
    public EditText address_company;
    public EditText latitude;
    public EditText longitude;
    public EditText email, phone, web;
    private CheckBox services_check, fun_check, industry_check, education_check;

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

    public boolean validator(String name, String address, float lat, float lon, String mail, String tel, String web_site) {
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
        if (Float.isNaN(lat)) {
            latitude.setError("Enter latitude");
            latitude.requestFocus();
            return true;
        }
        if (Float.isNaN(lon)) {
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
        float lat = Float.parseFloat(latitude.getText().toString().trim());
        float lon = Float.parseFloat(longitude.getText().toString().trim());
        String mail = (String) email.getText().toString().trim();
        String tel = (String) phone.getText().toString().trim();
        String web_site = (String) web.getText().toString().trim();
        boolean services = (boolean) services_check.isChecked();
        boolean fun = (boolean) fun_check.isChecked();
        boolean industry = (boolean) industry_check.isChecked();
        boolean education = (boolean) education_check.isChecked();
        if (!validator(name, address, lat, lon, mail, tel, web_site)) {
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
        } else
            Toast.makeText(this, "error", Toast.LENGTH_LONG).show();
    }

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