package com.luju.ygz.dc.service;

import com.luju.pojo.DcPlanInfo;
import luju.common.util.DataProcess;
import luju.common.util.ListToSet;

import java.util.List;
import java.util.Set;

public interface DcServiceI {
    
    /* data select from ora to mysql*/
    public void selectPlanDataFromOra(DataProcess dataProcess);

    /* select jt data and process*/
    public void selectJtPlanData(DataProcess dataProcess);

    /* select zm data and process*/
    public void selectZmPlanData(DataProcess dataProcess);

    /* select tc data and process*/
    public void selectTcPlanData(DataProcess dataProcess);
    public void processTcPlanData(DataProcess dataProcess);

    /* select zc data and process*/
    public void selectZcPlanData(DataProcess dataProcess);

    /* select all data and process */
    public Set<DcPlanInfo> processAllData(ListToSet comparatorSet);

    /* select jt data*/
    public List<DcPlanInfo> selectJtDataInPath1();

    /* select zm data*/
    public List<DcPlanInfo> selectZmDataInPath1();
}
