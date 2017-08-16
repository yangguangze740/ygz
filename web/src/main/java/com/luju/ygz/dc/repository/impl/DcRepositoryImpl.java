package com.luju.ygz.dc.repository.impl;

import com.luju.pojo.DcPlanInfo;
import com.luju.pojo.ResultInfo;
import com.luju.ygz.dc.repository.DcRepositoryI;
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
public class DcRepositoryImpl implements DcRepositoryI {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private JdbcTemplate mysqlJdbcTemplate;

    PrimaryKeyUtil uuid = new PrimaryKeyUtil();

    @Override
    public List<DcPlanInfo> selectDcPlanFromOra() {
        String sql = "SELECT CC, JHKSSJ, JHJSSJ, ZYXM, ZYGD,TFX, DJ, M.GJHID, SWH, ZGBZ, CS, JSL FROM CQZ_GJHML M LEFT JOIN CQZ_GJHZW Z ON M.GJHID = Z.GJHID";
        Object[] args = {};

        try {
            return  jdbcTemplate.query(sql, args, new DcPlanCopyRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select from ora error");
        return null;
        }
    }

    @Override
    public List<DcPlanInfo> selectJtPlan() {
        String sql = "SELECT dcNumber, dcStartTime, dcEndTime, dcType, dcSource, dcDj, dcTFX FROM ygz_show.dc_plan_copy where dcType='解体' group by dcNumber, dcStartTime, dcEndTime, dcType, dcSource, dcDj,dcTFX order by dcStartTime";
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
        String sql = "SELECT dcNumber,dcStartTime,dcEndTime,dcType,dcSource,dcTFX,dcDj FROM ygz_show.dc_plan_copy where dcType = '摘帽' group by dcNumber,dcStartTime,dcEndTime,dcType,dcSource,dcTFX,dcDj order by dcStartTime";
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
    public List<DcPlanInfo> processTcPlan() {
        String sql = "SELECT distinct dcNumber,dcType,dcDestination,dcSWH,dcCS FROM dc_tc_plan group by dcNumber,dcType,dcDestination,dcSWH,dcCS order by dcDestination,dcSWH";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new DcProcessTcPlanRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select tc2 error");
            return null;
        }
    }

    @Override
    public List<DcPlanInfo> selectZcPlan() {
        String sql = "SELECT dcNumber,dcStartTime,dcEndTime,dcType,dcSource,dcTFX,dcDj FROM ygz_show.dc_plan_copy where dcType = '转场' order by dcNumber";
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
    public List<ResultInfo> selectJtData4XD1() {
        String sql = "SELECT dcNumber, dcStartTime, dcMidTime, dcType, dcDestination, dcDj, dcXD FROM dc_jt_plan WHERE dcXD = 'XD' group by dcNumber, dcStartTime, dcMidTime, dcType, dcDestination, dcDj, dcXD order by dcStartTime";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new DcJtData1RowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select error");
            return null;
        }
    }

    @Override
    public List<ResultInfo> selectJtData4XD2() {
        String sql = "SELECT dcNumber, dcMidTime, dcEndTime, dcType, dcDestination,dcEnd, dcDj, dcXD,dcPath2 FROM dc_jt_plan WHERE dcXD = 'XD' group by dcNumber, dcMidTime, dcEndTime, dcType, dcDestination, dcEnd, dcDj, dcXD,dcPath2 order by dcMidTime";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new DcJtData2RowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select error");
            return null;
        }
    }

    @Override
    public List<ResultInfo> selectZmData4XD1() {
        String sql = "SELECT dcNumber,dcStartTime,dcMidTime,dcType,dcDestination,dcDj,dcXD FROM dc_zm_plan where dcXD = 'XD' group by dcNumber,dcStartTime,dcMidTime,dcType,dcDestination,dcDj,dcXD";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new DcZmData1RowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select zm data1 error");
            return null;
        }
    }

    @Override
    public List<ResultInfo> selectZmData4XD2() {
        String sql = "SELECT dcNumber,dcMidTime1,dcEndTime,dcType,dcDestination,dcEnd,dcDj,dcXD,dcPath2 FROM dc_zm_plan where dcXD = 'XD' group by dcNumber,dcMidTime1,dcEndTime,dcType,dcDestination,dcEnd,dcDj,dcXD,dcPath2 order by dcMidTime1";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new DcZmData2RowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select zm data2 error");
            return null;
        }
    }

    @Override
    public List<ResultInfo> selectZcData() {
        String sql = "SELECT dcNumber,dcStartTime,dcEndTime,dcType,dcSource,dcDestination,dcDj,dcXD,dcPath FROM dc_zc_plan group by dcNumber,dcStartTime,dcEndTime,dcType,dcSource,dcDestination,dcDj,dcXD,dcPath";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new DcZcDataRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select zm data2 error");
            return null;
        }
    }

    @Override
    public List<ResultInfo> selectTcData() {
        String sql = "SELECT distinct dcDj,dcNumber,dcStartTime,dcEndTime,dcType,dcDestination FROM ygz_show.dc_tc_plan order by dcDestination;";
        Object[] args = {};

        try {
            return mysqlJdbcTemplate.query(sql, args, new DcTcDataRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select tc data error");
            return null;
        }
    }

    @Override
    public List<ResultInfo> selectJtDataInPath1(DcPlanInfo dcPlanInfo) {
        String sql = "SELECT dcNumber,dcSource,dcPath1 FROM dc_jt_plan where dcNumber = ? and dcSource = ?;";
        Object[] args = {
                dcPlanInfo.getDcNumber(),
                dcPlanInfo.getDcSource()
        };

        try {
            return mysqlJdbcTemplate.query(sql, args, new DcJtDataInPath1RowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select jt path1 error");
            return null;
        }
    }

    @Override
    public List<ResultInfo> selectZmDataInPath1(DcPlanInfo dcPlanInfo) {
        String sql = "SELECT dcNumber,dcSource,dcPath1 FROM dc_zm_plan where dcNumber = ? and dcSource = ?";
        Object[] args = {
                dcPlanInfo.getDcNumber(),
                dcPlanInfo.getDcSource()
        };

        try {
            return mysqlJdbcTemplate.query(sql, args, new DcZmDataInPath1RowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select zm path1 error");
            return null;
        }
    }

    @Override
    public List<ResultInfo> selectTcDataInPath1(DcPlanInfo dcPlanInfo) {
        String sql = "SELECT distinct dcNumber,dcSource,dcDestination,dcPath FROM dc_tc_plan where dcNumber = ? and dcSource = ? and dcDestination = ?";
        Object[] args = {
                dcPlanInfo.getDcNumber(),
                dcPlanInfo.getDcSource(),
                dcPlanInfo.getDcDestination()
        };

        try {
            return mysqlJdbcTemplate.query(sql, args, new DcTcDataInPath1RowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select tc path1 error");
            return null;
        }
    }

    @Override
    public boolean insertToPlanCopy(DcPlanInfo info) {
        String sql = "INSERT INTO dc_plan_copy (copyId, dcNumber, dcStartTime, dcEndTime, dcType, dcSource, dcDj, dcGJHID, dcZGBZ, dcCS, dcJSL, dcTFX, dcSWH) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
                info.getDcSWH()
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
    public boolean insertJtPlan4QC(DcPlanInfo info) {
        String sql = "INSERT INTO dc_jt_plan (jtId, dcNumber, dcStartTime, dcMidTime, dcEndTime, dcType, dcSource, dcDestination, dcEnd, dcDj, dcTFX, dcTF, dcXD, dcDH, dcPath1, dcPath2) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] args = {
                uuid.uuidPrimaryKey(),
                info.getDcNumber(),
                info.getDcStartTime(),
                info.getDcMidTime(),
                info.getDcEndTime(),
                info.getDcType(),
                ConstantFields.TYPE_QCX,
                info.getDcSource(),
                info.getDcEnd(),
                info.getDcDj(),
                info.getDcTFX(),
                info.getDcTF(),
                info.getDcXD(),
                info.getDcDH(),
                ConstantFields.QC+info.getDcSource(),
                info.getDcSource()+info.getDcEnd()
        };
        try {
            return mysqlJdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("insert QC error");
            return false;
        }
    }

    @Override
    public boolean insertJtPlan4JD(DcPlanInfo info) {
        String sql = "INSERT INTO dc_jt_plan (jtId, dcNumber, dcStartTime, dcMidTime, dcEndTime, dcType, dcSource, dcDestination, dcEnd, dcDj, dcTFX, dcTF, dcXD, dcDH, dcPath1, dcPath2) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] args = {
                uuid.uuidPrimaryKey(),
                info.getDcNumber(),
                info.getDcStartTime(),
                info.getDcMidTime(),
                info.getDcEndTime(),
                info.getDcType(),
                ConstantFields.TYPE_JDX,
                info.getDcSource(),
                info.getDcEnd(),
                info.getDcDj(),
                info.getDcTFX(),
                info.getDcTF(),
                info.getDcXD(),
                info.getDcDH(),
                ConstantFields.JD+info.getDcSource(),
                info.getDcSource()+info.getDcEnd()
        };
        try {
            return mysqlJdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("insert JD error");
            return false;
        }
    }

    @Override
    public boolean insertZmPlan4XT1(DcPlanInfo info) {
        String sql = "INSERT INTO dc_zm_plan (zmId, dcNumber, dcStartTime, dcMidTime, dcMidTime1, dcEndTime, dcType, dcSource, dcDestination, dcEnd, dcDj, dcTFX, dcTF, dcXD, dcDH, dcPath1, dcPath2) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] args = {
                uuid.uuidPrimaryKey(),
                info.getDcNumber(),
                info.getDcStartTime(),
                info.getDcMidTime(),
                info.getDcMidTime1(),
                info.getDcEndTime(),
                info.getDcType(),
                ConstantFields.XT1,
                info.getDcSource(),
                info.getDcEnd(),
                info.getDcDj(),
                info.getDcTFX(),
                info.getDcTF(),
                info.getDcXD(),
                info.getDcDH(),
                ConstantFields.ZM+ConstantFields.XT1+info.getDcSource(),
                ConstantFields.ZM+info.getDcSource()+info.getDcEnd()
        };
        try {
            return mysqlJdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("insert ZM XT1 error");
            return false;
        }
    }

    @Override
    public boolean insertZmPlan4XT2(DcPlanInfo info) {
        String sql = "INSERT INTO dc_zm_plan (zmId, dcNumber, dcStartTime, dcMidTime, dcMidTime1, dcEndTime, dcType, dcSource, dcDestination, dcEnd, dcDj, dcTFX, dcTF, dcXD, dcDH, dcPath1, dcPath2) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] args = {
                uuid.uuidPrimaryKey(),
                info.getDcNumber(),
                info.getDcStartTime(),
                info.getDcMidTime(),
                info.getDcMidTime1(),
                info.getDcEndTime(),
                info.getDcType(),
                ConstantFields.XT2,
                info.getDcSource(),
                info.getDcEnd(),
                info.getDcDj(),
                info.getDcTFX(),
                info.getDcTF(),
                info.getDcXD(),
                info.getDcDH(),
                ConstantFields.ZM+ConstantFields.XT2+info.getDcSource(),
                ConstantFields.ZM+info.getDcSource()+info.getDcEnd()
        };
        try {
            return mysqlJdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("insert ZM XT2 error");
            return false;
        }
    }

    @Override
    public boolean insertTcPlan4XT1(DcPlanInfo info) {
        String sql = "INSERT INTO dc_tc_plan (tcId, dcNumber, dcStartTime, dcEndTime, dcType, dcSource, dcDestination, dcDj, dcTFX, dcTF, dcXD, dcDH, dcSWH, dcCS, dcPath) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] args = {
                uuid.uuidPrimaryKey(),
                info.getDcNumber(),
                info.getDcStartTime(),
                info.getDcEndTime(),
                info.getDcType(),
                ConstantFields.XT1,//北
                info.getDcDestination(),
                info.getDcDj(),
                info.getDcTFX(),
                info.getDcTF(),
                info.getDcXD(),
                info.getDcDH(),
                info.getDcSWH(),
                info.getDcCS(),
                ConstantFields.XT1+info.getDcDestination()
        };
        try {
            return mysqlJdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("insert tc4xt1 error");
            return false;
        }
    }

    @Override
    public boolean insertTcPlan4XT2(DcPlanInfo info) {
        String sql = "INSERT INTO dc_tc_plan (tcId, dcNumber, dcStartTime, dcEndTime, dcType, dcSource, dcDestination, dcDj, dcTFX, dcTF, dcXD, dcDH, dcSWH, dcCS, dcPath) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] args = {
                uuid.uuidPrimaryKey(),
                info.getDcNumber(),
                info.getDcStartTime(),
                info.getDcEndTime(),
                info.getDcType(),
                ConstantFields.XT2,//南
                info.getDcDestination(),
                info.getDcDj(),
                info.getDcTFX(),
                info.getDcTF(),
                info.getDcXD(),
                info.getDcDH(),
                info.getDcSWH(),
                info.getDcCS(),
                ConstantFields.XT2+info.getDcDestination()
        };
        try {
            return mysqlJdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("insert tc4xt2 error");
            return false;
        }
    }

    @Override
    public boolean insertTcPlan4Six(DcPlanInfo info) {
        String sql = "INSERT INTO dc_tc_six (tcId, dcNumber, dcType, dcDestination, dcSWH, dcCS, dcCS6) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Object[] args = {
                uuid.uuidPrimaryKey(),
                info.getDcNumber(),
                info.getDcType(),
                info.getDcDestination(),
                info.getDcSWH(),
                info.getDcCS(),
                info.getDcCS6()
        };
        try {
            return mysqlJdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("insert tc 4 six error");
            return false;
        }
    }

    @Override
    public boolean insertZcPlan(DcPlanInfo info) {
        String sql = "INSERT INTO dc_zc_plan (zcId, dcNumber, dcStartTime, dcEndTime, dcType, dcSource, dcDestination, dcDj, dcTFX, dcTF, dcXD, dcDH, dcPath) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] args = {
                uuid.uuidPrimaryKey(),
                info.getDcNumber(),
                info.getDcStartTime(),
                info.getDcEndTime(),
                info.getDcType(),
                ConstantFields.TYPE_ZC,
                info.getDcDestination(),
                info.getDcDj(),
                info.getDcTFX(),
                info.getDcTF(),
                info.getDcXD(),
                info.getDcDH(),
                ConstantFields.ZC+info.getDcDestination()
        };
        try {
            return mysqlJdbcTemplate.update(sql, args) == 1;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("insert zc error");
            return false;
        }
    }

    @Override
    public int deletePlanCopy() {
        String sql = "TRUNCATE TABLE dc_plan_copy";

        try {
            return mysqlJdbcTemplate.update(sql);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("delete error");
            return 0;
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

            userInfo.setDcNumber(resultSet.getString("dcNumber"));
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

    private class DcProcessTcPlanRowMapper implements RowMapper<DcPlanInfo> {
        public DcPlanInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            DcPlanInfo userInfo = new DcPlanInfo();

            userInfo.setDcNumber(resultSet.getString("dcNumber"));
            userInfo.setDcType(resultSet.getString("dcType"));
            userInfo.setDcDestination(resultSet.getString("dcDestination"));
            userInfo.setDcSWH(resultSet.getInt("dcSWH"));
            userInfo.setDcCS(resultSet.getInt("dcCS"));

            return userInfo;
        }
    }

    private class DcTcDataRowMapper implements RowMapper<ResultInfo> {
        public ResultInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            ResultInfo userInfo = new ResultInfo();
            List<String> list = new ArrayList<String>();
            list.add(ConstantFields.XT1);
            list.add(ConstantFields.XT2);

            userInfo.setDj(resultSet.getInt("dcDj"));
            userInfo.setNumber(resultSet.getString("dcNumber"));
            userInfo.setStartTime(resultSet.getTimestamp("dcStartTime"));
            userInfo.setEndTime(resultSet.getTimestamp("dcEndTime"));
            userInfo.setType(resultSet.getString("dcType"));
            userInfo.setDestination(resultSet.getString("dcDestination"));
            userInfo.setSelect(list);

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

            return userInfo;
        }
    }

    private class DcJtData1RowMapper implements RowMapper<ResultInfo> {
        public ResultInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            ResultInfo userInfo = new ResultInfo();
            List<String> list = new ArrayList<String>();
            list.add(ConstantFields.TYPE_QCX);
            list.add(ConstantFields.TYPE_JDX);

            userInfo.setNumber(resultSet.getString("dcNumber"));
            userInfo.setStartTime(resultSet.getTimestamp("dcStartTime"));
            userInfo.setEndTime(resultSet.getTimestamp("dcMidTime"));
            userInfo.setType(resultSet.getString("dcType"));
            userInfo.setDestination(resultSet.getString("dcDestination"));
            userInfo.setDj(resultSet.getInt("dcDj"));
            userInfo.setXd(resultSet.getString("dcXD"));
            userInfo.setSelect(list);

            return userInfo;
        }
    }

    private class DcJtData2RowMapper implements RowMapper<ResultInfo> {
        public ResultInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            ResultInfo userInfo = new ResultInfo();

            userInfo.setNumber(resultSet.getString("dcNumber"));
            userInfo.setStartTime(resultSet.getTimestamp("dcMidTime"));
            userInfo.setEndTime(resultSet.getTimestamp("dcEndTime"));
            userInfo.setType(resultSet.getString("dcType"));
            userInfo.setSource(resultSet.getString("dcDestination"));
            userInfo.setDestination(resultSet.getString("dcEnd"));
            userInfo.setDj(resultSet.getInt("dcDj"));
            userInfo.setXd(resultSet.getString("dcXD"));
            userInfo.setPath(resultSet.getString("dcPath2"));

            return userInfo;
        }
    }

    private class DcZmData1RowMapper implements RowMapper<ResultInfo> {
        public ResultInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            ResultInfo userInfo = new ResultInfo();
            List<String> list = new ArrayList<String>();
            list.add(ConstantFields.XT1);
            list.add(ConstantFields.XT2);

            userInfo.setNumber(resultSet.getString("dcNumber"));
            userInfo.setStartTime(resultSet.getTimestamp("dcStartTime"));
            userInfo.setEndTime(resultSet.getTimestamp("dcMidTime"));
            userInfo.setType(resultSet.getString("dcType"));
            userInfo.setDestination(resultSet.getString("dcDestination"));
            userInfo.setDj(resultSet.getInt("dcDj"));
            userInfo.setXd(resultSet.getString("dcXD"));

            return userInfo;
        }
    }

    private class DcZmData2RowMapper implements RowMapper<ResultInfo> {
        public ResultInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            ResultInfo userInfo = new ResultInfo();

            userInfo.setNumber(resultSet.getString("dcNumber"));
            userInfo.setStartTime(resultSet.getTimestamp("dcMidTime1"));
            userInfo.setEndTime(resultSet.getTimestamp("dcEndTime"));
            userInfo.setType(resultSet.getString("dcType"));
            userInfo.setSource(resultSet.getString("dcDestination"));
            userInfo.setDestination(resultSet.getString("dcEnd"));
            userInfo.setDj(resultSet.getInt("dcDj"));
            userInfo.setXd(resultSet.getString("dcXD"));
            userInfo.setPath(resultSet.getString("dcPath2"));

            return userInfo;
        }
    }

    private class DcZcDataRowMapper implements RowMapper<ResultInfo> {
        public ResultInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            ResultInfo userInfo = new ResultInfo();

            userInfo.setNumber(resultSet.getString("dcNumber"));
            userInfo.setStartTime(resultSet.getTimestamp("dcStartTime"));
            userInfo.setEndTime(resultSet.getTimestamp("dcEndTime"));
            userInfo.setType(resultSet.getString("dcType"));
            userInfo.setSource(resultSet.getString("dcSource"));
            userInfo.setDestination(resultSet.getString("dcDestination"));
            userInfo.setDj(resultSet.getInt("dcDj"));
            userInfo.setXd(resultSet.getString("dcXD"));
            userInfo.setPath(resultSet.getString("dcPath"));

            return userInfo;
        }
    }

    private class DcJtDataInPath1RowMapper implements RowMapper<ResultInfo> {
        public ResultInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            ResultInfo userInfo = new ResultInfo();

            userInfo.setNumber(resultSet.getString("dcNumber"));
            userInfo.setSource(resultSet.getString("dcSource"));
            userInfo.setPath(resultSet.getString("dcPath1"));

            return userInfo;
        }
    }

    private class DcZmDataInPath1RowMapper implements RowMapper<ResultInfo> {
        public ResultInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            ResultInfo userInfo = new ResultInfo();

            userInfo.setNumber(resultSet.getString("dcNumber"));
            userInfo.setSource(resultSet.getString("dcSource"));
            userInfo.setPath(resultSet.getString("dcPath1"));

            return userInfo;
        }
    }

    private class DcTcDataInPath1RowMapper implements RowMapper<ResultInfo> {
        public ResultInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            ResultInfo userInfo = new ResultInfo();

            userInfo.setNumber(resultSet.getString("dcNumber"));
            userInfo.setSource(resultSet.getString("dcSource"));
            userInfo.setDestination(resultSet.getString("dcDestination"));
            userInfo.setPath(resultSet.getString("dcPath"));

            return userInfo;
        }
    }
}
