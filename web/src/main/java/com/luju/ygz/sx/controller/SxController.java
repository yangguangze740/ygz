package com.luju.ygz.sx.controller;

import com.luju.pojo.DcPlanInfo;
import com.luju.ygz.sx.service.SxServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/luju")
public class SxController {

    @Autowired
    private SxServiceI sxService;

    @RequestMapping("/sxPlan")
    public ModelAndView sdPlan(){
        ModelAndView mav = new ModelAndView("luju/sxPlan");
        List<DcPlanInfo> sxList = sxService.selectAllList();

        mav.addObject("sxList",sxList);

        return mav;
    }
}
