package com.internship.frejaeidjmeterplugin.jmeter.visualizer.gui;

import java.awt.BorderLayout;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.visualizers.gui.AbstractVisualizer;

public class FrejaEidPluginVisualizerGui extends AbstractVisualizer {

    private final FrejaEidPluginVisualizerGuiPanel frejaEIDPluginVsualizerGuiPanel;
    private static final String RESPONSE_CODE = "FAILED";

    public FrejaEidPluginVisualizerGui() {
        super();
        frejaEIDPluginVsualizerGuiPanel = new FrejaEidPluginVisualizerGuiPanel();
        setLayout(new BorderLayout(0, 5));
        setBorder(makeBorder());
        add(makeTitlePanel(), BorderLayout.NORTH);
        add(frejaEIDPluginVsualizerGuiPanel, BorderLayout.CENTER);
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
        frejaEIDPluginVsualizerGuiPanel.getAuthResults().setError("");
        frejaEIDPluginVsualizerGuiPanel.getSignResults().setError("");
        switch (sampleResult.getContentType()) {
            case "auth":
                authStatistics(sampleResult.getResponseCode());
                break;
            case "sign":
                signStatistics(sampleResult.getResponseCode());
                break;
            case "both":
                bothStatstics(sampleResult);
                break;
            default:
                frejaEIDPluginVsualizerGuiPanel.getAuthResults().setError("Please choose request");
                frejaEIDPluginVsualizerGuiPanel.getSignResults().setError("Please choose request");
                break;
        }
    }

    @Override
    public void clearData() {
    }

    private void authStatistics(String responseCode) {
        if (responseCode.equals(RESPONSE_CODE)) {
            frejaEIDPluginVsualizerGuiPanel.getAuthResults().increasetFailed();
        } else {
            frejaEIDPluginVsualizerGuiPanel.getAuthResults().increaseDelivered();
        }
    }

    private void signStatistics(String responseCode) {
        if (responseCode.equals(RESPONSE_CODE)) {
            frejaEIDPluginVsualizerGuiPanel.getSignResults().increasetFailed();
        } else {
            frejaEIDPluginVsualizerGuiPanel.getSignResults().increaseDelivered();
        }
    }

    private void bothStatstics(SampleResult sampleResult) {
        byte[] responseDataByte = sampleResult.getResponseData();
        ByteArrayInputStream bais = new ByteArrayInputStream(responseDataByte);
        DataInputStream in = new DataInputStream(bais);
        List<String> responseDataString = new LinkedList<>();
        try {
            while (in.available() > 0) {
                String element = in.readUTF();
                responseDataString.add(element);
            }
        } catch (IOException ex) {
            Logger.getLogger(FrejaEidPluginVisualizerGui.class.getName()).log(Level.SEVERE, null, ex);
        }
        authStatistics(responseDataString.get(0));
        signStatistics(responseDataString.get(1));
    }

}
