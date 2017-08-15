package com.luju.ygz.dc.service;

import com.luju.pojo.DcPlanInfo;
import com.luju.pojo.ResultInfo;
import luju.common.util.DataProcess;
import luju.common.util.ListToSet;

import java.util.List;
import java.util.Set;

public interface DcServiceI {
    
    /* data select from ora to mysql*/
    void selectPlanDataFromOra(DataProcess dataProcess);

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
    List<DcPlanInfo> selectJtDataInPath1(DcPlanInfo dcPlanInfo);

    /* select zm data in path*/
    List<DcPlanInfo> selectZmDataInPath1(DcPlanInfo dcPlanInfo);

    /* select tc data in path*/
    List<DcPlanInfo> selectTcDataInPath1(DcPlanInfo dcPlanInfo);
}
