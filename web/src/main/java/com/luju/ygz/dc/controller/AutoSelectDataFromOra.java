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

    @Scheduled(cron = "0 0/45 * * * ?")
    public void AutoSelectDataFromOra() {

        jcService.selectPlanDataFromOra(dataProcess);
        System.out.println("jc select ora");

//        dcService.selectPlanDataFromOra(dataProcess);
        dcService.selectPlanDataFromOraWithDelete(dataProcess);
        System.out.println("dc select ora");

        dcService.deleteShowData();
        dcService.deleteTcData();
        dcService.deleteTcDataSix();
        dcService.deleteTFCX();

        dcService.processTcDataNew(dataProcess);
        dcService.processDcData(dataProcess);
        dcService.processJFCX();

    }

//    @Scheduled(cron = "0 0 4 * * ?")
//    public void AutoDeleteDataFromOra() {
//        dcService.deleteDcCopy();
//    }
}
