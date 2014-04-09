package ua.com.nv.protocol;


public enum Commands {
    HELP("list all commands"),
    CONSUMERS("list all online consumers"),
    PRIVATE("enter to private mode, your msg will be send only for selected consumer"),
    BROADCAST("enter to public mode, your msg will be broadcasted to everyone"),
    LOGOUT("logout from current profile"),
    SEND("send msg "),
    HISTORY("last 30 msg");
    Commands(String explanation){
    this.explanation=explanation;
    }

    public String getExplanation() {
        return explanation;
    }

    private String explanation;
}
