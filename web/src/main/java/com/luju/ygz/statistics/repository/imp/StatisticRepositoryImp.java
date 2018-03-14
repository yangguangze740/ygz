package com.luju.ygz.statistics.repository.imp;

import com.luju.pojo.DcPlanInfo;
import com.luju.pojo.StatisticsInfo;
import com.luju.ygz.sf.repository.impl.SfRepositoryImpl;
import com.luju.ygz.statistics.repository.StaisticRepositoryI;
import luju.common.util.ConstantFields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StatisticRepositoryImp implements StaisticRepositoryI{

    private static final Logger LOG = LoggerFactory.getLogger(StatisticRepositoryImp.class);

    @Autowired
    private JdbcTemplate mysqlJdbcTemplate;

    @Override
    public int queryJlConflict() {
        String sql = "SELECT COUNT(MA.statisticsId) FROM ygz_show.dc_statistics AS MA WHERE MA.type = '交叉进路'";

        Object[] args = {};
        try {
            return mysqlJdbcTemplate.queryForInt(sql, args);
        } catch (Exception e) {
            LOG.error("[worker] select jlConflictSum  error", e.getMessage());

            return 0;
        }
    }

    @Override
    public int queryBetter() {
        String sql = "SELECT COUNT(MA.statisticsId) FROM ygz_show.dc_statistics AS MA WHERE MA.type = '优选'";

        Object[] args = {};
        try {
            return mysqlJdbcTemplate.queryForInt(sql, args);
        } catch (Exception e) {
            LOG.error("[worker] select betterSum  error", e.getMessage());

            return 0;
        }
    }

    @Override
    public int queryError() {
        String sql ="SELECT COUNT(MA.statisticsId) FROM ygz_show.dc_statistics AS MA WHERE MA.type = '错办'";

        Object[] args = {};
        try {
            return mysqlJdbcTemplate.queryForInt(sql, args);
        } catch (Exception e) {
            LOG.error("[worker] select Error  error", e.getMessage());

            return 0;
        }
    }

    @Override
    public List<StatisticsInfo> queryMonthConflict() {
        StringBuilder builder = new StringBuilder();
        builder.append(" SELECT DAY(B.dayList) AS logTime , COUNT(A.logTime) AS NUM FROM ");
        builder.append(" (SELECT DATE(NOW()) dayList UNION ALL SELECT DATE(NOW() - INTERVAL 1 DAY)  ");
        builder.append(" UNION ALL SELECT DATE(NOW() - INTERVAL 2 DAY) UNION ALL SELECT DATE(NOW() - INTERVAL 3 DAY) ");
        builder.append(" UNION ALL SELECT DATE(NOW() - INTERVAL 4 DAY) UNION ALL SELECT DATE(NOW() - INTERVAL 5 DAY)  ");
        builder.append(" UNION ALL SELECT DATE(NOW() - INTERVAL 6 DAY) UNION ALL SELECT DATE(NOW() - INTERVAL 7 DAY) ");
        builder.append(" UNION ALL SELECT DATE(NOW() - INTERVAL 8 DAY) UNION ALL SELECT DATE(NOW() - INTERVAL 9 DAY)  ");
        builder.append(" UNION ALL SELECT DATE(NOW() - INTERVAL 10 DAY) UNION ALL SELECT DATE(NOW() - INTERVAL 11 DAY) ");
        builder.append(" UNION ALL SELECT DATE(NOW() - INTERVAL 12 DAY) UNION ALL SELECT DATE(NOW() - INTERVAL 13 DAY) ");
        builder.append(" UNION ALL SELECT DATE(NOW() - INTERVAL 14 DAY) UNION ALL SELECT DATE(NOW() - INTERVAL 15 DAY) ");
        builder.append(" UNION ALL SELECT DATE(NOW() - INTERVAL 16 DAY) UNION ALL SELECT DATE(NOW() - INTERVAL 17 DAY) ");
        builder.append(" UNION ALL SELECT DATE(NOW() - INTERVAL 18 DAY) UNION ALL SELECT DATE(NOW() - INTERVAL 19 DAY) ");
        builder.append(" UNION ALL SELECT DATE(NOW() - INTERVAL 20 DAY) UNION ALL SELECT DATE(NOW() - INTERVAL 21 DAY) ");
        builder.append(" UNION ALL SELECT DATE(NOW() - INTERVAL 22 DAY) UNION ALL SELECT DATE(NOW() - INTERVAL 23 DAY) ");
        builder.append(" UNION ALL SELECT DATE(NOW() - INTERVAL 24 DAY) UNION ALL SELECT DATE(NOW() - INTERVAL 25 DAY) ");
        builder.append(" UNION ALL SELECT DATE(NOW() - INTERVAL 26 DAY) UNION ALL SELECT DATE(NOW() - INTERVAL 27 DAY) ");
        builder.append(" UNION ALL SELECT DATE(NOW() - INTERVAL 28 DAY) UNION ALL SELECT DATE(NOW() - INTERVAL 29 DAY) ) B LEFT JOIN (SELECT  logTime FROM dc_statistics");
        builder.append(" WHERE DATE_FORMAT(logTime, '%Y-%m-%d') >= DATE(NOW()) - INTERVAL 29 DAY AND type = '交叉进路'  ) A ON DATE_FORMAT(A.logTime, '%Y-%m-%d') = B.dayList");
        builder.append(" GROUP BY B.dayList");

        Object[] args = {};
        try {
            return mysqlJdbcTemplate.query(builder.toString(), args, new SelectMaintenanceNumberForLineRowMApper());
        } catch (Exception e) {
            LOG.error("[JsonModel] select month maintenance maintenance for line  error with info {}.", e.getMessage());

            return null;
        }
    }

    @Override
    public List<StatisticsInfo> queryAllList() {
        String sql = "SELECT logTime,data1,data2,type,user,details FROM ygz_show.dc_statistics";
        Object[] args = {};
        try {
            return mysqlJdbcTemplate.query(sql, args,new DcDataRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("select Sf data error");
            return null;
        }
    }

    private class DcDataRowMapper implements RowMapper<StatisticsInfo> {
        public StatisticsInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            StatisticsInfo userInfo = new StatisticsInfo();

            userInfo.setLogTime(resultSet.getString("logTime"));
            userInfo.setData1(resultSet.getString("data1"));
            userInfo.setData2(resultSet.getString("data2"));
            userInfo.setType(resultSet.getString("type"));
            userInfo.setUser(resultSet.getString("user"));
            userInfo.setDetails(resultSet.getString("details"));


            return userInfo;
        }
    }

    private class SelectMaintenanceNumberForLineRowMApper implements RowMapper<StatisticsInfo> {

        @Override
        public StatisticsInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            StatisticsInfo jsonModel = new StatisticsInfo();

            jsonModel.setLogTime(resultSet.getString("logTime"));
            jsonModel.setValue(resultSet.getInt("NUM"));

            return jsonModel;
        }
    }
}
