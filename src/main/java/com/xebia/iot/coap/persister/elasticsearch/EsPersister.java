package com.xebia.iot.coap.persister.elasticsearch;


import com.xebia.iot.coap.persister.Persister;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class EsPersister extends Persister {

    private EsPersisterInfo persisterInfo;
    private TransportClient client;

    public EsPersister(EsPersisterInfo persisterInfo) {
        this.persisterInfo = persisterInfo;
        initClient();
    }

    private void initClient() {
        try {
            client = new PreBuiltTransportClient(Settings.EMPTY);
            for(int i=0; i<persisterInfo.getEsClusterHost().length; i++)
                client.addTransportAddress(
                        new TransportAddress(InetAddress.getByName(
                                persisterInfo.getEsClusterHost()[i]),
                                persisterInfo.getEsClusterPort()[i]
                        )
                );
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void persiste(String data) {
        client.prepareIndex(persisterInfo.getIndexName(), persisterInfo.getDocumentTypeName())
                .setSource(data);
        client.close();
    }
}
