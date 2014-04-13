package ua.com.nv.protocol.commander;

import ua.com.nv.protocol.MsgDirector;


public interface Commander extends Transaction, MsgDirector {
    public String getCommandAlias();

}
