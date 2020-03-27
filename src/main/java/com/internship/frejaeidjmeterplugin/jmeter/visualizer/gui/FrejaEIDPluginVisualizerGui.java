package com.internship.frejaeidjmeterplugin.jmeter.visualizer.gui;

import java.awt.BorderLayout;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.visualizers.gui.AbstractVisualizer;

public class FrejaEIDPluginVisualizerGui extends AbstractVisualizer {

    private final FrejaEIDPluginVisualizerGuiPanel panel;

    public FrejaEIDPluginVisualizerGui() {
        super();
        panel = new FrejaEIDPluginVisualizerGuiPanel();
        setLayout(new BorderLayout(0, 5));
        setBorder(makeBorder());
        add(makeTitlePanel(), BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
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
        panel.getAuthResults().setError("");
        panel.getSignResults().setError("");
        switch (sampleResult.getContentType()) {
            case "auth":
                authStatistics(sampleResult.getSampleLabel());
                break;
            case "sign":
                signStatistics(sampleResult.getSampleLabel());
                break;
            case "both":
                bothStatstics(sampleResult);
                break;
            default:
                panel.getAuthResults().setError("Please choose request");
                panel.getSignResults().setError("Please choose request");
                break;
        }
    }

    @Override
    public void clearData() {
    }

    private void authStatistics(String label) {
        if (label.equals("FAILED")) {
            panel.getAuthResults().increasetFailed();
        } else {
            panel.getAuthResults().increaseDelivered();
        }
    }

    private void signStatistics(String label) {
        if (label.equals("FAILED")) {
            panel.getSignResults().increasetFailed();
        } else {
            panel.getSignResults().increaseDelivered();
        }
    }

    private void bothStatstics(SampleResult sampleResult) {
        authStatistics(sampleResult.getResponseCode());
        signStatistics(sampleResult.getResponseMessage());
    }

}
