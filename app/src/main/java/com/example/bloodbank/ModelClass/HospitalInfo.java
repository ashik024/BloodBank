package com.example.bloodbank.ModelClass;

public class HospitalInfo {

    String name;
    String address;
    String contactnum;
    String rating;
    String status;

    public HospitalInfo(String name, String address, String contactnum, String rating, String status) {
        this.name = name;
        this.address = address;
        this.contactnum = contactnum;
        this.rating = rating;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactnum() {
        return contactnum;
    }

    public void setContactnum(String contactnum) {
        this.contactnum = contactnum;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
