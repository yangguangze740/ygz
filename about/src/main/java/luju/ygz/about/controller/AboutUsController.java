package luju.ygz.about.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/about")
public class AboutUsController {

    @RequestMapping("/index")
    public String aboutUs() {
        return "about/index";
    }
}
