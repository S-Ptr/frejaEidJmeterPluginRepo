package com.internship.frejaeidjmeterplugin.jmeter.sampler.gui;

import com.internship.frejaeidjmeterplugin.jmeter.sampler.AuthSampler;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientInternalException;
import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jmeter.samplers.gui.AbstractSamplerGui;
import org.apache.jmeter.testelement.TestElement;

public class AuthSamplerGui extends AbstractSamplerGui {

    AuthSamplerGuiPanel panel;

    public AuthSamplerGui() {
        super();
        panel = new AuthSamplerGuiPanel();
        setLayout(new BorderLayout(0, 5));
        setBorder(makeBorder());
        add(makeTitlePanel(), BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
    }

    @Override
    public String getLabelResource() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void configure(TestElement testElement) {
        super.configure(testElement);
        if (testElement instanceof AuthSampler) {
            AuthSampler authSampler = (AuthSampler) testElement;
            panel.setAuthEmailField(authSampler.getEmail());
        }
    }

    @Override
    public String getStaticLabel() {
        return "Freja eID Authentication Request";
    }

    @Override
    public TestElement createTestElement() {
        AuthSampler authSampler = null;
        try {
            authSampler = new AuthSampler();
        } catch (FrejaEidClientInternalException ex) {
            Logger.getLogger(AuthSamplerGui.class.getName()).log(Level.SEVERE, null, ex);
        }
        modifyTestElement(authSampler);
        return authSampler;
    }

    @Override
    public void modifyTestElement(TestElement testElement) {
        configureTestElement(testElement);
        AuthSampler authSampler = (AuthSampler) testElement;
        authSampler.setEmail(panel.getAuthEmailField());
    }

    @Override
    public void clearGui() {
        super.clearGui();
    }
}
