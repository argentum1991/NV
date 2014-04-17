package ua.com.nv.protocol.commander;


import ua.com.nv.protocol.builder.SimpleTelnetEnveloper;
import ua.com.nv.protocol.SimpleTelnetMsg;
import ua.com.nv.protocol.commander.util.Commands;

public class HelpCommander  extends AbstractCommander {
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






}
