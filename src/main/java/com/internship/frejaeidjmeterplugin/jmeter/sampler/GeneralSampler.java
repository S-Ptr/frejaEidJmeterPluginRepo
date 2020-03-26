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
        SampleResult sr = new SampleResult();

        try {
            if (getSelected().equals("auth")) {
                initAuthenticate(sr, getPropertyAsString("email"));
            } else if (getSelected().equals("sign")) {
                initSign(sr, getPropertyAsString("email"), "Transaction", "This is transaction", MinRegistrationLevel.EXTENDED);
            } else {
                return sr;
            }
        } catch (FrejaEidClientInternalException ex) {
            Logger.getLogger(AuthSampler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FrejaEidException ex) {
            setSampleResult(sr, "Freja eID Auth Request Failed", false, "FAILED", ex.getClass().getSimpleName());
            Logger.getLogger(AuthSampler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FrejaEidClientPollingException ex) {
            setSampleResult(sr, "Freja eID Auth Request Delivered", true, "DELIVERED", "");
            Logger.getLogger(AuthSampler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sr;
    }

    private void setSampleResult(SampleResult sr, String label, boolean successful, String responseMessage, String responseCode) {
        sr.setSuccessful(successful);
        sr.setSampleLabel(label);
        sr.setResponseCode(responseCode);
        sr.setResponseMessage(responseMessage);
    }

    private void initAuthenticate(SampleResult sr, String email) throws FrejaEidClientInternalException, FrejaEidException, FrejaEidClientPollingException {
        sr.setTimeStamp(System.currentTimeMillis() / 1000L);
        String reference = authService.initiateAuthenticationRequest(email, MinRegistrationLevel.BASIC);
        AuthenticationResult ar = authService.getResults(reference);
        setSampleResult(sr, "Freja eID Response: " + ar.getStatus(), true, ar.getStatus().toString(), ar.getStatus().toString());
        sr.setResponseOK();
    }

    private void initSign(SampleResult sr, String email, String title, String dataToSignText, MinRegistrationLevel registrationLevel) throws FrejaEidClientInternalException, FrejaEidException, FrejaEidClientPollingException {
        String reference = signService.initiateSignRequest(email, title, dataToSignText, registrationLevel);
        SignResult result = signService.getResults(reference);
        setSampleResult(sr, "Freja eID Response: " + result.getStatus().toString(), true, result.getStatus() + "", result.getStatus().toString());
        sr.latencyEnd();
        sr.setResponseOK();
    }

}
