package workshop.persistence;

import javax.sql.DataSource;

import com.zaxxer.hikari.pool.HikariPool;
import org.springframework.beans.DirectFieldAccessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@EnableJpaRepositories
class PersistenceConfig {

    @Bean
    TableContentLogger tableContentLogger(JdbcTemplate template) {
        return new TableContentLogger(template);
    }

    @Bean
    Puzzle action(MasterRepository masterRepository, DetailRepository detailRepository,
            HikariPool pool, DetailHandler handler) {
        return new Puzzle5(masterRepository, detailRepository, pool, handler);
    }

    @Bean
    HikariPool hikariPool(DataSource dataSource) {
        return (HikariPool) new DirectFieldAccessor(dataSource).getPropertyValue("pool");
    }

    @Bean
    DetailHandler detailHandler(MasterRepository masterRepository, DetailRepository detailRepository, HikariPool pool) {
        return new DetailHandler(masterRepository, detailRepository, pool);
    }
}
