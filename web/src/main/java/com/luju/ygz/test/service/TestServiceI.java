package com.luju.ygz.test.service;

import com.luju.pojo.DcPlanInfo;
import luju.common.util.DataProcess;

import java.util.List;

public interface TestServiceI {

    /* 从ora数据库中获取接车数据*/
    void selectPlanDataFromOra(DataProcess dataProcess);

    List<DcPlanInfo> processSdData(DataProcess dataProcess);
}
