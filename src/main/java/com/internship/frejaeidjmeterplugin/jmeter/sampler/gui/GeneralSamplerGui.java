
package com.internship.frejaeidjmeterplugin.jmeter.sampler.gui;

import com.internship.frejaeidjmeterplugin.jmeter.sampler.GeneralSampler;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientInternalException;
import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jmeter.samplers.gui.AbstractSamplerGui;
import org.apache.jmeter.testelement.TestElement;


public class GeneralSamplerGui extends AbstractSamplerGui {
    private GeneralSamplerGuiPanel panel;

    public GeneralSamplerGui() {
        super();
        panel = new GeneralSamplerGuiPanel();
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
    public TestElement createTestElement() {
        GeneralSampler general = null;
        try {
            general = new GeneralSampler();
        } catch (FrejaEidClientInternalException ex) {
            Logger.getLogger(GeneralSamplerGuiPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        modifyTestElement(general);
        return general;
    }

    @Override
    public void modifyTestElement(TestElement te) {
        super.configureTestElement(te);
        GeneralSampler gen = (GeneralSampler) te;
        gen.setEmail(panel.getTxtAuthEmail().getText());
    }

    @Override
    public void configure(TestElement element) {
        super.configure(element);
        if (element instanceof GeneralSampler) {
            GeneralSampler ref = (GeneralSampler) element;
            panel.setTxtAuthEmail(ref.getEmail());
        }
    }

    @Override
    public String getStaticLabel() {
        return "Freja eID General Request";
    }

}
