package com.internship.frejaeidjmeterplugin.jmeter.enums;

public enum EnviromentAddress {
    SYSTEM_TEST_SERVICES("https://services-st.test.frejaeid.com"),
    COSTUMER_TEST_SERVICES("https://services.test.frejaeid.com"),
    PRODUCTION_SERVICES("https://services.prod.frejaeid.com"),
    SYSTEM_TEST_MOBILE_CLIENT("wss://frejaeidmobilesvc-st.test.frejaeid.com"),
    CUSTOMER_TEST_MOBILE_CLIENT("wss://frejaeidmobilesvc.test.frejaeid.com"),
    PRODUCTION_MOBILE_CLIENT("wss://frejaeidmobilesvc.prod.frejaeid.com");

    private final String url;

    private EnviromentAddress(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

}
