package com.luju.ygz.sd.controller;

import com.luju.pojo.DcPlanInfo;
import com.luju.ygz.sd.service.SdServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

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
        Map<String,List<DcPlanInfo>> mapList = sdService.selectDcPath();

//        String chunjian = sdService.select4chunjian();
//        String chunjian2 = sdService.select4chunjian2();
//        if (!chunjian.equals("")) {
//            mav.addObject("chunjian",chunjian);
//        }
//
//        if (!chunjian2.equals("")) {
//            mav.addObject("chunjian2",chunjian2);
//        }

        mav.addObject("cxList",cxList);
        mav.addObject("sdList",sdList);
        mav.addObject("sixList",sixList);
        mav.addObject("tzList",tzList);
        mav.addObject("partition",partition);
        mav.addObject("mapList",mapList);

        return mav;
    }

    @ResponseBody
    @RequestMapping("/updatesSource")
    public Map<String,List<DcPlanInfo>> updateSource(DcPlanInfo dcPlanInfo) {

        sdService.updateSource(dcPlanInfo);
        Map<String,List<DcPlanInfo>> mapList = sdService.selectDcPath();

        return mapList;
    }

    @ResponseBody
    @RequestMapping("/updatesDestination")
    public Map<String,List<DcPlanInfo>> updateDestination(DcPlanInfo dcPlanInfo) {

        sdService.updateDestination(dcPlanInfo);
        Map<String,List<DcPlanInfo>> mapList = sdService.selectDcPath();

        return mapList;
    }
}
