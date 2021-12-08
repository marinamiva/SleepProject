/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.util.Date;
import java.util.Objects;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import BITalino.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Patient implements Serializable {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private Integer id;
    private String name;
    private String lastname;
    private String telephone;
    private String address;
    private Date dateOfBirth;
    private String dni;
    private String gender;
    private static File patFile;

    public Patient() {
         super();
    }

    public Patient(Integer id) {
        super();
        this.id = id;

    }

    public Patient(Integer id, String name, String lastname, String telephone, String addres, Date dob, String dni, String gender) {
        super();
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.telephone = telephone;
        this.dateOfBirth = dob;
        this.gender = gender;
        this.dni = dni;
        this.address = addres;
    }

    public Patient(String name, String lastname, String telephone, String address, String dni, String gender) {

        this.name = name;
        this.lastname = lastname;
        this.telephone = telephone;
        this.address = address;
        this.dni = dni;
        this.gender = gender;
    }



    public Patient(String name, String lastname, String telephone, String address, Date dateOfBirth, String dni, String gender) {

        this.name = name;
        this.lastname = lastname;
        this.telephone = telephone;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.dni = dni;
        this.gender = gender;

    }

    public Patient(Integer id, String name, String lastname, String telephone) {
        super();
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.telephone = telephone;
    }

    public Patient(String name, String lastname, String telephone) {
        super();
        this.name = name;
        this.lastname = lastname;
        this.telephone = telephone;
    }

    public static void createFile(List<Integer> EEG, List<Integer> LUX) { //calls for the recorded frame everytime
        Patient pat = new Patient();
        FileWriter flwriter = null;
        try {
            
            flwriter = new FileWriter("./recordedSignal"+pat.getName()+".txt");
            BufferedWriter bfwriter = new BufferedWriter(flwriter);
           //falta añadir al file el nombre del paciente y la fecha y hora pero hay que pasarle el paciente
           // bfwriter.write(name + System.lineSeparator());
           // bfwriter.write(date + System.lineSeparator());

           bfwriter.write(pat.getName());
           LocalDate localdate = LocalDate.now();
           String date = localdate.toString();
           bfwriter.write(date);
           
            int size = EEG.size();
            for (int i = 0; i < size; i++) {
                String str = EEG.get(i).toString();
                String str2 = LUX.get(i).toString();
                //recordedEEG.add(EEG.get(i));
                //recordedLUX.add(LUX.get(i));
                bfwriter.write(str + "\t" + str2 + System.lineSeparator());
                //System.out.println(recordedEEG);
                //System.out.println(recordedLUX);
            }
            bfwriter.flush();
            bfwriter.close();
            System.out.println("File was successfully created..");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (flwriter != null) {
                try {//cierra el flujo principal
                    flwriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void readFile() throws IOException {
        String strng;

        try {
            File file = new File("./recordedSignal.txt");
            BufferedReader obj = new BufferedReader(new FileReader(file));

            while ((strng = obj.readLine()) != null) {

                System.out.println(strng);
            }
            patFile = file;

            System.out.println("File was successfully read..");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }

        }
    }

    public static File getPatFile() {
        return patFile;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void viewEEG() {

    }

    @Override
    public String toString() {
        return "Patient{" + "id=" + id + ", name=" + name + ", lastname=" + lastname + ", telephone=" + telephone + ", address=" + address + ", dateOfBirth=" + dateOfBirth + ", dni=" + dni + ", gender=" + gender +  '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.dni);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Patient other = (Patient) obj;
        if (!Objects.equals(this.dni, other.dni)) {
            return false;
        }
        return true;
    }

}
