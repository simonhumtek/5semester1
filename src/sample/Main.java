package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;

import java.util.Scanner;

public class Main extends Application {
    public static void main(String[] args) {launch(args); }
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Mine Rejseplan");
        primaryStage.setScene(new Scene(root, 500, 300));
        primaryStage.show();
    }

    trainMPS mainTrain = new trainMPS();
    Connection conn = null;
       try {
        String url = "jdbc:sqlite:C:/sqlite/SQLdatabase.db";
        conn = mainTrain.connect(url);
// con.setAutoCommit (false);
        //Select;
        // Denne kode ikke relevant da vi benytter en GUI
        /*
        System.out.println("Hvilken station rejser du fra??");
        Scanner scanner = new Scanner(System.in);
        String student = scanner.nextLine();
        System.out.println("Hvilken station vil du gerne til?");
        String course = scanner.nextLine();
        System.out.println("Hvad tid vil du gerne afsted??");
        int scgrade = scanner.nextInt();
        PreparedStatement pstmt = mainTrain.Updatepreparedstatement(conn);
        pstmt.setInt(1, scgrade);
        pstmt.setString(2, student);
        pstmt.setString(3, course);
        int rowAffected = pstmt.executeUpdate();
        System.out.println(String.format("Rows affected %d", rowAffected));
        ResultSet res = mainTrain.plainstatement(student, conn);
        mainTrain.PresentStudents(res);
        pstmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
       */


}
