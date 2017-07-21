package luju;


import com.alibaba.druid.sql.dialect.oracle.parser.OracleSelectParser;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Jdbc4Oracle {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void select4List() {
        String sql = "SELECT NODE_FOUR_WAY FROM V_CZD_DJH";
        Object[] args = {};

        try {
            System.out.println(jdbcTemplate.query(sql, args, new Select4ListRowMapper()));
        } catch (Exception e) {

            System.out.println("error");
        }
    }

    private class Select4ListRowMapper implements RowMapper<PlaceDistinct> {

        @Override
        public PlaceDistinct mapRow(ResultSet rs, int rowNum) throws SQLException {
            PlaceDistinct distinct = new PlaceDistinct();

            distinct.setDistinctId(rs.getString("NODE_FOUR_WAY"));


            return distinct;
        }
    }

    public static void main(String args[]) {
        Jdbc4Oracle jdbc4Oracle = new Jdbc4Oracle();
//        Jdbc4OracleTest jdbc4OracleTest = new Jdbc4OracleTest();
        jdbc4Oracle.select4List();
    }
}
