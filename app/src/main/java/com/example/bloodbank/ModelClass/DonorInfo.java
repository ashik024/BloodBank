package com.example.bloodbank.ModelClass;

public class DonorInfo {

    String DonorName;
    String DonorEmail;
    String DonorImg;
    String DonorGender;
    String DonorPhoneOne;
    String DonorPhoneTwo;
    String DonorBloodGroup;
    String DonorAddress;
    String DonorCity;
    String DonorPostal;

    public DonorInfo(String donorName, String donorEmail, String donorImg, String donorGender, String donorPhoneOne, String donorPhoneTwo, String donorBloodGroup, String donorAddress, String donorCity, String donorPostal) {
        DonorName = donorName;
        DonorEmail = donorEmail;
        DonorImg = donorImg;
        DonorGender = donorGender;
        DonorPhoneOne = donorPhoneOne;
        DonorPhoneTwo = donorPhoneTwo;
        DonorBloodGroup = donorBloodGroup;
        DonorAddress = donorAddress;
        DonorCity = donorCity;
        DonorPostal = donorPostal;
    }

    public String getDonorName() {
        return DonorName;
    }

    public void setDonorName(String donorName) {
        DonorName = donorName;
    }

    public String getDonorEmail() {
        return DonorEmail;
    }

    public void setDonorEmail(String donorEmail) {
        DonorEmail = donorEmail;
    }

    public String getDonorImg() {
        return DonorImg;
    }

    public void setDonorImg(String donorImg) {
        DonorImg = donorImg;
    }

    public String getDonorGender() {
        return DonorGender;
    }

    public void setDonorGender(String donorGender) {
        DonorGender = donorGender;
    }

    public String getDonorPhoneOne() {
        return DonorPhoneOne;
    }

    public void setDonorPhoneOne(String donorPhoneOne) {
        DonorPhoneOne = donorPhoneOne;
    }

    public String getDonorPhoneTwo() {
        return DonorPhoneTwo;
    }

    public void setDonorPhoneTwo(String donorPhoneTwo) {
        DonorPhoneTwo = donorPhoneTwo;
    }

    public String getDonorBloodGroup() {
        return DonorBloodGroup;
    }

    public void setDonorBloodGroup(String donorBloodGroup) {
        DonorBloodGroup = donorBloodGroup;
    }

    public String getDonorAddress() {
        return DonorAddress;
    }

    public void setDonorAddress(String donorAddress) {
        DonorAddress = donorAddress;
    }

    public String getDonorCity() {
        return DonorCity;
    }

    public void setDonorCity(String donorCity) {
        DonorCity = donorCity;
    }

    public String getDonorPostal() {
        return DonorPostal;
    }

    public void setDonorPostal(String donorPostal) {
        DonorPostal = donorPostal;
    }
}
