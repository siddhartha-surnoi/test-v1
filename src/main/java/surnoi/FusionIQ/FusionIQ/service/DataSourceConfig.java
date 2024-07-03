package surnoi.FusionIQ.FusionIQ.service;

import com.zaxxer.hikari.HikariConfig;

import com.zaxxer.hikari.HikariDataSource;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration

public class DataSourceConfig {

    @Bean

    public DataSource dataSource() {

        HikariConfig config = new HikariConfig();




//      config.setJdbcUrl("jdbc:mysql://localhost:3306/Fusion?createDatabaseIfNotExist=true");

        config.setJdbcUrl("jdbc:mysql://fusioniq.c5go2uu64ya8.ap-south-1.rds.amazonaws.com:3306/versiontest1?createDatabaseIfNotExist=true");

//      config.setJdbcUrl("jdbc:mysql://localhost:3306/rakesh");

        config.setUsername("admin");

        config.setPassword("admin123");

        config.addDataSourceProperty("connectionInitSql", "SET GLOBAL max_allowed_packet=134217728"); // 128 MB

        return new HikariDataSource(config);

    }

    @Bean

    public PlatformTransactionManager transactionManager() {

        return new DataSourceTransactionManager(dataSource());

    }

}
 