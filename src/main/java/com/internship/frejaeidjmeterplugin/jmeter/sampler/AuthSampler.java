package com.internship.frejaeidjmeterplugin.jmeter.sampler;

import com.internship.frejaeidjmeterplugin.jmeter.frejaRequests.AuthenticationService;
import com.verisec.frejaeid.client.beans.authentication.get.AuthenticationResult;
import com.verisec.frejaeid.client.enums.MinRegistrationLevel;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientInternalException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jmeter.samplers.SampleResult;

public class AuthSampler {

    private final AuthenticationService authService;

    public AuthSampler() throws FrejaEidClientInternalException {
        authService = new AuthenticationService();
    }

    public SampleResult sample(String email) {
        SampleResult sampleResult = new SampleResult();
        sampleResult.sampleStart();
        try {
            String reference = authService.initiateAuthenticationRequest(email, MinRegistrationLevel.BASIC);
            AuthenticationResult authResult = authService.getResult(reference);

            sampleResult.setSuccessful(true);
            sampleResult.setSampleLabel("Freja eID Response: " + authResult.getStatus().toString());
            sampleResult.setResponseCode(authResult.getStatus().toString());
            sampleResult.setResponseMessage(authResult.getStatus() + "");
            sampleResult.setContentType("auth");
            if ((authResult.getStatus().toString()).equals("DELIVERED TO MOBILE")) {
                sampleResult.setResponseOK();
            }
        } catch (Exception ex) {
            sampleResult.setSuccessful(false);
            sampleResult.setSampleLabel("FAILED");
            sampleResult.setResponseMessage(ex.getClass().getSimpleName());
            sampleResult.setContentType("auth");
            Logger.getLogger(AuthSampler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            sampleResult.sampleEnd();
            sampleResult.latencyEnd();
            return sampleResult;
        }
    }
}
