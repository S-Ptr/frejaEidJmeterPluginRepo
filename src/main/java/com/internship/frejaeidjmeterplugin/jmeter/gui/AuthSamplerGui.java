package com.internship.frejaeidjmeterplugin.jmeter.gui;

import com.internship.frejaeidjmeterplugin.jmeter.sample.AuthSample;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientInternalException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jmeter.samplers.gui.AbstractSamplerGui;
import org.apache.jmeter.testelement.TestElement;

public class AuthSamplerGui extends AbstractSamplerGui {

    public AuthSamplerGui() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBorder(javax.swing.BorderFactory.createTitledBorder("Authentication service"));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 388, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 272, Short.MAX_VALUE)
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
        AuthSample auth = null;
        try {
            auth = new AuthSample();
        } catch (FrejaEidClientInternalException ex) {
            Logger.getLogger(AuthSamplerGui.class.getName()).log(Level.SEVERE, null, ex);
        }
        modifyTestElement(auth);
        return auth;
    }

    @Override
    public void modifyTestElement(TestElement te) {
        AuthSample auth = (AuthSample) te;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
