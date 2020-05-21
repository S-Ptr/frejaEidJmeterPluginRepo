package com.internship.frejaeidjmeterplugin.jmeter.frejaRequests;

import com.internship.frejaeidjmeterplugin.jmeter.email.impl.ConfirmEmailService;
import com.internship.frejaeidjmeterplugin.jmeter.email.ConfirmEmailServiceApi;
import com.internship.frejaeidjmeterplugin.jmeter.settings.EnviromentSettings;
import com.verisec.frejaeid.mobilecommons.enums.UserInfoType;
import com.verisec.frejaeid.mobilecommons.enums.LanguageCode;
import com.verisec.frejaeid.commons.exception.FrejaEidException;
import com.verisec.frejaeid.commons.service.json.JsonServiceFactory;
import com.verisec.frejaeid.mobileclient.ResponseStatus;
import com.verisec.frejaeid.mobileclient.clients.api.MobileClientApi;
import com.verisec.frejaeid.mobileclient.clients.impl.MobileClient;
import com.verisec.frejaeid.mobilecommons.beans.registration.account.RegisterAccountEventResponseBean;
import com.verisec.frejaeid.service.commons.service.json.JsonService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class MobileClientService {

    private static MobileClientService instance;
    private HashMap<String, MobileClient> mobileClients;
    private MobileClient singleMobileClient;
    private final ConfirmEmailServiceApi confirmEmailService;
    private static final JsonService jsonService;

    static {
        jsonService = new JsonService();
        JsonServiceFactory.registerJsonServiceProvider(jsonService);
    }

    public MobileClientService() throws FrejaEidException, Exception {
        confirmEmailService = new ConfirmEmailService();
    }

    public static MobileClientService getInstance() {
        if (instance == null) {
            try {
                instance = new MobileClientService();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return instance;
    }

    public ResponseStatus restoreUserToBasicLevel(MobileClientApi mobileClient, String email, String password) throws FrejaEidException {
        try {
            mobileClient.openSecureConnection();
            RegisterAccountEventResponseBean registerAccountEventResponseBean = jsonService.deserializeFromJson(mobileClient.restoreAccount(email, UserInfoType.EMAIL, LanguageCode.EN).getMessage(), RegisterAccountEventResponseBean.class);
            confirmEmailService.openAndConfirmLinkFromLastEmailMessage(email, password);
            return mobileClient.getTokenParamAndVerifyOtp(registerAccountEventResponseBean, email);
        } catch (Exception ex) {
            throw new FrejaEidException("Restoring user to BASIC level has failed.", ex);
        }
    }

    public void connectMobileClientsWithEmails(HashMap<Integer, String> emails, List<String> passwords) throws FrejaEidException {
        mobileClients = new HashMap<>();
        for (Map.Entry pair : emails.entrySet()) {
            int emailKey = (int) pair.getKey();
            String email = (String) pair.getValue();
            MobileClient mobileClient = new MobileClient(EnviromentSettings.getWebSocketAdress(), EnviromentSettings.getWebSocketKeystorePath(), EnviromentSettings.getKeystorePassword());
            restoreUserToBasicLevel(mobileClient, email, passwords.get(emailKey));
            mobileClients.put(email, mobileClient);
        }
    }

    public void initializeSingleMobileClient(String email, String password) throws FrejaEidException {
        restoreUserToBasicLevel(singleMobileClient, email, password);
    }

    public void setSingleMobileClient(MobileClient singleMobileClient) {
        this.singleMobileClient = singleMobileClient;
    }

    public MobileClient getSingleMobileClient() {
        return singleMobileClient;
    }

    public MobileClient getMobileClientForEmail(String email) {
        return mobileClients.get(email);
    }
}
