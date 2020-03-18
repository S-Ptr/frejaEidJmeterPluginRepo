/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.internship.frejaeidjmeterplugin.jmeter.main;

import com.internship.frejaeidjmeterplugin.jmeter.gui.AuthSamplerGui;
import com.internship.frejaeidjmeterplugin.jmeter.visualizer.gui.AuthVisualizerGui;
import javax.swing.JFrame;

/**
 *
 * @author PC
 */
public class Main {
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setBounds(0, 0, 400, 400);
        f.add( new AuthSamplerGui());
        f.setVisible(true);
    }
}
