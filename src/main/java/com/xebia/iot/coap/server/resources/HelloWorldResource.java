package com.xebia.iot.coap.server.resources;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;

public class HelloWorldResource extends CoapResource {

    public HelloWorldResource() {
        super("helloWorld");
        getAttributes().setTitle("helloWorld Resource");
    }

    @Override
    public void handleGET(CoapExchange exchange) {
        exchange.respond(CoAP.ResponseCode.CONTENT,
                "Hello IoT world, this is a test from a CoAP server");
    }
}