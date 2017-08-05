package com.luju.pojo;

import java.sql.Timestamp;

public class DcPlanInfo {

    private String jtId;
    private String dcNumber;
    private String dcStartTimeS;
    private String dcEndTimeS;
    private Timestamp dcStartTime;
    private Timestamp dcMidTime;
    private Timestamp dcEndTime;
    private String dcType;
    private String dcSource;
    private String dcEnd;
    private String dcDestination;
    private int dcDj;
    private String dcGJHID;
    private String dcZGBZ;
    private int dcCS;
    private String dcJSL;
    private String dcTFX;
    private String dcTF;
    private String dcXD;
    private String dcDH;
    private int dcSWH;

    public int getDcSWH() {
        return dcSWH;
    }

    public void setDcSWH(int dcSWH) {
        this.dcSWH = dcSWH;
    }

    public String getDcEnd() {
        return dcEnd;
    }

    public void setDcEnd(String dcEnd) {
        this.dcEnd = dcEnd;
    }

    public String getJtId() {
        return jtId;
    }

    public void setJtId(String jtId) {
        this.jtId = jtId;
    }

    public Timestamp getDcMidTime() {
        return dcMidTime;
    }

    public void setDcMidTime(Timestamp dcMidTime) {
        this.dcMidTime = dcMidTime;
    }

    public String getDcDestination() {
        return dcDestination;
    }

    public void setDcDestination(String dcDestination) {
        this.dcDestination = dcDestination;
    }

    public String getDcTF() {
        return dcTF;
    }

    public void setDcTF(String dcTF) {
        this.dcTF = dcTF;
    }

    public String getDcXD() {
        return dcXD;
    }

    public void setDcXD(String dcXD) {
        this.dcXD = dcXD;
    }

    public String getDcDH() {
        return dcDH;
    }

    public void setDcDH(String dcDH) {
        this.dcDH = dcDH;
    }

    public String getDcPath() {
        return dcPath;
    }

    public void setDcPath(String dcPath) {
        this.dcPath = dcPath;
    }

    private String dcPath;

    public String getDcTFX() {
        return dcTFX;
    }

    public void setDcTFX(String dcTFX) {
        this.dcTFX = dcTFX;
    }

    public String getDcNumber() {
        return dcNumber;
    }

    public void setDcNumber(String dcNumber) {
        this.dcNumber = dcNumber;
    }

    public String getDcStartTimeS() {
        return dcStartTimeS;
    }

    public void setDcStartTimeS(String dcStartTimeS) {
        this.dcStartTimeS = dcStartTimeS;
    }

    public String getDcEndTimeS() {
        return dcEndTimeS;
    }

    public void setDcEndTimeS(String dcEndTimeS) {
        this.dcEndTimeS = dcEndTimeS;
    }

    public Timestamp getDcStartTime() {
        return dcStartTime;
    }

    public void setDcStartTime(Timestamp dcStartTime) {
        this.dcStartTime = dcStartTime;
    }

    public Timestamp getDcEndTime() {
        return dcEndTime;
    }

    public void setDcEndTime(Timestamp dcEndTime) {
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
