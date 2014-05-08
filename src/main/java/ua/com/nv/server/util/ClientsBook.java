package ua.com.nv.server.util;

import ua.com.nv.server.Client;
import ua.com.nv.server.DELIVERY_MODE;
import ua.com.nv.server.Sender;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


public final class ClientsBook {
    private static ConcurrentHashMap<String, Client> clients = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, HashSet<String>> undeliveredMsg = new ConcurrentHashMap<>();

    private ClientsBook() {

    }

    public static boolean transmitMsg(DELIVERY_MODE mode, String msg) {
        if (mode == DELIVERY_MODE.BROADCAST) {
            broadcasting(msg);
        } else {
            String whom = mode.getReceiver();
          return   privatecasting(msg, whom);
        }
        return true;
    }

    private static void broadcasting(String msg) {
        for (Client receiver : clients.values()) {
            deliverMsg(receiver, msg);
        }
    }

    private static boolean privatecasting(String msg, String whom) {
        Client receiver = clients.get(whom);
        if (receiver == null) {
            return false;
        }
        deliverMsg(receiver, msg);
        return true;
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
            undeliveredMsg.putIfAbsent(curClient.getUserName(), new HashSet<String>());
        }
    }

    public static boolean addClient(String userName, String pass) {
        if (!clients.containsKey(userName)) {
            Client inserted = new Client(userName, 1);
            inserted.setPass(pass);
            clients.put(userName, inserted);
            undeliveredMsg.putIfAbsent(inserted.getUserName(), new HashSet<String>());
            return true;
        }
        return false;
    }

    public static Collection<Client> getAllClients() {
        return clients.values();
    }


    public static void unbindSenderFromClient(String userName) {
        if (clients.containsKey(userName)) {
            Client client = clients.get(userName);
            client.setOfflineMode();
        }
    }


    public static Client bindSenderToClient(String userName, String pass, Sender sender) {
        if (clients.containsKey(userName)) {
            Client curClient = clients.get(userName);
            if (curClient.getPass().equals(pass)) {
                curClient.setOnlineMode(sender);
                Collection<String> set = getUndeliveredMsg(curClient.getUserName());
                deliverMsgToAppearedClient(curClient, set);
                return curClient;
            }
        }
        return null;
    }

    public static Collection<String> getUndeliveredMsg(String userName) {
        return undeliveredMsg.get(userName);

    }

    public static void addUndeliveredMsg(String userName, String msg) {
        if (undeliveredMsg.contains(userName)) {
            undeliveredMsg.get(userName).add(msg);
        }
    }

    private static void deliverMsgToAppearedClient(Client client, Collection<String> set) {
        for (String msg : set) {
            client.sendMsg(msg);

        }
        set.clear();

    }


}
