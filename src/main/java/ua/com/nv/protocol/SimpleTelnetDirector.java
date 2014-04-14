package ua.com.nv.protocol;


import ua.com.nv.protocol.commander.Commander;
import ua.com.nv.protocol.commander.WelcomeCommander;
import ua.com.nv.protocol.commander.util.CommanderBook;
import ua.com.nv.protocol.commander.util.DirectedCommanderGraph;
import ua.com.nv.server.ClientSecurityControl;
import ua.com.nv.server.ClientSession;


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
        Commander nextCommander = CommanderBook.getCommander(clientCommand);
        if (nextCommander != null) {
            DirectedCommanderGraph.
                    isPossibleNextCommand(currentCommander.getCommandAlias(), nextCommander.getCommandAlias());
            this.currentCommander = nextCommander;
        }
        return currentCommander;
    }
}
