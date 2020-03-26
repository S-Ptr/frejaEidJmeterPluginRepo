package com.internship.frejaeidjmeterplugin.jmeter.sampler;

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

public class AuthSampler extends AbstractSampler {

    private final AuthenticationService authService;

    public String getEmail() {
        return getPropertyAsString("email");
    }

    public void setEmail(String email) {
        setProperty("email", email);
    }

    public AuthSampler() throws FrejaEidClientInternalException {
        authService = new AuthenticationService();
    }

    @Override
    public SampleResult sample(Entry entry) {
        SampleResult sampleResult = new SampleResult();
        sampleResult.sampleStart();
        try {
            String reference = authService.initiateAuthenticationRequest(getPropertyAsString("email"), MinRegistrationLevel.BASIC);
            AuthenticationResult authResult = authService.getResults(reference);
            sampleResult.setSuccessful(true);
            sampleResult.setSampleLabel("Freja eID Response: " + authResult.getStatus().toString());
            sampleResult.setResponseCode(authResult.getStatus().toString());
            sampleResult.setResponseMessage(authResult.getStatus() + "");
            if ((authResult.getStatus().toString()).equals("DELIVERED TO MOBILE")) {
                sampleResult.setResponseOK();
            }
        } catch (Exception ex) {
            sampleResult.setSuccessful(false);
            sampleResult.setSampleLabel("Unhandled Exception");
            sampleResult.setResponseMessage(ex.getClass().getSimpleName());
            Logger.getLogger(AuthSampler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            sampleResult.sampleEnd();
            sampleResult.latencyEnd();
            return sampleResult;
        }
    }
}
