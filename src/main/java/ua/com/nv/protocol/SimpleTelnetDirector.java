package ua.com.nv.protocol;


import ua.com.nv.protocol.commander.Commander;
import ua.com.nv.protocol.commander.WelcomeCommander;
import ua.com.nv.server.ClientSecurityControl;
import ua.com.nv.server.ClientSession;
import ua.com.nv.protocol.commander.util.CommanderBook;


public class SimpleTelnetDirector implements MsgDirector, ClientSecurityControl {
    private Commander currentCommander = new WelcomeCommander();
    private ClientSession session;

    public SimpleTelnetDirector() {

    }

    @Override
    public void processRequest(String clientRequest) {
        getCurrentCommander(clientRequest).processRequest(clientRequest);
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
        Commander commander = CommanderBook.getCommander(clientCommand);
        if (commander != null) {
            this.currentCommander = commander;
        }
        return currentCommander;
    }
}
