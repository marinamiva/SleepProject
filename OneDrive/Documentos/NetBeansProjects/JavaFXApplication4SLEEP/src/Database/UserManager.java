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

import java.security.NoSuchAlgorithmException;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Client.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;




public class UserManager implements UserManagerInterface {
	private EntityManager em;
        private static final String URL = "jdbc:sqlite:lib/db/SleepControlDB2.db";
        private Connection c;
	
        
        public UserManager(Connection con){
            this.c=con;
        }
        
        //ESTA FORMA DE HACERLO ES CON JPA Y NOSOTRAS ESTAMOS USANDO JDBCD, HAY QUE BUSCAR FORMA DE HACERLO EN JDBC
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
		Query q = em.createNativeQuery("SELECT patient_dni, password, from Users WHERE patient_dni =?", User.class);
		q.setParameter(1,dni);   
		User user = (User) q.getSingleResult();
		return user;	
	}
	
	

	public void deleteUser(User user) {
		em.getTransaction().begin();
		em.remove(user);
		em.getTransaction().commit();
		em.close();
		
	}
	
	
        @Override
  public User checkPasswordGood(User user){
      User user2 = null;
      try{
            String sql = "SELECT password FROM Users WHERE patient_dni = ? AND password = ?";
             PreparedStatement prep = c.prepareStatement(sql);
             prep.setString(1, "%"+user.getUsername()+"%");
             prep.setString(2,  "%"+user.getPassword()+"%");
             ResultSet rs = prep.executeQuery();
             while(rs.next()){
                    String username = rs.getString("patient_dni");
                    byte[] password = rs.getBytes("password");

                    if(username == user.getUsername()){
                        if(password == user.getPassword()){
                            System.out.println("logged in!!!");
                        }else{
                            System.out.println("Password didnt match");
                        }
                        user2 = new User(username, password);
              }
             
      }
      }catch(SQLException ex){
               ex.printStackTrace();
               }
      return user2;
  }

    @Override
    public void createUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*
    @Override
    public User checkPassword(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    */
  

    



}



/*
@Override
	public void createUser(User user) {
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
	}

@Override
    public void createUser(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

@Override
	public User checkPassword(User userps) {
		User user = null;
		try {
			Query q = em.createNativeQuery("SELECT * FROM Users WHERE patient_dni = ? AND password = ?", User.class);
			q.setParameter(1, userps.getUsername());
			q.setParameter(2, userps.getPassword());
			user = (User) q.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
		return user;
	}
  @Override
    public User checkPassword(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


*/
