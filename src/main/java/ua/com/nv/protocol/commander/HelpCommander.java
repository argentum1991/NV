package ua.com.nv.protocol.commander;


import ua.com.nv.protocol.SimpleTelnetMsg;

public class HelpCommander extends AbstractCommander {
    public HelpCommander() {
        this.concreteCommand = Commands.HELP;
    }

    @Override
    public void processRequest(String clientRequest) {
        this.enveloper.setMsg(new SimpleTelnetMsg());
        for (Commands curCommand : Commands.values()) {
            enveloper.addCommandInfoHeader(curCommand);
        }
    }



    @Override
    public String getReceiverId() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


}
