package com.luju.ygz.dc.repository;

import com.luju.pojo.DcPlanInfo;

import java.util.List;

public interface DcRepositoryI {

    /* data select from ora to mysql*/
    public List<DcPlanInfo> selectDcPlanFromOra();

    /* data insert from ora to mysql*/
    public boolean insertToPlanCopy(DcPlanInfo info);
}
