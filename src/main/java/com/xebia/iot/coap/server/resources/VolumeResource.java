package com.xebia.iot.coap.server.resources;

import com.xebia.iot.coap.persister.Persister;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class VolumeResource extends CoapResource {

    private ArrayList<Persister> persisters;

    public VolumeResource(ArrayList<Persister> persisters) {
        super("volume");
        getAttributes().setTitle("Volume Resource");
        this.persisters = persisters;
    }

    @Override
    public void handlePOST(CoapExchange exchange) {
        try {
            //TODO charsetName maven ?
            String data = new String(exchange.getRequestPayload(), "UTF-8");
            exchange.accept();
            System.out.println("persiste ");
            exchange.respond(CoAP.ResponseCode.CONTENT, "ok");
            for(Persister persister : persisters)
                persister.persiste(data);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}