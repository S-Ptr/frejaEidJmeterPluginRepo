package com.internship.frejaeidjmeterplugin.jmeter.email;

import com.verisec.frejaeid.commons.exception.FrejaEidException;
import java.io.IOException;
import javax.mail.MessagingException;


public interface ConfirmEmailServiceApi {
    void openAndConfirmLinkFromLastEmailMessage() throws FrejaEidException, IOException, MessagingException, InterruptedException;
}
