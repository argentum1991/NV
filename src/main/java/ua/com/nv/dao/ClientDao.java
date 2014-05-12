package ua.com.nv.dao;

import redis.clients.jedis.Jedis;
import ua.com.nv.server.Client;

import java.util.HashSet;
import java.util.Set;


public final class ClientDao {
    private static Jedis jedis;

    static {
        jedis = new Jedis("localhost");
    }

    public static Set<Client> getClientsId() {
        Set<Client> clients = new HashSet<>();
        Client testClient1 = new Client("robert", 1);
        testClient1.setPass("nayman");

        Client testClient2 = new Client("erik", 1);
        testClient2.setPass("berman");


        clients.add(testClient1);
        clients.add(testClient2);
        return clients;
    }

    public static HashSet<String> getAssotiatedMsgWith(String clientId) {
        return new HashSet<>();
    }

    public static void addClient(Client client) {
        String userName = client.getUserName();
        jedis.set(userName, "userName", client.getUserName());
        jedis.set(userName, "pass", client.getPass());
        jedis.set(userName, "status", String.valueOf(client.getStatus()));
    }

    public static void addMsgToClient(String msg, String userName) {
        jedis.sadd(userName, msg);
    }


}
