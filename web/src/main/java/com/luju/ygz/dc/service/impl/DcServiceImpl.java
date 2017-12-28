package com.luju.ygz.dc.service.impl;

import com.luju.pojo.*;
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
        List<String> gjhIdList1 = new ArrayList<>();
        List<String> gjhIdList2 = new ArrayList<>();
        list = dataProcess.dcTimeList(list);

        List<DcPlanInfo> jlsj = dcRepository.selectCopyData();

        for (DcPlanInfo entry1 :list) {
            int i = 0;

            if (jlsj.size() != 0) {

                for (DcPlanInfo entry2 :jlsj) {
                    if ( (!(entry1.getJLSJ().equals(entry2.getJLSJ()))) && (entry1.getDcGJHID().equals(entry2.getDcGJHID()))) {
                        // delete and insert
//                        System.out.println(entry1.getDcGJHID());
                        dcRepository.deleteRepeatDataFromCopy(entry1.getDcGJHID());
                        listEquals.add(entry1);
//                        gjhIdList1.add(entry1.getDcGJHID());
                        break;
                    }
                    if ( entry1.getDcGJHID().equals(entry2.getDcGJHID()) && entry1.getJLSJ().equals(entry2.getJLSJ()) ) {
                        break;
                    }
                    if ( (!(entry1.getJLSJ().equals(entry2.getJLSJ()))) && (!(entry1.getDcGJHID().equals(entry2.getDcGJHID()))) ){
                        //insert
                        i++;
                    }
                }
            } else {
                dcRepository.insertToPlanCopy(entry1);
            }

            if (i == jlsj.size() && jlsj.size()!=0) {
                listEquals.add(entry1);
//                gjhIdList2.add(entry1.getDcGJHID());
            }
        }

        if (listEquals.size()!= 0) {
            for (DcPlanInfo equals: listEquals){
                dcRepository.insertToPlanCopy(equals);
            }
        }
    }

    @Override
    public void selectPlanDataFromOraWithDelete(DataProcess dataProcess) {
        dcRepository.deleteDcCopy();
        List<DcPlanInfo> list = dcRepository.selectDcPlanFromOra();
        list = dataProcess.dcTimeList(list);
        for (DcPlanInfo entry : list) {
            dcRepository.insertToPlanCopy(entry);
        }
    }

    @Override
    public void processTcDataNew(DataProcess dataProcess) {
        List<DcPlanInfo> list = dcRepository.selectTcPlan();
        list = dataProcess.tcTimeList(list);
        dcRepository.insertTcPlanNewAll(list);

        list = dcRepository.processTcDataNew();

        list = dataProcess.tcDataList(list);
        dcRepository.insertTcDataNewAll(list);
    }

    @Override
    public boolean processDcData(DataProcess dataProcess) {

        List<DcPlanInfo> list = new ArrayList<>();
        List<DcPlanInfo> list4Bwj = new ArrayList<>();

        list.addAll(dataProcess.jtDataList1(dcRepository.selectXxJtPlan()));
        list.addAll(dataProcess.jtDataList2(dcRepository.selectXxJtPlan()));
        list.addAll(dataProcess.zmDataList1(dcRepository.selectXxZmPlan()));
        list.addAll(dataProcess.zmDataList2(dcRepository.selectXxZmPlan()));
        list.addAll(dataProcess.zcTimeList(dcRepository.selectXxZcPlan()));
        list.addAll(dcRepository.selectTcPlanNew());
        list.addAll(dataProcess.jcDataList(jcRepository.selectJcPlanNew()));

        boolean dataWithoutBwj = dcRepository.insertDcData(list);

        list4Bwj.addAll(dataProcess.bwjTimeListNew(jcRepository.selectXxBwjPlanNew()));

        boolean dataWithBwj = dcRepository.insertDcData(list4Bwj);

        return (dataWithBwj && dataWithoutBwj);
    }

    @Override
    public boolean processSdData(DataProcess dataProcess) {
        List<DcPlanInfo> list = new ArrayList<>();
        List<DcPlanInfo> bwjList = new ArrayList<>();

        list.addAll(dataProcess.jcZwqList(jcRepository.selectZwqJcPlanNew()));
        list.addAll(dataProcess.jcDcList(jcRepository.selectDcJcPlanNew()));
        list.addAll(dataProcess.yhDataList(jcRepository.selectYhJcPlanNew()));
        list.addAll(dataProcess.jtQcDataList1(dcRepository.selectJtPlan()));
        list.addAll(dataProcess.jtQcDataList2(dcRepository.selectJtPlan()));
        list.addAll(dataProcess.zmT2DataList1(dcRepository.selectZmPlan()));
        list.addAll(dataProcess.zmT2DataList2(dcRepository.selectZmPlan()));
        list.addAll(dataProcess.zcTimeList(dcRepository.selectZcPlan()));

        boolean dataWithOutBwj = dcRepository.insertDcData(list);

        bwjList.addAll(dataProcess.bwjDataList(jcRepository.selectBwjPlanNew()));

        boolean dataWithBwj = dcRepository.insertDcData(bwjList);

        return (dataWithOutBwj && dataWithBwj);

    }


    @Override
    public List<DcPlanInfo> selectDcData() {
        return dcRepository.selectDcData();
    }

    @Override
    public Map<String,List<DcPlanInfo>> selectDcPath() {

        StatisticsInfo info = new StatisticsInfo();
        Map<String,List<DcPlanInfo>> map = new HashMap<>();

        List<String> strings1 = new ArrayList<>();
        List<List<String>> listString1 = new ArrayList<>();
        List<List<String>> listString2 = new ArrayList<>();

        List<DcPlanInfo> list = dcRepository.selectDcData();

//        String array[];

        for (DcPlanInfo bean : list) {
            String uuid = bean.getDcId();

            List<DcPlanInfo> pathList = dcRepository.selectPath(bean);

            if (pathList.size() != 0) {
                for (DcPlanInfo entry : pathList) {
                    strings1.add(uuid);
                    strings1.add(entry.getDcId());

                    listString1.add(strings1);
                    boolean b = listString1.removeAll(listString2);//删除1中 与2 重复的元素

                    if (b == false) {
                        listString2.addAll(listString1);
                        listString1.removeAll(listString2);

                    }
                    String key = " "+bean.getDcId()+" "+bean.getDcNumber()+" "+bean.getDcType();

//                    array = new String[]{bean.getDcId(),entry.getDcId()};

                    map.put(key,pathList);
                }

                for (Map.Entry<String,List<DcPlanInfo>> entry : map.entrySet()) {
                    String key  = entry.getKey();
                    key = key.substring(38);
                    info.setData1(key);
                    for (DcPlanInfo entry1 : entry.getValue()) {
                        info.setData2(entry1.getDcNumber()+" " +entry1.getDcType());
                        List<StatisticsInfo> statisticsInfos = dcRepository.selectStatisticsInfo();
                        if (statisticsInfos.size() != 0) {
                            int index = 0;
                            for (StatisticsInfo entry2 :statisticsInfos) {
                                if (!entry2.getData1().equals(info.getData2()) && !entry2.getData2().equals(info.getData1())
                                        && !entry2.getData1().equals(info.getData1()) && !entry2.getData2().equals(info.getData2())
                                        ){
                                    index++;
                                }
                            }
                            if (index == statisticsInfos.size()) {
                                dcRepository.insertStatisticsInfo(info);
                            }
                        } else {
                            dcRepository.insertStatisticsInfo(info);
                        }
                    }
                }
            }
        }

        return map;
    }

    @Override
    public void processJFCX() {
        List<JcPlanInfo> listJF = jcRepository.selectJcPlan4JF();
        List<JcPlanInfo> listCX = jcRepository.selectJcPlan4CX();
        for (JcPlanInfo jf : listJF) {
            jcRepository.insertToPlan4JF(jf);
        }
        for (JcPlanInfo cx : listCX) {
            jcRepository.insertToPlan4CX(cx);
        }
    }

    @Override
    public List<DcPlanInfo> selectJF() {
        return dcRepository.selectJF();
    }

    @Override
    public List<DcPlanInfo> selectCX() {
        return dcRepository.selectCX();
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

    @Override
    public int deleteTcData() {
        return dcRepository.deleteTcData();
    }

    @Override
    public int deleteTcDataSix() {
        return dcRepository.deleteTcDataSix();
    }

    @Override
    public int deleteJFCX() {
        return dcRepository.deleteTFCX();
    }

    @Override
    public int insertTextarea(String s) {
        return dcRepository.insertTextarea(s);
    }

    @Override
    public List<TextareaInfo> selectTextarea(TextareaInfo info) {
        return dcRepository.selectTextareaInfo(info);
    }

    @Override
    public int updateTextareaIsSelected(int isS) {
        return dcRepository.updateTextareaInfoIsSelected(isS);
    }

    @Override
    public List<StatisticsInfo> selectStatisticsInfoWithTime(String time) {
        return dcRepository.selectStatisticsInfoWithTime(time);
    }

    @Override
    public List<DcPlanInfo> select4Partition() {

        List<DcPlanInfo> list4Return = new ArrayList<>();
        DcPlanInfo dcPlanInfo = new DcPlanInfo();
        String dcDH = null;
        String partitionId = null;
        String partitionNumber = null;
        int partition = 0;
        List<DcPlanInfo> list4Partition = dcRepository.select4Partition();
        for (DcPlanInfo entry: list4Partition) {

            if (dcDH == null) {
                dcDH = entry.getDcDH();
                if (entry.getDcDH().equals("02") || entry.getDcDH().equals("03") || entry.getDcDH().equals("04")) {
                    partition = 1;
                    partitionId = entry.getDcId();
                    partitionNumber = entry.getDcNumber();
                }
                if (entry.getDcDH().equals("05") || entry.getDcDH().equals("06") || entry.getDcDH().equals("07")) {
                    partition = 2;
                    partitionId = entry.getDcId();
                    partitionNumber = entry.getDcNumber();
                }
            }

            else {
                switch (partition){
                    case 1 :
                        if (entry.getDcDH().equals("02") || entry.getDcDH().equals("03") || entry.getDcDH().equals("04")) {
                            dcPlanInfo.setDcId(partitionId);
                            dcPlanInfo.setPartition(partitionNumber + " " +entry.getDcNumber());
                            list4Return.add(dcPlanInfo);
                        } else {
                            partition = 2;
                        }
                        dcDH = entry.getDcDH();
                        partitionId = entry.getDcId();
                        partitionNumber = entry.getDcNumber();
                        break;
                    case 2 :
                        if (entry.getDcDH().equals("05") || entry.getDcDH().equals("06") || entry.getDcDH().equals("07")) {
                            dcPlanInfo.setDcId(partitionId);
                            dcPlanInfo.setPartition(partitionNumber + " " +entry.getDcNumber());
                            list4Return.add(dcPlanInfo);
                        } else {
                            partition = 1;
                        }
                        dcDH = entry.getDcDH();
                        partitionId = entry.getDcId();
                        partitionNumber = entry.getDcNumber();
                        break;
                }
            }
        }
        return list4Return;
    }

}
