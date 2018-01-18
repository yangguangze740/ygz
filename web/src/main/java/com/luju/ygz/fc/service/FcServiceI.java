package com.luju.ygz.fc.service;

import luju.common.util.DataProcess;

public interface FcServiceI {

    void selectPlanDataFromOra(DataProcess dataProcess);

    boolean processBFData(DataProcess dataProcess);
}
