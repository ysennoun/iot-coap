package com.xebia.iot.coap.server.resources;

import com.xebia.iot.persister.Persister;
import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.server.resources.CoapExchange;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class VolumeResource extends CoapResource {

    private static final String CHARSET_NAME = "UTF-8";
    private ArrayList<Persister> persisters;

    public VolumeResource(ArrayList<Persister> persisters) {
        super("volume");
        getAttributes().setTitle("Volume Resource");
        this.persisters = persisters;
    }

    @Override
    public void handlePOST(CoapExchange exchange) {
        try {
            String data = new String(exchange.getRequestPayload(), CHARSET_NAME);
            exchange.accept();
            exchange.respond(CoAP.ResponseCode.CONTENT, "ok");
            for(Persister persister : persisters)
                persister.persiste(data);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}