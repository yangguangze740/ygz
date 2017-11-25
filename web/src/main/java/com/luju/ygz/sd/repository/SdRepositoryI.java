package com.luju.ygz.sd.repository;


import com.luju.pojo.DcPlanInfo;

import java.util.List;

public interface SdRepositoryI {
    List<DcPlanInfo> select4SdList();

    List<DcPlanInfo> select4CxList();

    List<DcPlanInfo> select4SixList();

    List<DcPlanInfo> select4TzList();

    List<DcPlanInfo> select4Partition();
}
