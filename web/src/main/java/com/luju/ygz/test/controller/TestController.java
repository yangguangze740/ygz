package com.luju.ygz.test.controller;

import com.luju.pojo.JcPlanInfo;
import com.luju.ygz.test.service.impl.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/luju")
public class TestController {
    @Autowired
    private TestServiceImpl service;

    @RequestMapping("/hello")
    public String test(JcPlanInfo info) {
        service.selectPlanDataFromOra();
        return "hello";
    }

    @RequestMapping("/world")
    public String dataFromCopyToOne(JcPlanInfo info) {
        service.selectPlanData4One();
        service.selectPlanData4HCJSL();
        return "world";
    }

    @RequestMapping("/jcPlan")
    public ModelAndView allJcData(JcPlanInfo info) {
        ModelAndView mav = new ModelAndView("/luju/jcPlan");

        List<JcPlanInfo> list = service.selectPlanAll();
        List<JcPlanInfo> path = service.selectPath();
        mav.addObject("list",list);

        return mav;
    }
}
