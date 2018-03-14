package com.luju.ygz.statistics.repository;


import com.luju.pojo.DcPlanInfo;
import com.luju.pojo.StatisticsInfo;

import java.util.List;

public interface StaisticRepositoryI {
    int queryJlConflict();

    int queryBetter();

    int queryError();

    List<StatisticsInfo> queryMonthConflict();

    List<StatisticsInfo> queryAllList();
}
