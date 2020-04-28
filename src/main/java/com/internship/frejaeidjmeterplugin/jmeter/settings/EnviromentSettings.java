package com.internship.frejaeidjmeterplugin.jmeter.settings;

import com.internship.frejaeidjmeterplugin.jmeter.enums.Enviroment;
import com.verisec.frejaeid.client.beans.general.SslSettings;
import com.verisec.frejaeid.client.enums.FrejaEnvironment;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientInternalException;
import java.io.File;
import java.util.HashMap;

public class EnviromentSettings {

    private static final String RELYING_PARTY_KEYSTORE_NAME = File.separator + "relyingparty_keystore.p12";
    private static final String WEB_SOCKET_KEYSTORE_NAME = File.separator + "websocket_keystore.jks";
    private static final String KEYSTORE_PASSWORD = "123123123";
    private static final String SERVICE_ADRESS_AUTH_AND_SIGN_SYSTEM_TEST = "https://services-st.test.frejaeid.com";
    private static final String SERVICE_ADRESS_AUTH_AND_SIGN_CUSTOMER_TEST = "https://services.test.frejaeid.com";
    private static final String SERVICE_ADRESS_AUTH_AND_SIGN_PRODUCTION_TEST = "https://services.prod.frejaeid.com";
    private static final String SERVICE_ADRESS_MOBILE_SYSTEM_TEST = "wss://frejaeidmobilesvc-st.test.frejaeid.com";
    private static final String SERVICE_ADRESS_MOBILE_CUSTOMER_TEST = "wss://frejaeidmobilesvc.test.frejaeid.com";
    private static final String SERVICE_ADRESS_MOBILE_PRODUCTION = "wss://frejaeidmobilesvc.prod.frejaeid.com";
    private static Enviroment enviroment;
    private static final HashMap<String, String> serviceAddressesMap;

    public EnviromentSettings() {
    }

    static {
        serviceAddressesMap = new HashMap<>();
        addAddressesToMap();
        enviroment = Enviroment.SYSTEM_TEST;
    }

    public static void setEnviroment(Enviroment enviroment) {
        EnviromentSettings.enviroment = enviroment;
    }

    public static Enviroment getEnviroment() {
        return enviroment;
    }

    public static SslSettings getSsllSettings() throws FrejaEidClientInternalException {
        return SslSettings.create(getRelyingPartyKeystore(), KEYSTORE_PASSWORD);
    }

    private static String getRelyingPartyKeystore() {
        return getKeystorePath(RELYING_PARTY_KEYSTORE_NAME);
    }

    public static String getWebSocketKeystore() {
        return getKeystorePath(WEB_SOCKET_KEYSTORE_NAME);
    }

    private static String getKeystorePath(String keyStoreName) {
        String keyStorePath = "resources";
        switch (enviroment) {
            case SYSTEM_TEST:
                keyStorePath = keyStorePath + "/system" + keyStoreName;
                break;
            case CUSTOMER_TEST:
                keyStorePath = keyStorePath + "/customer" + keyStoreName;
                break;
            case PRODUCTION:
                keyStorePath = keyStorePath + "/production" + keyStoreName;
                break;
        }
        return keyStorePath;
    }

    public static String getServiceAdress(String serviceType) {
        switch (enviroment) {
            case SYSTEM_TEST:
                return serviceAddressesMap.get(serviceType + "System");
            case CUSTOMER_TEST:
                return serviceAddressesMap.get(serviceType + "Customer");
            case PRODUCTION:
                return serviceAddressesMap.get(serviceType + "Production");
            default:
                return "n/a";
        }
    }

    public static FrejaEnvironment getFrejaEnvironment() {
        if (Enviroment.PRODUCTION == enviroment) {
            return FrejaEnvironment.PRODUCTION;
        }
        return FrejaEnvironment.TEST;
    }

    public static String getKEYSTORE_PASSWORD() {
        return KEYSTORE_PASSWORD;
    }

    private static void addAddressesToMap() {
        serviceAddressesMap.put("requestSystem", SERVICE_ADRESS_AUTH_AND_SIGN_SYSTEM_TEST);
        serviceAddressesMap.put("requestCustomer", SERVICE_ADRESS_AUTH_AND_SIGN_CUSTOMER_TEST);
        serviceAddressesMap.put("requestProduction", SERVICE_ADRESS_AUTH_AND_SIGN_PRODUCTION_TEST);
        serviceAddressesMap.put("openConnectionSystem", SERVICE_ADRESS_MOBILE_SYSTEM_TEST);
        serviceAddressesMap.put("openConnectionCustomer", SERVICE_ADRESS_MOBILE_CUSTOMER_TEST);
        serviceAddressesMap.put("openConnectionProduction", SERVICE_ADRESS_MOBILE_PRODUCTION);
    }

}
