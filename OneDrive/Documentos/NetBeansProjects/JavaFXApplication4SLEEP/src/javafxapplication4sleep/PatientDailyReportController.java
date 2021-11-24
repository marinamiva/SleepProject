/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4sleep;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import java.awt.event.ActionEvent;
import javafx.application.Application;
import javafx.fxml.*;
import javafx.scene.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javafx.scene.control.*;
import java.util.Date;
import static javafx.application.Application.launch;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 */
public class PatientDailyReportController implements Initializable {

//implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    //llamar al date, da fallo por que no lo encuentra, ya que tenemos que conseguir conectarlo
 
    @FXML private Label patientDailyReport;
    @FXML private TextField answer1;
    @FXML private TextField answer2;
    @FXML private TextField answer3;
    @FXML private TextField answer4;
    @FXML private TextField answer5;
    @FXML private TextField answer6;
    @FXML private TextField answer7;
    @FXML private TextField answer8;
    @FXML private TextField answer9;
    @FXML private TextField answer10;
    @FXML private TextField answer11;
    @FXML private TextArea doughts;
    @FXML private Button save;
    @FXML private Button back;


    String sleepQuality, exhaustion, averageHours, movement, timeToFallAsleep, rest, stayAwake, timesAwake, dreams, worries, todaysMood, doubtsForDoctor;
    Date date;//no lleva a nada, preguntar alberto
    
    @FXML void saveReport (ActionEvent evento) throws ParseException {
        
        date = new Date();
        
        // new label for date
        // Casting de date a String
        // dateLabel.setText("STRING QUE TENGO EN LA VARIABLE ANTERIOR");
        sleepQuality=answer1.getText();
        exhaustion=answer2.getText();
        averageHours=answer3.getText();
        movement=answer4.getText();
        timeToFallAsleep=answer5.getText();
        rest=answer6.getText();
        stayAwake=answer7.getText();
        timesAwake=answer8.getText();
        dreams=answer9.getText();
        worries=answer10.getText();
        todaysMood=answer11.getText();
        doubtsForDoctor=doughts.getText();
        
        
        //Report report1 = new Report (date,sleepQuality, exhaustion, averageHours, movement, timeToFallAsleep, rest, stayAwake, timesAwake, dreams, worries, todaysMood, doubtsForDoctor);
    }
        
      public void start(Stage primaryStage) throws Exception {

        Parent root1 = FXMLLoader.load(getClass().getResource("ReportHistory.fxml"));
        
        Scene scene = new Scene(root1);
        
        primaryStage.setTitle("Daily Report");
        primaryStage.setScene(scene);
        primaryStage.show();
    }      
           
       public void goBackClientMenu (javafx.event.ActionEvent event) throws IOException{
           Parent root = FXMLLoader.load(getClass().getResource("OptionsMenu.fxml"));
        
        Scene clientMenu = new Scene(root);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(clientMenu);
        window.show();
     }
     
    
   /* 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
