package com.internship.frejaeidjmeterplugin.jmeter.sampler.gui;

import com.internship.frejaeidjmeterplugin.jmeter.enums.Enviroment;
import com.internship.frejaeidjmeterplugin.jmeter.settings.EnviromentSettings;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

public class FrejaEidPluginGuiPanel extends javax.swing.JPanel {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkAuth;
    private javax.swing.JCheckBox checkOpenSecureConnection;
    private javax.swing.JCheckBox checkSign;
    private javax.swing.JComboBox<Enviroment> cmbEnviroment;
    private javax.swing.JLabel lblTitleEmaill;
    private javax.swing.JLabel lblTitleEnviroment;
    private javax.swing.JTextField txtEmail;
    // End of variables declaration//GEN-END:variables

    public FrejaEidPluginGuiPanel() {
        initComponents();
        setComboEnviroment();
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
        addActionOnComboBoxEnviroment();
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        setLayoutHorizontal(layout);
        setLayoutVertical(layout);    
    }// </editor-fold>//GEN-END:initComponents

    private void setEnviromentSettings(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEnviromentActionPerformed
        // TODO add your handling code here:
        EnviromentSettings.setEnviroment((Enviroment) cmbEnviroment.getSelectedItem());
    }//GEN-LAST:event_cmbEnviromentActionPerformed

    private void initializeVariables() {
        checkAuth = new javax.swing.JCheckBox();
        checkSign = new javax.swing.JCheckBox();
        lblTitleEmaill = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        checkOpenSecureConnection = new javax.swing.JCheckBox();
        lblTitleEnviroment = new javax.swing.JLabel();
        cmbEnviroment = new javax.swing.JComboBox<>();
    }

    private void setTitles() {
        checkAuth.setText("InitAuthentication enabled");
        checkSign.setText("InitSign enabled");
        lblTitleEmaill.setText("Email:");
        checkOpenSecureConnection.setText("Open secure connection enabled");
        lblTitleEnviroment.setText("Enviroment:");
    }

    private void addActionOnComboBoxEnviroment() {
        cmbEnviroment.addActionListener(this::setEnviromentSettings);
    }

    private void setLayoutHorizontal(javax.swing.GroupLayout layout) {
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(83, 83, 83)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lblTitleEnviroment)
                                        .addComponent(checkOpenSecureConnection)
                                        .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                                        .addComponent(checkSign)
                                        .addComponent(checkAuth)
                                        .addComponent(lblTitleEmaill)
                                        .addComponent(cmbEnviroment, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(122, Short.MAX_VALUE))
        );
    }

    private void setLayoutVertical(javax.swing.GroupLayout layout) {
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(checkAuth)
                                .addGap(18, 18, 18)
                                .addComponent(checkSign)
                                .addGap(18, 18, 18)
                                .addComponent(checkOpenSecureConnection)
                                .addGap(18, 18, 18)
                                .addComponent(lblTitleEmaill)
                                .addGap(18, 18, 18)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblTitleEnviroment)
                                .addGap(18, 18, 18)
                                .addComponent(cmbEnviroment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(17, Short.MAX_VALUE))
        );
    }

}
