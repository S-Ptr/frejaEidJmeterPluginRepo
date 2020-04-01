package com.internship.frejaeidjmeterplugin.jmeter.visualizer.gui;

import com.internship.frejaeidjmeterplugin.jmeter.sampler.gui.FrejaEidPlugnGui;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jmeter.samplers.SampleResult;

public class FrejaEidPluginVisualizer {

    private static final String RESPONSE_CODE = "FAILED";
    private final FrejaEidPlugnGui frejaEidPlugnGui;

    public FrejaEidPluginVisualizer(FrejaEidPlugnGui frejaEidPlugnGui) {
        this.frejaEidPlugnGui = frejaEidPlugnGui;
    }

    public void add(SampleResult sampleResult) {
        frejaEidPlugnGui.getFrejaEIDPluginGuiPanel().getAuthResults().setError("");
        frejaEidPlugnGui.getFrejaEIDPluginGuiPanel().getSignResults().setError("");
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
                frejaEidPlugnGui.getFrejaEIDPluginGuiPanel().getAuthResults().setError("Please choose request");
                frejaEidPlugnGui.getFrejaEIDPluginGuiPanel().getSignResults().setError("Please choose request");
                break;
        }
    }

    private void authStatistics(String responseCode) {
        if (responseCode.equals(RESPONSE_CODE)) {
            frejaEidPlugnGui.getFrejaEIDPluginGuiPanel().getAuthResults().increasetFailed();
        } else {
            frejaEidPlugnGui.getFrejaEIDPluginGuiPanel().getAuthResults().increaseDelivered();
        }
    }

    private void signStatistics(String responseCode) {
        if (responseCode.equals(RESPONSE_CODE)) {
            frejaEidPlugnGui.getFrejaEIDPluginGuiPanel().getSignResults().increasetFailed();
        } else {
            frejaEidPlugnGui.getFrejaEIDPluginGuiPanel().getSignResults().increaseDelivered();
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
            Logger.getLogger(FrejaEidPluginVisualizer.class.getName()).log(Level.SEVERE, null, ex);
        }
        authStatistics(responseDataString.get(0));
        signStatistics(responseDataString.get(1));
    }
}
