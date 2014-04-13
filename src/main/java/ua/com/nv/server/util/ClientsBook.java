package ua.com.nv.server.util;

import ua.com.nv.server.Client;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


public final class ClientsBook {
    private static ConcurrentHashMap<String, Client> clients;
    private static ConcurrentHashMap<String, HashSet<String>> undeliveredMsg;

    public static void transmitMsg(String clientId, String msg) {
        if (clientId.equals("BROADCAST")) {
            for (Client receiver : clients.values()) {
                deliverMsg(receiver, msg);
            }
        } else {
            Client receiver = clients.get(clientId);
            deliverMsg(receiver, msg);
        }
    }

    private static void deliverMsg(Client receiver, String msg) {
        if (receiver.inOnlineMode()) {
            receiver.sendMsg(msg);
        } else {
            undeliveredMsg.get(receiver.clientId).add(msg);
        }

    }

    public static void addClients(List<String> clientId) {
        for (String keyId : clientId) {
            clients.putIfAbsent(keyId, new Client(keyId));
        }
    }

    public static void addClient(Client client) {

    }



    public static void unbindConnectionFromClient(String clientId) {

    }

    public static void addUndeliveredMsg() {

    }


}
