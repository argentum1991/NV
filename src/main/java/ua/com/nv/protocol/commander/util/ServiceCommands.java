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
    IAC(255);

    private int code;

    ServiceCommands(int code) {
        this.code = code;

    }

    public byte getCode() {
        return (byte) code;
    }


}
