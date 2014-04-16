package ua.com.nv.protocol.commander;

import org.apache.log4j.Logger;
import ua.com.nv.protocol.SimpleTelnetMsg;
import ua.com.nv.server.ClientSession;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;



public class RegisterCommander extends AbstractCommander {

    private static final Logger log= Logger.getLogger(RegisterCommander.class);

    private String login;
    private String pass1;
    private String pass2;
    private Iterator<String> stageIterator;
    private List<String> stages;

    public RegisterCommander() {


        String[] commands = {"Please, enter your name:\n", "Please, enter your password:\n","Please, confirm your password:\n"};
        stages = Arrays.<String>asList(commands);
        this.concreteCommand = Commands.REGISTER;

    }



    @Override
    public void processRequest(String clientRequest) {

        this.enveloper.setMsg(new SimpleTelnetMsg());

        if (!inProcess || stageIterator == null) {
            stageIterator = stages.iterator();
            inProcess = true;
            String nextStage = getNextStageCaption();
            log.info("NEXT STAGE:" + nextStage);
            enveloper.addResponseCommandHeader(nextStage);
            return;
        }

        String nextStage = getNextStageCaption();
        log.info("NEXT STAGE:" + nextStage);



        if (login == null) {
            login = clientRequest;
            enveloper.addMsgContent(nextStage);
            log.info("LOGIN:" + login);
            log.info("PASS1:" + pass1);
            log.info("PASS2:" + pass2);

        } else if (pass1 == null) {

            pass1 = clientRequest;
            log.info("LOGIN:" + login);
            log.info("PASS1:" + pass1);
            log.info("PASS2:" + pass2);
        } else if (pass2 == null) {

            pass1 = clientRequest;
            log.info("LOGIN:" + login);
            log.info("PASS1:" + pass1);
            log.info("PASS2:" + pass2);

            boolean  isValid=    director.setDataForClientRegistration(login,pass2);


            if (isValid) {
                enveloper.addSuccesfullyRegisterHeader(login);
                inProcess = false;
            } else {
                enveloper.addUnknownUserHeader();
                enveloper.addUnsuccessRegisterHeader(login);
            }

            login = null;
            pass1 = null;
            pass2=null;
            stageIterator = null;
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
