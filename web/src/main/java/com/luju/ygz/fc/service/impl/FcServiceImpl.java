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
    public boolean processSFData(DataProcess dataProcess) {

        List<DcPlanInfo> listAll = new ArrayList<>();
        List<DcPlanInfo> list4bz = repository.sfbzList();
        List<DcPlanInfo> list4tc = repository.sftcList();
        for (DcPlanInfo entry : list4bz) {
            int swh = entry.getDcSWH();
            for (int i = 1; i<=swh; i++) {
                listAll.addAll(dataProcess.calValue(list4bz,swh,i));
            }
        }
        for (DcPlanInfo entry : list4tc) {
            int swh = entry.getDcSWH();
            for (int i = 1; i<=swh; i++) {
                listAll.addAll(dataProcess.calValue(list4tc,swh,i));
            }
        }
        listAll.addAll(repository.sfzmList());
        listAll.addAll(repository.sfzcList());

        boolean dataWithAdd = dcRepository.insertDcData(listAll);

        return dataWithAdd;
    }


}
