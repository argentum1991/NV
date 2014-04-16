package ua.com.nv.protocol.commander;


import ua.com.nv.protocol.builder.SimpleTelnetEnveloper;
import ua.com.nv.protocol.SimpleTelnetMsg;
import ua.com.nv.protocol.commander.util.Commands;

public class BroadcastCommander extends AbstractCommander<SimpleTelnetEnveloper> {


   public BroadcastCommander() {
        this.concreteCommand = Commands.BROADCAST;
    }

    @Override
    public void processRequest(String clientRequest) {
        enveloper.setMsg(new SimpleTelnetMsg());
        enveloper.addMsgContent(clientRequest);

    }




    @Override
    public String getReceiverId() {
        return "BROADCAST";  //To change body of implemented methods use File | Settings | File Templates.
    }


}
