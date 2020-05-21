package com.internship.frejaeidjmeterplugin.jmeter.sampler.gui.panel;

import com.internship.frejaeidjmeterplugin.jmeter.frejaRequests.MobileClientService;
import com.internship.frejaeidjmeterplugin.jmeter.sampler.gui.FrejaEidPluginGui;
import com.internship.frejaeidjmeterplugin.jmeter.settings.EmailSettings;
import com.internship.frejaeidjmeterplugin.jmeter.settings.EnviromentSettings;
import com.internship.frejaeidjmeterplugin.jmeter.threads.ConnectMobileClientsWithEmailsThread;
import com.internship.frejaeidjmeterplugin.jmeter.threads.InitializeSingleMobileClientThread;
import com.verisec.frejaeid.commons.exception.FrejaEidException;
import com.verisec.frejaeid.mobileclient.clients.impl.MobileClient;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

public class EmailInputPanel extends javax.swing.JPanel {

     // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBrowse;
    private javax.swing.ButtonGroup btnGroupEmailInput;
    private javax.swing.JButton btnInitializePreconditions;
    private javax.swing.JLabel lblLoadingPicture;
    private javax.swing.JLabel lblStatusPicture;
    private javax.swing.JLabel lblTitleEmail;
    private javax.swing.JLabel lblTitlePassword;
    private javax.swing.JRadioButton radioSingleUser;
    private javax.swing.JRadioButton radioUserListFromFile;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUserListFromFile;
    // End of variables declaration//GEN-END:variables

    public EmailInputPanel() {
        initComponents();
        setDefaultSettings();
        populateBtnGroupEmailInput();
    }

    public JButton getBtnInitializePreconditions() {
        return btnInitializePreconditions;
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public JPasswordField getTxtPassword() {
        return txtPassword;
    }

    public JLabel getLblLoadingPicture() {
        return lblLoadingPicture;
    }

    public JLabel getLblStatusPicture() {
        return lblStatusPicture;
    }

    public JRadioButton getRadioSingleUser() {
        return radioSingleUser;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        initializeVariables();
        setTitles();
        addActionOnRadioSingleUser();
        addActionOnRadioUserListFromFile();
        addActionOnBtnBrowse();
        addActionOnBtnInitializePreconditions();
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        setLayoutHorizontal(layout);
        setLayoutVertical(layout);
    }// </editor-fold>//GEN-END:initComponents

    private void chooseSingleUser(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioSingleUserActionPerformed
        // TODO add your handling code here:
        txtEmail.setEditable(true);
        txtPassword.setEditable(true);
        txtUserListFromFile.setEditable(false);
        btnBrowse.setEnabled(false);
    }//GEN-LAST:event_radioSingleUserActionPerformed
    private void chooseListOfUsersFromFile(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioUserListFromFileActionPerformed
        // TODO add your handling code here:
        txtEmail.setEditable(false);
        txtPassword.setEditable(false);
        txtUserListFromFile.setEditable(true);
        btnBrowse.setEnabled(true);
    }//GEN-LAST:event_radioUserListFromFileActionPerformed

    private void openFileChooser(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        addFilterToFileChooser(fileChooser);
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            EmailSettings.setFileWithUsersInfo(file);
            txtUserListFromFile.setText(file.getAbsolutePath());
        } else {
            JOptionPane.showMessageDialog(fileChooser, "Something went wrong. Please try again.", "File Selection Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBrowseActionPerformed

    private void initializePreconditions(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInitializePreconditionsActionPerformed
        // TODO add your handling code here:
        lblStatusPicture.setVisible(false);
        lblLoadingPicture.setVisible(true);
        btnInitializePreconditions.setVisible(false);
        try {
            if (radioSingleUser.isSelected()) {
                MobileClientService.getInstance().setSingleMobileClient(new MobileClient(EnviromentSettings.getWebSocketAdress(), EnviromentSettings.getWebSocketKeystorePath(), EnviromentSettings.getKeystorePassword()));
                InitializeSingleMobileClientThread thread = new InitializeSingleMobileClientThread(lblLoadingPicture, lblStatusPicture, FrejaEidPluginGui.getFrejaEIDPluginGuiPanel().getBtnSaveConfiguration(), btnInitializePreconditions, txtEmail.getText(), new String(txtPassword.getPassword()));
                thread.start();
            } else {
                ConnectMobileClientsWithEmailsThread thread = new ConnectMobileClientsWithEmailsThread(lblLoadingPicture, lblStatusPicture, FrejaEidPluginGui.getFrejaEIDPluginGuiPanel().getBtnSaveConfiguration(), btnInitializePreconditions);
                thread.start();
            }
        } catch (FrejaEidException ex) {
            ex.printStackTrace();
        }

    }//GEN-LAST:event_btnInitializePreconditionsActionPerformed

    private void initializeVariables() {
        btnGroupEmailInput = new javax.swing.ButtonGroup();
        radioSingleUser = new javax.swing.JRadioButton();
        txtEmail = new javax.swing.JTextField();
        lblTitleEmail = new javax.swing.JLabel();
        lblTitlePassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        radioUserListFromFile = new javax.swing.JRadioButton();
        txtUserListFromFile = new javax.swing.JTextField();
        btnBrowse = new javax.swing.JButton();
        lblLoadingPicture = new javax.swing.JLabel();
        lblStatusPicture = new javax.swing.JLabel();
        btnInitializePreconditions = new javax.swing.JButton();
    }

    private void setTitles() {
        setBorder(javax.swing.BorderFactory.createTitledBorder("Email input"));
        lblTitleEmail.setText("Email:");
        lblTitlePassword.setText("Password:");
        radioUserListFromFile.setText("User List from File:");
        btnBrowse.setText("Browse");
        radioSingleUser.setText("Single User:");
        lblLoadingPicture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/loader.gif"))); // NOI18N
        lblLoadingPicture.setText("Mobile clients are initializing ...");
        btnInitializePreconditions.setText("Initialize preconditions");
    }

    private void addActionOnRadioSingleUser() {
        radioSingleUser.addActionListener(this::chooseSingleUser);
    }

    private void addActionOnRadioUserListFromFile() {
        radioUserListFromFile.addActionListener(this::chooseListOfUsersFromFile);

    }

    private void addActionOnBtnBrowse() {
        btnBrowse.addActionListener(this::openFileChooser);
    }

    private void addActionOnBtnInitializePreconditions() {
        btnInitializePreconditions.addActionListener(this::initializePreconditions);
    }

    private void setLayoutHorizontal(javax.swing.GroupLayout layout) {
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(25, 25, 25)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblTitlePassword)
                                                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblLoadingPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(txtUserListFromFile, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(btnBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(lblTitleEmail)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(radioSingleUser)))
                                .addGap(0, 57, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(radioUserListFromFile)
                                        .addComponent(btnInitializePreconditions, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblStatusPicture))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    private void setLayoutVertical(javax.swing.GroupLayout layout) {
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(radioSingleUser)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblTitleEmail)
                                .addGap(8, 8, 8)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblTitlePassword)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(radioUserListFromFile)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtUserListFromFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnBrowse))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblLoadingPicture, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblStatusPicture)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnInitializePreconditions)
                                .addGap(71, 71, 71))
        );
    }

    private void addFilterToFileChooser(JFileChooser fileChooser) {
        fileChooser.addChoosableFileFilter(new FileFilter() {
            public String getDescription() {
                return "Text Files (*.txt)";
            }

            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    return f.getName().toLowerCase().endsWith(".txt");
                }
            }
        });
    }

    private void setDefaultSettings() {
        lblLoadingPicture.setVisible(false);
        btnBrowse.setEnabled(false);
        txtUserListFromFile.setEditable(false);
        radioSingleUser.setSelected(true);
        txtUserListFromFile.setEditable(false);
        btnBrowse.setEnabled(false);
    }

    private void populateBtnGroupEmailInput() {
        btnGroupEmailInput.add(radioSingleUser);
        btnGroupEmailInput.add(radioUserListFromFile);
    }
}
