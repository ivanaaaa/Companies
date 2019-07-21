package com.example.companies;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FragmentIndustry extends Fragment {

    View v;
    private RecyclerView myrecyclerview;
    private List<Services> lstIndustry;

    public FragmentIndustry() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.industry_fragment,container,false);
        myrecyclerview = (RecyclerView) v.findViewById(R.id.industry_recyclerview);
        RecyclerViewAdapter recyclerAdapter= new RecyclerViewAdapter(getContext(),lstIndustry);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerAdapter);
        return v;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lstIndustry=new ArrayList<>();
        lstIndustry.add(new Services("Evropa","Kocani","078965426","https//evropa.com.mk",R.drawable.img1));
        lstIndustry.add(new Services("Vincini","Vinica","078965427","https//vincini.com.mk",R.drawable.img1));
        lstIndustry.add(new Services("","Goce Delcev br.159","078565426","https//milinasweets.com",R.drawable.img1));
        lstIndustry.add(new Services("Buystuff","ulica4","078965487","https//BuyStuff.com",R.drawable.img1));
        lstIndustry.add(new Services("Mebel","ulica5","077965426","https//Mebel.com",R.drawable.img1));
        lstIndustry.add(new Services("Osvetluvanje","ulica6","075123987","https//Osvetluvanje.com",R.drawable.img1));
        lstIndustry.add(new Services("BuyCar","ulica7","078965426","https//Buycar.com",R.drawable.img1));
        lstIndustry.add(new Services("Findsmth","ulica8","078965957","https//Findsmth.com",R.drawable.img1));
    }
}
