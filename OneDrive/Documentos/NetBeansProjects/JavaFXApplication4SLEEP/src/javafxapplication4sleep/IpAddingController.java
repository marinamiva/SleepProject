/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4sleep;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sun.font.TextLabel;

/**
 * FXML Controller class
 *
 * @author mmsan
 */
public class IpAddingController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private Label IpAdding;
    @FXML private Label Explanation;
    @FXML private TextField IP;
    @FXML private Button Save;
    
    
    String ipString;
    InetAddress ip;
    
   public void Introduction (ActionEvent evento) throws IOException{
        
        ipString = IP.getText();
        ip=InetAddress.getByName(ipString);
    }
    
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("IpAdding.fxml"));
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("IP CONNECTION");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
        @FXML
public void gotoWelcome(javafx.event.ActionEvent event) throws IOException {

        
        Parent root = FXMLLoader.load(getClass().getResource("WelcomeWindowApp.fxml"));
        
        Scene Welcomescene = new Scene(root);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(Welcomescene);
        window.show();
       
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
