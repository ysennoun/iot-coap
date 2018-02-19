package com.xebia.iot.coap;

import com.xebia.iot.coap.server.Server;
import com.xebia.iot.coap.server.resources.ActionResource;
import com.xebia.iot.coap.server.resources.HelloWorldResource;
import com.xebia.iot.coap.server.resources.PositionResource;
import com.xebia.iot.coap.server.resources.VolumeResource;
import com.xebia.iot.persister.Persister;
import com.xebia.iot.persister.console.ConsolePersister;
import junit.framework.TestCase;
import org.eclipse.californium.core.CoapResource;

import java.net.SocketException;
import java.util.ArrayList;

public class ClientServerIntegrationTest extends TestCase {

    private static Server server;
    private static String coapRemoteServerUrl = "coap://californium.eclipse.org:5683";
    private static String coapLocalServerUlr = "coap://localhost:5683/";
    private static String subResource = "data";
    private static String helloWorldResource = "helloWorld";
    private static String actionResource = "action";
    private static String positionResource = "position";
    private static String volumeResource = "volume";

    public void runServer() {
        ArrayList<Persister> persisters = new ArrayList<Persister>();
        persisters.add(new ConsolePersister());
        try {
            server = new Server();
            server.add(new HelloWorldResource());
            server.add(new ActionResource());
            CoapResource path = new CoapResource(subResource);
            path.add(new PositionResource(persisters));
            path.add(new VolumeResource(persisters));
            server.add(path);
            server.start();
        } catch (SocketException e) {
            System.err.println("Failed to initialize server: " + e.getMessage());
        }
    }
    public void stopServer() {
        System.out.println("stopServer ");
        server.stop();
        System.out.println("stopped");
    }

    public void testRemoteGet() {
        Client clientTest = new Client(coapRemoteServerUrl);
        clientTest.doGet();
        assertTrue(clientTest.isSuccessful());
    }

    public void testHelloWorldResource() {
        runServer();
        Client clientTest = new Client(coapLocalServerUlr + helloWorldResource);
        clientTest.doGet();
        assertTrue(clientTest.isSuccessful());
        assertEquals("Hello IoT world, this is a test from a CoAP server",
                clientTest.getResponseText() );
        stopServer();
    }

    public void testActionResource() {
        runServer();
        Client clientTest = new Client(coapLocalServerUlr
                + actionResource);
        clientTest.doGet();
        assertTrue(clientTest.isSuccessful());
        stopServer();
    }

    public void testPositionResource() {
        runServer();
        Client clientTest = new Client(coapLocalServerUlr
                + subResource
                + "/" + positionResource);
        clientTest.doPost("message to send");
        assertTrue(clientTest.isSuccessful());
        stopServer();
    }

    public void testVolumeResource() {
        runServer();
        Client clientTest = new Client(coapLocalServerUlr
                + subResource
                + "/" + volumeResource);
        clientTest.doPost("message to send");
        stopServer();
        assertTrue(clientTest.isSuccessful());
    }
}
