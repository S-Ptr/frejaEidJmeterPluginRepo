
package com.internship.frejaeidjmeterplugin.jmeter.visualizer.gui;

public class ResultPanel extends javax.swing.JPanel {
    private int delivered;
    private int failed;
    
    public ResultPanel() {
        initComponents();
        delivered = 0;
        failed = 0;
    }
    
   public void setError (String error){
       lblError.setText(error);
   } 
    
   public void increaseDelivered (){
        delivered+=1;
        lblDelivered.setText(delivered+"");
    }
   
    public void increasetFailed (){
        failed+=1;
        lblFailed.setText(failed+"");
    }

    public void clearGui (){
        delivered = 0;
        failed = 0;
        lblDelivered.setText("");
        lblFailed.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblDelivered = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblFailed = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lblError = new javax.swing.JLabel();

        jLabel1.setText("Delivered:");

        lblDelivered.setText(" ");

        jLabel3.setText("Failed:");

        jButton1.setText("Restart values");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblError.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblError.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblError)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(93, 93, 93)
                        .addComponent(lblDelivered))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93)
                        .addComponent(lblFailed))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(182, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblDelivered))
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblFailed))
                .addGap(39, 39, 39)
                .addComponent(lblError)
                .addGap(32, 32, 32)
                .addComponent(jButton1)
                .addContainerGap(66, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        delivered = 0;
        failed = 0;
        lblDelivered.setText("");
        lblFailed.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblDelivered;
    private javax.swing.JLabel lblError;
    private javax.swing.JLabel lblFailed;
    // End of variables declaration//GEN-END:variables
}
