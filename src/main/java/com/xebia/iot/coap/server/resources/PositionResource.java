package com.xebia.iot.coap.server.resources;

import com.xebia.iot.coap.persister.Persister;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class PositionResource extends CoapResource {

    private static ArrayList<Persister> persisters;

    public PositionResource(ArrayList<Persister> persisters) {
        super("position");
        getAttributes().setTitle(" Position Resource");
        this.persisters = persisters;
    }

    @Override
    public void handlePOST(CoapExchange exchange) {
        try {
            String data = new String(exchange.getRequestPayload(), "UTF-8");
            exchange.accept();
            for(Persister persister : persisters)
                persister.persiste(data);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}