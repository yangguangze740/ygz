package com.luju.ygz.xz.repository.imp;


import com.luju.pojo.DcPlanInfo;

import com.luju.ygz.xz.repository.XzRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
@Repository
public class XzRepositoryImpl implements XzRepositoryI {

    @Autowired
    private JdbcTemplate mysqlJdbcTemplate;

    @Override
    public List<DcPlanInfo> select4AllXzData() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT dcId,dcNumber,dcStartTime,dcEndTime,dcType,dcTypeE,dcSource,dcDestination,dcDj,dcPath,dcIsUpdate,dcDH,jcSumHc FROM dc_show_data WHERE");
        sql.append(" dcXD = 'XZ' AND dcStartTime > NOW() AND dcStartTime > NOW() AND dcStartTime < ADDDATE(NOW(), INTERVAL 10800 SECOND)");
        sql.append(" AND dcDestination = 'XZ01' or dcDestination = 'XZ01' or dcDestination = 'XB01' ORDER BY dcStartTime");
        Object[] args = {};
        try {
            return  mysqlJdbcTemplate.query(sql.toString(), args, new XzDataRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select dc show data error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> select4CxData() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT DISTINCT dcId, dcNumber, dcType, dcStartTime, jcJSL, jcImportant FROM dc_show_data S LEFT JOIN jc_plan_detals D ON D.jcNumber = S.dcNumber");
        sql.append(" WHERE dcType = '接车' AND dcStartTime < ADDDATE(NOW(), INTERVAL 10800 SECOND)");
        sql.append(" AND jcImportant = '超限' AND dcXD = 'XZ' AND dcPath = '01' ORDER BY dcStartTime");
        Object[] args = {};
        try {
            return mysqlJdbcTemplate.query(sql.toString(), args, new CxDataRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select dc show data error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> select4XbData() {
        String sql = "SELECT dcNumber, dcType FROM dc_show_data WHERE dcNumber = 'X215' AND dcNumber = 'X1463' AND dcNumber = 'X2435' AND dcDestination != 'XB01';";
        Object[] args = {};
        try {
            return mysqlJdbcTemplate.query(sql, args, new XbDataRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select dc show data error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> select4JlData() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT dcId,dcNumber,dcStartTime,dcEndTime,dcType,dcTypeE,dcSource,dcDestination,dcDj,dcPath,dcIsUpdate,dcDH,jcSumHc");
        sql.append("  FROM dc_show_data WHERE dcNumber LIKE '9%' AND LENGTH(dcNumber) = 5 AND dcStartTime > NOW()");
        sql.append(" AND dcStartTime > NOW() AND dcStartTime < ADDDATE(NOW(), INTERVAL 10800 SECOND) ORDER BY dcStartTime");
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql.toString(), args, new JlDataRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select dc show data error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> select4JlsData(DcPlanInfo time) {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT dcId,dcNumber,dcStartTime,dcEndTime,dcType,dcTypeE,dcSource,dcDestination,dcDj,dcPath,dcIsUpdate,dcDH,jcSumHc");
            sql.append(" FROM dc_show_data WHERE dcDestination = 'XZ03' AND (dcStartTime > ? AND dcEndTime < ?) ORDER BY dcStartTime");
            Object[] args = {
                    time.getDcStartTime(),
                    time.getDcEndTime()
            };

        try {
            return mysqlJdbcTemplate.query(sql.toString(), args, new JlDataRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select dc show data error");
            return null;
        }
    }

    private class JlDataRowMapper implements RowMapper<DcPlanInfo>{
        public DcPlanInfo mapRow(ResultSet resultset,int i)throws SQLException{
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
            dcPlanInfo.setDcDestination(resultset.getString("dcDestination"));

            return dcPlanInfo;

        }
    }

    private class XzDataRowMapper implements RowMapper<DcPlanInfo>{
        public DcPlanInfo mapRow(ResultSet resultset,int i)throws SQLException{
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
            dcPlanInfo.setDcDestination(resultset.getString("dcDestination"));

            return dcPlanInfo;
        }
    }

    private class CxDataRowMapper implements RowMapper<DcPlanInfo>{
        public DcPlanInfo mapRow(ResultSet resultSet,int i)throws SQLException{
            DcPlanInfo dcPlanInfo = new DcPlanInfo();
            dcPlanInfo.setDcId(resultSet.getString("dcId"));
            dcPlanInfo.setDcNumber(resultSet.getString("dcNumber"));
            dcPlanInfo.setDcType(resultSet.getString("dcType"));
            dcPlanInfo.setDcStartTime(resultSet.getTimestamp("dcStartTime"));
            dcPlanInfo.setJLSJ(resultSet.getTimestamp("jcJSL"));
            dcPlanInfo.setJcImportant(resultSet.getString("jcImportant"));

            return dcPlanInfo;
        }
    }

    private class XbDataRowMapper implements RowMapper<DcPlanInfo>{
        public DcPlanInfo mapRow(ResultSet resultSet,int i)throws SQLException{
            DcPlanInfo dcPlanInfo = new DcPlanInfo();
            dcPlanInfo.setDcNumber(resultSet.getString("dcNumber"));
            dcPlanInfo.setDcType(resultSet.getString("dcType"));

            return dcPlanInfo;
        }
    }
}