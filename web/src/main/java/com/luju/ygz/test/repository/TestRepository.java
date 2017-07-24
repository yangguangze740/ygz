package com.luju.ygz.test.repository;


import com.luju.pojo.JcPlanInfo;

import java.util.List;

public interface TestRepository {


    public List<JcPlanInfo> selectJcPlanFromOra();

    public List<JcPlanInfo> selectJcPlan();

    public boolean insertToPlanCopy(JcPlanInfo info);
}
