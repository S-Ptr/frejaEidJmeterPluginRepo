/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.internship.frejaeidjmeterplugin.jmeter.gui.action;

import com.internship.frejaeidjmeterplugin.jmeter.frejaRequests.FrejaRequestType;
import com.internship.frejaeidjmeterplugin.jmeter.gui.GeneralSamplerGui;
import static com.internship.frejaeidjmeterplugin.jmeter.gui.GeneralSamplerGui.authMail;
import static com.internship.frejaeidjmeterplugin.jmeter.gui.GeneralSamplerGui.requestType;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
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
    private final JButton btnOk;

    private boolean authTxtVisible;

    public GeneralSamplerGuiAction(JPanel panel, JTextField txtMail, JCheckBox checkAuth, JCheckBox checkSign, JLabel lblError, JButton btnOk) {
        this.txtMail = txtMail;
        this.checkAuth = checkAuth;
        this.panel = panel;
        this.lblError = lblError;
        this.checkSign = checkSign;
        this.btnOk = btnOk;
        authTxtVisible = false;
        setActonOnCheckAuth();
        setActionOnTxtMail();
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

    private void setActionOnTxtMail() {
        btnOk.addActionListener(
                (ActionEvent e) -> {
                    lblError.setText("");
                    if (!checkAuth.isSelected() && !checkSign.isSelected()) {
                        lblError.setText("Select one of the options");
                        return;
                    }
                    if (checkAuth.isSelected()) {
                        GeneralSamplerGui.requestType = FrejaRequestType.INIT_AUTHENTICATION;
                        GeneralSamplerGui.authMail = txtMail.getText();
                        txtMail.setText("");
                    } else {
                        requestType = FrejaRequestType.INIT_SIGN;
                    }
                }
        );
    }
}
