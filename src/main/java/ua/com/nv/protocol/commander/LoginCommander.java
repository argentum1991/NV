package ua.com.nv.protocol.commander;


import org.apache.log4j.Logger;
import ua.com.nv.protocol.commander.util.ChatCommands;
import ua.com.nv.server.ClientSession;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class LoginCommander extends AbstractCommander {

    private final Logger log = Logger.getLogger(LoginCommander.class);
    private String login;
    private String pass;
    private Iterator<String> stageIterator;
    private List<String> stages;

    public LoginCommander() {

        String[] commands = {"Please, enter your name:\r\n", "Please, enter your password:\r\n"};
        stages = Arrays.<String>asList(commands);
        this.concreteCommand = ChatCommands.LOGIN;
        stageIterator = stages.iterator();
    }

    @Override
    public void processRequest(String clientRequest) {

        if (!inProcess) {
            inProcess = true;
            stageIterator = stages.iterator();
            String nextStage = getNextStageCaption();
            log.info("NEXT STAGE:" + nextStage);
            enveloper.addResponseCommandHeader(nextStage);
            return;
        }
        String nextStage = getNextStageCaption();
        if (login == null) {
            login = clientRequest;
            enveloper.addMsgContent(nextStage);
            log.info("LOGIN:" + login);
            log.info("PASS:" + pass);
        } else if (pass == null) {

            pass = clientRequest;
            log.info("LOGIN:" + login);
            log.info("PASS:" + pass);

            director.setDataForClientSession(login, pass);
            ClientSession session = director.getSession();
            if (session.isAuthenticated()) {

                enveloper.addWelcomeUserHeader(login);

            } else {
                enveloper.addUnknownUserHeader();
            }

            inProcess = false;
            login = null;
            pass = null;

        }


    }

    private String getNextStageCaption() {

        if (stageIterator.hasNext()) {
            return stageIterator.next();

        } else {
            return null;
        }

    }


    @Override
    public boolean isBreakable() {
        return false;
    }

}
