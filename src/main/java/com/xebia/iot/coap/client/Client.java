package com.xebia.iot.coap.client;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;

public class Client {

    private String coapUrl;
    private CoapClient client;
    private CoapResponse coapResponse;

    public Client(String coapUrl){
        this.coapUrl = coapUrl;
        this.client = new CoapClient(this.coapUrl);
    }

    public String doPost(String payload) {
        CoapResponse coapResponse = client.post(payload, MediaTypeRegistry.TEXT_PLAIN);
        return coapResponse.getResponseText();
    }

    public String getGetResponseText() {
        return this.coapResponse.getResponseText();
    }

    public void doGet() {
        this.coapResponse = this.client.get();
    }

    public int getGetCode() {
        return this.coapResponse.getCode().value;
    }

    public boolean isGetSuccessful() {
        return this.coapResponse.isSuccess();
    }
}