package com.internship.frejaeidjmeterplugin.jmeter.sampler.gui;

import com.internship.frejaeidjmeterplugin.jmeter.sampler.FrejaEIDPluginSampler;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientInternalException;
import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jmeter.samplers.gui.AbstractSamplerGui;
import org.apache.jmeter.testelement.TestElement;

public class FrejaEIDPlugnGui extends AbstractSamplerGui {

    private final FrejaEIDPluginGuiPanel panel;

    public FrejaEIDPlugnGui() {
        super();
        panel = new FrejaEIDPluginGuiPanel();
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
    public TestElement createTestElement() {
        FrejaEIDPluginSampler general = null;
        try {
            general = new FrejaEIDPluginSampler();
        } catch (FrejaEidClientInternalException ex) {
            Logger.getLogger(FrejaEIDPluginGuiPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        modifyTestElement(general);
        return general;
    }

    @Override
    public void configure(TestElement element) {
        super.configure(element);
        if (element instanceof FrejaEIDPluginSampler) {
            FrejaEIDPluginSampler ref = (FrejaEIDPluginSampler) element;
            panel.setTxtEmail(ref.getEmail());
        }
    }

    @Override
    public void modifyTestElement(TestElement te) {
        super.configureTestElement(te);
        FrejaEIDPluginSampler gen = (FrejaEIDPluginSampler) te;
        gen.setEmail(panel.getTxtEmail().getText());
        if (panel.getCheckAuth().isSelected() && panel.getCheckSign().isSelected()) {
            gen.setSelected("both");
        } else if (panel.getCheckAuth().isSelected()) {
            gen.setSelected("auth");
        } else if (panel.getCheckSign().isSelected()) {
            gen.setSelected("sign");
        } else {
            gen.setSelected("noAction");
        }
    }

    @Override
    public String getStaticLabel() {
        return "Freja eID Plugin";
    }

}
