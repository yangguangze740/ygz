package com.luju.ygz.test.service.impl;

import com.luju.pojo.DcPlanInfo;
import com.luju.pojo.JcPlanInfo;
import com.luju.ygz.dc.repository.DcRepositoryI;
import com.luju.ygz.test.repository.TestRepositoryI;
import com.luju.ygz.test.service.TestServiceI;
import luju.common.util.DataProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestServiceImpl implements TestServiceI {

    @Autowired
    private TestRepositoryI testRepository;

    @Autowired
    private DcRepositoryI dcRepository;

    @Autowired
    private TestRepositoryI jcRepository;

    @Override
    public void selectPlanDataFromOra(DataProcess dataProcess) {
        testRepository.deletePlanCopy();
        List<JcPlanInfo> list = testRepository.selectJcPlanFromOra();
        list = dataProcess.jcTimeList(list);

        testRepository.insertJcData(list);
    }

    @Override
    public List<DcPlanInfo> processSdData(DataProcess dataProcess) {
        List<DcPlanInfo> list = new ArrayList<>();
        List<DcPlanInfo> bwjList = new ArrayList<>();

        list.addAll(dataProcess.jcZwqList(jcRepository.selectJcPlanNew()));
        list.addAll(dataProcess.jcDcList(jcRepository.selectJcPlanNew()));
        list.addAll(dataProcess.yhDataList(jcRepository.selectJcPlanNew()));
        list.addAll(dataProcess.jtQcDataList1(dcRepository.selectJtPlan()));
        list.addAll(dataProcess.jtQcDataList2(dcRepository.selectJtPlan()));
        list.addAll(dataProcess.zmT2DataList1(dcRepository.selectZmPlan()));
        list.addAll(dataProcess.zmT2DataList2(dcRepository.selectZmPlan()));
        list.addAll(dataProcess.zcTimeList(dcRepository.selectZcPlan()));

        boolean dataWithOutBwj = dcRepository.insertDcData(list);

        bwjList.addAll(dataProcess.bwjDataList(jcRepository.selectBwjPlanNew()));

        boolean dataWithBwj = dcRepository.insertDcData(bwjList);

        return list;
    }
}
