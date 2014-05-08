package ua.com.nv.protocol.director;


import ua.com.nv.server.DELIVERY_MODE;

import java.io.Reader;

public interface MsgDirector {


    public String getResponseMsg();

    public void processRequest(String request) throws UnsupportedOperationException;

    public void reInitMsg();
    public DELIVERY_MODE getMode();

}
