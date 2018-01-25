package com.luju.ygz.sf.service.impl;

import com.luju.pojo.DcPlanInfo;
import com.luju.ygz.sf.repository.SfRepositoryI;
import com.luju.ygz.sf.service.SfServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SfServiceImpl implements SfServiceI {

    @Autowired
    private SfRepositoryI repository;

    @Override
    public List<DcPlanInfo> sfList() {
        return repository.select4SfList();
    }
}
