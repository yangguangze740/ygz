package com.luju.ygz.sz.repository.impl;

import com.luju.pojo.DcPlanInfo;
import com.luju.ygz.sz.repository.SzRepositoryI;
import com.luju.ygz.xz.repository.imp.XzRepositoryImpl;
import luju.common.util.ConstantFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static luju.common.util.ConstantFields.ZWQ;

@Repository
public class SzRepositoryImpl implements SzRepositoryI {

    @Autowired
    private JdbcTemplate  mysqlJdbcTemplate;

    @Override
    public List<DcPlanInfo> select4AllSzData() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT dcId,dcNumber,dcStartTime,dcEndTime,dcType,dcTypeE,dcSource,dcDestination,dcDj,dcPath,dcIsUpdate,dcDH,jcSumHc");
        sql.append(" FROM dc_show_data WHERE dcType = '接车' AND dcSource !='MSJ' AND (dcXD = 'SZ') AND dcStartTime > NOW()");
        sql.append(" AND dcStartTime < ADDDATE(NOW(), INTERVAL 10800 SECOND) ORDER BY dcStartTime");

        Object[] args = {};
        try {
            return  mysqlJdbcTemplate.query(sql.toString(), args, new SzDataRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select dc show data error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> select4JlData() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT DISTINCT dcId, dcNumber, dcType, dcStartTime, jcJSL, jcImportant FROM dc_show_data S");
        sql.append(" LEFT JOIN jc_plan_detals D ON D.jcNumber = S.dcNumber WHERE dcType = '接车' AND dcStartTime > NOW()");
        sql.append(" AND dcXD = 'SZ' AND dcStartTime < ADDDATE(NOW(), INTERVAL 10800 SECOND) AND jcJSL != '超限' AND dcDestination != 'XZ06' AND dcNumber  LIKE '9%' AND LENGTH(dcNumber) = 5");

        Object[] args = {};
        try {
            return  mysqlJdbcTemplate.query(sql.toString(), args, new JlDataRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select dc show data error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> select4CxData() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT DISTINCT dcId, dcNumber, dcType, dcStartTime, jcJSL, jcImportant FROM");
        sql.append(" dc_show_data S LEFT JOIN jc_plan_detals D ON D.jcNumber = S.dcNumber WHERE");
        sql.append(" dcType = '接车' AND dcXD = 'SZ' AND dcStartTime > NOW() AND dcStartTime < ADDDATE(NOW(), INTERVAL 10800 SECOND) AND dcNumber NOT LIKE '9%' AND jcJSL = '超限' AND dcDestination != 'XZ06'");

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
    public List<DcPlanInfo> select4AllJlData() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT dcId,dcNumber,dcStartTime,dcEndTime,dcType,dcTypeE,dcSource,dcDestination,dcDj,dcPath,dcIsUpdate,dcDH,jcSumHc");
        sql.append(" FROM dc_show_data WHERE dcNumber LIKE '9%' AND dcXD = 'SZ' AND LENGTH(dcNumber) = 5 AND dcStartTime > NOW()");
        sql.append(" AND dcStartTime < ADDDATE(NOW(), INTERVAL 10800 SECOND) ORDER BY dcStartTime");

        Object[] args = {};
        try {
            return  mysqlJdbcTemplate.query(sql.toString(), args, new JllDataRowMapper());
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
        sql.append(" FROM dc_show_data WHERE dcDestination = 'SZ04' AND dcStartTime > NOW() AND (dcStartTime > ? AND dcEndTime < ?) ORDER BY dcStartTime");
        Object[] args = {
                time.getDcStartTime(),
                time.getDcEndTime()
        };
        try {
            return  mysqlJdbcTemplate.query(sql.toString(), args, new JlsDataRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select dc show data error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> selec4FiveData() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT dcId,dcNumber,dcStartTime,dcEndTime,dcType,dcTypeE,dcSource,dcDestination,dcDj,dcPath,dcIsUpdate,dcDH,jcSumHc");
        sql.append(" FROM dc_show_data WHERE dcNumber LIKE '5%' AND LENGTH(dcNumber) = 5 AND dcStartTime > NOW()");
        sql.append(" AND dcXD = 'SZ' AND dcStartTime < ADDDATE(NOW(), INTERVAL 10800 SECOND) AND dcTypeE = 'JC' ORDER BY dcStartTime");

        Object[] args = {};
        try {
            return  mysqlJdbcTemplate.query(sql.toString(), args, new JllDataRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select dc show data error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> select4XbData() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT dcId,dcNumber,dcStartTime,dcEndTime,dcType,dcTypeE,dcSource,dcDestination,dcDj,dcPath,dcIsUpdate,dcDH,jcSumHc");
        sql.append(" FROM dc_show_data WHERE (dcNumber = 'X292' OR 'X468' OR 'X2480') AND (dcDestination != 'SZ06' OR 'SZ04')");
        sql.append(" AND dcXD = 'SZ'AND dcStartTime > NOW() AND dcStartTime < ADDDATE(NOW(), INTERVAL 10800 SECOND)");

        Object[] args = {};
        try {
            return  mysqlJdbcTemplate.query(sql.toString(), args, new JllDataRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select dc show data error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> select4ZwData() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT dcId,dcNumber,dcStartTime,dcEndTime,dcType,dcTypeE,dcSource,dcDestination,dcDj,dcPath,dcIsUpdate,dcDH,jcSumHc");
        sql.append(" FROM dc_show_data WHERE dcSource = 'ZWQ' AND dcStartTime > NOW() AND dcStartTime < ADDDATE(NOW(), INTERVAL 10800 SECOND)");

        Object[] args = {};
        try {
            return  mysqlJdbcTemplate.query(sql.toString(), args, new JllDataRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select dc show data error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> select4YhData() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT dcId,dcNumber,dcStartTime,dcEndTime,dcType,dcTypeE,dcSource,dcDestination,dcDj,dcPath,dcIsUpdate,dcDH,jcSumHc");
        sql.append(" FROM dc_show_data WHERE dcSource = 'YH' AND dcStartTime > NOW() AND dcStartTime < ADDDATE(NOW(), INTERVAL 10800 SECOND)");

        Object[] args = {};
        try {
            return  mysqlJdbcTemplate.query(sql.toString(), args, new JllDataRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select dc show data error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> select4DcData() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT dcId,dcNumber,dcStartTime,dcEndTime,dcType,dcTypeE,dcSource,dcDestination,dcDj,dcPath,dcIsUpdate,dcDH,jcSumHc");
        sql.append(" FROM dc_show_data WHERE dcSource = 'DC' AND dcStartTime > NOW() AND dcStartTime < ADDDATE(NOW(), INTERVAL 10800 SECOND)");

        Object[] args = {};
        try {
            return  mysqlJdbcTemplate.query(sql.toString(), args, new JllDataRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select dc show data error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> select4Time1Data() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT dcId,dcNumber,dcStartTime,dcEndTime,dcType,dcTypeE,dcSource,dcDestination,dcDj,dcPath,dcIsUpdate,dcDH,jcSumHc");
        sql.append(" FROM dc_show_data WHERE dcSource = 'YH' AND dcDestination = 'SZ06' AND dcStartTime > NOW() AND dcStartTime < ADDDATE(NOW(), INTERVAL 10800 SECOND)");

        Object[] args = {};
        try {
            return  mysqlJdbcTemplate.query(sql.toString(), args, new JllDataRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select dc show data error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> select4Time2Data() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT dcId,dcNumber,dcStartTime,dcEndTime,dcType,dcTypeE,dcSource,dcDestination,dcDj,dcPath,dcIsUpdate,dcDH,jcSumHc");
        sql.append(" FROM dc_show_data WHERE dcSource = 'YH' AND dcDestination = 'SZ06' AND dcStartTime > NOW() AND dcStartTime < ADDDATE(NOW(), INTERVAL 10800 SECOND)");

        Object[] args = {};
        try {
            return  mysqlJdbcTemplate.query(sql.toString(), args, new JllDataRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select dc show data error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> select4JcData() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT DISTINCT dcId, dcNumber, dcType, dcStartTime, jcJSL, jcImportant FROM");
        sql.append(" dc_show_data S LEFT JOIN jc_plan_detals D ON D.jcNumber = S.dcNumber WHERE");
        sql.append(" dcType = '接车' AND dcXD = 'SZ'AND dcStartTime > NOW() AND dcStartTime < ADDDATE(NOW(), INTERVAL 10800 SECOND) AND dcNumber LIKE '9%' AND LENGTH(dcNumber) = 5 AND jcJSL = '超限' AND dcDestination != 'XZ06'");

        Object[] args = {};
        try {
            return  mysqlJdbcTemplate.query(sql.toString(), args, new CxDataRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select dc show data error");
            return null;
        }
    }

    private class JlsDataRowMapper implements RowMapper<DcPlanInfo> {
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
            dcPlanInfo.setDcDestination(resultset.getString("dcDestination"));

            return dcPlanInfo;
        }
    }

    private class JllDataRowMapper implements RowMapper<DcPlanInfo> {
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
            dcPlanInfo.setDcJSL(resultset.getString("jcJSL"));
            dcPlanInfo.setDcImportant(resultset.getString("jcImportant"));
            return dcPlanInfo;
        }
    }

    private class SzDataRowMapper implements RowMapper<DcPlanInfo> {
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

            if(resultset.getString("dcSource").equals(ConstantFields.ZWQ) ){
                dcPlanInfo.setDcSource(ConstantFields.ZWQSOURCEC);
            }
            if(resultset.getString("dcSource").equals(ConstantFields.YH)){
                dcPlanInfo.setDcSource(ConstantFields.YHSOURCEC);
            }
            if (resultset.getString("dcSource").equals(ConstantFields.DC)){
                dcPlanInfo.setDcSource(ConstantFields.DCSOURCEC);
            }

            return dcPlanInfo;
        }
    }

    private class JlDataRowMapper implements RowMapper<DcPlanInfo> {
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
}
