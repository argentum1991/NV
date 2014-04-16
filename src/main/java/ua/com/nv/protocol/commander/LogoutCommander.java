package ua.com.nv.protocol.commander;


import ua.com.nv.protocol.SimpleTelnetEnveloper;
import ua.com.nv.protocol.SimpleTelnetMsg;

public class LogoutCommander  extends AbstractCommander<SimpleTelnetEnveloper> {
    public LogoutCommander() {
        this.concreteCommand = Commands.LOGOUT;
    }

    @Override
    public void processRequest(String clientRequest) {
        this.director.sessionInvalidate();
        enveloper.setMsg(new SimpleTelnetMsg());
        enveloper.addMsgContent("You out");
    }



    @Override
    public String getReceiverId() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


}
