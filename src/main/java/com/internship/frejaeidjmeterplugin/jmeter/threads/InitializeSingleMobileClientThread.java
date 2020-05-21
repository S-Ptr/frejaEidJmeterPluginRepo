package com.internship.frejaeidjmeterplugin.jmeter.threads;

import com.internship.frejaeidjmeterplugin.jmeter.frejaRequests.MobileClientService;
import com.internship.frejaeidjmeterplugin.jmeter.settings.EmailSettings;
import com.verisec.frejaeid.commons.exception.FrejaEidException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

import javax.swing.JLabel;

public class InitializeSingleMobileClientThread extends Thread {

    private final JLabel lblLoadingPicture;
    private final JLabel lblStatusPicture;
    private boolean running;
    private final JButton btnSaveConfigurationn;
    private final JButton btnInitialize;
    private final String email;
    private final String password;

    public InitializeSingleMobileClientThread(JLabel lblPicture,JLabel lblStatusPicture, JButton saveConfiguration, JButton btnInitialize, String email, String password) {
        this.lblLoadingPicture = lblPicture;
        this.email = email;
        this.password = password;
        running = true;
        this.btnSaveConfigurationn = saveConfiguration;
        this.btnInitialize = btnInitialize;
        this.lblStatusPicture = lblStatusPicture;
    }

    @Override
    public void run() {
        while (running) {
            try {
                EmailSettings.setSingleUserEmail(email);
                EmailSettings.setSingleUser(true);
                MobileClientService.getInstance().initializeSingleMobileClient(email, password);
                setView("Initializing are complete", "/images/check.png");
                btnSaveConfigurationn.setEnabled(true);
                running = false;
            } catch (FrejaEidException ex) {
                setView("Email or password are incorrect", "/images/close.png");
                running = false;
                btnInitialize.setVisible(true);
                ex.printStackTrace();
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