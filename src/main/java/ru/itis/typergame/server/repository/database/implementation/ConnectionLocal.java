package ru.itis.typergame.server.repository.database.implementation;

import org.jetbrains.annotations.NotNull;

import ru.itis.typergame.server.repository.database.ConnectionGiver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionLocal implements ConnectionGiver {


    private  final String JDBC_DRIVER ="org.PostgreSQL.Driver";
    private  final String DATABASE_URL ="jdbc:postgresql://localhost:5432/Kursach";
//    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/Kursach";
    private  final String DATABASE_USER ="postgres";
    private  final String DATABASE_PASSWORD ="123456789";

    public Connection getConnection() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
    }

}
