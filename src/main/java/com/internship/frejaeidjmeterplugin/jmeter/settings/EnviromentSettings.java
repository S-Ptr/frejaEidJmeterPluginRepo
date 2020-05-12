package com.internship.frejaeidjmeterplugin.jmeter.settings;

import com.internship.frejaeidjmeterplugin.jmeter.enums.Enviroment;
import com.internship.frejaeidjmeterplugin.jmeter.enums.EnviromentAddress;
import com.verisec.frejaeid.client.enums.FrejaEnvironment;
import java.io.File;

public class EnviromentSettings {

    private static final String RELYING_PARTY_KEYSTORE_NAME = File.separator + "relyingparty_keystore.p12";
    private static final String WEB_SOCKET_KEYSTORE_NAME = File.separator + "websocket_keystore.jks";
    private static final String RESOURCES_FOLDER = "resources";
    private static final String KEYSTORE_PASSWORD = "123123123";
    private static Enviroment enviroment;
    private static String webSocketKeystorePath;
    private static String relyingPartyKeystorePath;
    private static String serviceAddress;
    private static String webSocketAdress;
    private static String USER_EMAIL = "petarpetrovic123.test@gmail.com";
    private static String USER_EMAIL_ACCOUNT_PASS = "Petar.123";
    

    public EnviromentSettings() {
    }

    static {
        enviroment = Enviroment.SYSTEM_TEST;
        populateSettings();
    }

    public static void setEnviroment(Enviroment enviroment) {
        EnviromentSettings.enviroment = enviroment;
    }

    public static Enviroment getEnviroment() {
        return enviroment;
    }

    public static String getKeystorePassword() {
        return KEYSTORE_PASSWORD;
    }

    public static String getWebSocketKeystorePath() {
        return webSocketKeystorePath;
    }

    public static String getRelyingPartyKeystorePath() {
        return relyingPartyKeystorePath;
    }

    public static String getWebSocketAdress() {
        return webSocketAdress;
    }

    public static String getServiceAddress() {
        return serviceAddress;
    }

    public static String getUserEmail() {
        return USER_EMAIL;
    }

    public static String getUserEmailAccountPassword() {
        return USER_EMAIL_ACCOUNT_PASS;
    }
    public static FrejaEnvironment getFrejaEnvironment() {
        if (Enviroment.PRODUCTION == enviroment) {
            return FrejaEnvironment.PRODUCTION;
        }
        return FrejaEnvironment.TEST;
    }

    public static void populateSettings() {
        switch (enviroment) {
            case SYSTEM_TEST:
                webSocketKeystorePath = RESOURCES_FOLDER + Enviroment.SYSTEM_TEST.getName() + WEB_SOCKET_KEYSTORE_NAME;
                webSocketAdress = EnviromentAddress.SYSTEM_TEST_MOBILE_CLIENT.getUrl();
                relyingPartyKeystorePath = RESOURCES_FOLDER + Enviroment.SYSTEM_TEST.getName() + RELYING_PARTY_KEYSTORE_NAME;
                serviceAddress = EnviromentAddress.SYSTEM_TEST_SERVICES.getUrl();
                break;
            case CUSTOMER_TEST:
                webSocketKeystorePath = RESOURCES_FOLDER + Enviroment.CUSTOMER_TEST.getName() + WEB_SOCKET_KEYSTORE_NAME;
                webSocketAdress = EnviromentAddress.CUSTOMER_TEST_MOBILE_CLIENT.getUrl();
                relyingPartyKeystorePath = RESOURCES_FOLDER + Enviroment.CUSTOMER_TEST.getName() + RELYING_PARTY_KEYSTORE_NAME;
                serviceAddress = EnviromentAddress.COSTUMER_TEST_SERVICES.getUrl();
                break;
            case PRODUCTION:
                webSocketKeystorePath = RESOURCES_FOLDER + Enviroment.PRODUCTION.getName() + WEB_SOCKET_KEYSTORE_NAME;
                webSocketAdress = EnviromentAddress.PRODUCTION_MOBILE_CLIENT.getUrl();
                relyingPartyKeystorePath = RESOURCES_FOLDER + Enviroment.PRODUCTION.getName() + RELYING_PARTY_KEYSTORE_NAME;
                serviceAddress = EnviromentAddress.PRODUCTION_SERVICES.getUrl();
                break;
        }
    }
}
