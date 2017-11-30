package com.luju.ygz.xx.repository.impl;


import com.luju.pojo.DcPlanInfo;
import com.luju.ygz.dc.repository.impl.DcRepositoryImpl;
import com.luju.ygz.xx.repository.XxRepositoryI;
import luju.common.util.ConstantFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class XxRepositoryImpl implements XxRepositoryI {

    @Autowired
    private JdbcTemplate mysqlJdbcTemplate;

    @Override
    public List<DcPlanInfo> select4AllList() {

        String sql = "SELECT dcId,dcNumber,dcStartTime,dcEndTime,dcType,dcTypeE,dcSource,dcDestination,dcDj,dcPath,dcIsUpdate,dcDH,jcSumHc FROM dc_show_data where  dcXD = 'XD' OR dcXD = 'XZ' AND dcStartTime > now()  AND dcStartTime < ADDDATE(now(),interval 10800 second) order by dcStartTime";
        Object[] args = {};
        try {
            return mysqlJdbcTemplate.query(sql, args, new XxDataRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select dc show data error");
            return null;
        }
    }

    private class XxDataRowMapper implements RowMapper<DcPlanInfo> {
        public DcPlanInfo mapRow(ResultSet resultset, int i) throws SQLException {

            DcPlanInfo dcPlanInfo = new DcPlanInfo();
            dcPlanInfo.setDcId(resultset.getString("dcId"));
            dcPlanInfo.setDcNumber(resultset.getString("dcNumber"));
            dcPlanInfo.setDcStartTime(resultset.getTimestamp("dcStartTime"));
            dcPlanInfo.setDcEndTime(resultset.getTimestamp("dcEndTime"));
            dcPlanInfo.setDcType(resultset.getString("dcType"));
            dcPlanInfo.setDcTypeE(resultset.getString("dcTypeE"));
            dcPlanInfo.setDcDestination(resultset.getString("dcDestination"));
            dcPlanInfo.setDcDj(resultset.getInt("dcDj"));
            dcPlanInfo.setDcPath(resultset.getString("dcPath"));
            dcPlanInfo.setIsUpdate(resultset.getInt("dcIsUpdate"));
            dcPlanInfo.setSumHc(resultset.getFloat("jcSumHc"));
            dcPlanInfo.setDcDH(resultset.getString("dcDH"));
            dcPlanInfo.setDcSource("马三家");

            return dcPlanInfo;
        }
    }
}
