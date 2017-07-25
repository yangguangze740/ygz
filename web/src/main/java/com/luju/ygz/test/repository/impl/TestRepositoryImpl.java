package com.luju.ygz.test.repository.impl;

import com.luju.pojo.JcPlanInfo;
import com.luju.ygz.test.repository.TestRepository;
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
public class TestRepositoryImpl implements TestRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private JdbcTemplate mysqlJdbcTemplate;

    PrimaryKeyUtil uuid = new PrimaryKeyUtil();

    @Override
    public List<JcPlanInfo> selectJcPlanFromOra() {
        String sql = "SELECT TRAIN_NUM, NODE_FOUR_WAY, TIME, TRACK_NUM ,PK,JSL ,QBID, HC FROM V_CZD_DJH V LEFT JOIN (SELECT CC, MAX(QBID) Q FROM CCH_YSDBML M GROUP BY CC) N ON V.TRAIN_NUM = N.CC LEFT JOIN CCH_YSDBZW Z ON N.Q = Z.QBID WHERE V.TRAIN_NUM IS NOT NULL AND V.NODE_FOUR_WAY = '马三家'";
        Object[] args = {};

        try {
            return jdbcTemplate.query(sql, args, new JcPlanCopyRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
            return null;
        }
    }

    @Override
    public List<JcPlanInfo> selectJcPlan4One() {
        String sql = "SELECT jcNumber, sum(jcHc) S,jcSource, jcEndTime, jcStartTime, jcDestination, jcType FROM ygz_show.jc_plan_copy group by jcNumber,jcSource, jcEndTime, jcStartTime, jcDestination, jcType";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new JcPlanOneRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
            return null;
        }
    }

    @Override
    public List<JcPlanInfo> selectJcPlan4HC() {
        String sql = "SELECT distinct jcNumber, jcHc FROM ygz_show.jc_plan_copy where jcHc >= 2.4";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new JcPlanHCRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
            return null;
        }
    }

    @Override
    public List<JcPlanInfo> selectJcPlan4JF() {
        String sql = "SELECT jcNumber,jcJSL FROM ygz_show.jc_plan_copy where jcJSL like '%禁峰%' OR jcJSL like '%工程车%' OR jcJSL like '%客体车%' OR jcJSL like '%地铁%'";
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
        String sql = "SELECT distinct jcNumber FROM ygz_show.jc_plan_copy where jcJSL like '%超限%'";
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
    public List<JcPlanInfo> selectJcPlan4CC() {
        String sql = "SELECT jcNumber,jcSumHc FROM ygz_show.jc_plan where jcSumHc > 84;";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new JcPlanCCRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
            return null;
        }
    }

    @Override
    public List<JcPlanInfo> selectJcPlanALL() {
        String sql = "SELECT P.jcNumber,jcType,jcStartTime,jcEndTime,jcSource,jcDestination,jcXD,jcDH,jcJSL FROM ygz_show.jc_plan P left join (SELECT distinct jcNumber,jcJSL FROM ygz_show.jc_plan_detals) D on P.jcNumber = D.jcNumber order by jcStartTime";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new JcPlanALLRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
            return null;
        }
    }

    @Override
    public List<JcPlanInfo> selectJcPath() {
        String sql = "SELECT jcNumber,I.jcPath, jcDCH, jcType, jcDestination FROM ygz_show.jc_path_info I left join (SELECT jcNumber,jcType,jcDestination,jcPath FROM ygz_show.jc_plan) P on P.jcPath=CONVERT(I.jcPath USING utf8) COLLATE utf8_general_ci WHERE jcType = '接车';";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new JcPathRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
            return null;
        }
    }

    @Override
    public boolean insertToPlanCopy(JcPlanInfo info) {
        String sql = "INSERT INTO jc_plan_copy (copyId, jcSource, jcNumber, jcEndTime, jcStartTime, jcDestination, jcHc, jcQBID, jcQBIDN, jcJSL, jcType ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] args = {
                uuid.uuidPrimaryKey(),
                info.getNODE_FOUR_WAY(),
                info.getTRAIN_NUM(),
                info.getTIME(),
                info.getJcStartTime(),
                info.getTRACK_NUM(),
                info.getJcHc(),
                info.getJcQBID(),
                info.getJcQBIDN(),
                info.getJcJSL(),
                info.getJcType()
        };

        try {
            return mysqlJdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean insertToPlan4One(JcPlanInfo info) {
        String sql = "INSERT INTO jc_plan (jcNumber, jcSource, jcEndTime, jcStartTime, jcDestination, jcType, jcSumHc, jcXD, jcDH, jcPath) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] args = {

                info.getTRAIN_NUM(),
                info.getNODE_FOUR_WAY(),
                info.getTIME(),
                info.getJcStartTime(),
                info.getTRACK_NUM(),
                info.getJcType(),
                info.getJcSumHc(),
                info.getJcXD(),
                info.getJcDH(),
                ConstantFields.J+info.getTRACK_NUM()
        };

        try {
            return mysqlJdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean insertToPlan4HC(JcPlanInfo info) {
        String sql = "INSERT INTO jc_plan_detals (delalsId, jcNumber, jcJSL) VALUES (?, ?, ?)";
        Object[] args = {

                uuid.uuidPrimaryKey(),
                info.getTRAIN_NUM(),
                ConstantFields.JF
        };

        try {
            return mysqlJdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean insertToPlan4JF(JcPlanInfo info) {
        String sql = "INSERT INTO jc_plan_detals (delalsId, jcNumber, jcJSL) VALUES (?, ?, ?)";
        Object[] args = {

                uuid.uuidPrimaryKey(),
                info.getTRAIN_NUM(),
                ConstantFields.JF
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
        String sql = "INSERT INTO jc_plan_detals (delalsId, jcNumber, jcJSL) VALUES (?, ?, ?)";
        Object[] args = {
                uuid.uuidPrimaryKey(),
                info.getTRAIN_NUM(),
                ConstantFields.CX
        };

        try {
            return mysqlJdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean insertToPlan4CC(JcPlanInfo info) {
        String sql = "INSERT INTO jc_plan_detals (delalsId, jcNumber, jcJSL) VALUES (?, ?, ?)";
        Object[] args = {
                uuid.uuidPrimaryKey(),
                info.getTRAIN_NUM(),
                ConstantFields.CC
        };

        try {
            return mysqlJdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private class JcPlanCopyRowMapper implements RowMapper<JcPlanInfo> {
        public JcPlanInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            JcPlanInfo userInfo = new JcPlanInfo();

            userInfo.setNODE_FOUR_WAY(resultSet.getString("NODE_FOUR_WAY"));
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

    private class JcPlanOneRowMapper implements RowMapper<JcPlanInfo> {
        public JcPlanInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            JcPlanInfo userInfo = new JcPlanInfo();

            userInfo.setTRAIN_NUM(resultSet.getString("jcNumber"));
            userInfo.setNODE_FOUR_WAY(resultSet.getString("jcSource"));
            userInfo.setTIME(resultSet.getTimestamp("jcEndTime"));
            userInfo.setJcStartTime(resultSet.getTimestamp("jcStartTime"));
            userInfo.setTRACK_NUM(resultSet.getString("jcDestination"));
            userInfo.setJcType(resultSet.getString("jcType"));
            userInfo.setJcSumHc(resultSet.getFloat("S"));

            return userInfo;
        }
    }

    private class JcPlanHCRowMapper implements RowMapper<JcPlanInfo> {
        public JcPlanInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            JcPlanInfo userInfo = new JcPlanInfo();

            userInfo.setTRAIN_NUM(resultSet.getString("jcNumber"));
            userInfo.setJcHc(resultSet.getFloat("jcHc"));

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

    private class JcPlanCCRowMapper implements RowMapper<JcPlanInfo> {
        public JcPlanInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            JcPlanInfo userInfo = new JcPlanInfo();

            userInfo.setTRAIN_NUM(resultSet.getString("jcNumber"));
            userInfo.setJcSumHc(resultSet.getFloat("jcSumHc"));

            return userInfo;
        }
    }

    private class JcPlanALLRowMapper implements RowMapper<JcPlanInfo> {
        public JcPlanInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            JcPlanInfo userInfo = new JcPlanInfo();

            userInfo.setTRAIN_NUM(resultSet.getString("jcNumber"));
            userInfo.setJcType(resultSet.getString("jcType"));
            userInfo.setJcStartTime(resultSet.getTimestamp("jcStartTime"));
            userInfo.setTIME(resultSet.getTimestamp("jcEndTime"));
            userInfo.setNODE_FOUR_WAY(resultSet.getString("jcSource"));
            userInfo.setTRACK_NUM(resultSet.getString("jcDestination"));
            userInfo.setJcXD(resultSet.getString("jcXD"));
            userInfo.setJcDH(resultSet.getString("jcDH"));
            userInfo.setJcJSL(resultSet.getString("jcJSL"));

            return userInfo;
        }

    }

    private class JcPathRowMapper implements RowMapper<JcPlanInfo> {
        public JcPlanInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            JcPlanInfo userInfo = new JcPlanInfo();

            userInfo.setTRAIN_NUM(resultSet.getString("jcNumber"));
            userInfo.setJcPath(resultSet.getString("jcPath"));
            userInfo.setJcType(resultSet.getString("jcType"));
            userInfo.setTRACK_NUM(resultSet.getString("jcDestination"));
            userInfo.setJcDCH(resultSet.getString("jcDCH"));

            return userInfo;
        }

    }
}
