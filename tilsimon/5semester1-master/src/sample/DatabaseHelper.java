package sample;

import java.sql.*;
import java.util.ArrayList;

/*
 Hj;lper klasse der skal fixe dalt med databasen
 */
public class DatabaseHelper {

    /*private static Connection conn = null;

    //https://www.sqlitetutorial.net/sqlite-java/sqlite-jdbc-driver/
    public static void connect() {

        try {
            // db parameters
            String url = "jdbc:sqlite:SQLdatabase.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static ArrayList<String>  getAllStations(){
        String sql = "SELECT StationName FROM Station";

        ArrayList<String> stations = new ArrayList<>();

        if (DatabaseHelper.conn == null) DatabaseHelper.connect();
        ResultSet rs;
        try (Statement stmt = conn.createStatement()) {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
               stations.add(rs.getString("Station"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stations;

    }
    public static ArrayList<String>  getAllTimes(){
        String sql = "SELECT Time FROM Time";

        ArrayList<String> times = new ArrayList<>();

        if (DatabaseHelper.conn == null) DatabaseHelper.connect();
        ResultSet rs;
        try (Statement stmt = conn.createStatement()) {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                times.add(rs.getString("Time"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return times;

    }

*/

}

