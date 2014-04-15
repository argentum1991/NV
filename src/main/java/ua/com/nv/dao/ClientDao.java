package ua.com.nv.dao;

import ua.com.nv.server.Client;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public final class ClientDao {

    public static List<Client> getClientsId() {
        List<Client> clients = new ArrayList<>();
        Client testClient = new Client("robert");
        testClient.setPass("nayman");
        clients.add(testClient);
        return clients;
    }

    public static HashSet<String> getAssotiatedMsgWith(String clientId) {
        return new HashSet<>();
    }


}
