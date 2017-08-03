package com.luju.ygz.dc.controller;

import com.luju.pojo.DcPlanInfo;
import com.luju.ygz.dc.service.DcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class DcController {

    @Autowired
    private DcService service;

    @RequestMapping("/hello")
    public String test(DcPlanInfo info) {
        service.selectPlanDataFromOra();
        return "hello";
    }
}
