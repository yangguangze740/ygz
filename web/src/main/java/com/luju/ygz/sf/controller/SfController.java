package com.luju.ygz.sf.controller;

import com.luju.pojo.DcPlanInfo;
import com.luju.ygz.sf.service.SfServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/luju")
public class SfController {

    @Autowired
    private SfServiceI service;

    @RequestMapping("/sfPlan")
    public ModelAndView xfData(){
        ModelAndView mav = new ModelAndView("luju/sfPlan");

        List<DcPlanInfo> sfList = service.sfList();
        Map<String,List<DcPlanInfo>> mapList = service.selectDcPath();

        mav.addObject("sfList",sfList);
        mav.addObject("mapList",mapList);

        return mav;
    }
}
