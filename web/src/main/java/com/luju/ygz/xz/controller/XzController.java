package com.luju.ygz.xz.controller;

import com.luju.pojo.*;

import com.luju.ygz.xz.service.XzServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import org.springframework.web.servlet.ModelAndView;


import java.util.List;


@Controller
@RequestMapping("/luju")
public class XzController {

    @Autowired
    private XzServiceI xzService;

    @RequestMapping("/xzPlan")
    public ModelAndView allXzData(){
        ModelAndView mav = new ModelAndView("luju/xzPlan");
        List<DcPlanInfo> allXzList = xzService.selectAllXzData();
        List<DcPlanInfo> cxList = xzService.selectCxData();
        List<DcPlanInfo> xbList = xzService.selectxbData();
        List<DcPlanInfo> jlList = xzService.selectjlData();

        mav.addObject("allXzList",allXzList);
        mav.addObject("cxList",cxList);
        mav.addObject("xbList",xbList);
        mav.addObject("jlList",jlList);

        return mav;
    }



}
