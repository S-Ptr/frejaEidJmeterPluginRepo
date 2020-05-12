package com.internship.frejaeidjmeterplugin.jmeter.visualizer.gui;

import com.internship.frejaeidjmeterplugin.jmeter.visualizer.gui.panel.FrejaEidResultsGraphPanel;
import com.internship.frejaeidjmeterplugin.jmeter.util.DataService;
import java.awt.BorderLayout;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTabbedPane;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.visualizers.gui.AbstractVisualizer;
import com.internship.frejaeidjmeterplugin.jmeter.visualizer.gui.panel.GenericPanel;

public class FrejaEidResultsGraphVisualizerGui extends AbstractVisualizer {

    private FrejaEidResultsGraphPanel authResults;
    private FrejaEidResultsGraphPanel signResults;
    private FrejaEidResultsGraphPanel openSecureConnectionResults;
    private HashMap<String, GenericPanel> panelList;


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
                DataService.statisticsForOneRequest(sampleResult, panelList);
            }
        } else {
            DataService.statisticsForRequests(sampleResult, panelList);
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

    @Override
    public void clearData() {
        for (GenericPanel next : panelList.values()) {
            next.clear();
        }
    }

}
