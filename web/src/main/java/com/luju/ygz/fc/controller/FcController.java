package com.luju.ygz.fc.controller;

import com.luju.ygz.fc.service.FcServiceI;
import luju.common.util.DataProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fc")
public class FcController {

    @Autowired
    private FcServiceI service;

    @RequestMapping("/test")
    public String test() {
        DataProcess dataProcess = new DataProcess();
        service.processBFData(dataProcess);

        return null;
    }
}
