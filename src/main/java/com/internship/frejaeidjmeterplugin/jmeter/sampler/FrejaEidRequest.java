package com.internship.frejaeidjmeterplugin.jmeter.sampler;

import com.internship.frejaeidjmeterplugin.jmeter.frejaRequests.MobileClientService;
import com.internship.frejaeidjmeterplugin.jmeter.settings.EmailSettings;
import com.internship.frejaeidjmeterplugin.jmeter.settings.RequestSettings;
import com.verisec.frejaeid.mobileclient.ResponseStatus;
import com.verisec.frejaeid.mobileclient.clients.impl.MobileClient;

public abstract class FrejaEidRequest {

    protected ResponseStatus handleRequest(String requestName, String reference, String email) throws InterruptedException {
        if (EmailSettings.isSingleUser()) {
            return transactionStatus(MobileClientService.getInstance().getSingleMobileClient(), reference, requestName);
        } else {
            return transactionStatus(MobileClientService.getInstance().getMobileClientForEmail(email), reference, requestName);
        }
    }

    protected ResponseStatus transactionStatus(MobileClient mobileClient, String reference, String requestName) throws InterruptedException {
        if (RequestSettings.getResponse(requestName).equals("approve")) {
            Thread.sleep(10000);
            return approveRequest(reference, mobileClient);
        } else if (RequestSettings.getResponse(requestName).equals("decline")) {  
            return mobileClient.declineTransaction(reference);
        }
        return null;
    }

    protected abstract ResponseStatus approveRequest(String reference, MobileClient mobileClient);
}
