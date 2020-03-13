/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.internship.frejaeidjmeterplugin.jmeter.main;

import com.internship.frejaeidjmeterplugin.jmeter.gui.AuthSamplerGui;
import javax.swing.JFrame;

/**
 *
 * @author Ivan
 */
public class Start {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setBounds(0, 0, 400, 400);
        frame.setTitle("FrejaEidJMeterPlugin");
        frame.add(new AuthSamplerGui());
        frame.setVisible(true);
    }
}
