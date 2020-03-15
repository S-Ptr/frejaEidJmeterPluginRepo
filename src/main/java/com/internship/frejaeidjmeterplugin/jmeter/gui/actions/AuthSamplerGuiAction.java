package com.internship.frejaeidjmeterplugin.jmeter.gui.actions;

import com.internship.frejaeidjmeterplugin.jmeter.gui.AuthSamplerGui;
import com.internship.frejaeidjmeterplugin.jmeter.sample.AuthSample;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientInternalException;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientPollingException;
import com.verisec.frejaeid.client.exceptions.FrejaEidException;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jmeter.samplers.Entry;

public class AuthSamplerGuiAction {

    private final AuthSamplerGui authSamplerGui;
    private final AuthSample authSample;

    public AuthSamplerGuiAction(AuthSample authSample, AuthSamplerGui authSamplerGui) {
        this.authSample = authSample;
        this.authSamplerGui = authSamplerGui;
        setActionOnBtnSendInitAuthenticate();
    }

    private void setActionOnBtnSendInitAuthenticate() {
        authSamplerGui.getBtnSendInitAuthenticate().addActionListener(
                (event) -> {
                    authSample.sample(new Entry());
                    String status = "";
                    try {
                        status = authSample.getResultsInitAuthenticate().getStatus() + "";
                    } catch (FrejaEidException | FrejaEidClientPollingException | FrejaEidClientInternalException ex) {
                        Logger.getLogger(AuthSamplerGuiAction.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    authSamplerGui.getLblStatus().setText(status);
                    if (status.equals("CANCELED")) {
                        authSamplerGui.getLblStatus().setForeground(Color.red);
                    } else if (status.equals("APPROVED")) {
                        authSamplerGui.getLblStatus().setForeground(Color.green);
                    }
                }
        );
    }

}
