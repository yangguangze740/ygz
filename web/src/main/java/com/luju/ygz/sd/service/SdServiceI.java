package com.luju.ygz.sd.service;


import com.luju.pojo.DcPlanInfo;

import java.util.List;

public interface SdServiceI {
    List<DcPlanInfo> selectSdList();

    List<DcPlanInfo> selectCxList();

    List<DcPlanInfo> selectSixList();

    List<DcPlanInfo> selectTzList();

    List<DcPlanInfo> selectPartitionList();
}
