package ua.com.nv.protocol.commander;

import org.apache.log4j.Logger;
import ua.com.nv.protocol.commander.util.ChatCommands;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;



public class RegisterCommander  extends AbstractCommander{

    private static final Logger log= Logger.getLogger(RegisterCommander.class);

    private String login;
    private String pass1;
    private String pass2;
    private Iterator<String> stageIterator;
    private List<String> stages;

    public RegisterCommander() {


        String[] commands = {"Please, enter your name:\r\n", "Please, enter your password:\r\n","Please, confirm your password:\r\n"};
        stages = Arrays.<String>asList(commands);
        this.concreteCommand = ChatCommands.REGISTER;

    }



    @Override
    public void processRequest(String clientRequest) {



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
    public boolean isBreakable(){
        return false;
    }
}
