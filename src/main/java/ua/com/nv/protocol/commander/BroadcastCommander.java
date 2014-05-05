package ua.com.nv.protocol.commander;


import ua.com.nv.protocol.commander.util.ChatCommands;

public class BroadcastCommander extends AbstractCommander {


    public BroadcastCommander() {
        this.concreteCommand = ChatCommands.BROADCAST;
    }

    @Override
    public void processRequest(String clientRequest) {
       inProcess=true;
        enveloper.addMsgContent(clientRequest);
    }


    @Override
    public String getReceiverId() {
        return "BROADCAST";  //To change body of implemented methods use File | Settings | File Templates.
    }


}
