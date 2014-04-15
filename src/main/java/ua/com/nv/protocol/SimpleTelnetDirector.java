package ua.com.nv.protocol;


import ua.com.nv.protocol.commander.Commander;
import ua.com.nv.protocol.commander.WelcomeCommander;
import ua.com.nv.protocol.commander.util.CommanderBook;
import ua.com.nv.protocol.commander.util.DirectedCommanderGraph;
import ua.com.nv.server.ClientSession;
import ua.com.nv.server.Sender;
import ua.com.nv.server.util.ClientsBook;


import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SimpleTelnetDirector implements MsgDirector, SessionDirector {

    private final static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CommanderBook.class);
    ClientSession session = new ClientSession();
    private Commander currentCommander = new WelcomeCommander();
    private Sender sender;

    public SimpleTelnetDirector(Sender sender) {
        this.sender = sender;
    }

    @Override
    public void processRequest(String clientRequest) {
        CommandAndBody cb = getClientCommandAndContent(clientRequest);
        String command = cb.command;
        String content = cb.body;
        log.info("COMMAND:" + command + "--CONTENT:" + content);
        Commander commander = getCurrentCommander(command);
        commander.processRequest(content);

    }

    @Override
    public String getResponseMsg() {
        log.info("RESPONSE MSG IN DIRECTOR:" + currentCommander.getResponseMsg());
        return currentCommander.getResponseMsg();

    }

    @Override
    public String getReceiverId() {
        return currentCommander.getReceiverId();
    }


    @Override
    public ClientSession getSession() {
        return session;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private Commander getCurrentCommander(String clientCommand) {
        if (currentCommander != null && currentCommander.isContinue()) {
            return currentCommander;
        }
        Commander nextCommander = CommanderBook.getCommander(clientCommand);
        if (nextCommander != null) {
            boolean isPossible = DirectedCommanderGraph.
                    isPossibleNextCommand(currentCommander.getCommandAlias(), nextCommander.getCommandAlias());
            if (isPossible) {
                this.currentCommander = nextCommander;
            }
        }
        return currentCommander;
    }


    private CommandAndBody getClientCommandAndContent(String request) {
        String regex = "^[A-Z]+:";//LOGOUT:, BROADCAST:, PRIVATE: and so on
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(request);
        String command = "";
        int end = 0;
        if (m.find()) {
            end = m.end();
            command = request.substring(m.start(), end);
        }
        String content = request.substring(end);
        return new CommandAndBody(command, content);
    }


    @Override
    public void setDataForClientSession(String user, String pass) {
        if (ClientsBook.bindSenderToClient(user, pass, sender)) {
            session.setClientId(user);
        }
    }

    @Override
    public boolean setDataForClientRegistration(String user, String pass) {
        return ClientsBook.addClient(user, pass);  //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    public void sessionInvalidate() {
        session.clientId = null;
        ClientsBook.unbindSenderFromClient(session.clientId);

    }

    private class CommandAndBody {
        String command;
        String body;

        CommandAndBody(String command, String body) {
            this.command = command;
            this.body = body;
        }
    }

}
