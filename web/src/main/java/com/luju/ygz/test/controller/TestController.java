package com.luju.ygz.test.controller;

import com.luju.pojo.DcPlanInfo;
import com.luju.ygz.test.service.TestServiceI;
import luju.common.util.DataProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/luju")
public class TestController {

    @Autowired
    private TestServiceI service;

    DataProcess dataProcess = new DataProcess();
    @RequestMapping("/test")
    public ModelAndView test1(){

        ModelAndView mav = new ModelAndView();
        List<DcPlanInfo> list = service.processSdData(dataProcess);

        mav.addObject("list",list);
        return mav;
    }

    @RequestMapping("/hello")
    public String test() {
        return "hello";
    }

    @RequestMapping("/world")
    public String dataFromCopyToOne() {

        return "world";
    }
}
