package com.internship.frejaeidjmeterplugin.jmeter.sampler;

import org.apache.jmeter.samplers.SampleResult;

public interface GenericSampler {
    SampleResult sample (String entry);
    String getSamplerName();
}
