/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.internship.frejaeidjmeterplugin.jmeter.frejaRequests;

import com.verisec.frejaeid.client.beans.sign.get.SignResult;
import com.verisec.frejaeid.client.enums.MinRegistrationLevel;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientInternalException;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientPollingException;
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author User
 */
public class SignServiceTest {
    
    private static SignService instance;
    
    public SignServiceTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        System.out.println("Class setup");
        try {
            instance = new SignService();
        } catch (FrejaEidClientInternalException ex) {
            Logger.getLogger(SignServiceTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Bad init");
        }
        System.out.println("Done");
    }
    
    @AfterAll
    public static void tearDownClass() {
        System.out.println("Class dissolution");
    }
    
    @BeforeEach
    public void setUp() {
        System.out.println("pre-test setup");
    }
    
    @AfterEach
    public void tearDown() {
        System.out.println("post-test cleanup");
    }

    /**
     * Test of initiateSignRequest method, of class SignService.
     */
    @Test
    public void testInitiateSignRequest() throws Exception {
        System.out.println("initiateSignRequest with regular data");
        String email = "aleksandar.markovic@verisec.com";
        String title = "asd";
        String dataToSignText = "asd";
        MinRegistrationLevel registrationLevel = MinRegistrationLevel.BASIC;
        String expResult = "";
        String result = instance.initiateSignRequest(email, title, dataToSignText, registrationLevel);
        assertNotEquals("", result);
        System.out.println("PASS");
    }
    
    @Test
    public void testInitiateSignRequestWrongData() throws Exception {
        System.out.println("initiateSignRequest with irregular data");
        String email = "asd.324??--dd";
        String title = "";
        String dataToSignText = "-------";
        MinRegistrationLevel registrationLevel = MinRegistrationLevel.BASIC;
        assertThrows(FrejaEidClientInternalException.class, () -> {instance.initiateSignRequest(email, title, dataToSignText, registrationLevel);});
        System.out.println("PASS");
    }
    
    @Test
    public void testInitiateSignRequestEmptyData() throws Exception {
        System.out.println("initiateSignRequest with empty data");
        assertThrows(FrejaEidClientInternalException.class, () -> {instance.initiateSignRequest("", "", "", MinRegistrationLevel.BASIC);});
        System.out.println("PASS");
    }

    @Test
    public void testGetResultsTimeout() throws Exception {
        System.out.println("getResultsTimeoutThrow");
        String email = "aleksandar.markovic@verisec.com";
        String title = "asd";
        String dataToSignText = "asd";
        MinRegistrationLevel registrationLevel = MinRegistrationLevel.BASIC;
        SignService instance = new SignService();
        String reference = instance.initiateSignRequest(email, title, dataToSignText, registrationLevel);
        assertThrows(FrejaEidClientPollingException.class, () -> {instance.getResults(reference);});
        System.out.println("PASS");
    }
    
    
    
}
