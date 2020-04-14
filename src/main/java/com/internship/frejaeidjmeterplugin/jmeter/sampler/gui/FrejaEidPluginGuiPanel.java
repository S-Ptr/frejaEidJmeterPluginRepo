package com.internship.frejaeidjmeterplugin.jmeter.sampler.gui;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

public class FrejaEidPluginGuiPanel extends javax.swing.JPanel {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkAuth;
    private javax.swing.JCheckBox checkOpenSecureConnection;
    private javax.swing.JCheckBox checkSign;
    private javax.swing.JLabel lblTitleEmaill;
    private javax.swing.JTextField txtEmail;
    // End of variables declaration//GEN-END:variables

    public FrejaEidPluginGuiPanel() {
        initComponents();
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        initializeVariables();
        setTitles();
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        setLayoutHorizontal(layout);
        setLayoutVertical(layout);
    }// </editor-fold>//GEN-END:initComponents

    private void initializeVariables() {
        checkAuth = new javax.swing.JCheckBox();
        checkSign = new javax.swing.JCheckBox();
        lblTitleEmaill = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        checkOpenSecureConnection = new javax.swing.JCheckBox();
    }

    private void setTitles() {
        checkAuth.setText("InitAuthentication enabled");
        checkSign.setText("InitSign enabled");
        lblTitleEmaill.setText("Email:");
        checkOpenSecureConnection.setText("Open secure connection enabled");
    }

    private void setLayoutHorizontal(javax.swing.GroupLayout layout) {
         layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkOpenSecureConnection)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkSign)
                    .addComponent(checkAuth)
                    .addComponent(lblTitleEmaill))
                .addContainerGap(122, Short.MAX_VALUE))
        );
    }

    private void setLayoutVertical(javax.swing.GroupLayout layout) {
       layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(checkAuth)
                .addGap(18, 18, 18)
                .addComponent(checkSign)
                .addGap(15, 15, 15)
                .addComponent(checkOpenSecureConnection)
                .addGap(26, 26, 26)
                .addComponent(lblTitleEmaill)
                .addGap(18, 18, 18)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
    }

}
