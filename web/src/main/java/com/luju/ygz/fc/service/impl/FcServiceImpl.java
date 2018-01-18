package com.luju.ygz.fc.service.impl;

import com.luju.pojo.DcPlanInfo;
import com.luju.pojo.FcPlanInfo;
import com.luju.ygz.dc.repository.DcRepositoryI;
import com.luju.ygz.fc.repository.FcRepositoryI;
import com.luju.ygz.fc.service.FcServiceI;
import luju.common.util.DataProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FcServiceImpl implements FcServiceI {

    @Autowired
    private FcRepositoryI repository;

    @Autowired
    private DcRepositoryI dcRepository;

    @Override
    public void selectPlanDataFromOra(DataProcess dataProcess) {
        repository.deletePlanCopy();
        List<FcPlanInfo> list = repository.selectFcPlanFromOra();
        list = dataProcess.fcTimeList(list);

        repository.insertFcData(list);
    }

    @Override
    public boolean processBFData(DataProcess dataProcess) {

        List<DcPlanInfo> listAll = new ArrayList<>();

        List<DcPlanInfo> list4bzNumber = repository.sfbzNumberList();
        for (DcPlanInfo entry : list4bzNumber) {
            List<DcPlanInfo> list = repository.sfbz(entry.getDcNumber());
            for (int i = 0; i<list.size(); i++) {
                listAll.addAll(dataProcess.calValue(list,i,list.size()));
            }
        }

        List<DcPlanInfo> list4tcNumber = repository.sftcNumberList();
        for (DcPlanInfo entry : list4tcNumber) {
            List<DcPlanInfo> list = repository.sftc(entry.getDcSource());
            for (int i = 0; i<list.size(); i++) {
                listAll.addAll(dataProcess.calValue(list,i,list.size()));
            }
        }

        listAll.addAll(repository.sfzmList());
        listAll.addAll(repository.sfzcList());

        boolean dataWithAdd = dcRepository.insertDcData(listAll);

        return dataWithAdd;
    }
}
