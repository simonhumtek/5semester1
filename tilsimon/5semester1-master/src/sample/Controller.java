package sample;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import java.awt.event.*;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.*;
import java.util.ArrayList;

import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class Controller {

    @FXML
    TextArea resultTextArea;
    @FXML
    TextField textFieldFromStation;
    @FXML
    TextField textFieldToStation;
    //@FXML
    //ComboBox<String> comboBoxStartTime;
    @FXML
    ComboBox<String> comboBoxTime;
    @FXML
    ComboBox<String> comboBoxRoute;


    public void initialize() {
        // initialize is called by javafx after the fxml file is read and gui objects are created
        // this cannot be done in the constructor because that happens before FXML loading

        ObservableList<String> boxList = FXCollections.observableArrayList("00.00", "00.30", "01.00", "01.30", "02.00", "02.30",
                "03.00", "03.30", "04.00", "04.30", "05.00", "05.30", "06.00", "06.30", "07.00", "07.30", "08.00", "08.30", "09.00", "09.30",
                "10.00", "10.30", "11.00", "11.30", "12.00");

        ObservableList<String> boxRoute = FXCollections.observableArrayList("KbhOde", "OdeKbh", "NkfKbh", "KbhNkf");


        comboBoxTime.getItems().addAll(boxList);
        comboBoxRoute.getItems().addAll(boxRoute);
    }

    public void StartTime() {
        return;
    }

    public void resetButton(ActionEvent actionEvent) {
        textFieldFromStation.clear();
        textFieldToStation.clear();
        resultTextArea.clear();
    }

    public void findButton(ActionEvent actionevent) throws SQLException {

        ConnectData connectData = new ConnectData();
        Connection connect = connectData.getConnection();

        float value = Float.parseFloat(comboBoxTime.getValue());
       // float value2 = Float.parseFloat(comboBoxRoute.getValue());

        String from = textFieldFromStation.getText();
        String to = textFieldToStation.getText();

        PreparedStatement returnTrainRoute = returnRoute(from, value, to, connect);

        ResultSet result = returnTrainRoute.executeQuery();

        while (result != null & result.next()) {
            float resultStartTime = result.getFloat(1);
            String resultFromStation = result.getString(2);
            float resultArrivalTime = result.getFloat(3);
            String resultToStation = result.getString(4);
            String results = "Tag tog kl: " + resultStartTime + "\nfra station: " + resultFromStation + "\n" +
                    "\n" + "Ankomst kl: " + resultArrivalTime + "\npå station: " + resultToStation + " \n";
            resultTextArea.appendText(results);
        }

        result.close();
        connect.close();
    }


    public PreparedStatement returnRoute(String FromStationName, float arrivalTime, String ToStationName, Connection connect) throws SQLException {

            PreparedStatement departure = null;

            String query =
                    "Select var2.Time, var3.StationID, var1.StartTime, var4.StationName " +
                    "From Time AS var1 " +
                    "INNER JOIN " +
                    "Time AS var2 ON var1.TrainID = var2.TrainID " +
                    "INNER JOIN " +
                    "Station AS var4 ON ar.StationID = var4.StationID " +
                    "INNER JOIN " +
                    "Station AS var3 ON ds.StationID = var3.StationID " +
                    "WHERE var4.StationName = '" + ToStationName + "'" + "And var4.StartTime <= '" + arrivalTime + "'" + "And var2.StartTime <= var1.StartTime" +
                    "And var3.StationName = '" + FromStationName + "'";

            departure = connect.prepareStatement(query);
            return departure;

            /*

            ar = var1
            ds = var2
            sds = var3
            sar = var4

             */
    }
}

