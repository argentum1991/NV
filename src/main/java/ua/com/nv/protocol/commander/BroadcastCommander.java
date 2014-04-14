package ua.com.nv.protocol.commander;


import ua.com.nv.protocol.SimpleTelnetMsg;
import ua.com.nv.server.ClientSession;

public class BroadcastCommander extends AbstractCommander {


    BroadcastCommander() {
        this.concreteCommand = Commands.BROADCAST;
    }

    @Override
    public void processRequest(String clientRequest) {
        enveloper.setMsg(new SimpleTelnetMsg());
        enveloper.addMsgContent(clientRequest);
    }



    @Override
    public String getReceiverId() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isContinue() {
        return inProcess;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
