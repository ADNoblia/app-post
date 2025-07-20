package com.noblia.app.configs;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

// @Configuration
public class DatasourceConfig {

    // @Bean
    public DataSource dataSource() {
        final var dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:testdb");
        dataSource.setUsername("sa");
        dataSource.setPassword("password");
        return dataSource;
    }
}
