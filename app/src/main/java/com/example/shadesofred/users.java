package com.example.shadesofred;

public class users {
    public String name , email , number , blood , state , city , rbstate, filter ;

    public users(){

    }



    public users(String name, String email, String number, String blood, String state, String city , String rbstate, String filter) {
        this.name = name;
        this.email = email;
        this.number = number;
        this.blood = blood;
        this.state = state;
        this.city = city;
        this.rbstate = rbstate;
        this.filter = filter;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getEmail() {
        return email;
    }

    public String getBlood() {
        return blood;
    }
    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getRbstate() {
        return rbstate;
    }
}
