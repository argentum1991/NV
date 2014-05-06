package ua.com.nv.protocol.director;


import java.io.Reader;

public interface MsgDirector {


    public String getResponseMsg();

    public void processRequest(String request);

    public String getReceiverId();

}
