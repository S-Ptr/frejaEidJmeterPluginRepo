package com.internship.frejaeidjmeterplugin.jmeter.visualizer.gui;

public class ResultPanel extends javax.swing.JPanel {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRestartValues;
    private javax.swing.JLabel lblDelivered;
    private javax.swing.JLabel lblFailed;
    private javax.swing.JLabel lblTitleDelivered;
    private javax.swing.JLabel lblTitleFailed;
    // End of variables declaration//GEN-END:variables

    private int delivered;
    private int failed;

    public ResultPanel() {
        initComponents();
        delivered = 0;
        failed = 0;
    }

    public void increaseDelivered() {
        delivered += 1;
        lblDelivered.setText(delivered + "");
    }

    public void increasetFailed() {
        failed += 1;
        lblFailed.setText(failed + "");
    }

    public void clearGui() {
        delivered = 0;
        failed = 0;
        lblDelivered.setText("");
        lblFailed.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        initializeVariables();
        setTitles();
        restartValuesActionListener();
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        setLayoutHorizontal(layout);
        setLayoutVertical(layout);
    }// </editor-fold>//GEN-END:initComponents

    private void initializeVariables() {
        lblTitleDelivered = new javax.swing.JLabel();
        lblDelivered = new javax.swing.JLabel();
        lblTitleFailed = new javax.swing.JLabel();
        lblFailed = new javax.swing.JLabel();
        btnRestartValues = new javax.swing.JButton();
    }

    private void setTitles() {
        lblTitleDelivered.setText("Delivered:");
        lblDelivered.setText(" ");
        lblTitleFailed.setText("Failed:");
        btnRestartValues.setText("Restart values");
    }

    private void restartValuesActionListener() {
        btnRestartValues.addActionListener(this::restartValues);
    }

    private void restartValues(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestartValuesActionPerformed
        delivered = 0;
        failed = 0;
        lblDelivered.setText("");
        lblFailed.setText("");
    }//GEN-LAST:event_btnRestartValuesActionPerformed

    private void setLayoutVertical(javax.swing.GroupLayout layout) {
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitleDelivered)
                    .addComponent(lblDelivered))
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitleFailed)
                    .addComponent(lblFailed))
                .addGap(71, 71, 71)
                .addComponent(btnRestartValues)
                .addContainerGap(66, Short.MAX_VALUE))
        );
    }

    private void setLayoutHorizontal(javax.swing.GroupLayout layout) {
         layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitleDelivered)
                        .addGap(93, 93, 93)
                        .addComponent(lblDelivered))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitleFailed, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93)
                        .addComponent(lblFailed))
                    .addComponent(btnRestartValues, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(182, Short.MAX_VALUE))
        );
    }
}
