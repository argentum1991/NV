package ua.com.nv.protocol.commander;


import ua.com.nv.protocol.builder.SimpleTelnetEnveloper;
import ua.com.nv.protocol.SimpleTelnetMsg;
import ua.com.nv.protocol.commander.util.Commands;

public class WelcomeCommander  extends AbstractCommander {


    public WelcomeCommander() {
        this.concreteCommand = Commands.WELCOME;
    }

    @Override
    public void processRequest(String clientRequest) {
      inProcess=true;
        this.enveloper.addCommandInfoHeader(concreteCommand);

    }





}
