package com.luju.ygz.sd.repository.impl;


import com.luju.pojo.DcPlanInfo;
import com.luju.pojo.StatisticsInfo;
import com.luju.ygz.sd.repository.SdRepositoryI;
import luju.common.util.ConstantFields;
import luju.common.util.DataProcess;
import luju.common.util.PrimaryKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SdRepositoryImpl implements SdRepositoryI {
    @Autowired
    private JdbcTemplate mysqlJdbcTemplate;

    DataProcess dataProcess = new DataProcess();

    PrimaryKeyUtil uuid = new PrimaryKeyUtil();

    @Override
    public List<DcPlanInfo> select4SdList() {
        String sql = "SELECT dcId,dcNumber,dcStartTime,dcEndTime,dcType,dcTypeE,dcSource,dcDestination,dcDj,dcPath,dcIsUpdate,dcDH,jcSumHc FROM dc_show_data where dcXD = 'SD' AND dcStartTime > now() AND dcStartTime < ADDDATE(now(),interval 10800 second) order by dcStartTime";
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
        sql.append(" FROM dc_show_data WHERE (dcDH != '02' OR dcDH != '10') AND dcXD = 'SD' AND LENGTH(dcNumber) = 6 AND dcStartTime > NOW()");
        sql.append(" AND dcStartTime < ADDDATE(NOW(), INTERVAL 10800 SECOND) AND dcTypeE = 'JC' ORDER BY dcStartTime");

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

    @Override
    public List<DcPlanInfo> selectDcData() {
        String sql = "SELECT dcId,dcNumber,dcStartTime,dcEndTime,dcType,dcTypeE,dcSource,dcDestination,dcDj,dcPath,dcIsUpdate,dcDH,jcSumHc FROM dc_show_data where dcXD = 'SD' AND dcStartTime > now() AND dcStartTime > now() AND dcStartTime < ADDDATE(now(),interval 10800 second) order by dcStartTime";
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
    public List<DcPlanInfo> selectPath(DcPlanInfo dcPlanInfo) {
        String sql = "SELECT dcId, dcNumber, dcType,jcPath, dcStartTime, dcEndTime  FROM dc_show_data D LEFT JOIN (SELECT DISTINCT jcPath FROM jc_path_info WHERE jcDCH IN (SELECT jcDCH FROM jc_path_info WHERE jcPath = ? )) I ON D.dcPath = I.jcPath WHERE jcPath is not null and (dcStartTime < ? AND dcEndTime > ? OR dcStartTime > ? AND ? > dcStartTime OR dcEndTime > ? AND ? > dcEndTime)";
        Object[] args = {
                dcPlanInfo.getDcPath(),
                dcPlanInfo.getDcStartTime(),
                dcPlanInfo.getDcEndTime(),
                dcPlanInfo.getDcStartTime(),
                dcPlanInfo.getDcEndTime(),
                dcPlanInfo.getDcStartTime(),
                dcPlanInfo.getDcEndTime()
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

    @Override
    public int updateSource(DcPlanInfo info) {
        String sql = "UPDATE dc_show_data SET dcSource = ?, dcPath = ?, dcIsUpdate = 1 where dcId = ?";
        Object[] args = {
                info.getDcSource(),
                info.getDcTypeE()+info.getDcSource()+info.getDcDestination(),
                info.getDcId()
        };
        try {
            return mysqlJdbcTemplate.update(sql,args);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("update source error");
            return 0;
        }
    }

    @Override
    public int updateDestination(DcPlanInfo dcPlanInfo) {
        String sql = "UPDATE dc_show_data SET dcDestination = ?, dcPath = ?, dcIsUpdate = 2 where dcId = ?";
        Object[] args = {
                dcPlanInfo.getDcDestination(),
                dcPlanInfo.getDcTypeE()+dcPlanInfo.getDcDestination()+dcPlanInfo.getDcSource(),
                dcPlanInfo.getDcId()
        };
        try {
            return mysqlJdbcTemplate.update(sql,args);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("update destination error");
            return 0;
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

    private class DcDataRowMapper implements RowMapper<DcPlanInfo>{
        public DcPlanInfo mapRow(ResultSet resultset,int i )throws SQLException{
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

            String source = resultset.getString("dcSource");
            if (source != null && source.equals(ConstantFields.DCSOURCE)) {
                dcPlanInfo.setDcSource(ConstantFields.DCSOURCEC);
            } else if (source != null && source.equals(ConstantFields.ZWQSOURCE)){
                dcPlanInfo.setDcSource(ConstantFields.ZWQSOURCEC);
            } else if (source != null && source.equals(ConstantFields.YHSOURCE)){
                dcPlanInfo.setDcSource(ConstantFields.YHSOURCEC);
            } else if (source != null && source.equals(ConstantFields.ZCSOURCE)){
                dcPlanInfo.setDcSource(ConstantFields.ZCSOURCEC);
            } else if (source != null && source.equals(ConstantFields.QC)){
                dcPlanInfo.setDcSource(ConstantFields.TYPE_QCX);
            } else if (source != null && source.equals(ConstantFields.JD)){
                dcPlanInfo.setDcSource(ConstantFields.TYPE_JDX);
            } else if (source != null && source.equals(ConstantFields.JDZ)){
                dcPlanInfo.setDcSource(ConstantFields.TYPE_ZX);
            } else if (source != null && source.equals(ConstantFields.JTD)){
                dcPlanInfo.setDcSource(ConstantFields.TYPE_DX);
            } else if (source != null && source.equals(ConstantFields.JTY)){
                dcPlanInfo.setDcSource(ConstantFields.TYPE_YX);
            }
            else {
                dcPlanInfo.setDcSource(source);
                if(resultset.getString("dcTypeE").equals(ConstantFields.JT)) {
                    dcPlanInfo.setSelectList(dataProcess.jtSelectList());
                }

                if(resultset.getString("dcTypeE").equals(ConstantFields.ZM)) {
                    dcPlanInfo.setSelectList(dataProcess.zmSelectList());
                }

                if(resultset.getString("dcTypeE").equals(ConstantFields.TYPE_TC)) {
                    dcPlanInfo.setSelectList(dataProcess.tcSelectList());
                }
            }

            if (source != null && resultset.getInt("dcIsUpdate") == 1) {
                if (resultset.getString("dcTypeE").equals(ConstantFields.JT)) {
                    if (source.equals(ConstantFields.QC)){
                        dcPlanInfo.setDcSource(ConstantFields.TYPE_QCX);
                    }else if(source.equals(ConstantFields.JDZ)){
                        dcPlanInfo.setDcSource(ConstantFields.TYPE_ZX);
                    }else if(source.equals(ConstantFields.JTD)){
                        dcPlanInfo.setDcSource(ConstantFields.TYPE_DX);
                    }else if(source.equals(ConstantFields.JTY)){
                        dcPlanInfo.setDcSource(ConstantFields.TYPE_YX);
                    }
                    else {
                        dcPlanInfo.setDcSource(ConstantFields.TYPE_JDX);
                    }
                    dcPlanInfo.setSelectList(dataProcess.jtSelectListUpdate(dcPlanInfo.getDcSource()));
                }
                if (resultset.getString("dcTypeE").equals(ConstantFields.ZM)) {
                    dcPlanInfo.setSelectList(dataProcess.zmtcSelectListUpdate(dcPlanInfo.getDcSource()));
                }
                if (resultset.getString("dcTypeE").equals(ConstantFields.TYPE_TC)) {
                    dcPlanInfo.setSelectList(dataProcess.zmtcSelectListUpdate(dcPlanInfo.getDcSource()));
                }

            }
            String des = resultset.getString("dcDestination");
            if (des == null && resultset.getString("dcTypeE").equals(ConstantFields.BWJ)) {
                    dcPlanInfo.setSelectList(dataProcess.bwjSelectList4N());
            }
            if (des != null && des.equals(ConstantFields.S)) {
                dcPlanInfo.setDcDestination(ConstantFields.BWJDS);
            }
            if (des != null && des.equals(ConstantFields.N)) {
                dcPlanInfo.setDcDestination(ConstantFields.BWJDN);
            }

            if (des != null && resultset.getInt("dcIsUpdate") == 2) {
                if (resultset.getString("dcTypeE").equals(ConstantFields.BWJ)) {
                    if (des.equals(ConstantFields.S)){
                        dcPlanInfo.setDcDestination(ConstantFields.BWJDS);
                    } else {
                        dcPlanInfo.setDcDestination(ConstantFields.BWJDN);
                    }
                    dcPlanInfo.setSelectList(dataProcess.bwjSelectListUpdate(dcPlanInfo.getDcDestination()));
                }
            }

            return dcPlanInfo;
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
                dcPlanInfo.setDcSource(ConstantFields.ZWQSOURCEC);
            }
            else if(resultset.getString("dcSource").equals(ConstantFields.YH)){
                dcPlanInfo.setDcSource(ConstantFields.YHSOURCEC);
            }

            else if (resultset.getString("dcSource").equals(ConstantFields.DC)){
                dcPlanInfo.setDcSource(ConstantFields.DCSOURCEC);
            }
            else {
                dcPlanInfo.setDcSource(resultset.getString("dcSource"));
            }




            return dcPlanInfo;
        }
    }
}
