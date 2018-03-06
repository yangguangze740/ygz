package com.luju.ygz.xf.service.impl;

import com.luju.pojo.DcPlanInfo;
import com.luju.pojo.StatisticsInfo;
import com.luju.ygz.xf.repository.XfRepositoryI;
import com.luju.ygz.xf.service.XfServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class XfServiceImpl implements XfServiceI{

    @Autowired
    private XfRepositoryI xfRepositoryI;

    @Override
    public List<DcPlanInfo> xfList() {

        return xfRepositoryI.select4XfList();
    }

    @Override
    public Map<String, List<DcPlanInfo>> selectDcPath() {
        StatisticsInfo info = new StatisticsInfo();
        Map<String,List<DcPlanInfo>> map = new HashMap<>();

        List<String> strings1 = new ArrayList<>();
        List<List<String>> listString1 = new ArrayList<>();
        List<List<String>> listString2 = new ArrayList<>();

        List<DcPlanInfo> list = xfRepositoryI.selectDcData();

        String key2;

        for (DcPlanInfo bean : list) {
            String uuid = bean.getDcId();

            List<DcPlanInfo> pathList = xfRepositoryI.selectPath(bean);

            if (pathList.size() != 0) {
                for (DcPlanInfo entry : pathList) {
                    strings1.add(uuid);
                    strings1.add(entry.getDcId());

                    listString1.add(strings1);
                    boolean b = listString1.removeAll(listString2);//删除1中 与2 重复的元素

                    if (b == false) {
                        listString2.addAll(listString1);
                        listString1.removeAll(listString2);

                    }
                    String key = " "+bean.getDcId()+" "+bean.getDcNumber()+" "+bean.getDcType();

                    key2 = " "+entry.getDcId()+" "+entry.getDcNumber()+" "+entry.getDcType();

                    if (!key2.equals(key) && key2 != null) {
                        map.put(key,pathList);
                    }else {
                        break;
                    }

                }

                for (Map.Entry<String,List<DcPlanInfo>> entry : map.entrySet()) {
                    String key  = entry.getKey();
                    key = key.substring(38);
                    info.setData1(key);
                    for (DcPlanInfo entry1 : entry.getValue()) {
                        info.setData2(entry1.getDcNumber()+" " +entry1.getDcType());
                        List<StatisticsInfo> statisticsInfos = xfRepositoryI.selectStatisticsInfo();
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
                                xfRepositoryI.insertStatisticsInfo(info);
                            }
                        } else {
                            xfRepositoryI.insertStatisticsInfo(info);
                        }
                    }
                }
            }
        }

        return map;
    }
    }

