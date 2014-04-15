package ua.com.nv.protocol.commander;


import ua.com.nv.protocol.SimpleTelnetMsg;

public class WelcomeCommander extends AbstractCommander {


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

    @Override
    public boolean isContinue() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
