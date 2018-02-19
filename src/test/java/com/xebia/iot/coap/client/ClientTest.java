package com.xebia.iot.coap.client;

import junit.framework.TestCase;

public class ClientTest extends TestCase {

    private static String coapRemoteServerUrl = "coap://californium.eclipse.org:5683";
    private static String coapLocalServerUrl = "coap://127.0.0.1:5683";
    private static String helloWorldMessage = "Hello IoT world, this is a test from a CoAP server";

    public void testRemoteGet() {
        Client clientTest = new Client(coapRemoteServerUrl);
        clientTest.doGet();
        assertTrue(clientTest.isGetSuccessful());
    }

    public void testLocalHelloWorldGet() {
        Client clientTest = new Client(coapLocalServerUrl + "/helloWorld");
        clientTest.doGet();
        assertEquals(helloWorldMessage, clientTest.getGetResponseText());
        assertTrue(clientTest.isGetSuccessful());
    }
}
