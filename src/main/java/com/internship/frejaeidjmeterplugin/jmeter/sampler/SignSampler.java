package com.internship.frejaeidjmeterplugin.jmeter.sampler;

import com.internship.frejaeidjmeterplugin.jmeter.frejaRequests.SignService;
import com.verisec.frejaeid.client.beans.sign.get.SignResult;
import com.verisec.frejaeid.client.enums.MinRegistrationLevel;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientInternalException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jmeter.samplers.SampleResult;

public class SignSampler {

    private final SignService signService;
    private static final String TITLE = "Transaction";
    private static final String SIGN_DATA = "Data";

    public SignSampler() throws FrejaEidClientInternalException {
        signService = new SignService();
    }

    public SampleResult sample(String email) {
        SampleResult sampleResult = new SampleResult();
        sampleResult.sampleStart();
        try {
            String reference = signService.initiateSignRequest(email, TITLE, SIGN_DATA, MinRegistrationLevel.BASIC);
            SignResult authResult = signService.getResult(reference);
            setSampleResult(sampleResult, "sign", true, "Freja eID Response: " + authResult.getStatus().toString(),
                    authResult.getStatus().toString(), "The sign request was delivered");
            sampleResult.setResponseCodeOK();
        } catch (Exception ex) {
            setSampleResult(sampleResult, "sign", false, "Freja eID Response: FAILED", "FAILED",
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

}
