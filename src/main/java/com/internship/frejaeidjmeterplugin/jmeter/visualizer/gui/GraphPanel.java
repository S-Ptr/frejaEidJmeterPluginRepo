/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.internship.frejaeidjmeterplugin.jmeter.visualizer.gui;

import java.util.List;
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
    
    public GraphPanel(String title, String xAxisName, String yAxisName, String initSeriesName){
        double[] initData = new double[1];
        initData[0] = 0;
        chart = QuickChart.getChart(title, xAxisName, yAxisName, initSeriesName,initData, initData);
        chartPanel = new XChartPanel(chart);
    }
    
    public XChartPanel getPanel(){
        return chartPanel;
    }
    
    public void updateSeries(String seriesName, List<? extends Number> xData, List<? extends Number> yData){
        chart.updateXYSeries(seriesName, xData, yData, null);
    }
    
    public void updateSeries(String seriesName, double[] xData, double[] yData){
        chart.updateXYSeries(seriesName, xData, yData, null);
    }
    
    
    
}
