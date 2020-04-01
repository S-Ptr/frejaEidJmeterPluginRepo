package com.internship.frejaeidjmeterplugin.jmeter.sampler.gui;

import com.internship.frejaeidjmeterplugin.jmeter.visualizer.gui.ResultPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JCheckBox;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class FrejaEidPluginGuiPanel extends javax.swing.JPanel {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkAuth;
    private javax.swing.JCheckBox checkSign;
    private javax.swing.JLabel lblTitleEmaill;
    private javax.swing.JPanel requestPanel;
    private javax.swing.JTextField txtEmail;
    // End of variables declaration//GEN-END:variables

    private ResultPanel authResults;
    private ResultPanel signResults;

    public FrejaEidPluginGuiPanel() {
        initComponents();
        setForm();
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(String email) {
        txtEmail.setText(email);
    }

    public JCheckBox getCheckAuth() {
        return checkAuth;
    }

    public JCheckBox getCheckSign() {
        return checkSign;
    }

    public ResultPanel getAuthResults() {
        return authResults;
    }

    public ResultPanel getSignResults() {
        return signResults;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        requestPanel = new javax.swing.JPanel();
        checkAuth = new javax.swing.JCheckBox();
        checkSign = new javax.swing.JCheckBox();
        lblTitleEmaill = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();

        checkAuth.setText("InitAuthentication enabled");

        checkSign.setText("InitSign enabled");

        lblTitleEmaill.setText("Email:");

        javax.swing.GroupLayout requestPanelLayout = new javax.swing.GroupLayout(requestPanel);
        requestPanel.setLayout(requestPanelLayout);
        requestPanelLayout.setHorizontalGroup(
            requestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(requestPanelLayout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(requestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkSign)
                    .addComponent(checkAuth)
                    .addComponent(lblTitleEmaill))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        requestPanelLayout.setVerticalGroup(
            requestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(requestPanelLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(checkAuth)
                .addGap(18, 18, 18)
                .addComponent(checkSign)
                .addGap(56, 56, 56)
                .addComponent(lblTitleEmaill)
                .addGap(18, 18, 18)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(requestPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(requestPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void initializeVariables() {
        requestPanel = new javax.swing.JPanel();
        checkAuth = new javax.swing.JCheckBox();
        checkSign = new javax.swing.JCheckBox();
        lblTitleEmaill = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
    }

    private void setTitles() {
        checkAuth.setText("InitAuthentication enabled");
        checkSign.setText("InitSign enabled");
        lblTitleEmaill.setText("Email:");
    }

    private void setRequestPanel() {
        javax.swing.GroupLayout requestPanelLayout = new javax.swing.GroupLayout(requestPanel);
        requestPanel.setLayout(requestPanelLayout);
        setRequestPanelHorizontalGroup(requestPanelLayout);
        setRequestPanelVerticalGroup(requestPanelLayout);
    }

    private void setRequestPanelHorizontalGroup(javax.swing.GroupLayout requestPanelLayout) {
        requestPanelLayout.setHorizontalGroup(
                requestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(requestPanelLayout.createSequentialGroup()
                                .addGap(83, 83, 83)
                                .addGroup(requestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(checkSign)
                                        .addComponent(checkAuth)
                                        .addComponent(lblTitleEmaill))
                                .addContainerGap(90, Short.MAX_VALUE))
        );
    }

    private void setRequestPanelVerticalGroup(javax.swing.GroupLayout requestPanelLayout) {
        requestPanelLayout.setVerticalGroup(
                requestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(requestPanelLayout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(checkAuth)
                                .addGap(18, 18, 18)
                                .addComponent(checkSign)
                                .addGap(56, 56, 56)
                                .addComponent(lblTitleEmaill)
                                .addGap(18, 18, 18)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(33, Short.MAX_VALUE))
        );
    }

    private void setHorizontalGroup(javax.swing.GroupLayout layout) {
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(requestPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(23, Short.MAX_VALUE))
        );
    }

    private void setVerticalGroup(javax.swing.GroupLayout layout) {
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(requestPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(32, Short.MAX_VALUE))
        );
    }

    private void setForm() {
        this.setLayout(new GridBagLayout());
        authResults = new ResultPanel();
        signResults = new ResultPanel();
        JTabbedPane jTP = new JTabbedPane();
        jTP.add("Request", requestPanel);
        jTP.add("Auth result", authResults);
        jTP.add("Sign result", signResults);
        GridBagConstraints myConstraints = new GridBagConstraints();
        myConstraints.ipadx = 50;
        myConstraints.ipady = 50;
        this.add(jTP, myConstraints);
    }

}
