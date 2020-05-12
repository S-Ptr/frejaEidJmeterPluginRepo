package com.internship.frejaeidjmeterplugin.jmeter.util;

import com.internship.frejaeidjmeterplugin.jmeter.visualizer.gui.FrejaEidPluginVisualizerGui;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jmeter.samplers.SampleResult;
import com.internship.frejaeidjmeterplugin.jmeter.visualizer.gui.panel.GenericPanel;

public class DataService {

    private static final String RESPONSE_CODE = "FAILED";

    public static HashMap<String, SampleResult> mapResponseData(SampleResult sampleResult) {
        byte[] responseDataByte = sampleResult.getResponseData();
        HashMap<String, SampleResult> responseData = null;
        ByteArrayInputStream bais = new ByteArrayInputStream(responseDataByte);
        ObjectInputStream in;
        try {
            in = new ObjectInputStream(bais);
            responseData = (HashMap<String, SampleResult>) in.readObject();
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

    public static void statisticsForOneRequest(SampleResult sampleResult, HashMap<String, GenericPanel> panelList) {
        GenericPanel result = panelList.get(sampleResult.getSampleLabel());
        if (sampleResult.getResponseCode().equals(RESPONSE_CODE)) {
            result.increaseFailed();
        } else {
            result.increaseDelivered();
        }
    }

    public static void statisticsForRequests(SampleResult sampleResult, HashMap<String, GenericPanel> panelList) {
        byte[] responseDataByte = sampleResult.getResponseData();
        ByteArrayInputStream bais = new ByteArrayInputStream(responseDataByte);
        try {
            ObjectInputStream in = new ObjectInputStream(bais);
            HashMap<String, SampleResult> responseData = (HashMap<String, SampleResult>) in.readObject();

            for (Map.Entry pair : responseData.entrySet()) {
                String requestName = (String) pair.getKey();
                SampleResult result = (SampleResult) pair.getValue();
                DataService.statisticsForOneRequest(result, panelList);
            }
        } catch (Exception ex) {
            Logger.getLogger(FrejaEidPluginVisualizerGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
