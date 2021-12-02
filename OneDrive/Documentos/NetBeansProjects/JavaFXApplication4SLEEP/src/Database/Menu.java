/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Client.Patient;
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

/**
 *
 * @author gabri
 */
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
       // dbm.createTables();
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
        System.out.println("Type the telephone of the patient");
        String telephone = br.readLine();
        System.out.println("Type the address of the patient");
        String address = br.readLine();
        System.out.println("Type the Date of Birth of the patient followed by dd/mm/yyyy");
        //String date = br.readLine();
        //SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
        String data= br.readLine();
        java.util.Date dateBirth = new java.util.Date(data);
        long birth = dateBirth.getTime();
        
        //java.util.Date dateBirth =  formato.parse(data);
        
        java.sql.Date dob = new java.sql.Date(birth); //LA FECHA SE METE MAL 
       
        //System.out.println("UTIL DATE: " + dateBirth);
         //System.out.println("SQL DATE: " + dob);
        
         System.out.println("Type the DNI of the patient");
        String dni = br.readLine();
        System.out.println("Type the gender of the patient: ");
        String gender = br.readLine();
       
        
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
