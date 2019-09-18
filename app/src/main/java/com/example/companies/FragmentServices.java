package com.example.companies;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
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

public class FragmentServices extends Fragment implements FragmentList.OnItemClickListener {


    public static final String Company_name = "Name";
    public static final String Company_address = "Address";
    public static final String Company_email = "Email";
    public static final String Company_phone = "Phone";
    public static final String Company_web = "Web site";
    View v;
    private RecyclerView myRecyclerView;
    private List<Companies.CompanyData> servicesList;
    private FragmentList companiesList;

    private SearchView searchView;

    public FragmentServices() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.services_fragment, container, false);
        myRecyclerView = v.findViewById(R.id.services_recyclerView);


        searchView = v.findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                companiesList.getFilter().filter(s);
                return false;
            }
        });
//        RecyclerViewAdapter recyclerAdapter= new RecyclerViewAdapter(getContext(),lstServices);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        myRecyclerView.setAdapter(new RecyclerView.Adapter() {
//            @Override
//            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                return null;
//            }
//
//            @Override
//            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//
//            }
//
//            @Override
//            public int getItemCount() {
//                return 0;
//            }
//        });
//        myrecyclerview.setAdapter(recyclerAdapter);
        servicesList = new ArrayList<Companies.CompanyData>();
        takeCompaniesList();
        return v;
    }

    private void takeCompaniesList() {
        final DatabaseReference database_reference = FirebaseDatabase.getInstance().getReference();
        database_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                servicesList.clear();

                for (DataSnapshot snapshot : dataSnapshot.child("Companies").getChildren()) {
                    Companies.CompanyData company;
                    company = snapshot.getValue(Companies.CompanyData.class);

                    if (company != null && company.isCheck_services()) {
                        servicesList.add(company);
                    }

                }

                companiesList = new FragmentList(getContext(), servicesList);
                myRecyclerView.setAdapter(companiesList);
                companiesList.OnItemClickListener(FragmentServices.this);

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

    @Override
    public void onItemClick(int position) {
        Intent intent;

        intent = new Intent(getActivity(), InfoActivity.class);

        Companies.CompanyData clickedItem = servicesList.get(position);

        intent.putExtra(Company_name, clickedItem.getName());
        intent.putExtra(Company_address, clickedItem.getAddress());
        intent.putExtra(Company_email, clickedItem.getEmail());
        intent.putExtra(Company_phone, clickedItem.getTelephone());
        intent.putExtra(Company_web, clickedItem.getWeb_site());

        startActivity(intent);
    }

}
