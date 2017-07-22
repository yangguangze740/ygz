package luju.ygz.test.repository;

import luju.test.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class TestRepositoryImpl {
    @Autowired
    private JdbcTemplate oraJdbcTemplate;

    public Object testQuery() {
        String sql = "SELECT NODE_FOUR_WAY FROM V_CZD_DJH";
        Object[] args = {};

        try {
            return oraJdbcTemplate.query(sql, args, new WorkerListRowMapper());
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    private class WorkerListRowMapper implements RowMapper<TestInfo> {
        public TestInfo mapRow(ResultSet resultSet, int i) throws SQLException {
            TestInfo userInfo = new TestInfo();

            userInfo.setNODE_FOUR_WAY(resultSet.getString("NODE_FOUR_WAY"));

            return userInfo;
        }
    }
}
