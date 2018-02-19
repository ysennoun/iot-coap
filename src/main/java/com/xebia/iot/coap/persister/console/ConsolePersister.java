package com.xebia.iot.coap.persister.console;

import com.xebia.iot.coap.persister.Persister;

public class ConsolePersister extends Persister {

    public void persiste(String data) {
        System.out.println(data);
    }
}
