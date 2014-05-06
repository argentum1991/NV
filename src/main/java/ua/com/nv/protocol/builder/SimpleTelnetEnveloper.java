package ua.com.nv.protocol.builder;


import ua.com.nv.protocol.SimpleTelnetMsg;
import ua.com.nv.protocol.commander.util.ChatCommands;
import ua.com.nv.protocol.commander.util.ServiceCommands;

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
        msg.appendToHeader("Hello, " + user +"\r\n"  );
        msg.appendToHeader("You are in BROADCAST mode!\r\n Send HELP: and you know anything you want\r\n");
    }

    public void addUnknownUserHeader() {
        msg.appendToHeader("Sorry, but your login or pass is incorrect\r\n");
    }

    public void addSuccesfullyRegisterHeader(String userName) {
        msg.appendToHeader("Congratulations, you successfully add new user with nickname:" + userName+"\r\n");
    }

    public void addUnsuccessRegisterHeader(String userName) {
        msg.appendToHeader("Sorry, but user with this nickname:" + userName + " is present \r\n");
    }


    public void addUnknownCommandHeader(String command, String likeCommand) {
        msg.appendToHeader(String.format("Sorry, but this commander: %s is unknown\r\n", command));
        if (!likeCommand.isEmpty()) {
            msg.appendToHeader(String.format("but maybe you want:%s \r\n", likeCommand));
        }

    }
    public void clearLastLine(){
    msg.appendToHeader(ServiceCommands.IAC.getCode());
     msg.appendToHeader(ServiceCommands.EL.getCode());
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
