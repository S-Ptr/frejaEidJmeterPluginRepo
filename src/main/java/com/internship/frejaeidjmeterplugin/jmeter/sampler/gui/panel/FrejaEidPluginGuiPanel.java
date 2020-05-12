package com.internship.frejaeidjmeterplugin.jmeter.sampler.gui.panel;

import com.internship.frejaeidjmeterplugin.jmeter.enums.Enviroment;
import com.internship.frejaeidjmeterplugin.jmeter.settings.EmailSettings;
import com.internship.frejaeidjmeterplugin.jmeter.settings.EnviromentSettings;
import java.io.File;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

public class FrejaEidPluginGuiPanel extends javax.swing.JPanel {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBrowse;
    private javax.swing.ButtonGroup btnGroupEmailInput;
    private javax.swing.JCheckBox checkAuth;
    private javax.swing.JCheckBox checkOpenSecureConnection;
    private javax.swing.JCheckBox checkSign;
    private javax.swing.JComboBox<String> cmbEnviroment;
    private javax.swing.JLabel lblTitleEmailInput;
    private javax.swing.JLabel lblTitleEnviroment;
    private javax.swing.JLabel lblTitleRequests;
    private javax.swing.JRadioButton radioSingleUser;
    private javax.swing.JRadioButton radioUserListFromFile;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtUserListFromFile;
    // End of variables declaration//GEN-END:variables

    public FrejaEidPluginGuiPanel() {
        initComponents();
        setComboEnviroment();
        populateBtnbtnGroupEmailInput();
    }

    public JCheckBox getCheckOpenSecureConnection() {
        return checkOpenSecureConnection;
    }

    public JCheckBox getCheckAuth() {
        return checkAuth;
    }

    public JCheckBox getCheckSign() {
        return checkSign;
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(String text) {
        this.txtEmail.setText(text);
    }

    private void setComboEnviroment() {
        cmbEnviroment.setModel(new DefaultComboBoxModel(Enviroment.values()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        initializeVariables();
        setTitles();
        setFields();
        addActionOnComboBoxEnviroment();
        addActionOnBtnBrowse();
        addActionOnRadioSingleUser();
        addActionOnRadioUserListFromFile();
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        setLayoutHorizontal(layout);
        setLayoutVertical(layout); 
    }// </editor-fold>//GEN-END:initComponents

    private void setEnviromentSettings(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEnviromentActionPerformed
        // TODO add your handling code here:
        EnviromentSettings.setEnviroment((Enviroment) cmbEnviroment.getSelectedItem());
        EnviromentSettings.populateSettings();
    }//GEN-LAST:event_cmbEnviromentActionPerformed

    private void openFileChooser(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        addFilterToFileChooser(fileChooser);
        int returnVal = fileChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            EmailSettings.populateListOfEmails(file);
            txtUserListFromFile.setText(file.getAbsolutePath());
        } else {
            JOptionPane.showMessageDialog(fileChooser, "Something went wrong. Please try again.", "File Selection Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBrowseActionPerformed

    private void disableUserListFromFile(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioSingleUserActionPerformed
        // TODO add your handling code here:
        txtEmail.setEnabled(true);
        txtUserListFromFile.setEditable(false);
        btnBrowse.setEnabled(false);
    }//GEN-LAST:event_radioSingleUserActionPerformed

    private void disableSingleUser(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioUserListFromFileActionPerformed
        // TODO add your handling code here:
        txtEmail.setEnabled(false);
        txtUserListFromFile.setEditable(true);
        btnBrowse.setEnabled(true);
    }//GEN-LAST:event_radioUserListFromFileActionPerformed

    private void initializeVariables() {
        btnGroupEmailInput = new javax.swing.ButtonGroup();
        checkAuth = new javax.swing.JCheckBox();
        checkSign = new javax.swing.JCheckBox();
        lblTitleEmailInput = new javax.swing.JLabel();
        checkOpenSecureConnection = new javax.swing.JCheckBox();
        lblTitleEnviroment = new javax.swing.JLabel();
        cmbEnviroment = new javax.swing.JComboBox<>();
        lblTitleRequests = new javax.swing.JLabel();
        radioSingleUser = new javax.swing.JRadioButton();
        radioUserListFromFile = new javax.swing.JRadioButton();
        txtEmail = new javax.swing.JTextField();
        txtUserListFromFile = new javax.swing.JTextField();
        btnBrowse = new javax.swing.JButton();
    }

    private void setTitles() {
        checkAuth.setText("InitAuthentication enabled");
        checkSign.setText("InitSign enabled");
        lblTitleEmailInput.setText("Email input:");
        checkOpenSecureConnection.setText("Open secure connection enabled");
        lblTitleEnviroment.setText("Enviroment:");
        btnBrowse.setText("Browse");
        lblTitleRequests.setText("Requests:");
        radioSingleUser.setText("Single User:");
        radioUserListFromFile.setText("User List from File:");
    }

    private void addActionOnComboBoxEnviroment() {
        cmbEnviroment.addActionListener(this::setEnviromentSettings);
    }

    private void addActionOnBtnBrowse() {
        btnBrowse.addActionListener(this::openFileChooser);
    }

    private void addActionOnRadioSingleUser() {
        radioSingleUser.addActionListener(this::disableUserListFromFile);
    }

    private void addActionOnRadioUserListFromFile() {
        radioUserListFromFile.addActionListener(this::disableSingleUser);
    }

    private void setFields() {
        btnBrowse.setEnabled(false);
        txtUserListFromFile.setEditable(false);
        radioSingleUser.setSelected(true);
    }

    private void setLayoutHorizontal(javax.swing.GroupLayout layout) {
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblTitleRequests)
                                        .addComponent(lblTitleEmailInput)
                                        .addComponent(lblTitleEnviroment))
                                .addGap(39, 39, 39)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(radioUserListFromFile)
                                                        .addComponent(radioSingleUser)
                                                        .addComponent(checkOpenSecureConnection)
                                                        .addComponent(checkSign)
                                                        .addComponent(checkAuth)
                                                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(txtUserListFromFile, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(btnBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(cmbEnviroment, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 64, Short.MAX_VALUE))))
        );
    }

    private void setLayoutVertical(javax.swing.GroupLayout layout) {
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(checkAuth)
                                        .addComponent(lblTitleRequests))
                                .addGap(18, 18, 18)
                                .addComponent(checkSign)
                                .addGap(18, 18, 18)
                                .addComponent(checkOpenSecureConnection)
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblTitleEmailInput)
                                        .addComponent(radioSingleUser))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(radioUserListFromFile)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtUserListFromFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnBrowse))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblTitleEnviroment)
                                        .addComponent(cmbEnviroment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(46, Short.MAX_VALUE))
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

    private void populateBtnbtnGroupEmailInput() {
        btnGroupEmailInput.add(radioSingleUser);
        btnGroupEmailInput.add(radioUserListFromFile);
    }

}
