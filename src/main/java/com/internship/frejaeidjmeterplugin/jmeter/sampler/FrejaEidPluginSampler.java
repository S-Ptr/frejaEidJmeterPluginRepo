package com.internship.frejaeidjmeterplugin.jmeter.sampler;

import com.internship.frejaeidjmeterplugin.jmeter.sampler.gui.FrejaEidPluginGui;
import com.verisec.frejaeid.client.exceptions.FrejaEidClientInternalException;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jmeter.samplers.AbstractSampler;
import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;

public class FrejaEidPluginSampler extends AbstractSampler {

    private final AuthSampler authSampler;
    private final SignSampler signSampler;
    private final MobileClientSampler mobileClientSampler;
    private final HashMap<String, GenericSampler> samplerMap;
    private long groupID;
    private static HashMap<Long, BufferedReader> fileMap;

    public FrejaEidPluginSampler() throws FrejaEidClientInternalException, Exception {
        authSampler = new AuthSampler();
        signSampler = new SignSampler();
        mobileClientSampler = new MobileClientSampler();
        samplerMap = new HashMap<>();
        addSamplers();
        fileMap = new HashMap<>();
    }

    public String getEmail() {
        return getPropertyAsString("email");
    }

    public void setEmail(String email) {
        setProperty("email", email);
    }

    public void setRequests(String requests) {
        setProperty("requests", requests);
    }

    public String getEmailFilePath() {
        return getPropertyAsString("emailFilePath");
    }

    public void setEmailFilePath(String path) {
        setProperty("emailFilePath", path);
    }

    public String getEmailInputType() {
        return getPropertyAsString("emailInputType");
    }

    public void setEmailInputType(String type) {
        setProperty("emailInputType", type);
    }

    private String[] getRequestsProperty() {
        if (getProperty("requests").toString().equals("")) {
            return new String[0];
        }
        return getProperty("requests").toString().split(" ");
    }

    public long getGroupID() {
        return groupID;
    }

    public void setGroupID(long groupID) {
        this.groupID = groupID;
    }
    
    @Override
    public SampleResult sample(Entry entry) {
        SampleResult sampleResult = new SampleResult();
        String[] requests = getRequestsProperty();
        String email = null;
        if (getEmailInputType().equals("SINGLE")) {
            email = getEmail();
        } else {
            try {
                email = FrejaEidPluginSampler.getLineFromFile(groupID,getEmailFilePath());
                setEmail(email);
            } catch (IOException ex) {
                sampleResult.latencyEnd();
                setSampleResult(sampleResult, "auth", false, "Freja eID Response: FAILED", "FAILED",
                        ex.getClass().getSimpleName());
                Logger.getLogger(AuthSampler.class.getName()).log(Level.SEVERE, null, ex);
                return sampleResult;
            }
        }
        switch (requests.length) {
            case RequestNumber.NO_REQUEST:
                sampleResult.setSampleLabel("noAction");
                break;
            case RequestNumber.ONE_REQUEST:
                GenericSampler currentSampler = samplerMap.get(requests[0]);
                sampleResult = currentSampler.sample(getEmail());
                break;
            default:
                sampleResult = processAllRequests(requests);
        }
        return sampleResult;
    }

    private byte[] getDataAsByteArray(HashMap<String, SampleResult> response) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out;
        try {
            out = new ObjectOutputStream(baos);
            out.writeObject(response);
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(FrejaEidPluginSampler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                baos.close();
            } catch (IOException ex) {
                Logger.getLogger(FrejaEidPluginSampler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return baos.toByteArray();
    }

    private void addSamplers() {
        samplerMap.put("auth", authSampler);
        samplerMap.put("sign", signSampler);
        samplerMap.put("mobile", mobileClientSampler);
    }

    private void setSampleResult(SampleResult sampleResult, String contentType, boolean isSuccessful, String sampleLabel, String responseCode, String responseMessage) {
        sampleResult.setSuccessful(isSuccessful);
        sampleResult.setSampleLabel(sampleLabel);
        sampleResult.setResponseCode(responseCode);
        sampleResult.setResponseMessage(responseMessage);
        sampleResult.setContentType(contentType);
    }
    
    private SampleResult processAllRequests(String[] requests) {
        SampleResult sampleResult = new SampleResult();
        HashMap<String, SampleResult> response = new HashMap<>();
        for (String request : requests) {
            GenericSampler genericSampler = samplerMap.get(request);
            SampleResult currentSamplerResult = genericSampler.sample(getEmail());
            response.put(genericSampler.getSamplerName(), currentSamplerResult);
        }
        sampleResult.setResponseData(getDataAsByteArray(response));
        return sampleResult;
    }
    
    public static synchronized String getLineFromFile(long groupID, String path) throws FileNotFoundException, IOException{
        if(!fileMap.containsKey(groupID)){
            fileMap.put(groupID, new BufferedReader(new FileReader(path)));
        }
        String line = fileMap.get(groupID).readLine();
        if(line == null){
            fileMap.replace(groupID, new BufferedReader(new FileReader(path)));
            line = fileMap.get(groupID).readLine();
        }
        return line;
    }

}
