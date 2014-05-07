package ua.com.nv.protocol.commander.util;


public enum CommandStatus {
    OK("Command is selected"),
    LOGICALLY_IMPOSSIBLE("Desired command is logically impossible for now\r\n"),
    FORBIDDEN_FOR_USER_STATUS("Desired command is forbidden for your status\r\n"),
    WRONG("Such command is absent, please clarify\r\n");

    CommandStatus(String explanation) {
        this.explanation = explanation;
    }

    private String explanation;

    public String getExplanation() {
        return explanation;
    }

}
