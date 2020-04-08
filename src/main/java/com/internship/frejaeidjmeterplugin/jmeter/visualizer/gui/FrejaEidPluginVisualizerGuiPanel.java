package com.internship.frejaeidjmeterplugin.jmeter.visualizer.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class FrejaEidPluginVisualizerGuiPanel extends JPanel {
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    private ResultPanel authResults;
    private ResultPanel signResults;
    private ResultPanel openSecureResults;

    public FrejaEidPluginVisualizerGuiPanel() {
        initComponents();
        setForm();
    }

    public ResultPanel getOpenSecureResults() {
        return openSecureResults;
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
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        setLayoutHorizontal(layout);
        setLayoutVertical(layout);
    }// </editor-fold>//GEN-END:initComponents

    private void setForm() {
        this.setLayout(new GridBagLayout());
        authResults = new ResultPanel();
        signResults = new ResultPanel();
        openSecureResults = new ResultPanel();
        JTabbedPane jTP = new JTabbedPane();
        jTP.add("Auth", authResults);
        jTP.add("Sign", signResults);
        jTP.add("Open secure connection", openSecureResults);
        GridBagConstraints myConstraints = new GridBagConstraints();
        myConstraints.ipadx = 50;
        myConstraints.ipady = 50;
        this.add(jTP, myConstraints);
    }

    private void setLayoutHorizontal(javax.swing.GroupLayout layout) {
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
    }

    private void setLayoutVertical(javax.swing.GroupLayout layout) {
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 320, Short.MAX_VALUE)
        );
    }
}
