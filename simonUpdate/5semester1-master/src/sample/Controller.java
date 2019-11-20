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
    TextArea textArea;

    @FXML
    TextField fieldFromStation;

    @FXML
    TextField fieldToStation;

    @FXML
    ComboBox<String> comboBoxStartTime;

    @FXML
    ComboBox<String> comboBoxEndTime;



    private ObservableList<String> timeList = FXCollections.observableArrayList( "00.00", "00.30", "01.00", "01.30", "02.00", "02.30",
            "03.00", "03.30", "04.00", "04.30", "05.00", "05.30", "06.00", "06.30", "07.00", "07.30", "08.00", "08.30", "09.00", "09.30",
            "10.00", "10.30", "11.00", "11.30", "12.00", "12.30", "13.00", "13.30", "14.00", "14.30", "15.00", "15.30", "16.00", "16.30",
            "17.00", "17.30", "18.00", "18.30", "19.00", "19.30", "20.00", "20.30", "21.00", "21.30", "22.00", "22.30", "23.00", "23.30");


    public void initialize() {
        // initialize is called by javafx after the fxml file is read and gui objects are created
        // this cannot be done in the constructor because that happens before FXML loading

        comboBoxStartTime.getItems().addAll(timeList);
    }

    public void StartTime() {
        return;
    }

    public void EndTime() {
        return;
    }

    public void resetButton(ActionEvent actionEvent) {
        fieldFromStation.clear();
        fieldToStation.clear();
        textArea.clear();
    }

    public void findButton(ActionEvent actionevent) throws SQLException {

        ConnectData connectData = new ConnectData();
        Connection connect = connectData.getConnection();

        float value = Float.parseFloat(comboBoxEndTime.getValue());

        String from = fieldFromStation.getText();
        String to = fieldToStation.getText();

        PreparedStatement returnTrainRoute = returnRoute(from, value , to, connect);

        ResultSet result = returnTrainRoute.executeQuery();

        while (result != null & result.next())
        {
            float resultStartTime = result.getFloat(1);
            String resultFromStation = result.getString(2);
            float resultArrivalTime = result.getFloat(3);
            String resultToStation = result.getString(4);
            String results = "Tag tog kl: " + resultStartTime + "\nfra station: " + resultFromStation + "\n" +
                    "\n" + "Ankomst kl: " + resultArrivalTime + "\npå station" + "\n";
            textArea.appendText(results + "\n");
        }

        result.close();
        connect.close();
    }

    public PreparedStatement returnRoute(String FromStationName, float arrivalTime, String ToStationName, Connection connect) throws SQLException {
            PreparedStatement departure = null;
            String query = "Select ds.StartTime, sds.StationName, ar.StartTime, sar.StationName " +
                    "From Times AS ar" +
                    "INNER JOIN " +
                    "Times AS ds ON ar.TrainID = ds.TrainID " +
                    "INNER JOIN " +
                    "Stations AS sar ON ar.StationID = sar.StationID " +
                    "INNER JOIN" +
                    "Stations AS sds ON ds.StationID = sds.StationID " +
                    "WHERE sar.StationName = '" + ToStationName + "'" + "And sar.StartTime <= '" + arrivalTime + "'" + "And ds.StartTime <= ar.StartTime" +
                    "And sds.StationName = '" + FromStationName + "'";

            departure = connect.prepareStatement(query);
            return departure;



    }
}

