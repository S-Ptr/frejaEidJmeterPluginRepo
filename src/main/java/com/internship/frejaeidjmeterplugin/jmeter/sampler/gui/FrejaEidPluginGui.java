package com.internship.frejaeidjmeterplugin.jmeter.sampler.gui;

import com.internship.frejaeidjmeterplugin.jmeter.sampler.gui.panel.FrejaEidPluginGuiPanel;
import com.internship.frejaeidjmeterplugin.jmeter.sampler.impl.FrejaEidPluginSampler;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientInternalException;
import java.awt.BorderLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jmeter.samplers.gui.AbstractSamplerGui;
import org.apache.jmeter.testelement.TestElement;

public class FrejaEidPluginGui extends AbstractSamplerGui {

    private static FrejaEidPluginGuiPanel frejaEIDPluginGuiPanel;

    public FrejaEidPluginGui() {
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

    public static FrejaEidPluginGuiPanel getFrejaEIDPluginGuiPanel() {
        return frejaEIDPluginGuiPanel;
    }

    @Override
    public TestElement createTestElement() {
        FrejaEidPluginSampler frejaEidPluginSampler = null;
        try {
            frejaEidPluginSampler = new FrejaEidPluginSampler();
        } catch (FrejaEidClientInternalException ex) {
            Logger.getLogger(FrejaEidPluginGuiPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FrejaEidPluginGui.class.getName()).log(Level.SEVERE, null, ex);
        }
        return frejaEidPluginSampler;
    }

    @Override
    public void configure(TestElement element) {
        super.configure(element);
    }

    @Override
    public void modifyTestElement(TestElement te) {
        super.configureTestElement(te);
        FrejaEidPluginSampler frejaEidPluginSampler = (FrejaEidPluginSampler) te;
    }

    @Override
    public String getStaticLabel() {
        return "Freja eID Plugin";
    }

}
