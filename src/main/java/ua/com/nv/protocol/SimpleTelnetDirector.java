package ua.com.nv.protocol;


import ua.com.nv.protocol.commander.Commander;
import ua.com.nv.protocol.commander.WelcomeCommander;
import ua.com.nv.protocol.commander.util.CommanderBook;
import ua.com.nv.protocol.commander.util.DirectedCommanderGraph;
import ua.com.nv.server.ClientSecurityControl;
import ua.com.nv.server.ClientSession;

import ua.com.nv.protocol.commander.util.CommanderBook;
import ua.com.nv.server.Sender;
import ua.com.nv.server.util.ClientsBook;



import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SimpleTelnetDirector implements MsgDirector, ClientSecurityControl, SessionDirector {
    ClientSession session;
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
        Commander commander = getCurrentCommander(command);
        commander.processRequest(content);
        commander.getResponseMsg();
    }

    @Override
    public String getResponseMsg() {
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
            DirectedCommanderGraph.
                    isPossibleNextCommand(currentCommander.getCommandAlias(), nextCommander.getCommandAlias());
            this.currentCommander = nextCommander;
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
    public ClientSession getClientSession() {
        return session;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setDataForClientSession(String user, String pass) {
        ClientsBook.bindSenderToClient(user, pass, sender);
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
