package com.internship.frejaeidjmeterplugin.jmeter.frejaRequests;

import com.verisec.frejaeid.client.beans.sign.get.SignResult;
import com.verisec.frejaeid.client.enums.MinRegistrationLevel;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientInternalException;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


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
            fail("Bad initialization");
        }
        System.out.println("Done");
    }

    @Test
    public void testInitiateSignRequest() throws Exception {
        System.out.println("Calling initiateSignRequest with regular data");
        String email = "aleksandar.markovic@verisec.com";
        String title = "JUnit test title";
        String dataToSignText = "JUnit test Message";
        MinRegistrationLevel registrationLevel = MinRegistrationLevel.BASIC;
        String result = instance.initiateSignRequest(email, title, dataToSignText, registrationLevel);
        System.out.println(result);
        assertNotNull(result);
        System.out.println("PASS");
    }
    
    @Test
    public void testInitiateSignRequestWrongData() throws Exception {
        System.out.println("Calling initiateSignRequest with irregular data");
        String email = "asd.324??--dd";
        String title = "";
        String dataToSignText = "-------";
        MinRegistrationLevel registrationLevel = MinRegistrationLevel.BASIC;
        assertThrows(FrejaEidClientInternalException.class, () -> {instance.initiateSignRequest(email, title, dataToSignText, registrationLevel);});
        System.out.println("PASS");
    }
    
    @Test
    public void testInitiateSignRequestEmptyData() throws Exception {
        System.out.println("Calling initiateSignRequest with empty data");
        assertThrows(FrejaEidClientInternalException.class, () -> {instance.initiateSignRequest("", "", "", MinRegistrationLevel.BASIC);});
        System.out.println("PASS");
    }


    
    
    
}
