/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.internship.frejaeidjmeterplugin.jmeter.sample;

import com.internship.frejaeidjmeterplugin.jmeter.frejaRequests.AuthenticationService;
import com.internship.frejaeidjmeterplugin.jmeter.frejaRequests.FrejaRequestType;
import com.internship.frejaeidjmeterplugin.jmeter.gui.GeneralSamplerGui;
import com.verisec.frejaeid.client.beans.authentication.get.AuthenticationResult;
import com.verisec.frejaeid.client.enums.MinRegistrationLevel;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientInternalException;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientPollingException;
import com.verisec.frejaeid.client.exceptions.FrejaEidException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jmeter.samplers.AbstractSampler;
import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;

/**
 *
 * @author PC
 */
public class GeneralSampler extends AbstractSampler {

    private final AuthenticationService authService;

    public GeneralSampler() throws FrejaEidClientInternalException {
        authService = new AuthenticationService();
    }

    @Override
    public SampleResult sample(Entry entry) {
        SampleResult sr = new SampleResult();

        try {
            if (GeneralSamplerGui.requestType == FrejaRequestType.INIT_AUTHENTICATION) {
                initAuthenticate(sr, GeneralSamplerGui.authMail);
            }
        } catch (FrejaEidClientInternalException ex) {
            Logger.getLogger(AuthSample.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FrejaEidException ex) {
            setSampleResult(sr, "Freja eID Auth Request Failed", false, ex.getClass().getSimpleName(), "");
            Logger.getLogger(AuthSample.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FrejaEidClientPollingException ex) {
            setSampleResult(sr, "Freja eID Auth Request Delivered", true, "", "");
            Logger.getLogger(AuthSample.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sr;
    }

    private void setSampleResult(SampleResult sr, String label, boolean successful, String responseMessage, String responseCode) {
        sr.setSuccessful(successful);
        sr.setSampleLabel(label);
        sr.setResponseCode(responseCode);
        sr.setResponseMessage(responseMessage);
    }

    private void initAuthenticate(SampleResult sr, String email) throws FrejaEidClientInternalException, FrejaEidException, FrejaEidClientPollingException {
        sr.setTimeStamp(System.currentTimeMillis() / 1000L);
        String reference = authService.initiateAuthenticationRequest(email, MinRegistrationLevel.BASIC);
        AuthenticationResult ar = authService.getResults(reference);
        setSampleResult(sr, "Freja eID Response: " + ar.getStatus(), true, ar.getStatus().toString(), ar.getStatus().toString());
        sr.setResponseOK();
    }
}
