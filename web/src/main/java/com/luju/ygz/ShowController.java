package com.luju.ygz;

import com.luju.pojo.*;
import com.luju.ygz.dc.service.DcServiceI;
import com.luju.ygz.test.service.TestServiceI;
import luju.common.util.ConstantFields;
import luju.common.util.DataProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/luju")
public class ShowController {

    @Autowired
    private TestServiceI service;

    @Autowired
    private DcServiceI dcService;

    List<ResultInfo> pathListJc = null;

    DataProcess dataProcess = new DataProcess();

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
            return "redirect:/luju/zyPlan.action";
        } else {
            return "redirect:/luju/route.action";
        }
    }

    @RequestMapping("/zyPlan")
    public ModelAndView allJcData() {
        ModelAndView mav = new ModelAndView("luju/zyPlan");

        List<DcPlanInfo> allList = dcService.selectDcData();
        List<DcPlanInfo> JFList = dcService.selectJF();
        List<DcPlanInfo> CXList = dcService.selectCX();
        Map<String,List<DcPlanInfo>> mapList = dcService.selectDcPath();
        List<DcPlanInfo> partitionList = dcService.select4Partition();

        mav.addObject("allList",allList);
        mav.addObject("mapList",mapList);
        mav.addObject("JFList",JFList);
        mav.addObject("CXList",CXList);
        mav.addObject("partitionList",partitionList);
        System.out.println("web reFlash");

        return mav;
    }

    @RequestMapping("/jcPlan")
    public ModelAndView allJcDataNew() {
        ModelAndView mav = new ModelAndView("luju/jcPlan");

        List<DcPlanInfo> allList = dcService.selectDcData();
        List<DcPlanInfo> JFList = dcService.selectJF();
        List<DcPlanInfo> CXList = dcService.selectCX();
        Map<String,List<DcPlanInfo>> mapList = dcService.selectDcPath();
        List<DcPlanInfo> partitionList = dcService.select4Partition();

        mav.addObject("allList",allList);
        mav.addObject("mapList",mapList);
        mav.addObject("JFCXList",JFList);
        mav.addObject("CXList",CXList);
        mav.addObject("partitionList",partitionList);
        System.out.println("web reFlash");

        return mav;
    }

    @ResponseBody
    @RequestMapping("/updateSource")
    public Map<String,List<DcPlanInfo>> updateSource(DcPlanInfo dcPlanInfo) {

        dcService.updateSource(dcPlanInfo);
        Map<String,List<DcPlanInfo>> mapList = dcService.selectDcPath();

        return mapList;
    }

    @ResponseBody
    @RequestMapping("/updateDestination")
    public Map<String,List<DcPlanInfo>> updateDestination(DcPlanInfo dcPlanInfo) {

        dcService.updateDestination(dcPlanInfo);
        Map<String,List<DcPlanInfo>> mapList = dcService.selectDcPath();

        return mapList;
    }

    @RequestMapping(value = "/textarea", method = RequestMethod.POST)
    public String textarea(HttpServletRequest request) {
        String areaValue = request.getParameter("areaValue");
        int count = dcService.insertTextarea(areaValue);
        if (count==0) {
           System.out.println("提交出错");
        } else {
            System.out.println("提交成功");
        }
        return "redirect:/luju/zyPlan.action";
    }

    @RequestMapping(value = "/selectTextarea", method = RequestMethod.POST)
    public List<TextareaInfo> selectTextarea(TextareaInfo info) {
        List<TextareaInfo> list = dcService.selectTextarea(info);
        return list;
    }

    @RequestMapping(value = "/statistics",method = RequestMethod.GET)
    public ModelAndView toStatistics() {
        ModelAndView mav = new ModelAndView("luju/statistics");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String time = df.format(new Date());
        List<StatisticsInfo> listNow = dcService.selectStatisticsInfoWithTime(time);
        mav.addObject("listNow",listNow);

        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "/time",method = RequestMethod.POST)
    public List<StatisticsInfo> list4Time(String dateWithChange) {

        List<StatisticsInfo> list4Time = dcService.selectStatisticsInfoWithTime(dateWithChange);
        return list4Time;
    }




    @RequestMapping("/bwjPath")
    public String selectBwjPath(JcPlanInfo jcPlanInfo) {

        List<ResultInfo> list4BwjPath = service.selectBwPlanInPath(jcPlanInfo);
        pathListJc.addAll(list4BwjPath);

        return null;
    }

    @RequestMapping("/jtPath")
    public String selectJtPath(DcPlanInfo dcPlanInfo) {

        List<ResultInfo> list4JtP1 = dcService.selectJtDataInPath1(dcPlanInfo);
        pathListJc.addAll(list4JtP1);

        return null;
    }

    @RequestMapping("/zmPath")
    public String selectZmPath(DcPlanInfo dcPlanInfo) {

        List<ResultInfo> list4ZmP1 = dcService.selectZmDataInPath1(dcPlanInfo);
        pathListJc.addAll(list4ZmP1);

        return null;
    }

    @RequestMapping("/tcPath")
    public ModelAndView selectTcPath(DcPlanInfo dcPlanInfo) {

        List<ResultInfo> list4TcP1 = dcService.selectTcDataInPath1(dcPlanInfo);
        pathListJc.addAll(list4TcP1);

        return null;
    }
}
