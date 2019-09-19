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

public class FragmentEducation extends Fragment implements FragmentList.OnItemClickListener {

    public static final String Company_name = "Name";
    public static final String Company_address = "Address";
    public static final String Company_email = "Email";
    public static final String Company_phone = "Phone";
    public static final String Company_web = "Web site";
    View v;
    private RecyclerView myRecyclerView;
    private List<Companies.CompanyData> educationList;
    private FragmentList companiesList;
    private SearchView searchView;

    public FragmentEducation() {
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.education_fragment, container, false);
        myRecyclerView = v.findViewById(R.id.education_recyclerView);
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
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        educationList = new ArrayList<Companies.CompanyData>();
        takeCompaniesList();
        return v;
    }

    private void takeCompaniesList() {
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Companies");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                educationList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Companies.CompanyData company;
                    company = snapshot.getValue(Companies.CompanyData.class);

                    if (company.isCheck_education()) {
                        educationList.add(company);
                    }
                }
                companiesList = new FragmentList(getContext(), educationList);
                myRecyclerView.setAdapter(companiesList);
                companiesList.OnItemClickListener(FragmentEducation.this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //hard coded
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        lstEducation=new ArrayList<>();
//        lstEducation.add(new Services("University Goce Delcev","Stip","078965426","https//ugd.com",R.drawable.img1));
//        lstEducation.add(new Services("OU Kiril i Metodij","Kocani","078965427","https//kim.com",R.drawable.img1));
//        lstEducation.add(new Services("Univerzitet Kiril i Metodij","Skopje","078565426","https//ukim.com",R.drawable.img1));
//        lstEducation.add(new Services("SOU ljupco Santov","Kocani","078965487","https//souljp.com",R.drawable.img1));
//        lstEducation.add(new Services("SOU Kole Nihtenin","Stip","077965426","https//soukn.com",R.drawable.img1));
//        lstEducation.add(new Services("OU Vanco Prke","Stip","075123987","https//ouvp.com",R.drawable.img1));
//        lstEducation.add(new Services("Jane Sandanski","Stip","078965426","https//janesandanski.com",R.drawable.img1));
//        lstEducation.add(new Services("OU Nikola Karev","Kocani","078965957","https//ounk.com",R.drawable.img1));
//    }
    @Override
    public void onItemClick(int position) {
        Intent intent;
        intent = new Intent(getActivity(), InfoActivity.class);
        Companies.CompanyData clickedItem = educationList.get(position);
        intent.putExtra(Company_name, clickedItem.getName());
        intent.putExtra(Company_address, clickedItem.getAddress());
        intent.putExtra(Company_email, clickedItem.getEmail());
        intent.putExtra(Company_phone, clickedItem.getTelephone());
        intent.putExtra(Company_web, clickedItem.getWeb_site());
        startActivity(intent);
    }
}
