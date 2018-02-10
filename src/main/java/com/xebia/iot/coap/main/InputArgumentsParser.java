package com.xebia.iot.coap.main;

import com.xebia.iot.coap.persister.Persister;
import com.xebia.iot.coap.persister.PersisterTypeInfo;
import com.xebia.iot.coap.persister.PersistersTypeInfo;
import com.xebia.iot.coap.persister.console.ConsolePersister;
import com.xebia.iot.coap.persister.elasticsearch.EsPersister;
import com.xebia.iot.coap.persister.elasticsearch.EsPersisterInfo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class InputArgumentsParser {

    private static String filePath;

    public InputArgumentsParser(String filePath) {
        this.filePath = filePath;
    }

    public static String getContentConfigurationFilePath(String filePath) {
        String content = null;
        try {
            content = new String ( Files.readAllBytes( Paths.get(filePath) ) );
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return content;
    }

    public ArrayList<Persister> getPersisters() {
        String content = getContentConfigurationFilePath(filePath);
        PersistersTypeInfo persitersTypeInfo = PersistersTypeInfo.parseJsonContent(content);
        ArrayList<Persister> persisters = new ArrayList<Persister>();
        for(PersisterTypeInfo pti : persitersTypeInfo.getPersisters()) {
            switch (pti.getType()){
                case "console":
                    persisters.add(new ConsolePersister());
                    break;
                case "elasticsearch":
                    String index = pti.getIndex();
                    String[] brokers = pti.getBrokers().split(",");
                    String[] hosts = new String[brokers.length];
                    int[] ports = new int[brokers.length];
                    for(int i=0; i< brokers.length; i++) {
                        String[] hostAndPort = brokers[i].split(":");
                        hosts[i] = hostAndPort[0];
                        ports[i] = Integer.parseInt(hostAndPort[1]);
                    }
                    EsPersisterInfo espi = new EsPersisterInfo(hosts, ports, index, index + "_type");
                    persisters.add(new EsPersister(espi));
                    break;
                default: break;
            }
        }
        return persisters;
    }
}
