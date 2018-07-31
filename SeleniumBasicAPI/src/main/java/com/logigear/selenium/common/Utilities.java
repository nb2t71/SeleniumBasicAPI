package com.logigear.selenium.common;

import java.util.Random;

public class Utilities {
	
	public static String getProjectPath() {
		return System.getProperty("user.dir");		
	}
	
	public static String generateRandomText(String strOrigin, int intLength) {
		
		Random rand = new Random();
		StringBuilder res = new StringBuilder();
		
		for(int i=0; i<intLength; i++) {
			int randIndex = rand.nextInt(strOrigin.length());
			res.append(strOrigin.charAt(randIndex));
		}
		
		return res.toString();
	}
}
