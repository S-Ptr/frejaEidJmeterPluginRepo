/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.internship.frejaeidjmeterplugin.jmeter.frejaRequests;

import com.verisec.frejaeid.client.beans.authentication.get.AuthenticationResult;
import com.verisec.frejaeid.client.beans.authentication.get.AuthenticationResultRequest;
import com.verisec.frejaeid.client.beans.authentication.init.InitiateAuthenticationRequest;
import com.verisec.frejaeid.client.beans.general.SslSettings;
import com.verisec.frejaeid.client.client.api.AuthenticationClientApi;
import com.verisec.frejaeid.client.client.impl.AuthenticationClient;
import com.verisec.frejaeid.client.enums.FrejaEnvironment;
import com.verisec.frejaeid.client.enums.MinRegistrationLevel;
import com.verisec.frejaeid.client.enums.TransactionContext;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientInternalException;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientPollingException;
import com.verisec.frejaeid.client.exceptions.FrejaEidException;

/**
 *
 * @author Ivan
 */
public class InitAuthenticationRequest {

    public static AuthenticationResult create(String email, String keyStorePath, String keyStorePassword, String relyingParty, MinRegistrationLevel registrationLevel, String serviceAdress) throws FrejaEidClientInternalException, FrejaEidException, FrejaEidClientPollingException {
        SslSettings sslSettings = SslSettings.create(keyStorePath, keyStorePassword);
        //keyStorePath = src/main/resources/relyingparty_keystore.p12
        //keyStorePassword = 123123123
        // email = "aleksandar.markovic@verisec.com"
        // registrationLevel = MinRegistrationLevel.EXTENDED
        AuthenticationClientApi authenticationClient = AuthenticationClient.create(sslSettings, FrejaEnvironment.TEST).setTestModeCustomUrl(serviceAdress).setTransactionContext(TransactionContext.PERSONAL).build();

        InitiateAuthenticationRequest request = null;

        if (relyingParty.equals("")) {
            request = InitiateAuthenticationRequest.createCustom()
                    .setEmail(email)
                    .setMinRegistrationLevel(registrationLevel)
                    .build();
        } else {
            request = InitiateAuthenticationRequest.createCustom()
                    .setEmail(email)
                    .setMinRegistrationLevel(registrationLevel)
                    .setRelyingPartyId(relyingParty)
                    .build();
        }
        String reference = authenticationClient.initiate(request);
        int maxWaitingTimeInSeconds = 30;
        AuthenticationResult result = null;
        if (relyingParty.equals("")) {
            result = authenticationClient.pollForResult(AuthenticationResultRequest.create(reference), maxWaitingTimeInSeconds);
        } else {
            result = authenticationClient.pollForResult(AuthenticationResultRequest.create(reference), maxWaitingTimeInSeconds);
        }
        return result;
    }
}
