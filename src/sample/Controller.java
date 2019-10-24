package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.*;


public class Controller {
    TrainModel m=TrainModel.getInstance(); // make a model object when you create the controller
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
    public void initialize() {
        // initialize is called by javafx after the fxml file is read and gui objects are created
        // this cannot be done in the constructor because that happens before FXML loading
        for(String s: TrainModel.getInstance() .getStation()) {
            stat3.getItems().add(s);
        }
    }
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
        return "route from"+stat1+ "\n to "+stat2+ "at" +time;
    }
 }