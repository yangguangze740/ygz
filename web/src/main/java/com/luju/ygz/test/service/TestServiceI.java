package com.luju.ygz.test.service;

import com.luju.pojo.JcPlanInfo;
import java.util.List;
import java.util.Set;

public interface TestServiceI {

    /* data select from ora to mysql*/
    public void selectPlanDataFromOra();

    /* data processing from mysql copy data to jc_plan */
    public void selectPlanData4One();

    /* data processing from mysql copy data to jc_plan_detals */
    public void selectPlanData4HCJSL();

    /* select all processed data from jc_plan */
    public Set<JcPlanInfo> selectAllData();

    /* process bwj data */
    public void selectBwjData();

    public List<JcPlanInfo> selectBwPlanInPath(JcPlanInfo jcPlanInfo);
}
