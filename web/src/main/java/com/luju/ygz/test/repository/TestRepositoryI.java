package com.luju.ygz.test.repository;

import com.luju.pojo.JcPlanInfo;
import com.luju.pojo.ResultInfo;

import java.util.List;

public interface TestRepositoryI {

    /* data select from ora to mysql*/
    List<JcPlanInfo> selectJcPlanFromOra();

    /* data processing from mysql copy data to jc_plan */
    List<JcPlanInfo> selectJcPlan4One();

    /* select and data processing from mysql copy data to jc_plan for HC */
    List<JcPlanInfo> selectJcPlan4HC();

    /* data processing from mysql copy data to jc_plan_detals for JF */
    List<JcPlanInfo> selectJcPlan4JF();

    /* data processing from mysql copy data to jc_plan_detals for CX */
    List<JcPlanInfo> selectJcPlan4CX();

    /* data processing from mysql copy data to jc_plan_detals for CC */
    List<JcPlanInfo> selectJcPlan4CC();

    /* select all processed data */
    List<ResultInfo> selectJcPlan4XD();

    /* process bwj data*/
    List<JcPlanInfo> selectBwjPlan();

    /* select bwj data*/
    List<ResultInfo> selectBwjData();

    /* select bwj data path*/
    List<JcPlanInfo> selectBwjDataInPath(JcPlanInfo jcPlanInfo);

    /* data insert from ora to mysql*/
    boolean insertToPlanCopy(JcPlanInfo info);
    /* insert processed data from mysql copy data to jc_plan */
    boolean insertToPlan4One(JcPlanInfo info);

    /* insert processed data from mysql copy data to jc_plan for HC */
    boolean insertToPlan4HC(JcPlanInfo info);

    /* insert processed data from mysql copy data to jc_plan_detals for JF */
    boolean insertToPlan4JF(JcPlanInfo info);

    /* insert processed data from mysql copy data to jc_plan_detals for CX*/
    boolean insertToPlan4CX(JcPlanInfo info);

    /* insert processed data from mysql copy data to jc_plan_detals for CC*/
    boolean insertToPlan4CC(JcPlanInfo info);

    boolean insertToBwjPlan4N(JcPlanInfo info);

    boolean insertToBwjPlan4S(JcPlanInfo info);

    int deletePlanCopy();
}
