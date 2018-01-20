package com.luju.ygz.xf.service.impl;

import com.luju.pojo.DcPlanInfo;
import com.luju.ygz.xf.repository.XfRepositoryI;
import com.luju.ygz.xf.service.XfServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XfServiceImpl implements XfServiceI{

    @Autowired
    private XfRepositoryI xfRepositoryI;

    @Override
    public List<DcPlanInfo> xfList() {

        return xfRepositoryI.select4XfList();
    }
}
