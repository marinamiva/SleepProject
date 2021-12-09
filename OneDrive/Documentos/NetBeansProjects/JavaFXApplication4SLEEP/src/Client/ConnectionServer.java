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
            //print.println(patient.getName());
            lastName=patient.getLastname();
            //print.println(patient.getLastname());
            telephone=patient.getTelephone();
            //print.println(patient.getTelephone());
            address=patient.getAddress();
            //print.println(patient.getAddress());
            formato = new SimpleDateFormat("yyyy-MM-dd");
            date = formato.format(patient.getDateOfBirth());
            //print.println(date);
            dni=patient.getDni();
            //print.println(patient.getDni());
            gender= patient.getGender();
            //print.println(patient.getGender());
            //print.println("X");
            total=name+"\n"+lastName+"\n"+telephone+"\n"+address+"\n"+formato+"\n"+date+"\n"+dni+"\n"+gender+"\n"+"finish";
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
            Date dat = rep.getTodaysDate();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            date = formato.format(dat);
            //print.println(rep.getPatdni());
            dni=rep.getPatdni();
            //print.println(date);
            sleepqual=rep.getsleepQuality();
            //print.println(rep.getsleepQuality());
            exhaus=rep.getExhaustion();
            //print.println(rep.getExhaustion());
            average=rep.getAverageHours();
            //print.println(rep.getAverageHours());
            movement=rep.getMovement();
            //print.println(rep.getMovement());
            timeToFall= rep.gettimeToFallAsleep();
            //print.println(rep.gettimeToFallAsleep());
            rest=rep.getRest();
            //print.println(rep.getRest());
            stayAwake=rep.getStayAwake();
            //print.println(rep.getStayAwake());
            timesAwake= rep.getTimesAwake();
            //print.println(rep.getTimesAwake());
            dreams=rep.getDreams();
            //print.println(rep.getDreams());
            worries=rep.getWorries();
            //print.println(rep.getWorries());
            todaysMood= rep.getTodaysMood();
            //print.println(rep.getTodaysMood());
            doubtsForDoctor=rep.getdoubtsForDoctor();
            //print.println(rep.getdoubtsForDoctor());
            //print.println("finish");
            total=date+"\n"+dni+"\n"+sleepqual+"\n"+exhaus+"\n"+average+"\n"+movement+"\n"+timeToFall+"\n"+rest+"\n"+stayAwake+"\n"+timesAwake+"\n"+dreams+"\n"+worries+"\n"+todaysMood+"\n"+doubtsForDoctor+"\n"+"finish";
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
        File file = new File("C:\\Users\\mmsan\\OneDrive - Fundación Universitaria San Pablo CEU\\Documentos\\temporal\\nuevo\\SleepProject\\OneDrive\\Documentos\\NetBeansProjects\\JavaFXApplication4SLEEP/recordedSignal_"+pat.getDni()+".txt"); 
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

    public static void main(String[] args) throws ClassNotFoundException, ParseException, UnknownHostException {
        dbman = new DBManager();
        dbman.connect();
        pmi = dbman.getPatientManager();
        br = new BufferedReader(new InputStreamReader(System.in));
        String ipString = "192.168.100.130";
        InetAddress ip1 = InetAddress.getByName(ipString);
        //String dni = ui.takeDNI(br, "Write dni:");
        //Patient patient = pmi.searchSpecificPatientByDNI(dni);
        Patient pat = new Patient("marina","miguelez","610167672","madrid","1234","female");
        File file = Patient.getPatFile();
        //sendPatient(patient, ip1);
        sendFile(pat, ip1);
        //java.util.Date dat=new java.util.Date("2021-11-11");
        //Report report = pmi.getDailyReport(dat);
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
