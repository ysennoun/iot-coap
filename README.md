# iot-CoAP

This project enables to create a CoAP client and server.

## Get Jar

Execute the following command:

	mvn clean package

It will give you a jar name `iot-coap-1.0-SNAPSHOT-jar-with-dependencies.jar`.

## CoAP Client

To create a client, execute the following command:

	java -cp iot-coap-1.0-SNAPSHOT-jar-with-dependencies.jar com.xebia.iot.coap.main.ClientMain <serverUri> <method>

where `<serverUri>` is alike: `"coap://<coap-server-ip>:5683"`

## CoAP Server

To create a subscriber, execute the following command:

	java -cp iot-coap-1.0-SNAPSHOT-jar-with-dependencies.jar com.xebia.iot.coap.main.ServerMain <configFilePath>
