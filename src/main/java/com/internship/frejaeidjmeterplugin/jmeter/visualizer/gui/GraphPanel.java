package com.internship.frejaeidjmeterplugin.jmeter.visualizer.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.demo.charts.ExampleChart;

public class GraphPanel extends JPanel implements ExampleChart<PieChart>{
        //JPanel implements ExampleChart<PieChart>{
// extends javax.swing.JPanel 
    public GraphPanel() {
        initComponents();
       // showChart();
    }

    
    private void showChart() {
         //   ExampleChart<PieChart> exampleChart = new PieChart02();
        PieChart chart = getChart();
       // JPanel chartPanel = new XChartPanel(chart);
        this.setLayout(new java.awt.BorderLayout());
        XChartPanel x = new XChartPanel(chart);
        this.add(x,BorderLayout.CENTER);
        this.setSize(200,200);
        this.validate();
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
            .addGap(0, 300, Short.MAX_VALUE)
        );
 }// </editor-fold>//GEN-END:initComponents

    
    @Override
    public PieChart getChart() {
         // Create Chart
       PieChart chart = new PieChartBuilder().width(400).height(400).title(getClass().getSimpleName()).build();

        // Customize Chart
        Color[] sliceColors = new Color[]{new Color(224, 68, 14), new Color(230, 105, 62), new Color(236, 143, 110)};
        chart.getStyler().setSeriesColors(sliceColors);

        // Series
        chart.addSeries("Gold", 24);
     //   chart.addSeries("Silver", 21);
     //   chart.addSeries("Platinum", 39);
        chart.addSeries("Copper", 17);
        chart.addSeries("Zinc", 40);

        return chart;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
