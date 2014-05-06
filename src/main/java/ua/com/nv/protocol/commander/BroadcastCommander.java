package ua.com.nv.protocol.commander;


import ua.com.nv.protocol.commander.util.ChatCommands;

public class BroadcastCommander extends AbstractCommander {


    public BroadcastCommander() {
        this.concreteCommand = ChatCommands.BROADCAST;
    }

    @Override
    public void processRequest(String clientRequest) {
       inProcess=true;
        putStampOn(director.getSession());
        enveloper.addMsgContent(clientRequest+"\r\n");
    }


    @Override
    public String getReceiverId() {
        return "BROADCAST";
    }


}
