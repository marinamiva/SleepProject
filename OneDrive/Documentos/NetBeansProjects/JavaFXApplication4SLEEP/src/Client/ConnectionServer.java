/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;


import java.io.*;
import java.io.IOException;
import java.net.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnectionServer {
    public static void sendPatient(Patient patient, InetAddress ip){
        
        Socket socketSender = null;
        PrintWriter print = null;
        
        try {
            socketSender = new Socket(ip, 9010);
            print=new PrintWriter(socketSender.getOutputStream(),true);
            print.println(patient.getName());
            print.println(patient.getLastname());
            print.println(patient.getTelephone());
            print.println(patient.getAddress());
            print.println(patient.getDateOfBirth());
            print.println(patient.getDni());
            print.println(patient.getGender());
            print.println("Finish");
            releaseResources(print, socketSender);
        } catch (IOException io) {
            System.out.println("No possible to connect.");
            Logger.getLogger(ConnectionServer.class.getName()).log(Level.SEVERE, null, io);
        } 
    }
    public static void sendReport(Report rep,InetAddress ip){
        
        Socket socketSender = null;
        PrintWriter print = null;
        
        try {
            socketSender = new Socket(ip, 9010);
            print=new PrintWriter(socketSender.getOutputStream(),true);
            //String date,sleepqual,exhaus,average,movement,timeToFall,rest,stayAwake,timesAwake,dreams,worries,todaysMood,doubtsForDoctor;
            Date dat=rep.getTodaysDate();
            SimpleDateFormat formato=new SimpleDateFormat("yyyy-MM-dd");
            String date=formato.format(dat);
            print.println(rep.getPatdni());
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
     
   
    public static void sendFile(File file,InetAddress ip){
        try {
            Socket socket = new Socket(ip, 9010);
            OutputStream outputStream = socket.getOutputStream();
            BufferedReader br = new BufferedReader(new FileReader(file));
            int character;
            while ((character = br.read()) != -1) {
                System.out.println(character);
                outputStream.write(character);
                outputStream.flush();
                
            }
            releaseResources(outputStream, br, socket);
            System.exit(0);
        } catch (IOException ex) {
            System.out.println("No possible to connect.");
            Logger.getLogger(ConnectionServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        
    
    

    public static void main(String[] args) throws ClassNotFoundException, ParseException, UnknownHostException {
        //el paciente serÃƒÂ­a obtenerlo de la base de datos pero ahora para probar lo creo:
        String ipString="10.61.84.50";
        InetAddress ip1=InetAddress.getByName(ipString);
        Patient patient = new Patient("marina", "miguelez", "672", "mad","715", "female");
        sendPatient(patient,ip1);
        Date dat=new Date("20-02-2021");
        Report report = new Report(dat,"jg","jh","jg","jh","jg","jh","jg","jh","jg","jh","jg","jh");
        sendReport(report,ip1);
        
    }

    
    private static void releaseResources(PrintWriter p, Socket socket) {
        try {    
            p.close();
            socket.close();
        } catch (IOException ex) {
                Logger.getLogger(ConnectionServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    private static void releaseResources(OutputStream outputStream,
            BufferedReader br, Socket socket) {
        try {
            try {
                br.close();

            } catch (IOException ex) {
                Logger.getLogger(ConnectionServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                outputStream.close();

            } catch (IOException ex) {
                Logger.getLogger(ConnectionServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            socket.close();

        } catch (IOException ex) {
            Logger.getLogger(ConnectionServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }





    
}
