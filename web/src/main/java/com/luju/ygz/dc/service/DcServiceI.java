package com.luju.ygz.dc.service;

import com.luju.pojo.DcPlanInfo;
import luju.common.util.DataProcess;

import java.util.List;

public interface DcServiceI {
    
    /* data select from ora to mysql*/
    public void selectPlanDataFromOra(DataProcess dataProcess);

    /* select jt data and process*/
    public void selectJtPlanData(DataProcess dataProcess);

    /* select zm data and process*/
    public void selectZmPlanData(DataProcess dataProcess);

    /* select tc data and process*/
    public void selectTcPlanData(DataProcess dataProcess);

    /* select zc data and process*/
    public void selectZcPlanData(DataProcess dataProcess);

    /* data select from ora to mysql*/
    public List<DcPlanInfo> selectJtData1();

    /* data select from ora to mysql*/
    public List<DcPlanInfo> selectJtData2();
}
