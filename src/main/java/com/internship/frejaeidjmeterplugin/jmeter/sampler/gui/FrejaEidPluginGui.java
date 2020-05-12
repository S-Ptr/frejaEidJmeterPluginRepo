package com.internship.frejaeidjmeterplugin.jmeter.sampler.gui;

import com.internship.frejaeidjmeterplugin.jmeter.sampler.gui.panel.FrejaEidPluginGuiPanel;
import com.internship.frejaeidjmeterplugin.jmeter.sampler.impl.FrejaEidPluginSampler;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientInternalException;
import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import org.apache.jmeter.samplers.gui.AbstractSamplerGui;
import org.apache.jmeter.testelement.TestElement;

public class FrejaEidPluginGui extends AbstractSamplerGui {

    private final FrejaEidPluginGuiPanel frejaEIDPluginGuiPanel;
    private final HashMap<JCheckBox, String> reguestsMap;

    public FrejaEidPluginGui() {
        super();
        frejaEIDPluginGuiPanel = new FrejaEidPluginGuiPanel();
        setLayout(new BorderLayout(0, 5));
        setBorder(makeBorder());
        add(makeTitlePanel(), BorderLayout.NORTH);
        add(frejaEIDPluginGuiPanel, BorderLayout.CENTER);
        reguestsMap = new HashMap<>();
        setRequestsMap();
    }

    @Override
    public String getLabelResource() {
        return this.getClass().getSimpleName();
    }

    @Override
    public TestElement createTestElement() {
        FrejaEidPluginSampler frejaEdPluginSampler = null;
        try {
            frejaEdPluginSampler = new FrejaEidPluginSampler();
        } catch (FrejaEidClientInternalException ex) {
            Logger.getLogger(FrejaEidPluginGuiPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FrejaEidPluginGui.class.getName()).log(Level.SEVERE, null, ex);
        }
        return frejaEdPluginSampler;
    }

    @Override
    public void configure(TestElement element) {
        super.configure(element);
        if (element instanceof FrejaEidPluginSampler) {
            FrejaEidPluginSampler frejaEdPluginSampler = (FrejaEidPluginSampler) element;
            frejaEIDPluginGuiPanel.setTxtEmail(frejaEdPluginSampler.getEmail());
        }
    }

    @Override
    public void modifyTestElement(TestElement te) {
        super.configureTestElement(te);
        FrejaEidPluginSampler frejaEidPluginSampler = (FrejaEidPluginSampler) te;
        frejaEidPluginSampler.setEmail(frejaEIDPluginGuiPanel.getTxtEmail().getText());
        String requests = setRequests();
        frejaEidPluginSampler.setRequests(requests);
    }

    @Override
    public String getStaticLabel() {
        return "Freja eID Plugin";
    }

    private String setRequests() {
        String response = "";
        for (Map.Entry pair : reguestsMap.entrySet()) {
            JCheckBox request = (JCheckBox) pair.getKey();
            if (request.isSelected()) {
                if (response.equals("")) {
                    response = (String) pair.getValue();
                } else {
                    response = response.concat(" ").concat((String) pair.getValue());
                }
            }
        }
        return response;
    }

    private void setRequestsMap() {
        reguestsMap.put(frejaEIDPluginGuiPanel.getCheckAuth(), "auth");
        reguestsMap.put(frejaEIDPluginGuiPanel.getCheckSign(), "sign");
        reguestsMap.put(frejaEIDPluginGuiPanel.getCheckOpenSecureConnection(), "mobile");
    }

}
