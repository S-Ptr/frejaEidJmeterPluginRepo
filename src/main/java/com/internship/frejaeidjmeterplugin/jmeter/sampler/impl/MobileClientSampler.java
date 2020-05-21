package com.internship.frejaeidjmeterplugin.jmeter.sampler.impl;

import com.internship.frejaeidjmeterplugin.jmeter.frejaRequests.MobileClientService;
import com.internship.frejaeidjmeterplugin.jmeter.sampler.GenericSampler;
import com.internship.frejaeidjmeterplugin.jmeter.settings.EmailSettings;
import com.verisec.frejaeid.mobileclient.clients.impl.MobileClient;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jmeter.samplers.SampleResult;

public class MobileClientSampler implements GenericSampler {

    public MobileClientSampler() throws Exception {
    }

    @Override
    public SampleResult sample(String email) {
        MobileClientService mobileClientService = MobileClientService.getInstance();
        SampleResult sampleResult = new SampleResult();
        sampleResult.sampleStart();
        MobileClient mobileClient = getMobileClient(email);
        try {
            mobileClient.openSecureConnection();
            sampleResult.latencyEnd();
            setSampleResult(sampleResult, "mobile", true, "mobile", "DELIVERED", "The open secure connection request was delivered");
        } catch (Exception ex) {
            sampleResult.latencyEnd();
            setSampleResult(sampleResult, "mobile", false, "mobile", "FAILED", ex.getMessage());
            Logger.getLogger(MobileClientSampler.class.getName()).log(Level.SEVERE, null, ex);
        }
        mobileClient.closeConnection();
        sampleResult.sampleEnd();
        return sampleResult;
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
        return "mobile";
    }

    private MobileClient getMobileClient(String email) {
        if (EmailSettings.isSingleUser()) {
            return MobileClientService.getInstance().getSingleMobileClient();
        }
        return MobileClientService.getInstance().getMobileClientForEmail(email);
    }

}
