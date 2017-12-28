package com.luju.pojo;

import java.sql.Timestamp;

public class FcPlanInfo {

    private String fcSource;//source
    private String fcNumber;//number
    private String fcDestination;//destination
    private Timestamp fcEndTime;//time
    private Timestamp fcStartTime;
    private String fcQBID;
    private float fcHc;
    private String fcJSL;
    private String fcImportant;
    private String fcType;
    private String fcQBIDN;
    private String fcXD;
    private String fcDH;
    private int color;

    public String getFcSource() {
        return fcSource;
    }

    public void setFcSource(String fcSource) {
        this.fcSource = fcSource;
    }

    public String getFcNumber() {
        return fcNumber;
    }

    public void setFcNumber(String fcNumber) {
        this.fcNumber = fcNumber;
    }

    public String getFcDestination() {
        return fcDestination;
    }

    public void setFcDestination(String fcDestination) {
        this.fcDestination = fcDestination;
    }

    public Timestamp getFcEndTime() {
        return fcEndTime;
    }

    public void setFcEndTime(Timestamp fcEndTime) {
        this.fcEndTime = fcEndTime;
    }

    public Timestamp getFcStartTime() {
        return fcStartTime;
    }

    public void setFcStartTime(Timestamp fcStartTime) {
        this.fcStartTime = fcStartTime;
    }

    public String getFcQBID() {
        return fcQBID;
    }

    public void setFcQBID(String fcQBID) {
        this.fcQBID = fcQBID;
    }

    public float getFcHc() {
        return fcHc;
    }

    public void setFcHc(float fcHc) {
        this.fcHc = fcHc;
    }

    public String getFcJSL() {
        return fcJSL;
    }

    public void setFcJSL(String fcJSL) {
        this.fcJSL = fcJSL;
    }

    public String getFcImportant() {
        return fcImportant;
    }

    public void setFcImportant(String fcImportant) {
        this.fcImportant = fcImportant;
    }

    public String getFcType() {
        return fcType;
    }

    public void setFcType(String fcType) {
        this.fcType = fcType;
    }

    public String getFcQBIDN() {
        return fcQBIDN;
    }

    public void setFcQBIDN(String fcQBIDN) {
        this.fcQBIDN = fcQBIDN;
    }

    public String getFcXD() {
        return fcXD;
    }

    public void setFcXD(String fcXD) {
        this.fcXD = fcXD;
    }

    public String getFcDH() {
        return fcDH;
    }

    public void setFcDH(String fcDH) {
        this.fcDH = fcDH;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
