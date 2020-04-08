/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.internship.frejaeidjmeterplugin.jmeter.visualizer.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
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
    private final XYChart chart;
    XChartPanel chartPanel;

    public FrejaGraphVisualizerGui() {
        super();
        double[] initData = new double[1];
        initData[0] = 0;
        chart = QuickChart.getChart("Authentication Sampler Test", "Latency", "Count", "Failure",initData, initData);
        chartPanel = new XChartPanel(chart);
        chart.addSeries("Success", initData);
        //chart.addSeries("Latency", initData);
        latency = new CopyOnWriteArrayList<>();
        latency.add((double)0);
        successCount = new CopyOnWriteArrayList<>();
        successCount.add((double)0);
        failCount = new CopyOnWriteArrayList<>();
        failCount.add((double)0);
        count = new CopyOnWriteArrayList<>();
        count.add((double)0);
        
        setLayout(new BorderLayout(0, 5));
        setBorder(makeBorder());
        add(makeTitlePanel(), BorderLayout.NORTH);
        add(chartPanel, BorderLayout.CENTER);

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
        
        if(sampleResult.getContentType() == "auth"){
            //count represents the time-based X axis on the plot.
            //It is incremented every time a sampler finishes it's work
            latency.add((double)sampleResult.getLatency());
            count.add(count.get(count.size()-1) + 1);
            if(sampleResult.getResponseCode() == "FAILED"){
                /* failCount represents a list of total failed requests
                 * corresponding to the order of SampleReult arrival in the count  list.
                 * This list, along with successCount, is used as the Y axis*/
                
                //get the last element in the list
                double last = failCount.get(failCount.size()-1);
                //append the incremented value of the last element to the list.
                failCount.add(last + 1);
                //update the other graph as well, for consistency. No increments here.
                successCount.add(successCount.get(successCount.size() - 1));
                
            }else{
                //same deal with successCount as with failCount
                double last = successCount.get(successCount.size()-1);
                successCount.add(last + 1);
                failCount.add(failCount.get(failCount.size()-1));
            }
            //Finally, update the views.
            chart.updateXYSeries("Failure", count, failCount, null);
            chart.updateXYSeries("Success", count, successCount, null);
            //chart.updateXYSeries("Latency", count, latency, null);
            chartPanel.repaint();
        }else if(sampleResult.getContentType() == "sign"){
            
        }
        
    }

    @Override
    public void clearData() {
        latency = new CopyOnWriteArrayList<>();
        latency.add((double)0);
        successCount = new CopyOnWriteArrayList<>();
        successCount.add((double)0);
        failCount = new CopyOnWriteArrayList<>();
        failCount.add((double)0);
        count = new CopyOnWriteArrayList<>();
        count.add((double)0);
        chart.updateXYSeries("Failure", count, failCount, null);
        chart.updateXYSeries("Success", count, successCount, null);
        chartPanel.repaint();
    }

}
