package ua.com.nv.protocol.commander;


import ua.com.nv.protocol.builder.SimpleTelnetEnveloper;
import ua.com.nv.protocol.SimpleTelnetMsg;
import ua.com.nv.protocol.commander.util.Commands;

public class WelcomeCommander  extends AbstractCommander<SimpleTelnetEnveloper> {


    public WelcomeCommander() {
        this.concreteCommand = Commands.WELCOME;
    }

    @Override
    public void processRequest(String clientRequest) {
        this.enveloper.setMsg(new SimpleTelnetMsg());
        this.enveloper.addCommandInfoHeader(concreteCommand);

    }

    @Override
    public String getResponseMsg() {
        return enveloper.getResponseMsg();
    }

    @Override
    public String getReceiverId() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


}
