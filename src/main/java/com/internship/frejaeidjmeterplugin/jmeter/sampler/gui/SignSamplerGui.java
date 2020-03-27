package com.internship.frejaeidjmeterplugin.jmeter.sampler.gui;

import com.internship.frejaeidjmeterplugin.jmeter.sampler.SignSampler;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientInternalException;
import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jmeter.samplers.gui.AbstractSamplerGui;
import org.apache.jmeter.testelement.TestElement;


public class SignSamplerGui extends AbstractSamplerGui {

    private SignSamplerGuiPanel panel;
    public SignSamplerGui() {
        super();
        panel = new SignSamplerGuiPanel();
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

        if (testElement instanceof SignSampler) {
            SignSampler signSampler = (SignSampler) testElement;
            panel.setSignEmailField(signSampler.getEmail());
        }

    }
    @Override
    public String getStaticLabel() {
        return "Freja eID Sign Request";
    }

    @Override
    public TestElement createTestElement() {
        TestElement testElement;
        try {
            testElement = new SignSampler();
        } catch (FrejaEidClientInternalException ex) {
            Logger.getLogger(SignSamplerGui.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        modifyTestElement(testElement);
        return testElement;
    }

    @Override
    public void modifyTestElement(TestElement testElement) {
        configureTestElement(testElement);

        if (testElement instanceof SignSampler) {
            SignSampler signSampler = (SignSampler) testElement;
            signSampler.setEmail(panel.getSignEmailField());
        }
    }
}
