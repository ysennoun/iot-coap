package com.xebia.iot.coap;

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

    public void doPost(String payload) {
        this.coapResponse = client.post(payload, MediaTypeRegistry.TEXT_PLAIN);
    }

    public void doGet() {
        this.coapResponse = this.client.get();
    }

    public String getResponseText() {
        return this.coapResponse.getResponseText();
    }

    public int getCode() {
        return this.coapResponse.getCode().value;
    }

    public boolean isSuccessful() {
        return this.coapResponse.isSuccess();
    }
}