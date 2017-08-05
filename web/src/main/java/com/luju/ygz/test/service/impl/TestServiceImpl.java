package com.luju.ygz.test.service.impl;

import com.luju.pojo.JcPlanInfo;
import com.luju.ygz.test.repository.TestRepositoryI;
import com.luju.ygz.test.service.TestServiceI;
import luju.common.util.ConstantFields;
import luju.common.util.DataProcess;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class TestServiceImpl implements TestServiceI {

    @Autowired
    private TestRepositoryI testRepository;

    @Override
    public void selectPlanDataFromOra() {
        testRepository.deletePlanCopy();
        List<JcPlanInfo> list = testRepository.selectJcPlanFromOra();
        DataProcess timeProcess = new DataProcess();
        list = timeProcess.jcTimeList(list);

        for (int k = 0; k< list.size(); k++){
            testRepository.insertToPlanCopy(list.get(k));
        }
    }

    @Override
    public void selectPlanData4One() {
        List<JcPlanInfo> list = testRepository.selectJcPlan4One();
        DataProcess dataProcess = new DataProcess();
        list = dataProcess.jcXDList(list);
        for (int k = 0; k < list.size();k++){
            testRepository.insertToPlan4One(list.get(k));
        }
    }

    @Override
    public void selectPlanData4HCJSL() {
        List<JcPlanInfo> list1 = testRepository.selectJcPlan4HC();
        for (int k = 0; k < list1.size(); k++) {
            testRepository.insertToPlan4HC(list1.get(k));
        }

        List<JcPlanInfo> list2 = testRepository.selectJcPlan4JF();
        for (int m = 0; m < list2.size(); m++) {
            testRepository.insertToPlan4JF(list2.get(m));
        }

        List<JcPlanInfo> list3 = testRepository.selectJcPlan4CX();
        for (int n = 0; n < list3.size(); n++) {
            testRepository.insertToPlan4CX(list3.get(n));
        }

        List<JcPlanInfo> list4 = testRepository.selectJcPlan4CC();
        for (int q = 0; q < list4.size(); q++) {
            testRepository.insertToPlan4CC(list4.get(q));
        }
    }

    @Override
    public List<JcPlanInfo> selectPlan4XD() {
        return testRepository.selectJcPlan4XD();
    }

    @Override
    public List<JcPlanInfo> selectPath() {
        return testRepository.selectJcPath();
    }

    @Override
    public void selectBwjData() {
        List<JcPlanInfo> list = testRepository.selectBwjData();
        DataProcess timeProcess = new DataProcess();
        list = timeProcess.bwjTimeList(list);
        for (int k = 0; k < list.size(); k++){
            testRepository.insertToBwjPlan4S(list.get(k));
            testRepository.insertToBwjPlan4N(list.get(k));
        }
    }

    @Override
    public List<JcPlanInfo> selectBwPlanAll() {
        return testRepository.selectBwPlan();
    }
}
