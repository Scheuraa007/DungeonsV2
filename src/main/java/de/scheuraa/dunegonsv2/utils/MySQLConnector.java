package de.scheuraa.dunegonsv2.utils;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import lombok.Getter;
import lombok.Setter;

public class MySQLConnector {

    @Getter @Setter
    private static MysqlDataSource dataSource;

    public MySQLConnector() {
        dataSource = new MysqlConnectionPoolDataSource();

    }
}
