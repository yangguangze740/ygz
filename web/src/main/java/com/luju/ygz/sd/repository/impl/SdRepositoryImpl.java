package com.luju.ygz.sd.repository.impl;


import com.luju.pojo.DcPlanInfo;
import com.luju.ygz.dc.repository.impl.DcRepositoryImpl;
import com.luju.ygz.sd.repository.SdRepositoryI;
import com.luju.ygz.sz.repository.impl.SzRepositoryImpl;
import com.luju.ygz.xx.repository.impl.XxRepositoryImpl;
import luju.common.util.ConstantFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SdRepositoryImpl implements SdRepositoryI {
    @Autowired
    private JdbcTemplate mysqlJdbcTemplate;

    @Override
    public List<DcPlanInfo> select4SdList() {
        String sql = "SELECT dcId,dcNumber,dcStartTime,dcEndTime,dcType,dcTypeE,dcSource,dcDestination,dcDj,dcPath,dcIsUpdate,dcDH,jcSumHc FROM dc_show_data where dcXD = 'SD' AND dcSource != 'MSJ' AND dcStartTime > now() AND dcStartTime < ADDDATE(now(),interval 10800 second) order by dcStartTime";
        Object[] args = {};
        try {
            return mysqlJdbcTemplate.query(sql, args, new SdDataRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select dc show data error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> select4CxList() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT DISTINCT dcId, dcNumber, dcType, dcStartTime, jcJSL, jcImportant FROM dc_show_data S");
        sql.append(" LEFT JOIN jc_plan_detals D ON D.jcNumber = S.dcNumber WHERE dcType = '接车' AND dcStartTime > NOW()");
        sql.append(" AND dcStartTime < ADDDATE(NOW(), INTERVAL 10800 SECOND) AND jcJSL = '超限' AND dcDestination != 'SD05' AND dcDestination != 'SD10'");

        Object[] args = {};
        try {
            return  mysqlJdbcTemplate.query(sql.toString(), args, new CxDataRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select dc show data error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> select4SixList() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT dcId,dcNumber,dcStartTime,dcEndTime,dcType,dcTypeE,dcSource,dcDestination,dcDj,dcPath,dcIsUpdate,dcDH,jcSumHc");
        sql.append(" FROM dc_show_data WHERE  dcDestination != 'SD02' AND dcDestination != 'SD10' AND dcXD = 'SD' AND LENGTH(dcNumber) = 6 AND dcStartTime > NOW()");
        sql.append(" AND dcStartTime < ADDDATE(NOW(), INTERVAL 10800 SECOND) ORDER BY dcStartTime");

        Object[] args = {};
        try {
            return  mysqlJdbcTemplate.query(sql.toString(), args, new SixDataRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select dc show data error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> select4TzList() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT dcId,dcNumber,dcStartTime,dcEndTime,dcType,dcTypeE,dcSource,dcDestination,dcDj,dcPath,dcIsUpdate,dcDH,jcSumHc");
        sql.append(" FROM dc_show_data WHERE (dcDestination = 'SD06' OR   dcDestination = 'SD07' OR dcDestination = 'SD08' OR dcDestination = 'SD09' OR dcDestination = 'SD10') AND dcNumber LIKE 'D%'");
        sql.append(" AND dcXD = 'SD' AND dcStartTime > NOW() AND dcStartTime < ADDDATE(NOW(), INTERVAL 10800 SECOND) ORDER BY dcStartTime");

        Object[] args = {};
        try {
            return  mysqlJdbcTemplate.query(sql.toString(), args, new SixDataRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select dc show data error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> select4Partition() {
        String sql = "SELECT dcId,dcNumber,dcStartTime,dcEndTime,dcType,dcDestination,dcDH FROM ygz_show.dc_show_data where dcXD = 'SD' and dcTypeE = 'JC' AND dcStartTime > now() and dcStartTime < ADDDATE(NOW(), INTERVAL 10800 SECOND) order by dcStartTime ";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new selectPartitionRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select selectStatisticsInfoWithTime error");
            return null;
        }
    }

    private class selectPartitionRowMapper implements RowMapper<DcPlanInfo> {
        public DcPlanInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            DcPlanInfo userInfo = new DcPlanInfo();

            userInfo.setDcId(resultSet.getString("dcId"));
            userInfo.setDcNumber(resultSet.getString("dcNumber"));
            userInfo.setDcStartTime(resultSet.getTimestamp("dcStartTime"));
            userInfo.setDcEndTime(resultSet.getTimestamp("dcEndTime"));
            userInfo.setDcType(resultSet.getString("dcType"));
            userInfo.setDcDestination(resultSet.getString("dcDestination"));
            userInfo.setDcDH(resultSet.getString("dcDH"));

            return userInfo;
        }
    }

    private class SixDataRowMapper implements RowMapper<DcPlanInfo> {
        public DcPlanInfo mapRow(ResultSet resultset, int i)throws SQLException {
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
            dcPlanInfo.setDcSource(resultset.getString("dcSource"));

            return dcPlanInfo;
        }
    }

    private class CxDataRowMapper implements RowMapper<DcPlanInfo> {
        public DcPlanInfo mapRow(ResultSet resultset, int i)throws SQLException {
            DcPlanInfo dcPlanInfo = new DcPlanInfo();
            dcPlanInfo.setDcId(resultset.getString("dcId"));
            dcPlanInfo.setDcNumber(resultset.getString("dcNumber"));
            dcPlanInfo.setDcType(resultset.getString("dcType"));
            dcPlanInfo.setDcStartTime(resultset.getTimestamp("dcStartTime"));
            dcPlanInfo.setJcJSL(resultset.getString("jcJSL"));
            dcPlanInfo.setJcImportant(resultset.getString("jcImportant"));
            return dcPlanInfo;

        }
    }

    private class SdDataRowMapper implements RowMapper<DcPlanInfo> {
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

            if(resultset.getString("dcSource").equals(ConstantFields.ZWQ) ){
                dcPlanInfo.setDcSource("转弯桥");
            }
            else if(resultset.getString("dcSource").equals(ConstantFields.YH)){
                dcPlanInfo.setDcSource("于洪");
            }

            else if (resultset.getString("dcSource").equals(ConstantFields.DC)){
                dcPlanInfo.setDcSource("大成");
            }
            else {
                dcPlanInfo.setDcSource(resultset.getString("dcSource"));
            }




            return dcPlanInfo;
        }
    }
}
