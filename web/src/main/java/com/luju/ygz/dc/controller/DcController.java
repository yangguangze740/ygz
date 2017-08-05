package com.luju.ygz.dc.controller;

import com.luju.pojo.DcPlanInfo;
import com.luju.ygz.dc.service.DcServiceI;
import luju.common.util.DataProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
        //list = service.selectJtData();
        return "world";
    }
}
