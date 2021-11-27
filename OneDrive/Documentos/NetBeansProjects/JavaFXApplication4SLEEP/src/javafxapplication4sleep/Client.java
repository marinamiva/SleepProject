/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4sleep;

import Client.Patient;
import Database.DBManager;
import Database.DBManagerInterface;
import Database.PatientManagerInterface;
import Database.UserManagerInterface;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author marin
 */
public class Client extends Application {
    
    private static DBManagerInterface dbm;
    private static Database.DBManagerInterface dbman;
    private static PatientManagerInterface pmi; 
    private static UserManagerInterface umi;
    private static BufferedReader br;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("GetStarted.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    

    public static void main(String[] args) throws Exception {
        launch(args);
        dbman=new DBManager();
        dbm.connect();
        dbm.createTables();
        
    }
}
        /*
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Hello, type the option you want: 1. Add patient, 2: Todavia no");
        int number = sc.nextInt();
        
        switch(number){
            
            case 1: 
                addPatient();
        }
        
    }
        
        public static void  addPatient() throws IOException {
            System.out.println("Type the name of the patient you'll add");
            String name = br.readLine();
            System.out.println("Type the lastname of the patient:");
             String lastname = br.readLine();
             System.out.println("Type the DNI of the patient");
             String dni = br.readLine();
             System.out.println("Type the telephone of the patient");
             String telephone = br.readLine();
             System.out.println("Type the address of the patient");
             String address = br.readLine();
             System.out.println("Type the Date of Birth of the patient followed by /");
             String date = br.readLine();
             System.out.println("Type the gender of the patient: ");
             String gender = br.readLine();
             
             Patient newpat = new Patient(name, lastname, dni, telephone,  address, gender);
             pmi.addpatientbyRegister(newpat);
        }
        
    }
    

*/

