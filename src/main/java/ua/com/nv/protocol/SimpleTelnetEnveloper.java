package ua.com.nv.protocol;


import ua.com.nv.protocol.commander.Commands;

public class SimpleTelnetEnveloper {



    private SimpleTelnetMsg msg;

    public void addCommandInfoHeader(Commands command) {
        StringBuilder header = new StringBuilder("Use: " + command.toString() + "\n");
        header.append(command.getExplanation());
        msg.appendToHeader(header.toString());
    }



    public void addWelcomeUserHeader(String user) {
        msg.appendToHeader("Hello, " + user + "\n");
    }
    public void addUnknownUserHeader() {
        msg.appendToHeader("Sorry, but your login or pass is incorrect\n");
    }

    public void addUnknownCommandHeader(String command, String likeCommand) {
        msg.appendToHeader(String.format("Sorry, but this commander: %s is unknown\n", command));
        if (!likeCommand.isEmpty()) {
            msg.appendToHeader(String.format("but maybe you want:%s ", likeCommand));
        }

    }

    public void addResponseCommandHeader(String response) {

    }

    public void addMsgContent(String content) {

    }



    public void setMsg(SimpleTelnetMsg msg) {
        this.msg = msg;
    }
    public String getResponseMsg(){
     return msg.toString();
    }

}
