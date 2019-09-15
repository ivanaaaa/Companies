package com.example.companies;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class FragmentFun extends Fragment implements FragmentList.OnItemClickListener{

    public static final String Company_name = "Name";
    public static final String Company_address = "Address";
    public static final String Company_email = "Email";
    public static final String Company_phone = "Phone";
    public static final String Company_web = "Web site";
    View v;
    private RecyclerView myrecyclerview;
    private List<Companies> funCompanies;
    private FragmentList fragmentList;

    public FragmentFun() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fun_fragment, container, false);
        myrecyclerview = (RecyclerView) v.findViewById(R.id.fun_recyclerview);

//        RecyclerViewAdapter recyclerAdapter= new RecyclerViewAdapter(getContext(),lstFun);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
//        myrecyclerview.setAdapter(recyclerAdapter);
        myrecyclerview.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 0;
            }
        });
        funCompanies = new ArrayList<>();
        getCompaniesList();
        return v;
    }

    private void getCompaniesList() {
        final DatabaseReference database_reference = FirebaseDatabase.getInstance().getReference("Companies");
        database_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                funCompanies.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Companies company;
                    company = snapshot.getValue(Companies.class);

                    if (company.isCheck_fun()) {
                        funCompanies.add(company);
                    }

                }

                fragmentList = new FragmentList(getContext(), funCompanies);
                myrecyclerview.setAdapter(fragmentList);
                fragmentList.OnItemClickListener((FragmentList.OnItemClickListener) FragmentFun.this);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        lstFun=new ArrayList<>();
//        lstFun.add(new Services("Igroteka","ulica1","078965426","https//Food and Drinks.com",R.drawable.img1));
//        lstFun.add(new Services("Club","ulica2","078965427","https//Club.com",R.drawable.img1));
//        lstFun.add(new Services("Car repair","ulica3","078565426","https//CarRepair.com",R.drawable.img1));
//        lstFun.add(new Services("Buystuff","ulica4","078965487","https//BuyStuff.com",R.drawable.img1));
//        lstFun.add(new Services("Mebel","ulica5","077965426","https//Mebel.com",R.drawable.img1));
//        lstFun.add(new Services("Osvetluvanje","ulica6","075123987","https//Osvetluvanje.com",R.drawable.img1));
//        lstFun.add(new Services("BuyCar","ulica7","078965426","https//Buycar.com",R.drawable.img1));
//        lstFun.add(new Services("Findsmth","ulica8","078965957","https//Findsmth.com",R.drawable.img1));
//    }

    @Override
    public void onItemClick(int position) {
        Intent intent;

        intent = new Intent(getActivity(),InfoActivity.class);

        Companies clickedItem = funCompanies.get(position);

        intent.putExtra(Company_name,clickedItem.getName());
        intent.putExtra(Company_address,clickedItem.getAddress());
        intent.putExtra(Company_email,clickedItem.getEmail());
        intent.putExtra(Company_phone,clickedItem.getTelephone());
        intent.putExtra(Company_web,clickedItem.getWeb_site());

        startActivity(intent);
    }
}
