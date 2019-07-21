package com.example.companies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<Services> mData;

    public RecyclerViewAdapter(Context mContext, List<Services> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v= LayoutInflater.from(mContext).inflate(R.layout.item_services,viewGroup,false);
        MyViewHolder vHolder = new MyViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

        myViewHolder.tv_name.setText(mData.get(position).getName());
        myViewHolder.tv_address.setText(mData.get(position).getAddress());
        myViewHolder.tv_phone.setText(mData.get(position).getPhone());
        myViewHolder.tv_web_address.setText(mData.get(position).getWeb_address());
        myViewHolder.img.setImageResource(mData.get(position).getPhoto());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_name;
        private TextView tv_address;
        private TextView tv_phone;
        private TextView tv_web_address;
        private ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = (TextView) itemView.findViewById(R.id.name_company);
            tv_address = (TextView) itemView.findViewById(R.id.address_company);
            tv_phone = (TextView) itemView.findViewById(R.id.phone_company);
            tv_web_address = (TextView) itemView.findViewById(R.id.web_address_company);
            img = (ImageView) itemView.findViewById(R.id.img_services);

        }
    }
}
