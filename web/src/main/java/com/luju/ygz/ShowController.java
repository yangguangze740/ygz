package com.luju.ygz;

import com.luju.pojo.DcPlanInfo;
import com.luju.pojo.JcPlanInfo;
import com.luju.pojo.ResultInfo;
import com.luju.ygz.dc.service.DcServiceI;
import com.luju.ygz.test.service.TestServiceI;
import luju.common.util.ConstantFields;
import luju.common.util.ListToSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/ygz")
public class ShowController {

    @Autowired
    private TestServiceI service;

    @Autowired
    private DcServiceI dcService;

    ListToSet comparatorSet = new ListToSet();

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

    @RequestMapping("/showPlan")
    public ModelAndView showAllData(ListToSet listToSet) {
        ModelAndView mav = new ModelAndView("/luju/jcPlan");

        List<ResultInfo> jcList = service.processJcData();
        List<ResultInfo> bwjList = service.processBwjData();
        List<ResultInfo> jt1List = dcService.processJt1Data();
        List<ResultInfo> jt2List = dcService.processJt2Data();
        List<ResultInfo> zm1List = dcService.processZm1Data();
        List<ResultInfo> zm2List = dcService.processZm2Data();
        List<ResultInfo> zcList = dcService.processZcData();
        List<ResultInfo> tcList = dcService.processTcData();

        Set<ResultInfo> allDataSet = listToSet.comparatorSet(jcList,bwjList,jt1List,jt2List,zm1List,zm2List,zcList,tcList);

        return mav;
    }


    @RequestMapping("/jcPath")
    public ModelAndView selectDcData(JcPlanInfo jcPlanInfo) {
        ModelAndView mav = new ModelAndView();

        List<JcPlanInfo> list4BwjPath = service.selectBwPlanInPath(jcPlanInfo);

        return mav;
    }

    @RequestMapping("/dcPath")
    public ModelAndView selectDcData(DcPlanInfo dcPlanInfo) {
        ModelAndView mav = new ModelAndView();

        List<DcPlanInfo> list4JtP1 = dcService.selectJtDataInPath1(dcPlanInfo);
        List<DcPlanInfo> list4ZmP1 = dcService.selectZmDataInPath1(dcPlanInfo);
        List<DcPlanInfo> list4TcP1 = dcService.selectTcDataInPath1(dcPlanInfo);

        return mav;
    }
}
