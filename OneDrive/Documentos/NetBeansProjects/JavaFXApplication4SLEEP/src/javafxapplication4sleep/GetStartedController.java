/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4sleep;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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



public class GetStartedController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
        @FXML private Button btnGETSTARTEDClicked;

    
    
     public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("GetStarted.fxml"));
        
        //will load a different xml when the button is pressed 
        //what we are looking forward is when clicking the button of the getstarted scene changes into the following one
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("SLEEP CONTROL");
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
       @FXML
    private void gotoWelcome(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("WelcomeWindowApp.fxml"));
        
        Scene Loginscene = new Scene(root);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(Loginscene);
        window.show();
       
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

}
