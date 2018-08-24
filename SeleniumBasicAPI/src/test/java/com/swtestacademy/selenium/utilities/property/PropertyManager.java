package com.swtestacademy.selenium.utilities.property;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.logigear.selenium.utilities.constant.Constant;

//**********************************************************************************************************
//Author: Onur Baskirt
//Description: PropertyManager class reads global configurations and use them during test execution.
//https://www.swtestacademy.com/read-configurations-property-file-selenium/
//**********************************************************************************************************
public class PropertyManager {

	private static PropertyManager instance;
	private static final Object lock = new Object();
	private static String propertyFilePath = Constant.PATH_SRC + "\\test\\resources\\data\\configuration.properties";
	private static String url;
	private static String wrongUsername;
	private static String wrongPassword;
	private static String railwayURL;
	private static String railwayUsername;
	private static String railwayPassword;
	private static String gmailUsername;
	private static String gmailDomain;
	private static String gmailPassword;

	// Create a Singleton instance. We need only one instance of Property Manager.
	public static PropertyManager getInstance() {
		if (instance == null) {
			synchronized (lock) {
				instance = new PropertyManager();
				instance.loadData();
			}
		}
		return instance;
	}

	// Get all configuration data and assign to related fields.
	private void loadData() {
		// Declare a properties object
		Properties prop = new Properties();

		// Read configuration.properties file
		try {
			prop.load(new FileInputStream(propertyFilePath));
			// prop.load(this.getClass().getClassLoader().getResourceAsStream("configuration.properties"));
		} catch (IOException e) {
			System.out.println("Configuration properties file cannot be found");
		}

		// Get properties from configuration.properties
		url = prop.getProperty("url");
		wrongUsername = prop.getProperty("wrongUsername");
		wrongPassword = prop.getProperty("wrongPassword");
		railwayURL = prop.getProperty("railwayURL");
		railwayUsername = prop.getProperty("railwayUsername");
		railwayPassword = prop.getProperty("railwayPassword");
		gmailUsername = prop.getProperty("gmailUsername");
		gmailDomain = prop.getProperty("gmailDomain");
		gmailPassword = prop.getProperty("gmailPassword");
	}

	public String getURL() {
		return url;
	}

	public String getWrongUsername() {
		return wrongUsername;
	}

	public String getWrongPassword() {
		return wrongPassword;
	}

	public String getRailwayURL() {
		return railwayURL;
	}

	public String getRailwayUsername() {
		return railwayUsername;
	}

	public String getRailwayPassword() {
		return railwayPassword;
	}

	public String getGmailUsername() {
		return gmailUsername;
	}

	public String getGmailDomain() {
		return gmailDomain;
	}

	public String getGmailPassword() {
		return gmailPassword;
	}

}
