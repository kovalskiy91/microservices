package kovalskiy91.selfstudy.microservices.api.gateway;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.Setter;
import lombok.val;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataBaseConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "database.datasource.primary")
    public DataSourceProperties primaryDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource primaryDataSource(DataSourceProperties primaryDataSourceProperties) {
        val dataSource = createDataSource(primaryDataSourceProperties);
        dataSource.setConnectionTimeout(primaryDataSourceProperties.getConnectionTimeout());
        return dataSource;
    }

    private HikariDataSource createDataSource(DataSourceProperties dataSourceProperties) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(dataSourceProperties.getJdbcUrl());
        dataSource.setUsername(dataSourceProperties.getUsername());
        dataSource.setPassword(dataSourceProperties.getPassword());
        dataSource.setPoolName(dataSourceProperties.getPoolName());
        dataSource.setMaximumPoolSize(dataSourceProperties.getMaximumPoolSize());
        dataSource.setMinimumIdle(dataSourceProperties.getMinimumIdle());
        return dataSource;
    }

    @Bean
    @ConfigurationProperties(prefix = "database.datasource.liquibase")
    public DataSourceProperties liquibaseDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @LiquibaseDataSource
    public DataSource liquibaseDataSource(DataSourceProperties liquibaseDataSourceProperties) {
        return createDataSource(liquibaseDataSourceProperties);
    }

    @Setter
    @Getter
    static class DataSourceProperties {
        private String jdbcUrl;
        private String username;
        private String password;
        private String poolName;
        private int maximumPoolSize;
        private int minimumIdle;
        private long connectionTimeout;
    }

}
