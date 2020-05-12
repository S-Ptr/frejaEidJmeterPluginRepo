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

public final class MobileClientService {

    private final HashMap<String, MobileClient> mobileClients;
    private final HashMap<Integer, String> emails;
    private final ConfirmEmailServiceApi confirmEmailService;
    private static final JsonService jsonService;

    static {
        jsonService = new JsonService();
        JsonServiceFactory.registerJsonServiceProvider(jsonService);
    }

    public MobileClientService() throws FrejaEidException, Exception {
        confirmEmailService = new ConfirmEmailService();
        emails = new HashMap<>();
        mobileClients = new HashMap<>();
    }

    static {
        JsonServiceFactory.registerJsonServiceProvider(new JsonService());
    }

    public void openSecureConnection(MobileClientApi mobileClient) throws Exception {
        mobileClient.openSecureConnection();
    }

    public ResponseStatus restoreUserToBasicLevel(MobileClientApi mobileClient) throws FrejaEidException {
        try {
            mobileClient.openSecureConnection();
            RegisterAccountEventResponseBean registerAccountEventResponseBean = jsonService.deserializeFromJson(mobileClient.restoreAccount(EnviromentSettings.getUserEmail(), UserInfoType.EMAIL, LanguageCode.EN).getMessage(), RegisterAccountEventResponseBean.class);
            confirmEmailService.openAndConfirmLinkFromLastEmailMessage();
            return mobileClient.getTokenParamAndVerifyOtp(registerAccountEventResponseBean, EnviromentSettings.getUserEmail());
        } catch (Exception ex) {
            throw new FrejaEidException("Restoring user to BASIC level has failed.", ex);
        }
    }

    public ResponseStatus bindUserToTransaction(MobileClientApi mobileClient, String reference) {
        return mobileClient.bindUserToTransaction(reference);
    }

    public ResponseStatus approveAuthenticationTransaction(MobileClientApi mobileClient, String reference) {
        return mobileClient.approveAuthenticationTransaction(reference);
    }

    public ResponseStatus approveSignTransaction(MobileClientApi mobileClient, String reference) throws Exception {
        return mobileClient.approveSignTransaction(reference);
    }

    public ResponseStatus declineTransaction(MobileClientApi mobileClient, String reference) {
        return mobileClient.declineTransaction(reference);
    }

    public void closeConnection(MobileClientApi mobileClient) {
        mobileClient.closeConnection();
    }

    public void populateMobileClients(List<String> emails) {
    }

    public MobileClient getMobileClient(String email) {
        return mobileClients.get(email);
    }
}
