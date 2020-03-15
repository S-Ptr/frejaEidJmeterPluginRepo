package com.internship.frejaeidjmeterplugin.jmeter.gui;
import com.internship.frejaeidjmeterplugin.jmeter.sample.AuthSample;
import javax.swing.JButton;
import javax.swing.JLabel;
import org.apache.jmeter.samplers.gui.AbstractSamplerGui;
import org.apache.jmeter.testelement.TestElement;

public class AuthSamplerGui extends AbstractSamplerGui {

    public AuthSamplerGui() {
        initComponents();
    }

    public JButton getBtnSendInitAuthenticate() {
        return btnSendInitAuthenticate;
    }

    public JLabel getLblStatus() {
        return lblStatus;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblInitAuthenticate = new javax.swing.JLabel();
        btnSendInitAuthenticate = new javax.swing.JButton();
        lblStatusTitle = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();

        lblInitAuthenticate.setText("InitAuthenticate");

        btnSendInitAuthenticate.setText("Send");

        lblStatusTitle.setText("Status:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblInitAuthenticate)
                    .addComponent(lblStatusTitle))
                .addGap(99, 99, 99)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblStatus)
                    .addComponent(btnSendInitAuthenticate, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInitAuthenticate)
                    .addComponent(btnSendInitAuthenticate))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStatusTitle)
                    .addComponent(lblStatus))
                .addContainerGap(210, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public String getLabelResource() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void configure(TestElement element) {
        super.configure(element);
    }

    @Override
    public String getStaticLabel() {
        return "Freja eID Authentication Request";
    }

    @Override
    public TestElement createTestElement() {
        AuthSample auth = new AuthSample();
        modifyTestElement(auth);
        return auth;
    }

    @Override
    public void modifyTestElement(TestElement te) {
        AuthSample auth = (AuthSample) te;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSendInitAuthenticate;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblInitAuthenticate;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblStatusTitle;
    // End of variables declaration//GEN-END:variables
}
