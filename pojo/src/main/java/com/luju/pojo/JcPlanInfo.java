package com.luju.pojo;

import java.sql.Timestamp;

public class JcPlanInfo  {

    private String copyId;
    private String NODE_FOUR_WAY;//source
    private String TRAIN_NUM;//number
    private String TRACK_NUM;//destination
    private Timestamp TIME;//time
    private Timestamp jcStartTime;
    private String jcQBID;
    private float jcHc;
    private float jcSumHc;
    private int jcIsCC;
    private String jcJSL;
    private String jcImportant;
    private String jcType;
    private String jcQBIDN;
    private String jcXD;
    private String jcDH;
    private String jcPath;
    private String jcDCH;
    private int color;// 0白 1黄 2红

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getJcDCH() {
        return jcDCH;
    }

    public void setJcDCH(String jcDCH) {
        this.jcDCH = jcDCH;
    }

    public String getJcPath() {
        return jcPath;
    }

    public void setJcPath(String jcPath) {
        this.jcPath = jcPath;
    }

    public float getJcSumHc() {
        return jcSumHc;
    }

    public void setJcSumHc(float jcSumHc) {
        this.jcSumHc = jcSumHc;
    }

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

    public String getJcQBID() {
        return jcQBID;
    }

    public void setJcQBID(String jcQBID) {
        this.jcQBID = jcQBID;
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

    public String getJcQBIDN() {
        return jcQBIDN;
    }

    public void setJcQBIDN(String jcQBIDN) {
        this.jcQBIDN = jcQBIDN;
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

    public int getJcIsCC() {
        return jcIsCC;
    }

    public void setJcIsCC(int jcIsCC) {
        this.jcIsCC = jcIsCC;
    }

    public String getJcXD() {
        return jcXD;
    }

    public void setJcXD(String jcXD) {
        this.jcXD = jcXD;
    }

    public String getJcDH() {
        return jcDH;
    }

    public void setJcDH(String jcDH) {
        this.jcDH = jcDH;
    }
}
