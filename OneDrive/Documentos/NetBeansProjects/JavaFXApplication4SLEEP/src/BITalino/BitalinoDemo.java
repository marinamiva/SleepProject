package BITalino;


import Client.Patient;
import Client.Signals;
import Database.DBManager;
import Database.PatientManager;
import Database.PatientManagerInterface;
import Database.UserManagerInterface;
import java.io.BufferedReader;
import java.net.InetAddress;
import java.sql.Connection;
import static java.time.LocalDate.now;
import java.util.Vector;
import java.util.ArrayList;
import java.util.List;

import javax.bluetooth.RemoteDevice;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BitalinoDemo {

    public static Frame[] frame;
    public static List<Integer> values = new ArrayList();
    public static List<Integer> values2 = new ArrayList();
    
    private Connection c;
    private static Database.DBManagerInterface dbman;
    private static PatientManagerInterface pmi;
    private  PatientManager pm;
    
    public static void main(String[] args) {
        dbman = new DBManager();
        dbman.connect();
        pmi = dbman.getPatientManager();
        
        
        BITalino bitalino = null;
        try {
            bitalino = new BITalino();
            // Code to find Devices
            //Only works on some OS
            Vector<RemoteDevice> devices = bitalino.findDevices();
            System.out.println(devices);

            String macAddress = "20:17:11:20:52:36";
            
            int SamplingRate = 100; //partimos de 100 
            bitalino.open(macAddress, SamplingRate);

            // Start acquisition on analog channels A2 and A6
            // We use channels A4 and A6, eeg and light
            int[] channelsToAcquire = {3,5}; 
            bitalino.start(channelsToAcquire);

            //Read in total 10000000 times
            for (int j = 0; j < 100; j++) {

                //Each time read a block of 10 samples 
                int block_size=10;
                frame = bitalino.read(block_size);

                System.out.println("size block: " + frame.length);

                //Print the samples
                for (int i = 0; i < frame.length; i++) {
                    System.out.println((j * block_size + i) + " seq: " + frame[i].seq + " "
                            + frame[i].analog[0] + " " 
                            + frame[i].analog[1] + " "
                    //  + frame[i].analog[2] + " "
                    //  + frame[i].analog[3] + " "
                    //  + frame[i].analog[4] + " "
                    //  + frame[i].analog[5]
                    );
                values.add(frame[i].analog[0]);
                values2.add(frame[i].analog[1]);
                }               
            }
            
            Patient.createFile(values, values2);
            ArrayList<Integer> val1=new ArrayList(values);
            ArrayList<Integer> val2=new ArrayList(values2);
            Signals signal=new Signals(now(),val1,val2);
            pmi.addEEG(signal);

            // values.add(frame);
             //Patient.createFile(frame);
            //stop acquisition
            bitalino.stop();
        } catch (BITalinoException ex) {
            Logger.getLogger(BitalinoDemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Throwable ex) {
            Logger.getLogger(BitalinoDemo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                //close bluetooth connection
                if (bitalino != null) {
                    bitalino.close();
                }
            } catch (BITalinoException ex) {
                Logger.getLogger(BitalinoDemo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
