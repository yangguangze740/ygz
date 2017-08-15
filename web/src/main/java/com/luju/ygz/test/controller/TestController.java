package com.luju.ygz.test.controller;

import com.luju.ygz.test.service.TestServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/luju")
public class TestController {

    @Autowired
    private TestServiceI service;

    @RequestMapping("/hello")
    public String test() {

        service.selectPlanDataFromOra();
        return "hello";
    }

    @RequestMapping("/world")
    public String dataFromCopyToOne() {

        service.selectPlanData4One();
        service.selectPlanData4HCJSL();
        service.selectBwjData();
        return "world";
    }
}
