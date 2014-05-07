package ua.com.nv.dao;

import ua.com.nv.server.Client;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public final class ClientDao {

    public static List<Client> getClientsId() {
        List<Client> clients = new ArrayList<>();
        Client testClient1 = new Client("robert",1);
        testClient1.setPass("nayman");

        Client testClient2 = new Client("erik",1);
        testClient1.setPass("berman");


        clients.add(testClient1);
        clients.add(testClient2);
        return clients;
    }

    public static HashSet<String> getAssotiatedMsgWith(String clientId) {
        return new HashSet<>();
    }


}
