package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private static DB database;

    public User() {
        database = new DB();
        try {
            database.connect("jdbc:mysql://localhost:3306/fintech512project?serverTimezone=UTC");
        }
        catch(SQLException e){
            int a = 0;
        }
    }

    public boolean register(String username, String password){
        boolean result = false;
        try {
            database.executeUpdate("DELETE FROM users WHERE username = '" + username + "';");
            database.executeUpdate("INSERT INTO users (username, password) VALUES ('" + username
                    + "', '" + password + "');");
            result = true;
        } catch (SQLException throwables) {
            result = false;
            throwables.printStackTrace();
        } finally {
            database.close();
        }
        return result;
    }

    public boolean login(String username, String password) {
        boolean result = false;

        try {
            ResultSet resultset = database.executeQuery("select * from users where username = '" + username + "';");
            if (!resultset.next()) {
                System.out.println("no result");
            } else {
                String passw = resultset.getString("password");
                if (passw.equals(password)) {
                    result = true;
                }
            }
        } catch (SQLException var10) {
            result = false;
        } finally {
            database.close();
        }
        return result;

    }
    public static void main() {

    }
}

