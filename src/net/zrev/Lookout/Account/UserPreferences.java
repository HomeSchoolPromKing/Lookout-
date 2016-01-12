package net.zrev.Lookout.Account;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class UserPreferences {
	
	
	public static void loadPreferences(){
		try {
			FileReader r;
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
