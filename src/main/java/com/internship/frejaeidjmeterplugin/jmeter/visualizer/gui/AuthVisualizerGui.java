
package com.internship.frejaeidjmeterplugin.jmeter.visualizer.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JTabbedPane;
import org.apache.jmeter.samplers.Clearable;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.visualizers.gui.AbstractVisualizer;


public class AuthVisualizerGui extends AbstractVisualizer
        implements Clearable {

    private ResultsPanel result;
    private GraphPanel graph;

    public AuthVisualizerGui() {
        initComponents();
        setForm();
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
    public void add(SampleResult sr) {
        if (sr.getResponseMessage().equals("DELIVERED")) {
            result.increaseDelivered();
        } else if (sr.getResponseMessage().equals("FAILED")) {
            result.increasetFailed();
        } else {
            result.increaseRejected();
        }
    }

    @Override
    public void clearData() {
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 320, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void setForm() {
        this.setLayout(new GridBagLayout());
        result = new ResultsPanel();
        graph = new GraphPanel();
        JTabbedPane jTP = new JTabbedPane();
        jTP.add("Results", result);
        jTP.add("Graph", graph);
        GridBagConstraints myConstraints = new GridBagConstraints();
        myConstraints.ipadx = 50;
        myConstraints.ipady = 50;
        this.add(jTP, myConstraints);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
