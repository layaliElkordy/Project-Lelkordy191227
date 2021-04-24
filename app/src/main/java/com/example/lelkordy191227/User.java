package com.example.lelkordy191227;

public class User {
    public String emailAddress;
    public String firstName;
    public String lastName;
    public String phoneNumber;
    public String userId;

    public User(){

    }



    public User (String emailAddress,String firstName,String lastName,String phoneNumber,String userId){
        this.emailAddress=emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.userId = userId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUserId() {
        return userId;
    }
}