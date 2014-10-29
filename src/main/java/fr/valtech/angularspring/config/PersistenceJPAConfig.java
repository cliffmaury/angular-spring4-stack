package fr.valtech.angularspring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by cliff.maury on 27/10/2014.
 */

/**
 * Configuration for persistence layer
 */
@Configuration
@EnableTransactionManagement
//@PropertySource({"classpath:"})
@ComponentScan(basePackageClasses = {fr.valtech.angularspring.app.domain.__Package.class, fr.valtech.angularspring.app.repository.__Package.class})
public class PersistenceJPAConfig {

    @Bean
    public DataSource dataSource() {
        EmbeddedDatabase database = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).build();
        return database;
    }

    @Bean
    public DataSource dataSourceDEV() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(org.hsqldb.jdbcDriver.class.getName());
        dataSource.setUrl("jdbc:hsqldb:hsql://localhost:9001/testdb");

        return dataSource;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        //factoryBean.setJtaDataSource();
        emf.setPackagesToScan(fr.valtech.angularspring.app.domain.__Package.class.getPackage().getName());
        //emf.setPersistenceProvider(new HibernatePersistenceProvider());
        emf.setJpaVendorAdapter(jpaVendorAdaptor());
        emf.setJpaProperties(jpaProperties());
        emf.afterPropertiesSet();
        return emf.getObject();
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdaptor() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.HSQL);
        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setGenerateDdl(true);
        return jpaVendorAdapter;
    }

    private Properties jpaProperties() {
        Properties extraProperties = new Properties();
//        extraProperties.put("hibernate.format_sql", "true");
//        extraProperties.put("hibernate.show_sql", "true");
        extraProperties.put("hibernate.hbm2ddl.auto", "create");
        return extraProperties;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory());
    }

}
