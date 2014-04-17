package ua.com.nv.protocol.commander;


import ua.com.nv.protocol.SimpleTelnetMsg;
import ua.com.nv.protocol.builder.MsgEnveloper;
import ua.com.nv.protocol.builder.SimpleTelnetEnveloper;
import ua.com.nv.protocol.commander.util.Commands;
import ua.com.nv.protocol.director.SessionDirector;
import ua.com.nv.server.ClientSession;


public abstract class AbstractCommander implements Commander {
    protected boolean inProcess = false;
    protected Commands concreteCommand;
    protected SimpleTelnetEnveloper enveloper=new SimpleTelnetEnveloper();
    SessionDirector director;

    @Override
    public String getResponseMsg() {
        String response= enveloper.getResponseMsg();  //To change body of implemented methods use File | Settings | File Templates.
        enveloper.setMsg(new SimpleTelnetMsg());
        return response;
    }




    public void processRequest(String clientRequest,String preparedMsg){
    enveloper.addMsgContent(preparedMsg);
    processRequest(clientRequest);
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
    public boolean inProcess() {
        return inProcess;
    }

    @Override
    public boolean isBreakable() {
        return true;
    }




    @Override
    public String getReceiverId() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }




}
