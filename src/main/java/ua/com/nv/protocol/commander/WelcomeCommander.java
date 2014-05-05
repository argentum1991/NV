package ua.com.nv.protocol.commander;


import ua.com.nv.protocol.commander.util.ChatCommands;

public class WelcomeCommander extends AbstractCommander {


    public WelcomeCommander() {
        this.concreteCommand = ChatCommands.WELCOME;
    }

    @Override
    public void processRequest(String clientRequest) {
        inProcess = true;
        this.enveloper.addCommandInfoHeader(concreteCommand);
    }


}
