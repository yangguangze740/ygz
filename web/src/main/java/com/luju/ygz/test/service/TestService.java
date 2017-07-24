package com.luju.ygz.test.service;


import com.luju.pojo.JcPlanInfo;

import java.util.List;

public interface TestService {
    
    public List<JcPlanInfo> selectPlanDataFromOra();

    public List<JcPlanInfo> selectPlanDataFromMysql();

    public boolean insertDataToMysql(JcPlanInfo info);
}
