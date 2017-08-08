package luju.common.util;

import com.luju.pojo.DcPlanInfo;
import com.luju.pojo.JcPlanInfo;
import org.joda.time.DateTime;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class DataProcess {

    Timestamp ts1 = null;
    Timestamp ts2 = null;
    Timestamp ts3 = null;

    String XD = "";
    String DH = "";
    String TF = "";
    String TC = "";
    String end = "";
    String destination;

    int cs;


    public List<JcPlanInfo> jcTimeList(List<JcPlanInfo> list) {

        for (int k = 0; k < list.size(); k++) {
            Field[] fields = list.get(k).getClass().getDeclaredFields();
            Object oi = list.get(k);
            for (int j = 1; j < fields.length; j++) {
                if (!fields[j].isAccessible()) {
                    fields[j].setAccessible(true);
                }
                try {
                    if (fields[j].getName().equals("TIME")) {

                        Timestamp timestamp = (Timestamp)fields[j].get(oi);
                        DateTime date = new DateTime(timestamp.getTime());
                        long t = date.getMillis()-ConstantFields.JC_TIME;

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
                        ts1 = Timestamp.valueOf(simpleDateFormat.format(t));
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            list.get(k).setJcStartTime(ts1);
            list.get(k).setJcType(ConstantFields.TYPE_JC);
        }
        return list;
    }

    public List<JcPlanInfo> jcXDList(List<JcPlanInfo> list) {

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
        }
        return list;
    }

    public List<JcPlanInfo> bwjTimeList(List<JcPlanInfo> list) {

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
                        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
                        ts1 = Timestamp.valueOf(simpleDateFormat1.format(time));
                        ts2 = Timestamp.valueOf(simpleDateFormat1.format(time1));
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            list.get(k).setJcStartTime(ts1);
            list.get(k).setTIME(ts2);
            list.get(k).setJcType(ConstantFields.TYPE_BWJ);
        }
        return list;

    }

    public List<DcPlanInfo> dcTimeList(List<DcPlanInfo> list) {
        DataProcess tp = new DataProcess();
        for (int k = 0; k < list.size(); k++) {
            Field[] fields = list.get(k).getClass().getDeclaredFields();
            Object oi = list.get(k);
            for (int j = 1; j < fields.length; j++) {
                if (!fields[j].isAccessible()) {
                    fields[j].setAccessible(true);
                }
                try {
                    if (fields[j].getName().equals("dcStartTimeS")) {
                        String time = fields[j].get(oi).toString();
                        ts1 = tp.time(time);
                    }
                    if (fields[j].getName().equals("dcEndTimeS")) {
                        String time = fields[j].get(oi).toString();
                        ts2 = tp.time(time);
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            list.get(k).setDcStartTime(ts1);
            list.get(k).setDcEndTime(ts2);
        }
        return list;
    }

    public List<DcPlanInfo> jtTimeList(List<DcPlanInfo> list) {
        for (int k = 0; k < list.size(); k++) {
            Field[] fields = list.get(k).getClass().getDeclaredFields();
            Object oi = list.get(k);
            for (int j = 1; j < fields.length; j++) {
                if (!fields[j].isAccessible()) {
                    fields[j].setAccessible(true);
                }
                try {
                    if (fields[j].getName().equals("dcStartTime")) {

                        Timestamp timestamp = (Timestamp)fields[j].get(oi);
                        DateTime date = new DateTime(timestamp.getTime());
                        long t = date.getMillis()+ConstantFields.JT1_TIME;
                        long t1 = date.getMillis()+ConstantFields.JT2_TIME;
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
                        ts1 = Timestamp.valueOf(simpleDateFormat.format(t));
                        ts2 = Timestamp.valueOf(simpleDateFormat.format(t1));
                    }
                    if(fields[j].getName().equals("dcSource")){
                        String des = fields[j].get(oi).toString();
                        XD = des.substring(0,2);
                        DH = des.substring(2,4);
                        if(Integer.parseInt(DH) == 2 || Integer.parseInt(DH) == 3 || Integer.parseInt(DH) == 4){
                            end = ConstantFields.XT2;//2、3、4南
                        } else {
                            end = ConstantFields.XT1;//5、6、7北
                        }
                    }
                    if(fields[j].getName().equals("dcTFX")){
                        String des = fields[j].get(oi).toString();
                        TF = des.substring(0,2);
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            list.get(k).setDcStartTime(ts1);
            list.get(k).setDcMidTime(ts2);
            list.get(k).setDcTF(TF);
            list.get(k).setDcXD(XD);
            list.get(k).setDcDH(DH);
            list.get(k).setDcEnd(end);
        }
        return list;
    }

    public List<DcPlanInfo> zmTimeList(List<DcPlanInfo> list) {
        for (int k = 0; k < list.size(); k++) {
            Field[] fields = list.get(k).getClass().getDeclaredFields();
            Object oi = list.get(k);
            for (int j = 1; j < fields.length; j++) {
                if (!fields[j].isAccessible()) {
                    fields[j].setAccessible(true);
                }
                try {
                    if (fields[j].getName().equals("dcStartTime")) {
                        Timestamp timestamp = (Timestamp)fields[j].get(oi);
                        DateTime date = new DateTime(timestamp.getTime());
                        if (list.get(k).getDcDj() == 1 || list.get(k).getDcDj() == 3) {
                            long t = date.getMillis()+ConstantFields.ZM1_TIME;
                            long t1 = date.getMillis()+ConstantFields.ZM2_TIME;
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
                            ts1 = Timestamp.valueOf(simpleDateFormat.format(t));
                            ts2 = Timestamp.valueOf(simpleDateFormat.format(t1));
                            ts3 = timestamp;
                        } else {
                            long t = date.getMillis()+ConstantFields.ZM4_TIME;
                            long t1 = date.getMillis()+ConstantFields.ZM5_TIME;
                            long t2 = date.getMillis()+ConstantFields.ZM3_TIME;
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
                            ts1 = Timestamp.valueOf(simpleDateFormat.format(t));
                            ts2 = Timestamp.valueOf(simpleDateFormat.format(t1));
                            ts3 = Timestamp.valueOf(simpleDateFormat.format(t2));
                        }
                    }
                    if(fields[j].getName().equals("dcSource")){
                        String des = fields[j].get(oi).toString();
                        XD = des.substring(0,2);
                        DH = des.substring(2,4);
                        if(Integer.parseInt(DH) == 3 || Integer.parseInt(DH) == 2 || Integer.parseInt(DH) == 4){
                            end = ConstantFields.XT2;//2、3、4南
                        } else {
                            end = ConstantFields.XT1;//5、6、7北
                        }
                    }
                    if(fields[j].getName().equals("dcTFX")){
                        String des = fields[j].get(oi).toString();
                        TF = des.substring(0,2);
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            list.get(k).setDcMidTime(ts1);
            list.get(k).setDcMidTime1(ts2);
            list.get(k).setDcStartTime(ts3);
            list.get(k).setDcTF(TF);
            list.get(k).setDcXD(XD);
            list.get(k).setDcDH(DH);
            list.get(k).setDcEnd(end);
        }
        return list;
    }

    public List<DcPlanInfo> tcTimeList(List<DcPlanInfo> list) {
        for (int k = 0; k < list.size(); k++) {
            Field[] fields = list.get(k).getClass().getDeclaredFields();
            Object oi = list.get(k);
            for (int j = 1; j < fields.length; j++) {
                if (!fields[j].isAccessible()) {
                    fields[j].setAccessible(true);
                }
                try {
                    if (fields[j].getName().equals("dcNumber")) {
                        TC = ConstantFields.TYPE_TC;
                    }
                    if (fields[j].getName().equals("dcStartTime")) {

                        Timestamp timestamp = (Timestamp)fields[j].get(oi);
                        DateTime date = new DateTime(timestamp.getTime());
                        long t = date.getMillis()+ConstantFields.TC_TIME;
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
                        ts1 = Timestamp.valueOf(simpleDateFormat.format(t));
                    }
                    if(fields[j].getName().equals("dcDestination") && fields[j].get(oi) !=null){
                        String des = fields[j].get(oi).toString();
                        XD = des.substring(0,2);
                        DH = des.substring(2,4);
                    }
                    if(fields[j].getName().equals("dcTFX")){
                        if (fields[j].get(oi) ==null) {
                            TF = null;
                        } else {
                            String des = fields[j].get(oi).toString();
                            TF = des.substring(0,2);
                        }
                    }
                    if(fields[j].getName().equals("dcZGBZ")){
                        destination = fields[j].get(oi).toString();
                    }
                    if(fields[j].getName().equals("dcCS")){
                        if (destination.equals("+")) {
                            cs = Integer.parseInt(fields[j].get(oi).toString());
                        }
                        if (destination.equals("-")) {
                            cs = Integer.parseInt("-"+fields[j].get(oi).toString());
                        }
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            list.get(k).setDcNumber(TC);
            list.get(k).setDcStartTime(ts1);
            list.get(k).setDcXD(XD);
            list.get(k).setDcDH(DH);
            list.get(k).setDcTF(TF);
            list.get(k).setDcCS(cs);
        }
        return list;
    }

    public List<DcPlanInfo> zcTimeList(List<DcPlanInfo> list) {

        for (int k = 0; k < list.size(); k++) {
            Field[] fields = list.get(k).getClass().getDeclaredFields();
            Object oi = list.get(k);
            for (int j = 1; j < fields.length; j++) {
                if (!fields[j].isAccessible()) {
                    fields[j].setAccessible(true);
                }
                try {
                    if (fields[j].getName().equals("dcStartTime")) {
                        Timestamp timestamp = (Timestamp)fields[j].get(oi);
                        DateTime date = new DateTime(timestamp.getTime());
                        long t = date.getMillis()-ConstantFields.ZC_TIME;
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
                        ts1 = Timestamp.valueOf(simpleDateFormat.format(t));
                    }
                    if(fields[j].getName().equals("dcDestination") && fields[j].get(oi) !=null){
                        String des = fields[j].get(oi).toString();
                        XD = des.substring(0,2);
                        DH = des.substring(2,4);
                    }
                    if(fields[j].getName().equals("dcTFX")){
                        if (fields[j].get(oi) == null) {
                            TF = null;
                        } else {
                            String des = fields[j].get(oi).toString();
                            TF = des.substring(0,2);
                            System.out.println();
                        }
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            list.get(k).setDcStartTime(ts1);
            list.get(k).setDcXD(XD);
            list.get(k).setDcDH(DH);
            list.get(k).setDcTF(TF);
        }
        return list;
    }

    public Timestamp time(String time) {

        Timestamp ts = new Timestamp(Calendar.getInstance().getTime().getTime());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        StringBuffer buffer = new StringBuffer(sdf.format(ts));
        buffer.append(" " + time + ":00");
        time = buffer.toString();
        ts = Timestamp.valueOf(time);
        DateTime date = new DateTime(ts.getTime());
        long t = date.getMillis();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
        ts = Timestamp.valueOf(simpleDateFormat.format(t));

        return ts;
    }
}
