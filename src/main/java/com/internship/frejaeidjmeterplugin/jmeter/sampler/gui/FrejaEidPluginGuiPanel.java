package com.internship.frejaeidjmeterplugin.jmeter.sampler.gui;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

public class FrejaEidPluginGuiPanel extends javax.swing.JPanel {

    private String filePath;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkAuth;
    private javax.swing.JCheckBox checkOpenSecureConnection;
    private javax.swing.JCheckBox checkSign;
    private javax.swing.JButton emailFileSelectButton;
    private javax.swing.ButtonGroup emailInputButtonGroup;
    private javax.swing.JTextField filePathTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblTitleEmaill;
    private javax.swing.JRadioButton listEmailChoice;
    private javax.swing.JRadioButton singleEmailChoice;
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

    public JTextField getEmailFilePath() {
        return filePathTextField;
    }

    public void setEmailFilePath(String path) {
        this.filePathTextField.setText(path);
    }

    public String getEmailInputType() {
        if (singleEmailChoice.isSelected()) {
            return "SINGLE";
        } else {
            return "LIST";
        }
    }

    public void setEmailInputType(String type) {
        if (type.equals("SINGLE")) {
            singleEmailChoice.setSelected(true);
        } else {
            listEmailChoice.setSelected(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        emailInputButtonGroup = new javax.swing.ButtonGroup();
        checkAuth = new javax.swing.JCheckBox();
        checkSign = new javax.swing.JCheckBox();
        lblTitleEmaill = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        checkOpenSecureConnection = new javax.swing.JCheckBox();
        singleEmailChoice = new javax.swing.JRadioButton();
        listEmailChoice = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        filePathTextField = new javax.swing.JTextField();
        emailFileSelectButton = new javax.swing.JButton();

        checkAuth.setText("InitAuthentication enabled");

        checkSign.setText("InitSign enabled");

        lblTitleEmaill.setText("Email input:");

        checkOpenSecureConnection.setText("Open secure connection enabled");

        emailInputButtonGroup.add(singleEmailChoice);
        singleEmailChoice.setSelected(true);
        singleEmailChoice.setText("Single User:");

        emailInputButtonGroup.add(listEmailChoice);
        listEmailChoice.setText("User List from File:");
        listEmailChoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listEmailChoiceActionPerformed(evt);
            }
        });

        jLabel1.setText("Requests:");

        emailFileSelectButton.setText("Browse");
        emailFileSelectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailFileSelectButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitleEmaill)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(listEmailChoice)
                    .addComponent(singleEmailChoice)
                    .addComponent(checkOpenSecureConnection)
                    .addComponent(checkSign)
                    .addComponent(checkAuth)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(filePathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(emailFileSelectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkAuth)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkSign)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkOpenSecureConnection)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(singleEmailChoice)
                    .addComponent(lblTitleEmaill))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(listEmailChoice)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filePathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailFileSelectButton))
                .addContainerGap(68, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void listEmailChoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listEmailChoiceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_listEmailChoiceActionPerformed

    private void emailFileSelectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailFileSelectButtonActionPerformed
        JFileChooser fileChooser = new JFileChooser();
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
        int returnVal = fileChooser.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            filePathTextField.setText(file.getAbsolutePath());
            filePath = file.getAbsolutePath();
        } else {
            JOptionPane.showMessageDialog(fileChooser, "Something went wrong. Please try again.", "File Selection Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_emailFileSelectButtonActionPerformed

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
