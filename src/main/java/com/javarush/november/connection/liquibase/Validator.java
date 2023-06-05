package com.javarush.november.connection.liquibase;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.Scope;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Validator {
    public void getConnection() {

        Map<String, Object> config = new HashMap<>();
        try (Connection connection = ConnectionData.getConnection()) {
            createConnection(config, connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createConnection(Map<String, Object> config, Connection connection) {
        try {
            Scope.child(config, () -> {
                Database database = DatabaseFactory
                        .getInstance()
                        .findCorrectDatabaseImplementation(new JdbcConnection(connection));
                Liquibase liquibase = new Liquibase("liquibase/changelog.xml",
                        new ClassLoaderResourceAccessor(), database);
                liquibase.update(new Contexts(), new LabelExpression());
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
