package com.luju.ygz.test.repository.impl;

import com.luju.pojo.DcPlanInfo;
import com.luju.pojo.JcPlanInfo;
import com.luju.ygz.test.repository.TestRepositoryI;
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
public class TestRepositoryImpl implements TestRepositoryI {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private JdbcTemplate mysqlJdbcTemplate;

    PrimaryKeyUtil uuid = new PrimaryKeyUtil();

    @Override
    public List<JcPlanInfo> selectJcPlanFromOra() {
        String sql = "SELECT TRAIN_NUM, NODE_FOUR_WAY, TIME, TRACK_NUM ,PK,JSL ,QBID, HC FROM V_CZD_DJH V LEFT JOIN (SELECT CC, MAX(QBID) Q FROM CCH_YSDBML M GROUP BY CC) N ON V.TRAIN_NUM = N.CC LEFT JOIN CCH_YSDBZW Z ON N.Q = Z.QBID WHERE V.TRAIN_NUM IS NOT NULL AND (V.NODE_FOUR_WAY = '马三家' OR V.NODE_FOUR_WAY = '于洪' OR V.NODE_FOUR_WAY = '转湾桥' OR V.NODE_FOUR_WAY = '大成')";
        Object[] args = {};

        try {
            return jdbcTemplate.query(sql, args, new JcPlanCopyRowMapper());
        } catch (Exception e) {
            e.printStackTrace();

            System.out.println("select jc data from ora error");
            return null;
        }
    }


    @Override
    public List<JcPlanInfo> selectJcPlan4JF() {
        String sql = "SELECT jcNumber,jcJSL FROM ygz_show.jc_plan_copy where jcStartTime < ADDDATE(NOW(), INTERVAL 10800 SECOND) AND jcHc>2.4 OR jcJSL like '%禁峰%' OR jcJSL like '%工程车%' OR jcJSL like '%客体车%' OR jcJSL like '%地铁%'";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new JcPlanJSLRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
            return null;
        }
    }

    @Override
    public List<JcPlanInfo> selectJcPlan4CX() {
        String sql = "SELECT distinct jcNumber FROM ygz_show.jc_plan_copy where jcStartTime < ADDDATE(NOW(), INTERVAL 10800 SECOND) AND jcJSL like '%超限%'";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new JcPlanCXRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
            return null;
        }
    }


    @Override
    public boolean insertJcData(final List<JcPlanInfo> jcPlanInfos) {
        String sql = "INSERT INTO jc_plan_copy (copyId, jcSource, jcNumber, jcEndTime, jcStartTime, jcDestination, jcHc, jcQBID, jcQBIDN, jcJSL, jcType ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int[] result = mysqlJdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                JcPlanInfo jcPlanInfo = jcPlanInfos.get(i);

                ps.setString(1, uuid.uuidPrimaryKey());
                ps.setString(2, jcPlanInfo.getNODE_FOUR_WAY());
                ps.setString(3, jcPlanInfo.getTRAIN_NUM());
                ps.setTimestamp(4, jcPlanInfo.getTIME());
                ps.setTimestamp(5, jcPlanInfo.getJcStartTime());
                ps.setString(6,jcPlanInfo.getTRACK_NUM());
                ps.setFloat(7,jcPlanInfo.getJcHc());
                ps.setString(8,jcPlanInfo.getJcQBID());
                ps.setString(9,jcPlanInfo.getJcQBIDN());
                ps.setString(10,jcPlanInfo.getJcJSL());
                ps.setString(11,jcPlanInfo.getJcType());

            }

            @Override
            public int getBatchSize() {
                return jcPlanInfos.size();
            }
        });

        return result.length == jcPlanInfos.size();
    }


    @Override
    public boolean insertToPlan4JF(JcPlanInfo info) {
        String sql = "INSERT INTO jc_plan_detals (delalsId, jcNumber, jcJSL, jcImportant) VALUES (?, ?, ?, ?)";
        Object[] args = {

                uuid.uuidPrimaryKey(),
                info.getTRAIN_NUM(),
                ConstantFields.JF,
                ConstantFields.IMPORTANT_JF
        };

        try {
            return mysqlJdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean insertToPlan4CX(JcPlanInfo info) {
        String sql = "INSERT INTO jc_plan_detals (delalsId, jcNumber, jcJSL, jcImportant) VALUES (?, ?, ?, ?)";
        Object[] args = {
                uuid.uuidPrimaryKey(),
                info.getTRAIN_NUM(),
                ConstantFields.CX,
                ConstantFields.IMPORTANT_CX
        };

        try {
            return mysqlJdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public int deletePlanCopy() {
        String sql = "TRUNCATE TABLE jc_plan_copy";

        try {
            return mysqlJdbcTemplate.update(sql);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("delete jc error");
            return 0;
        }
    }

    @Override
    public List<DcPlanInfo> selectBwjPlanNew() {
        String sql = "SELECT dcNumber,dcStartTime,dcEndTime,dcDestination,dcXD,dcDH FROM ygz_show.dc_show_data where dcType='接车' AND dcDestination LIKE 'S%'order by dcStartTime;";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new BwjPlanNewRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> selectXxBwjPlanNew() {
        String sql = "SELECT dcNumber,dcStartTime,dcEndTime,dcDestination,dcXD,dcDH FROM ygz_show.dc_show_data where dcType='接车' AND dcDestination LIKE 'X%'order by dcStartTime;";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new BwjPlanNewRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> selectJcPlanNew() {
        String sql = "SELECT jcNumber, sum(jcHc) S,jcSource, jcEndTime, jcStartTime, jcDestination, jcType FROM jc_plan_copy WHERE jcSource = 'MSJ' group by jcNumber,jcSource, jcEndTime, jcStartTime, jcDestination, jcType";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new JcPlanNewRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
            return null;
        }
    }
    @Override
    public List<DcPlanInfo> selectZwqJcPlanNew() {
        String sql = "SELECT jcNumber, sum(jcHc) S,jcSource, jcEndTime, jcStartTime, jcDestination, jcType FROM jc_plan_copy WHERE jcSource = 'ZWQ' group by jcNumber,jcSource, jcEndTime, jcStartTime, jcDestination, jcType";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new JcPlanNewRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> selectDcJcPlanNew() {
        String sql = "SELECT jcNumber, sum(jcHc) S,jcSource, jcEndTime, jcStartTime, jcDestination, jcType FROM jc_plan_copy WHERE jcSource = 'DC' group by jcNumber,jcSource, jcEndTime, jcStartTime, jcDestination, jcType";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new JcPlanNewRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> selectYhJcPlanNew() {
        String sql = "SELECT jcNumber, sum(jcHc) S,jcSource, jcEndTime, jcStartTime, jcDestination, jcType FROM jc_plan_copy WHERE jcSource = 'YH' group by jcNumber,jcSource, jcEndTime, jcStartTime, jcDestination, jcType";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new JcPlanNewRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
            return null;
        }
    }

    private class JcPlanCopyRowMapper implements RowMapper<JcPlanInfo> {
        public JcPlanInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            JcPlanInfo userInfo = new JcPlanInfo();

            String source = resultSet.getString("NODE_FOUR_WAY");
            if (source.equals("马三家")) {
                userInfo.setNODE_FOUR_WAY(ConstantFields.JCSOURCE);
            }
            if (source.equals("大成")) {
                userInfo.setNODE_FOUR_WAY(ConstantFields.DC);
            }
            if (source.equals("于洪")) {
                userInfo.setNODE_FOUR_WAY(ConstantFields.YH);
            }
            if (source.equals("转湾桥")) {
                userInfo.setNODE_FOUR_WAY(ConstantFields.ZWQ);
            }
            userInfo.setTRACK_NUM(resultSet.getString("TRACK_NUM"));
            userInfo.setTRAIN_NUM(resultSet.getString("TRAIN_NUM"));
            userInfo.setTIME(resultSet.getTimestamp("TIME"));
            userInfo.setJcQBID(resultSet.getString("QBID"));
            userInfo.setJcHc(resultSet.getFloat("HC"));
            userInfo.setJcQBIDN(resultSet.getString("PK"));
            userInfo.setJcJSL(resultSet.getString("JSL"));

            return userInfo;
        }
    }

    private class JcPlanJSLRowMapper implements RowMapper<JcPlanInfo> {
        public JcPlanInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            JcPlanInfo userInfo = new JcPlanInfo();

            userInfo.setTRAIN_NUM(resultSet.getString("jcNumber"));
            userInfo.setJcJSL(resultSet.getString("jcJSL"));

            return userInfo;
        }
    }

    private class JcPlanCXRowMapper implements RowMapper<JcPlanInfo> {
        public JcPlanInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            JcPlanInfo userInfo = new JcPlanInfo();

            userInfo.setTRAIN_NUM(resultSet.getString("jcNumber"));

            return userInfo;
        }
    }

    private class BwjPlanNewRowMapper implements RowMapper<DcPlanInfo>{
        public DcPlanInfo mapRow(ResultSet resultSet,int i)throws SQLException{
            DcPlanInfo userInfo = new DcPlanInfo();

            userInfo.setDcNumber(resultSet.getString("dcNumber"));
            userInfo.setDcEndTime(resultSet.getTimestamp("dcEndTime"));
            userInfo.setDcSource(resultSet.getString("dcDestination"));
            userInfo.setDcXD(resultSet.getString("dcXD"));
            userInfo.setDcDH(resultSet.getString("dcDH"));

            return userInfo;
        }
    }

    private class JcPlanNewRowMapper implements RowMapper<DcPlanInfo> {
        public DcPlanInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            DcPlanInfo userInfo = new DcPlanInfo();

            userInfo.setDcNumber(resultSet.getString("jcNumber"));
            userInfo.setDcSource(resultSet.getString("jcSource"));
            userInfo.setDcEndTime(resultSet.getTimestamp("jcEndTime"));
            userInfo.setDcStartTime(resultSet.getTimestamp("jcStartTime"));
            if (resultSet.getString("jcDestination")!= null){
                userInfo.setDcDestination(resultSet.getString("jcDestination"));
            }
            userInfo.setDcType(resultSet.getString("jcType"));
            userInfo.setJcSumHc(resultSet.getFloat("S"));

            return userInfo;
        }
    }
}
