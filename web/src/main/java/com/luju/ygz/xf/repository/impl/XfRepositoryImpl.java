package com.luju.ygz.xf.repository.impl;

import com.luju.pojo.DcPlanInfo;
import com.luju.pojo.StatisticsInfo;
import com.luju.ygz.sf.repository.impl.SfRepositoryImpl;
import com.luju.ygz.xf.repository.XfRepositoryI;
import luju.common.util.ConstantFields;
import luju.common.util.PrimaryKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class XfRepositoryImpl implements XfRepositoryI {

    @Autowired
    private JdbcTemplate mysqlJdbcTemplate;

    PrimaryKeyUtil uuid = new PrimaryKeyUtil();

    @Override
    public List<DcPlanInfo> select4XfList() {
        String sql = "SELECT dcId,dcNumber,dcStartTime,dcEndTime,dcType,dcTypeE,dcSource,dcDestination,dcDj,dcPath,dcIsUpdate,dcDH,jcSumHc FROM dc_show_data where (dcXD = 'XF' or (dcXD = 'XZ' and dcTypeE = 'FC')) AND dcStartTime > now() AND dcStartTime < ADDDATE(now(),interval 10800 second) order by dcStartTime";
        Object[] args = {};
        try {
            return mysqlJdbcTemplate.query(sql, args,new DcDataRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select xf data error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> selectDcData() {
        String sql = "SELECT dcId,dcNumber,dcStartTime,dcEndTime,dcType,dcTypeE,dcSource,dcDestination,dcDj,dcPath,dcIsUpdate,dcDH,jcSumHc FROM dc_show_data where dcXD = 'XD' AND dcStartTime > now() AND dcStartTime > now() AND dcStartTime < ADDDATE(now(),interval 10800 second) order by dcStartTime";
        Object[] args = {};
        try {
            return mysqlJdbcTemplate.query(sql, args, new DcDataRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select dc show data error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> selectPath(DcPlanInfo bean) {
        String sql = "SELECT dcId, dcNumber, dcType,jcPath, dcStartTime, dcEndTime  FROM dc_show_data D LEFT JOIN (SELECT DISTINCT jcPath FROM jc_path_info WHERE jcDCH IN (SELECT jcDCH FROM jc_path_info WHERE jcPath = ? )) I ON D.dcPath = I.jcPath WHERE jcPath is not null and (dcStartTime < ? AND dcEndTime > ? OR dcStartTime > ? AND ? > dcStartTime OR dcEndTime > ? AND ? > dcEndTime)";
        Object[] args = {
                bean.getDcPath(),
                bean.getDcStartTime(),
                bean.getDcEndTime(),
                bean.getDcStartTime(),
                bean.getDcEndTime(),
                bean.getDcStartTime(),
                bean.getDcEndTime()
        };

        try {
            return mysqlJdbcTemplate.query(sql, args, new DcPathRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select tc data error");
            return null;
        }
    }

    @Override
    public List<StatisticsInfo> selectStatisticsInfo() {
        String sql = "SELECT DISTINCT logTime,data1,data2 FROM dc_statistics;";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new selectStatisticsInfoRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select selectStatisticsInfo error");
            return null;
        }
    }

    private class selectStatisticsInfoRowMapper implements RowMapper<StatisticsInfo> {
        public StatisticsInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            StatisticsInfo userInfo = new StatisticsInfo();

            userInfo.setLogTime(resultSet.getString("logTime"));
            userInfo.setData1(resultSet.getString("data1"));
            userInfo.setData2(resultSet.getString("data2"));

            return userInfo;
        }
    }

    @Override
    public int insertStatisticsInfo(StatisticsInfo info) {
        String sql = "insert into dc_statistics (statisticsId,logTime,data1,data2) value (?,CURDATE(),?,?)";
        Object[] args = {
                uuid.uuidPrimaryKey(),
                info.getData1(),
                info.getData2()
        };
        try {
            return mysqlJdbcTemplate.update(sql,args);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("insert StatisticsInfo error");
            return 0;
        }
    }

    private class DcPathRowMapper implements RowMapper<DcPlanInfo> {
        public DcPlanInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            DcPlanInfo userInfo = new DcPlanInfo();

            userInfo.setDcId(resultSet.getString("dcId"));
            userInfo.setDcNumber(resultSet.getString("dcNumber"));
            userInfo.setDcType(resultSet.getString("dcType"));
            userInfo.setDcPath(resultSet.getString("jcPath"));
            userInfo.setDcStartTime(resultSet.getTimestamp("dcStartTime"));
            userInfo.setDcEndTime(resultSet.getTimestamp("dcEndTime"));


            return userInfo;
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
            userInfo.setDcDestination(resultSet.getString("dcDestination"));
            userInfo.setDcDj(resultSet.getInt("dcDj"));
            userInfo.setDcPath(resultSet.getString("dcPath"));
            userInfo.setIsUpdate(resultSet.getInt("dcIsUpdate"));
            userInfo.setDcDH(resultSet.getString("dcDH"));
            userInfo.setSumHc(resultSet.getInt("jcSumHc"));

            String source = resultSet.getString("dcSource");
            if (source != null && source.equals(ConstantFields.JCSOURCE)) {
                userInfo.setDcSource(ConstantFields.JCSOURCEC);
            } else if (source != null && source.equals(ConstantFields.ZCSOURCE)){
                userInfo.setDcSource(ConstantFields.ZCSOURCEC);
            } else if (source != null && source.equals(ConstantFields.ZWQSOURCE)){
                userInfo.setDcSource(ConstantFields.ZWQSOURCEC);
            }else if (source != null && source.equals(ConstantFields.DCSOURCE)){
                userInfo.setDcSource(ConstantFields.DCSOURCEC);
            }else if (source != null && source.equals(ConstantFields.YHSOURCE)){
                userInfo.setDcSource(ConstantFields.YHSOURCEC);
            }else if (source != null && source.equals(ConstantFields.QC)){
                userInfo.setDcSource(ConstantFields.TYPE_QCX);
            } else if (source != null && source.equals(ConstantFields.JD)){
                userInfo.setDcSource(ConstantFields.TYPE_JDX);
            }else if (source != null && source.equals(ConstantFields.ZCSOURCE)){
                userInfo.setDcSource(ConstantFields.ZCSOURCEC);}
            else {
                userInfo.setDcSource(source);
            }

            return userInfo;
        }
    }
}
