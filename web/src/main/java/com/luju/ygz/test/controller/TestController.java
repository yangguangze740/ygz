package com.luju.ygz.test.controller;

import com.luju.pojo.JcPlanInfo;
import com.luju.ygz.test.service.impl.TestServiceImpl;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestServiceImpl service;

    public static long JC_TIME = 480000;
    public static final String type = "接车";

    @RequestMapping("/hello")
    public String test(JcPlanInfo info) {

        List<JcPlanInfo> list = service.selectPlanDataFromOra();
        Timestamp ts = null;
        for(int k=0;k<list.size();k++){
            Field[] fields = list.get(k).getClass().getDeclaredFields();
            Object oi = list.get(k);
            for (int j = 1; j < fields.length; j++) {
                if(!fields[j].isAccessible()){
                    fields[j].setAccessible(true);
                }
                try {
                    if(fields[j].getName().equals("TIME")){
                        fields[j].get(oi).toString();
                        Timestamp timestamp = (Timestamp)fields[j].get(oi);
                        DateTime date = new DateTime(timestamp.getTime());
                        long time = date.getMillis()-JC_TIME;
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss:S");
                        simpleDateFormat.format(timestamp);
                        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
                        ts = Timestamp.valueOf(simpleDateFormat1.format(time));
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            list.get(k).setJcStartTime(ts);
            list.get(k).setJcType(type);

            service.insertDataToMysql(list.get(k));
        }
        return "hello";
    }

    @RequestMapping("/world")
    public String processData(JcPlanInfo info) {
        service.selectPlanDataFromMysql();
        return "world";
    }
}
