package ua.com.nv.dao;


import ua.com.nv.server.Client;

import java.util.Map;

public final class ClientMapper {
    public static Client ClientMapper(Map<String, String> fields) {
        String userName = fields.get("userName");
        String pass = fields.get("pass");
        int status = (Integer.parseInt(fields.get("status")));

        Client client = new Client(userName, status);
        return client;
    }



}
