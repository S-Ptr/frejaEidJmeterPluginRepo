package com.internship.frejaeidjmeterplugin.jmeter.http.impl;
import com.internship.frejaeidjmeterplugin.jmeter.http.HttpServiceApi;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

public class HttpService implements HttpServiceApi {

    private static final int DEFAULT_CONNECTION_TIMEOUT_IN_MILLISECONDS = 20000;
    private static final int DEFAULT_READ_TIMEOUT_IN_MILLISECONDS = 20000;
    private final HttpClient httpClient;

    public HttpService(String keystorePath, String keystorePass) throws Exception {
        HttpClientBuilder httpClientBuilder = HttpClients.custom().useSystemProperties();
        SSLContext sslContext = createSSLContext(keystorePath, keystorePass);

        httpClientBuilder.setSSLContext(sslContext);

        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(DEFAULT_CONNECTION_TIMEOUT_IN_MILLISECONDS).
                setConnectionRequestTimeout(DEFAULT_CONNECTION_TIMEOUT_IN_MILLISECONDS).setSocketTimeout(DEFAULT_READ_TIMEOUT_IN_MILLISECONDS).build();
        httpClientBuilder.setDefaultRequestConfig(requestConfig);

        httpClient = httpClientBuilder.build();
    }

    private SSLContext createSSLContext(String keystorePath, String keystorePass) throws Exception {
        try (InputStream keyStoreStream = new FileInputStream(keystorePath);) {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(keyStoreStream, keystorePass.toCharArray());
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keyStore, keystorePass.toCharArray());
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(keyStore);
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(keyManagerFactory.getKeyManagers(), tmf.getTrustManagers(), null);
            return sslContext;
        } catch (Exception ex) {
            throw new Exception("Failed to create SSL context. ",ex);
        }
    }

    @Override
    public HttpResponse executeHttpRequest(HttpUriRequest httpUriRequest) throws IOException {
        return httpClient.execute(httpUriRequest);
    }
}
