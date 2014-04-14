package ua.com.nv.protocol.commander;





import ua.com.nv.protocol.SimpleTelnetMsg;
import ua.com.nv.server.ClientSession;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;



public class LoginCommander extends AbstractCommander {


    private String login;
    private String pass;
    private Iterator<String> stageIterator;
    private List<String> stages;

    public LoginCommander() {

        String[] commands = {"Please, enter your name:\n", "Please, enter your password:\n"};
        stages = Arrays.<String>asList(commands);
        this.concreteCommand = Commands.LOGIN;

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
        } else if (pass == null) {
            pass = clientRequest;

        } else {
            director.setDataForClientSession(login, pass);
            ClientSession session = director.getClientSession();
            if (session.isAuthenticated()) {
                enveloper.addWelcomeUserHeader(login);
                inProcess = false;
            } else {
                enveloper.addUnknownUserHeader();
            }

            login = null;
            pass = null;
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
