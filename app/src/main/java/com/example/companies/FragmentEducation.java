package com.example.companies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FragmentEducation extends Fragment {
    public FragmentEducation() {
    }

    View v;
    private RecyclerView myrecyclerview;
    private List<Services> lstEducation;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.education_fragment,container,false);
        myrecyclerview = (RecyclerView) v.findViewById(R.id.education_recyclerview);
        RecyclerViewAdapter recyclerAdapter= new RecyclerViewAdapter(getContext(),lstEducation);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerAdapter);
        return v;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lstEducation=new ArrayList<>();
        lstEducation.add(new Services("University Goce Delcev","Stip","078965426","https//ugd.com",R.drawable.img1));
        lstEducation.add(new Services("OU Kiril i Metodij","Kocani","078965427","https//kim.com",R.drawable.img1));
        lstEducation.add(new Services("Univerzitet Kiril i Metodij","Skopje","078565426","https//ukim.com",R.drawable.img1));
        lstEducation.add(new Services("SOU ljupco Santov","Kocani","078965487","https//souljp.com",R.drawable.img1));
        lstEducation.add(new Services("SOU Kole Nihtenin","Stip","077965426","https//soukn.com",R.drawable.img1));
        lstEducation.add(new Services("OU Vanco Prke","Stip","075123987","https//ouvp.com",R.drawable.img1));
        lstEducation.add(new Services("Jane Sandanski","Stip","078965426","https//janesandanski.com",R.drawable.img1));
        lstEducation.add(new Services("OU Nikola Karev","Kocani","078965957","https//ounk.com",R.drawable.img1));
    }
}
