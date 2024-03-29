package com.example.companies;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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


public class FragmentInfo extends Fragment implements FragmentList.OnItemClickListener {

    public static final String Company_name = "Name";
    public static final String Company_address = "Address";
    public static final String Company_email = "Email";
    public static final String Company_phone = "Phone";
    public static final String Company_web = "Web site";
    private RecyclerView recyclerView;
    private FragmentList companyAdapter;
    private List<Companies.CompanyData> companies;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_fragment, container, false);
        recyclerView = view.findViewById(R.id.info_fragment);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        companies = new ArrayList<Companies.CompanyData>();
        getCompanies();
        return view;
    }

    private void getCompanies() {
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Companies");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                companies.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Companies.CompanyData company = snapshot.getValue(Companies.CompanyData.class);
                    if (company.isCheck_services() || company.isCheck_industry() || company.isCheck_fun() || company.isCheck_education()) {
                        companies.add(company);
                    }
                }
                companyAdapter = new FragmentList(getContext(), companies);
                recyclerView.setAdapter(companyAdapter);
                companyAdapter.OnItemClickListener(FragmentInfo.this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(getActivity(), InfoActivity.class);
        Companies.CompanyData clickedItem = companies.get(position);
        intent.putExtra(Company_name, clickedItem.getName());
        intent.putExtra(Company_address, clickedItem.getAddress());
        intent.putExtra(Company_email, clickedItem.getEmail());
        intent.putExtra(Company_phone, clickedItem.getTelephone());
        intent.putExtra(Company_web, clickedItem.getWeb_site());
        startActivity(intent);
    }
}

