package com.logigear.selenium.utilities.gmail;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePartHeader;
import com.logigear.selenium.constant.Constant;

public class gmailApi {
	public URL activeAccount(String email) {
		URL url = null;
		int timeout = 360;
		System.out.printf("Activating account at %s second\n", timeout);

		while (true) {
			try {
				if (timeout <= 0) {
					throw new ExceptionInInitializerError("Can't active new account!");
				}

				// Set up the HTTP transport and JSON factory
				HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
				JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

				// Load client secrets
				GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory,
						new InputStreamReader(new FileInputStream(
								new File(Constant.PATH_FILE_CLIENT_SECRET + "\\" + "client_secret.json"))));
//				new File("src/main/java/com/logigear/selenium/utilities/gmail/client_secret.json"))));

				// Set up authorization code flow
				GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, jsonFactory,
						clientSecrets, Collections.singletonList(GmailScopes.MAIL_GOOGLE_COM))
								.setDataStoreFactory(new FileDataStoreFactory(new java.io.File("credentials")))
								.setAccessType("offline").build();

				// Authorize
				Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver())
						.authorize("user");

				Gmail service = new Gmail.Builder(httpTransport, jsonFactory, credential).setApplicationName("ThaoNBT")
						.build();

				// Get list message's info
				ListMessagesResponse listResponse = service.users().messages().list("me").setQ("is:unread label:inbox")
						.setMaxResults(new Long(20)).execute();
				List<Message> messages = listResponse.getMessages();

				// Get detail of messages if message list is not empty element
				if (!messages.isEmpty()) {
					for (Message message : messages) {

						// get Message
						Message content = service.users().messages().get("me", message.getId()).setFormat("metadata")
								.execute();

						for (MessagePartHeader header : content.getPayload().getHeaders()) {

							// Finding subject contain account's username
							if (header.getName().equals("Subject"))
								if (header.getValue().contains(email)) {

									// Finding the URL into message's content
									String[] parts = content.getSnippet().split("\\s+");

									for (String part : parts) {
										try {
											url = new URL(part);
											WebDriver newWindow = new ChromeDriver();

											newWindow.navigate().to(url);
											newWindow.close();
										} catch (Exception e) {
										}
									}
									return url;
								}
						}
					}
				}

				timeout -= 5;
				TimeUnit.SECONDS.sleep(5);
			} catch (Exception e) {
				Assert.fail(e.getMessage());
			}
		}
	}
}
