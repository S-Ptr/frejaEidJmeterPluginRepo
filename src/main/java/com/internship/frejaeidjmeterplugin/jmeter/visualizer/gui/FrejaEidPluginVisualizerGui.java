package com.internship.frejaeidjmeterplugin.jmeter.visualizer.gui;

import java.awt.BorderLayout;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.visualizers.gui.AbstractVisualizer;

public class FrejaEidPluginVisualizerGui extends AbstractVisualizer {

    private final FrejaEidPluginVisualizerGuiPanel frejaEIDPluginVsualizerGuiPanel;
    private static final String RESPONSE_CODE = "FAILED";
    private final HashMap<String, ResultPanel> results;

    public FrejaEidPluginVisualizerGui() {
        super();
        frejaEIDPluginVsualizerGuiPanel = new FrejaEidPluginVisualizerGuiPanel();
        setLayout(new BorderLayout(0, 5));
        setBorder(makeBorder());
        add(makeTitlePanel(), BorderLayout.NORTH);
        add(frejaEIDPluginVsualizerGuiPanel, BorderLayout.CENTER);
        results = new HashMap<>();
        setResultsPanels();
    }

    @Override
    public String getStaticLabel() {
        return "Freja eID Plugin Statistics";
    }

    @Override
    public String getLabelResource() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void add(SampleResult sampleResult) {
        HashMap<String, String> responseData = getResponseData(sampleResult);
        if (responseData == null) {
            if (!sampleResult.getSampleLabel().equals("noAction")) {
                statisticsOneRequest(sampleResult.getContentType(), sampleResult.getResponseCode());
            }
        } else {
            statsticsMoreRequests(sampleResult);
        }
    }

    private HashMap<String, String> getResponseData(SampleResult sampleResult) {
        byte[] responseDataByte = sampleResult.getResponseData();
        HashMap<String, String> responseData = null;
        ByteArrayInputStream bais = new ByteArrayInputStream(responseDataByte);
        ObjectInputStream in;
        try {
            in = new ObjectInputStream(bais);
            responseData = (HashMap<String, String>) in.readObject();
            in.close();
        } catch (Exception ex) {
            Logger.getLogger(FrejaEidPluginVisualizerGui.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bais.close();
            } catch (IOException ex) {
                Logger.getLogger(FrejaEidPluginVisualizerGui.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return responseData;
    }

    @Override
    public void clearData() {
    }

    private void statisticsOneRequest(String request, String responseCode) {
        ResultPanel result = results.get(request);
        if (responseCode.equals(RESPONSE_CODE)) {
            result.increasetFailed();
        } else {
            result.increaseDelivered();
        }
    }

    private void statsticsMoreRequests(SampleResult sampleResult) {
        byte[] responseDataByte = sampleResult.getResponseData();
        ByteArrayInputStream bais = new ByteArrayInputStream(responseDataByte);
        try {
            ObjectInputStream in = new ObjectInputStream(bais);
            HashMap<String, String> responseData = (HashMap<String, String>) in.readObject();

            for (Map.Entry pair : responseData.entrySet()) {
                String requestName = (String) pair.getKey();
                String responseCode = (String) pair.getValue();
                statisticsOneRequest(requestName, responseCode);
            }
        } catch (Exception ex) {
            Logger.getLogger(FrejaEidPluginVisualizerGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setResultsPanels() {
        results.put("auth", frejaEIDPluginVsualizerGuiPanel.getAuthResults());
        results.put("sign", frejaEIDPluginVsualizerGuiPanel.getSignResults());
        results.put("opse", frejaEIDPluginVsualizerGuiPanel.getOpenSecureResults());
    }

}
