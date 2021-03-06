package luju.common.util;

import com.luju.pojo.DcPlanInfo;
import com.luju.pojo.FcPlanInfo;
import com.luju.pojo.JcPlanInfo;
import org.joda.time.DateTime;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class DataProcess {

    Timestamp ts1 = null;
    Timestamp ts2 = null;

    String XD = "";
    String DH = "";
    String TF = "";
    String TC = "";
    String end = "";
    String destination;
    String dcd; // use for tc > 6
    String source;

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
        }
        return list;
    }

    public List<DcPlanInfo> xzTimeList(List<DcPlanInfo> list) {

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
                        long t = date.getMillis()-ConstantFields.XZ_TIME;

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
                        ts1 = Timestamp.valueOf(simpleDateFormat.format(t));
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            list.get(k).setDcStartTime(ts1);
        }
        return list;
    }
    public List<DcPlanInfo> szTimeList(List<DcPlanInfo> list) {

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
                        long t = date.getMillis()-ConstantFields.XZ_TIME;

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
                        ts1 = Timestamp.valueOf(simpleDateFormat.format(t));
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            list.get(k).setDcStartTime(ts1);
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

    public List<DcPlanInfo> tcTimeList(List<DcPlanInfo> list) {
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
            list.get(k).setDcPath(ConstantFields.XXBFC+des);
        }
        return list;
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

    public List<DcPlanInfo> jtQcDataList1(List<DcPlanInfo> list) {
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

    public List<DcPlanInfo> jtQcDataList2(List<DcPlanInfo> list) {
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
                        if(Integer.parseInt(DH) == 2 || Integer.parseInt(DH) == 3 || Integer.parseInt(DH) == 4 || Integer.parseInt(DH) == 5){
                            end = ConstantFields.T402;//2、3、4 5
                        } else {
                            end = ConstantFields.T404;//6 7 8 9 10
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
                            long yt = date.getMillis();
                            long t = date.getMillis()+ConstantFields.ZM1_TIME;
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
                            ts1 = Timestamp.valueOf(simpleDateFormat.format(yt));
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

    public List<DcPlanInfo> zmT2DataList1(List<DcPlanInfo> list) {
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
                        if (list.get(k).getDcDj() == 2 || list.get(k).getDcDj() == 4) {
                            long yt = date.getMillis();
                            long t = date.getMillis()+ConstantFields.ZM1_TIME;
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
                            ts1 = Timestamp.valueOf(simpleDateFormat.format(yt));
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

    public List<DcPlanInfo> zmT2DataList2(List<DcPlanInfo> list) {
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
                        if (list.get(k).getDcDj() == 2 || list.get(k).getDcDj() == 4) {
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
                        if(Integer.parseInt(DH) == 2 || Integer.parseInt(DH) == 3 || Integer.parseInt(DH) == 4 || Integer.parseInt(DH) == 5){
                            end = ConstantFields.T402;
                        } else {
                            end = ConstantFields.T404;
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

    public List<DcPlanInfo> bwjDataList(List<DcPlanInfo> list) {

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
                    if(fields[j].getName().equals("dcDestination") ){
                        des = fields[j].get(oi).toString();
                        if (des.length() ==4) {
                            XD = des.substring(0,2);
                            DH = des.substring(2,4);
                        }
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

    public List<DcPlanInfo> jcZwqList(List<DcPlanInfo> list) {
        String des = null;

        for(int k=0;k<list.size();k++){
            Field[] fields = list.get(k).getClass().getDeclaredFields();
            Object oi = list.get(k);
            for (int j = 1; j < fields.length; j++) {
                if(!fields[j].isAccessible()){
                    fields[j].setAccessible(true);
                }
                try {
                    if(fields[j].getName().equals("dcDestination") ){
                        des = fields[j].get(oi).toString();
                        if (des.length() == 4) {
                            XD = des.substring(0,2);
                            DH = des.substring(2,4);
                        }
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
            list.get(k).setDcPath(ConstantFields.JC+ConstantFields.ZWQSOURCE1+des);
        }
        return list;
    }

    public List<DcPlanInfo> jcDcList(List<DcPlanInfo> list) {
        String des = null;

        for(int k=0;k<list.size();k++){
            Field[] fields = list.get(k).getClass().getDeclaredFields();
            Object oi = list.get(k);
            for (int j = 1; j < fields.length; j++) {
                if(!fields[j].isAccessible()){
                    fields[j].setAccessible(true);
                }
                try {
                    if(fields[j].getName().equals("jcDestination")){
                        des = fields[j].get(oi).toString();
                        if (des.length() ==4) {
                            XD = des.substring(0,2);
                            DH = des.substring(2,4);
                        }
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
            list.get(k).setDcPath(ConstantFields.JC+ConstantFields.DCSOURCE1+des);
        }
        return list;
    }

    public List<DcPlanInfo> yhDataList(List<DcPlanInfo> list) {
        String des = null;

        for(int k=0;k<list.size();k++){
            Field[] fields = list.get(k).getClass().getDeclaredFields();
            Object oi = list.get(k);
            for (int j = 1; j < fields.length; j++) {
                if(!fields[j].isAccessible()){
                    fields[j].setAccessible(true);
                }
                try {
                    if(fields[j].getName().equals("dcDestination") ){
                        des = fields[j].get(oi).toString();
                        if (des.length() ==4) {
                            XD = des.substring(0,2);
                            DH = des.substring(2,4);
                        }
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
            list.get(k).setDcPath(ConstantFields.JC+ConstantFields.YHSOURCE1+des);
        }
        return list;
    }

    public List<String> jtSelectList() {
        List<String> jtSelectList = new ArrayList<String>();

        jtSelectList.add(ConstantFields.TYPE_QCX);
        jtSelectList.add(ConstantFields.TYPE_JDX);
        jtSelectList.add(ConstantFields.TYPE_ZX);
        jtSelectList.add(ConstantFields.TYPE_DX);
        jtSelectList.add(ConstantFields.TYPE_YX);

        return jtSelectList;
    }

    public List<String> zmSelectList() {
        List<String> zmSelectList = new ArrayList<String>();

        zmSelectList.add(ConstantFields.T404);
        zmSelectList.add(ConstantFields.T402);

        return zmSelectList;
    }

    public List<String> tcSelectList() {
        List<String> tcSelectList = new ArrayList<String>();

        tcSelectList.add(ConstantFields.T404);
        tcSelectList.add(ConstantFields.T402);

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
            jtSelectList.add(ConstantFields.TYPE_ZX);
            jtSelectList.add(ConstantFields.TYPE_DX);
            jtSelectList.add(ConstantFields.TYPE_YX);
        }else if(s.equals(ConstantFields.TYPE_JDX)) {
            jtSelectList.add(ConstantFields.TYPE_JDX);
            jtSelectList.add(ConstantFields.TYPE_QCX);
            jtSelectList.add(ConstantFields.TYPE_ZX);
            jtSelectList.add(ConstantFields.TYPE_DX);
            jtSelectList.add(ConstantFields.TYPE_YX);
        }else if(s.equals(ConstantFields.TYPE_ZX)) {
            jtSelectList.add(ConstantFields.TYPE_ZX);
            jtSelectList.add(ConstantFields.TYPE_QCX);
            jtSelectList.add(ConstantFields.TYPE_JDX);
            jtSelectList.add(ConstantFields.TYPE_DX);
            jtSelectList.add(ConstantFields.TYPE_YX);
        }else if(s.equals(ConstantFields.TYPE_DX)) {
            jtSelectList.add(ConstantFields.TYPE_DX);
            jtSelectList.add(ConstantFields.TYPE_QCX);
            jtSelectList.add(ConstantFields.TYPE_JDX);
            jtSelectList.add(ConstantFields.TYPE_ZX);
            jtSelectList.add(ConstantFields.TYPE_YX);
        }else {
            jtSelectList.add(ConstantFields.TYPE_YX);
            jtSelectList.add(ConstantFields.TYPE_QCX);
            jtSelectList.add(ConstantFields.TYPE_JDX);
            jtSelectList.add(ConstantFields.TYPE_ZX);
            jtSelectList.add(ConstantFields.TYPE_DX);
        }
        return jtSelectList;
    }

    public List<String> zmtcSelectListUpdate(String s) {
        List<String> zmSelectList = new ArrayList<String>();

        if (s.equals(ConstantFields.T402)){
            zmSelectList.add(ConstantFields.T402);
            zmSelectList.add(ConstantFields.T404);
        } else {
            zmSelectList.add(ConstantFields.T404);
            zmSelectList.add(ConstantFields.T402);
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

    // fc

    public List<FcPlanInfo> fcTimeList(List<FcPlanInfo> list) {
        Timestamp end = null;
        Timestamp start = null;

        for (int k = 0; k < list.size(); k++) {
            Field[] fields = list.get(k).getClass().getDeclaredFields();
            Object oi = list.get(k);
            for (int j = 1; j < fields.length; j++) {
                if (!fields[j].isAccessible()) {
                    fields[j].setAccessible(true);
                }
                try {
                    if (fields[j].getName().equals("fcEndTime")) {
                        Timestamp timestamp = (Timestamp)fields[j].get(oi);
                        DateTime date = new DateTime(timestamp.getTime());
                        long t = date.getMillis()-ConstantFields.FC_TIME;
                        long t1 = date.getMillis()+ConstantFields.FC_TIME;
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
                        start = Timestamp.valueOf(simpleDateFormat.format(t));
                        end = Timestamp.valueOf(simpleDateFormat.format(t1));
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            list.get(k).setFcStartTime(start);
            list.get(k).setFcEndTime(end);
            list.get(k).setFcType(ConstantFields.TYPE_FC);
        }
        return list;
    }

    public DcPlanInfo calSub(DcPlanInfo dcPlanInfo, int i, int size) {
        Timestamp startTime = dcPlanInfo.getDcStartTime();
        Timestamp endTime = dcPlanInfo.getDcEndTime();
        DateTime start = new DateTime(startTime.getTime());
        DateTime end = new DateTime(endTime.getTime());
        long startL = start.getMillis();
        long endL = end.getMillis();
        long sub = (endL - startL)/size;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
        dcPlanInfo.setDcStartTime(Timestamp.valueOf(simpleDateFormat.format(startL+(i)*sub)));
        dcPlanInfo.setDcEndTime(Timestamp.valueOf(simpleDateFormat.format(startL+(i+1)*sub)));

        if (dcPlanInfo.getDcType().equals("编组")) {
            dcPlanInfo.setDcTypeE(ConstantFields.BZ);
            dcPlanInfo.setDcPath(ConstantFields.BZ+dcPlanInfo.getDcSource()+ConstantFields.S);
        }
        if (dcPlanInfo.getDcType().equals("挑车")) {
            dcPlanInfo.setDcTypeE(ConstantFields.TYPE_TC);
        }
        if (dcPlanInfo.getDcSource().length() == 4) {
            dcPlanInfo.setDcXD((dcPlanInfo.getDcSource().substring(0,2)));
            dcPlanInfo.setDcDH((dcPlanInfo.getDcSource().substring(2,4)));
        }
        dcPlanInfo.setDcDestination(dcPlanInfo.getDcSource());
        dcPlanInfo.setDcSource(ConstantFields.S);

        return dcPlanInfo;
    }

    public List<DcPlanInfo> fczmDataList(List<DcPlanInfo> list) {
        String des = null;
        DcPlanInfo dcPlanInfo = new DcPlanInfo();

        for(int k=0;k<list.size();k++){
            Field[] fields = list.get(k).getClass().getDeclaredFields();
            Object oi = list.get(k);
            for (int j = 1; j < fields.length; j++) {
                if(!fields[j].isAccessible()){
                    fields[j].setAccessible(true);
                }
                try {
                    if(fields[j].getName().equals("dcDestination") ){
                        des = fields[j].get(oi).toString();
                        if (des.length() ==4) {
                            XD = des.substring(0,2);
                            DH = des.substring(2,4);
                        }
                    }

                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            list.get(k).setDcXD(XD);
            list.get(k).setDcDH(DH);
            list.get(k).setDcTypeE(ConstantFields.FC);
            list.get(k).setDcPath(ConstantFields.ZM+dcPlanInfo.getDcSource()+des);
        }
        return list;
    }

    public List<DcPlanInfo> fczcDataList(List<DcPlanInfo> list) {
        String des = null;
        DcPlanInfo dcPlanInfo = new DcPlanInfo();

        for(int k=0;k<list.size();k++){
            Field[] fields = list.get(k).getClass().getDeclaredFields();
            Object oi = list.get(k);
            for (int j = 1; j < fields.length; j++) {
                if(!fields[j].isAccessible()){
                    fields[j].setAccessible(true);
                }
                try {
                    if(fields[j].getName().equals("dcSource")){
                        des = fields[j].get(oi).toString();
                        if (des.length() ==4) {
                            XD = des.substring(0,2);
                            DH = des.substring(2,4);
                        }
                    }

                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            list.get(k).setDcXD(XD);
            list.get(k).setDcDH(DH);
            list.get(k).setDcTypeE(ConstantFields.FC);
            list.get(k).setDcPath(ConstantFields.ZC+dcPlanInfo.getDcSource()+ConstantFields.S);
        }
        return list;
    }

    public List<DcPlanInfo> fcMsjDataList(List<DcPlanInfo> list) {
        String des = null;

        for(int k=0;k<list.size();k++){
            Field[] fields = list.get(k).getClass().getDeclaredFields();
            Object oi = list.get(k);
            for (int j = 1; j < fields.length; j++) {
                if(!fields[j].isAccessible()){
                    fields[j].setAccessible(true);
                }
                try {
                    if(fields[j].getName().equals("dcDestination") ){
                        des = fields[j].get(oi).toString();
                        if (des.length() ==4) {
                            XD = des.substring(0,2);
                            DH = des.substring(2,4);
                        }
                    }

                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            list.get(k).setDcXD(XD);
            list.get(k).setDcDH(DH);
            list.get(k).setDcTypeE(ConstantFields.FC);
            list.get(k).setDcPath(ConstantFields.FC+ConstantFields.JCSOURCE+des);
        }
        return list;
    }

    public List<DcPlanInfo> fcDcDataList(List<DcPlanInfo> list) {
        String des = null;

        for(int k=0;k<list.size();k++){
            Field[] fields = list.get(k).getClass().getDeclaredFields();
            Object oi = list.get(k);
            for (int j = 1; j < fields.length; j++) {
                if(!fields[j].isAccessible()){
                    fields[j].setAccessible(true);
                }
                try {
                    if(fields[j].getName().equals("dcDestination") ){
                        des = fields[j].get(oi).toString();
                        if (des.length() ==4) {
                            XD = des.substring(0,2);
                            DH = des.substring(2,4);
                        }
                    }

                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            list.get(k).setDcXD(XD);
            list.get(k).setDcDH(DH);
            list.get(k).setDcTypeE(ConstantFields.FC);
            list.get(k).setDcPath(ConstantFields.FC+ConstantFields.DC+des);
        }
        return list;
    }

    public List<DcPlanInfo> fcYhDataList(List<DcPlanInfo> list) {
        String des = null;

        for(int k=0;k<list.size();k++){
            Field[] fields = list.get(k).getClass().getDeclaredFields();
            Object oi = list.get(k);
            for (int j = 1; j < fields.length; j++) {
                if(!fields[j].isAccessible()){
                    fields[j].setAccessible(true);
                }
                try {
                    if(fields[j].getName().equals("dcDestination") ){
                        des = fields[j].get(oi).toString();
                        if (des.length() ==4) {
                            XD = des.substring(0,2);
                            DH = des.substring(2,4);
                        }
                    }

                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            list.get(k).setDcXD(XD);
            list.get(k).setDcDH(DH);
            list.get(k).setDcTypeE(ConstantFields.FC);
            list.get(k).setDcPath(ConstantFields.FC+ConstantFields.YH+des);
        }
        return list;
    }

    public List<DcPlanInfo> fcZwqDataList(List<DcPlanInfo> list) {
        String des = null;

        for(int k=0;k<list.size();k++){
            Field[] fields = list.get(k).getClass().getDeclaredFields();
            Object oi = list.get(k);
            for (int j = 1; j < fields.length; j++) {
                if(!fields[j].isAccessible()){
                    fields[j].setAccessible(true);
                }
                try {
                    if(fields[j].getName().equals("dcDestination") ){
                        des = fields[j].get(oi).toString();
                        if (des.length() ==4) {
                            XD = des.substring(0,2);
                            DH = des.substring(2,4);
                        }
                    }

                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            list.get(k).setDcXD(XD);
            list.get(k).setDcDH(DH);
            list.get(k).setDcTypeE(ConstantFields.FC);
            list.get(k).setDcPath(ConstantFields.FC+ConstantFields.ZWQ+des);
        }
        return list;
    }

//    public List<DcPlanInfo> calValue(List<DcPlanInfo> list,int i, int size) {
//        long tStart = 0;
//        long tEnd = 0;
//        String des = null;
//        String typeE = null;
//
//        for (int k = 0; k < list.size(); k++) {
//            Field[] fields = list.get(k).getClass().getDeclaredFields();
//            Object oi = list.get(k);
//            for (int j = 1; j < fields.length; j++) {
//                if (!fields[j].isAccessible()) {
//                    fields[j].setAccessible(true);
//                }
//                try {
//                    if (fields[j].getName().equals("dcStartTime")) {
//                        Timestamp timestamp = (Timestamp)fields[j].get(oi);
//                        DateTime date = new DateTime(timestamp.getTime());
//                        tStart = date.getMillis();
//                    }
//                    if (fields[j].getName().equals("dcEndTime")) {
//                        Timestamp timestamp = (Timestamp)fields[j].get(oi);
//                        DateTime date = new DateTime(timestamp.getTime());
//                        tEnd = date.getMillis();
//                    }
//                    if (fields[j].getName().equals("dcType")) {
//                        String type = fields[j].get(oi).toString();
//                        if (type.equals("编组")) {
//                            typeE = ConstantFields.BZ;
//                        }
//                        if (type.equals("挑车")) {
//                            typeE = ConstantFields.TYPE_TC;
//                        }
//                    }
//                    if(fields[j].getName().equals("dcSource") ){
//                        des = fields[j].get(oi).toString();
//                        if (des.length() == 4) {
//                            XD = des.substring(0,2);
//                            DH = des.substring(2,4);
//                        }
//                    }
//
//                } catch (IllegalArgumentException e) {
//                    e.printStackTrace();
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//            }
//            long value = (tEnd - tStart)/size;
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
//            list.get(k).setDcStartTime(Timestamp.valueOf(simpleDateFormat.format(tStart+(i)*value)));
//            list.get(k).setDcEndTime(Timestamp.valueOf(simpleDateFormat.format(tStart+(i+1)*value)));
//            list.get(k).setDcXD(XD);
//            list.get(k).setDcDH(DH);
//            list.get(k).setDcTypeE(typeE);
//            list.get(k).setDcDestination(ConstantFields.S);
//        }
//        return list;
//    }
}
