package ua.com.nv.dao;

import redis.clients.jedis.Jedis;
import ua.com.nv.server.Client;

import java.util.Collection;
import java.util.Map;
import java.util.Set;


public final class ClientDao {
    private static Jedis jedis;
    private static String msgPattern = "-<msg>";

    static {
        jedis = new Jedis("localhost", 6379);

    }

    public static Set<String> getClientsName() {
        return jedis.smembers("users");
    }

    public static Collection<String> getAssotiatedMsgWith(String userName) {
        Collection<String> msgs = jedis.smembers(userName + msgPattern);
        jedis.del(userName + msgPattern);
        return msgs;
    }

    public static void addClient(Client client) {
        String userName = client.getUserName();
        jedis.hmset(userName,ClientMapper.getMapFromClient(client));

    }

    public static boolean isClientExists(String userName) {
        Map<String, String> userMap = jedis.hgetAll(userName);
        return (userMap != null && !userMap.isEmpty());
    }

    public static void addMsgToClient(String msg, String userName) {
        jedis.sadd(userName + msgPattern, msg);
    }

    public static Client getClientByUserName(String userName) {
        Map<String, String> fields = jedis.hgetAll(userName);
        return ClientMapper.getClient(fields);
    }


}
