package com.luju.ygz.dc.service;

import luju.common.util.DataProcess;

public interface DcServiceI {
    
    /* data select from ora to mysql*/
    public void selectPlanDataFromOra(DataProcess dataProcess);

    /* data select from ora to mysql*/
    public void selectJtPlanData(DataProcess dataProcess);
}
