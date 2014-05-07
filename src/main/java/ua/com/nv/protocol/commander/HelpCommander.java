package ua.com.nv.protocol.commander;


import ua.com.nv.protocol.commander.util.ChatCommands;

public class HelpCommander  extends AbstractCommander {
    public HelpCommander() {
        this.concreteCommand = ChatCommands.HELP;
    }

    @Override
    public void processRequest(String clientRequest) {
       inProcess=true;

        for (ChatCommands curCommand : ChatCommands.values()) {
            enveloper.addCommandInfoHeader(curCommand);
        }
    }






}
