package com.luju.ygz;

import com.luju.pojo.DcPlanInfo;
import com.luju.pojo.JcPlanInfo;
import com.luju.pojo.ResultInfo;
import com.luju.ygz.dc.service.DcServiceI;
import com.luju.ygz.test.service.TestServiceI;
import luju.common.util.ConstantFields;
import luju.common.util.DataProcess;
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
@RequestMapping("/luju")
public class ShowController {

    @Autowired
    private TestServiceI service;

    @Autowired
    private DcServiceI dcService;

    List<ResultInfo> pathListJc = null;

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
            return "redirect:/luju/zyPlan.action";
        } else {
            return "redirect:/luju/route.action";
        }
    }

    @RequestMapping("/zyPlan")
    public ModelAndView allJcData(ListToSet listToSet) {
        ModelAndView mav = new ModelAndView("luju/zyPlan");

        DataProcess dataProcess = new DataProcess();

//        List<ResultInfo> pathListJt = null;
//        List<ResultInfo> pathListZm = null;
//        List<ResultInfo> pathListTc = null;
//
//        List<ResultInfo> jcList = service.processJcData(); // path
//        List<ResultInfo> bwjList = service.processBwjData();
//        List<ResultInfo> jt1List = dcService.processJt1Data();
//        List<ResultInfo> jt2List = dcService.processJt2Data();//path
//        List<ResultInfo> zm1List = dcService.processZm1Data();
//        List<ResultInfo> zm2List = dcService.processZm2Data();//path
//        List<ResultInfo> zcList = dcService.processZcData();//path
//        List<ResultInfo> tcList = dcService.processTcData();

//        pathListJc = dataProcess.selectPath(jcList,pathListJc);
//        pathListJt = dataProcess.selectPath(jcList,pathListJt);
//        pathListZm = dataProcess.selectPath(jcList,pathListZm);
//        pathListTc = dataProcess.selectPath(jcList,pathListTc);

//        pathListJc.addAll(pathListJt);
//        pathListJc.addAll(pathListZm);
//        pathListJc.addAll(pathListTc);

        List<DcPlanInfo> allList = dcService.processDcData(dataProcess);

//        Set<ResultInfo> allDataSet = listToSet.comparatorSet(jcList,bwjList,jt1List,jt2List,zm1List,zm2List,zcList,tcList);
        mav.addObject("allList",allList);

        return mav;
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
