package luju.ygz.test.controller;

import luju.ygz.test.service.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestServiceImpl service;

    @RequestMapping("/hello")
    public String test() {

        service.testQuery();

        return "hello";
    }
}
