package com.xebia.iot.coap.server.resources;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;

import java.io.IOException;
import java.util.Random;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

public class ActionResource extends CoapResource {

    public ActionResource() {
        super("action");
        getAttributes().setTitle("Action Resource");
    }

    @Override
    public void handleGET(CoapExchange exchange) {

        Random r = new Random();
        String payload = String.valueOf(r.nextInt(50));
        try {
            String jsonPayload = jsonBuilder()
                    .startObject()
                    .field("code", payload)
                    .endObject()
                    .toString();
            exchange.respond(CoAP.ResponseCode.CONTENT, jsonPayload);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
