package com.example.companies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import static com.example.companies.FragmentInfo.Company_address;
import static com.example.companies.FragmentInfo.Company_email;
import static com.example.companies.FragmentInfo.Company_name;
import static com.example.companies.FragmentInfo.Company_phone;
import static com.example.companies.FragmentInfo.Company_web;

public class InfoCompaniesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
//
//        String name = intent.getStringExtra(Company_name);
//        String address = intent.getStringExtra(Company_address);
//        String email = intent.getStringExtra(Company_email);
//        String phone = intent.getStringExtra(Company_phone);
//        String web = intent.getStringExtra(Company_web);

//
//        TextView textViewName = findViewById(R.id.companyName);
//        TextView textViewAddress = findViewById(R.id.companyAddress);
//        TextView textViewEmail = findViewById(R.id.companyEmail);
//        TextView textViewPhone = findViewById(R.id.companyTelephone);
//        TextView textViewWeb = findViewById(R.id.companyWeb);
//
//        textViewName.setText(name);
//        textViewAddress.setText(address);
//        textViewEmail.setText(email);
//        textViewPhone.setText(phone);
//        textViewWeb.setText(web);
    }
}
