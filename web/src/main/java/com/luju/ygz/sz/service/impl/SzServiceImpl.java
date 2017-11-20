package com.luju.ygz.sz.service.impl;

import com.luju.pojo.DcPlanInfo;
import com.luju.ygz.sz.repository.SzRepositoryI;
import com.luju.ygz.sz.service.SzServiceI;
import luju.common.util.ConstantFields;
import luju.common.util.DataProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SzServiceImpl implements SzServiceI {
    @Autowired
    private SzRepositoryI szRepository;

    @Override
    public List<DcPlanInfo> selectAllSzData() {
        List<DcPlanInfo> list = szRepository.select4AllSzData();
        DataProcess dataProcess = new DataProcess();

        if(list.size() != 0 ){
            return dataProcess.xzTimeList(list);

        }

        return null;
    }

    @Override
    public List<DcPlanInfo> selectJlData() {
        return szRepository.select4JlData();
    }

    @Override
    public List<DcPlanInfo> selectCxData() {
        return szRepository.select4CxData();
    }

    @Override
    public List<DcPlanInfo> selectJlsData() {
        List<DcPlanInfo> list = szRepository.select4AllJlData();
        DcPlanInfo time = new DcPlanInfo();

        for(DcPlanInfo lists : list){
            time.setDcStartTime(lists.getDcStartTime());
            time.setDcEndTime(lists.getDcEndTime());
        }


        return szRepository.select4JlsData(time);
    }

    @Override
    public List<DcPlanInfo> selectFiveData() {
        return szRepository.selec4FiveData();
    }

    @Override
    public List<DcPlanInfo> selectXbData() {
        return szRepository.select4XbData();
    }

    @Override
    public List<DcPlanInfo> selectZwData() {
        List<DcPlanInfo> zwList  = szRepository.select4ZwData();
        List<DcPlanInfo> yhList = szRepository.select4YhData();
        List<DcPlanInfo> dcList = szRepository.select4DcData();

        for(DcPlanInfo yhLists : yhList){
            yhLists.getDcStartTime();

            for(DcPlanInfo dcLists : dcList){
                dcLists.getDcStartTime();

                long dcTime = (long)dcLists.getDcStartTime().getTime();
                long yhTime = (long)yhLists.getDcStartTime().getTime();

                long time1 = dcTime - yhTime;

                if(Math.abs(time1) < ConstantFields.TIME){
                      return szRepository.select4Time1Data();
                }
            }

            for(DcPlanInfo zwLists : zwList){
                zwLists.getDcStartTime();

                long zwTime = (long)yhLists.getDcStartTime().getTime();
                long yhTime = (long)zwLists.getDcStartTime().getTime();

                long time2 = zwTime - yhTime;
                if(Math.abs(time2) < ConstantFields.TIME){
                    return szRepository.select4Time2Data();

                }
            }
        }

        return null;
    }

    @Override
    public List<DcPlanInfo> selectJcData() {
        return szRepository.select4JcData();
    }
}
