package com.luju.ygz;

import com.luju.pojo.DcPlanInfo;
import com.luju.pojo.JcPlanInfo;
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
@RequestMapping("/luju")
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
            return "redirect:/luju/zyPlan.action";
        } else {
            return "redirect:/luju/route.action";
        }
    }

    @RequestMapping("/zyPlan")
    public ModelAndView allJcData(JcPlanInfo info) {
        ModelAndView mav = new ModelAndView("luju/zyPlan");

        Set<JcPlanInfo> jcSet = service.selectAllData();
        Set<DcPlanInfo> dcSet = dcService.processAllData(comparatorSet);
        mav.addObject("dcSet",dcSet);

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
