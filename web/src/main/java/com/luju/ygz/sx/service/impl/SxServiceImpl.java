package com.luju.ygz.sx.service.impl;

import com.luju.pojo.DcPlanInfo;
import com.luju.ygz.sx.repository.SxRepositoryI;
import com.luju.ygz.sx.service.SxServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SxServiceImpl implements SxServiceI {
    @Autowired
    private SxRepositoryI sxRepository;
    @Override
    public List<DcPlanInfo> selectAllList() {
        return sxRepository.select4SdList();
    }
}
