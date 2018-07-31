package com.logigear.selenium.utilities.gmail;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.logigear.selenium.utilities.gmail.resources.Resources;

public class GoogleConnector {
	private static GoogleConnector instance = null;
	public Gmail service = null;
	
	/** Application name for identification purposes. */
    private static final String APPLICATION_NAME = "gmailutils";

    /** Directory used to store the user credentials. */
    private static final File CREDENTIALS_DIRECTORY = new File(System.getProperty("user.home"), ".store/gmailutils");
    
    /** Secrets of the FX calendar application. */
    //private GoogleClientSecrets clientSecrets;
    
    /**  Factory to create the data store object. */
    private DataStoreFactory dataStoreFactory = null;
    
    /** JSON implementation. */
    private final JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
    
    private static final List<String> SCOPES = Lists.newArrayList(GmailScopes.MAIL_GOOGLE_COM);
    
    // see direction here: https://developers.google.com/gmail/api/quickstart/java
//    String secretFile = "gmail/client_secret_seltrain.json";
    String secretFile = "client_secrets.json";
    /**
     * On demand instance creator method used to get the single instance of this
     * google authenticator class.
     *
     * @return The single instance of this class, if the instance does not exist,
     *         this is immediately created.
     */
    public static GoogleConnector getInstance() {
        if (instance == null) {
            try {
                instance = new GoogleConnector();
            } catch (Exception e) {
                throw new RuntimeException("The GoogleConnector could not be instanced!", e);
            }
        }
        return instance;
    }
    
    public GoogleConnector()
    {
    	try {
    		if(CREDENTIALS_DIRECTORY.exists() == false)
    			CREDENTIALS_DIRECTORY.mkdirs();
            File storeCredential = new File(CREDENTIALS_DIRECTORY, "StoredCredential");
            if(storeCredential.exists() == false) {
            	File src = new File(Resources.class.getResource("StoredCredential").toURI());
            	Files.copy(src, storeCredential);
            }
    		// Configure HTTP transport
            dataStoreFactory = new FileDataStoreFactory(CREDENTIALS_DIRECTORY);
            
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public Gmail getService() {
    	if(service == null) {
    		try {
				startAuthentication();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	return service;
    }
    
    private Credential getCredentials(final NetHttpTransport httpTransport) throws Exception {
		// load client secrets
    	InputStream in = Resources.class.getResourceAsStream(secretFile);
        GoogleClientSecrets clientSecrets = 
        		GoogleClientSecrets.load(jsonFactory, new InputStreamReader(in));

		if (clientSecrets.getDetails().getClientId().startsWith("Enter") || clientSecrets.getDetails().getClientSecret().startsWith("Enter ")) {
			System.out.println("Enter Client ID and Secret from https://code.google.com/apis/console/ into src/main/resources/client_secrets.json");
			clearCredentials();
			return null;
		}
		// set up authorization code flow
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
        		httpTransport, jsonFactory, clientSecrets, SCOPES)
				.setDataStoreFactory(dataStoreFactory).setAccessType("offline").build();
		// authorize
		return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }
    /**
     * Build and return an authorized Gmail client service.
     *
     * @return an authorized Gmail client service
     * @throws IOException
     */
    public void startAuthentication() throws Exception {
    	final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        
        Credential credential = getCredentials(httpTransport);
        
        service =  new Gmail.Builder(httpTransport, jsonFactory, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

	protected void clearCredentials() throws IOException {
		if (CREDENTIALS_DIRECTORY.exists()) {
			deleteDirectory(CREDENTIALS_DIRECTORY);
		}
	}

	protected boolean deleteDirectory(File path) {
		if (path.exists()) {
			File[] files = path.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					deleteDirectory(files[i]);
				} else {
					files[i].delete();
				}
			}
		}
		return path.delete();
	}

 
}


