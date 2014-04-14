package ua.com.nv.protocol.commander;


import ua.com.nv.protocol.SimpleTelnetMsg;

public class HelpCommander extends AbstractCommander {
    public HelpCommander() {
        this.concreteCommand = Commands.CONSUMERS;
    }

    @Override
    public void processRequest(String clientRequest) {
        this.enveloper.setMsg(new SimpleTelnetMsg());
        for (Commands curCommand : Commands.values()) {
            enveloper.addMsgContent(curCommand.getExplanation());
        }
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
