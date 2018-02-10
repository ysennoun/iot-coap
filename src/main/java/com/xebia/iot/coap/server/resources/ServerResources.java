package com.xebia.iot.coap.server.resources;

import com.xebia.iot.coap.persister.Persister;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;

public class ServerResources extends CoapResource {

    private static String resourceName;
    private static ArrayList<Persister> persisters;

    public ServerResources(String resourceName, ArrayList<Persister> persisters) {
        super(resourceName);
        this.resourceName = resourceName;
        getAttributes().setTitle(this.resourceName + " Resource");
        this.persisters = persisters;
    }

    @Override
    public void handleGET(CoapExchange exchange) {

        String payload;
        switch(this.resourceName) {
            case "helloWorld" :
                payload = "Hello IoT world, this is a test from a CoAP server";
                break;
            case "action" :
                Random r = new Random();
                payload = String.valueOf(r.nextInt(50));
                break;
            default:
                payload = "ERROR";
                break;
        }
        exchange.respond(CoAP.ResponseCode.CONTENT, payload);
    }

    @Override
    public void handlePOST(CoapExchange exchange) {
        try {
            String data = new String(exchange.getRequestPayload(), "UTF-8");
            exchange.accept();
            exchange.respond(CoAP.ResponseCode.CONTENT, "ok");
            for(Persister persister : persisters)
                persister.persiste(data);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}