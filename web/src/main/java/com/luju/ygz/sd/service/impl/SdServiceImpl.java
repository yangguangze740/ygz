package com.luju.ygz.sd.service.impl;


import com.luju.pojo.DcPlanInfo;
import com.luju.ygz.sd.repository.SdRepositoryI;
import com.luju.ygz.sd.service.SdServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SdServiceImpl implements SdServiceI {
    @Autowired
    private SdRepositoryI sdRepository;

    @Override
    public List<DcPlanInfo> selectSdList() {
        return sdRepository.select4SdList();
    }
}
