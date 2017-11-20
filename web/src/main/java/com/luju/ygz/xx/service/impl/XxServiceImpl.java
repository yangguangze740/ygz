package com.luju.ygz.xx.service.impl;


import com.luju.pojo.DcPlanInfo;
import com.luju.ygz.xx.repository.XxRepositoryI;
import com.luju.ygz.xx.service.XxServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class XxServiceImpl implements XxServiceI {
    @Autowired
    private XxRepositoryI xxRepositoryI;

    @Override
    public List<DcPlanInfo> selectAllList() {
        return xxRepositoryI.select4AllList();
    }
}
