package com.internship.frejaeidjmeterplugin.jmeter.main;

import com.internship.frejaeidjmeterplugin.jmeter.gui.AuthSamplerGui;
import com.internship.frejaeidjmeterplugin.jmeter.gui.actions.AuthSamplerGuiAction;
import com.internship.frejaeidjmeterplugin.jmeter.sample.AuthSample;
import javax.swing.JFrame;


public class Start {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setBounds(0, 0, 400, 400);
        frame.setTitle("FrejaEidJMeterPlugin");
        AuthSamplerGui authSamplerGui = new AuthSamplerGui();
        AuthSample auth = new AuthSample();
        AuthSamplerGuiAction authSamplerGuiAction = new AuthSamplerGuiAction(auth, authSamplerGui);
        frame.add(authSamplerGui);
        frame.setVisible(true);
    }
}
