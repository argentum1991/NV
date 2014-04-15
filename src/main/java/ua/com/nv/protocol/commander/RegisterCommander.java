package ua.com.nv.protocol.commander;

import ua.com.nv.protocol.SimpleTelnetMsg;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class RegisterCommander extends AbstractCommander {



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
    public boolean isContinue() {
        return inProcess;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void processRequest(String clientRequest) {

        this.enveloper.setMsg(new SimpleTelnetMsg());

        if (!inProcess || stageIterator==null) {
            stageIterator = stages.iterator();
            inProcess = true;
            enveloper.addCommandInfoHeader(this.concreteCommand);
            return;
        }

        String nextStage = getNextStageCaption();
        enveloper.addMsgContent(nextStage);

        if (login == null) {
            login = clientRequest;
        } else if (pass1 == null) {
            pass1 = clientRequest;
        } else if (pass2 == null) {
            pass2 = clientRequest;

        } else {
            if (pass2.equals(pass1)){


                if (director.setDataForClientRegistration(login,pass1)) {
                    enveloper.addMsgContent("Ok, you successfully registered, Please login");
                    inProcess = false;
                } else {
                    enveloper.addMsgContent("Sorry, but user with this nickname:"+login+" is present");
                }
            }else{
              enveloper.addMsgContent("Sorry, but your pass1 is not equals pass2");
            }


            login = null;
            pass1 = null;
            pass2 = null;
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
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getReceiverId() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
