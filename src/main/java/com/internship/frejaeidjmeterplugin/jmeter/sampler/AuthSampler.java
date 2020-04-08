package com.internship.frejaeidjmeterplugin.jmeter.sampler;

import com.internship.frejaeidjmeterplugin.jmeter.frejaRequests.AuthenticationService;
import com.verisec.frejaeid.client.beans.authentication.get.AuthenticationResult;
import com.verisec.frejaeid.client.enums.MinRegistrationLevel;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientInternalException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jmeter.samplers.SampleResult;

public class AuthSampler implements GenericSampler {

    private final AuthenticationService authService;

    public AuthSampler() throws FrejaEidClientInternalException {
        authService = new AuthenticationService();
    }

    @Override
    public SampleResult sample(String email) {
        SampleResult sampleResult = new SampleResult();
        sampleResult.sampleStart();
        try {
            String reference = authService.initiateAuthenticationRequest(email, MinRegistrationLevel.BASIC);
            AuthenticationResult authResult = authService.getResult(reference);
            setSampleResult(sampleResult, "auth", true, "Freja eID Response: " + authResult.getStatus().toString(),
                    authResult.getStatus().toString(), "The auth request was delivered");
            sampleResult.setResponseCodeOK();
        } catch (Exception ex) {
            setSampleResult(sampleResult, "auth", false, "Freja eID Response: FAILED", "FAILED",
                    ex.getClass().getSimpleName());
            Logger.getLogger(AuthSampler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            sampleResult.sampleEnd();
            return sampleResult;
        }
    }

    private void setSampleResult(SampleResult sampleResult, String contentType, boolean isSuccessful, String sampleLabel, String responseCode, String responseMessage) {
        sampleResult.setSuccessful(isSuccessful);
        sampleResult.setSampleLabel(sampleLabel);
        sampleResult.setResponseCode(responseCode);
        sampleResult.setResponseMessage(responseMessage);
        sampleResult.setContentType(contentType);
    }

    @Override
    public String getSamplerName() {
        return "auth";
    }
}
