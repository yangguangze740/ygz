package com.luju.ygz.test.service.impl;

import com.luju.pojo.JcPlanInfo;
import com.luju.ygz.test.repository.TestRepositoryI;
import com.luju.ygz.test.service.TestServiceI;
import luju.common.util.ConstantFields;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class TestServiceImpl implements TestServiceI {

    @Autowired
    private TestRepositoryI testRepository;

    @Override
    public void selectPlanDataFromOra() {
        List<JcPlanInfo> list = testRepository.selectJcPlanFromOra();
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
                        long time = date.getMillis()-ConstantFields.JC_TIME;
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
            list.get(k).setJcType(ConstantFields.TYPE_JC);

            testRepository.insertToPlanCopy(list.get(k));
        }
    }

    @Override
    public void selectPlanData4One() {
        String XD = "";
        String DH = "";
        List<JcPlanInfo> list = testRepository.selectJcPlan4One();

        for(int k=0;k<list.size();k++){
            Field[] fields = list.get(k).getClass().getDeclaredFields();
            Object oi = list.get(k);
            for (int j = 1; j < fields.length; j++) {
                if(!fields[j].isAccessible()){
                    fields[j].setAccessible(true);
                }
                try {
                    if(fields[j].getName().equals("TRACK_NUM")){
                        String des = fields[j].get(oi).toString();
                        XD = des.substring(0,2);
                        DH = des.substring(2,4);
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            list.get(k).setJcXD(XD);
            list.get(k).setJcDH(DH);
            testRepository.insertToPlan4One(list.get(k));
        }
    }

    @Override
    public void selectPlanData4HCJSL() {
        List<JcPlanInfo> list1 = testRepository.selectJcPlan4HC();
        for (int k = 0; k < list1.size(); k++) {
            testRepository.insertToPlan4HC(list1.get(k));
        }

        List<JcPlanInfo> list2 = testRepository.selectJcPlan4JF();
        for (int m = 0; m < list2.size(); m++) {
            testRepository.insertToPlan4JF(list2.get(m));
        }

        List<JcPlanInfo> list3 = testRepository.selectJcPlan4CX();
        for (int n = 0; n < list3.size(); n++) {
            testRepository.insertToPlan4CX(list3.get(n));
        }

        List<JcPlanInfo> list4 = testRepository.selectJcPlan4CC();
        for (int q = 0; q < list4.size(); q++) {
            testRepository.insertToPlan4CC(list4.get(q));
        }
    }

    @Override
    public List<JcPlanInfo> selectPlanAll() {
        return testRepository.selectJcPlanALL();
    }

    @Override
    public List<JcPlanInfo> selectPath() {
        return testRepository.selectJcPath();
    }

    @Override
    public boolean insertDataToMysql(JcPlanInfo info) {
        return testRepository.insertToPlanCopy(info);
    }

    @Override
    public List<JcPlanInfo> selectBwPlanAll() {
        return testRepository.selectBwPlan();
    }

    @Override
    public void selectBwjData() {
        List<JcPlanInfo> list = testRepository.selectBwjData();
        Timestamp ts = null;
        Timestamp ts1 = null;
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
                        long time = date.getMillis()+ConstantFields.BWJ1_TIME;
                        long time1 = date.getMillis()+ConstantFields.BWJ2_TIME;
//                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss:S");
//                        simpleDateFormat.format(timestamp);
                        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
                        ts = Timestamp.valueOf(simpleDateFormat1.format(time));
                        ts1 = Timestamp.valueOf(simpleDateFormat1.format(time1));
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            list.get(k).setJcStartTime(ts);
            list.get(k).setTIME(ts);
            list.get(k).setJcType(ConstantFields.TYPE_BWJ);

            testRepository.insertToBwjPlan4S(list.get(k));
            testRepository.insertToBwjPlan4N(list.get(k));
        }
    }
}
