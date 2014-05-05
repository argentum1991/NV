package ua.com.nv.protocol.commander.util;


public enum ChatCommands {
    /*
    50-137	Unassigned
     */

    HELP("list all commands", 50),
    CONSUMERS("list all online consumers", 51),
    PRIVATE("enter to private mode, your msg will be send only for selected consumer", 52),
    BROADCAST("enter to public mode, your msg will be broadcasted to everyone", 53),
    LOGOUT("logout from current profile", 54),
    LOGIN("enter your credentials and principals and enter to chat", 55),
    REGISTER("add new user ", 56),
    HISTORY("last 30 msg", 57),
    WELCOME("Welcome to chat!\n please try to login or register yourself.\n " +
            "Available for now commands are \n LOGIN:\n or REGISTER:\n", 58),
    HOME("Go to welcome Page", 59);


    private String explanation;
    private int code;

    ChatCommands(String explanation, int code) {
        this.explanation = explanation;
        this.code = code;
    }

    public String getExplanation() {
        return explanation;
    }

    public byte getCode() {
        return (byte) code;
    }


}
