package ua.com.nv.dao;

import redis.clients.jedis.Jedis;
import ua.com.nv.server.Client;

import java.util.Collection;
import java.util.Map;
import java.util.Set;


public final class ClientDao {
    private static Jedis jedis;
    private static String undeliveredMsgAlias = "-<undelivered-msg>";
    private static String storedMsgAlias = "-<stored-msg>";
    private static String usersAlias = "userList";

    static {
        jedis = new Jedis("localhost", 6379);

    }

    public static Set<String> getClientsName() {
        return jedis.smembers(usersAlias);
    }

    public static Collection<String> getAssotiatedMsgWith(String userName) {
        Collection<String> msgs = jedis.smembers(userName + undeliveredMsgAlias);
        jedis.del(userName + undeliveredMsgAlias);
        return msgs;
    }

    public static void addClient(Client client) {
        String userName = client.getUserName();
        jedis.hmset(userName, ClientMapper.getMapFromClient(client));
        jedis.sadd(usersAlias, client.getUserName());
        jedis.sadd(client.getUserName() + undeliveredMsgAlias, "");
        jedis.lpush(client.getUserName() + storedMsgAlias, "");
    }

    public static boolean isClientExists(String userName) {
        Map<String, String> userMap = jedis.hgetAll(userName);
        return (userMap != null && !userMap.isEmpty());
    }

    public static void addUndeliveredMsgToClient(String msg, String userName) {
        jedis.sadd(userName + undeliveredMsgAlias, msg);
    }

    public static Client getClientByUserName(String userName) {
        Map<String, String> fields = jedis.hgetAll(userName);
        return ClientMapper.getClient(fields);
    }

    public static Collection<String> getStoredMsg(String userName) {
        String client = userName + storedMsgAlias;
        return jedis.lrange(client, 0, -1);
    }

    public static void addClientMsg(String msg, String userName) {
        String client = userName + storedMsgAlias;
        if (jedis.llen(client) > 30) {
            jedis.lpop(client);
        }
        jedis.rpush(client, msg);
    }


}
