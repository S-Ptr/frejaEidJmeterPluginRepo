package com.internship.frejaeidjmeterplugin.util;

import com.internship.frejaeidjmeterplugin.jmeter.visualizer.gui.FrejaEidPluginVisualizerGui;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jmeter.samplers.SampleResult;

public class DataService {
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
    
}
