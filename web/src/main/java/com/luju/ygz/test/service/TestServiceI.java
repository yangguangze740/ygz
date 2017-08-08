package com.luju.ygz.test.service;

import com.luju.pojo.JcPlanInfo;
import java.util.List;

public interface TestServiceI {

    /* data select from ora to mysql*/
    public void selectPlanDataFromOra();

    /* data processing from mysql copy data to jc_plan */
    public void selectPlanData4One();

    /* data processing from mysql copy data to jc_plan_detals */
    public void selectPlanData4HCJSL();

    /* select all processed data from jc_plan */
    public List<JcPlanInfo> selectPlan4XD();

    /* select path to determine the conflict */
    public List<JcPlanInfo> selectPath();

    /* 处理本务机数据 */
    public void selectBwjData();

    /* 获取本务机车辆*/
    public List<JcPlanInfo> selectBwPlanAll();

    public List<JcPlanInfo> selectBwPlanInPath();
}
