package com.internship.frejaeidjmeterplugin.jmeter.visualizer.gui;

import java.awt.BorderLayout;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTabbedPane;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.visualizers.gui.AbstractVisualizer;

public class FrejaEidResultsGraphVisualizerGui extends AbstractVisualizer {

    private FrejaEidResultsGraphPanel authResults;
    private FrejaEidResultsGraphPanel signResults;
    private FrejaEidResultsGraphPanel openSecureConnectionResults;
    private HashMap<String, FrejaEidResultsGraphPanel> panelList;
    private static final String RESPONSE_CODE = "FAILED";

    public FrejaEidResultsGraphVisualizerGui() {
        super();
        panelList = new HashMap<>();
        setLayout(new BorderLayout(0, 5));
        setBorder(makeBorder());
        add(makeTitlePanel(), BorderLayout.NORTH);
        initPanels();
        JTabbedPane jTP = new JTabbedPane();
        jTP.add("Auth", authResults.getPanel());
        jTP.add("Sign", signResults.getPanel());
        jTP.add("Open secure connection", openSecureConnectionResults.getPanel());
        add(jTP, BorderLayout.CENTER);
    }

    private void initPanels() {
        authResults = new FrejaEidResultsGraphPanel("Authentication Sampler Results", "Number", "Count");
        signResults = new FrejaEidResultsGraphPanel("Sign Sampler Results", "Number", "Count");
        openSecureConnectionResults = new FrejaEidResultsGraphPanel("Open Secure Connection Results", "Number", "Count");
        panelList.put("auth", authResults);
        panelList.put("sign", signResults);
        panelList.put("mobile", openSecureConnectionResults);
    }

    @Override
    public String getStaticLabel() {
        return "Freja eID Results Graph";
    }

    @Override
    public String getLabelResource() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void add(SampleResult sampleResult) {

        HashMap<String, SampleResult> responseData = getResponseData(sampleResult);
        if (responseData == null) {
            if (!sampleResult.getSampleLabel().equals("noAction")) {
                statisticsForOneRequest(sampleResult.getContentType(), sampleResult.getResponseCode());
            } else {
                return;
            }
        } else {
            statisticsForRequests(sampleResult);
        }
    }

    private HashMap<String, SampleResult> getResponseData(SampleResult sampleResult) {
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

    private void statisticsForOneRequest(String request, String responseCode) {
        FrejaEidResultsGraphPanel result = panelList.get(request);
        if (responseCode.equals(RESPONSE_CODE)) {
            result.increaseFailed();
        } else {
            result.increaseDelivered();
        }
    }

    private void statisticsForRequests(SampleResult sampleResult) {
        byte[] responseDataByte = sampleResult.getResponseData();
        ByteArrayInputStream bais = new ByteArrayInputStream(responseDataByte);
        try {
            ObjectInputStream in = new ObjectInputStream(bais);
            HashMap<String, SampleResult> responseData = (HashMap<String, SampleResult>) in.readObject();

            for (Map.Entry pair : responseData.entrySet()) {
                String requestName = (String) pair.getKey();
                String responseCode = ((SampleResult) pair.getValue()).getResponseCode();
                statisticsForOneRequest(requestName, responseCode);
            }
        } catch (Exception ex) {
            Logger.getLogger(FrejaEidPluginVisualizerGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void clearData() {
        for (FrejaEidResultsGraphPanel next : panelList.values()) {
            next.clear();
        }
    }

}
