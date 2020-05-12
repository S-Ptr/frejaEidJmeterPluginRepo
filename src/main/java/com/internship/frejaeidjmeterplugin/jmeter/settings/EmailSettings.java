package com.internship.frejaeidjmeterplugin.jmeter.settings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmailSettings {

    private static HashMap<Integer, String> emails;
    private static List<String> passwords;

    static {
        emails = new HashMap<>();
        passwords = new LinkedList<>();
    }

    public void EmailSettings() {
    }

    public static boolean isSingleUser() {
        if (emails == null){
            return true;
        }
        return false;
    }
    
     public static String getRandomEmail (int number) {
        return emails.get(number);
    }

    public static void populateListOfEmails(File file) {
        BufferedReader br;
        int emailNumberKey = 0;
        try {
            br = new BufferedReader(new FileReader(file));
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                String[] emailAndPassword = line.split(" ");
                emails.put(emailNumberKey++, emailAndPassword[0]);
                passwords.add(emailAndPassword[1]);
            }
        } catch (Exception ex) {
            Logger.getLogger(EmailSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
