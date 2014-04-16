package ua.com.nv.protocol.builder;


public interface MsgEnveloper {

    public void addMsgContent(String content);

    public String getResponseMsg();
    public void addMsgHeader(String header);

}
