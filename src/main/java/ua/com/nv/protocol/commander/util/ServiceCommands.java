package ua.com.nv.protocol.commander.util;


public enum ServiceCommands {
    SB(250),
    SE(240),
    GA(249),
    WILL(251),
    WONT(252),
    DO(253),
    DONT(254),
    DM(242),
    EL(248),
    IAC(255);

    private char code;

    ServiceCommands(int code) {
        this.code =(char) code;

    }

    public char getCode() {
        return  code;
    }


}
