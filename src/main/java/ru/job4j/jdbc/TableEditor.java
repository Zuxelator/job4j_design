package ru.job4j.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try (FileInputStream fis = new FileInputStream("data\\app.properties")) {
            properties.load(fis);
            connection = DriverManager.getConnection(properties.getProperty("url"),
                    properties.getProperty("login"), properties.getProperty("password"));
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) throws Exception {
            executor(String.format(
                    "create table if not exists %s(%s, %s);",
                    tableName,
                    "id serial primary key"
            ));
    }

    public void dropTable(String tableName) {
        executor(String.format("drop table %s;", tableName));
    }

    public void addColumn(String tableName, String columnName, String type) {
        executor(String.format("ALTER TABLE %s ADD COLUMN %s %S;", tableName, columnName, type));
    }

    public void dropColumn(String tableName, String columnName) {
        executor(String.format("ALTER TABLE %s DROP COLUMN %s;", tableName, columnName));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        executor(String.format("ALTER TABLE %s RENAME COLUMN %s TO %s", tableName, columnName, newColumnName));
    }

    private void executor(String query) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(query);
            statement.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}