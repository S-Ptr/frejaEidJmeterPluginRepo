package com.internship.frejaeidjmeterplugin.jmeter.sample;

import com.internship.frejaeidjmeterplugin.jmeter.frejaRequests.AuthenticationService;
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

public class AuthSample extends AbstractSampler {

    private final AuthenticationService authService;

    public AuthSample() throws FrejaEidClientInternalException {
        authService = new AuthenticationService();
    }

    @Override
    public SampleResult sample(Entry entry) {
        SampleResult sr = new SampleResult();
        try {
            String reference = authService.initiateAuthenticationRequest("aleksandar.markovic@verisec.com", MinRegistrationLevel.BASIC);
            AuthenticationResult ar = authService.getResults(reference);
            sr.setResponseMessage(ar.getStatus() + "");
            sr.setSampleLabel("Freja eID Test");

        } catch (FrejaEidClientInternalException | FrejaEidException | FrejaEidClientPollingException ex) {
            Logger.getLogger(AuthSample.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return sr;
        }
    }
}
