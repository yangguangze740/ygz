package com.luju.ygz.test.repository;

import com.luju.pojo.JcPlanInfo;
import java.util.List;

public interface TestRepositoryI {

    /* data select from ora to mysql*/
    public List<JcPlanInfo> selectJcPlanFromOra();

    /* data processing from mysql copy data to jc_plan */
    public List<JcPlanInfo> selectJcPlan4One();

    /* select and data processing from mysql copy data to jc_plan for HC */
    public List<JcPlanInfo> selectJcPlan4HC();

    /* data processing from mysql copy data to jc_plan_detals for JF */
    public List<JcPlanInfo> selectJcPlan4JF();

    /* data processing from mysql copy data to jc_plan_detals for CX */
    public List<JcPlanInfo> selectJcPlan4CX();

    /* data processing from mysql copy data to jc_plan_detals for CC */
    public List<JcPlanInfo> selectJcPlan4CC();

    /* select all processed data */
    public List<JcPlanInfo> selectJcPlanALL();

    /* select path data to determine the conflict */
    public List<JcPlanInfo> selectJcPath();

    /*获取本务机数据*/
    public List<JcPlanInfo> selectBwPlan();

    public List<JcPlanInfo> selectBwjData();

    /* data insert from ora to mysql*/
    public boolean insertToPlanCopy(JcPlanInfo info);
    /* insert processed data from mysql copy data to jc_plan */
    public boolean insertToPlan4One(JcPlanInfo info);

    /* insert processed data from mysql copy data to jc_plan for HC */
    public boolean insertToPlan4HC(JcPlanInfo info);

    /* insert processed data from mysql copy data to jc_plan_detals for JF */
    public boolean insertToPlan4JF(JcPlanInfo info);

    /* insert processed data from mysql copy data to jc_plan_detals for CX*/
    public boolean insertToPlan4CX(JcPlanInfo info);

    /* insert processed data from mysql copy data to jc_plan_detals for CC*/
    public boolean insertToPlan4CC(JcPlanInfo info);

    public boolean insertToBwjPlan4N(JcPlanInfo info);

    public boolean insertToBwjPlan4S(JcPlanInfo info);
}
