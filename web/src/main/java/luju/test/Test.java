package luju.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Test {

    ApplicationContext context = new ClassPathXmlApplicationContext("spring/test.xml");

    JdbcTemplate oraJdbcTemplate = (JdbcTemplate) context.getBean("oraJdbcTemplate");

    public static void main(String[] args) {

        Test test = new Test();
        System.out.println(test.select4Worker());

    }

    public List<TestInfo> select4Worker() {
        String sql = "select NODE_FOUR_WAY from V_CZD_DJH";

        try {
            return oraJdbcTemplate.query(sql, new WorkerListRowMapper());
        } catch (Exception e) {
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
