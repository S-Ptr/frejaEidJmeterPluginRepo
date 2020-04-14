/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.internship.frejaeidjmeterplugin.jmeter.visualizer.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTabbedPane;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.visualizers.gui.AbstractVisualizer;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;

/**
 *
 * @author User
 */
public class FrejaGraphVisualizerGui extends AbstractVisualizer {
    List<Double> latency;
    List<Double> successCount;
    List<Double> failCount;
    List<Double> count;
    GraphPanel authResults;
    GraphPanel signResults;
    GraphPanel openSecureResults;
    HashMap<String, GraphPanel> panelList;
    private static final String RESPONSE_CODE = "FAILED";

    public FrejaGraphVisualizerGui() {
        super();
        panelList = new HashMap<>();
        setLayout(new BorderLayout(0, 5));
        setBorder(makeBorder());
        add(makeTitlePanel(), BorderLayout.NORTH);
        authResults = new GraphPanel("Authentication Sampler Results", "Number" ,"Count");
        signResults = new GraphPanel("Sign Sampler Results", "Number" ,"Count");
        openSecureResults = new GraphPanel("Open Secure Connection Results", "Number" ,"Count");
        panelList.put("auth", authResults);
        panelList.put("sign", signResults);
        panelList.put("mobile", openSecureResults);
        JTabbedPane jTP = new JTabbedPane();
        jTP.add("Auth", authResults.getPanel());
        jTP.add("Sign", signResults.getPanel());
        jTP.add("Open secure connection", openSecureResults.getPanel());
        add(jTP, BorderLayout.CENTER);

    }

    @Override
    public String getStaticLabel() {
        return "Freja eID Graph";
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
            } else {
                return;
            }
        } else {
            statisticsMoreRequests(sampleResult);
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
    
    private void statisticsOneRequest(String request, String responseCode) {
        GraphPanel result = panelList.get(request);
        if (responseCode.equals(RESPONSE_CODE)) {
            result.increaseFailed();
        } else {
            result.increaseDelivered();
        }
    }

    private void statisticsMoreRequests(SampleResult sampleResult) {
        byte[] responseDataByte = sampleResult.getResponseData();
        ByteArrayInputStream bais = new ByteArrayInputStream(responseDataByte);
        try {
            ObjectInputStream in = new ObjectInputStream(bais);
            HashMap<String, String> responseData = (HashMap<String, String>) in.readObject();

            for(Map.Entry pair : responseData.entrySet()) {
                String requestName = (String) pair.getKey();
                String responseCode = (String) pair.getValue();
                statisticsOneRequest(requestName, responseCode);
            }
        } catch (Exception ex) {
            Logger.getLogger(FrejaEidPluginVisualizerGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void clearData() {
        for(GraphPanel next : panelList.values()) {
            next.clear();
        }
    }

}
