package com.luju.ygz.xx.repository.impl;


import com.luju.pojo.DcPlanInfo;
import com.luju.ygz.dc.repository.impl.DcRepositoryImpl;
import com.luju.ygz.xx.repository.XxRepositoryI;
import luju.common.util.ConstantFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class XxRepositoryImpl implements XxRepositoryI {

    @Autowired
    private JdbcTemplate mysqlJdbcTemplate;

    @Override
    public List<DcPlanInfo> select4AllList() {

        String sql = "SELECT dcId,dcNumber,dcStartTime,dcEndTime,dcType,dcTypeE,dcSource,dcDestination,dcDj,dcPath,dcIsUpdate,dcDH,jcSumHc FROM dc_show_data where  (dcXD = 'XD' OR dcXD = 'XZ' OR dcXD = 'XB' OR dcXD = 'XF') AND dcStartTime > now()  AND dcStartTime < ADDDATE(now(),interval 10800 second) order by dcStartTime";
        Object[] args = {};
        try {
            return mysqlJdbcTemplate.query(sql, args, new XxDataRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select dc show data error");
            return null;
        }
    }

    private class XxDataRowMapper implements RowMapper<DcPlanInfo> {
        public DcPlanInfo mapRow(ResultSet resultset, int i) throws SQLException {

            DcPlanInfo dcPlanInfo = new DcPlanInfo();
            dcPlanInfo.setDcId(resultset.getString("dcId"));
            dcPlanInfo.setDcNumber(resultset.getString("dcNumber"));
            dcPlanInfo.setDcStartTime(resultset.getTimestamp("dcStartTime"));
            dcPlanInfo.setDcEndTime(resultset.getTimestamp("dcEndTime"));
            dcPlanInfo.setDcType(resultset.getString("dcType"));
            dcPlanInfo.setDcTypeE(resultset.getString("dcTypeE"));
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
            } else if (source != null && source.equals(ConstantFields.ZWQSOURCE)){
                dcPlanInfo.setDcSource(ConstantFields.ZWQSOURCEC);
            }else if (source != null && source.equals(ConstantFields.DCSOURCE)){
                dcPlanInfo.setDcSource(ConstantFields.DCSOURCEC);
            }else if (source != null && source.equals(ConstantFields.YHSOURCE)){
                dcPlanInfo.setDcSource(ConstantFields.YHSOURCEC);
            }else if (source != null && source.equals(ConstantFields.QC)){
                dcPlanInfo.setDcSource(ConstantFields.TYPE_QCX);
            } else if (source != null && source.equals(ConstantFields.JD)){
                dcPlanInfo.setDcSource(ConstantFields.TYPE_JDX);
            }else if (source != null && source.equals(ConstantFields.ZCSOURCE)){
                dcPlanInfo.setDcSource(ConstantFields.ZCSOURCEC);}
            else {
                dcPlanInfo.setDcSource(source);
            }
            String des = resultset.getString("dcDestination");
            if (des != null && des.equals(ConstantFields.S)) {
                dcPlanInfo.setDcDestination(ConstantFields.BWJDS);
            }
            else if (des != null && des.equals(ConstantFields.N)) {
                dcPlanInfo.setDcDestination(ConstantFields.BWJDN);
            }
            else {dcPlanInfo.setDcDestination(des);}

            return dcPlanInfo;
        }
    }
}
