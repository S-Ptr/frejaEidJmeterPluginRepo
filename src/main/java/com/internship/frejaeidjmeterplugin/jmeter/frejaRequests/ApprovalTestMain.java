/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.internship.frejaeidjmeterplugin.jmeter.frejaRequests;

import com.verisec.frejaeid.client.enums.MinRegistrationLevel;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientInternalException;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientPollingException;
import com.verisec.frejaeid.client.exceptions.FrejaEidException;
import com.verisec.frejaeid.mobileclient.ResponseStatus;
import com.verisec.frejaeid.mobileclient.clients.impl.MobileClient;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class ApprovalTestMain {

    private static final String KEYSTORE_PATH = "src/main/resources/websocket_keystore.jks";
    private static final String KEYSTORE_PASSWORD = "123123123";
    private static final String SERVICE_ADRESS = "wss://frejaeidmobilesvc-st.test.frejaeid.com";

    /*public static void main(String[] args) throws Exception {
        try {
            SignService asdf = new SignService();
            MobileClient mobileClient = new MobileClient(SERVICE_ADRESS, KEYSTORE_PATH, KEYSTORE_PASSWORD);
            mobileClient.openSecureConnection();
            String ref = asdf.initiateSignRequest("aleksandar.markovic@verisec.com", "whadup", "rip and tear", MinRegistrationLevel.EXTENDED);
            System.out.println(ref);
            ResponseStatus response = mobileClient.approveSignTransaction(ref);
            System.out.println(response.toString());
            mobileClient.closeConnection();

        } catch (FrejaEidClientInternalException ex) {
            Logger.getLogger(ApprovalTestMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FrejaEidException ex) {
            Logger.getLogger(ApprovalTestMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FrejaEidClientPollingException ex) {
            Logger.getLogger(ApprovalTestMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (com.verisec.frejaeid.commons.exception.FrejaEidException ex) {
            Logger.getLogger(ApprovalTestMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/

}
