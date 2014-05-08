package ua.com.nv.protocol.commander;


import ua.com.nv.protocol.commander.util.ChatCommands;
import ua.com.nv.server.DELIVERY_MODE;

public class BroadcastCommander extends AbstractCommander {


    public BroadcastCommander() {
        this.concreteCommand = ChatCommands.BROADCAST;
    }

    @Override
    public void processRequest(String clientRequest) {
        inProcess = true;
        if (!clientRequest.isEmpty()) {
            putStampOn(director.getSession());
            enveloper.addMsgContent(clientRequest + "\r\n");
        }
    }

    @Override
    public DELIVERY_MODE getMode(){
        return DELIVERY_MODE.BROADCAST;
    }






}
