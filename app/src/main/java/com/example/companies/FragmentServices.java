package com.example.companies;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentServices extends Fragment {

    View v;
    private RecyclerView myrecyclerview;
    private List<Companies> ServicesCompanies;
    private FragmentList fragmentList;

    public FragmentServices() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.services_fragment, container, false);
        myrecyclerview = v.findViewById(R.id.services_recyclerview);
//        RecyclerViewAdapter recyclerAdapter= new RecyclerViewAdapter(getContext(),lstServices);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
//        myrecyclerview.setAdapter(recyclerAdapter);
        ServicesCompanies = new ArrayList<>();
        getCompaniesList();
        return v;
    }

    private void getCompaniesList() {
        final DatabaseReference database_reference = FirebaseDatabase.getInstance().getReference("Companies");
        database_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ServicesCompanies.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Companies company;
                    company = snapshot.getValue(Companies.class);

                    if (company.isCheck_services()){
                        ServicesCompanies.add(company);
                    }

                }

                fragmentList = new FragmentList(getContext(), ServicesCompanies);
                myrecyclerview.setAdapter(fragmentList);
                fragmentList.OnItemClickListener((FragmentList.OnItemClickListener) FragmentServices.this);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

//example of list
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        lstServices=new ArrayList<>();
//        lstServices.add(new Services("Food and Drinks","ulica1","078965426","https//Food and Drinks.com",R.drawable.img1));
//        lstServices.add(new Services("Club","ulica2","078965427","https//Club.com",R.drawable.img1));
//        lstServices.add(new Services("Car repair","ulica3","078565426","https//CarRepair.com",R.drawable.img1));
//        lstServices.add(new Services("Buystuff","ulica4","078965487","https//BuyStuff.com",R.drawable.img1));
//        lstServices.add(new Services("Mebel","ulica5","077965426","https//Mebel.com",R.drawable.img1));
//        lstServices.add(new Services("Osvetluvanje","ulica6","075123987","https//Osvetluvanje.com",R.drawable.img1));
//        lstServices.add(new Services("BuyCar","ulica7","078965426","https//Buycar.com",R.drawable.img1));
//        lstServices.add(new Services("Findsmth","ulica8","078965957","https//Findsmth.com",R.drawable.img1));
//    }


}
