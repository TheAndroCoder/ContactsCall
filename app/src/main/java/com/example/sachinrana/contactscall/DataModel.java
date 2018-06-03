package com.example.sachinrana.contactscall;

import android.database.Cursor;

/**
 * Created by Sachin Rana on 08-02-2018.
 */

public class DataModel {
    String Name;
    String phone;
    String Time;
    public DataModel(String Name,String phone){
        this.Name=Name;
        this.phone=phone;
    }
    public DataModel(String Name,String phone,String Time){
        this.Name=Name;
        this.phone=phone;
        this.Time = Time;
    }


    public String getName() {
        if(Name==null){
            return "No Name";
        }
        return Name;
    }


    public String getPhone() {
        return phone;
    }
    public String getTime(){return Time;}

}
