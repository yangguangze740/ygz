package com.luju.ygz.sz.service;

import com.luju.pojo.DcPlanInfo;

import java.util.List;

public interface SzServiceI {
    List<DcPlanInfo> selectAllSzData();

    List<DcPlanInfo> selectJlData();

    List<DcPlanInfo> selectCxData();

    List<DcPlanInfo> selectJlsData();

    List<DcPlanInfo> selectFiveData();

    List<DcPlanInfo> selectXbData();

    List<DcPlanInfo> selectZwData();

    List<DcPlanInfo> selectJcData();
}
