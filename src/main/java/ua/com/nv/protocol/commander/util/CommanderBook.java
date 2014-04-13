package ua.com.nv.protocol.commander.util;

import ua.com.nv.protocol.commander.*;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


public final class CommanderBook {
    private static ConcurrentHashMap<String, Class> commanders=new ConcurrentHashMap<>();
    /*
        HELP("list all commands"),
    CONSUMERS("list all online consumers"),
    PRIVATE("enter to private mode, your msg will be send only for selected consumer"),
    BROADCAST("enter to public mode, your msg will be broadcasted to everyone"),
    LOGOUT("logout from current profile"),
    LOGIN("enter your credentials and principals and enter to chat"),
    REGISTER("add new user "),
    SEND("send msg "),
    HISTORY("last 30 msg"),
    WELCOME("Welcome to chat!\n please try to login or register yourself.\n " +
            "Available for now commands are \n LOGIN:\n or REGISTER:\n");
     */
    static {
        commanders.put("LOGIN:", LoginCommander.class);
        commanders.put("LOGOUT:", LogoutCommander.class);
        commanders.put("PRIVATE:", PrivatecastCommander.class);
        commanders.put("BROADCAST:", BroadcastCommander.class);
        commanders.put("CONSUMERS:", ConsumersCommander.class);
        commanders.put("HELP:", HelpCommander.class);
        commanders.put("REGISTER:",RegisterCommander.class);
        commanders.put("HISTORY:", HelpCommander.class);


    }

    public static Commander getCommander(String request) {
        Commander returned = null;
        if (!commanders.contains(request)) {
            return returned;
        }
        try {
            returned = (Commander) commanders.get(request).newInstance();
            return returned;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return returned;
    }

    public static Set<String> getCommanderAliases() {
        return commanders.keySet();
    }


}
