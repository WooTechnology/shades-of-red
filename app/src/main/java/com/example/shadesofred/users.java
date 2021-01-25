package com.example.shadesofred;

public class users {
    public String name , email , number , blood , state , city , rbstate ;

    public users(){

    }



    public users(String name, String email, String number, String blood, String state, String city , String rbstate) {
        this.name = name;
        this.email = email;
        this.number = number;
        this.blood = blood;
        this.state = state;
        this.city = city;
        this.rbstate = rbstate;
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
