package com.luju.ygz.xf.repository.impl;

import com.luju.pojo.DcPlanInfo;
import com.luju.ygz.xf.repository.XfRepositoryI;
import luju.common.util.ConstantFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class XfRepositoryImpl implements XfRepositoryI {

    @Autowired
    private JdbcTemplate mysqlJdbcTemplate;

    @Override
    public List<DcPlanInfo> select4XfList() {
        String sql = "SELECT dcId,dcNumber,dcStartTime,dcEndTime,dcType,dcTypeE,dcSource,dcDestination,dcDj,dcPath,dcIsUpdate,dcDH,jcSumHc FROM dc_show_data where (dcXD = 'XF' or (dcXD = 'XZ' and dcTypeE = 'FC')) AND dcStartTime > now() AND dcStartTime < ADDDATE(now(),interval 10800 second) order by dcStartTime";
        Object[] args = {};
        try {
            return mysqlJdbcTemplate.query(sql, args,new DcDataRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select xf data error");
            return null;
        }
    }

    private class DcDataRowMapper implements RowMapper<DcPlanInfo> {
        public DcPlanInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            DcPlanInfo userInfo = new DcPlanInfo();

            userInfo.setDcId(resultSet.getString("dcId"));
            userInfo.setDcNumber(resultSet.getString("dcNumber"));
            userInfo.setDcStartTime(resultSet.getTimestamp("dcStartTime"));
            userInfo.setDcEndTime(resultSet.getTimestamp("dcEndTime"));
            userInfo.setDcType(resultSet.getString("dcType"));
            userInfo.setDcTypeE(resultSet.getString("dcTypeE"));
            userInfo.setDcSource(resultSet.getString("dcDestination"));
            userInfo.setDcDj(resultSet.getInt("dcDj"));
            userInfo.setDcPath(resultSet.getString("dcPath"));
            userInfo.setIsUpdate(resultSet.getInt("dcIsUpdate"));
            userInfo.setDcDH(resultSet.getString("dcDH"));
            userInfo.setSumHc(resultSet.getInt("jcSumHc"));

            String source = resultSet.getString("dcSource");
            if (source != null && source.equals(ConstantFields.JCSOURCE)) {
                userInfo.setDcDestination(ConstantFields.JCSOURCEC);
            } else if (source != null && source.equals(ConstantFields.ZCSOURCE)){
                userInfo.setDcDestination(ConstantFields.ZCSOURCEC);
            } else if (source != null && source.equals(ConstantFields.ZWQSOURCE)){
                userInfo.setDcDestination(ConstantFields.ZWQSOURCEC);
            }else if (source != null && source.equals(ConstantFields.DCSOURCE)){
                userInfo.setDcDestination(ConstantFields.DCSOURCEC);
            }else if (source != null && source.equals(ConstantFields.YHSOURCE)){
                userInfo.setDcDestination(ConstantFields.YHSOURCEC);
            }else if (source != null && source.equals(ConstantFields.QC)){
                userInfo.setDcDestination(ConstantFields.TYPE_QCX);
            } else if (source != null && source.equals(ConstantFields.JD)){
                userInfo.setDcDestination(ConstantFields.TYPE_JDX);
            }else if (source != null && source.equals(ConstantFields.ZCSOURCE)){
                userInfo.setDcDestination(ConstantFields.ZCSOURCEC);}
            else {
                userInfo.setDcDestination(source);
            }

            return userInfo;
        }
    }
}
