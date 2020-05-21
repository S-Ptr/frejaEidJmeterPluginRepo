package com.internship.frejaeidjmeterplugin.jmeter.sampler.impl;

import com.internship.frejaeidjmeterplugin.jmeter.frejaRequests.AuthenticationService;
import com.internship.frejaeidjmeterplugin.jmeter.sampler.FrejaEidRequest;
import com.internship.frejaeidjmeterplugin.jmeter.sampler.GenericSampler;
import com.verisec.frejaeid.client.beans.authentication.get.AuthenticationResult;
import com.verisec.frejaeid.client.enums.MinRegistrationLevel;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientInternalException;
import com.verisec.frejaeid.mobileclient.ResponseStatus;
import com.verisec.frejaeid.mobileclient.clients.impl.MobileClient;
import org.apache.jmeter.samplers.SampleResult;

public class AuthSampler extends FrejaEidRequest implements GenericSampler {

    private final AuthenticationService authService;

    public AuthSampler() throws FrejaEidClientInternalException, Exception {
        authService = new AuthenticationService();
    }

    @Override
    public SampleResult sample(String email) {
        SampleResult sampleResult = new SampleResult();
        sampleResult.sampleStart();
        try {
            String reference = authService.initiateAuthenticationRequest(email, MinRegistrationLevel.BASIC);
            ResponseStatus status = handleRequest(getSamplerName(), reference, email);
            AuthenticationResult authResult = authService.getResult(reference);
            sampleResult.latencyEnd();
            setSampleResult(sampleResult, "auth", true, "auth",
                    authResult.getStatus().toString(), status.toString());
            sampleResult.setResponseCodeOK();
        } catch (Exception ex) {
            sampleResult.latencyEnd();
            setSampleResult(sampleResult, "auth", false, "auth", "FAILED",
                    ex.getClass().getSimpleName());
            ex.printStackTrace();
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

    @Override
    protected ResponseStatus approveRequest(String reference, MobileClient mobileClient) {
        return mobileClient.approveAuthenticationTransaction(reference);
    }
}
