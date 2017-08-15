package com.luju.ygz.dc.controller;

import com.luju.pojo.DcPlanInfo;
import com.luju.ygz.dc.service.DcServiceI;
import luju.common.util.ComparatorSet;
import luju.common.util.DataProcess;
import luju.common.util.ListToSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/test")
public class DcController {

    @Autowired
    private DcServiceI service;

    DataProcess dataProcess = new DataProcess();
    ListToSet comparatorSet = new ListToSet();

    @RequestMapping("/hello")
    public String test() {
        service.selectPlanDataFromOra(dataProcess);

        return "hello";
    }

    @RequestMapping("/world")
    public String processDcData() {
        service.selectJtPlanData(dataProcess);
        service.selectTcPlanData(dataProcess);
        service.selectZcPlanData(dataProcess);
        service.selectZmPlanData(dataProcess);
        service.processTcPlanData(dataProcess);

        return "world";
    }

    @RequestMapping("/dcPlan")
    public ModelAndView selectDcData() {
        ModelAndView mav = new ModelAndView();
        Set<DcPlanInfo> dcSet = service.processAllData(comparatorSet);

        List<DcPlanInfo> list4JtP1 = service.selectJtDataInPath1();
        List<DcPlanInfo> list4ZmP1 = service.selectZmDataInPath1();

        return mav;
    }
}
