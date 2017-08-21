package com.luju.ygz.dc.service.impl;

import com.luju.pojo.DcPlanInfo;
import com.luju.pojo.ResultInfo;
import com.luju.ygz.dc.repository.DcRepositoryI;
import com.luju.ygz.dc.service.DcServiceI;
import com.luju.ygz.test.repository.TestRepositoryI;
import luju.common.util.ConstantFields;
import luju.common.util.DataProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DcServiceImpl implements DcServiceI {

    @Autowired
    private DcRepositoryI dcRepository;

    @Autowired
    private TestRepositoryI jcRepository;

    @Override
    public void selectPlanDataFromOra(DataProcess dataProcess) {
        List<DcPlanInfo> listEquals = new ArrayList<>();
        List<DcPlanInfo> list = dcRepository.selectDcPlanFromOra();
        list = dataProcess.dcTimeList(list);

        List<DcPlanInfo> jlsj = dcRepository.selectCopyData();

        for (DcPlanInfo entry1  :list) {
            int i = 0;

            if (jlsj.size() != 0) {

                for (DcPlanInfo entry2 :jlsj) {
                    if ( (!(entry1.getJLSJ().equals(entry2.getJLSJ()))) && (entry1.getDcGJHID().equals(entry2.getDcGJHID()))) {
                        // delete and insert
                        dcRepository.deleteRepeatDataFromCopy(entry1.getDcGJHID());
                        listEquals.add(entry1);
                        break;
                    } else if ( entry1.getDcGJHID().equals(entry2.getDcGJHID()) && entry1.getJLSJ().equals(entry2.getJLSJ()) ) {
                        break;
                    } else {
                        //insert
                        i++;
                    }
                }
            } else {
                dcRepository.insertToPlanCopy(entry1);
            }
            if (i == jlsj.size() && jlsj.size()!=0) {
                listEquals.add(entry1);
            }
        }
        if (listEquals.size()!= 0) {
            for (DcPlanInfo equals: listEquals){
                dcRepository.insertToPlanCopy(equals);
            }
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
    public List<ResultInfo> processJt1Data() {
        return dcRepository.selectJtData4XD1();
    }

    @Override
    public List<ResultInfo> processJt2Data() {
        return dcRepository.selectJtData4XD2();
    }

    @Override
    public List<ResultInfo> processZm1Data() {
        return dcRepository.selectZmData4XD1();
    }

    @Override
    public List<ResultInfo> processZm2Data() {
        return dcRepository.selectZmData4XD2();
    }

    @Override
    public List<ResultInfo> processZcData() {
        return dcRepository.selectZcData();
    }

    @Override
    public List<ResultInfo> processTcData() {
        return dcRepository.selectTcData();
    }

    @Override
    public List<ResultInfo> selectJtDataInPath1(DcPlanInfo dcPlanInfo) {
        return dcRepository.selectJtDataInPath1(dcPlanInfo);
    }

    @Override
    public List<ResultInfo> selectZmDataInPath1(DcPlanInfo dcPlanInfo) {
        return dcRepository.selectZmDataInPath1(dcPlanInfo);
    }

    @Override
    public List<ResultInfo> selectTcDataInPath1(DcPlanInfo dcPlanInfo) {
        return dcRepository.selectTcDataInPath1(dcPlanInfo);
    }

    @Override
    public void processTcDataNew(DataProcess dataProcess) {
        List<DcPlanInfo> list = dcRepository.selectTcPlan();
        list = dataProcess.tcTimeList(list);
        for (int k =0; k < list.size(); k++) {
            dcRepository.insertTcPlanNew(list.get(k));
        }
        list = dcRepository.processTcDataNew();

        list = dataProcess.tcDataList(list);
        for (int k =0; k < list.size(); k++) {
            dcRepository.insertTcDataNew(list.get(k));
        }
    }

    @Override
    public boolean processDcData(DataProcess dataProcess) {

        List<DcPlanInfo> list = new ArrayList<>();

        list.addAll(dataProcess.jtDataList1(dcRepository.selectJtPlan()));
        list.addAll(dataProcess.jtDataList2(dcRepository.selectJtPlan()));
        list.addAll(dataProcess.zmDataList1(dcRepository.selectZmPlan()));
        list.addAll(dataProcess.zmDataList2(dcRepository.selectZmPlan()));
        list.addAll(dataProcess.zcTimeList(dcRepository.selectZcPlan()));
        list.addAll(dcRepository.selectTcPlanNew());
        list.addAll(dataProcess.bwjTimeListNew(jcRepository.selectBwjPlanNew()));
        list.addAll(dataProcess.jcDataList(jcRepository.selectJcPlanNew()));

        boolean b = dcRepository.insertDcData(list);

        return b;
    }

    @Override
    public List<DcPlanInfo> selectDcData() {
        return dcRepository.selectDcData();
    }

    @Override
    public Map<DcPlanInfo,List<DcPlanInfo>> selectDcPath() {

        Map<DcPlanInfo,List<DcPlanInfo>> map = new HashMap<DcPlanInfo,List<DcPlanInfo>>();

        List<String> strings1 = new ArrayList<>();
        List<List<String>> listString1 = new ArrayList<>();
        List<List<String>> listString2 = new ArrayList<>();

        List<DcPlanInfo> list = dcRepository.selectDcData();

        for (int i = 0; i<list.size();i++) {
            String uuid = list.get(i).getDcId();
            List<DcPlanInfo> pathList = dcRepository.selectPath(list.get(i));

            if (pathList.size() != 0) {
                for (int k =0 ;k<pathList.size(); k++) {
                    strings1.add(uuid);
                    strings1.add(pathList.get(k).getDcId());
                    listString1.add(strings1);
                    boolean b = listString1.removeAll(listString2);

                    if (b == false) {
                        listString2.addAll(listString1);
                        listString1.removeAll(listString2);
                        map.put(list.get(i),pathList);
                    }
                }
            }
        }
        return map;
    }

    @Override
    public int updateSource(DcPlanInfo dcPlanInfo) {
        if (dcPlanInfo.getDcSource().equals(ConstantFields.TYPE_QCX)) {
            dcPlanInfo.setDcSource(ConstantFields.QC);
        }
        if (dcPlanInfo.getDcSource().equals(ConstantFields.TYPE_JDX)) {
            dcPlanInfo.setDcSource(ConstantFields.JD);
        }

        return dcRepository.updateSource(dcPlanInfo);
    }

    @Override
    public int updateDestination(DcPlanInfo dcPlanInfo) {
        if (dcPlanInfo.getDcDestination().equals(ConstantFields.BWJDS)) {
            dcPlanInfo.setDcDestination(ConstantFields.S);
        }
        if (dcPlanInfo.getDcDestination().equals(ConstantFields.BWJDN)) {
            dcPlanInfo.setDcDestination(ConstantFields.N);
        }
        return dcRepository.updateDestination(dcPlanInfo);
    }

    @Override
    public int deleteShowData() {
        return dcRepository.deleteDcShow();
    }
}
