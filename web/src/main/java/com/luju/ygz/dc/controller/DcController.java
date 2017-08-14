package com.luju.ygz.dc.controller;

import com.luju.pojo.DcPlanInfo;
import com.luju.ygz.dc.service.DcServiceI;
import luju.common.util.DataProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
    public String processDcData() {
//        service.selectJtPlanData(dataProcess);
//        service.selectTcPlanData(dataProcess);
//        service.selectZcPlanData(dataProcess);
//        service.selectZmPlanData(dataProcess);
        service.processTcPlanData(dataProcess);
        return "world";
    }

    @RequestMapping("/dcPlan")
    public ModelAndView selectDcData() {
        ModelAndView mav = new ModelAndView();
        List<DcPlanInfo> list4Jt1 = service.selectJtData1();
        List<DcPlanInfo> list4Jt2 = service.selectJtData2();
        List<DcPlanInfo> list4Zm1 = service.selectZmData1();
        List<DcPlanInfo> list4Zm2 = service.selectZmData2();
        List<DcPlanInfo> list4Zc2 = service.selectZcData();
        //
        //

        List<DcPlanInfo> list4JtP1 = service.selectJtDataInPath1();
        List<DcPlanInfo> list4ZmP1 = service.selectZmDataInPath1();

        return mav;
    }
}
