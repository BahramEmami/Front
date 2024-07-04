package com.example.client;

import java.sql.Date;

public class User {

    private String ID;
    private String email;
    private String firstName;
    private String LastName;
    private String passWord;
    private String additionalName;
    private String country;
    private String city;
    private String profession;

    enum Work_Type{
        WANT_TO_HIRED,
        LOOKING_FOR_JOB,
        WANT_TO_PROVIDE_SERVICE
    }
    private Work_Type workType;
    private String token;


    public User(String ID, String email, String firstName, String lastName, String passWord,String additionalName, String workType , String token) {

        this.ID = ID;
        this.email = email;
        this.firstName = firstName;
        this.LastName = lastName;
        this.passWord = passWord;
        this.additionalName = additionalName;


        switch (workType){
            case "want_to_hired" -> this.workType = Work_Type.WANT_TO_HIRED;
            case "looking_for_job" -> this.workType = Work_Type.LOOKING_FOR_JOB;
            case "want_to_provide_service" -> this.workType = Work_Type.WANT_TO_PROVIDE_SERVICE;

        }
        this.token = token;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getAdditionalName() {
        return additionalName;
    }

    public void setAdditionalName(String additionalName) {
        this.additionalName = additionalName;
    }



    public String getWorkType() {
        return workType.name().toLowerCase();
    }

    public void setWorkType(String workType) {
        switch (workType){
            case "want_to_hired" -> this.workType = Work_Type.WANT_TO_HIRED;
            case "looking_for_job" -> this.workType = Work_Type.LOOKING_FOR_JOB;
            case "want_to_provide_service" -> this.workType = Work_Type.WANT_TO_PROVIDE_SERVICE;

        }    }
}
