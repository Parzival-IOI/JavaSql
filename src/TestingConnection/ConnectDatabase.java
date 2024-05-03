package TestingConnection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDatabase {
    private Connection conn;
    public ConnectDatabase() throws SQLException {
        conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/Parzival",
                "root",
                "1234"
        );
        System.out.println("Successfully connected to database");
    }

    public void createDatabase() throws SQLException {
        String query = "CREATE DATABASE Parzival";

        Statement statement = conn.createStatement();
        boolean success = statement.execute(query);
        if (success) {
            System.out.println("create database successfully");
        }
    }

    public void createTable() throws SQLException {
        String query = "CREATE TABLE testing (id int primary key, name varchar(255))";
        conn.createStatement().execute(query);
        System.out.println("successfully created table");
    }

    public void insertData() throws SQLException {
        String query = "INSERT INTO testing (id, name) VALUES (? , ?)";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, 2);
        preparedStatement.setString(2, "Art3mis");
        preparedStatement.executeUpdate();

        System.out.println("successfully inserted data");
    }

    public void selectData() throws SQLException {
        String query = "SELECT * FROM testing";
        ResultSet resultSet = conn.createStatement().executeQuery(query);
        printRow(resultSet);
        System.out.println("successfully selected data");
    }

    private void printRow(ResultSet resultset) throws SQLException {
        ResultSetMetaData resultSetMetaData = resultset.getMetaData();
        while (resultset.next()) {
            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                System.out.print("|" + resultset.getString(i) + "|");
            }
            System.out.println();
        }
    }


}
