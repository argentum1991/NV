package ua.com.nv.protocol.director;


import ua.com.nv.protocol.SimpleTelnetMsg;
import ua.com.nv.protocol.builder.SimpleTelnetEnveloper;
import ua.com.nv.protocol.commander.*;
import ua.com.nv.protocol.commander.util.ChatCommands;
import ua.com.nv.protocol.commander.util.CommandStatus;
import ua.com.nv.protocol.commander.util.CommanderBook;
import ua.com.nv.server.Client;
import ua.com.nv.server.ClientSession;
import ua.com.nv.server.DELIVERY_MODE;
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
    private boolean tryChangeCommand = false;
    private Sender sender;
    protected SimpleTelnetEnveloper enveloper = new SimpleTelnetEnveloper();

    public SimpleTelnetDirector(Sender sender) {
        this.sender = sender;
        changeCommander.setSessionDirector(this);
        currentCommander.setSessionDirector(this);
    }

    @Override
    public void processRequest(String clientRequest) {
        enveloper.setMsg(new SimpleTelnetMsg());
        String response = "";

        if (tryChangeCommand || checkForChangeCommandRequest(clientRequest)) {
            currentCommander = changeCommander.processRequest(clientRequest, currentCommander);
            enveloper.addMsgContent(changeCommander.getResponseMsg());
            tryChangeCommand = changeCommander.inProcess();
            return;
        } else {
            currentCommander.processRequest(clientRequest);
            response = currentCommander.getResponseMsg();
        }

        enveloper.addMsgContent(response);
        if (!currentCommander.inProcess()) {
            if (currentCommander.getClass() == WelcomeCommander.class) {
                tryChangeCommand = true;
                changeCommander.processRequest(clientRequest, currentCommander);
            } else if (session.getStatus() == 0) {
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
        log.info("Current commander:"+currentCommander+ "--RESPONSE MSG IN DIRECTOR:" + currentCommander.getResponseMsg());
        return enveloper.getResponseMsg();

    }

    @Override
    public DELIVERY_MODE getMode() {
        return currentCommander.getMode();
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
        this.currentCommander = new WelcomeCommander();
        currentCommander.processRequest("");
    }

    private boolean checkForChangeCommandRequest(String input) {
        Pattern p = Pattern.compile(regexForChangeCommand);
        Matcher matcher = p.matcher(input);
        if (matcher.find()) {
            return true;
        }
        return false;
    }


}
