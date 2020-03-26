package com.internship.frejaeidjmeterplugin.jmeter.sampler;

import com.internship.frejaeidjmeterplugin.jmeter.frejaRequests.SignService;
import com.verisec.frejaeid.client.beans.sign.get.SignResult;
import com.verisec.frejaeid.client.enums.MinRegistrationLevel;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientInternalException;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientPollingException;
import com.verisec.frejaeid.client.exceptions.FrejaEidException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jmeter.samplers.AbstractSampler;
import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;

public class SignSampler extends AbstractSampler {

    private final SignService signService;

    public SignSampler() throws FrejaEidClientInternalException {
        signService = new SignService();
    }

    public String getEmail() {
        return getPropertyAsString("email");
    }

    public void setEmail(String email) {
        setProperty("email", email);
    }

    public String getSignTitle() {
        return getPropertyAsString("signTitle");
    }

    public void setSignTitle(String signTitle) {
        setProperty("signTitle", signTitle);
    }

    public String getSignData() {
        return getPropertyAsString("signData");
    }

    public void setSignData(String email) {
        setProperty("signData", email);
    }

    @Override
    public SampleResult sample(Entry entry) {
        SampleResult sampleResult = new SampleResult();
        sampleResult.sampleStart();
        try {
            String reference = signService.initiateSignRequest(getPropertyAsString("email"), getPropertyAsString("signTitle"), getPropertyAsString("signData"), MinRegistrationLevel.BASIC);
            sampleResult.latencyEnd();
            SignResult authResult = signService.getResults(reference);
            sampleResult.setSuccessful(true);
            sampleResult.setSampleLabel("Freja eID Response: " + authResult.getStatus().toString());
            sampleResult.setResponseCode(authResult.getStatus().toString());
            sampleResult.setResponseMessage(authResult.getStatus() + "");
            if ((authResult.getStatus().toString()).equals("DELIVERED TO MOBILE")) {
                sampleResult.setResponseOK();
            }
        } catch (FrejaEidClientInternalException | FrejaEidClientPollingException | FrejaEidException ex) {
            sampleResult.latencyEnd();
            sampleResult.setSuccessful(false);
            sampleResult.setSampleLabel("Unhandled Exception");
            sampleResult.setResponseMessage(ex.getClass().getSimpleName());
            Logger.getLogger(AuthSampler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            sampleResult.sampleEnd();
            return sampleResult;
        }
    }

}
