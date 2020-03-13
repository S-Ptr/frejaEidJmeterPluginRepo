/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.internship.frejaeidjmeterplugin.jmeter.sample;
import com.internship.frejaeidjmeterplugin.jmeter.frejaRequests.InitAuthenticationRequest;
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

/**
 *
 * @author Ivan
 */
public class AuthSample extends AbstractSampler {

    private static AuthSample instance;
    
    private  AuthSample() {
    }

    @Override
    public SampleResult sample(Entry entry) {
        return null;
    }

    public static AuthSample getInstance() {
        if (instance == null){
            return new AuthSample();
        }
        return instance;
    }

    public AuthenticationResult sendInitAuthenticate () {  
        try {
            System.out.println("Init authentication is sent");
           return InitAuthenticationRequest.create("aleksandar.markovic@verisec.com", "src/main/resources/relyingparty_keystore.p12",
                    "123123123", "", MinRegistrationLevel.BASIC, "https://services-st.test.frejaeid.com");
        } catch (FrejaEidClientInternalException ex) {
            Logger.getLogger(AuthSample.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FrejaEidException ex) {
            Logger.getLogger(AuthSample.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FrejaEidClientPollingException ex) {
            Logger.getLogger(AuthSample.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
