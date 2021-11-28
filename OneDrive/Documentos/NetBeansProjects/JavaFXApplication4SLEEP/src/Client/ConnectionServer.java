/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Database.PatientManager;
import java.io.*;
import java.io.IOException;
import java.net.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnectionServer implements Runnable {
      public static void main(String[] args) throws ClassNotFoundException, ParseException {
        InputStream is = null;
        ObjectInputStream ois = null;
        ObjectOutputStream objectOut=null;
        ServerSocket serversocket = null;
        Socket socketReceiver = null;
        Socket socketSender=null;
        PrintWriter print = null;
        BufferedReader buf=null;
        OutputStream outputStream=null;
        java.util.Date dat=new Date("13/02/99");
        Patient patientToSend=new Patient(1,"marina","miguelez","672","mad",dat,"715","female");
              
        try{
            socketSender=new Socket("localhost",9010);
            outputStream=socketSender.getOutputStream();
           
        }catch(IOException io){
            System.out.println("No possible to connect.");
            System.exit(-1);
            Logger.getLogger(ConnectionServer.class.getName()).log(Level.SEVERE,null,io);
        } try {
            
            objectOut=new ObjectOutputStream(outputStream);
            objectOut.writeObject(patientToSend);
            objectOut.flush(); 
        }catch(IOException ex){
            System.out.println("unnable to write the objects on the server.");
            Logger.getLogger(ConnectionServer.class.getName()).log(Level.SEVERE,null,ex);
        } finally{
            releaseResources(objectOut, socketSender);
        }
    }
    
    /*
    private ArrayList<Patient>  patients;
    private ArrayList<Users> users;
    private ArrayList<Report> reports;
    private ArrayList<EEG> eegs;
    InputStream is = null;
    ObjectInputStream ois = null;
    ObjectOutputStream objectOut=null;
    ServerSocket serversocket = null;
    Socket socketReceiver = null;
    Socket socketSender=null;
    PrintWriter print = null;
    BufferedReader buf=null;
    OutputStream outputStream=null;
    
    
    
    /**
     * Envía un paciente al server, le pregunta la fecha del report que quiere ver y le envía el report de ese paciente.
     * @throws ClassNotFoundException
     * @throws ParseException
     */
    /*public void sendClientandReport() throws ClassNotFoundException, ParseException{
    
        try{
            serversocket = new ServerSocket(9000); //podría poner socketReceiver.getPort();
            socketReceiver = serversocket.accept();
            is = socketReceiver.getInputStream();
            
            System.out.println("The connection established from the address" + socketReceiver.getInetAddress()); 
            socketSender=new Socket(socketReceiver.getInetAddress(),9009);
            
        }catch(IOException ex){
            ex.printStackTrace();
        }
        
        try{
            ois = new ObjectInputStream(is);
            Object newpat=null; //NO SE SI NULL O NEW pATIENT()
            Patient patientconnected = (Patient) newpat;
            String dni = patientconnected.getDni();
            print=new PrintWriter(socketSender.getOutputStream(),true);
            //System.out.println("The patient you are going to send is:\n" + patientconnected.toString()); 
            
            // crear conexion -> crear manejador de pacientes -> llamar al método del manejador de pacientes
            Patient pat = PatientManager.searchSpecificPatientByDNI(dni); 
            objectOut=new ObjectOutputStream(socketSender.getOutputStream());

            objectOut.writeObject(pat);
            while((newpat= ois.readObject())!= null){
                print.println("Choose the report's date you want to see: (DD/MM/YY)\n");

                buf=new BufferedReader(new InputStreamReader(socketReceiver.getInputStream()));
                String dateString=buf.readLine();
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date dateUtil=formato.parse(dateString);
                java.sql.Date dateSql= new java.sql.Date(dateUtil.getTime());
                Report rep = PatientManager.viewReport(dni,dateSql);
                objectOut.writeObject(rep);
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        
        finally{
            releaseResources(objectOut,socketReceiver);
        }
    }
    
    /**
     * Envía al servidor un paciente elegido por su DNI y el EEG de la fecha seleccionada.
     * @throws java.lang.ClassNotFoundException
     * @throws java.text.ParseException
     */
   /* public void sendClientandEEG() throws ClassNotFoundException, ParseException{

        try{
            serversocket = new ServerSocket(9000); //podría poner socketReceiver.getPort();
            socketReceiver = serversocket.accept();
            is = socketReceiver.getInputStream();
            
            System.out.println("The connection established from the address" + socketReceiver.getInetAddress()); 
            socketSender=new Socket(socketReceiver.getInetAddress(),9009);
            
        }catch(IOException ex){
            ex.printStackTrace();
        }
        
        try{
            ois = new ObjectInputStream(is);
            Object newpat= null;
            ObjectOutputStream objectOut=null;
            Patient patientconnected = (Patient) newpat;
            String dni = patientconnected.getDni();
            print=new PrintWriter(socketSender.getOutputStream(),true);
            System.out.println("The patient you are going to send is:\n" + patientconnected.toString()); 
            Patient pat = PatientManager.searchSpecificPatientByDNI(dni); //Es la que se va a crear
            objectOut=new ObjectOutputStream(socketSender.getOutputStream());
            objectOut.writeObject(pat);
            while((newpat= ois.readObject())!= null){
                
                print.println("Choose the EEG's date you want to see: (DD/MM/YY)\n");
                buf=new BufferedReader(new InputStreamReader(socketReceiver.getInputStream()));
                String dateString=buf.readLine();
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date dateUtil=formato.parse(dateString);
                java.sql.Date dateSql= new java.sql.Date(dateUtil.getTime());
                EEG eeg = PatientManager.viewEEG(dni,dateSql);
                objectOut.writeObject(eeg);
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        
        finally{
            releaseResources(objectOut,socketReceiver);
        }
}
    /**
     * Envía al servidor un paciente elegido por su DNI y una tabla de todas las fechas en las que hay EEG y sus nombres.
     * @throws java.lang.ClassNotFoundException
     * @throws java.text.ParseException
     */
    /*public void sendClientandHistoryEEG() throws ClassNotFoundException, ParseException{

        try{
            serversocket = new ServerSocket(9000); //podría poner socketReceiver.getPort();
            socketReceiver = serversocket.accept();
            is = socketReceiver.getInputStream();
            
            System.out.println("The connection established from the address" + socketReceiver.getInetAddress()); 
            socketSender=new Socket(socketReceiver.getInetAddress(),9009);
            
        }catch(IOException ex){
            ex.printStackTrace();
        }
        
        try{
            ois = new ObjectInputStream(is);
            Object newpat= null;
            ObjectOutputStream objectOut=null;
            Patient patientconnected = (Patient) newpat;
            String dni = patientconnected.getDni();
            print=new PrintWriter(socketSender.getOutputStream(),true);
            System.out.println("The patient you are going to send is:\n" + patientconnected.toString()); 
            Patient pat = PatientManager.searchSpecificPatientByDNI(dni); 
            objectOut=new ObjectOutputStream(socketSender.getOutputStream());
            objectOut.writeObject(pat);
            while((newpat= ois.readObject())!= null){
                
                
                ArrayList<EEG> eegs = PatientManager.viewEEGHistory(dni); //El método sí existe no sé por qué da error!
                objectOut.writeObject(reports);
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        
        finally{
            releaseResources(objectOut,socketReceiver);
        }
}
    /**
     * Envía al servidor un paciente elegido por su DNI y una tabla de todas las fechas en las que hay Report y sus nombres.
     * @throws java.lang.ClassNotFoundException
     * @throws java.text.ParseException
     */
    /*public void sendClientandHistoryReport() throws ClassNotFoundException, ParseException{

        try{
            serversocket = new ServerSocket(9000); //podría poner socketReceiver.getPort();
            socketReceiver = serversocket.accept();
            is = socketReceiver.getInputStream();
            
            System.out.println("The connection established from the address" + socketReceiver.getInetAddress()); 
            socketSender=new Socket(socketReceiver.getInetAddress(),9009);
            
        }catch(IOException ex){
            ex.printStackTrace();
        }
        
        try{
            ois = new ObjectInputStream(is);
            Object newpat= null;
            ObjectOutputStream objectOut=null;
            Patient patientconnected = (Patient) newpat;
            String dni = patientconnected.getDni();
            print=new PrintWriter(socketSender.getOutputStream(),true);
            System.out.println("The patient you are going to send is:\n" + patientconnected.toString()); 
            Patient pat = PatientManager.searchSpecificPatientByDNI(dni); 
            objectOut=new ObjectOutputStream(socketSender.getOutputStream());
            objectOut.writeObject(pat);
            while((newpat= ois.readObject())!= null){
                ArrayList<Report> reports = PatientManager.viewReportHistory(dni); //NO se si va a ser con arraylist aun o si es una tabla. Si es una tabla la tabla puede ser un Objeto?
                objectOut.writeObject(reports);
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        
        finally{
            releaseResources(objectOut,socketReceiver);
        }
}
    public static void main(String[] args) throws ClassNotFoundException, ParseException {
        
        try {
            print=new PrintWriter(socketSender.getOutputStream(),true);
            
            print.println("What do you want to do?:\n1.See a patient / Report o EEG o history.");

            buf=new BufferedReader(new InputStreamReader(socketReceiver.getInputStream()));
            Integer num=buf.read(); 
        //Hacer esto de q solo se pueda un num del 1 al 2
            switch(num){
                case 1:  receiveClientandReport()
                    break;
                case 2:  receiveClientandEEG()
                    break;
                case 0:
                    break;

            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }*/
   

     
    private static void releaseResources(ObjectOutputStream oos, Socket socket){
        
        try{
            oos.close();
        }catch(IOException ex){
             Logger.getLogger(ConnectionServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try{
            socket.close();
        }catch(IOException ex){
             Logger.getLogger(ConnectionServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
}
