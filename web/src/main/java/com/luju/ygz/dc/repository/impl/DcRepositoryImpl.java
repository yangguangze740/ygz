package com.luju.ygz.dc.repository.impl;

import com.luju.pojo.DcPlanInfo;
import com.luju.pojo.StatisticsInfo;
import com.luju.pojo.TextareaInfo;
import com.luju.ygz.dc.repository.DcRepositoryI;
import luju.common.util.ConstantFields;
import luju.common.util.DataProcess;
import luju.common.util.PrimaryKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class DcRepositoryImpl implements DcRepositoryI {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private JdbcTemplate mysqlJdbcTemplate;

    PrimaryKeyUtil uuid = new PrimaryKeyUtil();

    DataProcess dataProcess = new DataProcess();

    @Override
    public List<DcPlanInfo> selectDcPlanFromOra() {
        String sql = "SELECT CC, JHKSSJ, JHJSSJ, ZYXM, ZYGD,TFX, DJ, M.GJHID, SWH, ZGBZ, CS, JSL,JLSJ,GDM FROM CQZ_GJHML M LEFT JOIN CQZ_GJHZW Z ON M.GJHID = Z.GJHID";
        Object[] args = {};

        try {
            return jdbcTemplate.query(sql, args, new DcPlanCopyRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select dc data from ora error");
        return null;
        }
    }

    @Override
    public List<DcPlanInfo> selectCopyData() {
        String sql = "SELECT dcGJHID,dcJLSJ FROM dc_plan_copy order by dcGJHID,dcJLSJ";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new DcCopyDataRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select error");
            return null;
        }
    }

    @Override
    public int deleteRepeatDataFromCopy(String s) {
        String sql = "DELETE FROM dc_plan_copy WHERE dcGJHID= ? ";
        Object[] args = {
                s
        };
        try {
            return mysqlJdbcTemplate.update(sql,args);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("deleteRepeatDataFromCopy error");
            return 0;
        }

    }

    @Override
    public List<DcPlanInfo> selectJtPlan() {
        String sql = "SELECT distinct dcNumber, dcStartTime, dcEndTime, dcType, dcSource, dcDj, dcTFX FROM ygz_show.dc_plan_copy where dcType='解体' AND dcSource LIKE 'S%' order by dcStartTime";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new DcJtPlanRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> selectXxJtPlan() {
        String sql = "SELECT distinct dcNumber, dcStartTime, dcEndTime, dcType, dcSource, dcDj, dcTFX FROM ygz_show.dc_plan_copy where dcType='解体' AND dcSource LIKE 'X%' order by dcStartTime";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new DcJtPlanRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> selectXxZmPlan() {
        String sql = "SELECT distinct dcNumber, dcStartTime, dcEndTime, dcType, dcSource, dcDj, dcTFX FROM ygz_show.dc_plan_copy where dcType='摘帽' AND dcSource LIKE 'X%' order by dcStartTime";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new DcJtPlanRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> selectXxZcPlan() {
        String sql = "SELECT distinct dcNumber, dcStartTime, dcEndTime, dcType, dcSource, dcDj, dcTFX FROM ygz_show.dc_plan_copy where dcType='转场' AND dcSource LIKE 'SB%' order by dcStartTime";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new DcJtPlanRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> selectJtzData() {
        String sql = "SELECT distinct dcNumber, dcStartTime, dcEndTime, dcType, dcSource, dcDj, dcTFX FROM ygz_show.dc_plan_copy where dcType='解体' order by dcStartTime";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new DcJtPlanRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> selectZmPlan() {
        String sql = "SELECT distinct dcNumber,dcStartTime,dcEndTime,dcType,dcSource,dcTFX,dcDj FROM ygz_show.dc_plan_copy where dcType = '摘帽' AND dcSource LIKE 'S%' order by dcStartTime";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new DcZmPlanRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select zm error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> selectTcPlan() {
        String sql = "SELECT dcNumber,dcStartTime,dcEndTime,dcType,dcSource,dcTFX,dcDj,dcGJHID,dcSWH,dcZGBZ,dcCS FROM dc_plan_copy where dcType='挑车' order by dcSource,dcSWH";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new DcTcPlanRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> selectZcPlan() {
        String sql = "SELECT distinct dcNumber,dcStartTime,dcEndTime,dcType,dcSource,dcTFX,dcDj,dcGDM FROM ygz_show.dc_plan_copy where dcType = '转场' AND dcSource LIKE 'S%'order by dcNumber";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new DcZcPlanRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> selectJF() {
        String sql = "SELECT distinct dcId, dcNumber, dcType, dcStartTime, dcDH, jcJSL, jcImportant FROM jc_plan_detals D LEFT JOIN dc_show_data S ON D.jcNumber = S.dcNumber WHERE dcType = '接车' AND dcStartTime < ADDDATE(NOW(), INTERVAL 10800 SECOND) AND jcJSL = '禁峰列车' AND dcXD = 'XD' ORDER BY dcStartTime";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new JFCXRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select JF error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> selectCX() {
        String sql = "SELECT distinct dcId, dcNumber, dcType, dcStartTime, jcJSL, jcImportant FROM jc_plan_detals D LEFT JOIN dc_show_data S ON D.jcNumber = S.dcNumber WHERE dcType = '接车' AND dcStartTime < ADDDATE(NOW(), INTERVAL 10800 SECOND) AND jcJSL = '超限列车' AND dcXD = 'XD' ORDER BY dcStartTime";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new JFCXRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select CX error");
            return null;
        }
    }

    @Override
    public boolean insertToPlanCopy(DcPlanInfo info) {
        String sql = "INSERT INTO dc_plan_copy (copyId, dcNumber, dcStartTime, dcEndTime, dcType, dcSource, dcDj, dcGJHID, dcZGBZ, dcCS, dcJSL, dcTFX, dcSWH, dcJLSJ,dcGDM) VALUES (?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] args = {
                uuid.uuidPrimaryKey(),
                info.getDcNumber(),
                info.getDcStartTime(),
                info.getDcEndTime(),
                info.getDcType(),
                info.getDcSource(),
                info.getDcDj(),
                info.getDcGJHID(),
                info.getDcZGBZ(),
                info.getDcCS(),
                info.getDcJSL(),
                info.getDcTFX(),
                info.getDcSWH(),
                info.getJLSJ(),
                info.getDcGDM()
        };
        try {
            return mysqlJdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("insert dc error");
            return false;
        }
    }


    @Override
    public boolean insertTcPlanNewAll(final List<DcPlanInfo> dcPlanInfos) {
        String sql = "INSERT INTO dc_tc_plan (tcId, dcNumber, dcStartTime, dcEndTime, dcType, dcSource, dcDestination, dcDj, dcTFX, dcTF, dcXD, dcDH, dcSWH, dcCS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int[] result = mysqlJdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                DcPlanInfo dcPlanInfo = dcPlanInfos.get(i);

                ps.setString(1, uuid.uuidPrimaryKey());
                ps.setString(2, ConstantFields.TYPE_TC);
                ps.setTimestamp(3, dcPlanInfo.getDcStartTime());
                ps.setTimestamp(4, dcPlanInfo.getDcEndTime());
                ps.setString(5, dcPlanInfo.getDcType());
                ps.setString(6,dcPlanInfo.getDcSource());
                ps.setString(7,dcPlanInfo.getDcDestination());
                ps.setInt(8,dcPlanInfo.getDcDj());
                ps.setString(9,dcPlanInfo.getDcTFX());
                ps.setString(10,dcPlanInfo.getDcTF());
                ps.setString(11,dcPlanInfo.getDcXD());
                ps.setString(12,dcPlanInfo.getDcDH());
                ps.setInt(13,dcPlanInfo.getDcSWH());
                ps.setInt(14,dcPlanInfo.getDcCS());

            }

            @Override
            public int getBatchSize() {
                return dcPlanInfos.size();
            }
        });

        return result.length == dcPlanInfos.size();

    }

    @Override
    public List<DcPlanInfo> processTcDataNew() {
        String sql = "SELECT tcId,dcDestination,dcSWH,dcCS FROM dc_tc_plan order by dcDestination,dcSWH";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new DcTcDataNewRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select error");
            return null;
        }
    }

    @Override
    public boolean insertTcDataNewAll(final List<DcPlanInfo> dcPlanInfos) {
        String sql = "INSERT INTO dc_tc_six (tcId, dcDestination, dcSWH, dcCS, dcCS6) VALUES (?, ?, ?, ?, ?)";
        int[] result = mysqlJdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                DcPlanInfo dcPlanInfo = dcPlanInfos.get(i);

                ps.setString(1, dcPlanInfo.getDcId());
                ps.setString(2, dcPlanInfo.getDcDestination());
                ps.setInt(3, dcPlanInfo.getDcSWH());
                ps.setInt(4, dcPlanInfo.getDcCS());
                ps.setInt(5, dcPlanInfo.getDcCS6());

            }

            @Override
            public int getBatchSize() {
                return dcPlanInfos.size();
            }
        });

        return result.length == dcPlanInfos.size();

    }

    @Override
    public List<DcPlanInfo> selectTcPlanNew() {
        String sql = "SELECT distinct dcNumber,dcStartTime,dcEndTime,dcType,dcSource,S.dcDestination,dcDj,dcTFX,dcTF,dcXD,dcDH,dcCS6 FROM dc_tc_plan P left join dc_tc_six S on P.tcId = S.tcId where dcCS6 = 1 ORDER BY dcDestination";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new DcTcPlanNewRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select tc data error");
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
    public int deleteDcShow() {
        String sql = "TRUNCATE TABLE dc_show_data";

        try {
            return mysqlJdbcTemplate.update(sql);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("delete dc show error");
            return 0;
        }
    }

    @Override
    public int deleteTcData() {
        String sql = "TRUNCATE TABLE dc_tc_plan";

        try {
            return mysqlJdbcTemplate.update(sql);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("delete dc tc plan error");
            return 0;
        }
    }

    @Override
    public int deleteTcDataSix() {
        String sql = "TRUNCATE TABLE dc_tc_six";

        try {
            return mysqlJdbcTemplate.update(sql);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("delete dc tc six error");
            return 0;
        }
    }

    @Override
    public int deleteTFCX() {
        String sql = "TRUNCATE TABLE jc_plan_detals";

        try {
            return mysqlJdbcTemplate.update(sql);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("delete tf cx error");
            return 0;
        }
    }

    @Override
    public int deleteDcCopy() {
        String sql = "TRUNCATE TABLE dc_plan_copy";

        try {
            return mysqlJdbcTemplate.update(sql);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("delete dc copy error");
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
    public int updateDestination(DcPlanInfo info) {
        String sql = "UPDATE dc_show_data SET dcDestination = ?, dcPath = ?, dcIsUpdate = 2 where dcId = ?";
        Object[] args = {
                info.getDcDestination(),
                info.getDcTypeE()+info.getDcDestination()+info.getDcSource(),
                info.getDcId()
        };
        try {
            return mysqlJdbcTemplate.update(sql,args);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("update destination error");
            return 0;
        }
    }

    @Override
    public int insertTextarea(String s) {
        String sql = "INSERT INTO dc_text (dcText) VALUES (?)";
        Object[] args = {
                s
        };
        try {
            return mysqlJdbcTemplate.update(sql,args);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("insert textarea error");
            return 0;
        }
    }

    @Override
    public List<TextareaInfo> selectTextareaInfo(TextareaInfo info) {
        String sql = "SELECT textId,dcText FROM dc_text where isSelected = 0";
        Object[] args = {
                info.getTextareaId(),
                info.getTextarea()
        };

        try {
            return mysqlJdbcTemplate.query(sql, args, new TextareaInfoRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select textareaInfo error");
            return null;
        }
    }

    @Override
    public int updateTextareaInfoIsSelected(int isS) {
        String sql = "UPDATE dc_text SET isSelected = 1 where textId = ?";
        Object[] args = {
                isS
        };
        try {
            return mysqlJdbcTemplate.update(sql,args);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("update isSelected error");
            return 0;
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
    public List<StatisticsInfo> selectStatisticsInfoWithTime(String time) {
        String sql = "SELECT DISTINCT logTime,data1,data2 FROM dc_statistics where logTime = ? ";
        Object[] args = {
                time
        };

        try {
            return mysqlJdbcTemplate.query(sql, args, new selectStatisticsInfoRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select selectStatisticsInfoWithTime error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> select4Partition() {
        String sql = "SELECT dcId,dcNumber,dcStartTime,dcEndTime,dcType,dcDestination,dcDH FROM ygz_show.dc_show_data where dcXD = 'XD' and dcTypeE = 'JC' AND dcStartTime > now() and dcStartTime < ADDDATE(NOW(), INTERVAL 10800 SECOND) order by dcStartTime ";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new selectPartitionRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select selectStatisticsInfoWithTime error");
            return null;
        }
    }

    private class TextareaInfoRowMapper implements RowMapper<TextareaInfo> {
        public TextareaInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            TextareaInfo userInfo = new TextareaInfo();

            userInfo.setTextareaId(resultSet.getInt("textId"));
            userInfo.setTextarea(resultSet.getString("dcText"));

            return userInfo;
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

    private class DcPlanCopyRowMapper implements RowMapper<DcPlanInfo> {
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

    private class DcCopyDataRowMapper implements RowMapper<DcPlanInfo> {
        public DcPlanInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            DcPlanInfo userInfo = new DcPlanInfo();

            userInfo.setDcGJHID(resultSet.getString("dcGJHID"));
            userInfo.setJLSJ(resultSet.getTimestamp("dcJLSJ"));

            return userInfo;
        }
    }

    private class DcJtPlanRowMapper implements RowMapper<DcPlanInfo> {
        public DcPlanInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            DcPlanInfo userInfo = new DcPlanInfo();

            userInfo.setDcNumber(resultSet.getString("dcNumber"));
            userInfo.setDcStartTime(resultSet.getTimestamp("dcStartTime"));
            userInfo.setDcEndTime(resultSet.getTimestamp("dcEndTime"));
            userInfo.setDcType(resultSet.getString("dcType"));
            userInfo.setDcSource(resultSet.getString("dcSource"));
            userInfo.setDcDj(resultSet.getInt("dcDj"));
            userInfo.setDcTFX(resultSet.getString("dcTFX"));

            return userInfo;
        }
    }

    private class DcZmPlanRowMapper implements RowMapper<DcPlanInfo> {
        public DcPlanInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            DcPlanInfo userInfo = new DcPlanInfo();

            userInfo.setDcNumber(resultSet.getString("dcNumber"));
            userInfo.setDcStartTime(resultSet.getTimestamp("dcStartTime"));
            userInfo.setDcEndTime(resultSet.getTimestamp("dcEndTime"));
            userInfo.setDcType(resultSet.getString("dcType"));
            userInfo.setDcSource(resultSet.getString("dcSource"));
            userInfo.setDcTFX(resultSet.getString("dcTFX"));
            userInfo.setDcDj(resultSet.getInt("dcDj"));

            return userInfo;
        }
    }

    private class DcTcPlanRowMapper implements RowMapper<DcPlanInfo> {
        public DcPlanInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            DcPlanInfo userInfo = new DcPlanInfo();

            userInfo.setDcNumber(ConstantFields.TYPE_TC);
            userInfo.setDcStartTime(resultSet.getTimestamp("dcStartTime"));
            userInfo.setDcEndTime(resultSet.getTimestamp("dcEndTime"));
            userInfo.setDcType(resultSet.getString("dcType"));
            userInfo.setDcDestination(resultSet.getString("dcSource"));
            userInfo.setDcTFX(resultSet.getString("dcTFX"));
            userInfo.setDcDj(resultSet.getInt("dcDj"));
            userInfo.setDcGJHID(resultSet.getString("dcGJHID"));
            userInfo.setDcSWH(resultSet.getInt("dcSWH"));
            userInfo.setDcZGBZ(resultSet.getString("dcZGBZ"));
            userInfo.setDcCS(resultSet.getInt("dcCS"));

            return userInfo;
        }
    }

    private class DcZcPlanRowMapper implements RowMapper<DcPlanInfo> {
        public DcPlanInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            DcPlanInfo userInfo = new DcPlanInfo();

            userInfo.setDcNumber(resultSet.getString("dcNumber"));
            userInfo.setDcStartTime(resultSet.getTimestamp("dcStartTime"));
            userInfo.setDcEndTime(resultSet.getTimestamp("dcEndTime"));
            userInfo.setDcType(resultSet.getString("dcType"));
            userInfo.setDcDestination(resultSet.getString("dcSource"));
            userInfo.setDcTFX(resultSet.getString("dcTFX"));
            userInfo.setDcDj(resultSet.getInt("dcDj"));
            userInfo.setDcGDM(resultSet.getString("dcGDM"));

            return userInfo;
        }
    }

    private class DcTcDataNewRowMapper implements RowMapper<DcPlanInfo> {
        public DcPlanInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            DcPlanInfo userInfo = new DcPlanInfo();

            userInfo.setDcId(resultSet.getString("tcId"));
            userInfo.setDcDestination(resultSet.getString("dcDestination"));
            userInfo.setDcSWH(resultSet.getInt("dcSWH"));
            userInfo.setDcCS(resultSet.getInt("dcCS"));

            return userInfo;
        }
    }

    private class DcTcPlanNewRowMapper implements RowMapper<DcPlanInfo> {
        public DcPlanInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            DcPlanInfo userInfo = new DcPlanInfo();

            userInfo.setDcNumber(resultSet.getString("dcNumber"));
            userInfo.setDcStartTime(resultSet.getTimestamp("dcStartTime"));
            userInfo.setDcEndTime(resultSet.getTimestamp("dcEndTime"));
            userInfo.setDcType(resultSet.getString("dcType"));
            userInfo.setDcSource(resultSet.getString("dcSource"));
            userInfo.setDcDestination(resultSet.getString("dcDestination"));
            userInfo.setDcDj(resultSet.getInt("dcDj"));
            userInfo.setDcTFX(resultSet.getString("dcTFX"));
            userInfo.setDcTF(resultSet.getString("dcTF"));
            userInfo.setDcXD(resultSet.getString("dcXD"));
            userInfo.setDcDH(resultSet.getString("dcDH"));
            userInfo.setDcCS6(resultSet.getInt("dcCS6"));
            userInfo.setDcTypeE(ConstantFields.TYPE_TC);

            return userInfo;
        }
    }

    @Override
    public boolean insertDcData(final List<DcPlanInfo> dcPlanInfos) {
        String sql ="insert dc_show_data(dcId,dcNumber,dcStartTime,dcEndTime,dcType,dcSource,dcDestination,dcDj,dcTFX,dcTF,dcXD,dcDH,dcPath,dcCS6,jcSumHc,dcTypeE) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        int[] result = mysqlJdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                DcPlanInfo dcPlanInfo = dcPlanInfos.get(i);

                ps.setString(1, uuid.uuidPrimaryKey());
                ps.setString(2, dcPlanInfo.getDcNumber());
                ps.setTimestamp(3, dcPlanInfo.getDcStartTime());
                ps.setTimestamp(4, dcPlanInfo.getDcEndTime());
                ps.setString(5, dcPlanInfo.getDcType());
                ps.setString(6,dcPlanInfo.getDcSource());
                ps.setString(7,dcPlanInfo.getDcDestination());
                ps.setInt(8,dcPlanInfo.getDcDj());
                ps.setString(9,dcPlanInfo.getDcTFX());
                ps.setString(10,dcPlanInfo.getDcTF());
                ps.setString(11,dcPlanInfo.getDcXD());
                ps.setString(12,dcPlanInfo.getDcDH());
                ps.setString(13,dcPlanInfo.getDcPath());
                ps.setInt(14,dcPlanInfo.getDcCS6());
                ps.setFloat(15,dcPlanInfo.getJcSumHc());
                ps.setString(16,dcPlanInfo.getDcTypeE());

            }

            @Override
            public int getBatchSize() {
                return dcPlanInfos.size();
            }
        });

        return result.length == dcPlanInfos.size();

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
            if (source != null && source.equals(ConstantFields.JCSOURCE)) {
                dcPlanInfo.setDcSource(ConstantFields.JCSOURCEC);
            } else if (source != null && source.equals(ConstantFields.ZCSOURCE)){
                dcPlanInfo.setDcSource(ConstantFields.ZCSOURCEC);
            } else if (source != null && source.equals(ConstantFields.QC)){
                dcPlanInfo.setDcSource(ConstantFields.TYPE_QCX);
            } else if (source != null && source.equals(ConstantFields.JD)){
                dcPlanInfo.setDcSource(ConstantFields.TYPE_JDX);
            } else {
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
                    } else {
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
                if (Integer.parseInt(resultset.getString("dcDH")) == 2 ||
                        Integer.parseInt(resultset.getString("dcDH")) == 3 ||
                        Integer.parseInt(resultset.getString("dcDH")) == 4) {
                    dcPlanInfo.setSelectList(dataProcess.bwjSelectList4S());
                } else {
                    dcPlanInfo.setSelectList(dataProcess.bwjSelectList4N());
                }
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

    private class JFCXRowMapper implements RowMapper<DcPlanInfo> {
        public DcPlanInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            DcPlanInfo userInfo = new DcPlanInfo();

            userInfo.setDcId(resultSet.getString("dcId"));
            userInfo.setDcNumber(resultSet.getString("dcNumber"));
            userInfo.setDcType(resultSet.getString("dcType"));
            userInfo.setDcDH(resultSet.getString("dcDH"));
            userInfo.setDcJSL(resultSet.getString("jcJSL"));
            userInfo.setDcImportant(resultSet.getString("jcImportant"));

            return userInfo;
        }
    }
}
