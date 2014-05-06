package ua.com.nv.protocol.director;


import ua.com.nv.protocol.SimpleTelnetMsg;
import ua.com.nv.protocol.builder.SimpleTelnetEnveloper;
import ua.com.nv.protocol.commander.AbstractCommander;
import ua.com.nv.protocol.commander.BroadcastCommander;
import ua.com.nv.protocol.commander.WelcomeCommander;
import ua.com.nv.protocol.commander.util.ChatCommands;
import ua.com.nv.protocol.commander.util.CommandStatus;
import ua.com.nv.protocol.commander.util.CommanderBook;
import ua.com.nv.server.Client;
import ua.com.nv.server.ClientSession;
import ua.com.nv.server.Sender;
import ua.com.nv.server.util.ClientsBook;

import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SimpleTelnetDirector implements MsgDirector, SessionDirector {

    private final static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CommanderBook.class);
    ClientSession session = new ClientSession();
    private AbstractCommander currentCommander = new WelcomeCommander();
    private Sender sender;
    protected SimpleTelnetEnveloper enveloper = new SimpleTelnetEnveloper();

    public SimpleTelnetDirector(Sender sender) {
        this.sender = sender;
    }

    @Override
    public void processRequest(String clientRequest) {
        enveloper.setMsg(new SimpleTelnetMsg());
        CommandAndBody cb = getClientCommandAndContent(clientRequest);
        String command = cb.command;
        String content = cb.body;
        log.info("COMMAND:" + command + "--CONTENT:" + content);
        CommanderBook.CommanderAndStatus cms = CommanderBook.
                getCurrentCommander(currentCommander, command, session.getStatus());
        AbstractCommander nextCommander = cms.commander;
        if (nextCommander != null) {
            if (nextCommander.getClass() != currentCommander.getClass()) {
                this.currentCommander = nextCommander;
                currentCommander.setSessionDirector(this);
            }
        } else {
            CommandStatus status=cms.status;
            switch (status) {
                case FORBIDDEN_FOR_USER_STATUS:
                enveloper.addMsgHeader("This command is prohibited for you");
                break;
                case LOGICALLY_IMPOSSIBLE:
                enveloper.addMsgHeader("This command is logically impossible after current");
                break;
                case WRONG:
                enveloper.addMsgHeader("This command is absent");
            }
            return;
        }


        currentCommander.processRequest(content);
        String response = currentCommander.getResponseMsg();
        enveloper.addMsgContent(response);
        if (!currentCommander.inProcess()) {
            if (session.getStatus() == 0) {
                currentCommander = new WelcomeCommander();
                currentCommander.processRequest("");
                enveloper.addMsgContent(currentCommander.getResponseMsg());
            } else {
                currentCommander = new BroadcastCommander();
                currentCommander.setSessionDirector(this);

            }
        }


    }


    @Override
    public String getResponseMsg() {
        log.info("RESPONSE MSG IN DIRECTOR:" + currentCommander.getResponseMsg());
        return enveloper.getResponseMsg();

    }

    @Override
    public String getReceiverId() {
        return currentCommander.getReceiverId();
    }


    @Override
    public ClientSession getSession() {
        return session;  //To change body of implemented methods use File | Settings | File Templates.
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
        Client client = ClientsBook.bindSenderToClient(user, pass, sender);
        if (client != null) {
            session.setClient(client);
        }
    }

    @Override
    public boolean setDataForClientRegistration(String user, String pass) {
        return ClientsBook.addClient(user, pass);  //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    public void sessionInvalidate() {
        ClientsBook.unbindSenderFromClient(session.client.getUserName());
        session.setClient(null);
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
