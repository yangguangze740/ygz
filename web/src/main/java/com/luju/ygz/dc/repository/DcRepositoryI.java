package com.luju.ygz.dc.repository;

import com.luju.pojo.DcPlanInfo;
import com.luju.pojo.StatisticsInfo;
import com.luju.pojo.TextareaInfo;

import java.util.List;

public interface DcRepositoryI {

    /* data select from ora to mysql*/
    List<DcPlanInfo> selectDcPlanFromOra();

    List<DcPlanInfo> selectCopyData();

    int deleteRepeatDataFromCopy(String s);

    /* data select from ora to mysql*/
    List<DcPlanInfo> selectJtPlan();

    List<DcPlanInfo> selectJtzData();

    /* process zm data*/
    List<DcPlanInfo> selectZmPlan();

    /* process tc data*/
    List<DcPlanInfo> selectTcPlan();

    /* process zc data*/
    List<DcPlanInfo> selectZcPlan();

    List<DcPlanInfo> selectJF();

    List<DcPlanInfo> selectCX();

    /* data insert from ora to mysql*/
    boolean insertToPlanCopy(DcPlanInfo info);

    boolean insertTcPlanNewAll(final List<DcPlanInfo> dcPlanInfos);

    List<DcPlanInfo> processTcDataNew();

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
