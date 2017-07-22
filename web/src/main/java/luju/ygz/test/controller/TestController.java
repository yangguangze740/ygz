package luju.ygz.test.controller;

import com.luju.pojo.JcPlanInfo;
import luju.ygz.test.service.impl.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestServiceImpl service;

    @RequestMapping("/hello")
    public String test(JcPlanInfo info) {

        service.selectPlanDataFromOra();

        List<JcPlanInfo> infos = (List)service.selectPlanDataFromOra();
        for (int i = 0; i < infos.size(); i++) {
            System.out.println(infos.get(i).getNODE_FOUR_WAY());
            service.insertDataToMysql(infos.get(i));
        }
        System.out.println(info.getNODE_FOUR_WAY());


        return "hello";
    }
}
