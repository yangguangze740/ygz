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
        List<DcPlanInfo> cxList = sdService.selectCxList();
        List<DcPlanInfo> sixList = sdService.selectSixList();
        List<DcPlanInfo> tzList = sdService.selectTzList();
        List<DcPlanInfo> partition = sdService.selectPartitionList();

        mav.addObject("cxList",cxList);
        mav.addObject("sdList",sdList);
        mav.addObject("sixList",sixList);
        mav.addObject("tzList",tzList);
        mav.addObject("partion",partition);
        return mav;
    }
}
