package fr.valtech.angularspring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
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
@PropertySource("classpath:config/application-${spring.profiles.active:" + Profiles.DEFAULT + "}.properties")
public class DataSourceConfig {

    public static final String DB_DRIVER = "db.driver";
    public static final String DB_URL = "db.url";

    @Inject
    private Environment environment;

    @Bean
    @Profile({ Profiles.TEST, Profiles.DEV })
    public DataSource dataSourceTEST() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).build();
    }

    @Bean
    @Profile(Profiles.DEV_STANDALONE)
    public DataSource dataSourceDEV() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty(DB_DRIVER));
        dataSource.setUrl(environment.getProperty(DB_URL));
        return dataSource;
    }

    @Bean
    @Profile({ Profiles.TEST, Profiles.DEV, Profiles.DEV_STANDALONE })
    public Database database() {
        return Database.HSQL;
    }
}
