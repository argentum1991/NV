package ua.com.nv.protocol.commander;


import org.apache.log4j.Logger;
import ua.com.nv.protocol.SimpleTelnetMsg;
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

        String[] commands = {"Please, enter your name:\n", "Please, enter your password:\n"};
        stages = Arrays.<String>asList(commands);
        this.concreteCommand = Commands.LOGIN;
        stageIterator=stages.iterator();
    }



    @Override
    public void processRequest(String clientRequest) {

        this.enveloper.setMsg(new SimpleTelnetMsg());
        String nextStage = getNextStageCaption();
        if (!inProcess ){
            inProcess = true;
            stageIterator = stages.iterator();
            nextStage = getNextStageCaption();
            log.info("NEXT STAGE:" + nextStage);
            enveloper.addResponseCommandHeader(nextStage);
            enveloper.addMsgContent(nextStage);
            log.info("NEXT STAGE:" + nextStage);
            return;
        }



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
    public String getResponseMsg() {
        return enveloper.getResponseMsg();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getReceiverId() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
    @Override
    public boolean isBreakable(){
     return false;
    }

}
