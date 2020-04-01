package com.internship.frejaeidjmeterplugin.jmeter.frejaRequests;

import com.verisec.frejaeid.commons.exception.FrejaEidException;
import com.verisec.frejaeid.mobileclient.clients.api.MobileClientApi;
import com.verisec.frejaeid.mobileclient.clients.impl.MobileClient;
import com.verisec.frejaeid.mobilecommons.enums.LanguageCode;
import com.verisec.frejaeid.mobilecommons.enums.UserInfoType;

public class OpenSecureConnectionService {

    private final MobileClientApi mobileClientApi;
    private static final String WEB_SOCKET_KEYSTORE_PATH = "src/main/resources/websocket_keystore.jks";
    private static final String WEB_SOCKET_KEYSTORE_PASSWORD = "123123123";
    private static final String SERVICE_ADRESS = "wss://frejaeidmobilesvc-st.test.frejaeid.com";

    public OpenSecureConnectionService() throws FrejaEidException, Exception {
        mobileClientApi = new MobileClient(SERVICE_ADRESS, WEB_SOCKET_KEYSTORE_PATH, WEB_SOCKET_KEYSTORE_PASSWORD);
    }

    public void openSecureConnection() throws Exception {
        mobileClientApi.openSecureConnection();
        mobileClientApi.registerAccount("aleksandar.markovic@verisec.com", UserInfoType.EMAIL, LanguageCode.EN);
    }

}
