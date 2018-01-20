package com.luju.ygz.fc.repository.impl;

import com.luju.pojo.DcPlanInfo;
import com.luju.pojo.FcPlanInfo;
import com.luju.ygz.fc.repository.FcRepositoryI;
import luju.common.util.ConstantFields;
import luju.common.util.PrimaryKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FcRepositoryImpl implements FcRepositoryI {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private JdbcTemplate mysqlJdbcTemplate;

    PrimaryKeyUtil uuid = new PrimaryKeyUtil();

    @Override
    public List<FcPlanInfo> selectFcPlanFromOra() {
        String sql = "SELECT TRAIN_NUM, NODE_FOUR_WAY, TIME, TRACK_NUM ,PK,JSL ,QBID, HC FROM V_CZD_FJH V LEFT JOIN (SELECT CC, MAX(QBID) Q FROM CCH_FBML M GROUP BY CC) N ON V.TRAIN_NUM = N.CC LEFT JOIN CCH_FBZW Z ON N.Q = Z.QBID WHERE V.TRAIN_NUM IS NOT NULL AND (V.NODE_FOUR_WAY = '马三家' OR V.NODE_FOUR_WAY = '于洪' OR V.NODE_FOUR_WAY = '转湾桥' OR V.NODE_FOUR_WAY = '大成')";
        Object[] args = {};

        try {
            return jdbcTemplate.query(sql, args, new FcPlanCopyRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select jc data from ora error");
            return null;
        }
    }

    @Override
    public int deletePlanCopy() {
        String sql = "TRUNCATE TABLE fc_plan_copy";

        try {
            return mysqlJdbcTemplate.update(sql);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("delete fc error");
            return 0;
        }
    }


    @Override
    public boolean insertFcData(final List<FcPlanInfo> fcPlanInfos) {
        String sql = "INSERT INTO fc_plan_copy (copyId, fcSource, fcNumber, fcEndTime, fcStartTime, fcDestination, fcHc, fcQBID, fcQBIDN, fcJSL, fcType ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int[] result = mysqlJdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                FcPlanInfo fcPlanInfo = fcPlanInfos.get(i);

                ps.setString(1, uuid.uuidPrimaryKey());
                ps.setString(2, fcPlanInfo.getFcSource());
                ps.setString(3, fcPlanInfo.getFcNumber());
                ps.setTimestamp(4, fcPlanInfo.getFcEndTime());
                ps.setTimestamp(5, fcPlanInfo.getFcStartTime());
                ps.setString(6,fcPlanInfo.getFcDestination());
                ps.setFloat(7,fcPlanInfo.getFcHc());
                ps.setString(8,fcPlanInfo.getFcQBID());
                ps.setString(9,fcPlanInfo.getFcQBIDN());
                ps.setString(10,fcPlanInfo.getFcJSL());
                ps.setString(11,fcPlanInfo.getFcType());

            }

            @Override
            public int getBatchSize() {
                return fcPlanInfos.size();
            }
        });

        return result.length == fcPlanInfos.size();
    }

    @Override
    public List<DcPlanInfo> sfbzNumberList() {
        String sql = "SELECT distinct dcSource FROM ygz_show.dc_plan_copy where dcType='编组' and dcSource like '%F%'";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new sfbzNumberRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select bian zu error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> sfbz(String number) {
        String sql = "SELECT dcNumber,dcSource,dcStartTime,dcEndTime,dcType,dcDj FROM dc_plan_copy where dcType = '编组' and dcSource = ?";
        Object[] args = {
            number
        };

        try {
            return mysqlJdbcTemplate.query(sql, args, new sfbzRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select bz error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> sftcNumberList() {
        String sql = "SELECT distinct dcSource FROM ygz_show.dc_plan_copy where dcType = '挑车' and dcSource like '%F%';";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new sftcNumberRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select bian zu error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> sftc(String source) {
        String sql = "SELECT dcNumber,dcSource,dcStartTime,dcEndTime,dcType,dcDj FROM dc_plan_copy where dcType = '挑车' and dcSource = ?";
        Object[] args = {
                source
        };

        try {
            return mysqlJdbcTemplate.query(sql, args, new sftcRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select tc error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> sfzcList() {
        String sql = "SELECT distinct dcNumber,dcSource,dcStartTime,dcEndTime,dcType,dcDj FROM dc_plan_copy where dcType = '转场' and dcSource like 'SB%' order by dcNumber;";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new sfzcDataRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select zhuan chang error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> sfzmList() {
    String sql = "SELECT distinct dcNumber,dcSource,dcStartTime,dcEndTime,dcType,dcDj FROM dc_plan_copy where dcType = '摘帽' and dcSource like '%F%' order by dcNumber;";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new sfzmDataRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select zhai mao error");
            return null;
        }
    }

    @Override
    public boolean insertDcData(final List<DcPlanInfo> dcPlanInfos) {
        String sql ="insert dc_show_data(dcId,dcNumber,dcStartTime,dcEndTime,dcType,dcSource,dcDestination,dcDj,dcPath) VALUES(?,?,?,?,?,?,?,?,?)";
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
                ps.setString(7,ConstantFields.S);
                ps.setInt(8,dcPlanInfo.getDcDj());
                ps.setString(9,dcPlanInfo.getDcPath());

            }

            @Override
            public int getBatchSize() {
                return dcPlanInfos.size();
            }
        });

        return result.length == dcPlanInfos.size();

    }

    private class FcPlanCopyRowMapper implements RowMapper<FcPlanInfo> {
        public FcPlanInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            FcPlanInfo userInfo = new FcPlanInfo();

            String source = resultSet.getString("NODE_FOUR_WAY");
            if (source.equals("马三家")) {
                userInfo.setFcSource(ConstantFields.JCSOURCE);
            }
            if (source.equals("大成")) {
                userInfo.setFcSource(ConstantFields.DC);
            }
            if (source.equals("于洪")) {
                userInfo.setFcSource(ConstantFields.YH);
            }
            if (source.equals("转湾桥")) {
                userInfo.setFcSource(ConstantFields.ZWQ);
            }
            userInfo.setFcDestination(resultSet.getString("TRACK_NUM"));
            userInfo.setFcNumber(resultSet.getString("TRAIN_NUM"));
            userInfo.setFcEndTime(resultSet.getTimestamp("TIME"));
            userInfo.setFcQBID(resultSet.getString("QBID"));
            userInfo.setFcHc(resultSet.getFloat("HC"));
            userInfo.setFcQBIDN(resultSet.getString("PK"));
            userInfo.setFcJSL(resultSet.getString("JSL"));

            return userInfo;
        }
    }

    private class sfbzNumberRowMapper implements RowMapper<DcPlanInfo> {
        public DcPlanInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            DcPlanInfo userInfo = new DcPlanInfo();

            userInfo.setDcSource(resultSet.getString("dcSource"));

            return userInfo;
        }
    }

    private class sfbzRowMapper implements RowMapper<DcPlanInfo> {
        public DcPlanInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            DcPlanInfo userInfo = new DcPlanInfo();

            userInfo.setDcNumber(resultSet.getString("dcNumber"));
            userInfo.setDcSource(resultSet.getString("dcSource"));
            userInfo.setDcStartTime(resultSet.getTimestamp("dcStartTime"));
            userInfo.setDcEndTime(resultSet.getTimestamp("dcEndTime"));
            userInfo.setDcType(resultSet.getString("dcType"));
            userInfo.setDcDj(resultSet.getInt("dcDj"));

            return userInfo;
        }
    }

    private class sftcNumberRowMapper implements RowMapper<DcPlanInfo> {
        public DcPlanInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            DcPlanInfo userInfo = new DcPlanInfo();

            userInfo.setDcSource(resultSet.getString("dcSource"));

            return userInfo;
        }
    }

    private class sftcRowMapper implements RowMapper<DcPlanInfo> {
        public DcPlanInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            DcPlanInfo userInfo = new DcPlanInfo();

            userInfo.setDcNumber("挑车");
            userInfo.setDcSource(resultSet.getString("dcSource"));
            userInfo.setDcStartTime(resultSet.getTimestamp("dcStartTime"));
            userInfo.setDcEndTime(resultSet.getTimestamp("dcEndTime"));
            userInfo.setDcType(resultSet.getString("dcType"));
            userInfo.setDcDj(resultSet.getInt("dcDj"));

            return userInfo;
        }
    }

    private class sfzcDataRowMapper implements RowMapper<DcPlanInfo> {
        public DcPlanInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            DcPlanInfo userInfo = new DcPlanInfo();

            userInfo.setDcNumber(resultSet.getString("dcNumber"));
            userInfo.setDcSource(resultSet.getString("dcSource"));
            userInfo.setJLSJ(resultSet.getTimestamp("dcStartTime"));
            userInfo.setJLSJ(resultSet.getTimestamp("dcEndTime"));
            userInfo.setDcType(resultSet.getString("dcType"));
            userInfo.setDcDj(resultSet.getInt("dcDj"));

            return userInfo;
        }
    }

    private class sfzmDataRowMapper implements RowMapper<DcPlanInfo> {
        public DcPlanInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            DcPlanInfo userInfo = new DcPlanInfo();

            userInfo.setDcNumber(resultSet.getString("dcNumber"));
            userInfo.setDcSource(resultSet.getString("dcSource"));
            userInfo.setJLSJ(resultSet.getTimestamp("dcStartTime"));
            userInfo.setJLSJ(resultSet.getTimestamp("dcEndTime"));
            userInfo.setDcType(resultSet.getString("dcType"));
            userInfo.setDcDj(resultSet.getInt("dcDj"));

            return userInfo;
        }
    }

}
