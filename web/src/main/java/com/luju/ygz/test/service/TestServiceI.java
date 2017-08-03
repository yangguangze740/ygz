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
    public List<JcPlanInfo> selectPlanAll();

    /* select path to determine the conflict */
    public List<JcPlanInfo> selectPath();

    /* data insert from ora to mysql*/
    public boolean insertDataToMysql(JcPlanInfo info);
   /*
   获取本务机车辆
    */
    List<JcPlanInfo> selectBwPlanAll();
    /*
    处理本务机数据
     */
    public void selectBwjData();
}
