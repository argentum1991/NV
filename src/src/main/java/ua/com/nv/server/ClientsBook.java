package ua.com.nv.server;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


public class ClientsBook {
    private ConcurrentHashMap<String, Client> clients;
    private ConcurrentHashMap<String, HashSet<String>> undeliveredMsg;

    public void sendMsg(String clientAlias, String msg) {
        if (clientAlias.equals("BROADCAST")) {
            for (Client receiver : clients.values()) {
                deliverMsg(receiver, msg);
            }
        } else {
            Client receiver = clients.get(clientAlias);
            deliverMsg(receiver, msg);
        }
    }

    private void deliverMsg(Client receiver, String msg) {
        if (receiver.inOnlineMode()) {
            receiver.printMsg(msg);
        } else {
            undeliveredMsg.get(receiver.clientId).add(msg);
        }

    }

    public void addClient(List<String> clientId) {
        for (String keyId : clientId) {
            clients.putIfAbsent(keyId, new Client(keyId));
        }
    }

    public Client getClient(String clientId) {
        return clients.putIfAbsent(clientId, new Client(clientId));
    }


}
