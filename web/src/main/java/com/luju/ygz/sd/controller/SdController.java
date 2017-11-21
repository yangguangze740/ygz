package com.luju.ygz.sd.controller;

import com.luju.pojo.DcPlanInfo;
import com.luju.ygz.sd.service.SdServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/luju")
public class SdController {
    @Autowired
    private SdServiceI sdService;

    @RequestMapping("/sdPlan")
    public ModelAndView sdPlan(){
        ModelAndView mav = new ModelAndView("luju/sdPlan");
        List<DcPlanInfo> sdList = sdService.selectSdList();

        mav.addObject("sdList",sdList);
        return mav;
    }
}
