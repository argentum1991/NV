package ua.com.nv.protocol;

import ua.com.nv.server.ClientSession;


public interface SessionDirector {
    public ClientSession getSession();

    public void setDataForClientSession(String user, String pass);
    public boolean setDataForClientRegistration(String user, String pass);

    public void sessionInvalidate();

}
