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

    private final AuthSampler authSampler;
    private final SignSampler signSampler;

    public GeneralSampler() throws FrejaEidClientInternalException {
        authSampler = new AuthSampler();
        signSampler = new SignSampler();
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

        switch (getSelected()) {
            case "auth":
                sampleResult = authSampler.sample(new Entry());
                break;
            case "sign":
                sampleResult = signSampler.sample(new Entry());
                break;
            default:
                return sampleResult;

        }
        return sampleResult;
    }
}
