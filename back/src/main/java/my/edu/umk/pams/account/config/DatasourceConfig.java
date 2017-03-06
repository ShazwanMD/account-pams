package my.edu.umk.pams.account.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DatasourceConfig {

    @Autowired
    private Environment env;

    @Bean
    public SessionFactory sessionFactory() {
        return new LocalSessionFactoryBuilder(dataSource())
                .scanPackages("com.canang.sagacity.finance")
                .addProperties(hibernateProperties())
                .buildSessionFactory();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new HibernateTransactionManager(sessionFactory());
    }

    @Bean
    public Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL81Dialect");
        properties.put("hibernate.show_sql", "false");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.format_sql", "true");
        properties.put("javax.persistence.validation.mode", "none");
        return properties;
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUsername(env.getProperty("db.local.username"));
        dataSource.setPassword(env.getProperty("db.local.password"));
        dataSource.setUrl(env.getProperty("db.local.url"));
        dataSource.setDriverClassName(env.getProperty("db.local.driver"));
        dataSource.setMaxIdle(10);
        dataSource.setMaxActive(20);
        dataSource.setMaxWait(-1);
        dataSource.setTestOnBorrow(true);
        dataSource.setValidationQuery(env.getProperty("db.local.validation.query"));
        return dataSource;
    }

    @Bean
    public DataSource batchDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUsername(env.getProperty("db.local.username"));
        dataSource.setPassword(env.getProperty("db.local.password"));
        dataSource.setUrl(env.getProperty("db.local.url"));
        dataSource.setDriverClassName(env.getProperty("db.local.driver"));
        dataSource.setMaxIdle(10);
        dataSource.setMaxActive(20);
        dataSource.setMaxWait(-1);
        dataSource.setTestOnBorrow(true);
        dataSource.setValidationQuery(env.getProperty("db.local.validation.query"));
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(batchDataSource());
    }
}

