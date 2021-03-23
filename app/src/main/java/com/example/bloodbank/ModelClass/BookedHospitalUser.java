package com.example.bloodbank.ModelClass;

public class BookedHospitalUser {

    String bookedname;
    String bookedphone;
    String bookedaddress;


    public BookedHospitalUser(String bookedname, String bookedphone, String bookedaddress) {

        this.bookedname = bookedname;
        this.bookedphone = bookedphone;
        this.bookedaddress = bookedaddress;
    }

    public String getBookedname() {
        return bookedname;
    }

    public void setBookedname(String bookedname) {
        this.bookedname = bookedname;
    }

    public String getBookedphone() {
        return bookedphone;
    }

    public void setBookedphone(String bookedphone) {
        this.bookedphone = bookedphone;
    }

    public String getBookedaddress() {
        return bookedaddress;
    }

    public void setBookedaddress(String bookedaddress) {
        this.bookedaddress = bookedaddress;
    }
}
