package com.luju.ygz.dc.repository.impl;

import com.luju.pojo.DcPlanInfo;
import com.luju.ygz.dc.repository.DcRepositoryI;
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
public class DcRepositoryImpl implements DcRepositoryI {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private JdbcTemplate mysqlJdbcTemplate;

    PrimaryKeyUtil uuid = new PrimaryKeyUtil();

    @Override
    public List<DcPlanInfo> selectDcPlanFromOra() {
        String sql = "SELECT CC, JHKSSJ, JHJSSJ, ZYXM, ZYGD,TFX, DJ, M.GJHID, ZGBZ, CS, JSL FROM CQZ_GJHML M LEFT JOIN CQZ_GJHZW Z ON M.GJHID = Z.GJHID WHERE M.CC IS NOT NULL";
        Object[] args = {};

        try {
            return  jdbcTemplate.query(sql, args, new DcPlanCopyRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select error");
        return null;
        }
    }

    @Override
    public List<DcPlanInfo> selectJtPlan() {
        String sql = "SELECT dcNumber, dcStartTime, dcEndTime, dcType, dcSource, dcDj, dcTFX FROM ygz_show.dc_plan_copy where dcType='解体' group by dcNumber, dcStartTime, dcEndTime, dcType, dcSource, dcDj,dcTFX order by dcStartTime";
        Object[] args = {};

        try {
            return  jdbcTemplate.query(sql, args, new DcJtPlanRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select error");
            return null;
        }
    }

    @Override
    public boolean insertToPlanCopy(DcPlanInfo info) {
        String sql = "INSERT INTO dc_plan_copy (copyId, dcNumber, dcStartTime, dcEndTime, dcType, dcSource, dcDj, dcGJHID, dcZGBZ, dcCS, dcJSL, dcTFX) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
                info.getDcTFX()
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
        String sql = "INSERT INTO dc_jt_copy (jtId, dcNumber, dcStartTime, dcMidTime, dcEndTime, dcType, dcSource, dcDestination, dcEnd, dcDj, dcTFX, dcTF, dcXD, dcDH, dcPath1, dcPath2) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
        String sql = "INSERT INTO dc_jt_copy (jtId, dcNumber, dcStartTime, dcMidTime, dcEndTime, dcType, dcSource, dcDestination, dcEnd, dcDj, dcTFX, dcTF, dcXD, dcDH, dcPath1, dcPath2) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
                ConstantFields.QC+info.getDcSource(),
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
    public int deletePlanCopy() {
        String sql = "TRUNCATE TABLE dc_plan_copy";

        try {
            return jdbcTemplate.update(sql);
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
            userInfo.setDcStartTimeS(resultSet.getString("dcStartTime"));
            userInfo.setDcEndTimeS(resultSet.getString("dcEndTime"));
            userInfo.setDcType(resultSet.getString("dcType"));
            userInfo.setDcSource(resultSet.getString("dcSource"));
            userInfo.setDcDj(resultSet.getInt("dcDj"));
            userInfo.setDcTFX(resultSet.getString("dcTFX"));

            return userInfo;
        }
    }
}
