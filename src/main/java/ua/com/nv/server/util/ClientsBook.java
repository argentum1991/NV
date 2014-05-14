package ua.com.nv.server.util;

import ua.com.nv.dao.ClientDao;
import ua.com.nv.protocol.commander.DELIVERY_MODE;
import ua.com.nv.server.Client;
import ua.com.nv.server.Sender;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


public final class ClientsBook {

    public static ConcurrentHashMap<String, Client> onLineClients = new ConcurrentHashMap<>();


    public static boolean transmitMsg(DELIVERY_MODE mode, String msg) {
        if (mode == DELIVERY_MODE.BROADCAST) {
            broadcasting(msg);
        } else {
            String whom = mode.getReceiver();
            return privatecasting(msg, whom);
        }
        return true;
    }

    private static void broadcasting(String msg) {

        for (String userName : ClientDao.getClientsName()) {
            if (onLineClients.contains(userName)) {
                Client receiver = onLineClients.get(userName);
                deliverMsg(receiver, msg);
            } else {
                addUndeliveredMsg(userName, msg);
            }

        }
    }

    private static boolean privatecasting(String msg, String whom) {
        if (onLineClients.contains(whom)) {
            Client receiver = onLineClients.get(whom);
            deliverMsg(receiver, msg);
            return true;
        } else {
            addUndeliveredMsg(whom, msg);
        }
        return false;
    }


    private static void deliverMsg(Client receiver, String msg) {

        receiver.sendMsg(msg);


    }

    public static void addClients(List<Client> clientList) {
        for (Client curClient : clientList) {
            addClient(curClient.getUserName(), curClient.getPass());
        }
    }

    public static boolean addClient(String userName, String pass) {
        if (!ClientDao.isClientExists(userName)) {
            Client inserted = new Client(userName, pass, 1);
            inserted.setPass(pass);
            ClientDao.addClient(inserted);
            return true;
        }
        return false;
    }


    public static void unbindSenderFromClient(String userName) {
        if (onLineClients.contains(userName)) {
            Client client = onLineClients.get(userName);
            onLineClients.remove(userName);
            client.setOfflineMode();
        }
    }


    public static Client bindSenderToClient(String userName, String pass, Sender sender) {
        if (ClientDao.isClientExists(userName)) {
            Client curClient = ClientDao.getClientByUserName(userName);
            if (curClient.getPass().equals(pass)) {
                curClient.setOnlineMode(sender);
                onLineClients.putIfAbsent(userName, curClient);
                return curClient;
            }
        }
        return null;
    }


    private static void addUndeliveredMsg(String userName, String msg) {
        ClientDao.addMsgToClient(msg, userName);
    }


    public static void getUndeliveredMsgFromStock(String userName) {
        if (onLineClients.contains(userName)) {
            Client curClient = onLineClients.get(userName);
            Collection<String> set = ClientDao.getAssotiatedMsgWith(userName);
            for (String msg : set) {
                if (!msg.isEmpty()){
                    curClient.sendMsg(msg);
                }
             }
        }
    }

    public static Collection<Client> getAllClients() {
        Collection<String> allNames = ClientDao.getClientsName();
        Set<Client> clients = new HashSet<Client>();
        for (String curName : allNames) {
            if (onLineClients.containsKey(curName)) {
                clients.add(onLineClients.get(curName));
            } else {
                clients.add(new Client(curName,"",1));
            }
         }

      return clients;
    }


}


