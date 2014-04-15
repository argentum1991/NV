package ua.com.nv.server.util;

import ua.com.nv.server.Client;
import ua.com.nv.server.Sender;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


public final class ClientsBook {
    private static ConcurrentHashMap<String, Client> clients = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, HashSet<String>> undeliveredMsg = new ConcurrentHashMap<>();

    private ClientsBook(){

    }
    public static void transmitMsg(String userName, String msg) {
        if (userName.equals("BROADCAST")) {
            for (Client receiver : clients.values()) {
                deliverMsg(receiver, msg);
            }
        } else {
            Client receiver = clients.get(userName);
            deliverMsg(receiver, msg);
        }
    }

    private static void deliverMsg(Client receiver, String msg) {
        if (receiver.inOnlineMode()) {
            receiver.sendMsg(msg);
        } else {
            undeliveredMsg.get(receiver.getUserName()).add(msg);
        }

    }

    public static void addClients(List<Client> clientList) {
        for (Client curClient : clientList) {
            clients.putIfAbsent(curClient.getUserName(), curClient);
        }
    }

    public static boolean addClient(String userName, String pass) {
        Client inserted = new Client(userName);
        inserted.setPass(pass);
        Client got = clients.putIfAbsent(userName, inserted);
        return inserted.getPass().equals(got.getPass());

    }

    public static Collection<Client> getAllClients() {
        return clients.values();
    }


    public static void unbindSenderFromClient(String userName) {
        Client client = clients.get(userName);
        client.setOfflineMode();
    }


    public static boolean bindSenderToClient(String userName, String pass, Sender sender) {
        if (clients.contains(userName)) {
            Client curClient = clients.get(userName);
            if (curClient.getPass().equals(pass)) {
                curClient.setOnlineMode(sender);
                return true;
            }
        }
        return false;
    }

    public static void addUndeliveredMsg() {

    }

}
