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
        add(panel,BorderLayout.CENTER);
    }
    
    @Override
    public String getLabelResource() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void configure(TestElement element) {
        super.configure(element);
        if(element instanceof AuthSampler){
            AuthSampler ref = (AuthSampler) element;
            panel.setAuthEmailField(ref.getEmail());
        }
    }

    @Override
    public String getStaticLabel() {
        return "Freja eID Authentication Request";
    }

    @Override
    public TestElement createTestElement() {
        AuthSampler auth = null;
        try {
            auth = new AuthSampler();
        } catch (FrejaEidClientInternalException ex) {
            Logger.getLogger(AuthSamplerGui.class.getName()).log(Level.SEVERE, null, ex);
        }
        modifyTestElement(auth);
        return auth;
    }

    @Override
    public void modifyTestElement(TestElement te) {
        configureTestElement(te);
        AuthSampler auth = (AuthSampler) te;
        auth.setEmail(panel.getAuthEmailField());
    }
    
    @Override
    public void clearGui() {
        super.clearGui();
        //maybe something else?
    }
}
