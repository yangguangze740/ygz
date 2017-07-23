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
    public List<JcPlanInfo> selectJcPlan() {
        String sql = "SELECT TRAIN_NUM ,TIME ,NODE_FOUR_WAY ,TRACK_NUM FROM V_CZD_DJH";
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
    public List<JcPlanInfo> selectJcZW() {
        String sql = "SELECT TRAIN_NUM ,TIME ,NODE_FOUR_WAY ,RACK_NUM FROM V_CZD_DJH";
        Object[] args = {};

        try {
            return jdbcTemplate.query(sql, args, new JcZWRowMapper());
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    @Override
    public boolean insertToPlanCopy(JcPlanInfo info) {
        String sql = "INSERT INTO jc_plan_copy (copyId, jcSource, jcNumber, jcEndTime, jcDestination, jcSumHc, jcQBID ) VALUES (?, ?, ?, ?, ?, ?, ?,)";
        Object[] args = {
                uuid.uuidPrimaryKey(),
                info.getNODE_FOUR_WAY(),
                info.getTRAIN_NUM(),
                info.getTIME(),
                info.getTRACK_NUM(),
                info.getJcSumHc(),
                info.getJcQBID()
        };

        try {
            return mysqlJdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean insertToZWCopy(JcPlanInfo info) {
        String sql = "INSERT INTO jc_zw_copy (zwCopyId, jc_QBID, jc_QBIDN, jc_Hc, jc_JSL) VALUES (?, ?, ?, ?, ?)";
        Object[] args = {
                uuid.uuidPrimaryKey(),
                info.getJcQBID(),
                info.getJcQBIDN(),
                info.getJcHc(),
                info.getJcJSL()
        };

        try {
            return mysqlJdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



    private class JcPlanRowMapper implements RowMapper<JcPlanInfo> {
        public JcPlanInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            JcPlanInfo userInfo = new JcPlanInfo();

            userInfo.setNODE_FOUR_WAY(resultSet.getString("NODE_FOUR_WAY"));
            userInfo.setTRACK_NUM(resultSet.getString("TRACK_NUM"));
            userInfo.setTRAIN_NUM(resultSet.getString("TRAIN_NUM"));
            userInfo.setTIME(resultSet.getTimestamp("TIME"));
//            userInfo.setJcQBID(resultSet.getString("JcQBID"));
//            userInfo.setJcSumHc(resultSet.getFloat("HC"));

            System.out.println(resultSet.getString("NODE_FOUR_WAY"));
            System.out.println(resultSet.getString("TRAIN_NUM"));
            System.out.println(resultSet.getTimestamp("TIME"));
            System.out.println(resultSet.getString("TRACK_NUM"));

            return userInfo;
        }
    }

    private class JcZWRowMapper implements RowMapper<JcPlanInfo> {
        public JcPlanInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            JcPlanInfo userInfo = new JcPlanInfo();

            userInfo.setJcQBIDN(resultSet.getString("PK"));
            userInfo.setJcHc(resultSet.getFloat("HC"));
            userInfo.setJcJSL(resultSet.getString("JSL"));

            return userInfo;
        }
    }
}
