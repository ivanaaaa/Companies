package com.example.companies;

import android.app.Application;

//import com.firebase.client.Firebase;

//import java.io.Serializable;

public class Companies extends Application {
    public static class CompanyData {
        private String Company_id;
        private String Name;
        private String Address;
        private String Latitude;
        private String Longitude;
        private String Email;
        private String Telephone;
        private String Web_site;
        private boolean check_services;
        private boolean check_fun;
        private boolean check_industry;
        private boolean check_education;

        public CompanyData() {
        }

        public CompanyData(String company_id, String name, String address, String latitude, String longitude, String email, String telephone, String web_site, boolean check_services, boolean check_fun, boolean check_industry, boolean check_education) {
            this.Company_id = company_id;
            this.Name = name;
            this.Address = address;
            this.Latitude = latitude;
            this.Longitude = longitude;
            this.Email = email;
            this.Telephone = telephone;
            this.Web_site = web_site;
            this.check_services = check_services;
            this.check_fun = check_fun;
            this.check_industry = check_industry;
            this.check_education = check_education;
        }

        public String getCompany_id() {
            return Company_id;
        }

        public void setCompany_id(String company_id) {
            this.Company_id = company_id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            this.Name = name;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String address) {
            this.Address = address;
        }

        public String getLatitude() {
            return Latitude;
        }

        public void setLatitude(String latitude) {
            this.Latitude = latitude;
        }

        public String getLongitude() {
            return Longitude;
        }

        public void setLongitude(String longitude) {
            this.Longitude = longitude;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String email) {
            this.Email = email;
        }

        public String getTelephone() {
            return Telephone;
        }

        public void setTelephone(String telephone) {
            this.Telephone = telephone;
        }

        public String getWeb_site() {
            return Web_site;
        }

        public void setWeb_site(String web_site) {
            this.Web_site = web_site;
        }

        public boolean isCheck_services() {
            return check_services;
        }

        public void setCheck_services(boolean check_services) {
            this.check_services = check_services;
        }

        public boolean isCheck_fun() {
            return check_fun;
        }

        public void setCheck_fun(boolean check_fun) {
            this.check_fun = check_fun;
        }

        public boolean isCheck_industry() {
            return check_industry;
        }

        public void setCheck_industry(boolean check_industry) {
            this.check_industry = check_industry;
        }

        public boolean isCheck_education() {
            return check_education;
        }

        public void setCheck_education(boolean check_education) {
            this.check_education = check_education;
        }
    }
}