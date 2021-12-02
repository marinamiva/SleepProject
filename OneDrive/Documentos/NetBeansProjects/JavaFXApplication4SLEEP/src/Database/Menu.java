/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Client.Patient;
import Client.ui;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class Menu {

    private Connection c;
    private static DBManagerInterface dbm;
    private static Database.DBManagerInterface dbman;
    private static PatientManagerInterface pmi;
    private static UserManagerInterface umi;
    private static BufferedReader br;
    private  PatientManager pm;

    public static void main(String[] args) throws IOException, ParseException, Exception {
        dbman = new DBManager();
        dbman.connect();
        //dbm.createTables();
      pmi = dbman.getPatientManager();
      
      

        br = new BufferedReader(new InputStreamReader(System.in));

        Scanner sc = new Scanner(System.in);

        System.out.println("Hello, type the option you want: 1. Add patient, 2. Search patient by DNI: ");
        int number = sc.nextInt();

        switch (number) {

            case 1:
                addPatient();
                break;
            case 2: 
                searchbyDNI();
        }

    }


    public static void addPatient() throws IOException, ParseException {
        try{
        Patient newpat = null;
        System.out.println("Type the name of the patient you'll add");
        String name = br.readLine();
        System.out.println("Type the lastname of the patient:");
        String lastname = br.readLine();
        String telephone = ui.takeTelephone(br,"Type the telephone of the patient");
        System.out.println("Type the address of the patient");
        String address = br.readLine();
        LocalDate data= ui.takeDate(br,"Type the Date of Birth of the patient followed by yyyy-MM-dd");
        java.util.Date dob = java.sql.Date.valueOf(data);
        String dni = ui.takeDNI(br,"Type the DNI of the patient (numeric only)");
        String gender = ui.takeGender(br, "Type the gender of the patient: ");
        newpat = new Patient(name, lastname, telephone, address,dob, dni, gender);
        System.out.println("The new patient is: " + newpat);
        pmi.addpatientbyRegister(newpat);
    
        
    }catch(NullPointerException e){
        e.printStackTrace(); 
    }
    }
    
    public static void searchbyDNI() throws IOException{
        System.out.println("Type the dni of the patient you want to search" );
        String dniobtained = br.readLine();
        Patient newpat = pmi.searchSpecificPatientByDNI(dniobtained);
        System.out.println("The patient is:" +newpat.toString());
       
    }
    
    
}
