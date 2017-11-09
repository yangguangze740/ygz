package com.luju.ygz.xz.service;


import com.luju.pojo.DcPlanInfo;

import java.util.List;

public interface XzServiceI {
    java.util.List<DcPlanInfo> selectAllXzData();

    java.util.List<DcPlanInfo> selectCxData();

    List<DcPlanInfo> selectxbData();

    List<DcPlanInfo> selectjlData();
}
