/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4sleep;

import Client.Report;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 *
 * @author mmsan
 */
public class DailyReportController implements Initializable {
    
    @FXML private Label patientDailyReport;
    @FXML private TextField Answer1;
    @FXML private TextField Answer2;
    @FXML private TextField Answer3;
    @FXML private TextField Answer4;
    @FXML private TextField Answer5;
    @FXML private TextField Answer6;
    @FXML private TextField Answer7;
    @FXML private TextField Answer8;
    @FXML private TextField Answer9;
    @FXML private TextField Answer10;
    @FXML private TextField Answer11;
    @FXML private TextField Doughts;
    @FXML private Button save;
    @FXML private Button back;
    
    String dateActual, answer1, answer2, answer3, answer4, answer5, answer6, answer7, answer8, answer9, answer10, answer11, doughts;
    Date date;
    
     @FXML void actionSave (javafx.event.ActionEvent evento) throws ParseException {
        answer1=Answer1.getText();
        answer2=Answer2.getText();
        answer3=Answer3.getText();
        answer4=Answer4.getText();
        answer5=Answer5.getText();
        answer6=Answer6.getText();
        answer7=Answer7.getText();
        answer8=Answer8.getText();
        answer9=Answer9.getText();
        answer10=Answer10.getText();
        answer11=Answer11.getText();
        doughts=Doughts.getText();
        dateActual = actualDate();
        SimpleDateFormat formatFinalDate = new SimpleDateFormat("dd/MM/yyyy");
        date=formatFinalDate.parse(dateActual) ;
       
        
        Report report1 = new Report (date,answer1,answer2, answer3, answer4, answer5, answer6, answer7, answer8, answer9, answer10, answer11, doughts);
        
        
    
     //String sleepQuality, String exhaustion,String averageHours,String movement,String timeToFallAsleep,String rest,String stayAwake, String timesAwake, String dreams, String worries, String todaysMood, String doubtsForDoctor
     }
     
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("DailyReport.fxml"));
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("DAILY REPORT");
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
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public static String actualDate(){
        Date actual = new Date();
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        
        return formatDate.format(actual);
    }
}
