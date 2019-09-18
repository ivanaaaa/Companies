package com.example.companies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FragmentList extends RecyclerView.Adapter<FragmentList.ViewHolder> implements Filterable {

    private ArrayList<Companies.CompanyData> allCompanies;
    private List<Companies.CompanyData> previewCompanies;
    private OnItemClickListener clickListener;
    private Context context;

    public interface OnItemClickListener {
        void onItemClick(int adapter_position);
    }

    public void OnItemClickListener(OnItemClickListener listener) {

        clickListener = listener;
    }

    //set
    public FragmentList(Context context, List<Companies.CompanyData> previewCompanies) {
        this.context = context;
        this.previewCompanies = previewCompanies;
        allCompanies = new ArrayList<>(previewCompanies);

    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.companies_item, viewGroup, false);
        return new FragmentList.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Companies.CompanyData company;

        company = previewCompanies.get(i);
        viewHolder.companyName.setText(company.getName());
        viewHolder.companyAddress.setText(company.getAddress());
        viewHolder.companyTelephone.setText(company.getTelephone());
        viewHolder.companyWeb.setText(company.getWeb_site());
    }

    @Override
    public int getItemCount() {
        return previewCompanies.size();
    }


    @Override
    public Filter getFilter() {
        return doFilter;
    }

    private Filter doFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Companies.CompanyData> filterList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filterList.addAll(allCompanies);
            } else {
                String enterForFilter = constraint.toString().toLowerCase().trim();
                for (Companies.CompanyData company : allCompanies) {
                    if (company.getName().toLowerCase().contains(enterForFilter)) {
                        filterList.add(company);
                    }
                }
            }
            FilterResults resultList = new FilterResults();
            resultList.values = filterList;
            return resultList;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            previewCompanies.clear();
            previewCompanies.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView companyName;
        private TextView companyAddress;
        private TextView companyTelephone;
        private TextView companyWeb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            companyName = itemView.findViewById(R.id.companyName);
            companyAddress = itemView.findViewById(R.id.companyAddress);
            companyTelephone = itemView.findViewById(R.id.companyTelephone);
            companyWeb = itemView.findViewById(R.id.companyWeb);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null) {
                        int adapter_position = getAdapterPosition();

                        if (adapter_position != RecyclerView.NO_POSITION) {
                            clickListener.onItemClick(adapter_position);
                        }
                    }
                }
            });

        }

    }

}
