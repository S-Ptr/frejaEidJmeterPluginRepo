package com.internship.frejaeidjmeterplugin.jmeter.visualizer.gui;

import com.internship.frejaeidjmeterplugin.jmeter.visualizer.gui.panel.FrejaEidPluginVisualizerGuiPanel;
import com.internship.frejaeidjmeterplugin.jmeter.visualizer.gui.panel.ResultPanel;
import com.internship.frejaeidjmeterplugin.jmeter.util.DataService;
import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.visualizers.gui.AbstractVisualizer;
import com.internship.frejaeidjmeterplugin.jmeter.visualizer.gui.panel.GenericPanel;

public class FrejaEidPluginVisualizerGui extends AbstractVisualizer {

    private final FrejaEidPluginVisualizerGuiPanel frejaEIDPluginVsualizerGuiPanel;
    private static final String ERROR_MESSAGE = "Please choose request/s";
    private final HashMap<String, GenericPanel> panelList;

    public FrejaEidPluginVisualizerGui() {
        super();
        frejaEIDPluginVsualizerGuiPanel = new FrejaEidPluginVisualizerGuiPanel();
        setLayout(new BorderLayout(0, 5));
        setBorder(makeBorder());
        add(makeTitlePanel(), BorderLayout.NORTH);
        add(frejaEIDPluginVsualizerGuiPanel, BorderLayout.CENTER);
        panelList = new HashMap<>();
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
                DataService.statisticsForOneRequest(sampleResult, panelList);
            } else {
                setError(ERROR_MESSAGE);
            }
        } else {
            DataService.statisticsForRequests(sampleResult, panelList);
        }
    }

    @Override
    public void clearData() {
        frejaEIDPluginVsualizerGuiPanel.getAuthResults().clear();
        frejaEIDPluginVsualizerGuiPanel.getSignResults().clear();
        frejaEIDPluginVsualizerGuiPanel.getOpenSecureResults().clear();
    }

    private void setError(String error) {
        for (Map.Entry pair : panelList.entrySet()) {
            ResultPanel result = (ResultPanel) pair.getValue();
            result.setError(error);
        }
    }

    private void setResultsPanels() {
        panelList.put("auth", frejaEIDPluginVsualizerGuiPanel.getAuthResults());
        panelList.put("sign", frejaEIDPluginVsualizerGuiPanel.getSignResults());
        panelList.put("mobile", frejaEIDPluginVsualizerGuiPanel.getOpenSecureResults());
    }

}
