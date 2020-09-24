package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class MysqlConfig {
    @Bean
    public JdbcTemplate mysqlJdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
