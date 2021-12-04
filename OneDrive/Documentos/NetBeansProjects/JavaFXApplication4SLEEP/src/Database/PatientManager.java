/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;
import Client.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;



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
            
        public ArrayList<Report> reportHistory(){
            ArrayList<Report> repList = new ArrayList<Report>();

            try {
                    String sql = "SELECT * FROM Reports";
                    PreparedStatement prep = c.prepareStatement(sql);
                    ResultSet rs = prep.executeQuery();
                    while (rs.next()) {
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
                        Report repnew = new Report (repdate, quality, exhaust, averageHours, movem, timeToFall, res, awake, timAwake,dreams, worr, mood, doubts);
                        repList.add(repnew);
                    }

            } catch (Exception e) {
                    e.printStackTrace();
            }
            return repList;
    }

            
            
            
    @Override
    public  Report getDailyReport(java.util.Date  dateReport){
        Report newreport = new Report();
        String sql = "SELECT * FROM Reports WHERE Report_date LIKE ?";
        try {
            PreparedStatement prep = c.prepareStatement(sql);
            prep.setString(1, "%"+dateReport+"%");
            ResultSet rs = prep.executeQuery();
            while(rs.next()){
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
                newreport = new Report(repdate, quality, exhaust, averageHours, movem, timeToFall, res, awake, timAwake, dreams, worr, mood, doubts);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PatientManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newreport;
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

	public  Patient getPatient(int pat_id) {
                Patient pat = new Patient();
                Connection c1 = null;
                
		try {
			String sql = "SELECT * FROM Patients WHERE patient_id LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, pat_id);
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("patient_id");
				String name = rs.getString("name");
				String lastname = rs.getString("lastname");
                                String telephone = rs.getString("telephone");
                                String address = rs.getString("address");
                                java.util.Date patdob=rs.getDate("dob");
                                String dni = rs.getString("dni");
                                String gender = rs.getString("gender");
                                pat.setId(id);
                                pat.setName(name);
                                pat.setLastname(lastname);
                                pat.setTelephone(telephone);
                                pat.setAddress(address);
                                pat.setDateOfBirth(patdob);
                                pat.setDni(dni);
                                pat.setGender(gender);
                                
			
		}
	}catch(Exception e) {
		e.printStackTrace();
	}
		return pat;
		
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
