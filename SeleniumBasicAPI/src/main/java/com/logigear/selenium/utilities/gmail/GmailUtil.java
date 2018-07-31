package com.logigear.selenium.utilities.gmail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;

public class GmailUtil {
	public static void main(String[] args) {
		
		//String q = "from:Google after:2018/05/01 before:2018/06/10";
//		String q = "after:2018/06/12 subject:'Please confirm your account diem.test80125@logigear.com'";
		String q = "after:2018/07/30 subject:'Please confirm your account acb@acb@c.om'";
		try {
			List<Message> messages = GmailUtil.listSearchMessages(q);
			
			List<GMessage> gmessages = getMessageContents(messages);
			for (GMessage message : gmessages) {
				System.out.println(message.toPrettyString(true));
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param query 
	 * sample: Retrieves all messages sent by the user between Jan 1, 2014 and Jan 30, 2014
	 *  => query="in:sent after:2014/01/01 before:2014/01/30"
	 *  reference here: https://support.google.com/mail/answer/7190?hl=en
	 * @return
	 * @throws IOException
	 */
	public static List<Message> listSearchMessages(String query) throws IOException {
		Gmail service = GoogleConnector.getInstance().getService();
        ListMessagesResponse response = service.users().messages().list("me").setQ(query).execute();
        List<Message> messages = new ArrayList<Message>();
        while (response.getMessages() != null) {
            messages.addAll(response.getMessages());
            if (response.getNextPageToken() != null) {
                String pageToken = response.getNextPageToken();
                response = service.users().messages().list("me").setQ(query).setPageToken(pageToken).execute();
            } else
                break;
        }
        return messages;
    }
	
	public static List<GMessage> getMessageContents(List<Message> messages) throws IOException {
        List<GMessage> gmessages = new ArrayList<>();
        Gmail service = GoogleConnector.getInstance().getService();
        
        for (Message m : messages) {
        	Message message = service.users().messages().get("me", m.getId()).execute();
        	gmessages.add(new GMessage(message));
		}
        return gmessages;
    }
	
	/**
	   * Get Message with given ID.
	   *
	   * @param service Authorized Gmail API instance.
	   * @param userId User's email address. The special value "me"
	   * can be used to indicate the authenticated user.
	   * @param messageId ID of Message to retrieve.
	   * @return Message Retrieved Message.
	   * @throws IOException
	   */
	  public static Message getMessage(Gmail service, String userId, String messageId)
	      throws IOException {
	    Message message = service.users().messages().get(userId, messageId).execute();

	    System.out.println("Message snippet: " + message.getSnippet());

	    return message;
	  }

}
