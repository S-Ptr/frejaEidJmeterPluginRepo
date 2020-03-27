package com.internship.frejaeidjmeterplugin.jmeter.frejaRequests;

import com.verisec.frejaeid.client.enums.MinRegistrationLevel;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientInternalException;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientPollingException;
import com.verisec.frejaeid.client.exceptions.FrejaEidException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AuthenticationServiceTest {

    private static AuthenticationService instance;

    public AuthenticationServiceTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        System.out.println("Class setup");
        try {
            instance = new AuthenticationService();
        } catch (FrejaEidClientInternalException ex) {
            Logger.getLogger(AuthenticationService.class.getName()).log(Level.SEVERE, null, ex);
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

    @Test
    public void testInitiateAuthenticationRequestRegularData() throws Exception {
        System.out.println("initiateAuthenticationRequest with regular data");
        String email = "aleksandar.markovic@verisec.com";
        MinRegistrationLevel registrationLevel = MinRegistrationLevel.BASIC;
        String expResult = "";
        String reference = instance.initiateAuthenticationRequest(email, registrationLevel);
        assertNotEquals(expResult, reference);
        assertNotNull(instance.getResult(reference));
    }

    @Test
    public void testInitiateAuthenticationEmptyUserInfo() {
        System.out.println("initiateAuthenticationRequest with regular data");
        String email = "";
        MinRegistrationLevel registrationLevel = MinRegistrationLevel.BASIC;
        String reference = "";
        try {
            reference = instance.initiateAuthenticationRequest(email, registrationLevel);
        } catch (Exception ex) {
            Logger.getLogger(AuthenticationServiceTest.class.getName()).log(Level.SEVERE, null, ex);
            assertEquals(ex.getMessage(), "UserInfo cannot be null or empty.");
        }
    }

    @Test
    public void testInitiateAuthenticationWrongUserInfo() throws Exception {
        System.out.println("initiateAuthenticationRequest with regular data");
        String email = "aleksandar@verisec.com";
        MinRegistrationLevel registrationLevel = MinRegistrationLevel.BASIC;
        String reference = instance.initiateAuthenticationRequest(email, registrationLevel);
        assertNotNull(reference);
    }

}
