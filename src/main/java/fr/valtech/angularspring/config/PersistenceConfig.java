package fr.valtech.angularspring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by cliff.maury on 30/10/2014.
 */
@Configuration
@EnableTransactionManagement
public class PersistenceConfig {

    @Inject
    private DataSource dataSource;

    @Inject
    private JpaVendorAdapter jpaVendorAdaptor;

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        //factoryBean.setJtaDataSource();
        emf.setPackagesToScan(fr.valtech.angularspring.app.domain.__Package.class.getPackage().getName());
        //emf.setPersistenceProvider(new HibernatePersistenceProvider());
        emf.setJpaVendorAdapter(jpaVendorAdaptor);
        emf.setJpaProperties(jpaProperties());
        emf.afterPropertiesSet();
        return emf.getObject();
    }

    private Properties jpaProperties() {
        Properties extraProperties = new Properties();
//        extraProperties.put("hibernate.format_sql", "true");
//        extraProperties.put("hibernate.show_sql", "true");
        extraProperties.put("hibernate.hbm2ddl.auto", "update");
        return extraProperties;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory());
    }

}
