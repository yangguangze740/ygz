package com.luju.ygz;



import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TestString {
    public static void main(String[] args) {

        String time = "14:00";

        Date date;
        Long st;

        DateFormat sdf = new SimpleDateFormat("HH:mm");

        try {
            System.out.println(sdf.parse(time));
            date = sdf.parse(time);
            st = date.getTime();
            st = st + 600000;
            date.setTime(st);
            System.out.println(date);
            System.out.println(sdf.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }



//        ts = Timestamp.valueOf(simpleDateFormat.format(time));
//
//        System.out.println(ts);



//        String admin = "admin";
//        String username = "admin";
//        String password = "admin";
//
//        String admin2 = "admin";
//        System.out.println(admin == admin2);


//        System.out.println(username == admin || password == admin);
//        System.out.println(username == admin && password == admin);

//        String s = "禁峰,超长,超限";
//        if(s.indexOf("禁峰")!= -1) {
//        }
//
//        String s1 = "02";
//        System.out.println(Integer.parseInt(s1));
    }
}
