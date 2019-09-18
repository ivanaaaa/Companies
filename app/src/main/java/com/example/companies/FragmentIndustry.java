package com.example.companies;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

public class FragmentIndustry extends Fragment implements FragmentList.OnItemClickListener {
    public static final String Company_name = "Name";
    public static final String Company_address = "Address";
    public static final String Company_email = "Email";
    public static final String Company_phone = "Phone";
    public static final String Company_web = "Web site";

    View v;
    private RecyclerView myRecyclerView;
    private List<Companies> industryList;
    private FragmentList companiesList;

    private SearchView searchView;

    public FragmentIndustry() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.industry_fragment, container, false);
        myRecyclerView = (RecyclerView) v.findViewById(R.id.industry_recyclerView);

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

//        RecyclerViewAdapter recyclerAdapter= new RecyclerViewAdapter(getContext(),lstIndustry);
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

        industryList = new ArrayList<>();
        takeCompaniesList();
        return v;
    }


    private void takeCompaniesList() {
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Companies");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                industryList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Companies company;
                    company = snapshot.getValue(Companies.class);

                    if (company.isCheck_industry()) {
                        industryList.add(company);
                    }

                }

                companiesList = new FragmentList(getContext(), industryList);
                myRecyclerView.setAdapter(companiesList);
                companiesList.OnItemClickListener(FragmentIndustry.this);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        lstIndustry=new ArrayList<>();
//        lstIndustry.add(new Services("Evropa","Kocani","078965426","https//evropa.com.mk",R.drawable.img1));
//        lstIndustry.add(new Services("Vincini","Vinica","078965427","https//vincini.com.mk",R.drawable.img1));
//        lstIndustry.add(new Services("","Goce Delcev br.159","078565426","https//milinasweets.com",R.drawable.img1));
//        lstIndustry.add(new Services("Buystuff","ulica4","078965487","https//BuyStuff.com",R.drawable.img1));
//        lstIndustry.add(new Services("Mebel","ulica5","077965426","https//Mebel.com",R.drawable.img1));
//        lstIndustry.add(new Services("Osvetluvanje","ulica6","075123987","https//Osvetluvanje.com",R.drawable.img1));
//        lstIndustry.add(new Services("BuyCar","ulica7","078965426","https//Buycar.com",R.drawable.img1));
//        lstIndustry.add(new Services("Findsmth","ulica8","078965957","https//Findsmth.com",R.drawable.img1));
//    }

    @Override
    public void onItemClick(int position) {
        Intent intent;

        intent = new Intent(getActivity(), InfoActivity.class);

        Companies clickedItem = industryList.get(position);

        intent.putExtra(Company_name, clickedItem.getName());
        intent.putExtra(Company_address, clickedItem.getAddress());
        intent.putExtra(Company_email, clickedItem.getEmail());
        intent.putExtra(Company_phone, clickedItem.getTelephone());
        intent.putExtra(Company_web, clickedItem.getWeb_site());

        startActivity(intent);
    }
}
