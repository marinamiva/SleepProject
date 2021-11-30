/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Client.Patient;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author gabri
 */
public class Menu {

    private static DBManagerInterface dbm;
    private static Database.DBManagerInterface dbman;
    private static PatientManagerInterface pmi;
    private static UserManagerInterface umi;
    private static BufferedReader br;

    public static void main(String[] args) throws IOException, ParseException {

        dbman = new DBManager();
        dbman.connect();
        //dbm.createTables();

        br = new BufferedReader(new InputStreamReader(System.in));

        Scanner sc = new Scanner(System.in);

        System.out.println("Hello, type the option you want: 1. Add patient, 2: Todavia no");
        int number = sc.nextInt();

        switch (number) {

            case 1:
                addPatient();
                break;
        }

    }

    public static void addPatient() throws IOException, ParseException {
        System.out.println("Type the name of the patient you'll add");
        String name = br.readLine();
        System.out.println("Type the lastname of the patient:");
        String lastname = br.readLine();
        System.out.println("Type the telephone of the patient");
        String telephone = br.readLine();
        System.out.println("Type the address of the patient");
        String address = br.readLine();
        System.out.println("Type the Date of Birth of the patient followed by /");
        String date = br.readLine();
        java.util.Date dateBirth = new SimpleDateFormat().parse(date);
        java.sql.Date dob = (java.sql.Date) dateBirth;
        System.out.println("Type the DNI of the patient");
        String dni = br.readLine();
        System.out.println("Type the gender of the patient: ");
        String gender = br.readLine();

        Patient newpat = new Patient(name, lastname, telephone, address, dob, dni, gender);
        pmi.addpatientbyRegister(newpat);
    }

}
