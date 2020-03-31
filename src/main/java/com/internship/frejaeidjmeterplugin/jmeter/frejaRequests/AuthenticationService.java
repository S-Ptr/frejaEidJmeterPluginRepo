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

public class AuthenticationService {

    private final AuthenticationClientApi authenticationClient;
    private static final String KEYSTORE_PATH = "src/main/resources/relyingparty_keystore.p12";
    private static final String KEYSTORE_PASSWORD = "123123123";
    private static final String SERVICE_ADRESS = "https://services-st.test.frejaeid.com";

    public AuthenticationService() throws FrejaEidClientInternalException {
        SslSettings sslSettings = SslSettings.create(KEYSTORE_PATH, KEYSTORE_PASSWORD);
        authenticationClient = AuthenticationClient.create(sslSettings, FrejaEnvironment.TEST).setTestModeCustomUrl(SERVICE_ADRESS).setTransactionContext(TransactionContext.PERSONAL).build();
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
