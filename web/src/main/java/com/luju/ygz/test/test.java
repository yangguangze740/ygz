package com.luju.ygz.test;

public class Test {
    public static void main(String[] args) {
        String jsl = "路用车地铁特禁溜禁峰客梯车";

        int i = jsl.lastIndexOf("禁峰");
        int a = jsl.lastIndexOf("客体车");
        int b = jsl.lastIndexOf("工程车");
        int c = jsl.lastIndexOf("地铁");
        System.out.println(i);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
//        String s = "2.4";
//        Float f = Float.valueOf(s);
//        System.out.println(f);
//        String s1 = "XD04";
//        String xd = "";
//        String dh = "";
//        xd = s1.substring(0,2);
//        dh = s1.substring(2,4);
//        System.out.println(xd);
//        System.out.println(dh);
    }
}
