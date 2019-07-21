package com.example.companies;

public class Education {
    private String Name;
    private String Address;
    private String Phone;
    private String Web_address;
    private int Photo;

    public Education() {
    }

    public Education(String name, String address, String phone, String web_address, int photo) {
        Name = name;
        Address = address;
        Phone = phone;
        Web_address = web_address;
        Photo = photo;
    }


    //getter
    public String getName() {
        return Name;
    }

    public String getAddress() {
        return Address;
    }

    public String getPhone() {
        return Phone;
    }

    public String getWeb_address() {
        return Web_address;
    }

    public int getPhoto() {
        return Photo;
    }
    //setter


    public void setName(String name) {
        Name = name;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public void setWeb_address(String web_address) {
        Web_address = web_address;
    }

    public void setPhoto(int photo) {
        Photo = photo;
    }
}
