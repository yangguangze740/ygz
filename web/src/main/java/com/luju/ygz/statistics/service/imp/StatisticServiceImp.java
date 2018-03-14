package com.luju.ygz.statistics.service.imp;

import com.luju.pojo.StatisticsInfo;
import com.luju.ygz.statistics.repository.StaisticRepositoryI;
import com.luju.ygz.statistics.service.StatisticServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticServiceImp implements StatisticServiceI{
    @Autowired
    private StaisticRepositoryI staisticRepositoryI;

    @Override
    public int queryJlConflict() {
        return staisticRepositoryI.queryJlConflict();
    }

    @Override
    public int queryBetter() {
        return staisticRepositoryI.queryBetter();
    }

    @Override
    public int queryError() {
        return staisticRepositoryI.queryError();
    }

    @Override
    public List<StatisticsInfo> queryMonthConflict() {
        return staisticRepositoryI.queryMonthConflict();
    }

    @Override
    public List<StatisticsInfo> queryAllList() {
        return staisticRepositoryI.queryAllList();
    }


}
