package com.luju.ygz.test.repository;


import com.luju.pojo.JcPlanInfo;

import java.util.List;

public interface TestRepository {

    /**
     *  查询接车作业计划表数据、接车目录表
     * @return 车次、源、目的、计划时间、QBID、总换长
     */
    public List<JcPlanInfo> selectJcPlan();

    /**
     *  查询接车正文表
     * @return （QBID）单换长、记事
     */
    public List<JcPlanInfo> selectJcZW();

    /**
     *  写入接车作业计划表数据、接车目录表数据到MySQL
     * @return null
     */
    public boolean insertToPlanCopy(JcPlanInfo info);

    /**
     *  写入接车正文表数据到MySQL
     * @return null
     */
    public boolean insertToZWCopy(JcPlanInfo info);

}
