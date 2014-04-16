package ua.com.nv.protocol;


import ua.com.nv.protocol.commander.Commands;
import ua.com.nv.protocol.commander.MsgEnveloper;

public class SimpleTelnetEnveloper implements MsgEnveloper {


    private SimpleTelnetMsg msg;

    public void addCommandInfoHeader(Commands command) {
        StringBuilder header = new StringBuilder(command.toString().toUpperCase() + ":" + "\n");
        header.append(command.getExplanation());
        header.append("\n");
        msg.appendToHeader(header.toString());
    }


    public void addWelcomeUserHeader(String user) {
        msg.appendToHeader("Hello, " + user + "\n");
    }

    public void addUnknownUserHeader() {
        msg.appendToHeader("Sorry, but your login or pass is incorrect\n");
    }

    public void addSuccesfullyRegisterHeader(String userName) {
        msg.appendToHeader("Congratulations, you successfully add new user with nickname:" + userName);
    }

    public void addUnsuccessRegisterHeader(String userName) {
        msg.appendToHeader("Sorry, but user with this nickname:" + userName + " is present");
    }


    public void addUnknownCommandHeader(String command, String likeCommand) {
        msg.appendToHeader(String.format("Sorry, but this commander: %s is unknown\n", command));
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
