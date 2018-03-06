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
            List<DcPlanInfo> list = repository.sfbz(entry.getDcNumber(),entry.getDcSource());
            for (int i = 0; i<list.size(); i++) {
                listAll.add(dataProcess.calSub(list.get(i),i,list.size()));
            }
        }

        List<DcPlanInfo> list4tcNumber = repository.sftcNumberList();
        for (DcPlanInfo entry : list4tcNumber) {
            List<DcPlanInfo> list = repository.sftc(entry.getDcNumber(),entry.getDcSource());
            for (int i = 0; i<list.size(); i++) {
                listAll.add(dataProcess.calSub(list.get(i),i,list.size()));
            }
        }
        if (repository.sfzmList()!= null){
            listAll.addAll(dataProcess.fczmDataList(repository.sfzmList()));
        }
        if (repository.sfzcList()!= null) {
            listAll.addAll(dataProcess.fczcDataList(repository.sfzcList()));
        }if (repository.sfqcList()!= null) {
            listAll.addAll(repository.sfqcList());
        }if(repository.sfscList()!=null){
            listAll.addAll(repository.sfscList());
        }
        if (repository.fcMsjList()!= null) {
            listAll.addAll(dataProcess.fcMsjDataList(repository.fcMsjList()));
        }
        if (repository.fcDcList()!= null) {
            listAll.addAll(dataProcess.fcDcDataList(repository.fcDcList()));
        }
        if (repository.fcYhList()!= null) {
            listAll.addAll(dataProcess.yhDataList(repository.fcYhList()));
        }
        if (repository.fcZwqList()!= null) {
            listAll.addAll(dataProcess.fcZwqDataList(repository.fcZwqList()));
        }

        boolean dataWithAdd = dcRepository.insertDcData(listAll);

        return dataWithAdd;
    }
}
