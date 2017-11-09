package com.luju.ygz.xz.repository;


import com.luju.pojo.DcPlanInfo;

import java.util.List;

public interface XzRepositoryI {
    java.util.List<DcPlanInfo> select4AllXzData();

    java.util.List<DcPlanInfo> select4CxData();

    List<DcPlanInfo> select4XbData();

    List<DcPlanInfo> select4JlData();

    List<DcPlanInfo> select4JlsData(DcPlanInfo time);
}
