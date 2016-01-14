package net.zrev.Lookout.Account;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class UserPreferences {
	
	
	public static void loadPreferences(){
		String userPrefs = "";
		try {
			Scanner s = new Scanner(new File("preferences.txt"));
			while(s.hasNextLine()){
				userPrefs += s.nextLine();
			}
		}
		catch(Exception e) {
			initPreferences();	
		}
	}
	
	private static void initPreferences(){
		
	}
	
	
	public static int startingResX = 1920, startingResY = 1080;
	public static int difficulty = 3;
	
}
