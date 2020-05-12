/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.internship.frejaeidjmeterplugin.jmeter.frejaRequests;

import com.verisec.frejaeid.client.exceptions.FrejaEidClientInternalException;
import com.verisec.frejaeid.client.exceptions.FrejaEidException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author PC
 */
public class Test {

    public static void main(String[] args) throws FrejaEidClientInternalException, FrejaEidException, Exception {
        /*   AuthenticationService auth = new AuthenticationService();
        MobileClientService mobile = new MobileClientService();
        System.out.println(mobile.restoreUserToBasicLevel());
        String reference = auth.initiateAuthenticationRequest("petarpetrovic123.test@gmail.com", MinRegistrationLevel.BASIC);
       // System.out.println(mobile.bindUserToTransaction(reference));*/
        int random = (int) (Math.random() * 9);
        System.out.println(random);
    }
}
