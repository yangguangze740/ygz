package com.luju.ygz.sf.repository;

import com.luju.pojo.DcPlanInfo;
import com.luju.pojo.StatisticsInfo;

import java.util.List;

public interface SfRepositoryI {

    List<DcPlanInfo> select4SfList();

    List<DcPlanInfo> selectDcData();

    List<DcPlanInfo> selectPath(DcPlanInfo bean);

    List<StatisticsInfo> selectStatisticsInfo();

    int insertStatisticsInfo(StatisticsInfo info);
}
