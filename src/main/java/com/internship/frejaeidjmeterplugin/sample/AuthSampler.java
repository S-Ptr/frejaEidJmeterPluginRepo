/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.internship.frejaeidjmeterplugin.sample;

import org.apache.jmeter.samplers.AbstractSampler;
import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;

/**
 *
 * @author Stefan
 */
public class AuthSampler extends AbstractSampler{

    @Override
    public SampleResult sample(Entry entry) {
        SampleResult sr = new SampleResult();
        sr.setSampleLabel("is this working?");
        sr.setResponseCodeOK();
        sr.setResponseMessage("it is");
        System.out.println("wassup");
        return sr;
    }
    
}
