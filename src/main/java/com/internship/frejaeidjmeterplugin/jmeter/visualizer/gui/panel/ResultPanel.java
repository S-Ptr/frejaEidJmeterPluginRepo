package com.internship.frejaeidjmeterplugin.jmeter.visualizer.gui.panel;

import java.util.concurrent.atomic.AtomicInteger;

public class ResultPanel extends javax.swing.JPanel implements GenericPanel {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblDelivered;
    private javax.swing.JLabel lblError;
    private javax.swing.JLabel lblFailed;
    private javax.swing.JLabel lblTitleDelivered;
    private javax.swing.JLabel lblTitleFailed;
    // End of variables declaration//GEN-END:variables

    private AtomicInteger delivered; 
    private AtomicInteger failed; 

    public ResultPanel() {
        initComponents();
        delivered = new AtomicInteger();
        failed = new AtomicInteger();
    }

    public void setError(String error) {
        this.lblError.setText(error);
    }

    @Override
    public void increaseDelivered() {
        delivered.addAndGet(1);
        lblDelivered.setText(delivered + "");
    }

    @Override
    public void increaseFailed() {
        failed.addAndGet(1);
        lblFailed.setText(failed + "");
    }

    @Override
    public void clear() {
        delivered = new AtomicInteger();
        failed = new AtomicInteger();
        lblDelivered.setText("");
        lblFailed.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitleDelivered = new javax.swing.JLabel();
        lblDelivered = new javax.swing.JLabel();
        lblTitleFailed = new javax.swing.JLabel();
        lblFailed = new javax.swing.JLabel();
        lblError = new javax.swing.JLabel();

        lblTitleDelivered.setText("Delivered:");

        lblDelivered.setText(" ");

        lblTitleFailed.setText("Failed:");

        lblError.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
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
                    .addComponent(lblError, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );
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
                .addGap(26, 26, 26)
                .addComponent(lblError)
                .addContainerGap(134, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void initializeVariables() {
        lblTitleDelivered = new javax.swing.JLabel();
        lblDelivered = new javax.swing.JLabel();
        lblTitleFailed = new javax.swing.JLabel();
        lblFailed = new javax.swing.JLabel();
        lblError = new javax.swing.JLabel();
    }

    private void setTitles() {
        lblTitleDelivered.setText("Delivered:");
        lblDelivered.setText("");
        lblTitleFailed.setText("Failed:");
        lblError.setText("");
        lblError.setForeground(new java.awt.Color(255, 51, 51));
    }

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
                                .addGap(26, 26, 26)
                                .addComponent(lblError)
                                .addGap(31, 31, 31)
                                .addContainerGap(80, Short.MAX_VALUE))
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
                                        .addComponent(lblError, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(35, Short.MAX_VALUE))
        );
    }
}
