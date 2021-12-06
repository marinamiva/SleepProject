/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;
import Client.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



public class PatientManager implements PatientManagerInterface  {

   
    private Connection c;
	
	public PatientManager(Connection connection) {
		this.c=connection;
	}
	
		
        public void addDailyreport(Report rep) {
            try {
                String sql = "INSERT INTO Reports (patient_dni, report_date, quality, exhaustion,hours,movement,time, rest,awake,times,worries, mood, doubts)"
                                + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
                PreparedStatement prep = c.prepareStatement(sql);
                prep.setString(1, rep.getPatdni());
                prep.setDate(2, (java.sql.Date) rep.getTodaysDate());
                prep.setString(3, rep.getsleepQuality());
                prep.setString(4, rep.getExhaustion());
                prep.setString(5, rep.getAverageHours());
                prep.setString(6, rep.getMovement());
                prep.setString(7, rep.gettimeToFallAsleep());
                prep.setString(8, rep.getRest());
                prep.setString(9, rep.getStayAwake());
                prep.setString(10, rep.getTimesAwake());
                prep.setString(11, rep.getWorries());
                prep.setString(12, rep.getTodaysMood());
                prep.setString(13, rep.getdoubtsForDoctor());
                prep.executeUpdate();
                prep.close();
                }
            catch(Exception e) {
                    e.printStackTrace();
                    }
    }
            
    public ArrayList<Report> reportHistory(String dni){
        ArrayList<Report> repList = new ArrayList<Report>();
        try {
                String sql = "SELECT * FROM Reports WHERE patient_dni LIKE ?";
                PreparedStatement prep = c.prepareStatement(sql);
                prep.setString(1, "%"+dni+"%");
                ResultSet rs = prep.executeQuery();
                
                while (rs.next()) {
                    String dni1=rs.getString("patient_dni");
                    java.util.Date repdate=rs.getDate("report_date");
                    String quality=rs.getString("quality");
                    String exhaust=rs.getString("exhaustion");
                    String averageHours=rs.getString("hours");
                    String movem=rs.getString("movement");
                    String timeToFall=rs.getString("time");
                    String res=rs.getString("rest");
                    String awake=rs.getString("awake");
                    String timAwake=rs.getString("times");
                    String dreams=rs.getString("dreams");
                    String worr=rs.getString("worries");
                    String mood=rs.getString("mood");
                    String doubts=rs.getString("doubts");
                    Report repnew = new Report (dni1,repdate, quality, exhaust, averageHours, movem, timeToFall, res, awake, timAwake,dreams, worr, mood, doubts);
                    repList.add(repnew);
                }

        } catch (Exception e) {
                e.printStackTrace();
        }
        return repList;
    }
    
     //This method is exclusively for the graphical interface part, it is just used there 
    public  ObservableList<Report> showReports() {
        ObservableList<Report> repList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM Reports";
            PreparedStatement prep = c.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                String dnipat = rs.getString("patient_dni");
                java.util.Date repdate=rs.getDate("report_date");
                 String quality=rs.getString("quality");
                    String exhaust=rs.getString("exhaustion");
                    String averageHours=rs.getString("hours");
                    String movem=rs.getString("movement");
                    String timeToFall=rs.getString("time");
                    String res=rs.getString("rest");
                    String awake=rs.getString("awake");
                    String timAwake=rs.getString("times");
                    String dreams=rs.getString("dreams");
                    String worr=rs.getString("worries");
                    String mood=rs.getString("mood");
                    String doubts=rs.getString("doubts");
                    Report repnew = new Report (dnipat,repdate, quality, exhaust, averageHours, movem, timeToFall, res, awake, timAwake,dreams, worr, mood, doubts);
                    repList.add(repnew);
            }

        } catch (Exception e) {
                e.printStackTrace();
        }
        return repList;
    }

    public  Report getDailyReport(java.util.Date  dateReport){
        Report newreport = new Report();
        String sql = "SELECT * FROM Reports WHERE Report_date LIKE ?";
        try {
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setString(1, "%"+dateReport+"%");
            ResultSet rs = prep.executeQuery();
            while(rs.next()){
                String dni=rs.getString("patient_dni");
                java.util.Date repdate=rs.getDate("report_date");
                String quality=rs.getString("quality");
                String exhaust=rs.getString("exhaustion");
                String averageHours=rs.getString("hours");
                String movem=rs.getString("movement");
                String timeToFall=rs.getString("time");
                String res=rs.getString("rest");
                String awake=rs.getString("awake");
                String timAwake=rs.getString("times");
                String dreams=rs.getString("dreams");
                String worr=rs.getString("worries");
                String mood=rs.getString("mood");
                String doubts=rs.getString("doubts");
                newreport = new Report(dni,repdate, quality, exhaust, averageHours, movem, timeToFall, res, awake, timAwake, dreams, worr, mood, doubts);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PatientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newreport;
    }
            
    public  ArrayList<Patient> showPatients() {
        ArrayList<Patient> patList = new ArrayList<Patient>();
        try {
            String sql = "SELECT * FROM Patients";
            PreparedStatement prep = c.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                int patId = rs.getInt("patient_id");
                String patName = rs.getString("name");
                String patLastName = rs.getString("lastname");
                String patTele = rs.getString("telephone");
                String patAddress = rs.getString("Address");
                java.util.Date patdob=rs.getDate("dob");
                String patdni = rs.getString("dni");
                String patgender = rs.getString("gender");
                Patient newPatient = new Patient(patId, patName, patLastName, patTele, patAddress, patdob,patdni, patgender);
                patList.add(newPatient);
            }

        } catch (Exception e) {
                e.printStackTrace();
        }
        return patList;
    }

    public void addpatientbyRegister(Patient pat) {
        try {

            String sql = "INSERT INTO Patients (name, lastname, telephone, address,DOB,dni,gender)"
                            + " VALUES (?,?,?,?,?,?,?);";
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setString(1, pat.getName());
            prep.setString(2, pat.getLastname());
            prep.setString(3, pat.getTelephone());
            prep.setString(4, pat.getAddress());
            prep.setDate(5, (java.sql.Date) pat.getDateOfBirth());
            prep.setString(6, pat.getDni());
            prep.setString(7, pat.getGender());
            prep.executeUpdate();
            prep.close();
            }
    catch(Exception e) {
            e.printStackTrace();
            }
    }
  
    @Override
     public  Patient searchSpecificPatientByDNI(String dni){
         Patient patientfound=new Patient();
            try {
                    String sql = "SELECT * FROM Patients WHERE dni LIKE ?";
                    PreparedStatement prep = c.prepareStatement(sql);
                    prep.setString(1, "%"+dni+"%");
                    ResultSet rs = prep.executeQuery();

                    while(rs.next()) {
                            int id = rs.getInt("patient_id");
                            String name = rs.getString("name");
                            String lastname = rs.getString("lastname");
                            String tele = rs.getString("telephone");
                            String address = rs.getString("address");
                            java.util.Date dob=rs.getDate("dob");
                            String gender = rs.getString("gender");
                            patientfound = new Patient(id,name,lastname,tele,address,dob,dni,gender);
                    }
            }catch(Exception e) {
                    e.printStackTrace();
            }
            return patientfound;
     }

    
     public void addBitalinoFrame(Patient pat) {
		try {
     
			String sql = "INSERT INTO Patients (name, lastname, telephone, address,DOB,dni,gender)"
					+ " VALUES (?,?,?,?,?,?,?);";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, pat.getName());
			prep.setString(2, pat.getLastname());
			prep.setString(3, pat.getTelephone());
                        prep.setString(4, pat.getAddress());
                        prep.setDate(5, (java.sql.Date) pat.getDateOfBirth());
                        prep.setString(6, pat.getDni());
                        prep.setString(7, pat.getGender());
			prep.executeUpdate();
			prep.close();
			}
		catch(Exception e) {
			e.printStackTrace();
			}
	}      
     
     public void addEEG(Signals eeg) {
         try{
             String sql = "INSERT INTO EEGs (patient_dni, eeg_date, eeg, eeg_lux) VALUES (?,?,?,?)";
             PreparedStatement prep = c.prepareStatement(sql);
             prep.setString(1, eeg.getDni());
             prep.setDate(2,java.sql.Date.valueOf(eeg.getEegDate()));
             String values=(eeg.getEegValues()).toString();
             String luxvalues=(eeg.getEegLUX()).toString();
             prep.setString(3, values);
             prep.setString(4, luxvalues);
         }catch(SQLException ex){
             ex.printStackTrace();
         }
         
     }
    
     
     public Signals viewEEG(String dni, java.util.Date date) {
         Signals eeg = new Signals();
         ArrayList<Integer> values=new ArrayList<>();
         String[] valuesString;
         Connection c1 = null; 
            try {
			String sql = "SELECT patient_id FROM Patients WHERE DNI = ?";
			PreparedStatement prep = c1.prepareStatement(sql);
			prep.setString(1, "%"+dni+"%");
                        ResultSet rs = prep.executeQuery();
                        int id = rs.getInt("patient_id");
                        
                        String sql1= "SELECT EEG FROM EEGs WHERE patient_id =? AND EEG_DATE= ?";
                        PreparedStatement prep1 = c1.prepareStatement(sql1);
			prep1.setString(1, "%"+id+"%");
                        prep1.setString(2, "%"+date+"%"); 
                        
			ResultSet rs2 = prep1.executeQuery();
                        while (rs2.next()) { 
                            String EEG=rs2.getString("EEG");  
                            valuesString=EEG.split("\\s+"); //LO SEPARA POR ESPACIOS SE SUPONE
                            for (int i=0; i<valuesString.length;i++){
                               values.add(Integer.parseInt(valuesString[i])); 
                            }
                            LocalDate date1 = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                            eeg=new Signals(date1,dni,values); 
                      }	
		}catch(Exception e) {
			e.printStackTrace();
		}
        return eeg;
    }
     public Signals viewEEGLUX(String dni, java.util.Date date) {
         Signals eeg = new Signals();
         ArrayList<Integer> values=new ArrayList<>();
         String[] valuesString;
         Connection c1 = null; 
            try {
			String sql = "SELECT patient_id FROM Patients WHERE DNI = ?";
			PreparedStatement prep = c1.prepareStatement(sql);
			prep.setString(1, "%"+dni+"%");
                        ResultSet rs = prep.executeQuery();
                        int id = rs.getInt("patient_id");
                        
                        String sql1= "SELECT EEG_LUX FROM EEGs WHERE patient_id =? AND EEG_DATE= ?";
                        PreparedStatement prep1 = c1.prepareStatement(sql1);
			prep1.setString(1, "%"+id+"%");
                        prep1.setString(2, "%"+date+"%");  //NO SE SI ESTO ESTA BIEN PORQUE DEBERIA SER SetDate PEOR DA ERROR
                        
			ResultSet rs2 = prep1.executeQuery();
                        while (rs2.next()) { 
                            String EEG=rs2.getString("EEG");  
                            valuesString=EEG.split("\\s+"); //LO SEPARA POR ESPACIOS SE SUPONE
                            for (int i=0; i<valuesString.length;i++){
                               values.add(Integer.parseInt(valuesString[i])); 
                            }
                            LocalDate date1 = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                            eeg=new Signals(date1,dni,values); 
                      }	
		}catch(Exception e) {
			e.printStackTrace();
		}
        return eeg;
    }

    public ArrayList<Signals> viewEEGHistory(String dni) {
         ArrayList<Signals> eegs = new ArrayList<Signals>();
         ArrayList<Integer> values=new ArrayList<>();
         String[] valuesString;
         Connection c1 = null;
            try {
			String sql = "SELECT patient_id FROM Patients WHERE DNI = ?";
			PreparedStatement prep = c1.prepareStatement(sql);
			prep.setString(1, "%"+dni+"%");
                        ResultSet rs = prep.executeQuery();
                        int id = rs.getInt("patient_id");
                        
                        String sql1= "SELECT * FROM EEGs WHERE patient_id =?";
                        PreparedStatement prep1 = c1.prepareStatement(sql1);
			prep1.setString(1, "%"+id+"%");
			ResultSet rs2 = prep1.executeQuery();
                        while (rs2.next()) {
                            String dni1=rs2.getString("patient_dni");
                            java.util.Date date = rs2.getDate("EEG_date");
                            String EEG=rs2.getString("EEG");
                            valuesString=EEG.split("\\s+"); //LO SEPARA POR ESPACIOS SE SUPONE
                            for (int i=0; i<valuesString.length;i++){
                               values.add(Integer.parseInt(valuesString[i]));
                            }
                            LocalDate date1 = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                            Signals eeg= new Signals(date1,dni,values,values);
                            eegs.add(eeg);
                        }
		}catch(Exception e) {
			e.printStackTrace();
		}
            return eegs;
    }
	
	
 private static Database.DBManagerInterface dbman;
    private static PatientManagerInterface pmi;
    private static UserManagerInterface umi;
    private static BufferedReader br;
	public static void main(String[] args) throws IOException, ParseException, Exception {
            dbman = new DBManager();
            dbman.connect();
            pmi = dbman.getPatientManager();
            br = new BufferedReader(new InputStreamReader(System.in));
            String dni="1234";
            byte[] pas=ui.takePasswordAndHashIt(br, "insert password");
            addPatientByRegister();
            //User user =umi.getUserByDNI(dni);
        }
        public static void addPatientByRegister() throws IOException, ParseException{
        try{
        Patient newpat = null;
        System.out.println("Type your name: ");
        String name = br.readLine();
        System.out.println("Type your lastname:");
        String lastname = br.readLine();
        String telephone = ui.takeTelephone(br,"Type your telephone:");
        System.out.println("Type your address: ");
        String address = br.readLine();
        LocalDate data= ui.takeDate(br,"Type your Date of Birth (followed by yyyy-MM-dd)");
        java.util.Date dob = java.sql.Date.valueOf(data);
        String dni = ui.takeDNI(br,"Type your DNI (numeric only)");
        String gender = ui.takeGender(br, "Type your gender: ");
        newpat = new Patient(name, lastname, telephone, address,dob, dni, gender);
        byte[] password = ui.takePasswordAndHashIt(br, "Introduce a password:");
            System.out.println(password);
        //User user = new User(dni, password);
	//umi.createUserRegister(user);
        
        System.out.println("\nYOUR INFORMATION IS: " + newpat+"\n");
        pmi.addpatientbyRegister(newpat);
    
        
        }catch(NullPointerException e){
            e.printStackTrace(); 
        }
    }
	
}
