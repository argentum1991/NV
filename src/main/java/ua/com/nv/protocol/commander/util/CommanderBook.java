package ua.com.nv.protocol.commander.util;

import org.apache.log4j.Logger;
import ua.com.nv.protocol.commander.*;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


public final class CommanderBook {
    private final static Logger log = Logger.getLogger(CommanderBook.class);
    private static ConcurrentHashMap<String, Class> commanders = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<Class, Integer> commanderStatus = new ConcurrentHashMap<>();

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
        commanders.put("REGISTER:", RegisterCommander.class);
        commanders.put("HISTORY:", HistoryCommander.class);
        commanders.put("WELCOME:", WelcomeCommander.class);


        commanderStatus.put(LoginCommander.class, 0);
        commanderStatus.put(LogoutCommander.class, 1);
        commanderStatus.put(ConsumersCommander.class, 1);
        commanderStatus.put(BroadcastCommander.class, 1);

        commanderStatus.put(WelcomeCommander.class, 0);
        commanderStatus.put(PrivatecastCommander.class, 1);
        commanderStatus.put(RegisterCommander.class, 0);

    }



    @SuppressWarnings("checked")
    private static CommanderAndStatus getCommander(String request) {
        CommanderAndStatus returned = null;

        if (!commanders.containsKey(request)) {
            log.info("REQUEST-" + request + " - NO");
            return new CommanderAndStatus(CommandStatus.WRONG, null);
        }

        try {

            AbstractCommander commander = (AbstractCommander) commanders.get(request).newInstance();
            log.info("REQUEST-" + request + " - COMMANDER" + commander);
            returned = new CommanderAndStatus(CommandStatus.OK, commander);
            return returned;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return returned;
    }

    public static Set<String> getCommanderAliases() {
        return commanders.keySet();
    }

    public static CommanderAndStatus getCurrentCommander(AbstractCommander currentCommander, String clientCommand, int status) {
       /*
        if (clientCommand.equals(ChatCommands.HOME.toString()+":")) {
            this.currentCommander = CommanderBook.getCommander(ChatCommands.WELCOME.toString()+":");
            return this.currentCommander;
        }
        */
        return getNextCommander(currentCommander, clientCommand, status);

    }


    private static CommanderAndStatus getNextCommander(AbstractCommander currentCommander, String clientCommand, int status) {

        CommanderAndStatus commanderAndStatus = CommanderBook.getCommander(clientCommand);
        AbstractCommander nextCommander = commanderAndStatus.commander;
        if (nextCommander != null) {
            boolean logically = checkForLogicallyPossibleNextCommand(currentCommander, nextCommander);
            boolean access = checkForClientAccessNextCommand(nextCommander, status);
            if (!logically) {
                commanderAndStatus.status = CommandStatus.LOGICALLY_IMPOSSIBLE;
                commanderAndStatus.commander = currentCommander;
                return commanderAndStatus;
            }
            if (!access) {
                commanderAndStatus.status = CommandStatus.FORBIDDEN_FOR_USER_STATUS;
                commanderAndStatus.commander = currentCommander;
             }

        } else {
            commanderAndStatus.commander = currentCommander;

        }
        return commanderAndStatus;
    }


    private static boolean checkForLogicallyPossibleNextCommand(Commander currentCommander, Commander nextCommander) {
        return DirectedCommanderGraph.
                isPossibleNextCommand(currentCommander.getCommandAlias(),
                        nextCommander.getCommandAlias());
    }

    private static boolean checkForClientAccessNextCommand(Commander nextCommand, int status) {
        int minKey = commanderStatus.get(nextCommand.getClass());
        if (status >= minKey) {
            return true;
        } else {
            return false;
        }
    }

    public static class CommanderAndStatus {
        public CommandStatus status;
        public AbstractCommander commander;

        public CommanderAndStatus(CommandStatus status, AbstractCommander commander) {
            this.status = status;
            this.commander = commander;
        }
    }


}
