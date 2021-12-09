/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Database.DBManager;
import Database.PatientManagerInterface;
import java.io.*;
import java.io.IOException;
import java.net.*;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionServer {

    /**
     * Sendind an object patient through a socket to a server.
     * @param patient
     * @param ip
     */
    public static void sendPatient(Patient patient, InetAddress ip) {
        Socket socketSender = null;
        PrintWriter print = null;
        SimpleDateFormat formato;
        String total, name, lastName, telephone, address, date, dni,gender ;

        try {
            socketSender = new Socket(ip, 9010);
            print = new PrintWriter(socketSender.getOutputStream(), true);
            name=patient.getName();
            lastName=patient.getLastname();
            telephone=patient.getTelephone();
            address=patient.getAddress();
            formato = new SimpleDateFormat("yyyy-MM-dd");
            date = formato.format(patient.getDateOfBirth());
            dni=patient.getDni();
            gender= patient.getGender();
            total="\n PATIENT'S INFORMATION \n Name: "+name+"\n Lastname: "+lastName+"\n Telephone: "+telephone+"\n Address: "+address+"\n Date of birth: "+date+"\n DNI: "+dni+"\n Gender: "+gender+"\n"+"\n finish";
            print.println(total);
            releaseResources(print, socketSender);
        } catch (IOException io) {
            System.out.println("No possible to connect.");
            Logger.getLogger(ConnectionServer.class.getName()).log(Level.SEVERE, null, io);
        }
    }

    /**
     * Sending an object Report through a socket to a server.
     * @param rep
     * @param ip
     */
    public static void sendReport(Report rep, InetAddress ip) {

        Socket socketSender = null;
        PrintWriter print = null;

        try {
            socketSender = new Socket(ip, 9010);
            print = new PrintWriter(socketSender.getOutputStream(), true);
            String dni,date,sleepqual,exhaus,average,movement,timeToFall,rest,stayAwake,timesAwake,dreams,worries,todaysMood,doubtsForDoctor;
            String total;
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            date = formato.format(rep.getTodaysDate());
            dni=rep.getPatdni();
            sleepqual=rep.getsleepQuality();
            exhaus=rep.getExhaustion();
            average=rep.getAverageHours();
            movement=rep.getMovement();
            timeToFall= rep.gettimeToFallAsleep();
            rest=rep.getRest();
            stayAwake=rep.getStayAwake();
            timesAwake= rep.getTimesAwake();
            dreams=rep.getDreams();
            worries=rep.getWorries();
            todaysMood= rep.getTodaysMood();
            doubtsForDoctor=rep.getdoubtsForDoctor();
            total="\n Report's date: " +date+"\n DNI: "+dni+"\n Sleep quality: "+sleepqual+"\n Exhaustion: "+exhaus+"\n Average hours: "+average+"\n Movement: "+movement+"\n Time to fall asleep: "+timeToFall+"\n Have you rest? "+rest+"\n Do you stay awake all day? "+stayAwake+"\n How many times did you wake up in the night? "+timesAwake+"\n Have you dreamt? "+dreams+"\n Worries: "+worries+"\n Todays mood: "+todaysMood+"\n Doubts: "+doubtsForDoctor+"\n"+"finish";
            print.println(total);
            releaseResources(print, socketSender);
        } catch (IOException io) {
            System.out.println("No possible to connect.");
            Logger.getLogger(ConnectionServer.class.getName()).log(Level.SEVERE, null, io);
        }
    }

    /**
     * Sending a File with the information about EEG of a patient, its name and the time of the EEG through a socket to a server.
     * @param file
     * @param ip
     */
    public static void sendFile(Patient pat, InetAddress ip) {
        File file = new File("./recordedSignal_"+pat.getDni()+".txt"); 

        try {
            Socket socket = new Socket(ip, 9010);
            OutputStream outputStream = socket.getOutputStream();
            BufferedReader br = new BufferedReader(new FileReader(file));
            int character;
            while ((character = br.read()) != -1) {
                outputStream.write(character);
                outputStream.flush();

            }
         outputStream.flush();
        releaseResources(outputStream, br, socket);
        } catch (IOException ex) {
            System.out.println("No possible to connect.");
            Logger.getLogger(ConnectionServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Connection c;
    private static Database.DBManagerInterface dbman;
    private static PatientManagerInterface pmi;
    private static BufferedReader br;

    public static void main(String[] args) throws ClassNotFoundException, ParseException, UnknownHostException, IOException {
        dbman = new DBManager();
        dbman.connect();
        pmi = dbman.getPatientManager();
        br = new BufferedReader(new InputStreamReader(System.in));
        //System.out.println("Write your IP: ");
        //String ipString=br.readLine();
        String ipString = "192.168.1.97";

        InetAddress ip1 = InetAddress.getByName(ipString);
        
        String dni = ui.takeDNI(br, "Write the dni of the data you want to send:");
        Patient pat = pmi.searchSpecificPatientByDNI(dni);
        //sendPatient(pat, ip1);
        //sendFile(pat, ip1);
        LocalDate data= ui.takeDate(br,"Type the Date of the report followed by yyyy-MM-dd");
        java.util.Date dat = java.sql.Date.valueOf(data);
        Report report = pmi.getDailyReport(dat);
        System.out.println(report.toString());
        //sendReport(report,ip1);

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
