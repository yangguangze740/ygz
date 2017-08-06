package com.luju.ygz.dc.service.impl;

import com.luju.pojo.DcPlanInfo;
import com.luju.ygz.dc.repository.DcRepositoryI;
import com.luju.ygz.dc.service.DcServiceI;
import luju.common.util.DataProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DcServiceImpl implements DcServiceI {

    @Autowired
    private DcRepositoryI dcRepository;

    @Override
    public void selectPlanDataFromOra(DataProcess dataProcess) {
        dcRepository.deletePlanCopy();
        List<DcPlanInfo> list = dcRepository.selectDcPlanFromOra();
        list = dataProcess.dcTimeList(list);

        for (int k = 0; k < list.size(); k++){
            dcRepository.insertToPlanCopy(list.get(k));
        }
    }

    @Override
    public void selectJtPlanData(DataProcess dataProcess) {
        List<DcPlanInfo> list = dcRepository.selectJtPlan();
        list = dataProcess.jtTimeList(list);

        for (int k =0; k < list.size(); k++) {
            dcRepository.insertJtPlan4QC(list.get(k));
            dcRepository.insertJtPlan4JD(list.get(k));
        }
    }

    @Override
    public void selectTcPlanData(DataProcess dataProcess) {
        List<DcPlanInfo> list = dcRepository.selectTcPlan();
        list = dataProcess.tcTimeList(list);
        for (int k =0; k < list.size(); k++) {
            dcRepository.insertTcPlan4XT1(list.get(k));
            dcRepository.insertTcPlan4XT2(list.get(k));
        }
    }

    @Override
    public List<DcPlanInfo> selectJtData1() {
        return dcRepository.selectJtPlan4XD1();
    }

    @Override
    public List<DcPlanInfo> selectJtData2() {
        return dcRepository.selectJtPlan4XD2();
    }
}
