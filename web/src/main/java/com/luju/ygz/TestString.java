package com.luju.ygz;

import java.util.*;

public class TestString {

    public static void main(String[] args) {

        String a[] = {"a","b"};
        String b[] = {"b","a"};
        Arrays.sort(a);
        Arrays.sort(b);

        System.out.println( Arrays.equals(a, b));





//        String a = "12 ab";
//        String b = "34 de";
//
//        String c = "ab 12";
//        String d = "de 34";
//
//        System.out.println(a.equals(c));
//        System.out.println(a.equals(d));
//        System.out.println(b.equals(c));
//        System.out.println(b.equals(d));
//
//        if(!a.equals(c) && !a.equals(d) && !b.equals(c) && !b.equals(d)) {
//            System.out.println("insert");
//        }

//        String a = "abc";
//        String b = "bc";
//
//        String c = "c";
//        String d = "c";
//
//
//        System.out.println( );
//
//        if (!(a.equals(b)) && c.equals(d)) {
//            System.out.println("true");
//        }
//
//        Integer.parseInt(a);


//        List<List<List<String>>> list = new ArrayList<>();
//        List<List<List<String>>> list1 = new ArrayList<>();
//
//        List<List<String>> listList1 = new ArrayList<>();
//        List<List<String>> listList2 = new ArrayList<>();
//
//        List<String> string1 = new ArrayList<>();
//
//        string1.add("a");
//        string1.add("b");
//
//        List<String> string2 = new ArrayList<>();
//        string2.add("b");
//        string2.add("a");
//
//        List<String> string3 = new ArrayList<>();
//
//        string3.add("1");
//        string3.add("2");
//
//        listList1.add(string1);
//
//
//        listList2.add(string2);
//
//        listList1.add(string3);
//
//        System.out.println(listList1);
//        System.out.println(listList2);
//        System.out.println(listList1.removeAll(listList2));





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

