package com.example.lenovo.notification1;

public class MyTime {
    public MyTime() {

    }


    public static String getTime(int year,int mon,int day,int hour, int min) {

        String mins,mons,days,hours;
        if(hour<10)
            hours = 0+String.valueOf(hour);
        else
            hours = String.valueOf(hour);
        if(min<10)
            mins =0+ String.valueOf(min);
        else
            mins = String.valueOf(min);
        if(mon<10)
            mons =0+ String.valueOf(mon);
        else
            mons = String.valueOf(mon);
        if(day<10)
            days = 0+String.valueOf(day);
        else
            days = String.valueOf(day);
//        days = String.valueOf(day);


        return year+":"+mons+":"+days+":"+hours+":"+mins;


    }

    public static String getTimeShow(int year,int mon,int day,int hour, int min) {

        String mins,mons,days,hours;
        if(hour<10)
            hours = 0+String.valueOf(hour);
        else
            hours = String.valueOf(hour);
        if(min<10)
            mins =0+ String.valueOf(min);
        else
            mins = String.valueOf(min);
        if(mon<10)
            mons =0+ String.valueOf(mon);
        else
            mons = String.valueOf(mon);
        if(day<10)
            days = 0+String.valueOf(day);
        else
            days = String.valueOf(day);
//        days = String.valueOf(day);


        return year+"/"+mons+"/"+days+"    "+hours+":"+mins;


    }

}