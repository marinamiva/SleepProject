/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4sleep;

import Client.Patient;
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
import java.util.ArrayList;
import javafx.scene.control.*;
import java.util.Date;
import java.util.List;
import static javafx.application.Application.launch;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 */
public class ViewEEGController implements Initializable {

    
   private static PatientManagerInterface pmi;
   private static Report rep;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private LineChart<?, ?> eegGraphic;
    @FXML private Button goBack;
    private XYChart.Series series;
    
    private List<Integer> eegValues = new ArrayList();
    private Patient patient;
    
       public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("ViewEEG.fxml"));
        
        Scene scene = new Scene(root);
        
        eegValues = patient.getRecordedEEG();
        
        if(!eegValues.isEmpty()){
            for (int i = 0; i < eegValues.size() ; i++) {
               series.getData().add(new XYChart.Data(""+i,eegValues.get(i)));  
            }
           
            eegGraphic.setCreateSymbols(false);
            eegGraphic.getData().addAll(series);
        }
        
        primaryStage.setTitle("EEG RECORDED");
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
       
        series = new XYChart.Series();   
        
      //  XYChart.Series series = new  XYChart.Series<>();
       
      //series.getData().add(pmi.viewEEG(dni, date)); // o sería agregandole aqui cada valor del bitalino que va recording 
    }    
    
}
