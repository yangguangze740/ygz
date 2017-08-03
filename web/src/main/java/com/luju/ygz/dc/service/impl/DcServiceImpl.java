package com.luju.ygz.dc.service.impl;

import com.luju.pojo.DcPlanInfo;
import com.luju.ygz.dc.repository.DcRepositoryI;
import com.luju.ygz.dc.service.DcServiceI;
import luju.common.util.ConstantFields;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
public class DcServiceImpl implements DcServiceI {

    @Autowired
    private DcRepositoryI dcRepository;

    @Override
    public void selectPlanDataFromOra() {
        List<DcPlanInfo> list = dcRepository.selectDcPlanFromOra();
        Timestamp st1 = null;
        Timestamp st2 = null;

        for(int k=0;k<list.size();k++){
            Field[] fields = list.get(k).getClass().getDeclaredFields();
            Object oi = list.get(k);
            for (int j = 1; j < fields.length; j++) {
                if(!fields[j].isAccessible()){
                    fields[j].setAccessible(true);
                }
                try {
                    if(fields[j].getName().equals("dcStartTimeS")){

                        String time = fields[j].get(oi).toString();
                        Timestamp ts = new Timestamp(Calendar.getInstance().getTime().getTime());

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        StringBuffer buffer = new StringBuffer(sdf.format(ts));
                        buffer.append(" "+time+":00");
                        time = buffer.toString();
                        st1 = Timestamp.valueOf(time);
                        DateTime date = new DateTime(st1.getTime());
                        long t = date.getMillis()+ ConstantFields.DC_TIME;

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
                        st1 = Timestamp.valueOf(simpleDateFormat.format(t));
                    }
                    if(fields[j].getName().equals("dcEndTimeS")){

                        String time = fields[j].get(oi).toString();
                        Timestamp ts = new Timestamp(Calendar.getInstance().getTime().getTime());

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        StringBuffer buffer = new StringBuffer(sdf.format(ts));
                        buffer.append(" "+time+":00");
                        time = buffer.toString();

                        st2 = Timestamp.valueOf(time);
                        DateTime date = new DateTime(st2.getTime());
                        long t = date.getMillis();

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
                        st2 = Timestamp.valueOf(simpleDateFormat.format(t));
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            list.get(k).setDcStartTime(st1);
            list.get(k).setDcEndTime(st2);

            dcRepository.insertToPlanCopy(list.get(k));
        }
    }
}
