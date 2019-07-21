package com.example.companies;

import android.app.Application;

import com.firebase.client.Firebase;

import java.io.Serializable;

public class Companies extends Application {

    private String Name;
    private String Address;
    private String Latitude;
    private String Longitude;
    private String Email;
    private String Category;
    private String Telephone;
    private String Web_site;

    public Companies() {
    }

    public Companies(String name, String address, String latitude, String longitude, String email, String category, String telephone, String web_site) {
        Name = name;
        Address = address;
        Latitude = latitude;
        Longitude = longitude;
        Email = email;
        Category = category;
        Telephone = telephone;
        Web_site = web_site;
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
}
