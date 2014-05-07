package ua.com.nv.protocol.commander.util;


public enum ChatCommands {
    /*
    50-137	Unassigned
     */


    CONSUMERS("List all online consumers\r\n",(char) 51),
    PRIVATE("Your msg will be send only for selected consumer\r\n",(char) 52),
    BROADCAST("Your msg will be broadcasted to everyone\r\n",(char) 53),
    LOGOUT("Logout from current profile \r\n",(char) 54),
    LOGIN("Enter your credentials and principals and enter to chat \r\n",(char) 55),
    REGISTER("Add new user\r\n ",(char) 56),
    HISTORY("Last 30 msg \r\n",(char) 57),
    WELCOME("Welcome to chat!\r\n Please try to login or register yourself.\r\n " +
            "Available for now commands are LOGIN: or REGISTER:\r\n", (char)58),
    HOME("Go to welcome Page\r\n", (char)59);


    private String explanation;
    private char code;

    ChatCommands(String explanation, char code) {
        this.explanation = explanation;
        this.code = code;
    }

    public String getExplanation() {
        return explanation;
    }

    public char getCode() {
        return  code;
    }


}
