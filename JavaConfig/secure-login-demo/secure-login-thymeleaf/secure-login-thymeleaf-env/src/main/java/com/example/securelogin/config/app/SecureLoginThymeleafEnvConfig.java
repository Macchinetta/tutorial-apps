package com.example.securelogin.config.app;

import java.time.Duration;
import java.util.Arrays;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.TransactionManager;
import org.terasoluna.gfw.common.time.ClockFactory;
import org.terasoluna.gfw.common.time.DefaultClockFactory;

/**
 * Define settings for the environment.
 */
@Configuration
@EnableCaching
public class SecureLoginThymeleafEnvConfig {

    /**
     * DataSource.driverClassName property.
     */
    @Value("${database.driverClassName}")
    private String driverClassName;

    /**
     * DataSource.url property.
     */
    @Value("${database.url}")
    private String url;

    /**
     * DataSource.username property.
     */
    @Value("${database.username}")
    private String username;

    /**
     * DataSource.password property.
     */
    @Value("${database.password}")
    private String password;

    /**
     * DataSource.maxTotal property.
     */
    @Value("${cp.maxActive}")
    private Integer maxActive;

    /**
     * DataSource.maxIdle property.
     */
    @Value("${cp.maxIdle}")
    private Integer maxIdle;

    /**
     * DataSource.minIdle property.
     */
    @Value("${cp.minIdle}")
    private Integer minIdle;

    /**
     * DataSource.maxWaitMillis property.
     */
    @Value("${cp.maxWait}")
    private Integer maxWait;

    /**
     * Property databaseName.
     */
    @Value("${database}")
    private String database;

    /**
     * Mail.host property.
     */
    @Value("${mail.host}")
    private String host;

    /**
     * Mail.port property.
     */
    @Value("${mail.port}")
    private Integer port;

    /**
     * Mail.from property.
     */
    @Value("${mail.from}")
    private String from;

    /**
     * Mail.subject property.
     */
    @Value("${mail.subject}")
    private String subject;

    /**
     * Configure {@link ClockFactory}.
     * @return Bean of configured {@link DefaultClockFactory}
     */
    @Bean("dateFactory")
    public ClockFactory dateFactory() {
        return new DefaultClockFactory();
    }

    /**
     * Configure {@link DataSource} bean.
     * @return Bean of configured {@link BasicDataSource}
     */
    @Bean(name = "dataSource", destroyMethod = "close")
    public DataSource dataSource() {
        BasicDataSource bean = new BasicDataSource();
        bean.setDriverClassName(driverClassName);
        bean.setUrl(url);
        bean.setUsername(username);
        bean.setPassword(password);
        bean.setDefaultAutoCommit(false);
        bean.setMaxTotal(maxActive);
        bean.setMaxIdle(maxIdle);
        bean.setMinIdle(minIdle);
        bean.setMaxWait(Duration.ofMillis(maxWait));
        return bean;
    }

    /**
     * Configuration to set up database during initialization.
     * @return Bean of configured {@link DataSourceInitializer}
     */
    @Bean
    public DataSourceInitializer dataSourceInitializer() {
        DataSourceInitializer bean = new DataSourceInitializer();
        bean.setDataSource(dataSource());

        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.addScript(new ClassPathResource("/database/" + database + "-schema.sql"));
        databasePopulator
                .addScript(new ClassPathResource("/database/" + database + "-dataload.sql"));
        databasePopulator.setSqlScriptEncoding("UTF-8");
        databasePopulator.setIgnoreFailedDrops(true);
        bean.setDatabasePopulator(databasePopulator);
        return bean;
    }

    // @formatter:off
    /**
     * Configure {@link TransactionManager} bean.
     * @return Bean of configured {@link DataSourceTransactionManager}
     */
    @Bean("transactionManager")
    public TransactionManager transactionManager() {
        DataSourceTransactionManager bean = new DataSourceTransactionManager();
        bean.setDataSource(dataSource());
        bean.setRollbackOnCommitFailure(true);
        return bean;
    }
    // @formatter:on


    /**
     * Configure {@link JavaMailSender} bean.
     * @return Bean of configured {@link JavaMailSender}
     */
    @Bean(name = "mailSender")
    public JavaMailSender mailSender() {
        JavaMailSenderImpl bean = new JavaMailSenderImpl();
        bean.setHost(host);
        bean.setPort(port);
        return bean;
    }

    /**
     * Configure {@link SimpleMailMessage} bean.
     * @return Bean of configured {@link SimpleMailMessage}
     */
    @Bean(name = "passwordReissueMessage")
    public SimpleMailMessage passwordReissueMessage() {
        SimpleMailMessage bean = new SimpleMailMessage();
        bean.setFrom(from);
        bean.setSubject(subject);
        return bean;
    }

    /**
     * Configure {@link CacheManager} bean.
     * @return Bean of configured {@link CacheManager}
     */
    @Bean(name = "cacheManager")
    public CacheManager cacheManager() {
        SimpleCacheManager bean = new SimpleCacheManager();
        bean.setCaches(Arrays.asList(new ConcurrentMapCache("isInitialPassword"),
                new ConcurrentMapCache("isCurrentPasswordExpired")));
        return bean;
    }
}
