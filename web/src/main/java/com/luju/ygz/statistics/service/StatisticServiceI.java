package com.luju.ygz.statistics.service;


import com.luju.pojo.StatisticsInfo;

import java.util.List;

public interface StatisticServiceI {
    int queryJlConflict();

    int queryBetter();

    int queryError();

    List<StatisticsInfo> queryMonthConflict();

    List<StatisticsInfo> queryAllList();
}
