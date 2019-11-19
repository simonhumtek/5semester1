package sample;

import java.sql.*;
import java.util.Scanner;


public class trainMPS {

    public Connection connect(String url)
            throws SQLException {
        return DriverManager.getConnection(url);
    }

    public ResultSet plainstatement(String student, Connection conn)
            throws SQLException {
        String query = "select Station.Station As Station, Train.RouteID As RouteID, Time.Time As Time from Time" +
                " INNER JOIN Station ON Station.Studentid = Study.Studentid " +
                "INNER JOIN Courses ON Courses.Courseid = Study.Courseid" +
                " Where Students.Name = '" + student + "'";
        Statement stmt = null;
        ResultSet res = null;
        stmt = conn.createStatement();
        res = stmt.executeQuery(query);
        return res;
    }

}
