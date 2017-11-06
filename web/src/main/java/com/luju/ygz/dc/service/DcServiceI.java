package com.luju.ygz.dc.service;

import com.luju.pojo.DcPlanInfo;
import com.luju.pojo.ResultInfo;
import com.luju.pojo.StatisticsInfo;
import com.luju.pojo.TextareaInfo;
import luju.common.util.DataProcess;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface DcServiceI {
    
    /* data select from ora to mysql*/
    void selectPlanDataFromOra(DataProcess dataProcess);

    void selectPlanDataFromOraWithDelete(DataProcess dataProcess);

    /* select jt data and process*/
    void selectJtPlanData(DataProcess dataProcess);

    /* select zm data and process*/
    void selectZmPlanData(DataProcess dataProcess);

    /* select tc data and process*/
    void selectTcPlanData(DataProcess dataProcess);
    void processTcPlanData(DataProcess dataProcess);

    /* select zc data and process*/
    void selectZcPlanData(DataProcess dataProcess);

    /* select jt1 data */
    List<ResultInfo> processJt1Data();

    /* select jt2 data */
    List<ResultInfo> processJt2Data();

    /* select zm1 data */
    List<ResultInfo> processZm1Data();

    /* select zm2 data */
    List<ResultInfo> processZm2Data();

    /* select zc data */
    List<ResultInfo> processZcData();

    /* select tc data */
    List<ResultInfo> processTcData();

    /* select jt data in path*/
    List<ResultInfo> selectJtDataInPath1(DcPlanInfo dcPlanInfo);
    /* select zm data in path*/
    List<ResultInfo> selectZmDataInPath1(DcPlanInfo dcPlanInfo);

    /* select tc data in path*/
    List<ResultInfo> selectTcDataInPath1(DcPlanInfo dcPlanInfo);



    void processTcDataNew(DataProcess dataProcess);

    boolean processDcData(DataProcess dataProcess);

    List<DcPlanInfo> selectDcData();

    Map<String,List<DcPlanInfo>> selectDcPath();

    void processJFCX();

    List<DcPlanInfo> selectJF();

    List<DcPlanInfo> selectCX();

    int updateSource(DcPlanInfo dcPlanInfo);

    int updateDestination(DcPlanInfo dcPlanInfo);

    int deleteShowData();

    int deleteTcData();

    int deleteTcDataSix();

    int deleteTFCX();

    int deleteDcCopy();

    int insertTextarea(String s);

    List<TextareaInfo> selectTextarea(TextareaInfo info);

    int updateTextareaIsSelected(int isS);

    List<StatisticsInfo> selectStatisticsInfoWithTime(String time);

    List<DcPlanInfo> select4Partition();
}
