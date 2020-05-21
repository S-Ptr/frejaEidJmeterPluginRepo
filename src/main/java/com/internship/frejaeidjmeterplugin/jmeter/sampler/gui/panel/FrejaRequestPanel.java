package com.internship.frejaeidjmeterplugin.jmeter.sampler.gui.panel;

import com.internship.frejaeidjmeterplugin.jmeter.sampler.gui.FrejaEidPluginGui;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;


public class FrejaRequestPanel extends javax.swing.JPanel {
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGroupAuth;
    private javax.swing.ButtonGroup btnGroupSign;
    private javax.swing.JCheckBox checkAuth;
    private javax.swing.JCheckBox checkSign;
    private javax.swing.JRadioButton radioApproveAuth;
    private javax.swing.JRadioButton radioApproveSign;
    private javax.swing.JRadioButton radioDeclineAuth;
    private javax.swing.JRadioButton radioDeclineSign;
    private javax.swing.JRadioButton radioNoActionAuth;
    private javax.swing.JRadioButton radioNoActionSign;
    // End of variables declaration//GEN-END:variables

    private boolean authChecked;
    private boolean signChecked;

    public FrejaRequestPanel() {
        initComponents();
        setDefaultSettings();
        populateBtnGroupAuth();
        populateBtnGroupSign();
    }

    public JRadioButton getRadioApproveAuth() {
        return radioApproveAuth;
    }

    public JRadioButton getRadioApproveSign() {
        return radioApproveSign;
    }

    public JRadioButton getRadioDeclineAuth() {
        return radioDeclineAuth;
    }

    public JRadioButton getRadioDeclineSign() {
        return radioDeclineSign;
    }

    public JRadioButton getRadioNoActionAuth() {
        return radioNoActionAuth;
    }

    public JRadioButton getRadioNoActionSign() {
        return radioNoActionSign;
    }
    
    public JCheckBox getCheckAuth() {
        return checkAuth;
    }

    public JCheckBox getCheckSign() {
        return checkSign;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        initializeVariables();
        setTitles();
        //addActionOnCheckAuth();
        //addActionOnCheckSign();
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        setLayoutHorizontal(layout);
        setLayoutVertical(layout);
    }// </editor-fold>//GEN-END:initComponents

    /* This methods are used for approve or decline transactions
    private void checkAuth(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkAuthActionPerformed
        // TODO add your handling code here:
        authChecked = !authChecked;
        setVisibleBtnGroupAuth(authChecked);
        setOpenSecureConnectionCheckBox();
    }//GEN-LAST:event_checkAuthActionPerformed

    private void checkSign(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkSignActionPerformed
        // TODO add your handling code here:
        signChecked = !signChecked;
        setVisibleBtnGroupSign(signChecked);
        setOpenSecureConnectionCheckBox();
    }//GEN-LAST:event_checkSignActionPerformed
*/
    private void populateBtnGroupAuth() {
        btnGroupAuth.add(radioApproveAuth);
        btnGroupAuth.add(radioDeclineAuth);
        btnGroupAuth.add(radioNoActionAuth);
        radioApproveAuth.setSelected(true);
    }

    private void populateBtnGroupSign() {
        btnGroupSign.add(radioApproveSign);
        btnGroupSign.add(radioDeclineSign);
        btnGroupSign.add(radioNoActionSign);
        radioApproveSign.setSelected(true);
    }

    private void setVisibleBtnGroupAuth(boolean value) {
        radioApproveAuth.setVisible(value);
        radioDeclineAuth.setVisible(value);
        radioNoActionAuth.setVisible(value);
    }

    private void setVisibleBtnGroupSign(boolean value) {
        radioApproveSign.setVisible(value);
        radioDeclineSign.setVisible(value);
        radioNoActionSign.setVisible(value);
    }

    private void setDefaultSettings() {
        setVisibleBtnGroupAuth(false);
        setVisibleBtnGroupSign(false);
        authChecked = false;
        signChecked = false;
    }

    private void setOpenSecureConnectionCheckBox() {
        if (signChecked || authChecked) {
            FrejaEidPluginGui.getFrejaEIDPluginGuiPanel().getCheckOpenSecureConnection().setEnabled(false);
            FrejaEidPluginGui.getFrejaEIDPluginGuiPanel().getCheckOpenSecureConnection().setSelected(false);
        } else {
            FrejaEidPluginGui.getFrejaEIDPluginGuiPanel().getCheckOpenSecureConnection().setEnabled(true);
        }
    }

    private void initializeVariables () {
        btnGroupAuth = new javax.swing.ButtonGroup();
        btnGroupSign = new javax.swing.ButtonGroup();
        checkAuth = new javax.swing.JCheckBox();
        radioApproveAuth = new javax.swing.JRadioButton();
        radioDeclineAuth = new javax.swing.JRadioButton();
        radioNoActionAuth = new javax.swing.JRadioButton();
        checkSign = new javax.swing.JCheckBox();
        radioApproveSign = new javax.swing.JRadioButton();
        radioDeclineSign = new javax.swing.JRadioButton();
        radioNoActionSign = new javax.swing.JRadioButton();
    }

    private void setTitles () {
        setBorder(javax.swing.BorderFactory.createTitledBorder("Freja eID Requests"));
        radioApproveAuth.setText("approve");
        radioDeclineAuth.setText("decline");
        radioNoActionAuth.setText("no action");
        checkSign.setText("InitSign enabled");
        checkAuth.setText("InitAuthentication enabled");
        radioApproveSign.setText("approve");
        radioDeclineSign.setText("decline");
        radioNoActionSign.setText("no action");
    }
    /* This methods are used for approve and decline transactions
    private void addActionOnCheckAuth() {
        checkAuth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkAuth(evt);
            }
        });
    }

    private void addActionOnCheckSign() {
        checkSign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkSign(evt);
            }
        });
    }
*/
    private void setLayoutHorizontal(javax.swing.GroupLayout layout) {
              layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkAuth)
                    .addComponent(checkSign)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(radioApproveSign)
                            .addComponent(radioDeclineSign)
                            .addComponent(radioNoActionSign)
                            .addComponent(radioApproveAuth)
                            .addComponent(radioDeclineAuth)
                            .addComponent(radioNoActionAuth))))
                .addContainerGap(275, Short.MAX_VALUE))
        );
    }

    private void setLayoutVertical(javax.swing.GroupLayout layout) {
            layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(checkAuth)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radioApproveAuth)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radioDeclineAuth)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radioNoActionAuth)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(checkSign)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radioApproveSign)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radioDeclineSign)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radioNoActionSign)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }
}
