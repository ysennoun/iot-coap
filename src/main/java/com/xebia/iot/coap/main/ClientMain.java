package com.xebia.iot.coap.main;

import com.xebia.iot.coap.Client;

import java.util.Scanner;

public class ClientMain {

    private static String coapServerUrl;
    private static String coapMethod;

    public static void main(String[] args) {

        if(args.length != 2)
            throw new IllegalArgumentException("Expected two arguments: <CoAPServerUrl> <CoAPMethod>");
        coapServerUrl = args[0];
        coapMethod = args[1];

        Client coapClient = new Client(coapServerUrl);
        String payload;
        int code;
        switch(coapMethod) {
            case "GET" :
                coapClient.doGet();
                code = coapClient.getCode();
                payload = coapClient.getResponseText();
                System.out.println("Resource '" + coapServerUrl + "'");
                System.out.println("Method '" + coapMethod + "'");
                System.out.println("CoAP server code response is : " + code);
                System.out.println("CoAP server response is : " + payload);
                break;
            case "POST":
                System.out.println("Enter payload to send: ");
                Scanner scanner = new Scanner(System.in);
                payload = scanner.nextLine();
                coapClient.doPost(payload);
                String response = coapClient.getResponseText();
                System.out.println("Response from CoAP server : " + response);
                break;
            default:
                System.err.println("ERROR: Unknwon method (" + coapMethod + ")");
                break;
        }
    }
}
