package ua.com.nv.dao;


import ua.com.nv.server.Client;

import java.util.HashMap;
import java.util.Map;

public final class ClientMapper {
    private static String[] properties={"userName","status","pass"};
    public static Client ClientMapper(Map<String, String> fields) {
        String userName = fields.get("userName");
        String pass = fields.get("pass");
        int status = (Integer.parseInt(fields.get("status")));
        Client client = new Client(userName, status);
        return client;
    }
    public static Map<String,String> getMapFromClient(Client client){
        HashMap<String,String> fields=new HashMap<>();
        fields.put(properties[0],client.getUserName());
        fields.put(properties[1],String.valueOf(client.getStatus()));
        fields.put(properties[2],String.valueOf(client.getPass()));
        return fields;
    }




}
