package com.luju.ygz.xf.service;

import com.luju.pojo.DcPlanInfo;

import java.util.List;
import java.util.Map;

public interface XfServiceI {

    List<DcPlanInfo> xfList();

    Map<String,List<DcPlanInfo>> selectDcPath();
}
