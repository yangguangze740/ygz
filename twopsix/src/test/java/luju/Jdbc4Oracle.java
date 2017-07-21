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

    private static final Logger LOG = LoggerFactory.getLogger(Jdbc4Oracle.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<PlaceDistinct> select4List() {
        String sql = "SELECT NODE_FOUR_WAY FROM V_CZD_DJH";
        Object[] args = {};

        try {
            return jdbcTemplate.query(sql, args, new Select4ListRowMapper());
        } catch (Exception e) {
            LOG.error("[PlaceDistinct] query4List error with info {}.", e.getMessage());

            return new ArrayList<>();
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

}
