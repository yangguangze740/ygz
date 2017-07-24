package com.luju.ygz.test.repository.impl;

import com.luju.pojo.JcPlanInfo;
import com.luju.ygz.test.repository.TestRepository;
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
    public List<JcPlanInfo> selectJcPlan() {
        String sql = "SELECT jcNumber, jcSource, jcEndTime, jcStartTime, jcType, jcHc, jcQBID, jcQBIDN, jcJSL, jcDestination FROM ygz_show.jc_plan_copy ";
        Object[] args = {};

        try {
            return jdbcTemplate.query(sql, args, new JcPlanRowMapper());
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
            System.out.println(info.getJcStartTime());
            System.out.println(info.getJcType());
            System.out.println(info.getJcJSL());
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

    private class JcPlanRowMapper implements RowMapper<JcPlanInfo> {
        public JcPlanInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            JcPlanInfo userInfo = new JcPlanInfo();

            userInfo.setTRAIN_NUM(resultSet.getString("jcNumber"));
            userInfo.setNODE_FOUR_WAY(resultSet.getString("jcSource"));
            userInfo.setTIME(resultSet.getTimestamp("jcEndTime"));
            userInfo.setJcStartTime(resultSet.getTimestamp("jcStartTime"));
            userInfo.setJcType(resultSet.getString("jcType"));
            userInfo.setJcHc(resultSet.getFloat("jcHc"));
            userInfo.setJcQBID(resultSet.getString("jcQBID"));
            userInfo.setJcQBIDN(resultSet.getString("jcQBIDN"));
            userInfo.setTRACK_NUM(resultSet.getString("jcDestination"));
            userInfo.setJcJSL(resultSet.getString("jcJSL"));

            return userInfo;
        }
    }
}
