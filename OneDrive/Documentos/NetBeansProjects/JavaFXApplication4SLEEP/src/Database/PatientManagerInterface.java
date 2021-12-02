/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Client.*;
import java.util.ArrayList;
import java.util.Date;



public interface PatientManagerInterface {
    public void addpatientbyRegister(Patient pat);
   public Patient searchSpecificPatientByDNI(String dni);
   public  ArrayList<Patient> showPatients();
    

}
