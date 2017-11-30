package com.luju.ygz.sd.service.impl;


import com.luju.pojo.DcPlanInfo;
import com.luju.pojo.StatisticsInfo;
import com.luju.ygz.sd.repository.SdRepositoryI;
import com.luju.ygz.sd.service.SdServiceI;
import luju.common.util.ConstantFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SdServiceImpl implements SdServiceI {
    @Autowired
    private SdRepositoryI sdRepository;

    @Override
    public List<DcPlanInfo> selectSdList() {
        return sdRepository.select4SdList();
    }

    @Override
    public List<DcPlanInfo> selectCxList() {
        return sdRepository.select4CxList();
    }

    @Override
    public List<DcPlanInfo> selectSixList() {
        return sdRepository.select4SixList();
    }

    @Override
    public List<DcPlanInfo> selectTzList() {
        return sdRepository.select4TzList();
    }

    @Override
    public List<DcPlanInfo> selectPartitionList() {
        List<DcPlanInfo> list4Return = new ArrayList<>();
        DcPlanInfo dcPlanInfo = new DcPlanInfo();
        String dcDH = null;
        String partitionId = null;
        String partitionNumber = null;
        int partition = 0;
        List<DcPlanInfo> list4Partition = sdRepository.select4Partition();
        for (DcPlanInfo entry: list4Partition) {

            if (dcDH == null) {
                dcDH = entry.getDcDH();
                if (entry.getDcDH().equals("02") || entry.getDcDH().equals("03") || entry.getDcDH().equals("04") || entry.getDcDH().equals("05")) {
                    partition = 1;
                    partitionId = entry.getDcId();
                    partitionNumber = entry.getDcNumber();
                }
                if (entry.getDcDH().equals("06") || entry.getDcDH().equals("07") || entry.getDcDH().equals("08") || entry.getDcDH().equals("09") || entry.getDcDH().equals("10")) {
                    partition = 2;
                    partitionId = entry.getDcId();
                    partitionNumber = entry.getDcNumber();
                }
            }

            else {
                switch (partition){
                    case 1 :
                        if (entry.getDcDH().equals("02") || entry.getDcDH().equals("03") || entry.getDcDH().equals("04") || entry.getDcDH().equals("05")) {
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
                        if (entry.getDcDH().equals("06") || entry.getDcDH().equals("07") || entry.getDcDH().equals("08")|| entry.getDcDH().equals("09")|| entry.getDcDH().equals("10")) {
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

    @Override
    public Map<String, List<DcPlanInfo>> selectDcPath() {
        StatisticsInfo info = new StatisticsInfo();
        Map<String,List<DcPlanInfo>> map = new HashMap<>();

        List<String> strings1 = new ArrayList<>();
        List<List<String>> listString1 = new ArrayList<>();
        List<List<String>> listString2 = new ArrayList<>();

        List<DcPlanInfo> list = sdRepository.selectDcData();

        for (DcPlanInfo bean : list) {
            String uuid = bean.getDcId();

            List<DcPlanInfo> pathList = sdRepository.selectPath(bean);

            if (pathList.size() != 0) {
                for (DcPlanInfo entry : pathList) {
                    strings1.add(uuid);
                    strings1.add(entry.getDcId());

                    listString1.add(strings1);
                    boolean b = listString1.removeAll(listString2);

                    if (b == false) {
                        listString2.addAll(listString1);
                        listString1.removeAll(listString2);

                    }
                    String key = " "+bean.getDcId()+" "+bean.getDcNumber()+" "+bean.getDcType();
                    map.put(key,pathList);
                }
                for (Map.Entry<String,List<DcPlanInfo>> entry : map.entrySet()) {
                    String key  = entry.getKey();
                    key = key.substring(38);
                    info.setData1(key);
                    for (DcPlanInfo entry1 : entry.getValue()) {
                        info.setData2(entry1.getDcNumber()+" " +entry1.getDcType());
                        List<StatisticsInfo> statisticsInfos = sdRepository.selectStatisticsInfo();
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
                                sdRepository.insertStatisticsInfo(info);
                            }
                        } else {
                            sdRepository.insertStatisticsInfo(info);
                        }
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
        if (dcPlanInfo.getDcSource().equals(ConstantFields.TYPE_ZX)) {
            dcPlanInfo.setDcSource(ConstantFields.JDZ);
        }
        if (dcPlanInfo.getDcSource().equals(ConstantFields.TYPE_DX)) {
            dcPlanInfo.setDcSource(ConstantFields.JTD);
        }
        if (dcPlanInfo.getDcSource().equals(ConstantFields.TYPE_YX)) {
            dcPlanInfo.setDcSource(ConstantFields.JTY);
        }

        return sdRepository.updateSource(dcPlanInfo);
    }

    @Override
    public int updateDestination(DcPlanInfo dcPlanInfo) {
        if (dcPlanInfo.getDcDestination().equals(ConstantFields.BWJDS)) {
            dcPlanInfo.setDcDestination(ConstantFields.S);
        }
        if (dcPlanInfo.getDcDestination().equals(ConstantFields.BWJDN)) {
            dcPlanInfo.setDcDestination(ConstantFields.N);
        }
        return sdRepository.updateDestination(dcPlanInfo);
    }
}
