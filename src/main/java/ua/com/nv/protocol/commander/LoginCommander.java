package ua.com.nv.protocol.commander;

import ua.com.nv.protocol.SimpleTelnetEnveloper;


public class LoginCommander extends AbstractCommander {
    SimpleTelnetEnveloper enveloper;

    public LoginCommander() {
        this.concreteCommand = Commands.LOGIN;
    }

    @Override
    public boolean isContinue() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void processRequest(String clientRequest) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getResponseMsg() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getReceiverId() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
