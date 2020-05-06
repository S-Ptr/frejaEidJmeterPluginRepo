package com.internship.frejaeidjmeterplugin.jmeter.sampler.gui;

import com.internship.frejaeidjmeterplugin.jmeter.sampler.FrejaEidPluginSampler;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientInternalException;
import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
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
    private static int i = 1;
    private static long instanceCount = 0;
    private long instanceID;
    private BufferedReader emailFileStream;
    private FileReader fileObject;

    public FrejaEidPluginGui() {
        super();
        frejaEIDPluginGuiPanel = new FrejaEidPluginGuiPanel();
        setLayout(new BorderLayout(0, 5));
        setBorder(makeBorder());
        add(makeTitlePanel(), BorderLayout.NORTH);
        add(frejaEIDPluginGuiPanel, BorderLayout.CENTER);
        reguestsMap = new HashMap<>();
        setRequestsMap();
        instanceCount++;
        instanceID = instanceCount;
    }

    @Override
    public String getLabelResource() {
        return this.getClass().getSimpleName();
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
        if (element instanceof FrejaEidPluginSampler) {
            FrejaEidPluginSampler frejaEidPluginSampler = (FrejaEidPluginSampler) element;
            frejaEIDPluginGuiPanel.setTxtEmail(frejaEidPluginSampler.getEmail());
            frejaEIDPluginGuiPanel.setEmailFilePath(frejaEidPluginSampler.getEmailFilePath());
            frejaEIDPluginGuiPanel.setEmailInputType(frejaEidPluginSampler.getEmailInputType());
        }
    }

    @Override
    public void modifyTestElement(TestElement te) {
        super.configureTestElement(te);
        FrejaEidPluginSampler frejaEidPluginSampler = (FrejaEidPluginSampler) te;
        frejaEidPluginSampler.setEmail(frejaEIDPluginGuiPanel.getTxtEmail().getText());
        String requests = setRequests();
        frejaEidPluginSampler.setRequests(requests);
        frejaEidPluginSampler.setEmailFilePath(frejaEIDPluginGuiPanel.getEmailFilePath().getText());
        frejaEidPluginSampler.setEmailInputType(frejaEIDPluginGuiPanel.getEmailInputType());
        frejaEidPluginSampler.setGroupID(instanceID);
    }

    @Override
    public String getStaticLabel() {
        return "Freja eID Plugin";
    }
    
    public BufferedReader getEmailFileStream(){
        return emailFileStream;
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
    
    public synchronized String getLineFromFile(String absolutePath) throws FileNotFoundException, IOException{
        if(emailFileStream == null){
            emailFileStream = new BufferedReader(new FileReader(absolutePath));
        }
        String line = emailFileStream.readLine();
        if(line == null){
            emailFileStream = new BufferedReader(new FileReader(absolutePath));
            line = emailFileStream.readLine();
        }
        return line;
    }
    
    
    

}
