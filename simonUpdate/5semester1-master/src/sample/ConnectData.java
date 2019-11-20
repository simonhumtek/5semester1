package sample;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectData {
    public Connection connection;

    public Connection getConnection() {

        try {
            connection = DriverManager.getConnection("C:\\Users\\Simon\\Documents\\HumTek\\SD\\5semester1-master\\SQLdatabase.db");


        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }



}