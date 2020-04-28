package com.internship.frejaeidjmeterplugin.jmeter.visualizer.gui;

import com.internship.frejaeidjmeterplugin.util.DataService;
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

public class FrejaEidLatencyVisualizerGui extends AbstractVisualizer{

    private FrejaEidLatencyGraphPanel authResults;
    private FrejaEidLatencyGraphPanel signResults;
    private FrejaEidLatencyGraphPanel openSecureConnectionResults;
    private HashMap<String, FrejaEidLatencyGraphPanel> panelList;
    private static final String RESPONSE_CODE = "FAILED";

    public FrejaEidLatencyVisualizerGui() {
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
        authResults = new FrejaEidLatencyGraphPanel("Authentication Sampler Latency", "Latency[ms]", "Count");
        signResults = new FrejaEidLatencyGraphPanel("Sign Sampler Latency", "Latency[ms]", "Count");
        openSecureConnectionResults = new FrejaEidLatencyGraphPanel("Open Secure Connection Latency", "Latency[ms]", "Count");
        panelList.put("auth", authResults);
        panelList.put("sign", signResults);
        panelList.put("mobile", openSecureConnectionResults);
    }

    @Override
    public String getStaticLabel() {
        return "Freja eID Latency Graph";
    }

    @Override
    public String getLabelResource() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void add(SampleResult sampleResult) {

        HashMap<String, SampleResult> responseData = DataService.mapResponseData(sampleResult);
        if (responseData == null) {
            if (!sampleResult.getSampleLabel().equals("noAction")) {
                statisticsForOneRequest(sampleResult.getContentType(), sampleResult);
            } else {
                return;
            }
        } else {
            statisticsForRequests(sampleResult);
        }
    }

    private void statisticsForOneRequest(String request, SampleResult result) {
        FrejaEidLatencyGraphPanel panel = panelList.get(request);
        if (!result.getResponseCode().equals(RESPONSE_CODE)) {
            panel.addLatency((double)result.getLatency());
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
                SampleResult result = (SampleResult) pair.getValue();
                statisticsForOneRequest(requestName, result);
            }
        } catch (Exception ex) {
            Logger.getLogger(FrejaEidPluginVisualizerGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void clearData() {
        for (FrejaEidLatencyGraphPanel next : panelList.values()) {
            next.clear();
        }
    }
}
