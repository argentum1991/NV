package ua.com.nv.protocol.commander;

import org.apache.log4j.Logger;
import ua.com.nv.protocol.SimpleTelnetMsg;
import ua.com.nv.protocol.commander.util.ChatCommands;
import ua.com.nv.protocol.commander.util.CommandStatus;
import ua.com.nv.protocol.commander.util.CommanderBook;


public class ChangeCommander extends AbstractCommander {
    private static Logger logger = Logger.getLogger(ChangeCommander.class);
    static String commandPattern = "^[A-Z]+:";//LOGOUT:, BROADCAST:, PRIVATE: and so on
    static String header1 = "ATTENTION!\r\n, check command:\r\n";


    public AbstractCommander processRequest(String request, AbstractCommander currentCommander) {
        enveloper.setMsg(new SimpleTelnetMsg());
        if (!inProcess()) {
            inProcess = true;
            enveloper.addMsgHeader(header1);
            logger.info(request);
            addCommandExplanation();
        } else if (request.matches(commandPattern)) {

            return getCommander(currentCommander, request);


        } else {

            inProcess = true;
            enveloper.addMsgContent(CommandStatus.WRONG.getExplanation());
        }

        return currentCommander;

    }


    @Override
    public boolean isBreakable() {
        return false;
    }

    private void addCommandExplanation() {
        for (ChatCommands curCommand : ChatCommands.values()) {
            enveloper.addCommandInfoHeader(curCommand);
        }
    }

    private AbstractCommander getCommander(AbstractCommander currentCommander, String command) {

        CommanderBook.CommanderAndStatus cms = CommanderBook.
                getCurrentCommander(currentCommander, command, director.getSession().getStatus());
        AbstractCommander nextCommander = cms.commander;

        if (nextCommander != null) {
            inProcess = false;
            if (nextCommander.getClass() != currentCommander.getClass()) {
                currentCommander = nextCommander;
                currentCommander.setSessionDirector(director);
            }
        }

        enveloper.addMsgContent(cms.status.getExplanation());
        logger.info(cms.status.getExplanation());
        logger.info("next command: "+currentCommander);
        return currentCommander;

    }

    @Override
    public void processRequest(String request) {
        throw new UnsupportedOperationException();
    }
}
