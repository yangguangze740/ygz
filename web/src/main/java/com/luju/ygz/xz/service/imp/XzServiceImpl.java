package com.luju.ygz.xz.service.imp;

import com.luju.pojo.*;
import com.luju.ygz.xz.repository.XzRepositoryI;
import com.luju.ygz.xz.service.XzServiceI;
import luju.common.util.DataProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class XzServiceImpl implements XzServiceI {

    @Autowired
    private XzRepositoryI xzRepository;


    private DataProcess dataProcess;

    @Override
    public List<DcPlanInfo> selectAllXzData() {
        List<DcPlanInfo> list = xzRepository.select4AllXzData();
        DataProcess dataProcess = new DataProcess();

        if(list.size() != 0 ){
            return dataProcess.xzTimeList(list);

        }

        return null;
    }

    @Override
    public List<DcPlanInfo> selectCxData() {
        return xzRepository.select4CxData();
    }

    @Override
    public List<DcPlanInfo> selectxbData() {
        return xzRepository.select4XbData();
    }

    @Override
    public List<DcPlanInfo> selectjlData() {
        List<DcPlanInfo> list = xzRepository.select4JlData();
        DcPlanInfo time = new DcPlanInfo();


        for(DcPlanInfo lists : list){
            time.setDcStartTime( lists.getDcStartTime());
            time.setDcEndTime(lists.getDcEndTime());
        }
        xzRepository.select4JlsData(time);

        return xzRepository.select4JlsData(time);
    }

    @Override
    public List<DcPlanInfo> selectJllData() {
        return xzRepository.select4JllData();
    }

    @Override
    public List<DcPlanInfo> selectJlsData() {
        return xzRepository.select4JllsData();
    }
}
