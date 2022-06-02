package ru.yandex.practicum.catsgram;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
public class ManualJdbcConnectService {
    private static final String JDBC_URL = "jdbc:mysql://cat.world:3306/allcats";
    private static final String JDBC_USERNAME = "iamacat";
    private static final String JDBC_PASSWORD = "iamapet";
    private static final String JDBC_DRIVER = "org.mysql.jdbc.Driver";

    public JdbcTemplate getTemplate() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(JDBC_URL);
        dataSource.setUsername(JDBC_USERNAME);
        dataSource.setPassword(JDBC_PASSWORD);
        dataSource.setDriverClassName(JDBC_DRIVER);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    };
}
