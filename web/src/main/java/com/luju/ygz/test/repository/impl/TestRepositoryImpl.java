package com.luju.ygz.test.repository.impl;

import com.luju.pojo.DcPlanInfo;
import com.luju.pojo.JcPlanInfo;
import com.luju.pojo.ResultInfo;
import com.luju.ygz.test.repository.TestRepositoryI;
import luju.common.util.ConstantFields;
import luju.common.util.PrimaryKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public List<ResultInfo> selectJcPlan4XD() {
        String sql = "SELECT P.jcNumber,jcType,jcStartTime,jcEndTime,jcSource,jcDestination,jcXD,jcDH,jcPath,GROUP_CONCAT(jcImportant) T,GROUP_CONCAT(jcJSL) J FROM jc_plan P LEFT JOIN (SELECT DISTINCT jcNumber, jcJSL, jcImportant FROM jc_plan_detals) D ON P.jcNumber = D.jcNumber WHERE jcType = '接车' AND jcXD = 'XD' GROUP BY jcNumber , jcType, jcStartTime,jcEndTime,jcSource,jcDestination,jcXD,jcDH,jcPath ORDER BY jcStartTime";
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
    public List<JcPlanInfo> selectBwjPlan() {
        String sql = "SELECT jcNumber,jcType,jcEndTime,jcXD,jcDH,jcDestination FROM jc_plan";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new BwjPlanCopyRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
            return null;
        }
    }

    @Override
    public List<ResultInfo> selectBwjData() {
        String sql = "SELECT jcNumber,jcType,jcStartTime,jcEndTime,jcSource FROM bwj_plan where jcXD = 'XD' group by jcNumber,jcType,jcStartTime,jcEndTime,jcSource order by jcStartTime";
        Object[] args = {};

        try{
            return mysqlJdbcTemplate.query(sql,args,new BwPlanAllRowMapper());
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("error");
            return null;
        }
    }

    @Override
    public List<ResultInfo> selectBwjDataInPath(JcPlanInfo jcPlanInfo) {
        String sql = "SELECT jcNumber,jcDestination,jcPath FROM bwj_plan where jcNumber = ? and jcDestination = ?";
        Object[] args = {
                jcPlanInfo.getTRAIN_NUM(),
                jcPlanInfo.getTRACK_NUM()
        };

        try{
            return mysqlJdbcTemplate.query(sql,args,new BwjPlanInPathRowMapper());
        }
        catch (Exception e){
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
        String sql = "INSERT INTO jc_plan (jcId, jcNumber, jcSource, jcEndTime, jcStartTime, jcDestination, jcType, jcSumHc, jcXD, jcDH, jcPath) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] args = {

                uuid.uuidPrimaryKey(),
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
    public boolean insertToPlan4CC(JcPlanInfo info) {
        String sql = "INSERT INTO jc_plan_detals (delalsId, jcNumber, jcJSL, jcImportant) VALUES (?, ?, ?, ?)";
        Object[] args = {
                uuid.uuidPrimaryKey(),
                info.getTRAIN_NUM(),
                ConstantFields.CC,
                ConstantFields.IMPORTANT_CC
        };

        try {
            return mysqlJdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean insertToBwjPlan4S(JcPlanInfo info) {
        String sql = "INSERT INTO bwj_plan (planId, jcNumber, jcSource, jcStartTime, jcEndTime, jcDestination, jcType, jcXD, jcDH,jcPath) VALUES (?,?,?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] args = {

                uuid.uuidPrimaryKey(),
                info.getTRAIN_NUM(),
                info.getTRACK_NUM(),
                info.getJcStartTime(),
                info.getTIME(),
                ConstantFields.BWJDS,
                info.getJcType(),
                info.getJcXD(),
                info.getJcDH(),
                ConstantFields.S+info.getTRACK_NUM()
        };
        try {
            return mysqlJdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean insertToBwjPlan4N(JcPlanInfo info) {
        String sql = "INSERT INTO bwj_plan (planId, jcNumber, jcSource, jcStartTime, jcEndTime, jcDestination, jcType,jcXD, jcDH, jcPath) VALUES (?,?,?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] args = {

                uuid.uuidPrimaryKey(),
                info.getTRAIN_NUM(),
                info.getTRACK_NUM(),
                info.getJcStartTime(),
                info.getTIME(),
                ConstantFields.BWJDN,
                info.getJcType(),
                info.getJcXD(),
                info.getJcDH(),
                ConstantFields.N+info.getTRACK_NUM()
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
        String sql = "SELECT jcNumber,jcType,jcEndTime,jcXD,jcDH,jcDestination FROM jc_plan";
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
        String sql = "SELECT jcNumber, sum(jcHc) S,jcSource, jcEndTime, jcStartTime, jcDestination, jcType FROM jc_plan_copy group by jcNumber,jcSource, jcEndTime, jcStartTime, jcDestination, jcType";
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

    private class JcPlanALLRowMapper implements RowMapper<ResultInfo> {
        public ResultInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            ResultInfo userInfo = new ResultInfo();

            userInfo.setNumber(resultSet.getString("jcNumber"));
            userInfo.setType(resultSet.getString("jcType"));
            userInfo.setStartTime(resultSet.getTimestamp("jcStartTime"));
            userInfo.setEndTime(resultSet.getTimestamp("jcEndTime"));
            userInfo.setSource(resultSet.getString("jcSource"));
            userInfo.setDestination(resultSet.getString("jcDestination"));
            userInfo.setXd(resultSet.getString("jcXD"));
            userInfo.setDh(resultSet.getString("jcDH"));
            userInfo.setPath(resultSet.getString("jcPath"));
            userInfo.setImportant(resultSet.getString("T"));
            userInfo.setJsl(resultSet.getString("J"));

            String jsl = resultSet.getString("J");
            int dh = Integer.parseInt(resultSet.getString("jcDH"));
            String xd = resultSet.getString("jcXD");

            if (jsl!= null && xd == ConstantFields.XD) {
                if (jsl.indexOf(ConstantFields.CC)!=-1) {
                    if(dh !=4 || dh !=5) {
                        userInfo.setColor(2);
                    }
                }
                else if (jsl.indexOf(ConstantFields.CX)!=-1) {
                    if(dh !=2 || dh !=5){
                        userInfo.setColor(2);
                    }
                }
                else if (jsl.indexOf(ConstantFields.JF)!=-1) {
                    if(dh !=2 || dh !=3 && dh !=4 ) {
                        userInfo.setColor(1);
                    }
                }
                else {
                    userInfo.setColor(0);
                }
            }
            return userInfo;
        }

    }

    private class BwjPlanCopyRowMapper implements RowMapper<JcPlanInfo>{
        public JcPlanInfo mapRow(ResultSet resultSet,int i)throws SQLException{
            JcPlanInfo userInfo = new JcPlanInfo();

            userInfo.setTRAIN_NUM(resultSet.getString("jcNumber"));
            userInfo.setJcType(resultSet.getString("jcType"));
            userInfo.setTIME(resultSet.getTimestamp("jcEndTime"));
            userInfo.setJcXD(resultSet.getString("jcXD"));
            userInfo.setJcDH(resultSet.getString("jcDH"));
            userInfo.setTRACK_NUM(resultSet.getString("jcDestination"));

            return userInfo;
        }
    }

    private class BwPlanAllRowMapper implements RowMapper<ResultInfo>{
        public ResultInfo mapRow(ResultSet resultSet, int i)throws SQLException{
            ResultInfo userInfo = new ResultInfo();
            List<String> list = new ArrayList<String>();
            list.add(ConstantFields.BWJDS);
            list.add(ConstantFields.BWJDN);

            userInfo.setNumber(resultSet.getString("jcNumber"));
            userInfo.setType(resultSet.getString("jcType"));
            userInfo.setStartTime(resultSet.getTimestamp("jcStartTime"));
            userInfo.setEndTime(resultSet.getTimestamp("jcEndTime"));
            userInfo.setSource(resultSet.getString("jcSource"));
            userInfo.setSelect(list);

            return userInfo;
        }
    }

    private class BwjPlanInPathRowMapper implements RowMapper<ResultInfo>{
        public ResultInfo mapRow(ResultSet resultSet,int i)throws SQLException{
            ResultInfo userInfo = new ResultInfo();

            userInfo.setNumber(resultSet.getString("jcNumber"));
            userInfo.setDestination(resultSet.getString("jcDestination"));
            userInfo.setPath(resultSet.getString("jcPath"));

            return userInfo;
        }
    }

    private class BwjPlanNewRowMapper implements RowMapper<DcPlanInfo>{
        public DcPlanInfo mapRow(ResultSet resultSet,int i)throws SQLException{
            DcPlanInfo userInfo = new DcPlanInfo();

            userInfo.setDcNumber(resultSet.getString("jcNumber"));
            userInfo.setDcType(resultSet.getString("jcType"));
            userInfo.setDcEndTime(resultSet.getTimestamp("jcEndTime"));
            userInfo.setDcXD(resultSet.getString("jcXD"));
            userInfo.setDcDH(resultSet.getString("jcDH"));
            userInfo.setDcDestination(resultSet.getString("jcDestination"));

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
            userInfo.setDcDestination(resultSet.getString("jcDestination"));
            userInfo.setDcType(resultSet.getString("jcType"));
            userInfo.setJcSumHc(resultSet.getFloat("S"));

            return userInfo;
        }
    }
}
