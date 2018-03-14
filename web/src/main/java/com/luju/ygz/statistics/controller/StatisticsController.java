package com.luju.ygz.statistics.controller;

import com.luju.pojo.DcPlanInfo;
import com.luju.pojo.StatisticsInfo;
import com.luju.ygz.statistics.service.StatisticServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/luju/statistics")
public class StatisticsController {
    @Autowired
    private StatisticServiceI statisticServiceI;

    @RequestMapping("/index")
    public ModelAndView statistic(){
        ModelAndView mav = new ModelAndView("luju/statistic");
        int jlConflict = statisticServiceI.queryJlConflict();
        int better = statisticServiceI.queryBetter();
        int error = statisticServiceI.queryError();
        List<StatisticsInfo> monthConflict = statisticServiceI.queryMonthConflict();
        List<StatisticsInfo> allList = statisticServiceI.queryAllList();

        mav.addObject("jlConflict",jlConflict);
        mav.addObject("better",better);
        mav.addObject("error",error);
        mav.addObject("monthConflict",monthConflict);
        mav.addObject("allList",allList);
        return mav;

    }

    @ResponseBody
    @RequestMapping("/all")
    public Map<String,List<StatisticsInfo>> allConfict(){
        Map<String,List<StatisticsInfo>> map = new HashMap<>();
        List<StatisticsInfo> monthConflict = statisticServiceI.queryMonthConflict();

        map.put("monthConflict",monthConflict);

        return map;
    }

}
