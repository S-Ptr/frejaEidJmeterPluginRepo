package com.internship.frejaeidjmeterplugin.jmeter.sampler;

import com.internship.frejaeidjmeterplugin.jmeter.frejaRequests.AuthenticationService;
import com.internship.frejaeidjmeterplugin.jmeter.frejaRequests.SignService;
import com.verisec.frejaeid.client.beans.authentication.get.AuthenticationResult;
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

public class GeneralSampler extends AbstractSampler {

    private final AuthenticationService authService;
    private final SignService signService;

    public GeneralSampler() throws FrejaEidClientInternalException {
        authService = new AuthenticationService();
        signService = new SignService();
    }

    public String getEmail() {
        return getPropertyAsString("email");
    }

    public void setEmail(String email) {
        setProperty("email", email);
    }

    public void setSelected(String text) {
        setProperty("selected", text);
    }

    public String getSelected() {
        return getPropertyAsString("selected");
    }

    @Override
    public SampleResult sample(Entry entry) {
        SampleResult sampleResult = new SampleResult();

        try {
            switch (getSelected()) {
                case "auth":
                    initAuthenticate(sampleResult, getPropertyAsString("email"));
                    break;
                case "sign":
                    initSign(sampleResult, getPropertyAsString("email"), "Transaction", "This is transaction", MinRegistrationLevel.BASIC);
                    break;
                default:
                    return sampleResult;
            }
        } catch (Exception ex) {
            sampleResult.latencyEnd();
            sampleResult.setSuccessful(false);
            sampleResult.setSampleLabel("Unhandled Exception");
            sampleResult.setResponseMessage(ex.getClass().getSimpleName());
            Logger.getLogger(AuthSampler.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            sampleResult.sampleEnd();
            sampleResult.latencyEnd();
            return sampleResult;
        }
    }

    private void setSampleResult(SampleResult sampleResult, String label, boolean successful, String responseMessage, String responseCode) {
        sampleResult.setSuccessful(successful);
        sampleResult.setSampleLabel(label);
        sampleResult.setResponseCode(responseCode);
        sampleResult.setResponseMessage(responseMessage);
    }

    private void initAuthenticate(SampleResult sampleResult, String email) throws FrejaEidClientInternalException, FrejaEidException, FrejaEidClientPollingException {
        sampleResult.setTimeStamp(System.currentTimeMillis() / 1000L);
        String reference = authService.initiateAuthenticationRequest(email, MinRegistrationLevel.BASIC);
        AuthenticationResult ar = authService.getResults(reference);
        setSampleResult(sampleResult, "Freja eID Response: " + ar.getStatus(), true, ar.getStatus().toString(), ar.getStatus().toString());
        sampleResult.setResponseOK();
    }

    private void initSign(SampleResult sampleResult, String email, String title, String dataToSignText, MinRegistrationLevel registrationLevel) throws FrejaEidClientInternalException, FrejaEidException, FrejaEidClientPollingException {
        String reference = signService.initiateSignRequest(email, title, dataToSignText, registrationLevel);
        SignResult result = signService.getResults(reference);
        setSampleResult(sampleResult, "Freja eID Response: " + result.getStatus().toString(), true, result.getStatus() + "", result.getStatus().toString());
        sampleResult.setResponseOK();
    }

}
