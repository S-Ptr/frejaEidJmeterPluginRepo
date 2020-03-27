package com.internship.frejaeidjmeterplugin.jmeter.frejaRequests;

import com.verisec.frejaeid.client.enums.MinRegistrationLevel;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientInternalException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AuthenticationServiceTest {

    private static AuthenticationService authenticationService;

    @BeforeAll
    public static void setUpClass() {
        System.out.println("Class setup");
        try {
            authenticationService = new AuthenticationService();
        } catch (FrejaEidClientInternalException ex) {
            Logger.getLogger(AuthenticationService.class.getName()).log(Level.SEVERE, null, ex);
            fail("Bad init");
        }
        System.out.println("Done");
    }

    @Test
    public void testInitiateAuthenticationRequestRegularData() throws Exception {
        System.out.println("initiateAuthenticationRequest with regular data");
        String email = "aleksandar.markovic@verisec.com";
        MinRegistrationLevel registrationLevel = MinRegistrationLevel.BASIC;
        String reference = authenticationService.initiateAuthenticationRequest(email, registrationLevel);
        assertNotNull(authenticationService.getResult(reference));
    }

    @Test
    public void testInitiateAuthenticationEmptyUserInfo() {
        System.out.println("initiateAuthenticationRequest with regular data");
        String email = "";
        MinRegistrationLevel registrationLevel = MinRegistrationLevel.BASIC;
        String reference = "";
        try {
            reference = authenticationService.initiateAuthenticationRequest(email, registrationLevel);
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
        String reference = authenticationService.initiateAuthenticationRequest(email, registrationLevel);
        assertNotNull(reference);
    }

}
