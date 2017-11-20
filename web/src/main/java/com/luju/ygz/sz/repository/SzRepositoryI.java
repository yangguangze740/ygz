package com.luju.ygz.sz.repository;

import com.luju.pojo.DcPlanInfo;

import java.util.List;


public interface SzRepositoryI {
    List<DcPlanInfo> select4AllSzData();

    List<DcPlanInfo> select4JlData();

    List<DcPlanInfo> select4CxData();

    List<DcPlanInfo> select4AllJlData();

    List<DcPlanInfo> select4JlsData(DcPlanInfo time);

    List<DcPlanInfo> selec4FiveData();

    List<DcPlanInfo> select4XbData();

    List<DcPlanInfo> select4ZwData();

    List<DcPlanInfo> select4YhData();

    List<DcPlanInfo> select4DcData();

    List<DcPlanInfo> select4Time1Data();

    List<DcPlanInfo> select4Time2Data();

    List<DcPlanInfo> select4JcData();
}
