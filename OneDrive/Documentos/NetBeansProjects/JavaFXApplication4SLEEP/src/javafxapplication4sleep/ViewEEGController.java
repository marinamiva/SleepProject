/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4sleep;

import Client.Report;
import Database.PatientManagerInterface;
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
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 */
public class ViewEEGController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private LineChart eegtablevalues;
    @FXML private Button goBack;
    
   private static PatientManagerInterface pmi;
   private static Report rep;
    
       public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("ViewEEG.fxml"));
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("EEG RECORDING");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
     
       
        @FXML
    private void goBackOptionsMenu(javafx.event.ActionEvent event) throws IOException {
               
        Parent root = FXMLLoader.load(getClass().getResource("OptionsMenu.fxml"));
        
        Scene optionsmenu = new Scene(root);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(optionsmenu);
        window.show();
    }
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       XYChart.Series series = new  XYChart.Series<>();
       
      //series.getData().add(pmi.viewEEG(dni, date)); // o sería agregandole aqui cada valor del bitalino que va recording 
    }    
    
}
