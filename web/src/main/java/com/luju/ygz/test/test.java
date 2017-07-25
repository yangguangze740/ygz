package com.luju.ygz.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/luju")
public class test {
    @RequestMapping("/test")
    public ModelAndView Test(){
        ModelAndView mav  = new ModelAndView("/luju/test");

        return mav;
    }
}
