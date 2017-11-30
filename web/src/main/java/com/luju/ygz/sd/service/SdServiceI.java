package com.luju.ygz.sd.service;


import com.luju.pojo.DcPlanInfo;

import java.util.List;
import java.util.Map;

public interface SdServiceI {
    List<DcPlanInfo> selectSdList();

    List<DcPlanInfo> selectCxList();

    List<DcPlanInfo> selectSixList();

    List<DcPlanInfo> selectTzList();

    List<DcPlanInfo> selectPartitionList();

    Map<String,List<DcPlanInfo>> selectDcPath();

    int updateSource(DcPlanInfo dcPlanInfo);

    int updateDestination(DcPlanInfo dcPlanInfo);
}
