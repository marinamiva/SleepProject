
package Client;

import java.io.*;
import java.net.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;


public class Signals implements Serializable {
     ArrayList<Integer> eegValues = new ArrayList<Integer>();
     ArrayList<Integer> eegLUX = new ArrayList<Integer>();
     private String dni;
     private LocalDate eegDate;

     
    public Signals(){
        super();
    }
    
    public Signals(LocalDate eegdate,String dni,ArrayList<Integer> eegVals, ArrayList<Integer> eegLUX){
        super();
        this.dni=dni;
        this.eegDate=eegdate;
        this.eegValues=eegVals;
        this.eegLUX=eegLUX;
    }
    
    public Signals(LocalDate eegdate,String dni,ArrayList<Integer> eegVals){
        super();
        this.dni=dni;
        this.eegDate=eegdate;
        this.eegValues=eegVals;
    }
    public Signals(LocalDate eegdate,ArrayList<Integer> eegVals,ArrayList<Integer> eegLUX){
        super();
        this.eegDate=eegdate;
        this.eegValues=eegVals;
        this.eegLUX=eegLUX;
    }
    
    
    public ArrayList<Integer> getEegLUX() {
        return eegLUX;
    }

    public void setEegLUX(ArrayList<Integer> eegLUX) {
        this.eegLUX = eegLUX;
    }

    public void setEegValues(ArrayList<Integer> eegValues) {
        this.eegValues = eegValues;
    }

    public void setEegDate(LocalDate eegDate) {
        this.eegDate = eegDate;
    }

    public String getDni() {
        return dni;
    }

    public LocalDate getEegDate() {
        return eegDate;
    }


    public ArrayList<Integer> getEegValues() {
        return eegValues;
    }

    @Override
    public String toString() {
        return "Signals{" + "dni=" + dni + ", eegDate=" + eegDate + ", eegValues=" + eegValues + ", eegLUX=" + eegLUX + '}';
    }





   

  
    
     
     
     
     
    

   
}
