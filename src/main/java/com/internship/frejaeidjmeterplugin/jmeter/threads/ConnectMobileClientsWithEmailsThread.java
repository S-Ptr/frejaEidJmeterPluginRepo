package com.internship.frejaeidjmeterplugin.jmeter.threads;

import com.internship.frejaeidjmeterplugin.jmeter.frejaRequests.MobileClientService;
import com.internship.frejaeidjmeterplugin.jmeter.settings.EmailSettings;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;

public class ConnectMobileClientsWithEmailsThread extends Thread {

    private final JLabel lblLoadingPicture;
    private final JLabel lblStatusPicture;
    private boolean running;
    private final JButton btnSaveConfiguration;
    private final JButton btnInitialize;

    public ConnectMobileClientsWithEmailsThread(JLabel lblLoadingPicture, JLabel lblStatusPicture, JButton btnSaveConfiguration, JButton btnInitialize) {
        this.lblLoadingPicture = lblLoadingPicture;
        this.lblStatusPicture = lblStatusPicture;
        running = true;
        this.btnSaveConfiguration = btnSaveConfiguration;
        this.btnInitialize = btnInitialize;
    }

    @Override
    public void run() {
        while (running) {
            try {
                EmailSettings.setSingleUser(false);
                EmailSettings.populateListOfEmails();
                MobileClientService.getInstance().connectMobileClientsWithEmails(EmailSettings.getEmails(), EmailSettings.getPasswords());
                setView("Initializing are complete", "/images/check.png");
                btnSaveConfiguration.setEnabled(true);
                running = false;
            } catch (Exception ex) {
                setView("Email or password are incorrect", "/images/close.png");
                running = false;
                btnInitialize.setVisible(true);
                Logger.getLogger(ConnectMobileClientsWithEmailsThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void setView(String lblStatusPictureText, String lblStatusPictureImagePath) {
        lblLoadingPicture.setVisible(false);
        lblStatusPicture.setText(lblStatusPictureText);
        lblStatusPicture.setIcon(new javax.swing.ImageIcon(getClass().getResource(lblStatusPictureImagePath)));
        lblStatusPicture.setVisible(true);
    }
}
