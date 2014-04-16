package ua.com.nv.protocol.commander;


import ua.com.nv.protocol.SessionDirector;
import ua.com.nv.server.ClientSession;


public abstract class AbstractCommander<T extends MsgEnveloper> implements Commander {
    protected boolean inProcess = false;
    protected Commands concreteCommand;
    protected T enveloper;
    SessionDirector director;

    @Override
    public String getResponseMsg() {
        return enveloper.getResponseMsg();  //To change body of implemented methods use File | Settings | File Templates.
    }

    protected void putStampOn(ClientSession session) {
        String stamp = String.format("AUTOR:%s \n", session.client.getUserName());
        enveloper.addMsgHeader(stamp);
    }

    @Override
      public String getCommandAlias() {
        return concreteCommand.toString() + ":";
    }
    @Override
    public void setSessionDirector(SessionDirector director) {
        this.director = director;

    }
    @Override
    public boolean inProcess(){
    return inProcess;
    }
    @Override
    public boolean isBreakable(){
    return true;
    }


}
