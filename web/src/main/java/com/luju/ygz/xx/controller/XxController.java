package com.luju.ygz.xx.controller;

import com.luju.pojo.DcPlanInfo;
import com.luju.ygz.xx.service.XxServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/luju")
public class XxController {

    @Autowired
    private XxServiceI xxServiceI;
    @RequestMapping("/xxPlan")
    public ModelAndView XxPlan(){
        ModelAndView mav = new ModelAndView("luju/xxPlan");
        List<DcPlanInfo> allList = xxServiceI.selectAllList();

        mav.addObject("allList",allList);

        return mav;
    }
}
