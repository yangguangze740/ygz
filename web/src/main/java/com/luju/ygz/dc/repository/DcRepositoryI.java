package com.luju.ygz.dc.repository;

import com.luju.pojo.DcPlanInfo;

import java.util.List;

public interface DcRepositoryI {

    /* data select from ora to mysql*/
    public List<DcPlanInfo> selectDcPlanFromOra();

    /* data select from ora to mysql*/
    public List<DcPlanInfo> selectJtPlan();

    /* process zm data*/
    public List<DcPlanInfo> selectZmPlan();

    /* process tc data*/
    public List<DcPlanInfo> selectTcPlan();

    /* process tc2 data*/
    public List<DcPlanInfo> processTcPlan();

    /* process tc data*/
    public List<DcPlanInfo> selectTcData();

    /* process zc data*/
    public List<DcPlanInfo> selectZcPlan();

    /* select jt data1*/
    public List<DcPlanInfo> selectJtData4XD1();

    /* select jt data2*/
    public List<DcPlanInfo> selectJtData4XD2();

    /* select zm data1*/
    public List<DcPlanInfo> selectZmData4XD1();

    /* select zm data2*/
    public List<DcPlanInfo> selectZmData4XD2();

    /* select zc data*/
    public List<DcPlanInfo> selectZcData();


    /* select jt data for ajax in path1*/
    public List<DcPlanInfo> selectJtDataInPath1();

    /* select zm data for ajax in path1*/
    public List<DcPlanInfo> selectZmDataInPath1();

    /* data insert from ora to mysql*/
    public boolean insertToPlanCopy(DcPlanInfo info);

    /* jt data insert to mysql*/
    public boolean insertJtPlan4QC(DcPlanInfo info);
    public boolean insertJtPlan4JD(DcPlanInfo info);

    /* zm data insert to mysql*/
    public boolean insertZmPlan4XT1(DcPlanInfo info);
    public boolean insertZmPlan4XT2(DcPlanInfo info);

    /* tc data insert to mysql*/
    public boolean insertTcPlan4XT1(DcPlanInfo info);

    /* tc data insert to mysql*/
    public boolean insertTcPlan4XT2(DcPlanInfo info);

    /* tc 6 data insert to mysql*/
    public boolean insertTcPlan4Six(DcPlanInfo info);

    /* zc data insert to mysql*/
    public boolean insertZcPlan(DcPlanInfo info);

    /* delete data from mysql before inserting new data */
    public int deletePlanCopy();
}
