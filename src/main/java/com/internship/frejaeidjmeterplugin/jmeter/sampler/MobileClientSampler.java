package com.internship.frejaeidjmeterplugin.jmeter.sampler;

import com.internship.frejaeidjmeterplugin.jmeter.frejaRequests.MobileClientService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jmeter.samplers.SampleResult;

public class MobileClientSampler implements GenericSampler {
    private final MobileClientService mobileClientService;
    
    public MobileClientSampler() throws Exception {
        mobileClientService = new MobileClientService();
    }
    
    @Override
    public SampleResult sample(String entry) {
        SampleResult sampleResult = new SampleResult();
        sampleResult.sampleStart();
        try {
            mobileClientService.openSecureConnection();
            setSampleResult(sampleResult, "mobile", true, "Freja eID Response: DELIVERED", "DELIVERED", "The open secure connection request was delivered");
        } catch (Exception ex) {
            setSampleResult(sampleResult, "mobile", false, "Freja eID Response: FAILED", "FAILED", ex.getMessage());
            Logger.getLogger(MobileClientSampler.class.getName()).log(Level.SEVERE, null, ex);
        }
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

}
