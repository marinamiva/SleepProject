/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;
import Client.Patient;
import Client.Report;
import Client.ui;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;



public class MenuPrueba {

    private Connection c;
    private static Database.DBManagerInterface dbman;
    private static PatientManagerInterface pmi;
    private static UserManagerInterface umi;
    private static BufferedReader br;
    private  PatientManager pm;

    public static void main(String[] args) throws IOException, ParseException, Exception {
        dbman = new DBManager();
        dbman.connect();
        dbman.createTables();
        //dbman.deleteTables();
        pmi = dbman.getPatientManager();
      
      

        br = new BufferedReader(new InputStreamReader(System.in));

        Scanner sc = new Scanner(System.in);
        
        while(true){
        System.out.println("Hello, type the option you want: 1. Add patient \n 2. Search patient by DNI: \n 3. Show patients: \n 4.Get patient by its id \n 5. Add Report \n 6.Report History \n 7. Get report\n ");
        int number=sc.nextInt();
        
        switch (number) {

            case 1:
                addPatient();
                break;
            case 2: 
                searchbyDNI();
                break;
            case 3: 
                showPatients();
                break;
            case 4:
                //getPatientbyId();
                break;
            case 5:
                //addDailyReport();
                break;
            case 6:
                //reportHistory();
                break;
            case 7:
                getReport();
                break;
            case 0:
                System.exit(0);
        }

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
    
    public static void showPatients(){ 
        ArrayList<Patient> pats = new ArrayList<Patient>();
        
        Patient newpat;
        pats = pmi.showPatients();
        Iterator it = pats.iterator();
        
        while(it.hasNext()){
            newpat = (Patient) it.next();
            System.out.println(newpat.toString());
            System.out.println("");
        }
}
    
    
    
    public static void addDailyReport(String dni) throws IOException{
        
        LocalDate data= ui.takeDate(br,"Enter today's date like this yyyy-MM-dd");
        java.util.Date todaysdate = java.sql.Date.valueOf(data);
        System.out.println("Have you slept well during the night?");
        String quality = br.readLine();
        System.out.println("Do you feel exhausted like you didnâ€™t sleep through the night?");
        String exhausted = br.readLine();
        System.out.println("What is the average of hours you sleep daily?");
        String avgHours = br.readLine();
        System.out.println("Do you stir a lot during the night");
        String movement = br.readLine();
        System.out.println("How long until you fall asleep? Does it take too long or a few minutes?");
        String timeToFallAsleep = br.readLine();
        System.out.println("Have you rested?");
        String rest = br.readLine();
        System.out.println("Are you able to stay awake during the day?");
        String awake = br.readLine();
        System.out.println("How many times did you wake up during the night?");
        String timesAwake = br.readLine();
        System.out.println("Do you remember the dream?");
        String dream = br.readLine();
        System.out.println("Do you feel nervous or have any worries?");
        String worries = br.readLine();
        System.out.println("How do you feel today? Symptons?");
        String mood = br.readLine();
        System.out.println("Doubts for the doctor");
        String doubtsDoctor = br.readLine();
        
        Report newRep = new Report(dni,todaysdate, quality, exhausted, avgHours, movement, timeToFallAsleep, rest, awake, timesAwake, dream, worries, mood, doubtsDoctor );
        System.out.println("The report introduced is" +newRep);
        pmi.addDailyreport(newRep);
        System.out.println("Report added succesfully");
    }
    
       public static void getReport() throws IOException{
         LocalDate data= ui.takeDate(br,"Type the day of the report you want to get like this yyyy-MM-dd");
        java.util.Date repsday = java.sql.Date.valueOf(data);
        
        Report newrepobtained = pmi.getDailyReport(repsday);
        System.out.println("The report is: " +newrepobtained);
    }
       
      /* public static void reportHistory(){
            ArrayList<Report> reps = new ArrayList<Report>();

          Report newrepo;
          reps = pmi.reportHistory();
          Iterator it = reps.iterator();

          while(it.hasNext()){
              newrepo = (Report) it.next();
              System.out.println(newrepo.toString());
              System.out.println("");
          }
       }*/
       
       
}
