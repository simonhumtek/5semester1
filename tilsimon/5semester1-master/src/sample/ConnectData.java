package sample;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectData {
    public Connection connection;

    public Connection getConnection() {

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:SQLdatabase.db");


        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }



}