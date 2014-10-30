package fr.valtech.angularspring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

/**
 * Created by cliff.maury on 27/10/2014.
 */

/**
 * Configuration for datasource according to environement
 */
@Configuration
//@PropertySource({"classpath:"})
public class DataSourceConfig {

    @Bean
    @Profile(Profiles.TEST)
    public DataSource dataSourceTEST() {
        EmbeddedDatabase database = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).build();
        return database;
    }

    @Bean
    @Profile(Profiles.DEV)
    public DataSource dataSourceDEV() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(org.hsqldb.jdbcDriver.class.getName());
        dataSource.setUrl("jdbc:hsqldb:hsql://localhost:9001/testdb");
        return dataSource;
    }

    @Bean
    @Profile({ Profiles.TEST, Profiles.DEV })
    public JpaVendorAdapter jpaVendorAdaptor() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.HSQL);
        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setGenerateDdl(true);
        return jpaVendorAdapter;
    }
}
