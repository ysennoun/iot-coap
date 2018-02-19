package com.xebia.iot.coap.persister;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

public class PersistersTypeInfo {

    private ArrayList<PersisterTypeInfo> persisters;

    public ArrayList<PersisterTypeInfo>  getPersisters() {
        return persisters;
    }

    public static PersistersTypeInfo parseJsonContent(String json) {
        PersistersTypeInfo result = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            result = objectMapper.readValue(json, PersistersTypeInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
