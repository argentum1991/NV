package ua.com.nv.protocol.director;


import java.io.Reader;

public interface MsgDirector {
    public void processClientInput(Reader reader);

    public boolean nextCycle();

    public String getResponseMsg();

    public void processRequest(String request);

    public String getReceiverId();

}
