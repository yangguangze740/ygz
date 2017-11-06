package com.luju.ygz.test.service;

import luju.common.util.DataProcess;

public interface TestServiceI {

    /* 从ora数据库中获取接车数据*/
    void selectPlanDataFromOra(DataProcess dataProcess);
}
