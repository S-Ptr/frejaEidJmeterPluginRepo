package com.internship.frejaeidjmeterplugin.jmeter.visualizer.gui;

import java.awt.Color;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;

public class FrejaEidResultsGraphPanel {
    private XYChart chart;
    private XChartPanel chartPanel;
    private List<Double> successCount;
    private List<Double> failCount;
    private List<Double> totalCount;
    
    private void resetLists(){
        successCount = new CopyOnWriteArrayList<>();
        successCount.add((double)0);
        failCount = new CopyOnWriteArrayList<>();
        failCount.add((double)0);
        totalCount = new CopyOnWriteArrayList<>();
        totalCount.add((double)0);
    }
    
    private void updateGraph(){
        chart.updateXYSeries("Failed", totalCount, failCount, null);
        chart.updateXYSeries("Delivered", totalCount, successCount, null);
    }

    private void chartStyle(Color failColor, Color successColor){
        chart.getSeriesMap().get("Failed").setLineColor(failColor);
        chart.getSeriesMap().get("Failed").setMarker(new org.knowm.xchart.style.markers.None());
        chart.getSeriesMap().get("Delivered").setLineColor(successColor);
        chart.getSeriesMap().get("Delivered").setMarker(new org.knowm.xchart.style.markers.None());
    }
    
    public FrejaEidResultsGraphPanel(String title, String xAxisName, String yAxisName) {
        double[] zeroArray = new double[1];
        zeroArray[0] = 0;
        chart = QuickChart.getChart(title, xAxisName, yAxisName,"Failed", zeroArray, zeroArray);
        chart.addSeries("Delivered", zeroArray, zeroArray);
        chartStyle(Color.RED, Color.green);
        chartPanel = new XChartPanel(chart);
        resetLists();
    }

    public XChartPanel getPanel() {
        return chartPanel;
    }

    public void increaseFailed() {
        double previousTotalCount = totalCount.get(totalCount.size() - 1);
        double previousFailCount = failCount.get(failCount.size() - 1);
        double previousSuccessCount = successCount.get(successCount.size() - 1);
        totalCount.add(previousTotalCount + 1);
        failCount.add(previousFailCount + 1);
        successCount.add(previousSuccessCount);
        updateGraph();
        chartPanel.repaint();
    }

    public void increaseDelivered() {
        double previousTotalCount = totalCount.get(totalCount.size() - 1);
        double previousFailCount = failCount.get(failCount.size() - 1);
        double previousSuccessCount = successCount.get(successCount.size() - 1);
        totalCount.add(previousTotalCount + 1);
        failCount.add(previousFailCount);
        successCount.add(previousSuccessCount + 1);
        updateGraph();
        chartPanel.repaint();
    }
    
    
    
    public void clear(){
        resetLists();
        updateGraph();
        chartPanel.repaint();
    }

}
