/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.internship.frejaeidjmeterplugin.jmeter.sampler.gui.action;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author PC
 */
public class GeneralSamplerGuiAction {

    private final JTextField txtMail;
    private final JCheckBox checkAuth;
    private final JCheckBox checkSign;
    private final JPanel panel;
    private final JLabel lblError;

    private boolean authTxtVisible;

    public GeneralSamplerGuiAction(JPanel panel, JTextField txtMail, JCheckBox checkAuth, JCheckBox checkSign, JLabel lblError) {
        this.txtMail = txtMail;
        this.checkAuth = checkAuth;
        this.panel = panel;
        this.lblError = lblError;
        this.checkSign = checkSign;
        authTxtVisible = false;
        setActonOnCheckAuth();
    }

    private void setActonOnCheckAuth() {
        checkAuth.addActionListener(
                (e) -> {
                    authTxtVisible = !authTxtVisible;
                    txtMail.setVisible(authTxtVisible);
                    panel.setVisible(false);
                    panel.setVisible(true);
                }
        );
    }

}
