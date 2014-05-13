package ua.com.nv.dao;

import redis.clients.jedis.Jedis;
import ua.com.nv.server.Client;

import java.util.Collection;

import java.util.Set;


public final class ClientDao {
    private static Jedis jedis;
    private static String msgPattern="-<msg>";
    static {
        jedis = new Jedis("localhost",6379);
    }

    public static Set<String> getClientsName() {
       return jedis.hkeys("users");
    }

    public static Collection<String> getAssotiatedMsgWith(String userName) {
       Collection<String> msgs= jedis.lrange(userName+msgPattern,0,-1);
       jedis.del(userName);
        return msgs;
    }

    public static void addClient(Client client) {
        String userName = client.getUserName();
        jedis.set(userName, "userName", client.getUserName());
        jedis.set(userName, "pass", client.getPass());
        jedis.set(userName, "status", String.valueOf(client.getStatus()));
    }

    public static void addMsgToClient(String msg, String userName) {
        jedis.sadd(userName+msgPattern, msg);
    }
    /*
    Jedis jedis=new Jedis("localhost",6379);
        jedis.del("users");
        jedis.sadd("users","rosbert","jon","erik");
        Map<String,String> fields=new HashMap<String, String>();

        fields.put("name","rosbert");
        fields.put("age","12");
        jedis.hmset("rosbert",fields);
        fields.clear();
        fields.put("name","erik");
        fields.put("age","22");
        jedis.hmset("erik",fields);


        //get
        Collection<String> users=jedis.smembers("users");
        for (String user:users){
          Map<String,String> params=jedis.hgetAll(user);
          System.out.print(params);
        }
     */


}
