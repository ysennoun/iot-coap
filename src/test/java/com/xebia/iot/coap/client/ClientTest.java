package com.xebia.iot.coap.client;

import junit.framework.TestCase;

public class ClientTest extends TestCase {

    private static String coapRemoteServerUrl = "coap://californium.eclipse.org:5683";

    public void testRemoteGet() {
        Client clientTest = new Client(coapRemoteServerUrl);
        clientTest.doGet();
        assertTrue(clientTest.isGetSuccessful());
    }
}
