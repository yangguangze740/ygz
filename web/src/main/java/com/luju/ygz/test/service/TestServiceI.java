package com.luju.ygz.test.service;

import com.luju.pojo.JcPlanInfo;
import com.luju.pojo.ResultInfo;

import java.util.List;
import java.util.Set;

public interface TestServiceI {

    /* data select from ora to mysql*/
    void selectPlanDataFromOra();

    /* data processing from mysql copy data to jc_plan */
    void selectPlanData4One();

    /* data processing from mysql copy data to jc_plan_detals */
    void selectPlanData4HCJSL();

    /* select all processed data from jc_plan */
    List<ResultInfo> processJcData();

    List<ResultInfo> processBwjData();

    /* process bwj data */
    void selectBwjData();

    List<ResultInfo> selectBwPlanInPath(JcPlanInfo jcPlanInfo);
}
