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
        sql.append(" dcType = '接车' and (dcXD = 'XZ' OR dcXD ='XB' ) AND dcStartTime > NOW() ");
        sql.append(" AND dcStartTime < ADDDATE(NOW(), INTERVAL 10800 SECOND) ORDER BY dcStartTime");
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
        sql.append(" WHERE dcType = '接车' AND dcStartTime > NOW() AND dcStartTime < ADDDATE(NOW(), INTERVAL 10800 SECOND)");
        sql.append(" AND jcJSL = '超限' AND dcDestination != 'XZ01' AND dcNumber LIKE '9%' AND LENGTH(dcNumber) = 5");
        Object[] args = {};
        try {
            return mysqlJdbcTemplate.query(sql.toString(), args, new CxDataRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select xz show data error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> select4XbData() {
        String sql = "SELECT dcId, dcNumber, dcType, dcDestination FROM dc_show_data WHERE (dcNumber = 'X215' OR dcNumber = 'X1463' OR dcNumber = 'X2435') AND dcDestination != 'XB01' AND dcStartTime > NOW() AND dcStartTime < ADDDATE(NOW(), INTERVAL 10800 SECOND)";
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
        sql.append(" FROM dc_show_data WHERE dcNumber LIKE '9%' AND LENGTH(dcNumber) = 5");
        sql.append(" AND dcStartTime < ADDDATE(NOW(), INTERVAL 10800 SECOND) ORDER BY dcStartTime");
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
            sql.append(" FROM dc_show_data WHERE dcDestination = 'XZ03' AND (dcStartTime > ? AND dcStartTime < ?) ORDER BY dcStartTime");
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
            dcPlanInfo.setDcSource("马三家");

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
            dcPlanInfo.setJcJSL(resultSet.getString("jcJSL"));
            dcPlanInfo.setJcImportant(resultSet.getString("jcImportant"));

            return dcPlanInfo;
        }
    }

    private class XbDataRowMapper implements RowMapper<DcPlanInfo>{
        public DcPlanInfo mapRow(ResultSet resultSet,int i)throws SQLException{
            DcPlanInfo dcPlanInfo = new DcPlanInfo();
            dcPlanInfo.setDcNumber(resultSet.getString("dcNumber"));
            dcPlanInfo.setDcType(resultSet.getString("dcType"));
            dcPlanInfo.setDcDestination(resultSet.getString("dcDestination"));

            return dcPlanInfo;
        }
    }
}
