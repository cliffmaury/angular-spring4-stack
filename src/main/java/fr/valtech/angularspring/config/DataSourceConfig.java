package fr.valtech.angularspring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.vendor.Database;

import javax.inject.Inject;
import javax.sql.DataSource;

/**
 * Created by cliff.maury on 27/10/2014.
 */

/**
 * Configuration for datasource according to environnment
 */
@Configuration
@PropertySource("classpath:config/application.properties")
@PropertySource("classpath:config/application-${spring.profiles.active:" + Profiles.DEV + "}.properties")
public class DataSourceConfig {

    @Inject
    private Environment environment;

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
        dataSource.setUrl(environment.getProperty("db.url"));
        return dataSource;
    }

    @Bean
    @Profile({ Profiles.TEST, Profiles.DEV })
    public Database database() {
        return Database.HSQL;
    }
}
