package ua.com.nv.protocol.commander;

import ua.com.nv.protocol.MsgDirector;
import ua.com.nv.protocol.SessionDirector;


public interface Commander extends Transaction, MsgDirector{
    public void setSessionDirector(SessionDirector director);
}
