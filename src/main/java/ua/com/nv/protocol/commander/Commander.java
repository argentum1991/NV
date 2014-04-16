package ua.com.nv.protocol.commander;

import ua.com.nv.protocol.director.MsgDirector;
import ua.com.nv.protocol.director.SessionDirector;



public interface Commander extends Transaction, MsgDirector{
    public void setSessionDirector(SessionDirector director);
    public String getCommandAlias();
}
