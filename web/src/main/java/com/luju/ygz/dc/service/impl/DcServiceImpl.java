package com.luju.ygz.dc.service.impl;

import com.luju.pojo.DcPlanInfo;
import com.luju.ygz.dc.repository.DcRepositoryI;
import com.luju.ygz.dc.service.DcServiceI;
import luju.common.util.ConstantFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class DcServiceImpl implements DcServiceI {

    @Autowired
    private DcRepositoryI dcRepository;

    @Override
    public void selectPlanDataFromOra() {
        List<DcPlanInfo> list = dcRepository.selectDcPlanFromOra();
        String st = null;
        for(int k=0;k<list.size();k++){
            Field[] fields = list.get(k).getClass().getDeclaredFields();
            Object oi = list.get(k);
            for (int j = 1; j < fields.length; j++) {
                if(!fields[j].isAccessible()){
                    fields[j].setAccessible(true);
                }
                try {
                    if(fields[j].getName().equals("JHKSSJ")){
                        fields[j].get(oi).toString();
                        DateFormat sdf = new SimpleDateFormat("HH:mm");
                        Date date = sdf.parse(fields[j].get(oi).toString());

                        System.out.println(date);
                        long time = date.getTime()+ConstantFields.DC_TIME;

                        date.setTime(time);
                        st = sdf.format(date);
                        list.get(k).setDcStartTime(st);
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            dcRepository.insertToPlanCopy(list.get(k));
        }
    }
}
