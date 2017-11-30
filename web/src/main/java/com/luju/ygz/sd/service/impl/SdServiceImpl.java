package com.luju.ygz.sd.service.impl;


import com.luju.pojo.DcPlanInfo;
import com.luju.ygz.sd.repository.SdRepositoryI;
import com.luju.ygz.sd.service.SdServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
