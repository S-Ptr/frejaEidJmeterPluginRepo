package com.internship.frejaeidjmeterplugin.jmeter.enums;

public enum Enviroment {
    SYSTEM_TEST("/systemtest"), CUSTOMER_TEST("/customertest"),
    PRODUCTION("/production");

    private final String name;

    private Enviroment(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
