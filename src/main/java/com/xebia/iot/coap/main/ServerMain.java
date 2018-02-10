package com.xebia.iot.coap.main;

import com.xebia.iot.coap.persister.Persister;
import com.xebia.iot.coap.server.Server;
import com.xebia.iot.coap.server.resources.ServerResources;
import org.eclipse.californium.core.CoapResource;

import java.net.SocketException;
import java.util.ArrayList;

public class ServerMain {

    private static String helloWorldResource = "helloWorld";
    private static String actionResource = "action";
    private static String subResource = "data";
    private static String positionResource = "position";
    private static String volumeResource = "volume";
    private static String configurationFilePath;

    public static void main(String[] args) {

        if(args.length != 1)
            throw new IllegalArgumentException("Expected one argument: <Persistance_configuration_file_path>");
        configurationFilePath = args[0];
        InputArgumentsParser inputArgsParser = new InputArgumentsParser(configurationFilePath);
        ArrayList<Persister> persisters = inputArgsParser.getPersisters();

        try {
           Server server = new Server();
           server.add(new ServerResources(helloWorldResource, persisters));
            server.add(new ServerResources(actionResource, persisters));
           CoapResource path = new CoapResource(subResource);
           path.add(new ServerResources(positionResource, persisters));
           path.add(new ServerResources(volumeResource, persisters));
           server.add(path);
           server.start();
        } catch (SocketException e) {
           System.err.println("Failed to initialize server: " + e.getMessage());
        }
    }
}
