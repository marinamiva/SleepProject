/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author marin
 */
public class ui {
    
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static String takeGender(BufferedReader reader, String text) {
		String gender = " ";
		String answer;
		try {
			do {
				System.out.println(text + "(m/f)");
				answer = reader.readLine();
				switch (answer) {
				case "M":
				case "m":
					gender = "Male";
					break;
				case "F":
				case "f":
					gender = "Female";
					break;
				default:
					System.out.println("The data introduced is NOT correct.");
					System.out.println("Please introduce m in case of Male or f in case of Female");
					System.out.println("Try again.");
					gender = " ";
					break;
				}
			} while (gender.equals(" "));
		} catch (IOException ex) {
			System.out.println("Error reading");
		}
		return gender;
	}
    public static LocalDate takeDate(BufferedReader reader, String text) {
		boolean check = false;
		String data = "";
		LocalDate day = LocalDate.now();
		System.out.println(text);
		while (!check || data.equals("")) {
			try {
				data = reader.readLine();
				day = LocalDate.parse(data, formatter);
				check = true;
			} catch (IOException ex) {
				System.out.println("Error reading");
			} catch (DateTimeParseException nfex) {
				System.out.println("You have not introduced a valid Date. Try again.");
				System.out.println(text);
			}
		}
		return day;
	}
}
