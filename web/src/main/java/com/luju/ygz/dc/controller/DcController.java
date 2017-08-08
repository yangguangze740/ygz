package com.luju.ygz.dc.controller;

import com.luju.ygz.dc.service.DcServiceI;
import luju.common.util.DataProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class DcController {

    @Autowired
    private DcServiceI service;

    DataProcess dataProcess = new DataProcess();

    @RequestMapping("/hello")
    public String test() {
        service.selectPlanDataFromOra(dataProcess);

        return "hello";
    }

    @RequestMapping("/world")
    public String selectJtData() {
        service.selectJtPlanData(dataProcess);
        service.selectTcPlanData(dataProcess);
        service.selectZcPlanData(dataProcess);
        service.selectZmPlanData(dataProcess);
        return "world";
    }
}
