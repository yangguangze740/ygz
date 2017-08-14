package com.luju.ygz;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class TestString {

    public static void main(String[] args) {
        Set<Person> set = new TreeSet<Person>(new PersonComparator());

        Person p1 =  new Person(10);
        Person p2 =  new Person(20);
        Person p3 =  new Person(30);
        Person p4 =  new Person(40);

        set.add(p1);
        set.add(p2);
        set.add(p3);
        set.add(p4);

        for(Iterator<Person> iterator = set.iterator();iterator.hasNext();){
            System.out.print(iterator.next().score+" ");
        }
    }
}

class Person{
    int score;

    public Person(int score){
        this.score = score;
    }

    public String toString(){
        return String.valueOf(this.score);
    }
}

class PersonComparator implements Comparator<Person>{

    @Override
    public int compare(Person o1, Person o2) {

        return o1.score - o2.score;
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

