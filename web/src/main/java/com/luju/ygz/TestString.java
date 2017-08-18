package com.luju.ygz;

import com.luju.pojo.DcPlanInfo;

import java.util.*;

public class TestString {

    public static void main(String[] args) {

        List<String> string1 = new ArrayList<>();
        string1.add("1");
        string1.add("2");
        string1.add("3");

        List<String> string2 = new ArrayList<>();
        string2.add("a");
        string2.add("b");
        string2.add("c");

        List<List<List<String>>> list = new ArrayList<List<List<String>>>();
        List<List<List<String>>> list1 = new ArrayList<List<List<String>>>();
        List<List<String>> listList = new ArrayList<>();

        for (List<String> s1 :listList) {
            for (String s : s1){
                s = "a";
            }
            System.out.println(s1);
        }

        for (List<List<String>> lists : list) {
            for (List<String> strings : lists) {
                for (String s : strings){
                    s = "1";
                    s = "2";
                }
                System.out.println(strings);
            }
        }

        for (List<List<String>> lists : list1) {
            for (List<String> strings : lists) {
                for (String s : strings){
                    s = "1";
                    s = "3";
                }
            }
        }

        for (int i = 0; i< list1.size(); i++) {
            List<List<String>>  lists = list1.get(i);
            for (List<String> strings : lists) {
                for (String s : strings){
                    s = "2";
                    s = "1";
                }
            }
        }
        boolean b = Collections.disjoint(list,list1);
        if (b = true) {
            System.out.println("不同");
        } else {
            System.out.println("相同");
        }


//        List<DcPlanInfo> list = new ArrayList<DcPlanInfo>();
//        Set<String> set=new HashSet<String>();
//        Collections.sort(list, new Comparator<Object>() {
//
//            @Override
//            public int compare(Object o1, Object o2) {
//
//                return o2.toString().compareTo(o1.toString());
//            }
//        });
    }
}

//        String fu = "-";
//        String cs = "1";
//        int a = Integer.parseInt(fu+cs);
//        System.out.println(a);
//        int b = 2;
//        System.out.println(a+b);

//        int a = -1;
//        int b = -2;
//
//        int c = a + b;
//        System.out.println(c);

//        TestString testString = new TestString();
//
//        System.out.println(testString.test());
//
//
//    }
//
//    public int test () {
//        for (int k = 0;k<10; k++) {
//            System.out.println("k");
//            return k;
//        }
//        return 0;

//        String time = "14:00";
//
//        Timestamp ts = new Timestamp(Calendar.getInstance().getTime().getTime());
//        Timestamp ts1;
//        System.out.println(ts);
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//        StringBuffer buffer = new StringBuffer(sdf.format(ts));
//        buffer.append(" "+time+":00");
//        System.out.println("buffer: "+buffer);
//        String t = buffer.toString();
//
//        ts1 = Timestamp.valueOf(t);
//        System.out.println(ts1);

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

