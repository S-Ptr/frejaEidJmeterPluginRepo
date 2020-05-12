package com.internship.frejaeidjmeterplugin.jmeter.http;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;


public interface HttpServiceApi {
    HttpResponse executeHttpRequest(HttpUriRequest httpUriRequest) throws IOException;
}
