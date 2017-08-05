package com.luju.ygz.test.controller;

import com.luju.pojo.JcPlanInfo;
import com.luju.ygz.test.service.TestServiceI;
import luju.common.util.ConstantFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/luju")
public class TestController {
    @Autowired
    private TestServiceI service;

    @RequestMapping("/route")
    public String routeLogin() {
        return "luju/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String test(HttpServletRequest request) {
        String username = request.getParameter(ConstantFields.REQUEST_NAME_KEY);
        String password = request.getParameter(ConstantFields.REQUEST_PSWD_KEY);

        String admin = "admin";

        if (username.equals(admin) && password.equals(admin)) {
            return "redirect:/luju/jcPlan.action";
        } else {
            return "redirect:/luju/route.action";
        }
    }

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

        List<JcPlanInfo> list = service.selectPlan4XD();
        List<JcPlanInfo> path = service.selectPath();
        mav.addObject("list",list);

        return mav;
    }

    @RequestMapping("/bwj")
    public String bwjtest(){
        service.selectBwjData();
        return "bwj";
    }

    @RequestMapping("/bwPlan")
    public ModelAndView allBwData(JcPlanInfo info){
        ModelAndView mav = new ModelAndView("/luju/bwPlan");

        List<JcPlanInfo> lists = service.selectBwPlanAll();

        mav.addObject("lists",lists);
        return mav;

    }
}
