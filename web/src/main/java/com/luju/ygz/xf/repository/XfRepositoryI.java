package com.luju.ygz.xf.repository;

import com.luju.pojo.DcPlanInfo;
import com.luju.pojo.StatisticsInfo;

import java.util.List;

public interface XfRepositoryI {

    List<DcPlanInfo> select4XfList();

    List<DcPlanInfo> selectDcData();

    List<DcPlanInfo> selectPath(DcPlanInfo bean);

    List<StatisticsInfo> selectStatisticsInfo();

    int insertStatisticsInfo(StatisticsInfo info);
}
