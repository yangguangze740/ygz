package com.luju.ygz.dc.controller;

import com.luju.ygz.dc.service.DcServiceI;
import com.luju.ygz.test.service.TestServiceI;
import luju.common.util.DataProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AutoSelectDataFromOra {

    @Autowired
    private DcServiceI dcService;

    @Autowired
    private TestServiceI jcService;

    DataProcess dataProcess = new DataProcess();

    @Scheduled(cron = "0 0/5 * * * ?")
    public void AutoSelectDataFromOra() {
        dcService.selectPlanDataFromOra(dataProcess);
        jcService.selectPlanDataFromOra(dataProcess);
    }
}
