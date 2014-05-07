package ua.com.nv.protocol.commander;

import ua.com.nv.protocol.SimpleTelnetMsg;
import ua.com.nv.protocol.commander.util.ChatCommands;
import ua.com.nv.protocol.commander.util.CommanderBook;



public class ChangeCommander extends AbstractCommander {
    static String commandPattern = "^[A-Z]+:";//LOGOUT:, BROADCAST:, PRIVATE: and so on
    static String header1 = "Please, check command\r\n" + ChatCommands.HELP.getExplanation();


    public AbstractCommander processRequest(String request, AbstractCommander currentCommander) {
        enveloper.setMsg(new SimpleTelnetMsg());
        if (!inProcess()) {
            inProcess = true;
            enveloper.addMsgContent(header1);
        } else {
            if (request.matches(commandPattern)) {
                return getCommander(currentCommander, request);
            }
        }
        return currentCommander;

    }


    @Override
    public boolean isBreakable() {
        return false;
    }

    private AbstractCommander getCommander(AbstractCommander currentCommander, String command) {

        CommanderBook.CommanderAndStatus cms = CommanderBook.
                getCurrentCommander(currentCommander, command, this.director.getSession().getStatus());
        AbstractCommander nextCommander = cms.commander;
        if (nextCommander != null) {
            inProcess = false;
            if (nextCommander.getClass() != currentCommander.getClass()) {
                currentCommander = nextCommander;
                currentCommander.setSessionDirector(director);

            }

        }
        enveloper.addMsgContent(cms.status.toString());
        return currentCommander;

    }

    @Override
    public void processRequest(String request) {
        throw new UnsupportedOperationException();
    }
}
