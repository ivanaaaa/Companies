package com.example.companies;

import android.app.Application;

import com.firebase.client.Firebase;

import java.io.Serializable;

public class Companies extends Application {

    private String Company_id;
    private String Name;
    private String Address;
    private String Latitude;
    private String Longitude;
    private String Email;
    private String Category;
    private String Telephone;
    private String Web_site;
    private boolean check_services;
    private boolean check_fun;
    private boolean check_industry;
    private boolean check_education;

    public Companies(String company_id, String name, String address, String lat, String lon, String mail, String tel, String web_site, boolean services, boolean fun, boolean industry, boolean education) {
    }

    public Companies(String company_id, String name, String address, String latitude, String longitude, String email, String category, String telephone, String web_site, Boolean check_services, Boolean check_fun, Boolean check_industry, Boolean check_education) {
        Company_id = company_id;
        Name = name;
        Address = address;
        Latitude = latitude;
        Longitude = longitude;
        Email = email;
        Category = category;
        Telephone = telephone;
        Web_site = web_site;
        this.check_services = check_services;
        this.check_fun = check_fun;
        this.check_industry = check_industry;
        this.check_education = check_education;
    }

    public String getCompany_id() {
        return Company_id;
    }

    public void setCompany_id(String company_id) {
        Company_id = company_id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public String getWeb_site() {
        return Web_site;
    }

    public void setWeb_site(String web_site) {
        Web_site = web_site;
    }

    public Boolean getCheck_services() {
        return check_services;
    }

    public void setCheck_services(Boolean check_services) {
        this.check_services = check_services;
    }

    public Boolean getCheck_fun() {
        return check_fun;
    }

    public void setCheck_fun(Boolean check_fun) {
        this.check_fun = check_fun;
    }

    public Boolean getCheck_industry() {
        return check_industry;
    }

    public void setCheck_industry(Boolean check_industry) {
        this.check_industry = check_industry;
    }

    public Boolean getCheck_education() {
        return check_education;
    }

    public void setCheck_education(Boolean check_education) {
        this.check_education = check_education;
    }
}