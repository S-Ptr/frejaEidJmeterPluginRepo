/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.internship.frejaeidjmeterplugin.jmeter.visualizer.gui;

import java.awt.Color;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;

/**
 *
 * @author User
 */
public class GraphPanel {

    private XYChart chart;
    private XChartPanel chartPanel;
    List<Double> successCount;
    List<Double> failCount;
    List<Double> count;

    public GraphPanel(String title, String xAxisName, String yAxisName) {
        double[] initData = new double[1];
        initData[0] = 0;
        chart = QuickChart.getChart(title, xAxisName, yAxisName,"Failed", initData, initData);
        chart.addSeries("Delivered", initData, initData);
        chart.getSeriesMap().get("Failed").setLineColor(Color.RED);
        chart.getSeriesMap().get("Failed").setMarker(new org.knowm.xchart.style.markers.None());
        chart.getSeriesMap().get("Delivered").setLineColor(Color.green);
        chart.getSeriesMap().get("Delivered").setMarker(new org.knowm.xchart.style.markers.None());
        chartPanel = new XChartPanel(chart);
        successCount = new CopyOnWriteArrayList<>();
        successCount.add((double)0);
        failCount = new CopyOnWriteArrayList<>();
        failCount.add((double)0);
        count = new CopyOnWriteArrayList<>();
        count.add((double)0);
    }

    public XChartPanel getPanel() {
        return chartPanel;
    }

    /*public void updateSeries(String seriesName, List<? extends Number> xData, List<? extends Number> yData) {
        chart.updateXYSeries(seriesName, xData, yData, null);
    }

    public void updateSeries(String seriesName, double[] xData, double[] yData) {
        chart.updateXYSeries(seriesName, xData, yData, null);
    }*/

    public void increaseFailed() {
        count.add(count.get(count.size() - 1) + 1);
        //get the last element in the list
        double last = failCount.get(failCount.size() - 1);
        //append the incremented value of the last element to the list.
        failCount.add(last + 1);
        //update the other graph as well, for consistency. No increments here.
        successCount.add(successCount.get(successCount.size() - 1));
        chart.updateXYSeries("Failed", count, failCount, null);
        chart.updateXYSeries("Delivered", count, successCount, null);
        chartPanel.repaint();
    }

    public void increaseDelivered() {
        count.add(count.get(count.size() - 1) + 1);
        //same deal with successCount as with failCount
        double last = successCount.get(successCount.size() - 1);
        successCount.add(last + 1);
        failCount.add(failCount.get(failCount.size() - 1));
        chart.updateXYSeries("Failed", count, failCount, null);
        chart.updateXYSeries("Delivered", count, successCount, null);
        chartPanel.repaint();
    }
    
    public void clear(){
        successCount = new CopyOnWriteArrayList<>();
        successCount.add((double)0);
        failCount = new CopyOnWriteArrayList<>();
        failCount.add((double)0);
        count = new CopyOnWriteArrayList<>();
        count.add((double)0);
        chart.updateXYSeries("Failed", count, failCount, null);
        chart.updateXYSeries("Delivered", count, successCount, null);
        chartPanel.repaint();
    }

}
