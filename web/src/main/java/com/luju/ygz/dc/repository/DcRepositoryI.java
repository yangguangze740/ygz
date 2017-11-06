package com.luju.ygz.dc.repository;

import com.luju.pojo.DcPlanInfo;
import com.luju.pojo.ResultInfo;
import com.luju.pojo.StatisticsInfo;
import com.luju.pojo.TextareaInfo;

import java.sql.Date;
import java.util.List;

public interface DcRepositoryI {

    /* data select from ora to mysql*/
    List<DcPlanInfo> selectDcPlanFromOra();

    List<DcPlanInfo> selectCopyData();

    int deleteRepeatDataFromCopy(String s);

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

    List<DcPlanInfo> selectJF();

    List<DcPlanInfo> selectCX();

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
    List<ResultInfo> selectJtDataInPath1(DcPlanInfo dcPlanInfo);

    /* select zm data for ajax in path1*/
    List<ResultInfo> selectZmDataInPath1(DcPlanInfo dcPlanInfo);

    /* select tc data for ajax in path1*/
    List<ResultInfo> selectTcDataInPath1(DcPlanInfo dcPlanInfo);

    List<ResultInfo> aqselectZmData4XD2();

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

    boolean insertTcPlanNew(DcPlanInfo info);

    boolean insertTcPlanNewAll(final List<DcPlanInfo> dcPlanInfos);

    List<DcPlanInfo> processTcDataNew();

    boolean insertTcDataNew(DcPlanInfo info);

    boolean insertTcDataNewAll(final List<DcPlanInfo> dcPlanInfos);

    List<DcPlanInfo> selectTcPlanNew();

    boolean insertDcData(final List<DcPlanInfo> dcPlanInfos);

    List<DcPlanInfo> selectDcData();

    List<DcPlanInfo> selectPath(DcPlanInfo dcPlanInfo);

    int deleteDcShow();

    int deleteTcData();

    int deleteTcDataSix();

    int deleteTFCX();

    int deleteDcCopy();

    int updateSource(DcPlanInfo info);

    int updateDestination(DcPlanInfo info);

    int insertTextarea(String s);

    List<TextareaInfo> selectTextareaInfo(TextareaInfo info);

    int updateTextareaInfoIsSelected(int isS);

    int insertStatisticsInfo(StatisticsInfo info);

    List<StatisticsInfo> selectStatisticsInfo();

    List<StatisticsInfo> selectStatisticsInfoWithTime(String time);

    List<DcPlanInfo> select4Partition();


}
