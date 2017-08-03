package com.luju.pojo;

import java.sql.Time;
import java.sql.Timestamp;

public class DcPlanInfo {

    private String dcNumber;
    private String dcStartTime;
    private String dcEndTime;
    private String dcType;
    private String dcSource;
    private int dcDj;
    private String dcGJHID;
    private String dcZGBZ;
    private int dcCS;
    private String dcJSL;

    public String getDcNumber() {
        return dcNumber;
    }

    public void setDcNumber(String dcNumber) {
        this.dcNumber = dcNumber;
    }

    public String getDcStartTime() {
        return dcStartTime;
    }

    public void setDcStartTime(String dcStartTime) {
        this.dcStartTime = dcStartTime;
    }

    public String getDcEndTime() {
        return dcEndTime;
    }

    public void setDcEndTime(String dcEndTime) {
        this.dcEndTime = dcEndTime;
    }

    public String getDcType() {
        return dcType;
    }

    public void setDcType(String dcType) {
        this.dcType = dcType;
    }

    public String getDcSource() {
        return dcSource;
    }

    public void setDcSource(String dcSource) {
        this.dcSource = dcSource;
    }

    public int getDcDj() {
        return dcDj;
    }

    public void setDcDj(int dcDj) {
        this.dcDj = dcDj;
    }

    public String getDcGJHID() {
        return dcGJHID;
    }

    public void setDcGJHID(String dcGJHID) {
        this.dcGJHID = dcGJHID;
    }

    public String getDcZGBZ() {
        return dcZGBZ;
    }

    public void setDcZGBZ(String dcZGBZ) {
        this.dcZGBZ = dcZGBZ;
    }

    public int getDcCS() {
        return dcCS;
    }

    public void setDcCS(int dcCS) {
        this.dcCS = dcCS;
    }

    public String getDcJSL() {
        return dcJSL;
    }

    public void setDcJSL(String dcJSL) {
        this.dcJSL = dcJSL;
    }
}
