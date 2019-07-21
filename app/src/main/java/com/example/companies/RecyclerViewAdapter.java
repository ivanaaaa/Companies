package com.example.companies;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<Services> mData;
    Dialog myDialog;


    public RecyclerViewAdapter(Context mContext, List<Services> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_services, viewGroup, false);
        final MyViewHolder vHolder = new MyViewHolder(v);

        //initialization Dialog
        myDialog = new Dialog(mContext);
        myDialog.setContentView(R.layout.info_companies);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        vHolder.item_sevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView info_name_tv = (TextView) myDialog.findViewById(R.id.info_name_id);
                TextView info_phone_tv = (TextView) myDialog.findViewById(R.id.info_phone_id);
                ImageView info_company_img = (ImageView) myDialog.findViewById(R.id.info_image_id);
                info_name_tv.setText(mData.get(vHolder.getAdapterPosition()).getName());
                info_phone_tv.setText(mData.get(vHolder.getAdapterPosition()).getPhone());
                info_company_img.setImageResource(mData.get(vHolder.getAdapterPosition()).getPhoto());

                Toast.makeText(mContext, "Test Click" + String.valueOf(vHolder.getAdapterPosition()), Toast.LENGTH_SHORT).show();
                myDialog.show();
            }
        });
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

        private LinearLayout item_sevices;
        private TextView tv_name;
        private TextView tv_address;
        private TextView tv_phone;
        private TextView tv_web_address;
        private ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            item_sevices = (LinearLayout) itemView.findViewById(R.id.services_item_id);
            tv_name = (TextView) itemView.findViewById(R.id.name_company);
            tv_address = (TextView) itemView.findViewById(R.id.address_company);
            tv_phone = (TextView) itemView.findViewById(R.id.phone_company);
            tv_web_address = (TextView) itemView.findViewById(R.id.web_address_company);
            img = (ImageView) itemView.findViewById(R.id.img_services);

        }
    }
}
