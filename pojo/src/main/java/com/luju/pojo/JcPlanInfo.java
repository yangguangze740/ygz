package com.luju.pojo;

import java.sql.Timestamp;

public class JcPlanInfo {

    private String copyId;
    private String NODE_FOUR_WAY;//number
    private String TRAIN_NUM;//destination
    private String TRACK_NUM;//source
    private Timestamp TIME;//time
    private Timestamp jcStartTime;
    private String jcType;
    private float jcSumHc;
    private float jcHc;
    private String jcJSL;
    private String jcImportant;
    private String jcQBID;
    private String jcQBIDN;

    public String getCopyId() {
        return copyId;
    }

    public void setCopyId(String copyId) {
        this.copyId = copyId;
    }

    public String getNODE_FOUR_WAY() {
        return NODE_FOUR_WAY;
    }

    public void setNODE_FOUR_WAY(String NODE_FOUR_WAY) {
        this.NODE_FOUR_WAY = NODE_FOUR_WAY;
    }

    public String getTRAIN_NUM() {
        return TRAIN_NUM;
    }

    public void setTRAIN_NUM(String TRAIN_NUM) {
        this.TRAIN_NUM = TRAIN_NUM;
    }

    public String getTRACK_NUM() {
        return TRACK_NUM;
    }

    public void setTRACK_NUM(String RACK_NUM) {
        this.TRACK_NUM = RACK_NUM;
    }

    public Timestamp getTIME() {
        return TIME;
    }

    public void setTIME(Timestamp TIME) {
        this.TIME = TIME;
    }

    public Timestamp getJcStartTime() {
        return jcStartTime;
    }

    public void setJcStartTime(Timestamp jcStartTime) {
        this.jcStartTime = jcStartTime;
    }

    public String getJcType() {
        return jcType;
    }

    public void setJcType(String jcType) {
        this.jcType = jcType;
    }

    public float getJcSumHc() {
        return jcSumHc;
    }

    public void setJcSumHc(float jcSumHc) {
        this.jcSumHc = jcSumHc;
    }

    public float getJcHc() {
        return jcHc;
    }

    public void setJcHc(float jcHc) {
        this.jcHc = jcHc;
    }

    public String getJcJSL() {
        return jcJSL;
    }

    public void setJcJSL(String jcJSL) {
        this.jcJSL = jcJSL;
    }

    public String getJcImportant() {
        return jcImportant;
    }

    public void setJcImportant(String jcImportant) {
        this.jcImportant = jcImportant;
    }

    public String getJcQBID() {
        return jcQBID;
    }

    public void setJcQBID(String jcQBID) {
        this.jcQBID = jcQBID;
    }

    public String getJcQBIDN() {
        return jcQBIDN;
    }

    public void setJcQBIDN(String jcQBIDN) {
        this.jcQBIDN = jcQBIDN;
    }
}
