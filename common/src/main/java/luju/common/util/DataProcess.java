package luju.common.util;

import com.luju.pojo.DcPlanInfo;
import com.luju.pojo.JcPlanInfo;
import com.luju.pojo.ResultInfo;
import org.joda.time.DateTime;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

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
    String dcd; // use for tc > 6

    int cs;
    int cs1;
    int cs6; // whether > 6
    boolean isB6;


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
            list.get(k).setNODE_FOUR_WAY(ConstantFields.JCSOURCE);
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

    public List<DcPlanInfo> tcDataList(List<DcPlanInfo> list) {
        for (int k = 0; k < list.size(); k++) {
            Field[] fields = list.get(k).getClass().getDeclaredFields();
            Object oi = list.get(k);
            for (int j = 1; j < fields.length; j++) {
                if (!fields[j].isAccessible()) {
                    fields[j].setAccessible(true);
                }
                try {
                    if(fields[j].getName().equals("dcDestination") && fields[j].get(oi) !=null){
                        if (k == 0) {
                            dcd = fields[j].get(oi).toString();
                        }
                        if (fields[j].get(oi).toString().equals(dcd)) {
                            dcd = fields[j].get(oi).toString();
                        } else {
                            dcd = fields[j].get(oi).toString();
                            isB6 = false;
                            cs1 = 0;
                        }
                    }
                    if(fields[j].getName().equals("dcCS")){

                        cs1 += Integer.parseInt(fields[j].get(oi).toString());
                        if (cs1 > 6 && isB6 == false) {
                            cs6 = 1;
                            isB6 = true;
                        }
                        if (cs1 < 6 && isB6 == false) {
                            cs6 = 0;
                        }

                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            list.get(k).setDcCS6(cs6);
        }
        return list;
    }

    public List<DcPlanInfo> zcTimeList(List<DcPlanInfo> list) {

        String des = null;

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
                        des = fields[j].get(oi).toString();
                        XD = des.substring(0,2);
                        DH = des.substring(2,4);
                    }
                    if(fields[j].getName().equals("dcTFX")){
                        if (fields[j].get(oi) == null) {
                            TF = null;
                        } else {
                            String de = fields[j].get(oi).toString();
                            TF = de.substring(0,2);
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
            list.get(k).setDcSource(ConstantFields.ZCSOURCE);
            list.get(k).setDcTypeE(ConstantFields.ZC);
            list.get(k).setDcPath(ConstantFields.ZC+des);
        }
        return list;
    }

    public List<ResultInfo> selectPath (List<ResultInfo> list, List<ResultInfo> pathList) {

        for (int i = 0; i<list.size();i++) {
            String number = list.get(i).getNumber();
            String source = list.get(i).getSource();
            String path = list.get(i).getPath();
            for (int k =0; k<pathList.size(); k++){
                pathList.get(k).setNumber(number);
                pathList.get(k).setSource(source);
                pathList.get(k).setPath(path);
            }
        }
        return pathList;
    }

    // 8.16 new code
    public List<DcPlanInfo> jtDataList1(List<DcPlanInfo> list) {
        String des = null;

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
                        des = fields[j].get(oi).toString();
                        XD = des.substring(0,2);
                        DH = des.substring(2,4);
                    }
                    if(fields[j].getName().equals("dcTFX")){
                        String de = fields[j].get(oi).toString();
                        TF = de.substring(0,2);
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            list.get(k).setDcStartTime(ts1);
            list.get(k).setDcEndTime(ts2);
            list.get(k).setDcTF(TF);
            list.get(k).setDcXD(XD);
            list.get(k).setDcDH(DH);
            list.get(k).setDcDestination(des);
            list.get(k).setDcSource(null);
            list.get(k).setDcTypeE(ConstantFields.JT);
        }
        return list;
    }

    public List<DcPlanInfo> jtDataList2(List<DcPlanInfo> list) {
        String des = null;

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
                        long t = date.getMillis()+ConstantFields.JT2_TIME;
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
                        ts1 = Timestamp.valueOf(simpleDateFormat.format(t));
                    }
                    if(fields[j].getName().equals("dcSource")){
                        des = fields[j].get(oi).toString();
                        XD = des.substring(0,2);
                        DH = des.substring(2,4);
                        if(Integer.parseInt(DH) == 2 || Integer.parseInt(DH) == 3 || Integer.parseInt(DH) == 4){
                            end = ConstantFields.XT2;//2、3、4南
                        } else {
                            end = ConstantFields.XT1;//5、6、7北
                        }
                    }
                    if(fields[j].getName().equals("dcTFX")){
                        String de = fields[j].get(oi).toString();
                        TF = de.substring(0,2);
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            list.get(k).setDcStartTime(ts1);
            list.get(k).setDcTF(TF);
            list.get(k).setDcXD(XD);
            list.get(k).setDcDH(DH);
            list.get(k).setDcDestination(end);
            list.get(k).setDcTypeE(ConstantFields.JT);
            list.get(k).setDcPath(ConstantFields.JT+des+end);
        }
        return list;
    }

    public List<DcPlanInfo> zmDataList1(List<DcPlanInfo> list) {
        String des = null;

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
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
                            ts2 = Timestamp.valueOf(simpleDateFormat.format(t));
                        } else {
                            long t = date.getMillis()+ConstantFields.ZM3_TIME;
                            long t1 = date.getMillis()+ConstantFields.ZM4_TIME;
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
                            ts1 = Timestamp.valueOf(simpleDateFormat.format(t));
                            ts2 = Timestamp.valueOf(simpleDateFormat.format(t1));
                        }
                    }
                    if(fields[j].getName().equals("dcSource")){
                        des = fields[j].get(oi).toString();
                        XD = des.substring(0,2);
                        DH = des.substring(2,4);
                    }
                    if(fields[j].getName().equals("dcTFX")){
                        String de = fields[j].get(oi).toString();
                        TF = de.substring(0,2);
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            list.get(k).setDcStartTime(ts1);
            list.get(k).setDcEndTime(ts2);
            list.get(k).setDcTF(TF);
            list.get(k).setDcXD(XD);
            list.get(k).setDcDH(DH);
            list.get(k).setDcDestination(des);
            list.get(k).setDcSource(null);
            list.get(k).setDcTypeE(ConstantFields.ZM);
        }
        return list;
    }

    public List<DcPlanInfo> zmDataList2(List<DcPlanInfo> list) {
        String des = null;

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
                            long t = date.getMillis()+ConstantFields.ZM2_TIME;
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
                            ts1 = Timestamp.valueOf(simpleDateFormat.format(t));
                        } else {
                            long t = date.getMillis()+ConstantFields.ZM5_TIME;
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
                            ts1 = Timestamp.valueOf(simpleDateFormat.format(t));
                        }
                    }
                    if(fields[j].getName().equals("dcSource")){
                        des = fields[j].get(oi).toString();
                        XD = des.substring(0,2);
                        DH = des.substring(2,4);
                        if(Integer.parseInt(DH) == 3 || Integer.parseInt(DH) == 2 || Integer.parseInt(DH) == 4){
                            end = ConstantFields.XT2;//2、3、4南
                        } else {
                            end = ConstantFields.XT1;//5、6、7北
                        }
                    }
                    if(fields[j].getName().equals("dcTFX")){
                        String de = fields[j].get(oi).toString();
                        TF = de.substring(0,2);
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            list.get(k).setDcStartTime(ts1);
            list.get(k).setDcTF(TF);
            list.get(k).setDcXD(XD);
            list.get(k).setDcDH(DH);
            list.get(k).setDcDestination(end);
            list.get(k).setDcTypeE(ConstantFields.ZM);
            list.get(k).setDcPath(ConstantFields.ZM+des+end);
        }
        return list;
    }

    public List<DcPlanInfo> bwjTimeListNew(List<DcPlanInfo> list) {

        for(int k=0;k<list.size();k++){
            Field[] fields = list.get(k).getClass().getDeclaredFields();
            Object oi = list.get(k);
            for (int j = 1; j < fields.length; j++) {
                if(!fields[j].isAccessible()){
                    fields[j].setAccessible(true);
                }
                try {
                    if(fields[j].getName().equals("dcEndTime")){
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
            list.get(k).setDcStartTime(ts1);
            list.get(k).setDcEndTime(ts2);
            list.get(k).setDcType(ConstantFields.TYPE_BWJ);
            list.get(k).setDcTypeE(ConstantFields.BWJ);
        }
        return list;

    }

    public List<DcPlanInfo> jcDataList(List<DcPlanInfo> list) {
        String des = null;

        for(int k=0;k<list.size();k++){
            Field[] fields = list.get(k).getClass().getDeclaredFields();
            Object oi = list.get(k);
            for (int j = 1; j < fields.length; j++) {
                if(!fields[j].isAccessible()){
                    fields[j].setAccessible(true);
                }
                try {
                    if(fields[j].getName().equals("dcDestination")){
                        des = fields[j].get(oi).toString();
                        XD = des.substring(0,2);
                        DH = des.substring(2,4);
                    }

                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            list.get(k).setDcXD(XD);
            list.get(k).setDcDH(DH);
            list.get(k).setDcTypeE(ConstantFields.JC);
            list.get(k).setDcPath(ConstantFields.JC+ConstantFields.JCSOURCE+des);
        }
        return list;
    }

    public List<String> jtSelectList() {
        List<String> jtSelectList = new ArrayList<String>();

        jtSelectList.add(ConstantFields.TYPE_QCX);
        jtSelectList.add(ConstantFields.TYPE_JDX);

        return jtSelectList;
    }

    public List<String> zmSelectList() {
        List<String> zmSelectList = new ArrayList<String>();

        zmSelectList.add(ConstantFields.XT1);
        zmSelectList.add(ConstantFields.XT2);

        return zmSelectList;
    }

    public List<String> tcSelectList() {
        List<String> tcSelectList = new ArrayList<String>();

        tcSelectList.add(ConstantFields.XT1);
        tcSelectList.add(ConstantFields.XT2);

        return tcSelectList;
    }

    public List<String> bwjSelectList4S() {
        List<String> bwjSelectList = new ArrayList<String>();

        bwjSelectList.add(ConstantFields.BWJDS);
        bwjSelectList.add(ConstantFields.BWJDN);

        return bwjSelectList;
    }

    public List<String> bwjSelectList4N() {
        List<String> bwjSelectList = new ArrayList<String>();

        bwjSelectList.add(ConstantFields.BWJDN);
        bwjSelectList.add(ConstantFields.BWJDS);

        return bwjSelectList;
    }

    public List<String> jtSelectListUpdate(String s) {
        List<String> jtSelectList = new ArrayList<String>();

        if (s.equals(ConstantFields.TYPE_QCX)) {
            jtSelectList.add(ConstantFields.TYPE_QCX);
            jtSelectList.add(ConstantFields.TYPE_JDX);
        } else {
            jtSelectList.add(ConstantFields.TYPE_JDX);
            jtSelectList.add(ConstantFields.TYPE_QCX);
        }
        return jtSelectList;
    }

    public List<String> zmtcSelectListUpdate(String s) {
        List<String> zmSelectList = new ArrayList<String>();

        if (s.equals(ConstantFields.XT1)){
            zmSelectList.add(ConstantFields.XT1);
            zmSelectList.add(ConstantFields.XT2);
        } else {
            zmSelectList.add(ConstantFields.XT2);
            zmSelectList.add(ConstantFields.XT1);
        }
        return zmSelectList;
    }

    public List<String> bwjSelectListUpdate(String s) {
        List<String> bwjSelectList = new ArrayList<String>();

        if (s.equals(ConstantFields.BWJDS)){
            bwjSelectList.add(ConstantFields.BWJDS);
            bwjSelectList.add(ConstantFields.BWJDN);
        } else {
            bwjSelectList.add(ConstantFields.BWJDN);
            bwjSelectList.add(ConstantFields.BWJDS);
        }
        return bwjSelectList;
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
