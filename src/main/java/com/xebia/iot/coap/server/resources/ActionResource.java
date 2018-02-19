package com.xebia.iot.coap.server.resources;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;

import java.util.Random;

public class ActionResource extends CoapResource {

    public ActionResource() {
        super("action");
        getAttributes().setTitle("Action Resource");
    }

    @Override
    public void handleGET(CoapExchange exchange) {

        Random r = new Random();
        String payload = String.valueOf(r.nextInt(50));
        //construct a json from payload
        exchange.respond(CoAP.ResponseCode.CONTENT, payload);
    }
}
