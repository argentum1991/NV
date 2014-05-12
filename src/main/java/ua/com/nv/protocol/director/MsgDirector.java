package ua.com.nv.protocol.director;


import ua.com.nv.protocol.commander.DELIVERY_MODE;


public interface MsgDirector {


    public String getResponseMsg();

    public void processRequest(String request) throws UnsupportedOperationException;

    public void reInitMsg();
    public DELIVERY_MODE getMode();

}
