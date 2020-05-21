package com.internship.frejaeidjmeterplugin.jmeter.sampler.gui.panel;

import com.internship.frejaeidjmeterplugin.jmeter.enums.Enviroment;
import com.internship.frejaeidjmeterplugin.jmeter.settings.EnviromentSettings;
import com.internship.frejaeidjmeterplugin.jmeter.settings.RequestSettings;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

public class FrejaEidPluginGuiPanel extends javax.swing.JPanel {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSaveConfiguration;
    private javax.swing.JCheckBox checkOpenSecureConnection;
    private javax.swing.JComboBox<String> cmbEnviroment;
    private com.internship.frejaeidjmeterplugin.jmeter.sampler.gui.panel.EmailInputPanel emailInputPanel;
    private com.internship.frejaeidjmeterplugin.jmeter.sampler.gui.panel.FrejaRequestPanel frejaRequestPanel;
    private javax.swing.JPanel panelEnviroment;
    private javax.swing.JPanel panelMobileRequests;
    // End of variables declaration//GEN-END:variables

    public FrejaEidPluginGuiPanel() {
        initComponents();
        setDefaultSettings();
    }

    public JCheckBox getCheckOpenSecureConnection() {
        return checkOpenSecureConnection;
    }

    public JButton getBtnSaveConfiguration() {
        return btnSaveConfiguration;
    }

    private void setDefaultSettings() {
        cmbEnviroment.setModel(new DefaultComboBoxModel(Enviroment.values()));
        btnSaveConfiguration.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        initializeVariables();
        setTitles();
        addActionOnComboBoxEnviroment();
        addActionOnBtnSaveConfiguration();
        setEnviromentPanel();
        setMobileRequestPanel();
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        setLayoutHorizontal(layout);
        setLayoutVertical(layout);     
    }// </editor-fold>//GEN-END:initComponents


    private void saveConfiguration(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveConfigurationActionPerformed
        // TODO add your handling code here:
        RequestSettings.populateRequests(frejaRequestPanel.getCheckAuth().isSelected(), frejaRequestPanel.getCheckSign().isSelected(), checkOpenSecureConnection.isSelected());
        RequestSettings.populateResponses(frejaRequestPanel.getRadioApproveAuth().isSelected(), frejaRequestPanel.getRadioDeclineAuth().isSelected(), frejaRequestPanel.getRadioNoActionAuth().isSelected(),
                frejaRequestPanel.getRadioApproveSign().isSelected(), frejaRequestPanel.getRadioDeclineSign().isSelected(), frejaRequestPanel.getRadioNoActionSign().isSelected());
        JOptionPane.showMessageDialog(this, "Configuration saved. You can run the program", "Successful", JOptionPane.OK_OPTION, new javax.swing.ImageIcon(getClass().getResource("/images/check.png")));
    }//GEN-LAST:event_btnSaveConfigurationActionPerformed

    private void setEnviromentSettings(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEnviromentActionPerformed
        // TODO add your handling code here:
        Enviroment enviroment = (Enviroment) cmbEnviroment.getSelectedItem();
        EnviromentSettings.setEnviroment(enviroment);
        EnviromentSettings.populateSettings();
    }//GEN-LAST:event_cmbEnviromentActionPerformed

    private void initializeVariables() {
        btnSaveConfiguration = new javax.swing.JButton();
        panelEnviroment = new javax.swing.JPanel();
        cmbEnviroment = new javax.swing.JComboBox<>();
        panelMobileRequests = new javax.swing.JPanel();
        checkOpenSecureConnection = new javax.swing.JCheckBox();
        frejaRequestPanel = new com.internship.frejaeidjmeterplugin.jmeter.sampler.gui.panel.FrejaRequestPanel();
        emailInputPanel = new com.internship.frejaeidjmeterplugin.jmeter.sampler.gui.panel.EmailInputPanel();
    }

    private void setTitles() {
        btnSaveConfiguration.setText("Save configuration");
        checkOpenSecureConnection.setText("Open secure connection enabled");
        panelMobileRequests.setBorder(javax.swing.BorderFactory.createTitledBorder("Mobile client requests"));
        panelEnviroment.setBorder(javax.swing.BorderFactory.createTitledBorder("Enviroment"));
    }

    private void addActionOnBtnSaveConfiguration() {
        btnSaveConfiguration.addActionListener(this::saveConfiguration);
    }

    private void addActionOnComboBoxEnviroment() {
        cmbEnviroment.addActionListener(this::setEnviromentSettings);
    }

    private void setLayoutHorizontal(javax.swing.GroupLayout layout) {
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(btnSaveConfiguration, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(panelEnviroment, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(frejaRequestPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 736, Short.MAX_VALUE)
                                                .addComponent(emailInputPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(panelMobileRequests, javax.swing.GroupLayout.PREFERRED_SIZE, 736, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(31, Short.MAX_VALUE))
        );
    }

    private void setLayoutVertical(javax.swing.GroupLayout layout) {
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(panelEnviroment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(emailInputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(frejaRequestPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(panelMobileRequests, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSaveConfiguration)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    private void setEnviromentPanel() {
        javax.swing.GroupLayout panelEnviromentLayout = new javax.swing.GroupLayout(panelEnviroment);
        panelEnviroment.setLayout(panelEnviromentLayout);
        panelEnviromentLayout.setHorizontalGroup(
                panelEnviromentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelEnviromentLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(cmbEnviroment, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelEnviromentLayout.setVerticalGroup(
                panelEnviromentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelEnviromentLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(cmbEnviroment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

    }

    private void setMobileRequestPanel() {
        panelMobileRequests.setPreferredSize(new java.awt.Dimension(656, 60));
        javax.swing.GroupLayout panelMobileRequestsLayout = new javax.swing.GroupLayout(panelMobileRequests);
        panelMobileRequests.setLayout(panelMobileRequestsLayout);
        panelMobileRequestsLayout.setHorizontalGroup(
                panelMobileRequestsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelMobileRequestsLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(checkOpenSecureConnection, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(491, Short.MAX_VALUE))
        );
        panelMobileRequestsLayout.setVerticalGroup(
                panelMobileRequestsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMobileRequestsLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(checkOpenSecureConnection)
                                .addContainerGap())
        );
    }

}
