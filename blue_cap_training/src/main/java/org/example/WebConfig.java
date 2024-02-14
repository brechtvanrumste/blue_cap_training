package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "org.example")
public class WebConfig {

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("mysqluser");
        dataSource.setPassword("mysqlpass");
        dataSource.setUrl(
                "jdbc:mysql://localhost:3306/myDb?createDatabaseIfNotExist=true");

        return dataSource;
    }
}

