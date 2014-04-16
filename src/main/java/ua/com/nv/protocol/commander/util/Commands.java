package ua.com.nv.protocol.commander.util;


public enum Commands {
    HELP("list all commands"),
    CONSUMERS("list all online consumers"),
    PRIVATE("enter to private mode, your msg will be send only for selected consumer"),
    BROADCAST("enter to public mode, your msg will be broadcasted to everyone"),
    LOGOUT("logout from current profile"),
    LOGIN("enter your credentials and principals and enter to chat"),
    REGISTER("add new user "),
    HISTORY("last 30 msg"),
    WELCOME("Welcome to chat!\n please try to login or register yourself.\n " +
            "Available for now commands are \n LOGIN:\n or REGISTER:\n"),
    HOME("Go to welcome Page");

    Commands(String explanation) {
        this.explanation = explanation;
    }

    public String getExplanation() {
        return explanation;
    }

    private String explanation;
}
