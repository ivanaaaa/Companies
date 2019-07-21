package com.example.companies;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;

import static android.text.TextUtils.*;

public class Input extends AppCompatActivity {
   public ImageView input;
    public EditText name_company;
    public EditText address_company;
    public EditText latitude;
    public EditText longitude;
    public EditText email,phone,web;
    private CheckBox services_check, fun_check,industry_check,education_check;
    Button save;
  //  private Firebase mRootRef;
   // private DatabaseReference mdb;

    FirebaseDatabase database;
    DatabaseReference ref;
    Companies Company;

//    public String Name,Address,Email,Web;
//    public DecimalFormat Longitude,Latitude;
//    public Integer Phone;
//
//    public Input() {
//    }
//
//    public Input(String name, String address, String email, Integer phone, String web, DecimalFormat longitude, DecimalFormat latitude) {
//        Name = name;
//        Address = address;
//        Email = email;
//        Phone = phone;
//        Web = web;
//        Longitude = longitude;
//        Latitude = latitude;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        //Toast.makeText(Input.this,"uspesno",Toast.LENGTH_LONG).show();
        name_company = (EditText) findViewById(R.id.name_company);
        name_company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name_company.getText().clear();
            }
        });

        address_company = (EditText) findViewById(R.id.address_company);
        address_company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                address_company.getText().clear();
            }
        });

//        input = (ImageView) findViewById(R.id.input);
//        input.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                openIntent();
//
//            }
//        });


        latitude = (EditText) findViewById(R.id.latitude);
        latitude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                latitude.getText().clear();
            }
        });

        longitude = (EditText) findViewById(R.id.longitude);
        longitude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                longitude.getText().clear();
            }
        });

        email = (EditText) findViewById(R.id.email);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email.getText().clear();
            }
        });

        phone = (EditText) findViewById(R.id.phone_company);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone.getText().clear();
            }
        });

        web = (EditText) findViewById(R.id.web_company);
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                web.getText().clear();
            }
        });

        //mRootRef = new Firebase("https://companies-fb229.firebaseio.com/Company");
        save = (Button) findViewById(R.id.btnInsert);




        input = (ImageView) findViewById(R.id.input);
        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openIntent();

            }
        });
        //mdb= FirebaseDatabase.getInstance().getReference();

        services_check=findViewById(R.id.services_check);
        fun_check=findViewById(R.id.fun_check);
        industry_check=findViewById(R.id.industry_check);
        education_check=findViewById(R.id.education_check);

        services_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(services_check.isChecked()){

                }
            }
        });


        database = FirebaseDatabase.getInstance();
        Company=new Companies();
        ref = database.getReference("Companies").child("Company");
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

    }
    private void getValues(){
        Company.setName(name_company.getText().toString());
        Company.setAddress(address_company.getText().toString());
        Company.setLatitude(latitude.getText().toString());
        Company.setLongitude(longitude.getText().toString());
        Company.setEmail(email.getText().toString());
        StringBuilder result=new StringBuilder();
        if(services_check.isChecked()){
            result.append("services");
        }
        if(fun_check.isChecked()){
            result.append("fun");
        }
        if(industry_check.isChecked()){
            result.append("industry");
        }
        if(education_check.isChecked()){
            result.append("education");
        }
        Company.setCategory(result.toString());
        Company.setTelephone(phone.getText().toString());
        Company.setWeb_site(web.getText().toString());

    }
    public void openIntent(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
//    save.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//
//            btnInsert();
//
//        }
//    });

    public void btnInsert (View view){

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               getValues();
               ref.setValue(Company);
                Toast.makeText(Input.this,"Successfully inserted",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
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
//    }
}
