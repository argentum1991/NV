package ua.com.nv.protocol.commander;


import ua.com.nv.protocol.commander.util.ChatCommands;

public class LogoutCommander  extends AbstractCommander {
    public LogoutCommander() {
        this.concreteCommand = ChatCommands.LOGOUT;
    }

    @Override
    public void processRequest(String clientRequest) {
        this.director.sessionInvalidate();

        enveloper.addMsgContent("You out");
    }





}
