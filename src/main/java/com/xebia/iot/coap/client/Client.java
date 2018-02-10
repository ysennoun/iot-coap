package com.xebia.iot.coap.client;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;

public class Client {

    private static String coapUrl;
    private static CoapClient client;

    public Client(String coapUrl){
        this.coapUrl = coapUrl;
        this.client = new CoapClient(this.coapUrl);
    }

    public String doPost(String payload) {
        CoapResponse coapResponse = client.post(payload, MediaTypeRegistry.TEXT_PLAIN);
        return coapResponse.getResponseText();
    }

    public String doGet() {
        return client.get().getResponseText();
    }
}
