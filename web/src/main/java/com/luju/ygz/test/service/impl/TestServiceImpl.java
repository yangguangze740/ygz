package com.luju.ygz.test.service.impl;

import com.luju.pojo.JcPlanInfo;
import com.luju.ygz.test.repository.TestRepositoryI;
import com.luju.ygz.test.service.TestServiceI;
import luju.common.util.DataProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestServiceI {

    @Autowired
    private TestRepositoryI testRepository;

    @Override
    public void selectPlanDataFromOra(DataProcess dataProcess) {
        testRepository.deletePlanCopy();
        List<JcPlanInfo> list = testRepository.selectJcPlanFromOra();
        list = dataProcess.jcTimeList(list);

        testRepository.insertJcData(list);
    }
}
