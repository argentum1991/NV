package ua.com.nv.protocol.commander;


public class LogoutCommander extends AbstractCommander {
    public LogoutCommander() {
        this.concreteCommand = Commands.LOGOUT;
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

    @Override
    public boolean isContinue() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
