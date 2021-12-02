/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;
import Client.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.*;



public class PatientManager implements PatientManagerInterface  {

   
    private Connection c;
	
	public PatientManager(Connection connection) {
		this.c=connection;
	}
	
	
	public static Report viewReport(String dni, java.util.Date dateRep) {
            Report rep = new Report();
            Connection c1 = null;
            
            try {
			String sql = "SELECT patient_id FROM Patients WHERE DNI = ?";
			PreparedStatement prep = c1.prepareStatement(sql);
			prep.setString(1, "%"+dni+"%");
                        ResultSet rs = prep.executeQuery();
                        int id = rs.getInt("patient_id");
                        
                        String sql1= "SELECT REPORT FROM Reports WHERE patient_id =? AND DATE_REPORT LIKE =?";
                        PreparedStatement prep2 = c1.prepareStatement(sql);
			prep.setString(1, "%"+id+"%");
                        prep.setString(2, "%"+dateRep+"%");
			ResultSet rs2 = prep.executeQuery();
                        while (rs.next()) {
                            java.sql.Date datesql= (java.sql.Date) rs.getDate("report_date");
                            java.util.Date  dat = new java.util.Date(datesql.getTime());
                            String quality=rs.getString("quality");
                            String exhaust=rs.getString("exhaustion");
                            String averageHours=rs.getString("hours");
                            String movem=rs.getString("movement");
                            String timeToFall=rs.getString("time");
                            String res=rs.getString("rest");
                            String awake=rs.getString("awake");
                            String timAwake=rs.getString("times awake");
                            String dreams=rs.getString("dreams");
                            String worr=rs.getString("worries");
                            String mood=rs.getString("mood");
                            String doubts=rs.getString("doubts");
                            rep=new Report(dat,quality,exhaust,averageHours,movem,timeToFall,res,awake,timAwake,dreams,worr,mood,doubts);
                        }
				
			
		}catch(Exception e) {
			e.printStackTrace();
		}
            return rep;
	}
	
	public  ArrayList<Patient> showPatients() {
		ArrayList<Patient> patList = new ArrayList<Patient>();
                //Connection c1 = null; //ESTO NO ES ASÍ, SÓLO QUE HAY QUE INICIALIZARLA PARA QUE NO DE ERROR
		try {
			String sql = "SELECT * FROM Patients";
			PreparedStatement prep = c.prepareStatement(sql);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int patId = rs.getInt("patient_id");
				String patName = rs.getString("name");
				String patAddress = rs.getString("lastname");
				String patTele = rs.getString("telephone");
				
				Patient newPatient = new Patient(patId, patName, patAddress, patTele);
				patList.add(newPatient);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return patList;
	}

    
	
    @Override
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
				//int id = rs.getInt("patient_id");
				String name = rs.getString("name");
				String lastname = rs.getString("lastname");
                                                                String tele = rs.getString("telephone");
                                                                String address = rs.getString("address");

                                                                java.util.Date  dayofbirth= rs.getDate("dob");
                                                                  
                                                                  java.sql.Date dob = new java.sql.Date(dayofbirth.getDate()); //LA FECHA SE METE MAL 
				//java.sql.Date dobsql = rs.getDate("dob");
                                //java.util.Date  dob = new java.util.Date(dobsql.getTime());
				String gender = rs.getString("gender");
				patientfound = new Patient(name,lastname,tele,address,dob,dni,gender);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return patientfound;
         }

	public static Patient getPatient(int pat_id) {
                Patient pat = new Patient();
                Connection c1 = null;
                
		try {
			String sql = "SELECT * FROM Patients WHERE patient_id LIKE ?;";
			PreparedStatement prep = c1.prepareStatement(sql);
			prep.setInt(1, pat_id);
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("patient_id");
				String name = rs.getString("name");
				String lastname = rs.getString("lastname");
                                String telephone = rs.getString("lastname");
                                pat.setName(name);
                                pat.setLastname(lastname);
                                pat.setTelephone(telephone);
                                
			
		}
	}catch(Exception e) {
		e.printStackTrace();
	}
		return pat;
		
	}

   public static ArrayList<Report> viewReportHistory(String dni) {
            
           ArrayList<Report> repList = new ArrayList<Report>(); 
           Connection c1 = null;
            try {
			String sql = "SELECT patient_id FROM Patients WHERE DNI = ?";
			PreparedStatement prep = c1.prepareStatement(sql);
			prep.setString(1, "%"+dni+"%");
                        ResultSet rs = prep.executeQuery();
                        int id = rs.getInt("patient_id");
                        
                        String sql1= "SELECT REPORT FROM Reports WHERE patient_id =? ";
                        PreparedStatement prep1 = c1.prepareStatement(sql1);
			prep1.setString(1, "%"+id+"%");
			ResultSet rs2 = prep.executeQuery();
                        while (rs2.next()) {
                            java.sql.Date datesql= (java.sql.Date) rs2.getDate("report_date");
                            java.util.Date  dat = new java.util.Date(datesql.getTime());
                            String quality=rs2.getString("quality");
                            String exhaust=rs2.getString("exhaustion");
                            String averageHours=rs2.getString("hours");
                            String movem=rs2.getString("movement");
                            String timeToFall=rs2.getString("time");
                            String res=rs2.getString("rest");
                            String awake=rs2.getString("awake");
                            String timAwake=rs2.getString("times awake");
                            String dreams=rs2.getString("dreams");
                            String worr=rs2.getString("worries");
                            String mood=rs2.getString("mood");
                            String doubts=rs2.getString("doubts");
                            Report rep=new Report(dat,quality,exhaust,averageHours,movem,timeToFall,res,awake,timAwake,dreams,worr,mood,doubts);
                            repList.add(rep);
                          
                        }
                        
                      rs2.close();
                      prep1.close();
                      rs.close();
                      prep.close();  
				
			
		}catch(Exception e) {
			e.printStackTrace();
		}
            return repList;
	}
/*
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
     */
  
    public static EEG viewEEG(String dni, java.util.Date date) {
         EEG eeg = new EEG();
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
                        prep1.setString(2, "%"+date+"%");  //NO SE SI ESTO ESTA BIEN PORQUE DEBERIA SER SetDate PEOR DA ERROR
                        
			ResultSet rs2 = prep1.executeQuery();
                        while (rs2.next()) {
                            java.sql.Date datesql= (java.sql.Date) rs2.getDate("EEG_date");
                            java.util.Date eeg_date = new java.util.Date(datesql.getTime());
                            String EEG=rs2.getString("EEG");
                           // eeg =new EEG(eeg_date, EEG); //No se como ya que no hay constructor en la clase EEG
                      }
                        
                      rs2.close();
                      prep1.close();
                      rs.close();
                      prep.close();  
				
			
		}catch(Exception e) {
			e.printStackTrace();
		}
            return eeg;
    }

    public static ArrayList<EEG> viewEEGHistory(String dni) {
         ArrayList<EEG> eegs = new ArrayList<EEG>();
         ArrayList<Integer> values=new ArrayList<>();
         Connection c1 = null;
            try {
			String sql = "SELECT patient_id FROM Patients WHERE DNI = ?";
			PreparedStatement prep = c1.prepareStatement(sql);
			prep.setString(1, "%"+dni+"%");
                        ResultSet rs = prep.executeQuery();
                        int id = rs.getInt("patient_id");
                        
                        String sql1= "SELECT EEG FROM EEGs WHERE patient_id =?";
                        PreparedStatement prep1 = c1.prepareStatement(sql1);
			prep1.setString(1, "%"+id+"%");  //NO SE SI ESTO ESTA BIEN PORQUE DEBERIA SER SetDate PEOR DA ERROR
                        
			ResultSet rs2 = prep1.executeQuery();
                        while (rs2.next()) {
                            //String valuesString=rs2.getInt("Values");
                            //Debería ser un text en el que estén separados los valores por comas o algo y luego ccrear un arrayList<Integer> con ese STring
                            //values= la creación del array list
                            EEG eeg= new EEG(values);
                            
                            eegs.add(eeg);
                           // eeg =new EEG(eeg_date, EEG); //No se como ya que no hay constructor en la clase EEG
                      }
                        
                       
                        
                      rs2.close();
                      prep1.close();
                      rs.close();
                      prep.close();  
				
			
		}catch(Exception e) {
			e.printStackTrace();
		}
            return eegs;
    }
	
	
	
	
	
}
