package com.internship.frejaeidjmeterplugin.jmeter.email.impl;

import com.internship.frejaeidjmeterplugin.jmeter.email.ConfirmEmailServiceApi;
import com.internship.frejaeidjmeterplugin.jmeter.http.impl.HttpService;
import com.internship.frejaeidjmeterplugin.jmeter.http.HttpServiceApi;
import com.internship.frejaeidjmeterplugin.jmeter.settings.EnviromentSettings;
import com.verisec.frejaeid.commons.exception.FrejaEidException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

public class ConfirmEmailService implements ConfirmEmailServiceApi {

    private Store store;
    private Folder emailFolder;
    private static final String HOST = "imap.gmail.com";
    private static final String MAIL_STORE_PROTOCOL_KEY = "mail.store.protocol";
    private static final String MAIL_STORE_PROTOCOL = "imaps";
    private static final String INBOX_FOLDER = "INBOX";
    private static final String CONFIRM_LINK_PATTERN = "href=\"([^\"]*)";
    private static final String CONFIRM_LINK_KEYWORD = "restore";
    private static final int CONFIRM_RESTORE_EMAIL_WAITING_TIME_IN_MILLISECONDS = 10000;
    private static final String CONFIRM_RESTORE_EMAIL_SUBJECT = "Confirm Email: Restore Account".toLowerCase().replaceAll("\\s+", "");
    private static final int HTTP_STATUS_CODE_OK = 200;
    private final HttpServiceApi httpService;

    public ConfirmEmailService() throws Exception {
        httpService = new HttpService(EnviromentSettings.getWebSocketKeystorePath(), EnviromentSettings.getKeystorePassword());
    }

    @Override
    public void openAndConfirmLinkFromLastEmailMessage(String email, String password) throws FrejaEidException, IOException, MessagingException, InterruptedException {
        try {
            connectToEmailServer(email, password);
            String confirmLink = getConfirmRestoreLinkFromEmailContent(getEmailMessageContent(getConfirmRestoreEmailMessage()));
            HttpGet httpGetRequest = new HttpGet(confirmLink);
            HttpResponse httpResponse = httpService.executeHttpRequest(httpGetRequest);
            if (httpResponse.getStatusLine().getStatusCode() != HTTP_STATUS_CODE_OK) {
                throw new FrejaEidException("Failed to confirm restore email.");
            }
        } catch (FrejaEidException ex) {
            throw new FrejaEidException("Failed to confirm restore email.", ex);
        } finally {
            closeEmailFolderAndStore();
        }
    }

    private String getConfirmRestoreLinkFromEmailContent(String emailContent) {
        ArrayList<String> links = new ArrayList<>();
        Pattern linkPattern = Pattern.compile(CONFIRM_LINK_PATTERN, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher pageMatcher = linkPattern.matcher(emailContent);

        while (pageMatcher.find()) {
            links.add(pageMatcher.group(1));
        }
        return links.stream().filter(link -> link.contains(CONFIRM_LINK_KEYWORD)).findFirst().get();
    }

    private void connectToEmailServer(String userEmail, String userEmailPassword) throws FrejaEidException {
        try {
            Properties properties = new Properties();
            properties.put(MAIL_STORE_PROTOCOL_KEY, MAIL_STORE_PROTOCOL);
            Session emailSession = Session.getInstance(properties);
            store = emailSession.getStore(MAIL_STORE_PROTOCOL);
            store.connect(HOST, userEmail, userEmailPassword);
        } catch (MessagingException ex) {
            throw new FrejaEidException("Failed to connect to email server.", ex);
        }
    }

    private Message getConfirmRestoreEmailMessage() throws FrejaEidException, InterruptedException {
        try {
            Thread.sleep(CONFIRM_RESTORE_EMAIL_WAITING_TIME_IN_MILLISECONDS);
            emailFolder = store.getFolder(INBOX_FOLDER);
            emailFolder.open(Folder.READ_WRITE);
            Message[] messages = emailFolder.getMessages();
            Message lastEmailMessage = messages[messages.length - 1];
            if (!lastEmailMessage.getSubject().toLowerCase().replaceAll("\\s", "").equals(CONFIRM_RESTORE_EMAIL_SUBJECT)) {
                throw new FrejaEidException("Failed to get valid email message from inbox.");
            }
            return lastEmailMessage;
        } catch (MessagingException ex) {
            throw new FrejaEidException("Failed to get last email message from inbox.", ex);
        }
    }

    private void closeEmailFolderAndStore() throws FrejaEidException {
        try {
            emailFolder.close(true);
            store.close();
        } catch (MessagingException ex) {
            throw new FrejaEidException("Failed to close email folder and store.", ex);
        }
    }

    private String getEmailMessageContent(Part part) throws IOException, MessagingException {
        Multipart mp = (Multipart) part.getContent();
        BodyPart emailContentBodyPart = mp.getBodyPart(0);
        return (String) emailContentBodyPart.getContent();
    }

}
