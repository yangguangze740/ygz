package com.luju.ygz.sd.repository;


import com.luju.pojo.DcPlanInfo;
import com.luju.pojo.StatisticsInfo;

import java.util.List;

public interface SdRepositoryI {
    List<DcPlanInfo> select4SdList();

    List<DcPlanInfo> select4CxList();

    List<DcPlanInfo> select4SixList();

    List<DcPlanInfo> select4TzList();

    List<DcPlanInfo> select4Partition();

    List<DcPlanInfo> selectDcData();

    List<DcPlanInfo> selectPath(DcPlanInfo dcPlanInfo);

    List<StatisticsInfo> selectStatisticsInfo();

    int insertStatisticsInfo(StatisticsInfo info);

    int updateSource(DcPlanInfo info);

    int updateDestination(DcPlanInfo dcPlanInfo);

    List<DcPlanInfo> select4chunjian();

    List<DcPlanInfo> select4chunjian2();
}
