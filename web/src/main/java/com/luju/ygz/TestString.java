package com.luju.ygz;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TestString {
    public static void main(String[] args) {

        String time = "14:00";

        Timestamp ts = new Timestamp(Calendar.getInstance().getTime().getTime());
        Timestamp ts1;
        System.out.println(ts);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        StringBuffer buffer = new StringBuffer(sdf.format(ts));
        buffer.append(" "+time+":00");
        System.out.println("buffer: "+buffer);
        String t = buffer.toString();

        ts1 = Timestamp.valueOf(t);
        System.out.println(ts1);

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
//
//        try {
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        String text = "2011-10-02 18:48:05";
//        ts = Timestamp.valueOf(text);
//        System.out.println("-----");
//        System.out.println(ts);

//        Date currentDate = new Date(System.currentTimeMillis());
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd");
//        System.out.println(sdf.format(currentDate));
//
//        StringBuffer buffer = new StringBuffer(sdf.format(currentDate).toString());
//        buffer.append(" "+time+":00");
//        System.out.println("buffer: "+ buffer);
//
//        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(sdf1.format(buffer));


//        String time = "14:00";
//
//        Date date;
//        Long st;
//
//        DateFormat sdf = new SimpleDateFormat("HH:mm");
//
//        try {
//            System.out.println(sdf.parse(time));
//            date = sdf.parse(time);
//            st = date.getTime();
//            st = st + 600000;
//            date.setTime(st);
//            System.out.println(date);
//            System.out.println(sdf.format(date));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }



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
