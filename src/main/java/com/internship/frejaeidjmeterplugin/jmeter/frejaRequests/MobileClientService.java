package com.internship.frejaeidjmeterplugin.jmeter.frejaRequests;

import com.internship.frejaeidjmeterplugin.jmeter.settings.EnviromentSettings;
import com.verisec.frejaeid.commons.exception.FrejaEidException;
import com.verisec.frejaeid.commons.service.json.JsonServiceFactory;
import com.verisec.frejaeid.mobileclient.clients.api.MobileClientApi;
import com.verisec.frejaeid.mobileclient.clients.impl.MobileClient;
import com.verisec.frejaeid.service.commons.service.json.JsonService;

public final class MobileClientService {

    private final MobileClientApi mobileClient;

    static {
        JsonServiceFactory.registerJsonServiceProvider(new JsonService());
    }

    public MobileClientService() throws FrejaEidException, Exception {
        mobileClient = new MobileClient(EnviromentSettings.getWebSocketAdress(), EnviromentSettings.getWebSocketKeystorePath(), EnviromentSettings.getKeystorePassword());
    }

    static {
        JsonServiceFactory.registerJsonServiceProvider(new JsonService());
    }

    public void openSecureConnection() throws Exception {
        mobileClient.openSecureConnection();
    }
}
