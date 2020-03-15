package com.internship.frejaeidjmeterplugin.jmeter.sample;
import com.internship.frejaeidjmeterplugin.jmeter.frejaRequests.AuthenticationService;
import com.verisec.frejaeid.client.beans.authentication.get.AuthenticationResult;
import com.verisec.frejaeid.client.enums.MinRegistrationLevel;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientInternalException;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientPollingException;
import com.verisec.frejaeid.client.exceptions.FrejaEidException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jmeter.samplers.AbstractSampler;
import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;

public class AuthSample extends AbstractSampler {

    private AuthenticationService initRequest;

    public AuthSample() {

    }

    @Override
    public SampleResult sample(Entry entry) {
         SampleResult sr = new SampleResult();
        try {
            sendInitAuthenticate("aleksandar.markovic@verisec.com", MinRegistrationLevel.BASIC, "src/main/resources/relyingparty_keystore.p12", "123123123", "https://services-st.test.frejaeid.com");
            AuthenticationResult ar = getResultsInitAuthenticate();
            sr.setResponseMessage(ar.getStatus()+"");
            sr.setSampleLabel("Freja eID Test");
            
        } catch (FrejaEidClientInternalException | FrejaEidException | FrejaEidClientPollingException ex) {
            Logger.getLogger(AuthSample.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            return sr;
        }
    }

    public void sendInitAuthenticate(String email, MinRegistrationLevel registrationLevel, String keystorePath, String keystorePassword, String serviceAdress) throws FrejaEidClientInternalException, FrejaEidException, FrejaEidClientPollingException {
        initRequest = AuthenticationService.create(keystorePath, keystorePassword, serviceAdress);
        initRequest.sendRequest(email, registrationLevel);
    }

    public AuthenticationResult getResultsInitAuthenticate() throws FrejaEidException, FrejaEidClientPollingException, FrejaEidClientInternalException {
        return initRequest.getResults();
    }

}
