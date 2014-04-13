package ua.com.nv.protocol.commander;

/**
 * Created with IntelliJ IDEA.
 * User: Land
 * Date: 13.04.14
 * Time: 12:33
 * To change this template use File | Settings | File Templates.
 */
public class LogoutCommander extends AbstractCommander {
    public LogoutCommander() {
        this.concreteCommand = Commands.LOGOUT;
    }

    @Override
    public void processRequest(String clientRequest) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getResponseMsg() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getReceiverId() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isContinue() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
