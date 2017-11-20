package com.luju.ygz.test.repository;

import com.luju.pojo.DcPlanInfo;
import com.luju.pojo.JcPlanInfo;

import java.util.List;

public interface TestRepositoryI {

    /* data select from ora to mysql*/
    List<JcPlanInfo> selectJcPlanFromOra();

    /* data processing from mysql copy data to jc_plan_detals for JF */
    List<JcPlanInfo> selectJcPlan4JF();

    /* data processing from mysql copy data to jc_plan_detals for CX */
    List<JcPlanInfo> selectJcPlan4CX();

    boolean insertJcData(final List<JcPlanInfo> jcPlanInfos);

    /* insert processed data from mysql copy data to jc_plan_detals for JF */
    boolean insertToPlan4JF(JcPlanInfo info);

    /* insert processed data from mysql copy data to jc_plan_detals for CX*/
    boolean insertToPlan4CX(JcPlanInfo info);

    int deletePlanCopy();

    List<DcPlanInfo> selectBwjPlanNew();

    List<DcPlanInfo> selectJcPlanNew();




}
