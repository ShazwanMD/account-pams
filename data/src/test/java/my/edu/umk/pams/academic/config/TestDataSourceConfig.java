package my.edu.umk.pams.academic.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class TestDataSourceConfig {

    @Autowired
    private Environment env;

    @Bean
    @Primary
    public DataSource primaryDataSource() {
        return getBasicDataSource();
    }

    @Bean
    public DataSource batchDataSource() {
        return getBasicDataSource();
    }

    private BasicDataSource getBasicDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUsername(env.getProperty("db.user"));
        dataSource.setPassword(env.getProperty("db.password"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setDriverClassName(env.getProperty("db.driver"));
        dataSource.setMaxIdle(10);
        dataSource.setMaxActive(20);
        dataSource.setMaxWait(-1);
        dataSource.setTestOnBorrow(true);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(getBasicDataSource());
    }
}
