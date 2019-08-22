package com.example.companies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FragmentList extends RecyclerView.Adapter<FragmentList.ViewHolder> {

    private List<Companies> allCompanies;
    private List<Companies> previewCompanies;
    private OnItemClickListener clickListener;
    private Context context;

    public interface OnItemClickListener {
        void onItemClick(int place);
    }

    public void OnItemClickListener(OnItemClickListener listener) {

        clickListener = listener;
    }

    //set
    public FragmentList(Context context, List<Companies> previewCompanies) {
        this.context = context;
        this.previewCompanies = previewCompanies;
        allCompanies = new ArrayList<>(previewCompanies);

    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.info_companies, viewGroup, false);
        return new FragmentList.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Companies company;

        company = previewCompanies.get(i);
        viewHolder.companyName.setText(company.getName());
        viewHolder.companyAddress.setText(company.getAddress());
        viewHolder.companyTelephone.setText(company.getTelephone());
    }

    @Override
    public int getItemCount() {
        return previewCompanies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView companyName;
        private TextView companyAddress;
        private TextView companyTelephone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            companyName = itemView.findViewById(R.id.info_name_id);
//            companyAddress = itemView.findViewById(R.id.info_address_id);
            companyTelephone = itemView.findViewById(R.id.info_phone_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) {
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION) {
                            clickListener.onItemClick(position);
                        }
                    }
                }
            });

        }

    }

}
