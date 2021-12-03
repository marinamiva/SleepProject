
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
     private Object file; //NO ES PARA QUE SE QUEDE ASÍ TIENE QUE SER UN FILE!?

    public Object getFile() {
        return file;
    }
     //he creado esto porque creo que es lo que tendrá pero no sabemos
     public EEG(ArrayList<Integer> eegVals){
         this.eegValues=eegVals;
     }
     
     public EEG(){
         super();
     }
     public EEG(Date eegdate,String dni,Object file){
         super();
         this.dni=dni;
         this.eegDate=eegdate;
         
         
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
     
     
     
     
    

   
}
