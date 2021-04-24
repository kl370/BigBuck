package model;

import java.sql.*;

public class DB {
    private Connection conn = null;
    public void connect(String url) throws SQLException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        conn = DriverManager.getConnection(url, "projectdb", "123456");
    }
    public void executeUpdate(String statementstring) throws SQLException {
        Statement statement  = conn.createStatement();
        statement.executeUpdate(statementstring);
    }
    public void close(){
        try { conn.close(); } catch (Exception ignored) {};
    }

    public ResultSet executeQuery(String statementstring) {
        ResultSet result = null;
        try {
            Statement statement = conn.createStatement();
            result = statement.executeQuery(statementstring);
        } catch (Exception ex) {
            return null;
        }
        return result;
    }
}
