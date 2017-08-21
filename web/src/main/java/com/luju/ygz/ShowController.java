package com.luju.ygz;

import com.luju.pojo.DcPlanInfo;
import com.luju.pojo.JcPlanInfo;
import com.luju.pojo.ResultInfo;
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

        dcService.deleteShowData();
        dcService.deleteTcData();
        dcService.deleteTcDataSix();

        dcService.processTcDataNew(dataProcess);
        dcService.processDcData(dataProcess);

        List<DcPlanInfo> allList = dcService.selectDcData();
        Map<DcPlanInfo,List<DcPlanInfo>> mapList = dcService.selectDcPath();

        mav.addObject("allList",allList);
        mav.addObject("mapList",mapList);
        System.out.println("web reFlash");

        return mav;
    }

    @ResponseBody
    @RequestMapping("/updateSource")
    public Map<DcPlanInfo,List<DcPlanInfo>> updateSource(DcPlanInfo dcPlanInfo) {

        dcService.updateSource(dcPlanInfo);
        Map<DcPlanInfo,List<DcPlanInfo>> mapList = dcService.selectDcPath();

        return mapList;
    }

    @ResponseBody
    @RequestMapping("/updateDestination")
    public Map<DcPlanInfo,List<DcPlanInfo>> updateDestination(DcPlanInfo dcPlanInfo) {

        dcService.updateDestination(dcPlanInfo);
        Map<DcPlanInfo,List<DcPlanInfo>> mapList = dcService.selectDcPath();

        return mapList;
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
