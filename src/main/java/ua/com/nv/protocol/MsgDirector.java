package ua.com.nv.protocol;


public interface MsgDirector {
    public void processRequest(String clientRequest);
    public String getResponseMsg();
    public String getReceiverId();

}
