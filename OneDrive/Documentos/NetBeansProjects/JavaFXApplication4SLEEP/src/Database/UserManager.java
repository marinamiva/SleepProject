/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;


import Client.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;




public class UserManager implements UserManagerInterface {
	
        private static final String URL = "jdbc:sqlite:lib/db/SleepControlDB2.db";
        private Connection c;
	
        
        public UserManager(Connection con){
            this.c=con;
        }
        
        @Override
	public void connect() {
            
            try {
                c = DriverManager.getConnection(URL);
                c.createStatement().execute("PRAGMA foreign_keys=ON");

            } catch (SQLException ex) {
                Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
            }
	}

            public void disconnect() {
                try {
                    // Close database connection
                    c.close();
                    // System.out.println("Database connection closed.");
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
                
            
        @Override
            public void createUserRegister(User newuser){
                try{
                    String sql = "INSERT INTO Users (patient_dni, password) VALUES (?,?)";
                    PreparedStatement prep = c.prepareStatement(sql);
                    prep.setString(1, newuser.getUsername());
                    prep.setString(2, new String(newuser.getPassword()));
                    prep.executeUpdate();
                    prep.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }

        @Override
	public User getUserByDNI(String dni) {
            User user=new User();
            try {
                String sql ="SELECT * from Users WHERE patient_dni =?";
                PreparedStatement prep = c.prepareStatement(sql);
                prep.setString(1, "%"+dni+"%");
                ResultSet rs = prep.executeQuery();
                while (rs.next()){
                   String dni1=rs.getString("PATIENT_DNI");
                   byte[] pas=rs.getBytes("Password");
                   user=new User(dni1,pas);
                   //user.setUsername(dni1);
                   //user.setPassword(pas);
                }
                	
            } catch (SQLException ex) {
                Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
            } return user;
	}
	
        public byte[] getPassword(Integer id){
            byte[] pas=null;
            try {
                String sql ="SELECT * from Users WHERE patient_id =?";
                PreparedStatement prep = c.prepareStatement(sql);
                prep.setString(1, "%"+id+"%");
                ResultSet rs = prep.executeQuery();
                while (rs.next()){
                   String dni1=rs.getString("PATIENT_DNI");
                   pas=rs.getBytes("Password");
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
            } return pas;
        }
	
	
        @Override
        public User checkPasswordGood(User user){
            User user2 = null;
            String username;
            try{
                String sql = "SELECT * FROM Users WHERE patient_dni = ? AND password = ?";
                PreparedStatement prep = c.prepareStatement(sql);
                prep.setString(1, "%"+user.getUsername()+"%");
                prep.setString(2,  "%"+user.getPassword()+"%");
                ResultSet rs = prep.executeQuery();
                while(rs.next()){
                    username=user.getUsername();
                    byte[] password = rs.getBytes("password");
                   if(password !=user.getPassword()){
                       System.out.println("Password didnt match");
                   } else{
                       System.out.println("logged in!!!");
                   }
                   user2 = new User(username, password);

             }
             }catch(SQLException ex){
                      ex.printStackTrace();
                      }
             return user2;
         }

    
    private static Database.DBManagerInterface dbman;
    private static PatientManagerInterface pmi;
    private static UserManagerInterface umi;
    private static BufferedReader br;
	public static void main(String[] args) throws IOException, ParseException, Exception {
            dbman = new DBManager();
            dbman.connect();
            pmi = dbman.getPatientManager();
            umi=dbman.getUserManager();
            //umi.connect();  
            br = new BufferedReader(new InputStreamReader(System.in));
            String dni="8267372";
            byte[] pas =umi.getPassword(1);
            System.out.println(pas);
            //byte[] pas=ui.takePasswordAndHashIt(br, "insert password");
            //User user=new User(dni,pas);
            //umi.deleteUser(user);
            //umi.createUserRegister(user);
            //User user =umi.getUserByDNI(dni);
            //System.out.println(user.getPassword());
            //System.out.println(user.getUsername());
        }
  

    



}




