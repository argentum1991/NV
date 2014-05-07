package ua.com.nv.protocol.director;


import ua.com.nv.protocol.SimpleTelnetMsg;
import ua.com.nv.protocol.builder.SimpleTelnetEnveloper;
import ua.com.nv.protocol.commander.AbstractCommander;
import ua.com.nv.protocol.commander.BroadcastCommander;
import ua.com.nv.protocol.commander.ChangeCommander;
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
    private static String regexForChangeCommand = "<ch:>";
    private AbstractCommander currentCommander = new WelcomeCommander();
    private ChangeCommander changeCommander = new ChangeCommander();
    private Sender sender;
    protected SimpleTelnetEnveloper enveloper = new SimpleTelnetEnveloper();

    public SimpleTelnetDirector(Sender sender) {
        this.sender = sender;
    }

    @Override
    public void processRequest(String clientRequest) {
        enveloper.setMsg(new SimpleTelnetMsg());

        currentCommander.processRequest(clientRequest);
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

    public boolean checkForChangeCommandRequest(String input) {
        Pattern p = Pattern.compile(regexForChangeCommand);
        Matcher matcher = p.matcher(input);
        if (matcher.find()) {
            return true;
        }
        return false;
    }


}
