package com.internship.frejaeidjmeterplugin.jmeter.frejaRequests;

import com.verisec.frejaeid.client.beans.general.SslSettings;
import com.verisec.frejaeid.client.beans.sign.get.SignResult;
import com.verisec.frejaeid.client.beans.sign.get.SignResultRequest;
import com.verisec.frejaeid.client.beans.sign.init.DataToSign;
import com.verisec.frejaeid.client.beans.sign.init.InitiateSignRequest;
import com.verisec.frejaeid.client.client.api.SignClientApi;
import com.verisec.frejaeid.client.client.impl.SignClient;
import com.verisec.frejaeid.client.enums.FrejaEnvironment;
import com.verisec.frejaeid.client.enums.MinRegistrationLevel;
import com.verisec.frejaeid.client.enums.TransactionContext;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientInternalException;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientPollingException;
import com.verisec.frejaeid.client.exceptions.FrejaEidException;


public class SignService {
    private final SignClientApi signClient;
    private static final String KEYSTORE_PATH = "src/main/resources/relyingparty_keystore.p12";
    private static final String KEYSTORE_PASSWORD = "123123123";
    private static final String SERVICE_ADRESS = "https://services-st.test.frejaeid.com";

    public SignService() throws FrejaEidClientInternalException {
        SslSettings sslSettings = SslSettings.create(KEYSTORE_PATH, KEYSTORE_PASSWORD);
        signClient = SignClient.create(sslSettings, FrejaEnvironment.TEST).setTestModeCustomUrl(SERVICE_ADRESS).setTransactionContext(TransactionContext.PERSONAL).build();
    }

    public String initiateSignRequest(String email, String title, String dataToSignText, MinRegistrationLevel registrationLevel) throws FrejaEidClientInternalException, FrejaEidException, FrejaEidClientPollingException {
        DataToSign dataToSign = DataToSign.create(dataToSignText);
        InitiateSignRequest request = InitiateSignRequest.createCustom()
                .setEmail(email)
                .setDataToSign(dataToSign)
                .setTitle(title)
                .setMinRegistrationLevel(registrationLevel)
                .build();
        return signClient.initiate(request);
    }


    public SignResult getResult(String reference) throws FrejaEidClientInternalException, FrejaEidException {
        SignResult result = signClient.getResult(SignResultRequest.create(reference));
        return result;
    }
}
