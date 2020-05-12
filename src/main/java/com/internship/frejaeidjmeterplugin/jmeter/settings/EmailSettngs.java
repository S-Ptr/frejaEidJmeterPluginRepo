package com.internship.frejaeidjmeterplugin.jmeter.settings;

import java.util.HashMap;

public class EmailSettngs {

    private static HashMap<Integer, String> emails;
    
    static {
        emails = new HashMap<>();
    }

    public void EmailSettings() {
    }

    public static String getEmail(int number) {
        return emails.get(number);
    }

    public static void populateListOfEmails (String path) {
        
    }
}
