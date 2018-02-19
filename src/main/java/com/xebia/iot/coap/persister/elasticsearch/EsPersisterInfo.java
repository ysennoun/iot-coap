package com.xebia.iot.coap.persister.elasticsearch;

public class EsPersisterInfo {

    private String[] esClusterHost;
    private int[] esClusterPort;
    private String indexName;
    private String documentTypeName;

    public EsPersisterInfo(String[] esClusterHost, int[] esClusterPort, String indexName, String documentTypeName) {

        this.esClusterHost = esClusterHost;
        this.esClusterPort = esClusterPort;
        this.indexName = indexName;
        this.documentTypeName = documentTypeName;
    }

    public String[] getEsClusterHost() {
        return esClusterHost;
    }

    public int[] getEsClusterPort() {
        return esClusterPort;
    }

    public String getIndexName() { return indexName; }

    public String getDocumentTypeName() {
        return documentTypeName;
    }
}
