package com.internship.frejaeidjmeterplugin.jmeter.email;

import com.verisec.frejaeid.commons.exception.FrejaEidException;
import java.io.IOException;
import javax.mail.MessagingException;


public interface ConfirmEmailServiceApi {
    void openAndConfirmLinkFromLastEmailMessage(String email, String password) throws FrejaEidException, IOException, MessagingException, InterruptedException;
}
