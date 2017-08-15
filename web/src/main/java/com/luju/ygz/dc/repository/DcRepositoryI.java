package com.luju.ygz.dc.repository;

import com.luju.pojo.DcPlanInfo;
import com.luju.pojo.ResultInfo;

import java.util.List;

public interface DcRepositoryI {

    /* data select from ora to mysql*/
    List<DcPlanInfo> selectDcPlanFromOra();

    /* data select from ora to mysql*/
    List<DcPlanInfo> selectJtPlan();

    /* process zm data*/
    List<DcPlanInfo> selectZmPlan();

    /* process tc data*/
    List<DcPlanInfo> selectTcPlan();

    /* process tc2 data*/
    List<DcPlanInfo> processTcPlan();

    /* process zc data*/
    List<DcPlanInfo> selectZcPlan();

    /* select jt data1*/
    List<ResultInfo> selectJtData4XD1();

    /* select jt data2*/
    List<ResultInfo> selectJtData4XD2();

    /* select zm data1*/
    List<ResultInfo> selectZmData4XD1();

    /* select zm data2*/
    List<ResultInfo> selectZmData4XD2();

    /* select zc data*/
    List<ResultInfo> selectZcData();

    /* process tc data*/
    List<ResultInfo> selectTcData();


    /* select jt data for ajax in path1*/
    List<DcPlanInfo> selectJtDataInPath1(DcPlanInfo dcPlanInfo);

    /* select zm data for ajax in path1*/
    List<DcPlanInfo> selectZmDataInPath1(DcPlanInfo dcPlanInfo);

    /* select tc data for ajax in path1*/
    List<DcPlanInfo> selectTcDataInPath1(DcPlanInfo dcPlanInfo);

    /* data insert from ora to mysql*/
    boolean insertToPlanCopy(DcPlanInfo info);

    /* jt data insert to mysql*/
    boolean insertJtPlan4QC(DcPlanInfo info);
    boolean insertJtPlan4JD(DcPlanInfo info);

    /* zm data insert to mysql*/
    boolean insertZmPlan4XT1(DcPlanInfo info);
    boolean insertZmPlan4XT2(DcPlanInfo info);

    /* tc data insert to mysql*/
    boolean insertTcPlan4XT1(DcPlanInfo info);

    /* tc data insert to mysql*/
    boolean insertTcPlan4XT2(DcPlanInfo info);

    /* tc 6 data insert to mysql*/
    boolean insertTcPlan4Six(DcPlanInfo info);

    /* zc data insert to mysql*/
    boolean insertZcPlan(DcPlanInfo info);

    /* delete data from mysql before inserting new data */
    int deletePlanCopy();
}
