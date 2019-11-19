package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
//import com.mysql.cj.xdevapi.Table;
import java.sql.*;


public class Controller {
    // TrainModel m=TrainModel.getInstance(); // make a model object when you create the controller
    @FXML
    TextField stat1;
    @FXML
    TextField stat2;
    @FXML
    TextField time;
    @FXML
    TextArea res;
    @FXML
    ComboBox stat3;
    @FXML
    ComboBox stat4;
    @FXML
    ComboBox From; //

    @FXML
    ComboBox To;

    @FXML
    TextArea rejser;

    @FXML
    ComboBox Timer;

    Connection conn;

    private Connection connect() {
        // SQLite connection string

        String url = "jdbc:sqlite:RejsePlanen123.sqlite";

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void selectAll() throws SQLException {
        String sql = "SELECT * FROM employees";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM stations");
        while (resultSet.next()) {
            From.getItems().add(resultSet.getString("name"));
            To.getItems().add(resultSet.getString("name"));
            //System.out.println(resultSet.getString("name"));
        }
    }

    @FXML
    public void setTimes() {
        for (int i = 0; i < 13; i++) {
            Timer.getItems().add(i + ".00");
        }
    }

    @FXML
    public void initialize() throws SQLException {
        connect();
        selectAll();
        setTimes();

        //comboBoxTo.getItems().addAll(getAllStations());
        //comboBoxFrom.getItems().addAll(getAllStations());

    }

    public void checkRejse() throws SQLException {
        Statement statement = conn.createStatement();
        String sFrom = String.valueOf(From.getValue());
        String sTo = String.valueOf(To.getValue());
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Rejser where fratid > " + Timer.getValue() + " AND afgangsStation = '" + sFrom + "' AND ankomstStation = '" + sTo + "'");
        while (resultSet.next()) {
            rejser.setText(rejser.getText() + "Fra " + resultSet.getString(3) + " klokken " + resultSet.getString(4) + " Til " + resultSet.getString(5) + " klokken " + resultSet.getString(6));

        }
        rejser.setWrapText(true);
    }
}
    /*
    @FXML
    public void initialize() {
        // initialize is called by javafx after the fxml file is read and gui objects are created
        // this cannot be done in the constructor because that happens before FXML loading
        for(String s: TrainModel.getInstance() .getStation()) {
            stat3.getItems().add(s);
        }
        DatabaseHelper.connect();
        ArrayList<String> stations = DatabaseHelper.getAllStations();
        ArrayList<String> times = DatabaseHelper.getAllTimes();
        System.out.println(stations);
        System.out.println(times);
    }
     */
    /*
    @FXML
    public void RouteHandler (ActionEvent e) {
        System.out.println("find route");
        res.setText(m.findRoute(stat1.getText(),stat2.getText(), time.getText()));
    }
}

 class TrainModel {  //is a Singleton!
    //variable for database connection
    private TrainModel() { }// initialise database connection}
    static TrainModel inst;
    static TrainModel getInstance() {if (inst==null) inst=new TrainModel(); return inst;}

    String[] getStation() {String[] s={"København","Roskilde", "Odense"}; return s;}
    String findRoute(String stat1,String stat2, String time) {
        //make query to database - make it into a string - return string
        return "route from "+stat1+ "\n to "+stat2+ "at " +time;
    }
 }

     */