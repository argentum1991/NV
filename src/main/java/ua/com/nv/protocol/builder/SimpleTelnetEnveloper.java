package ua.com.nv.protocol.builder;


import ua.com.nv.protocol.SimpleTelnetMsg;
import ua.com.nv.protocol.commander.util.ChatCommands;

public class SimpleTelnetEnveloper implements MsgEnveloper {


    private SimpleTelnetMsg msg;

    public SimpleTelnetEnveloper(){
        msg=new SimpleTelnetMsg();
    }
    public void addCommandInfoHeader(ChatCommands command) {
        StringBuilder header = new StringBuilder(command.toString().toUpperCase() + ":" );
        header.append(command.getExplanation());

        msg.appendToHeader(header.toString());
    }


    public void addWelcomeUserHeader(String user) {
        msg.appendToHeader("Hello, " + user  );
    }

    public void addUnknownUserHeader() {
        msg.appendToHeader("Sorry, but your login or pass is incorrect");
    }

    public void addSuccesfullyRegisterHeader(String userName) {
        msg.appendToHeader("Congratulations, you successfully add new user with nickname:" + userName);
    }

    public void addUnsuccessRegisterHeader(String userName) {
        msg.appendToHeader("Sorry, but user with this nickname:" + userName + " is present");
    }


    public void addUnknownCommandHeader(String command, String likeCommand) {
        msg.appendToHeader(String.format("Sorry, but this commander: %s is unknown", command));
        if (!likeCommand.isEmpty()) {
            msg.appendToHeader(String.format("but maybe you want:%s ", likeCommand));
        }

    }

    public void addResponseCommandHeader(String response) {
        msg.appendToHeader(response);
    }
    @Override
    public void addMsgContent(String content) {
        msg.appendToContent(content);
    }
    @Override
    public void addMsgHeader(String header){
        msg.appendToHeader(header);
    }


    public void setMsg(SimpleTelnetMsg msg) {
        this.msg = msg;
    }
   @Override
    public String getResponseMsg() {
        return msg.toString();
    }

}
