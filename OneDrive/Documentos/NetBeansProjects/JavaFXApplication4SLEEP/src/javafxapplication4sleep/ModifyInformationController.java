/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4sleep;


import Client.Patient;
import Database.DBManager;
import Database.DBManagerInterface;
import Database.PatientManager;
import Database.PatientManagerInterface;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 */
public class ModifyInformationController implements Initializable {

    /**
     * Initializes the controller class.
     */
      @FXML private Label modifyinformation; //i dont know if its necessary to add here the labels 
    @FXML private TextField Name;
    @FXML private TextField Lastname;
    @FXML private TextField DNI;
    @FXML private TextField Telephone;
    @FXML private TextField Address;
    @FXML private ChoiceBox Gender;
    @FXML private DatePicker DOB;
    @FXML private Button saveModifiedinfo;
    @FXML private Button Back; 
    @FXML private Label saved;
    

    
String name1,lastname1,dni1,telephone1,address1,gender1,username1;
Date date1;

private static Database.DBManagerInterface dbman;
private static PatientManagerInterface pmi;
    
        @FXML void actionSave (javafx.event.ActionEvent evento) throws ParseException {
        name1=Name.getText();
        lastname1=Lastname.getText();
        dni1=DNI.getText();
        telephone1=Telephone.getText();
        address1=Address.getText();
        gender1=Gender.getValue().toString();
        String datestring=DOB.getValue().toString(); //Hay que elegir que lo hagan en un formato concreto (dd/mm/yyy)
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        date1= format.parse(datestring);
        username1=DNI.getText();
        
       dbman = new DBManager();
        dbman.connect();
       // dbm.createTables();
        pmi = dbman.getPatientManager();
        
       Patient patient1 = new Patient(name1,lastname1,telephone1,address1,date1,dni1,gender1);
       //saved.setText("Saved Correctly");
       /*
            REVISAR TODO ESTO PORQUE NO HAY UN WHILE CREADO EN LA FUNCION Y NO SE SI PUEDO TOCARLA PARA QUE
            DEVUELVA TRUE SI SE HA GUARDADO O FALSE Y SEGUN ESO VER QUE MENSAJE DEVOLVER.
       */
       
       pmi.addpatientbyRegister(patient1);
       
    }
    
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("ModifyInformation.fxml"));
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Modify Information");
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
        Gender.getItems().addAll("Male", "Female");
        // TODO
    }    
    
}
