package com.internship.frejaeidjmeterplugin.jmeter.settings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class EmailSettings {

    private static HashMap<Integer, String> emails;
    private static List<String> passwords;
    private static File fileWithUsersInfo;
    private static String singleUserEmail;
    private static boolean singleUser;

    static {
        emails = new HashMap<>();
        passwords = new LinkedList<>();
        singleUser = true;
    }

    public void EmailSettings() {
    }

    public static void setSingleUser(boolean singleUser) {
        EmailSettings.singleUser = singleUser;
    }

    public static String getSingleUserEmail() {
        return singleUserEmail;
    }

    public static boolean isSingleUser() {
        return singleUser;
    }

    public static void setSingleUserEmail(String singleUserEmail) {
        EmailSettings.singleUserEmail = singleUserEmail;
    }

    public static HashMap<Integer, String> getEmails() {
        return emails;
    }

    public static List<String> getPasswords() {
        return passwords;
    }

    public static void setFileWithUsersInfo(File fileWithUsersInfo) {
        EmailSettings.fileWithUsersInfo = fileWithUsersInfo;
    }

    public static File getFileWithUsersInfo() {
        return fileWithUsersInfo;
    }

    public static String getRandomEmail(int number) {
        return emails.get(number);
    }

    public static int getNumberOfEmails() {
        return emails.size() - 1;
    }

    public static void populateListOfEmails() throws FileNotFoundException, IOException {
        BufferedReader br;
        int emailNumberKey = 0;
        br = new BufferedReader(new FileReader(fileWithUsersInfo));
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            String[] emailAndPassword = line.split(" ");
            emails.put(emailNumberKey++, emailAndPassword[0]);
            passwords.add(emailAndPassword[1]);
        }

    }

}
