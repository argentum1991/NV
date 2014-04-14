package ua.com.nv.protocol.commander;


import ua.com.nv.protocol.SimpleTelnetMsg;

public class LogoutCommander extends AbstractCommander {
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
    public String getResponseMsg() {
        return enveloper.getResponseMsg();  //To change body of implemented methods use File | Settings | File Templates.
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
