package com.luju.ygz.dc.service.impl;

import com.luju.pojo.DcPlanInfo;
import com.luju.ygz.dc.repository.DcRepositoryI;
import com.luju.ygz.dc.service.DcServiceI;
import luju.common.util.ComparatorSet;
import luju.common.util.DataProcess;
import luju.common.util.ListToSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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
    public void selectZmPlanData(DataProcess dataProcess) {
        List<DcPlanInfo> list = dcRepository.selectZmPlan();
        list = dataProcess.zmTimeList(list);
        for (int k =0; k < list.size(); k++) {
            dcRepository.insertZmPlan4XT1(list.get(k));
            dcRepository.insertZmPlan4XT2(list.get(k));
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
    public void processTcPlanData(DataProcess dataProcess) {
        List<DcPlanInfo> list = dcRepository.processTcPlan();
        list = dataProcess.tcDataList(list);
        for (int k =0; k < list.size(); k++) {
            dcRepository.insertTcPlan4Six(list.get(k));
        }
    }

    @Override
    public void selectZcPlanData(DataProcess dataProcess) {
        List<DcPlanInfo> list = dcRepository.selectZcPlan();
        list = dataProcess.zcTimeList(list);
        for (int k =0; k < list.size(); k++) {
            dcRepository.insertZcPlan(list.get(k));
        }
    }


    @Override
    public Set<DcPlanInfo> processAllData(ListToSet comparatorSet) {
        List<DcPlanInfo> list4jt1 = dcRepository.selectJtData4XD1();
        List<DcPlanInfo> list4jt2 = dcRepository.selectJtData4XD2();
        List<DcPlanInfo> list4zm1 = dcRepository.selectZmData4XD1();
        List<DcPlanInfo> list4zm2 = dcRepository.selectZmData4XD2();
        List<DcPlanInfo> list4zc = dcRepository.selectZcData();
        List<DcPlanInfo> list4tc = dcRepository.selectTcData();
        Set<DcPlanInfo> set = comparatorSet.comparatorSet(list4jt1,list4jt2,list4zm1,list4zm2,list4zc,list4tc);

        return set;
    }

    @Override
    public List<DcPlanInfo> selectJtDataInPath1() {
        return dcRepository.selectJtDataInPath1();
    }

    @Override
    public List<DcPlanInfo> selectZmDataInPath1() {
        return dcRepository.selectZmDataInPath1();
    }
}
