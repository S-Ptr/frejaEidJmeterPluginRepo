package com.internship.frejaeidjmeterplugin.jmeter.frejaRequests;

import com.internship.frejaeidjmeterplugin.jmeter.settings.EnviromentSettings;
import com.verisec.frejaeid.client.beans.authentication.get.AuthenticationResult;
import com.verisec.frejaeid.client.beans.authentication.get.AuthenticationResultRequest;
import com.verisec.frejaeid.client.beans.authentication.init.InitiateAuthenticationRequest;
import com.verisec.frejaeid.client.client.api.AuthenticationClientApi;
import com.verisec.frejaeid.client.client.impl.AuthenticationClient;
import com.verisec.frejaeid.client.enums.MinRegistrationLevel;
import com.verisec.frejaeid.client.enums.TransactionContext;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientInternalException;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientPollingException;
import com.verisec.frejaeid.client.exceptions.FrejaEidException;

public final class AuthenticationService {

    private final AuthenticationClientApi authenticationClient;

    public AuthenticationService() throws FrejaEidClientInternalException, FrejaEidException {
        authenticationClient = AuthenticationClient.create(EnviromentSettings.getSsllSettings(), EnviromentSettings.getFrejaEnvironment()).setTestModeCustomUrl(EnviromentSettings.getServiceAdress(ServiceType.AUTHENTICATION)).setTransactionContext(TransactionContext.PERSONAL).build();
    }

    public String initiateAuthenticationRequest(String email, MinRegistrationLevel registrationLevel) throws FrejaEidClientInternalException, FrejaEidException, FrejaEidClientPollingException {
        InitiateAuthenticationRequest request = InitiateAuthenticationRequest.createCustom()
                .setEmail(email)
                .setMinRegistrationLevel(registrationLevel)
                .build();
        return authenticationClient.initiate(request);
    }

    public AuthenticationResult getResult(String reference) throws FrejaEidClientInternalException, FrejaEidException {
        return authenticationClient.getResult(AuthenticationResultRequest.create(reference));
    }

}
