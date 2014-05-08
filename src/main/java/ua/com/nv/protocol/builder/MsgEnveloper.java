package ua.com.nv.protocol.builder;


import ua.com.nv.protocol.commander.util.ChatCommands;

public interface MsgEnveloper<T> {

    public void addMsgContent(String content);

    public String getResponseMsg();

    public void setMsg(T msg);

    public void addMsgHeader(String header);

    public void addCommandInfoHeader(ChatCommands command);


    public void addWelcomeUserHeader(String user);

    public void addUnknownUserHeader();

    public void addSuccesfullyRegisterHeader(String userName);

    public void addUnsuccessRegisterHeader(String userName);

    public void addUnknownCommandHeader(String command, String likeCommand);

    public void addResponseCommandHeader(String response);

    public void addUnknownReceiverHeader(String receiver);
}
