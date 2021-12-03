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


public class ConnectionServer {
     public static void sendPatient(Patient patient){
        InputStream is = null;
        ObjectInputStream ois = null;
        ObjectOutputStream objectOut = null;
        ServerSocket serversocket = null;
        Socket socketReceiver = null;
        Socket socketSender = null;
        PrintWriter print = null;
        BufferedReader buf = null;
        OutputStream outputStream = null;
        
        try {
            socketSender = new Socket("localhost", 9010);
            print=new PrintWriter(socketSender.getOutputStream(),true);
            print.println(patient.getName());
            print.println(patient.getLastname());
            print.println(patient.getTelephone());
            print.println(patient.getAddress());
            print.println(patient.getDni());
            print.println(patient.getGender());
            print.println("Finish");
            releaseResources(print, socketSender);
        } catch (IOException io) {
            System.out.println("No possible to connect.");
            Logger.getLogger(ConnectionServer.class.getName()).log(Level.SEVERE, null, io);
        } 
    }
    public static void sendReport(Report rep){
        InputStream is = null;
        ObjectInputStream ois = null;
        ObjectOutputStream objectOut = null;
        ServerSocket serversocket = null;
        Socket socketReceiver = null;
        Socket socketSender = null;
        PrintWriter print = null;
        BufferedReader buf = null;
        OutputStream outputStream = null;
        
        try {
            socketSender = new Socket("localhost", 9010);
            print=new PrintWriter(socketSender.getOutputStream(),true);
            //String date,sleepqual,exhaus,average,movement,timeToFall,rest,stayAwake,timesAwake,dreams,worries,todaysMood,doubtsForDoctor;
            Date dat=rep.getTodaysDate();
            SimpleDateFormat formato=new SimpleDateFormat("dd-MM-yyyy");
            String date=formato.format(dat);
            print.println(date);
            print.println(rep.getsleepQuality());
            print.println(rep.getExhaustion());
            print.println(rep.getAverageHours());
            print.println(rep.getMovement());
            print.println(rep.gettimeToFallAsleep());
            print.println(rep.getRest());
            print.println(rep.getStayAwake());
            print.println(rep.getTimesAwake());
            print.println(rep.getDreams());
            print.println(rep.getWorries());
            print.println(rep.getTodaysMood());
            print.println(rep.getdoubtsForDoctor());
            print.println("Finish");
            releaseResources(print, socketSender);
        } catch (IOException io) {
            System.out.println("No possible to connect.");
            Logger.getLogger(ConnectionServer.class.getName()).log(Level.SEVERE, null, io);
        } 
    }
     
    //NO ESTA HECHO TENGO QUE BUSCAR EL PASAR DE OBJECT A FILE!!
    public static void sendEEG(EEG eeg){
        InputStream is = null;
        ObjectInputStream ois = null;
        ObjectOutputStream objectOut = null;
        ServerSocket serversocket = null;
        Socket socketReceiver = null;
        Socket socketSender = null;
        PrintWriter print = null;
        BufferedReader buf = null;
        OutputStream outputStream = null;
        
        try {
            socketSender = new Socket("localhost", 9010);
            print=new PrintWriter(socketSender.getOutputStream(),true);
            objectOut=new ObjectOutputStream(socketSender.getOutputStream());
            //FileInputStream fis=(new FileInputStream(eeg.getFile());
            print.println("Finish");
            releaseResources(print, socketSender);
        } catch (IOException io) {
            System.out.println("No possible to connect.");
            Logger.getLogger(ConnectionServer.class.getName()).log(Level.SEVERE, null, io);
        } 
    }

    public static void main(String[] args) throws ClassNotFoundException, ParseException {
        //el paciente serÃ­a obtenerlo de la base de datos pero ahora para probar lo creo:
        Patient patient = new Patient("marina", "miguelez", "672", "mad","715", "female");
        sendPatient(patient);
        Date dat=new Date("20-02-2021");
        Report report = new Report(dat,"jg","jh","jg","jh","jg","jh","jg","jh","jg","jh","jg","jh");
        sendReport(report);
        
    }

    /**
     * EnvÃ­a al servidor un paciente elegido por su DNI y el EEG de la fecha seleccionada.
     * @throws java.lang.ClassNotFoundException
     * @throws java.text.ParseException
     */
 /* public void sendEEG() throws ClassNotFoundException, ParseException{

        try{
            serversocket = new ServerSocket(9000); //podrÃ­a poner socketReceiver.getPort();
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
     * EnvÃ­a al servidor un paciente elegido por su DNI y una tabla de todas las fechas en las que hay EEG y sus nombres.
     * @throws java.lang.ClassNotFoundException
     * @throws java.text.ParseException
     */
 /*public void sendClientandHistoryEEG() throws ClassNotFoundException, ParseException{

        try{
            serversocket = new ServerSocket(9000); //podrÃ­a poner socketReceiver.getPort();
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
                
                
                ArrayList<EEG> eegs = PatientManager.viewEEGHistory(dni); //El mÃ©todo sÃ­ existe no sÃ© por quÃ© da error!
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
     * EnvÃ­a al servidor un paciente elegido por su DNI y una tabla de todas las fechas en las que hay Report y sus nombres.
     * @throws java.lang.ClassNotFoundException
     * @throws java.text.ParseException
     */
 /*public void sendClientandHistoryReport() throws ClassNotFoundException, ParseException{

        try{
            serversocket = new ServerSocket(9000); //podrÃ­a poner socketReceiver.getPort();
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
    private static void releaseResources(PrintWriter p, Socket socket) {

            p.close();
        

        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ConnectionServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
}
