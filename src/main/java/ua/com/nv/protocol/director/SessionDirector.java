package ua.com.nv.protocol.director;

import ua.com.nv.server.ClientSession;


public interface SessionDirector {
    public ClientSession getSession();

    public void setDataForClientSession(String user, String pass);

    public boolean setDataForClientRegistration(String user, String pass);

    public void getUndeliveredMsgFromStock(String user);

    public void saveMsgInBuffer(String msg);

    public void sessionInvalidate();

}
