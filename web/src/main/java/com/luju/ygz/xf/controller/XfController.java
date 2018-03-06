package com.luju.ygz.xf.controller;

import com.luju.pojo.DcPlanInfo;
import com.luju.ygz.xf.service.XfServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/luju")
public class XfController {

    @Autowired
    private XfServiceI service;

    @RequestMapping("/xfPlan")
    public ModelAndView xfData(){
        ModelAndView mav = new ModelAndView("luju/xfPlan");

        List<DcPlanInfo> xfList = service.xfList();
        Map<String,List<DcPlanInfo>> mapList = service.selectDcPath();

        mav.addObject("xfList",xfList);

        return mav;
    }
}
