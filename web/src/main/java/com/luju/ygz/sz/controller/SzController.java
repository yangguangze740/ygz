package com.luju.ygz.sz.controller;

import com.luju.pojo.DcPlanInfo;
import com.luju.ygz.sz.service.SzServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/luju")
public class SzController {

    @Autowired
    private SzServiceI szService;

    @RequestMapping("/szPlan")
    public ModelAndView allSzData(){
        ModelAndView mav = new ModelAndView("luju/szPlan");
        List<DcPlanInfo> list = szService.selectAllSzData();
        List<DcPlanInfo> jlList = szService.selectJlData();
        List<DcPlanInfo> cxList = szService.selectCxData();
        List<DcPlanInfo> jlsList = szService.selectJlsData();
        List<DcPlanInfo> fiveList = szService.selectFiveData();
        List<DcPlanInfo> xbList = szService.selectXbData();
        List<DcPlanInfo> yhList = szService.selectZwData();
        List<DcPlanInfo> jcList = szService.selectJcData();

        mav.addObject("jlList",jlList);
        mav.addObject("list",list);
        mav.addObject("cxList",cxList);
        mav.addObject("jlsList",jlsList);
        mav.addObject("fiveList",fiveList);
        mav.addObject("xbList",xbList);
        mav.addObject("yhList",yhList);
        mav.addObject("jcList",jcList);
        return mav;
    }
}
