package com.luju.ygz.dc.repository;

import com.luju.pojo.DcPlanInfo;

import java.util.List;

public interface DcRepositoryI {

    /* data select from ora to mysql*/
    public List<DcPlanInfo> selectDcPlanFromOra();

    /* data select from ora to mysql*/
    public List<DcPlanInfo> selectJtPlan();

    /* data insert from ora to mysql*/
    public boolean insertToPlanCopy(DcPlanInfo info);

    /* jt data insert to mysql*/
    public boolean insertJtPlan4QC(DcPlanInfo info);
    public boolean insertJtPlan4JD(DcPlanInfo info);

    /* delete data from mysql before inserting new data */
    public int deletePlanCopy();
}
