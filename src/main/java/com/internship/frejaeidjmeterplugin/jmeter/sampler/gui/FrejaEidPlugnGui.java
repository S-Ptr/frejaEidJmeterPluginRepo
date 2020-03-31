package com.internship.frejaeidjmeterplugin.jmeter.sampler.gui;

import com.internship.frejaeidjmeterplugin.jmeter.sampler.FrejaEidPluginSampler;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientInternalException;
import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jmeter.samplers.gui.AbstractSamplerGui;
import org.apache.jmeter.testelement.TestElement;

public class FrejaEidPlugnGui extends AbstractSamplerGui {

    private final FrejaEidPluginGuiPanel frejaEIDPluginGuiPanel;

    public FrejaEidPlugnGui() {
        super();
        frejaEIDPluginGuiPanel = new FrejaEidPluginGuiPanel();
        setLayout(new BorderLayout(0, 5));
        setBorder(makeBorder());
        add(makeTitlePanel(), BorderLayout.NORTH);
        add(frejaEIDPluginGuiPanel, BorderLayout.CENTER);
    }

    @Override
    public String getLabelResource() {
        return this.getClass().getSimpleName();
    }

    @Override
    public TestElement createTestElement() {
        FrejaEidPluginSampler general = null;
        try {
            general = new FrejaEidPluginSampler();
        } catch (FrejaEidClientInternalException ex) {
            Logger.getLogger(FrejaEidPluginGuiPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        modifyTestElement(general);
        return general;
    }

    @Override
    public void configure(TestElement element) {
        super.configure(element);
        if (element instanceof FrejaEidPluginSampler) {
            FrejaEidPluginSampler ref = (FrejaEidPluginSampler) element;
            frejaEIDPluginGuiPanel.setTxtEmail(ref.getEmail());
        }
    }

    @Override
    public void modifyTestElement(TestElement te) {
        super.configureTestElement(te);
        FrejaEidPluginSampler gen = (FrejaEidPluginSampler) te;
        gen.setEmail(frejaEIDPluginGuiPanel.getTxtEmail().getText());
        if (frejaEIDPluginGuiPanel.getCheckAuth().isSelected() && frejaEIDPluginGuiPanel.getCheckSign().isSelected()) {
            gen.setSelected("both");
        } else if (frejaEIDPluginGuiPanel.getCheckAuth().isSelected()) {
            gen.setSelected("auth");
        } else if (frejaEIDPluginGuiPanel.getCheckSign().isSelected()) {
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
