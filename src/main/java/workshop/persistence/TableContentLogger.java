package workshop.persistence;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

public class TableContentLogger {

    private static final Logger LOG = LoggerFactory.getLogger(TableContentLogger.class);

    private final JdbcTemplate template;

    public TableContentLogger(JdbcTemplate template) {
        this.template = template;
    }

    public void logTable(String tableName) {
        String sql = "SELECT * FROM " + tableName + " ORDER BY object_id asc";
        MetaAware rse = new MetaAware();
        String result = template.query(sql, rse);
        LOG.info("Table {} --------------------------------------------------\n{}", tableName, result);
    }

    private static class MetaAware implements ResultSetExtractor<String> {

        @Override
        public String extractData(ResultSet rs) throws SQLException, DataAccessException {
            ResultSetMetaData metaData = rs.getMetaData();
            int numCols = metaData.getColumnCount();
            StringBuilder buffer = new StringBuilder();
            while (rs.next()) {
                if (rs.isFirst()) {
                    for (int i = 1; i <= numCols; i++) {
                        buffer.append(metaData.getColumnName(i))
                                .append(i < numCols ? ";" : "");
                    }
                    buffer.append("\n");
                }
                for (int i = 1; i <= numCols; i++) {
                    buffer.append(rs.getString(i))
                            .append(i < numCols ? ";" : "");
                }
                buffer.append("\n");
            }
            return buffer.toString();
        }
    }
}
