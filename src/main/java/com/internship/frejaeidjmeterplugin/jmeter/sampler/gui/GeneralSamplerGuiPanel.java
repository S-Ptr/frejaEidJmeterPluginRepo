package com.internship.frejaeidjmeterplugin.jmeter.sampler.gui;

import com.internship.frejaeidjmeterplugin.jmeter.frejaRequests.FrejaRequestType;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GeneralSamplerGuiPanel extends JPanel {

    public static FrejaRequestType requestType;
    private boolean isAuthChecked;
    private boolean isSignChecked;
    
    public GeneralSamplerGuiPanel() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        checkAuth = new javax.swing.JCheckBox();
        checkSign = new javax.swing.JCheckBox();
        lblError = new javax.swing.JLabel();
        txtAuthEmail = new javax.swing.JTextField();

        setBorder(javax.swing.BorderFactory.createTitledBorder("General request"));

        checkAuth.setText(" InitAuthentication enabled");
        checkAuth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkAuthActionPerformed(evt);
            }
        });

        checkSign.setText("InitSign enabled");
        checkSign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkSignActionPerformed(evt);
            }
        });

        lblError.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(checkAuth)
                        .addContainerGap(110, Short.MAX_VALUE))
                    .addComponent(lblError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAuthEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkSign))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(checkAuth)
                .addGap(18, 18, 18)
                .addComponent(checkSign)
                .addGap(18, 18, 18)
                .addComponent(lblError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(txtAuthEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void checkAuthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkAuthActionPerformed
        // TODO add your handling code here:
        lblError.setText("");
        isAuthChecked = !isAuthChecked;
        if (isSignChecked){
            lblError.setText("Please select one of this options");
        }
    }//GEN-LAST:event_checkAuthActionPerformed

    private void checkSignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkSignActionPerformed
        // TODO add your handling code here:
        lblError.setText("");
        isSignChecked = !isSignChecked;
        if (isAuthChecked){
            lblError.setText("Please select one of this options");
        }
    }//GEN-LAST:event_checkSignActionPerformed

    public JTextField getTxtAuthEmail() {
        return txtAuthEmail;
    }

    public void setTxtAuthEmail(String text) {
        this.txtAuthEmail.setText(text);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkAuth;
    private javax.swing.JCheckBox checkSign;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblError;
    private javax.swing.JTextField txtAuthEmail;
    // End of variables declaration//GEN-END:variables
}
