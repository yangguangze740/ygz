package com.luju.ygz.test.service.impl;

import com.luju.pojo.JcPlanInfo;
import com.luju.ygz.test.service.TestService;
import com.luju.ygz.test.repository.impl.TestRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestRepositoryImpl testRepository;

    @Override
    public List<JcPlanInfo> selectPlanDataFromOra() {
        return testRepository.selectJcPlan();
    }

    @Override
    public List<JcPlanInfo> selectZWDataFromOra() {
        return testRepository.selectJcZW();
    }

    @Override
    public boolean insertDataToMysql(JcPlanInfo info) {
        return (testRepository.insertToPlanCopy(info)||testRepository.insertToZWCopy(info));
    }
}
