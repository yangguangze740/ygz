package com.luju.ygz.sf.service;

import com.luju.pojo.DcPlanInfo;

import java.util.List;
import java.util.Map;

public interface SfServiceI {

    List<DcPlanInfo> sfList();

    Map<String,List<DcPlanInfo>> selectDcPath();
}