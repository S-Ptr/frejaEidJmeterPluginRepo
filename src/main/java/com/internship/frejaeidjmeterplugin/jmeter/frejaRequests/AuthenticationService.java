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
    private InitiateAuthenticationRequest request;
    private String reference;
    private static String keystorePath;
    private static String keystorePassword;
    private static String serviceAdress;
    

    private AuthenticationService() throws FrejaEidClientInternalException {
            SslSettings sslSettings = SslSettings.create(keystorePath, keystorePassword);
            authenticationClient = AuthenticationClient.create(sslSettings, FrejaEnvironment.TEST).setTestModeCustomUrl(serviceAdress).setTransactionContext(TransactionContext.PERSONAL).build();
    }

    public static AuthenticationService create (String keyPath, String keyPassword, String serviceAdr) throws FrejaEidClientInternalException{
        keystorePath = keyPath;
        keystorePassword = keyPassword;
        serviceAdress = serviceAdr;
        return new AuthenticationService();
    }
    
    public void sendRequest(String email, MinRegistrationLevel registrationLevel) throws FrejaEidClientInternalException, FrejaEidException, FrejaEidClientPollingException {
        request = InitiateAuthenticationRequest.createCustom()
                .setEmail(email)
                .setMinRegistrationLevel(registrationLevel)
                .build();
        reference = authenticationClient.initiate(request);
    }

    public AuthenticationResult getResults() throws FrejaEidClientInternalException, FrejaEidException, FrejaEidClientPollingException {
        int maxWaitingTimeInSeconds = 30;
        AuthenticationResult result = authenticationClient.pollForResult(AuthenticationResultRequest.create(reference), maxWaitingTimeInSeconds);
        return result;
    }

    public static String getKeystorePassword() {
        return keystorePassword;
    }

    public static String getKeystorePath() {
        return keystorePath;
    }

    public static String getServiceAdress() {
        return serviceAdress;
    }
}
