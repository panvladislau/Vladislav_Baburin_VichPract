package code.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private String database;

    public DB(String database){
        this.database = database;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;";
        String user = "user=admin;";
        String password = "password=admin;";
        String database = String.format("database=%s;", this.database);
        String connectionUrl = url + database + user + password;
        return DriverManager.getConnection(connectionUrl);
    }

    public static void connectionFailed() {
        System.out.println("You can't you the program without correct Database connection.");
        System.exit(0);
    }
}