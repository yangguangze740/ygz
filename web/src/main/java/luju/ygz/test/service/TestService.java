package luju.ygz.test.service;


import com.luju.pojo.JcPlanInfo;

import java.util.List;

public interface TestService {
    
    public List<JcPlanInfo> selectPlanDataFromOra();

    public List<JcPlanInfo> selectZWDataFromOra();

    /**
     *  将从Ora查询的数据全部录入mysql
     * @return boolean
     */
    public boolean insertDataToMysql(JcPlanInfo info);
}
