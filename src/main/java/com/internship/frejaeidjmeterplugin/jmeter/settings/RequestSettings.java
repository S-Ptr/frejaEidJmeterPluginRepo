package com.internship.frejaeidjmeterplugin.jmeter.settings;

import java.util.HashMap;

public class RequestSettings {

    private static String[] requests;
    private static HashMap<String, String> mobileResponses;

    public static void populateRequests(boolean authSelected, boolean signSelected, boolean openSecureSelected) {
        String requestsString = "";
        if (authSelected) {
            requestsString = requestsString.concat("auth");
        }
        if (signSelected) {
            if (!requestsString.equals("")) {
                requestsString = requestsString.concat(" ");
            }
            requestsString = requestsString.concat("sign");
        }
        if (openSecureSelected) {
            requestsString = requestsString.concat("mobile");
        }

        if (requestsString.equals("")) {
            requests = new String[0];
        } else {
            requests = requestsString.split(" ");
        }

    }

    public static void populateResponses(boolean authApprove, boolean authDecline, boolean authNoAction, boolean signApprove, boolean signDecline, boolean signNoAction) {
        mobileResponses = new HashMap<>();
        if (authApprove) {
            mobileResponses.put("auth", "approve");
        } else if (authDecline) {
            mobileResponses.put("auth", "decline");
        } else {
            mobileResponses.put("auth", "noAction");
        }
        if (signApprove) {
            mobileResponses.put("sign", "approve");
        } else if (signDecline) {
            mobileResponses.put("sign", "decline");
        } else {
            mobileResponses.put("sign", "noAction");
        }
    }

    public static String getResponse(String request) {
        if (mobileResponses == null) {
            return null;
        }
        if (!mobileResponses.containsKey(request)) {
            return null;
        }
        return mobileResponses.get(request);
    }

    public static String[] getRequests() {
        return requests;
    }

}
