package fr.valtech.angularspring.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
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

    public static final String PREFIX = "db.";

    public static final String DB_DRIVER = PREFIX + "driver";
    public static final String DB_URL = PREFIX + "url";
    public static final String DB_USERNAME = PREFIX + "user";
    public static final String DB_PASSWORD = PREFIX + "password";
    public static final String DATA_SOURCE_CLASS_NAME = "dataSourceClassName";
    public static final String DATABASE_NAME = "databaseName";
    public static final String SERVER_NAME = "serverName";
    public static final String CACHE_PREP_STMTS = "cachePrepStmts";
    public static final String PREP_STMT_CACHE_SIZE = "prepStmtCacheSize";
    public static final String PREP_STMT_CACHE_SQL_LIMIT = "prepStmtCacheSqlLimit";
    public static final String USE_SERVER_PREP_STMTS = "useServerPrepStmts";

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
        dataSource.setDriverClassName(getValue(DB_DRIVER));
        dataSource.setUrl(getValue(DB_URL));
        return dataSource;
    }

    @Bean
    @Profile({ Profiles.STAGING, Profiles.PRODUCTION })
    public DataSource dataSourceHIKARI() {
        HikariConfig config = new HikariConfig();
        config.setDataSourceClassName(getValue(DATA_SOURCE_CLASS_NAME));
        config.setUsername(getValue(DB_USERNAME));
        config.setPassword(getValue(DB_PASSWORD));

        config.addDataSourceProperty(DATABASE_NAME, getValue(PREFIX + DATABASE_NAME));
        config.addDataSourceProperty(SERVER_NAME, getValue(PREFIX + SERVER_NAME));

        //MySQL optimizations, see https://github.com/brettwooldridge/HikariCP/wiki/MySQL-Configuration
        config.addDataSourceProperty(CACHE_PREP_STMTS, getValue(PREFIX + CACHE_PREP_STMTS, "true"));
        config.addDataSourceProperty(PREP_STMT_CACHE_SIZE, getValue(PREFIX + PREP_STMT_CACHE_SIZE, "250"));
        config.addDataSourceProperty(PREP_STMT_CACHE_SQL_LIMIT, getValue(PREFIX + PREP_STMT_CACHE_SQL_LIMIT, "2048"));
        config.addDataSourceProperty(USE_SERVER_PREP_STMTS, getValue(PREFIX + USE_SERVER_PREP_STMTS, "true"));

        return new HikariDataSource(config);
    }

    @Bean
    @Profile({ Profiles.TEST, Profiles.DEV, Profiles.DEV_STANDALONE })
    public Database databaseDEV() {
        return Database.HSQL;
    }

    @Bean
    @Profile({ Profiles.STAGING, Profiles.PRODUCTION })
    public Database databasePROD() {
        return Database.MYSQL;
    }

    private String getValue(String key) {
        return environment.getProperty(key);
    }

    private String getValue(String key, String defaultValue) {
        return environment.getProperty(key, defaultValue);
    }

}
