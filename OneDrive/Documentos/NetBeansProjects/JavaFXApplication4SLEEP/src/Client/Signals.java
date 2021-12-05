
package Client;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;


public class Signals implements Serializable {
     ArrayList<Integer> eegValues = new ArrayList<Integer>();
     ArrayList<Integer> eegLUX = new ArrayList<Integer>();
     private String dni;
     private Date eegDate;

     
    public Signals(){
        super();
    }
    
    public Signals(Date eegdate,String dni,ArrayList<Integer> eegVals, ArrayList<Integer> eegLUX){
        super();
        this.dni=dni;
        this.eegDate=eegdate;
        this.eegValues=eegVals;
        this.eegLUX=eegLUX;
    }
    
    public Signals(Date eegdate,String dni,ArrayList<Integer> eegVals){
        super();
        this.dni=dni;
        this.eegDate=eegdate;
        this.eegValues=eegVals;
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

    public void setEegDate(Date eegDate) {
        this.eegDate = eegDate;
    }

    public String getDni() {
        return dni;
    }

    public Date getEegDate() {
        return eegDate;
    }
     

    public ArrayList<Integer> getEegValues() {
        return eegValues;
    }

    @Override
    public String toString() {
        return "Signals{" + "eegValues=" + eegValues + ", eegLUX=" + eegLUX + ", dni=" + dni + ", eegDate=" + eegDate + '}';
    }

  
    
     
     
     
     
    

   
}
