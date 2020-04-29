package com.internship.frejaeidjmeterplugin.jmeter.visualizer.gui;

import com.internship.frejaeidjmeterplugin.util.DataService;
import java.awt.BorderLayout;
import java.io.ByteArrayInputStream;
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
    private static final String ERROR_MESSAGE = "Please choose request/s";
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
        setError("");
        HashMap<String, SampleResult> responseData = DataService.mapResponseData(sampleResult);
        if (responseData == null) {
            if (!sampleResult.getSampleLabel().equals("noAction")) {
                statisticsOneRequest(sampleResult.getContentType(), sampleResult.getResponseCode());
            } else {
                setError(ERROR_MESSAGE);
            }
        } else {
            statsticsMoreRequests(sampleResult);
        }
    }

    @Override
    public void clearData() {
        frejaEIDPluginVsualizerGuiPanel.getAuthResults().clearGui();
        frejaEIDPluginVsualizerGuiPanel.getSignResults().clearGui();
        frejaEIDPluginVsualizerGuiPanel.getOpenSecureResults().clearGui();
    }

    private void setError(String error) {
        for (Map.Entry pair : results.entrySet()) {
            ResultPanel result = (ResultPanel) pair.getValue();
            result.setError(error);
        }
    }

    private void statisticsOneRequest(String request, String responseCode) {
        ResultPanel result = results.get(request);
        if (responseCode.equals(RESPONSE_CODE)) {
            result.increaseFailed();
        } else {
            result.increaseDelivered();
        }
    }

    private void statsticsMoreRequests(SampleResult sampleResult) {
        byte[] responseDataByte = sampleResult.getResponseData();
        ByteArrayInputStream bais = new ByteArrayInputStream(responseDataByte);
        try {
            ObjectInputStream in = new ObjectInputStream(bais);
            HashMap<String, SampleResult> responseData = (HashMap<String, SampleResult>) in.readObject();

            for (Map.Entry pair : responseData.entrySet()) {
                String requestName = (String) pair.getKey();
                String responseCode = ((SampleResult) pair.getValue()).getResponseCode();
                statisticsOneRequest(requestName, responseCode);
            }
        } catch (Exception ex) {
            Logger.getLogger(FrejaEidPluginVisualizerGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setResultsPanels() {
        results.put("auth", frejaEIDPluginVsualizerGuiPanel.getAuthResults());
        results.put("sign", frejaEIDPluginVsualizerGuiPanel.getSignResults());
        results.put("mobile", frejaEIDPluginVsualizerGuiPanel.getOpenSecureResults());
    }
    
   

}
