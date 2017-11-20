package com.luju.ygz.xx.repository.impl;


import com.luju.pojo.DcPlanInfo;
import com.luju.ygz.dc.repository.impl.DcRepositoryImpl;
import com.luju.ygz.xx.repository.XxRepositoryI;
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

        String sql = "SELECT dcId,dcNumber,dcStartTime,dcEndTime,dcType,dcTypeE,dcSource,dcDestination,dcDj,dcPath,dcIsUpdate,dcDH,jcSumHc FROM dc_show_data where dcXD = 'XD' AND dcXD = 'XZ' AND dcStartTime > now() AND dcStartTime > now() AND dcStartTime < ADDDATE(now(),interval 10800 second) order by dcStartTime";
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
        public DcPlanInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            DcPlanInfo userInfo = new DcPlanInfo();

            userInfo.setDcNumber(resultSet.getString("CC"));
            userInfo.setDcStartTimeS(resultSet.getString("JHKSSJ"));
            userInfo.setDcEndTimeS(resultSet.getString("JHJSSJ"));
            userInfo.setDcType(resultSet.getString("ZYXM"));
            userInfo.setDcSource(resultSet.getString("ZYGD"));
            userInfo.setDcTFX(resultSet.getString("TFX"));
            userInfo.setDcDj(resultSet.getInt("DJ"));
            userInfo.setDcGJHID(resultSet.getString("GJHID"));
            userInfo.setDcSWH(resultSet.getInt("SWH"));
            userInfo.setDcZGBZ(resultSet.getString("ZGBZ"));
            userInfo.setDcCS(resultSet.getInt("CS"));
            userInfo.setDcJSL(resultSet.getString("JSL"));
            userInfo.setJLSJ(resultSet.getTimestamp("JLSJ"));
            userInfo.setDcGDM(resultSet.getString("GDM"));

            return userInfo;
        }
    }
}
