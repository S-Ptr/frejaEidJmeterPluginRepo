package com.internship.frejaeidjmeterplugin.jmeter.sampler;

import com.verisec.frejaeid.client.exceptions.FrejaEidClientInternalException;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jmeter.samplers.AbstractSampler;
import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;

public class FrejaEidPluginSampler extends AbstractSampler {

    private final AuthSampler authSampler;
    private final SignSampler signSampler;

    public FrejaEidPluginSampler() throws FrejaEidClientInternalException {
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
                sampleResult = authSampler.sample(getEmail());
                break;
            case "sign":
                sampleResult = signSampler.sample(getEmail());
                break;
            case "both":
                SampleResult currentSampler = authSampler.sample(getEmail());
                String responseCodeAuth = currentSampler.getResponseCode();
                sampleResult.setContentType("both");
                currentSampler = signSampler.sample(getEmail());
                String responseCodeSign = currentSampler.getResponseCode();
                sampleResult.setResponseData(getDataAsByteArray(responseCodeAuth, responseCodeSign));
                break;
            default:
                sampleResult.setSampleLabel("noAction");
                return sampleResult;
        }
        return sampleResult;
    }

    private byte[] getDataAsByteArray(String sampleLabelAuth, String sampleLabelSign) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);
        try {
            out.writeUTF(sampleLabelAuth);
            out.writeUTF(sampleLabelSign);
        } catch (IOException ex) {
            Logger.getLogger(FrejaEidPluginSampler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
                baos.close();
            } catch (IOException ex) {
                Logger.getLogger(FrejaEidPluginSampler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return baos.toByteArray();
    }
}
