package com.luju.ygz.sf.repository.impl;

import com.luju.pojo.DcPlanInfo;
import com.luju.ygz.sf.repository.SfRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SfRepositoryImpl implements SfRepositoryI {

    @Autowired
    private JdbcTemplate mysqlJdbcTemplate;

    @Override
    public List<DcPlanInfo> select4SfList() {
        String sql = "SELECT dcId,dcNumber,dcStartTime,dcEndTime,dcType,dcTypeE,dcSource,dcDestination,dcDj,dcPath,dcIsUpdate,dcDH,jcSumHc FROM dc_show_data where dcXD = 'SF' AND dcStartTime > now() AND dcStartTime < ADDDATE(now(),interval 10800 second) order by dcStartTime";
        Object[] args = {};
        try {
            return mysqlJdbcTemplate.query(sql, args,new DcDataRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select Sf data error");
            return null;
        }
    }

    private class DcDataRowMapper implements RowMapper<DcPlanInfo> {
        public DcPlanInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            DcPlanInfo userInfo = new DcPlanInfo();

            userInfo.setDcId(resultSet.getString("dcId"));
            userInfo.setDcNumber(resultSet.getString("dcNumber"));
            userInfo.setDcStartTime(resultSet.getTimestamp("dcStartTime"));
            userInfo.setDcEndTime(resultSet.getTimestamp("dcEndTime"));
            userInfo.setDcType(resultSet.getString("dcType"));
            userInfo.setDcTypeE(resultSet.getString("dcTypeE"));
            userInfo.setDcSource(resultSet.getString("dcSource"));
            userInfo.setDcDestination(resultSet.getString("dcDestination"));
            userInfo.setDcDj(resultSet.getInt("dcDj"));
            userInfo.setDcPath(resultSet.getString("dcPath"));
            userInfo.setIsUpdate(resultSet.getInt("dcIsUpdate"));
            userInfo.setDcDH(resultSet.getString("dcDH"));
            userInfo.setSumHc(resultSet.getInt("jcSumHc"));

            return userInfo;
        }
    }
}
