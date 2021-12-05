
package Client;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EEG implements Serializable {
     InputStream inputStream=null;
     PrintWriter printWriter=null;
     BufferedReader bufferedReader=null;
     Socket socket=null;
     ArrayList<Integer> eegValues = new ArrayList<Integer>();
     private String dni;
     private Date eegDate;

     
    public EEG(){
        super();
    }

    public EEG(Date eegdate,String dni,ArrayList<Integer> eegVals){
        super();
        this.dni=dni;
        this.eegDate=eegdate;  
    }

    public void setEegValues(ArrayList<Integer> eegValues) {
        this.eegValues = eegValues;
    }

    public void setEegDate(Date eegDate) {
        this.eegDate = eegDate;
    }

    public String getDni() {
        return dni;
    }

    public Date getEegDate() {
        return eegDate;
    }
     
      public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public PrintWriter getPrintWriter() {
        return printWriter;
    }

    public void setPrintWriter(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ArrayList<Integer> getEegValues() {
        return eegValues;
    }
    
    @Override
    public String toString() {
        return "EEG{" + "eegValues=" + eegValues + ", dni=" + dni + ", eegDate=" + eegDate + '}';
    }
     
     
     
     
    

   
}
